package View;

import java.util.List;
import java.util.Scanner;

public class ViewInicial {
    private Scanner sc;
    public ViewInicial(){
        sc = new Scanner(System.in);
    }

    public int viewInicial(){

        System.out.println("\nBem Vindo ao Treinador De Bancada\n  Login(1)\n  Registar(2)\n  Carregar ficheiro de texto(3)\n  Carregar ficheiro binario(4)\n  Sair(0)");
        int ret = sc.nextInt();
        return ret;
    }

    public int carregaPerfil(){
        System.out.println("Introduza o seu id.");
        return sc.nextInt();
    }

    public void finish(){
        System.out.println("Obrigado!");
        sc.close();
    }

    public void erroDeIdent(){
        System.out.println("Email ou password incorretos.");
    }

    public void emailExistente(){
        System.out.println("Email já registado.");
    }

    public void mostraLista(List<String> lista){
        for(String s : lista){
            System.out.println(s);
        }
        System.out.println();
    }
}
