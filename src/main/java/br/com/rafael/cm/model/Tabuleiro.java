package br.com.rafael.cm.model;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private int quantidadeMinas;

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
}
