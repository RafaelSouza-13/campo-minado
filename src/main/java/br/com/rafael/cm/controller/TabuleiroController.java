package br.com.rafael.cm.controller;

import br.com.rafael.cm.exception.ExplosaoException;
import br.com.rafael.cm.model.Campo;
import br.com.rafael.cm.model.Tabuleiro;

public class TabuleiroController {
    Tabuleiro tabuleiro;
    CampoController campoController;

    public TabuleiroController(int linha, int coluna, int quantidadeMinas){
        tabuleiro = new Tabuleiro(linha, coluna, quantidadeMinas);
        campoController = new CampoController();
        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }

    public void abrir(int linha, int coluna){
        try{
            tabuleiro.getCampos().parallelStream().
                    filter(c -> c.getLinha() == linha && c.getColuna() == coluna).
                    findFirst().ifPresent(c -> campoController.abrir(c));
        }catch (ExplosaoException e){
            tabuleiro.getCampos().forEach(c -> c.setAberto(true));
            throw e;
        }

    }

    public void alterarMarcar(int linha, int coluna){
        tabuleiro.getCampos().parallelStream().
                filter(c -> c.getLinha() == linha && c.getColuna() == coluna).
                findFirst().ifPresent(c -> campoController.alternarMarcacao(c));
    }

    private void gerarCampos() {
        for(int linha = 0; linha < tabuleiro.getLinhas(); linha++){
            for(int coluna = 0; coluna < tabuleiro.getColunas(); coluna ++){
                tabuleiro.getCampos().add(new Campo(linha, coluna));
            }
        }
    }

    private void associarVizinhos() {
        for(Campo campo: tabuleiro.getCampos()){
            for(Campo vizinho: tabuleiro.getCampos()){
                campoController.adicionarVizinho(campo, vizinho);
            }
        }
    }

    private void sortearMinas(){
        long minasArmadas = 0;
        while(minasArmadas < tabuleiro.getQuantidadeMinas()){
            int aleatorio = (int) (Math.random() * tabuleiro.getCampos().size());
            campoController.minarCampo(tabuleiro.getCampos().get(aleatorio));
            minasArmadas = tabuleiro.getCampos().stream().filter(c -> c.isMinado()).count();
        }
    }

    public boolean objetivoAlcancado(){
        return tabuleiro.getCampos().stream().allMatch(c -> campoController.campoConcluido(c));
    }

    public void reinicarJogo(){
        tabuleiro.getCampos().stream().forEach(c -> campoController.reiniciarCampo(c));
        sortearMinas();
    }

    public String exibeTabuleiro(){
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for(int coluna = 0; coluna < tabuleiro.getColunas(); coluna++){
            sb.append(" ");
            sb.append(coluna);
            sb.append(" ");
        }
        sb.append("\n");
        int index = 0;
        for(int linha = 0; linha < tabuleiro.getLinhas(); linha++){
            sb.append(linha);
            sb.append(" ");
            for(int coluna = 0; coluna < tabuleiro.getColunas(); coluna++){
                sb.append(" ");
                sb.append(campoController.exibeCampo(tabuleiro.getCampos().get(index)));
                sb.append(" ");
                index++;
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
