package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Jogo implements Serializable {
    private String equipaCasa;
    private String equipaFora;
    private int golosCasa;
    private int golosFora;
    private LocalDate date;
    private List<Integer> jogadoresCasa;
    private List<Integer> jogadoresFora;
    Map<Integer, Integer> substituicoesCasa = new HashMap<>();
    Map<Integer, Integer> substitucoesFora = new HashMap<>();

    public Jogo (String ec, String ef, int gc, int gf, LocalDate d,  List<Integer> jc, Map<Integer, Integer> sc,  List<Integer> jf, Map<Integer, Integer> sf){
        equipaCasa = ec;
        equipaFora = ef;
        golosCasa = gc;
        golosFora = gf;
        date = d;
        jogadoresCasa = new ArrayList<>(jc);
        jogadoresFora = new ArrayList<>(jf);
        substituicoesCasa = new HashMap<>(sc);
        substitucoesFora = new HashMap<>(sf);
    }

    public Jogo(String equipaCasa, String equipaFora, LocalDate data){
        this.equipaCasa = equipaCasa;
        this.equipaFora = equipaFora;
        this.golosCasa = 0;
        this.golosFora = 0;
        this.date = data;
        this.jogadoresCasa = new ArrayList<>();
        this.jogadoresFora = new ArrayList<>();
        this.substituicoesCasa = new HashMap<>();
        this.substitucoesFora = new HashMap<>();
    }

    public static Jogo parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        Map<Integer, Integer> subsC = new HashMap<>();
        Map<Integer, Integer> subsF = new HashMap<>();
        for (int i = 5; i < 16; i++){
            jc.add(Integer.parseInt(campos[i]));
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            subsC.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        for (int i = 19; i < 30; i++){
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            subsF.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        return new Jogo(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                        LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                        jc, subsC, jf, subsF);
    }


    public void onzeCasa(List<Integer> jogadoresCasa) {
        this.jogadoresCasa = new ArrayList<>(jogadoresCasa);
    }

    public void onzeFora(List<Integer> jogadoresFora) {
        this.jogadoresFora = new ArrayList<>(jogadoresFora);
    }

    public List<Integer> getJogadoresCasa() {
        return new ArrayList<>(jogadoresCasa);
    }

    public List<Integer> getJogadoresFora() {
        return new ArrayList<>(jogadoresFora);
    }

    /**
     * Método que dá a equipa da casa.
     * @return Devolve a equipa da casa.
     */
    public String getEquipaCasa() {
        return equipaCasa;
    }

    public String getEquipaFora() {
        return equipaFora;
    }

    /**
     * O método vencedor dá um aumneto de 10% à equipa da casa e calcula o vencedor a partir das habilidades.
     * @param HabCasa valor da habilidade da equipa da casa.
     * @param HabFora valor da habilidade da equipa de fora.
     * @return Devolve uma String que contém o vencedor e o resultado.
     */
    public String vencedor(double HabCasa, double HabFora){
        String ret;
        Random gerador = new Random();
        int golos1 = gerador.nextInt(5);
        int golos2 = gerador.nextInt(5);
        double probCaseira = HabCasa*1.1;
        double probVisitante = HabFora*1;
        if(probCaseira > probVisitante) {
            while(golos1 == golos2) golos1 = gerador.nextInt(5);
            if(golos1 > golos2){
                this.golosCasa = golos1;
                this.golosFora = golos2;
            }
            else if(golos2 > golos1){
                this.golosCasa = golos2;
                this.golosFora = golos1;
            }
            ret ="Vencedor: " + this.equipaCasa + "\n" +
                    "Resultado: " + this.golosCasa + "-" + this.golosFora + "\n"
                    + "11 Visitado: " + this.jogadoresCasa + "\n" +
                    "11 Visitante: " + this.jogadoresFora + "\n";
        }
        else if(probCaseira < probVisitante){
            while(golos1 == golos2) golos1 = gerador.nextInt(5);
            if(golos1 > golos2){
                this.golosCasa = golos2;
                this.golosFora = golos1;
            }
            else if(golos2 > golos1){
                this.golosCasa = golos1;
                this.golosFora = golos2;
            }
            ret = "Vencedor: " + this.equipaFora + "\n" +
                    "Resultado: " + this.golosCasa + "-" + this.golosFora + "\n"
                    + "11 Visitado: " + this.jogadoresCasa + "\n" +
                    "11 Visitante: " + this.jogadoresFora + "\n";;
        }
        else {
            this.golosCasa = golos1;
            this.golosFora = golos1;
            ret = "Empate!\n" +
                    "Resultado: " + this.golosCasa + "-" + this.golosFora + "\n"
                    + "11 Visitado: " + this.jogadoresCasa + "\n" +
                    "11 Visitante: " + this.jogadoresFora + "\n";;

        }
        return ret;
    }

    /**
     * Função que traduz a classe Utilizador.
     *
     * @return Devolve uma String que representa a tradução.
     */
    public String toString() {
        return  "Jogo:" + equipaCasa + " - " + equipaFora + "\n"+
                "Resultado: " + golosCasa + " - " + golosFora + "\n" +
                "Dia: " + date;
                //+ " -> " + substituicoesCasa.toString()
                //+ " -> " + substitucoesFora.toString();
    }

    /**
     * Função que traduz a classe Utilizador.
     *
     * @return Devolve uma String que representa a tradução.
     */
    public String toStringFuturo() {
        return  "Jogo:" + equipaCasa + " - " + equipaFora + "\n"+
                "Resultado: Jogo por realizar\n" +
                "Dia: " + date;
        //+ " -> " + substituicoesCasa.toString()
        //+ " -> " + substitucoesFora.toString();
    }


}
