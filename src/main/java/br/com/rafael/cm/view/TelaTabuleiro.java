package br.com.rafael.cm.view;

import br.com.rafael.cm.controller.TabuleiroController;

import javax.swing.*;

public class TelaTabuleiro extends JFrame {

    public static void main(String[] args) {
        new TelaTabuleiro();
    }

    public TelaTabuleiro(){
        TabuleiroController tabuleiroController = new TabuleiroController(16, 30, 50);
        add(new PainelTabuleiro(tabuleiroController));
        setTitle("Campo minado");
        setVisible(true);
        setSize(690, 438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }


}
