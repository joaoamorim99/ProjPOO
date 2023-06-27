package Model;

import com.sun.jdi.event.StepEvent;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class Sistema implements Serializable {
    private Map<String, User> utilizadores;
    private Map<String, Equipa> equipas;
    private List<Jogo> jogosRealizados;
    private List<Jogo> jogosFuturos;
    private Equipa equipaUtil;

    public Sistema() {
        this.utilizadores = new TreeMap<>();
        this.equipas = new TreeMap<>();
        this.jogosRealizados = new ArrayList<>();
        this.jogosFuturos = new ArrayList<>();
        this.equipaUtil = null;
    }

    public Sistema(Map<String, Equipa> equipas, List<Jogo> jogos) {
        this.utilizadores = new TreeMap<>();
        this.setEquipas(equipas);
        this.setJogosRealizados(jogos);
        this.jogosFuturos = new ArrayList<>();
        this.equipaUtil = null;
    }


    public Sistema(Sistema s) {
        this.setUtilizadores(s.getUtilizadores());
        this.setEquipas(s.getEquipas());
        this.setJogosRealizados(s.getJogosRealizados());
        this.setJogosFuturos(s.getJogosFuturos());
        this.equipaUtil = null;
    }

    public Map<String, User> getUtilizadores() {
        Map<String,User> ret = new TreeMap<>();
        for(User u : this.utilizadores.values()){
            ret.put(u.getEmail(),u.clone());
        }
        return ret;
    }

    public Map<String, Equipa> getEquipas() {
        Map<String,Equipa> ret = new TreeMap<>();
        for(Equipa e : this.equipas.values()){
            ret.put(e.getNome(),e.clone());
        }
        return ret;
    }

    public List<Jogo> getJogosRealizados() {
        List<Jogo> ret = new ArrayList<>();
        for(Jogo j : this.jogosRealizados){
            ret.add(j);
        }
        return ret;
    }

    public List<Jogo> getJogosFuturos() {
        List<Jogo> ret = new ArrayList<>();
        for(Jogo j : this.jogosFuturos){
            ret.add(j);
        }
        return ret;
    }

    public Equipa getEquipaUtil() {
        return equipaUtil.clone();
    }

    public void setUtilizadores(Map<String, User> utilizadores) {
        this.utilizadores = new TreeMap<>();
        for(User u : utilizadores.values()){
            this.utilizadores.put(u.getEmail(),u);
        }
    }

    public void setEquipas(Map<String, Equipa> equipas) {
        this.equipas = new TreeMap<>();
        for(Equipa e : equipas.values()){
            this.equipas.put(e.getNome(),e);
        }
    }

    public void setJogosRealizados(List<Jogo> jogosRealizados) {
        this.jogosRealizados = new ArrayList<>();
        for(Jogo j : jogosRealizados){
            this.jogosRealizados.add(j);
        }
    }

    public void setJogosFuturos(List<Jogo> jogosFuturos) {
        this.jogosFuturos = new ArrayList<>();
        for(Jogo j : jogosFuturos){
            this.jogosFuturos.add(j);
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mapa dos Utilizadores : ").append(this.utilizadores + "\n");
        sb.append("Mapa das Equipas : ").append(this.equipas + "\n");
        sb.append("Mapa dos Jogos que já foram realizados : ").append(this.jogosRealizados + "\n");
        sb.append("Mapa dos Jogos que faltam realizar : ").append(this.jogosFuturos + "\n");
        return sb.toString();
    }

    public boolean equals(Object oneSystem) {
        if (this == oneSystem)
            return true;

        if ((oneSystem == null) || (this.getClass() != oneSystem.getClass()))
            return false;
        else {
            Sistema s = (Sistema) oneSystem;
            return (this.utilizadores.equals(s.getUtilizadores())
                    && this.equipas == s.getEquipas()
                    && this.jogosRealizados == s.getJogosRealizados())
                    && this.jogosFuturos.equals(s.getJogosFuturos());
        }
    }

    public Sistema clone() {
        return new Sistema(this);
    }

    /**
     * Métodos que devolve todos os jogos realizados pela equipa do utilizador.
     * @return - Lista de jogos realizados.
     */
    public List<String> listaJogosFeitos(){
        List<String> ret = new ArrayList<>();
        for(Jogo j : this.jogosRealizados) {
            if(j.getEquipaCasa().compareTo(this.equipaUtil.getNome()) == 0 || j.getEquipaFora().compareTo(this.equipaUtil.getNome()) == 0)
            ret.add(j.toString());
        }
        return ret;
    }

    /**
     * Métodos que devolve todos os jogos por realizar.
     * @return - Lista de jogos por realizar.
     */
    public List<String> listaJogosFuturos(){
        List<String> ret = new ArrayList<>();
        if(this.jogosFuturos.size() > 0){
            for (Jogo j : this.jogosFuturos) {
                ret.add(j.toStringFuturo());
            }
        }
        return ret;
    }

    /**
     * Método que atribui um valor de habilidade a cada equipa do sistema.
     */
    public void atribuiHab(){
        for(Equipa e : this.equipas.values()) e.calculaHabilidade();
    }

    /**
     * Método login de um utilizador.
     * @param email String que representa o email.
     * @param pass String relativa a pass.
     * @return Devolve uma variável do tipo booolean que verifica se o utilizador está logado.
     */
    public boolean login(String email, String pass) {
        boolean ret = false;
        if(this.utilizadores.containsKey(email)){
            this.utilizadores.get(email).setLogged(true);
            String equipa = this.utilizadores.get(email).getEquipa();
            if(equipa != null)  this.equipaUtil = this.equipas.get(equipa).clone();
            ret = true;
        }
        return ret;
    }

    /**
     * Método cria conta de um utilizador.
     * @param email String que representa o email.
     * @param password String relativa a pass.
     * @return Devolve uma variável do tipo boolean que verifica se o utilizador criou conta.
     */
    public boolean criaConta(String email, String password ) {
        boolean ret = false;
        if(!this.utilizadores.containsKey(email)){
            User ut = new User(email,password,this.utilizadores.size(),true);
            this.utilizadores.put(email,ut);
            ret = true;
        }
        return ret;
    }

    /**
     * Método que seleciona a equipa escohida pelo utilizador e guarda o nome desta numa variável instância do utilizador e guarda também um objeto Equipa com essa equipa para facilitar o acesso por parte do sistema.
     * @param equipa - Nome da equipa escolhida.
     * @param email - Email do utilizador.
     */
    public void escolheEquipa(String equipa, String email){
        if(this.equipas.containsKey(equipa)){
            this.utilizadores.get(email).setEquipa(equipa);
            this.equipaUtil = this.equipas.get(equipa);
        }
    }

    /**
     * Método que recebe a equipa origem e o número de um jogador, encontra o jogador no sistema, coloca-o na equipa do utilizador e remove-o da equipa origem.
     * @param equipaOrigem - Nome da equipa origem.
     * @param numeroJogador - Número do jogador.
     */
    public void compra(String equipaOrigem, int numeroJogador) {
        Jogador trans = this.equipas.get(equipaOrigem).buscaJpeloN(numeroJogador);
        while(this.equipaUtil.existeNumero(numeroJogador)) numeroJogador++;
        trans.setNumeroJogador(numeroJogador);
        this.equipas.get(equipaOrigem).removeJogador(trans);
        this.equipaUtil.insereJogador(trans);
    }

    /**
     * Método que recebe o nome da equipa destino e o número do jogador a transferir, depois retira o jogador da equipa do utilizador e coloca-o na equipa destino.
     * @param equipaDestino - Nome da equipa destino.
     * @param numeroJogador - Número do jogador a transferir.
     */
    public void venda(String equipaDestino, int numeroJogador) {
        Jogador trans = this.equipaUtil.buscaJpeloN(numeroJogador);
        while(this.equipas.get(equipaDestino).existeNumero(numeroJogador)) numeroJogador++;
        trans.setNumeroJogador(numeroJogador);
        this.equipaUtil.removeJogador(trans);
        this.equipas.get(equipaDestino).insereJogador(trans);
    }

    /**
     * Método que altera a tática de uma equipa, existem apenas duas táticas possíveis.
     */
    public void mudarTatica() {
        this.equipaUtil.mudaTatica();
    }

    /**
     * Método que gera jogos para que posteriormente o utlizador posso simular.
     */
    public void geraJogos(){
        String equipaUt = this.equipaUtil.getNome();
        int par = 0;
        for(Equipa e : this.equipas.values()){
            if(e.getNome().compareTo(equipaUt)!=0){
                if(par % 2 == 0){
                    Jogo jogo = new Jogo(equipaUt,e.getNome(), LocalDate.now().plusWeeks(1*par));
                    jogo.onzeCasa(this.equipaUtil.onzeInicial());
                    jogo.onzeFora(e.onzeInicial());
                    this.jogosFuturos.add(jogo);
                }
                else{
                    Jogo jogo = new Jogo(e.getNome(),equipaUt, LocalDate.now().plusWeeks(1*par));
                    jogo.onzeCasa(e.onzeInicial());
                    jogo.onzeFora(this.equipaUtil.onzeInicial());
                    this.jogosFuturos.add(jogo);
                }
                par++;
            }
        }

    }

    /**
     * Método que simula um determinado jogo.
     * @return  devolve uma string com o resultado do encontro.
     */
    public String simularJogo() {
        Jogo jogo = this.getJogosFuturos().get(0);
        Equipa caseira = this.getEquipas().get(jogo.getEquipaCasa()).clone();
        Equipa visitante = this.getEquipas().get(jogo.getEquipaFora()).clone();
        String vencedor = jogo.vencedor(caseira.getHabilidadeE(),visitante.getHabilidadeE());
        this.jogosRealizados.add(jogo);
        this.jogosFuturos.remove(0);
        return vencedor;
    }

    /**
     * Método que limpa a lista de jogos por realizar.
     */
    public void cleanFuturo(){
        this.jogosFuturos = new ArrayList<>();
    }

    /**
     * Método que retorna a lista dos jogadores da equipa do utilizador.
     * @return - Lista de jogadores.
     */
    public List<String> listarJogadoresUtilizador() {
        return listarJogadores(this.equipaUtil.getNome());
    }

    /**
     * Método que devolve o histórico de equipas pelas quais um jogador passou.
     * @param num - Número do jogador.
     * @param equipa - Nome da equipa atual do jogador
     * @return
     */
    public List<String> getHistoricoJog(int num,String equipa){
       return new ArrayList<>(this.equipas.get(equipa).getHistoricoJogador(num));
    }


    /**
     * Método que devolve a lista dos jogadores pertencentes a uma equipa.
     * @param equipa - Nome da equipa.
     * @return- Lista de jogadores.
     */
    public List<String> listarJogadores(String equipa) {
        List<String> ret = new ArrayList<>();
        if(this.equipas.containsKey(equipa)){
            ret = this.equipas.get(equipa).getJogadores();
        }
        return ret;
    }

    /**
     * Método que devolve a lista de todas as equipas do sistema.
     * @return - Lista de equipas.
     */
    public List<String> listarEquipas() {
        List<String> ret = new ArrayList<>();
        for(Equipa e : this.getEquipas().values()){
            ret.add(e.getNome());
        }
        return ret;
    }

    /**
     * Método que adiciona uma equipa ao sistema.
     * @param e - Equipa a adicionar.
     */
    public void addEquipa(Equipa e){
        this.equipas.put(e.getNome(),e);
    }


    /**
     * Grava o estado da aplicação num determinado ficheiro.
     *
     * @param nomeficheiro Recebe o nome do ficheiro.
     * @throws IOException           Exception.
     * @throws FileNotFoundException Exception.
     */
    public void grava(String nomeficheiro) throws IOException {
        FileOutputStream o = new FileOutputStream(nomeficheiro);
        ObjectOutputStream r = new ObjectOutputStream(o);
        this.equipas.replace(this.equipaUtil.getNome(),this.equipaUtil);
        r.writeObject(this);
        r.flush();
        r.close();
    }

    /**
     * Iniciar a aplicação com o estado guardado num determinado ficheiro.
     *
     * @param nomeficheiro Recebe o nome do ficheiro.
     * @return Devolve a aplicação inciada.
     * @throws IOException            Exception.
     * @throws ClassNotFoundException Exception.
     * @throws FileNotFoundException  Exception.
     */
    public static Sistema carrega(String nomeficheiro) throws IOException, ClassNotFoundException {
        FileInputStream r = new FileInputStream(nomeficheiro);
        ObjectInputStream o = new ObjectInputStream(r);
        Sistema g = (Sistema) o.readObject();
        o.close();
        return g;
    }


}

