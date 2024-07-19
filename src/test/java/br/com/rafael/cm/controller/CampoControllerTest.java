package br.com.rafael.cm.controller;

import br.com.rafael.cm.exception.ExplosaoException;
import br.com.rafael.cm.model.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CampoControllerTest {

    private CampoController campoController = new CampoController();
    private Campo campo;
    @BeforeEach
    void iniciarCampo(){
        campo = new Campo(3, 3);
    }
    @Test
    void testAdicionaVizinhoLinha(){
        Campo vizinhoEsquerda = new Campo(3, 2);
        Campo vizinhoDireita = new Campo(3, 4);
        boolean resultEsquerda = campoController.adicionarVizinho(campo, vizinhoEsquerda);
        boolean resultDireita = campoController.adicionarVizinho(campo, vizinhoDireita);
        boolean result = resultEsquerda && resultDireita;
        assertTrue(result);
    }
    @Test
    void testNegaVizinhoLinha(){
        Campo vizinhoEsquerda = new Campo(3, 1);
        Campo vizinhoDireita = new Campo(3, 5);
        boolean resultEsquerda = campoController.adicionarVizinho(campo, vizinhoEsquerda);
        boolean resultDireita = campoController.adicionarVizinho(campo, vizinhoDireita);
        boolean result = resultDireita || resultEsquerda;
        assertFalse(result);
    }

    @Test
    void testDistanciaDiagonal(){
        Campo vizinho = new Campo(2, 2);
        boolean result = campoController.adicionarVizinho(campo, vizinho);
        assertTrue(result);
    }

    @Test
    void testNaoVizinhoDiagonal(){
        Campo vizinho = new Campo(1, 1);
        boolean result = campoController.adicionarVizinho(campo, vizinho);
        assertFalse(result);
    }

    @Test
    void testValorPadraoAtributoMarcado(){
        assertFalse(campo.isMarcado());
    }

    @Test
    void alternarMarcacao(){
        assertTrue(campoController.alternarMarcacao(campo));
    }

    @Test
    void alternarMarcacaoDuasChamadas(){
        campoController.alternarMarcacao(campo);
        assertFalse(campoController.alternarMarcacao(campo));
    }

    @Test
    void abrirNaoMinadoENaoMarcado(){
        assertTrue(campoController.abrir(campo));
    }

    @Test
    void abrirNaoMinadoEMarcado(){
        campoController.alternarMarcacao(campo);
        assertFalse(campoController.abrir(campo));
    }

    @Test
    void abrirMinadoEMarcado(){
        campoController.minarCampo(campo);
        campoController.alternarMarcacao(campo);
        assertFalse(campoController.abrir(campo));
    }

    @Test
    void abrirMinadoENaoMarcado(){
        campoController.minarCampo(campo);
        assertThrows(ExplosaoException.class, ()->{
            campoController.abrir(campo);
        });
    }

    @Test
    void testAbrirComVizinhosSeguros(){
        Campo campo11 = new Campo(2, 2);
        Campo campo22 = new Campo(1,1);
        campoController.adicionarVizinho(campo11, campo22);
        campoController.adicionarVizinho(campo, campo11);

        campoController.abrir(campo);

        assertTrue(campo11.isAberto() && campo22.isAberto());
    }

    @Test
    void testAbrirComVizinhosMinado(){
        Campo campo22 = new Campo(2, 2);
        Campo campo12 = new Campo(1, 2);
        Campo campo11 = new Campo(1,1);
        campoController.minarCampo(campo12);
        campoController.adicionarVizinho(campo, campo22);
        campoController.adicionarVizinho(campo22, campo12);
        campoController.adicionarVizinho(campo22, campo11);
        campoController.abrir(campo);

        assertTrue(!campo11.isAberto() && campo22.isAberto());
    }

}
