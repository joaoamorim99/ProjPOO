package View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewUtilizador {
    private Scanner sc;
    public  ViewUtilizador(){
        sc = new Scanner(System.in);
    }

    public List<String> login(){
        List<String> ret = new ArrayList<>();
        System.out.println("Início de sessão");
        System.out.println("Email:");
        String m = sc.nextLine();
        ret.add(m);
        System.out.println("Password:");
        String p = sc.nextLine();
        ret.add(p);
        return ret;
    }

    public List<String> signup(){
        List<String> ret = new ArrayList<>();
        System.out.println("Registar conta");
        System.out.println("Email:");
        String m = sc.nextLine();
        ret.add(m);
        System.out.println("Password:");
        String p = sc.nextLine();
        ret.add(p);
        return ret;
    }

    public String escolheEquipa(){
        System.out.println("Por favor escolha uma equipa");
        String ret = sc.nextLine();
        return ret;
    }
    public int escolheJogador(){
        System.out.println("Escolha o número do jogador");
        int ret = sc.nextInt();
        sc.nextLine();
        return ret;
    }

    public void registoSucesso(int id){
        System.out.println("A sua conta foi registada com sucesso!");
        System.out.println("O seu id de utilizador é: "+id);
        System.out.println("Guarde o seu número para mais tarde carregar o seu jogo.");
    }

    public void mostraResultado(String s){
        System.out.println(s);
    }

    public void geradoSucesso(){
        System.out.println("Os jogos foram gerados com sucesso!");
    }

    public int menuU(){
        //for (int i = 0; i < 50; ++i) System.out.println();
        System.out.println("Menu de Utlizador:\n\nMudar Tática da equipa(1)\nComprar Jogador(2)\nVender Jogador(3)\nVer histórico de jogos(4)\nGerar mais jogos(5)\nVer calendário de jogos(6)\nSimular jogo(7)\nVer plantel(8)\nVer histórico de um jogador(9)\nSair(0)");
        int p = sc.nextInt();
        return p;
    }
}
