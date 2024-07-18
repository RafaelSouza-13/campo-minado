package br.com.rafael.cm.controller;

import br.com.rafael.cm.model.Campo;

public class CampoController {

    public boolean adicionarVizinho(Campo campoAtual, Campo campoVizinho){
        boolean linhaDiferente = campoAtual.getLinha() != campoVizinho.getLinha();
        boolean colunaDiferente = campoAtual.getColuna() != campoVizinho.getColuna();
        boolean diagonal = linhaDiferente && colunaDiferente;
        int deltaLinha = Math.abs(campoAtual.getColuna() - campoVizinho.getColuna());
        int deltaColuna = Math.abs(campoAtual.getLinha() - campoVizinho.getLinha());
        int deltaGeral = deltaLinha + deltaColuna;

        if((deltaGeral == 1) || (deltaGeral == 2 && diagonal)){
            campoAtual.getVizinhos().add(campoVizinho);
            return true;
        }
        return false;
    }
}
