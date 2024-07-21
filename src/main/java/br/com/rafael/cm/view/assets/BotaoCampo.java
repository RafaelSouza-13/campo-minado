package br.com.rafael.cm.view.assets;

import br.com.rafael.cm.controller.CampoController;
import br.com.rafael.cm.enuns.CampoEvento;
import br.com.rafael.cm.interfaces.CampoObservador;
import br.com.rafael.cm.model.Campo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BotaoCampo extends JButton implements CampoObservador, MouseListener {
    private Campo campo;
    CampoController campoController;
    private final Color BG_PADRAO = new Color(184, 184, 184);
    private final Color BG_MARCAR = new Color(8, 179, 247);
    private final Color BG_EXPLODIR = new Color(189, 66, 68);
    private final Color TEXTO_VERDE = new Color(0, 100, 0);



    public BotaoCampo(Campo campo){
        this.campo = campo;
        campo.registraObservador(this);
        this.campoController = new CampoController();
        setBackground(BG_PADRAO);
        addMouseListener(this);
        setBorder(BorderFactory.createBevelBorder(0));
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
        SwingUtilities.invokeLater(()->{
            repaint();
            validate();
        });
    }

    private void aplicarEstiloAbrir() {
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        if(campo.isMinado()){
            setBackground(BG_EXPLODIR);
            return;
        }
        setBackground(BG_PADRAO);


        switch (campoController.minasNaVizinhanca(campo)){
            case 1:
                setForeground(TEXTO_VERDE);
                break;
            case 2:
                setForeground(Color.BLUE);
                break;
            case 3:
                setForeground(Color.YELLOW);
                break;
            case 4:
            case 5:
            case 6:
            default:
                setForeground(Color.pink);
        }
        String valor = !campoController.vizinhancaSegura(campo)? campoController.minasNaVizinhanca(campo)+"" : "";
        setText(valor);
    }

    private void aplicarEstiloMarcar() {
        setBackground(BG_MARCAR);
        setText("M");
    }

    private void aplicarEstiloPadrao(){
        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createBevelBorder(0));
        setText("");

    }

    private void aplicarEstiloExplodir(){
        setBackground(BG_EXPLODIR);
        setForeground(Color.WHITE);
        setText("X");
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == 1){
            campoController.abrir(campo);
        }else{
            if(campoController.alternarMarcacao(campo)){
                setBackground(BG_MARCAR);
            }else{
                setBackground(BG_PADRAO);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
