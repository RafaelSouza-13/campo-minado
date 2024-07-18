package br.com.rafael.cm.controller;

import br.com.rafael.cm.model.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CampoControllerTest {

    private CampoController campoController = new CampoController();
    private Campo atual;
    @BeforeEach
    void iniciarCampo(){
        atual = new Campo(3, 3);
    }
    @Test
    void testAdicionaVizinhoLinha(){
        Campo vizinhoEsquerda = new Campo(3, 2);
        Campo vizinhoDireita = new Campo(3, 4);
        boolean resultEsquerda = campoController.adicionarVizinho(atual, vizinhoEsquerda);
        boolean resultDireita = campoController.adicionarVizinho(atual, vizinhoDireita);
        boolean result = resultEsquerda && resultDireita;
        assertTrue(result);
    }
    @Test
    void testNegaVizinhoLinha(){
        Campo vizinhoEsquerda = new Campo(3, 1);
        Campo vizinhoDireita = new Campo(3, 5);
        boolean resultEsquerda = campoController.adicionarVizinho(atual, vizinhoEsquerda);
        boolean resultDireita = campoController.adicionarVizinho(atual, vizinhoDireita);
        boolean result = resultDireita || resultEsquerda;
        assertFalse(result);
    }

    @Test
    void testDistanciaDiagonal(){
        Campo vizinho = new Campo(2, 2);
        boolean result = campoController.adicionarVizinho(atual, vizinho);
        assertTrue(result);
    }

    @Test
    void testNaoVizinhoDiagonal(){
        Campo vizinho = new Campo(1, 1);
        boolean result = campoController.adicionarVizinho(atual, vizinho);
        assertFalse(result);
    }

}
