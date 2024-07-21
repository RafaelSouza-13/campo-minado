package br.com.rafael.cm.controller;

import br.com.rafael.cm.enuns.CampoEvento;
import br.com.rafael.cm.interfaces.CampoObservador;
import br.com.rafael.cm.model.Campo;
import br.com.rafael.cm.model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TabuleiroController implements CampoObservador {
    Tabuleiro tabuleiro;
    CampoController campoController;
    private final List<Consumer<Boolean>> observadores = new ArrayList<>();

    public TabuleiroController(int linha, int coluna, int quantidadeMinas){
        tabuleiro = new Tabuleiro(linha, coluna, quantidadeMinas);
        campoController = new CampoController();
        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }

    public Tabuleiro getTabuleiro(){
        return tabuleiro;
    }

    public void paraCada(Consumer<Campo> funcao){
        tabuleiro.getCampos().forEach(funcao);
    }

    public void registrarObservador(Consumer<Boolean> observador){
        observadores.add(observador);
    }

    private void notificarObservadores(boolean resultado){
        observadores.stream().forEach(o -> o.accept(resultado));
    }

    public void abrir(int linha, int coluna){

        tabuleiro.getCampos().parallelStream().
                filter(c -> c.getLinha() == linha && c.getColuna() == coluna).
                findFirst().ifPresent(c -> campoController.abrir(c));

    }

    public void alterarMarcar(int linha, int coluna){
        tabuleiro.getCampos().parallelStream().
                filter(c -> c.getLinha() == linha && c.getColuna() == coluna).
                findFirst().ifPresent(c -> campoController.alternarMarcacao(c));
    }

    private void gerarCampos() {
        campoController.registraObservador(this);
        for(int linha = 0; linha < tabuleiro.getLinhas(); linha++){
            for(int coluna = 0; coluna < tabuleiro.getColunas(); coluna ++){
                Campo campo = new Campo(linha, coluna);
                tabuleiro.getCampos().add(campo);
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


    public void mostrarMinas(){
        tabuleiro.getCampos().stream().filter(c -> c.isMinado()).
                forEach(c -> c.setAberto(true));
    }


    @Override
    public void evento(Campo campo, CampoEvento evento) {
        if(evento == CampoEvento.EXPLODIR){
            mostrarMinas();
            System.out.println("Perdeu");
            notificarObservadores(false);
        }else if(objetivoAlcancado()){
            notificarObservadores(true);
            System.out.println("Ganhou");
        }
    }
}
