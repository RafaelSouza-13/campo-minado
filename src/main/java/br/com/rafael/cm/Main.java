package br.com.rafael.cm;


import br.com.rafael.cm.controller.TabuleiroController;
import br.com.rafael.cm.view.TabuleiroView;

public class Main {
    public static void main(String[] args) {
        TabuleiroController tabuleiroController = new TabuleiroController(9, 9, 6);
        TabuleiroView tabuleiro = new TabuleiroView(tabuleiroController);
    }
}