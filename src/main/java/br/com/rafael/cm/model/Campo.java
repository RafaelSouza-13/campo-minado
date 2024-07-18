package br.com.rafael.cm.model;

import java.util.ArrayList;
import java.util.List;

public class Campo {
    private final int linha;
    private final int coluna;
    private boolean isAberto;
    private boolean isMinado;
    private boolean isMarcado;
    private List<Campo> vizinhos = new ArrayList<>();

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }
}
