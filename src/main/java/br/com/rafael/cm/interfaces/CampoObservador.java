package br.com.rafael.cm.interfaces;

import br.com.rafael.cm.enuns.CampoEvento;
import br.com.rafael.cm.model.Campo;

public interface CampoObservador {

    public void evento(Campo campo, CampoEvento evento);
}
