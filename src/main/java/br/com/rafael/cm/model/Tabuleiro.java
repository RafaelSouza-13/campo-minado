package br.com.rafael.cm.model;

import br.com.rafael.cm.enuns.CampoEvento;
import br.com.rafael.cm.interfaces.CampoObservador;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Tabuleiro implements CampoObservador {
    private int linhas;
    private int colunas;
    private int quantidadeMinas;
    private final List<Consumer<Boolean>> observadores = new ArrayList<>();


    private final List<Campo> campos = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int quantidadeMinas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.quantidadeMinas = quantidadeMinas;
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }

    public int getQuantidadeMinas() {
        return quantidadeMinas;
    }

    public void setQuantidadeMinas(int quantidadeMinas) {
        this.quantidadeMinas = quantidadeMinas;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    @Override
    public void evento(Campo campo, CampoEvento evento) {
        if(evento == CampoEvento.EXPLODIR){
            System.out.println("Perdeu");
        }else if(evento == CampoEvento.ABRIR){
            System.out.println("Abrir");
        }
    }

    public void registrarObservador(Consumer<Boolean> observador){
        observadores.add(observador);
    }

    public void notificarObservadores(boolean resultado){
        observadores.stream().forEach(o -> o.accept(resultado));
    }
}
