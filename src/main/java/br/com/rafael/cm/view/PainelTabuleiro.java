package br.com.rafael.cm.view;

import br.com.rafael.cm.controller.CampoController;
import br.com.rafael.cm.controller.TabuleiroController;
import br.com.rafael.cm.model.Campo;
import br.com.rafael.cm.view.assets.BotaoCampo;

import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro extends JPanel {

    public PainelTabuleiro(TabuleiroController tabuleiroController) {
        System.out.println(tabuleiroController.getTabuleiro().getColunas());
        setLayout(new GridLayout(tabuleiroController.getTabuleiro().getLinhas(), tabuleiroController.getTabuleiro().getColunas()));
        tabuleiroController.paraCada(c -> add(new BotaoCampo(c)));
        tabuleiroController.getTabuleiro().registrarObservador(e -> {
            if(e.isGanhou()){
                JOptionPane.showMessageDialog(this, "Voce ganhou");
            }else{
                JOptionPane.showMessageDialog(this, "Voce perdeu");
            }
        });
    }
}
