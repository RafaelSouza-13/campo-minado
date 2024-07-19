package br.com.rafael.cm.view;

import br.com.rafael.cm.controller.TabuleiroController;
import br.com.rafael.cm.exception.ExplosaoException;
import br.com.rafael.cm.exception.SairJogoException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroView {
    private TabuleiroController tabuleiroController;
    private Scanner scanner = new Scanner(System.in);

    public TabuleiroView(){
        configurarJogo();
        executarJogo();
    }

    private void configurarJogo(){
        System.out.print("Digite o número de linhas entre 1 e 9: ");
        int linhas = scanner.nextInt();
        System.out.print("Digite o colunas de linhas entre 1 e 9: ");
        int colunas = scanner.nextInt();
        System.out.print("Número de bombas: ");
        int bombas = scanner.nextInt();
        scanner.nextLine();
        this.tabuleiroController = new TabuleiroController(linhas, colunas, bombas);
    }

    private void executarJogo() {
        try{
            boolean continuar = true;
            while(continuar){
                cicloJogo();
                System.out.println("Outra partida? (S/n) ");
                String resposta = scanner.nextLine();
                if(resposta.equalsIgnoreCase("n")){
                    continuar = false;
                }else{
                    tabuleiroController.reinicarJogo();
                }
            }
        }catch (SairJogoException e){
            System.out.println("Saindo do jogo");
        }finally {
            scanner.close();
        }
    }

    private void cicloJogo() {
        try{
            while(!tabuleiroController.objetivoAlcancado()){
                System.out.println(tabuleiroController.exibeTabuleiro());
                String digitado = capturarValorDado("Digite (x e y): ");
                try{
                    Iterator<Integer> xy = Arrays.stream(digitado.split(",")).
                            map(e -> Integer.parseInt(e.trim())).iterator();
                    digitado = capturarValorDado("(1 - abrir 2 - marcar):");
                    if(digitado.equalsIgnoreCase("1")){
                        tabuleiroController.abrir(xy.next(), xy.next());
                    }else if(digitado.equalsIgnoreCase("2")){
                        tabuleiroController.alterarMarcar(xy.next(), xy.next());
                    }
                }catch (NumberFormatException e){
                    System.out.println("Digite um valor válido para coordenada");
                }
            tabuleiroController.exibeTabuleiro();
            }
            System.out.println("Voce ganhou");
        }catch (ExplosaoException e){
            System.out.println(tabuleiroController.exibeTabuleiro());
            System.out.println("Voce perdeu o jogo");
        }
    }

    private String capturarValorDado(String texto){
        System.out.print(texto);
        String digitado = scanner.nextLine();
        if(digitado.equalsIgnoreCase("sair")){
            throw new SairJogoException();
        }
        return digitado;
    }
}
