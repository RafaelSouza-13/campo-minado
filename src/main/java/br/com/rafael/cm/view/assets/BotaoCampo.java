package br.com.rafael.cm.view.assets;

import br.com.rafael.cm.controller.CampoController;
import br.com.rafael.cm.enuns.CampoEvento;
import br.com.rafael.cm.interfaces.CampoObservador;
import br.com.rafael.cm.model.Campo;

import javax.swing.*;
import java.awt.*;

public class BotaoCampo extends JButton implements CampoObservador {
    private Campo campo;
    private final Color BG_PADRAO = new Color(184, 184, 184);
    private final Color BG_MARCAR = new Color(8, 179, 247);
    private final Color BG_EXPLODIR = new Color(189, 66, 68);
    private final Color TEXTO_VERDE = new Color(0, 100, 0);



    public BotaoCampo(Campo campo){
        this.campo = campo;
        CampoController campoController = new CampoController();
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createBevelBorder(0));
        campoController.registraObservador(this);
    }

    @Override
    public void evento(Campo campo, CampoEvento evento) {
        switch (evento){
            case ABRIR:
                aplicarEstiloAbrir();
                break;
            case MARCAR:
                aplicarEstiloMarcar();
                break;
            case EXPLODIR:
                aplicarEstiloExplodir();
                break;
            default:
                aplicarEstiloPadrao();
        }
    }

    private void aplicarEstiloAbrir() {
    }

    private void aplicarEstiloMarcar() {
    }

    private void aplicarEstiloPadrao(){

    }

    private void aplicarEstiloExplodir(){}

}
