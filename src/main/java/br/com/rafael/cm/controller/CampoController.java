package br.com.rafael.cm.controller;

import br.com.rafael.cm.exception.ExplosaoException;
import br.com.rafael.cm.model.Campo;

public class CampoController {

    public boolean adicionarVizinho(Campo campoAtual, Campo campoVizinho){
        boolean linhaDiferente = campoAtual.getLinha() != campoVizinho.getLinha();
        boolean colunaDiferente = campoAtual.getColuna() != campoVizinho.getColuna();
        boolean diagonal = linhaDiferente && colunaDiferente;
        int deltaLinha = Math.abs(campoAtual.getLinha() - campoVizinho.getLinha());
        int deltaColuna = Math.abs(campoAtual.getColuna() - campoVizinho.getColuna());
        int deltaGeral = deltaLinha + deltaColuna;

        if((deltaGeral == 1) || (deltaGeral == 2 && diagonal)){
            campoAtual.getVizinhos().add(campoVizinho);
            return true;
        }
        return false;
    }

    public boolean alternarMarcacao(Campo campo){
        if(!campo.isAberto()){
            campo.setMarcado(!campo.isMarcado());
        }
        return campo.isMarcado();
    }

    public boolean abrir(Campo campo){
        if(!(campo.isAberto() || campo.isMarcado())){
            campo.setAberto(true);
            if(campo.isMinado()){
                throw new ExplosaoException("Você perdeu o jogo");
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

}
