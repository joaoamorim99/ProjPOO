package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Equipa implements Serializable {

    private String nome;
    private List<Jogador> jogadores;
    private int tatica; // Quando está 0 corresponde à tática 4-3-3, quando está a 1 corresponde à tática 4-4-2
    private double habilidadeE;

    /**
     * Construtor por omissão.
     */
    public Equipa() {
        nome="";
        jogadores = new ArrayList<>();
        tatica = 0;
        habilidadeE = 0 ;
    }

    /**
     * Construtor por cópia.
     *
     * @param e Recebe um objeto da classe Equipa.
     */
    public Equipa(Equipa e) {
        nome=e.nome;
        jogadores = e.jogadores;
        tatica = e.tatica;
        habilidadeE = e.habilidadeE;
    }

    /**
     * Construtor parametrizado
     * @param nomeE String que corresponde ao nome da equipa.
     */
    public Equipa(String nomeE) {
        nome=nomeE;
        jogadores = new ArrayList<>();
        tatica = 0;
        habilidadeE = 0;
    }

    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }

    public void insereJogador(Jogador j) {
        j.adicionaEquipa(this.nome);
        jogadores.add(j.clone());
    }

    public Jogador buscaJpeloN(int numeroJ){
        Jogador j = null;
        for(Jogador player : this.jogadores){
            if(player.getNumeroJogador() == numeroJ) j = player;
        }
        return j;
    }

    public void removeJogador(Jogador j){
        this.jogadores.remove(j);
    }

    public String getNome(){
        return nome;
    }

    public void mudaTatica(){
        if(this.tatica == 0) this.tatica = 1;
        else this.tatica = 0;
    }

    public String toString(){
        String r =  "Equipa:" + nome + " " + habilidadeE + "\n";
        return r;
    }

    /**
     * Método calcula habilidade.
     * A habilidade é calculada através da média da habilidade de todos os jogadores pertencentes a uma equipa.
     */
    public void calculaHabilidade(){
        double conta = 0;
        for(Jogador j : this.jogadores){
            conta += j.getHabilidade();
        }
        this.habilidadeE = conta/this.jogadores.size();
    }

    public boolean existeNumero(int num){
        boolean flag = false;
        for(Jogador j : this.jogadores){
            if(j.getNumeroJogador() == num) flag = true;
        }
        return flag;
    }

    public double getHabilidadeE() {
        return habilidadeE;
    }

    /**
     * Método que percorre a lista de jogadores e verifica a posição dos mesmos,
     * dessa forma acede aos primeiros onze jogadores e inseri-os na lista mediante a tática estipulada.
     * @return Devolve a lista com os onze jogdores, identificados pelo número da camisola.
     */
    public List<Integer> onzeInicial(){
        List<Integer> ret = new ArrayList<>();
        int gr = 1,df = 4,md = 3,at = 3;
        if(this.tatica == 1){
            md = 4;
            at = 2;
        }
        for(Jogador j : this.jogadores){
            if(gr != 0){
                if(j instanceof GuardaRedes){
                    ret.add(j.getNumeroJogador());
                    gr--;
                }
            }
            else if(df != 0){
                if(j instanceof Defesa || j instanceof Lateral){
                    ret.add(j.getNumeroJogador());
                    df--;
                }
            }
            else if(md != 0){
                if(j instanceof Medio){
                    ret.add(j.getNumeroJogador());
                    md--;
                }
            }
            else if(at != 0){
                if(j instanceof Avancado){
                    ret.add(j.getNumeroJogador());
                    at--;
                }
            }
        }
        return ret;

    }


    public List<String> getHistoricoJogador(int nm){
        List<String> ret = new ArrayList<>();
        for(Jogador j : this.jogadores){
            if(j.getNumeroJogador() == nm) ret = new ArrayList<>(j.getHistorico());
        }
        return ret;
    }

    public List<String> getJogadores(){
        List<String> ret = new ArrayList<>();
        for(Jogador j : this.jogadores){
            String posicao = j.getClass().toString().split("\\.")[1];
            ret.add(j.getNomeJogador()+"\n\tNúmero: "+j.getNumeroJogador() + "\n\tPosiçao: " +posicao + "\n\tHabilidade: " + j.getHabilidade());
        }
        return ret;
    }

    /**
     * Função que faz um clone da classe User.
     *
     * @return Devolve esse clone.
     */
    public Equipa clone() {
        return new Equipa(this);
    }


}


