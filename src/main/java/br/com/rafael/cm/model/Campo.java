package br.com.rafael.cm.model;

import java.util.ArrayList;
import java.util.List;

public class Campo {
    private final int linha;
    private final int coluna;
    private boolean isAberto;
    private boolean isMinado;
    private boolean isMarcado;
    private List<Campo> vizinhos;

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.vizinhos = new ArrayList<>();
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public boolean isAberto() {
        return isAberto;
    }

    public void setAberto(boolean aberto) {
        isAberto = aberto;
    }

    public boolean isMinado() {
        return isMinado;
    }

    public void setMinado(boolean minado) {
        isMinado = minado;
    }

    public boolean isMarcado() {
        return isMarcado;
    }

    public void setMarcado(boolean marcado) {
        isMarcado = marcado;
    }

    public List<Campo> getVizinhos() {
        return vizinhos;
    }
}
