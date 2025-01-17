package br.com.rafael.cm.controller;

import br.com.rafael.cm.enuns.CampoEvento;
import br.com.rafael.cm.exception.ExplosaoException;
import br.com.rafael.cm.interfaces.CampoObservador;
import br.com.rafael.cm.model.Campo;

import java.util.ArrayList;
import java.util.List;

public class CampoController {

    public boolean adicionarVizinho(Campo campoAtual, Campo campoVizinho){
        boolean linhaDiferente = campoAtual.getLinha() != campoVizinho.getLinha();
        boolean colunaDiferente = campoAtual.getColuna() != campoVizinho.getColuna();
        boolean diagonal = linhaDiferente && colunaDiferente;
        int deltaLinha = Math.abs(campoAtual.getLinha() - campoVizinho.getLinha());
        int deltaColuna = Math.abs(campoAtual.getColuna() - campoVizinho.getColuna());
        int deltaGeral = deltaLinha + deltaColuna;

        if((deltaGeral == 1 && !diagonal) || (deltaGeral == 2 && diagonal)){
            campoAtual.getVizinhos().add(campoVizinho);
            return true;
        }
        return false;
    }

    public boolean alternarMarcacao(Campo campo){
        if(!campo.isAberto()){
            campo.setMarcado(!campo.isMarcado());
            if(campo.isMarcado()){
                campo.notificarObservadores(campo, CampoEvento.MARCAR);
            }else{
                campo.notificarObservadores(campo, CampoEvento.DESMARCAR);
            }
        }
        return campo.isMarcado();
    }

    public boolean abrir(Campo campo){
        if(!(campo.isAberto() || campo.isMarcado())){
            if(campo.isMinado()){
                campo.notificarObservadores(campo, CampoEvento.EXPLODIR);
                return true;
            }
            campo.setAberto(true);
            if(campo.isAberto()){
                campo.notificarObservadores(campo, CampoEvento.ABRIR);
            }
            if(vizinhancaSegura(campo)){
                campo.getVizinhos().forEach(v -> abrir(v));
            }
            return true;
        }
        return false;
    }

    public boolean vizinhancaSegura(Campo campo){
        return campo.getVizinhos().stream().noneMatch(v -> v.isMinado());
    }

    public void minarCampo(Campo campo){
        if(!campo.isMinado()){
            campo.setMinado(true);
        }
    }

    public boolean campoConcluido(Campo campo){
        boolean desvendado = !campo.isMinado() && campo.isAberto();
        boolean protegido = campo.isMinado() && campo.isMarcado();
        return desvendado || protegido;
    }

    public int minasNaVizinhanca(Campo campo){
        return (int) campo.getVizinhos().stream().filter(v-> v.isMinado()).count();
    }

    public void reiniciarCampo(Campo campo){
        campo.setAberto(false);
        campo.setMinado(false);
        campo.setMarcado(false);
    }


}
