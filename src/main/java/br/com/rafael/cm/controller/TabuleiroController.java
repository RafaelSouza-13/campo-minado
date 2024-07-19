package br.com.rafael.cm.controller;

import br.com.rafael.cm.model.Campo;
import br.com.rafael.cm.model.Tabuleiro;

public class TabuleiroController {
    Tabuleiro tabuleiro;
    CampoController campoController;

    public void iniciarJogo(int linha, int coluna, int quantidadeMinas){
        tabuleiro = new Tabuleiro(linha, coluna, quantidadeMinas);
        campoController = new CampoController();
        gerarCampos();
        associarVizinhos();
        sortearMinas();
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
            minasArmadas = tabuleiro.getCampos().stream().filter(c -> c.isMinado()).count();
            int aleatorio = (int) (Math.random() * tabuleiro.getCampos().size());
            campoController.minarCampo(tabuleiro.getCampos().get(aleatorio));
        }
    }

    public boolean objetivoAlcancado(){
        return tabuleiro.getCampos().stream().allMatch(c -> campoController.campoConcluido(c));
    }

    public void reinicarJogo(){
        tabuleiro.getCampos().stream().forEach(c -> campoController.reiniciarCampo(c));
        sortearMinas();
    }


}
