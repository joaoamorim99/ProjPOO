package Controller;

import Model.*;
import View.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Controller.Parser.parse;

public class Controller {
    private Sistema s;
    private ViewInicial v = new ViewInicial();
    private int idUtil;

    public Controller(Sistema s, ViewInicial v){
        this.s = s;
    }

    public Sistema getS() {
        return s;
    }

    public void setS(Sistema s) {
        this.s = s;
    }

    public ViewInicial getV() {
        return v;
    }

    public void setV(ViewInicial v) {
        this.v = v;
    }

    public void controllerStart() throws IOException, ClassNotFoundException, LinhaIncorretaException {
        int n1 = v.viewInicial();
        switch(n1) {
            case 1:
                controllerLog();
                break;
            case 2:
                this.s.cleanFuturo();
                controllerSign();
                break;
            case 3:
                this.s = parse();
                controllerStart();

                break;
            case 4:
                int id = v.carregaPerfil();
                this.s = Sistema.carrega("progresso"+id);
                //v.limpa();
                controllerStart();
                break;
            case 0:
                v.finish();
                break;
        }
    }

    public void controllerLog() throws IOException, ClassNotFoundException {
        List<String> ret = new ArrayList<>();
        ViewUtilizador u = new ViewUtilizador();
        ret = u.login();
        boolean b = s.login(ret.get(0),ret.get(1));
        if(!b) {
            v.erroDeIdent();
            controllerLog();
        } else {
            this.idUtil = this.s.getUtilizadores().get(ret.get(0)).getId();
            int t = u.menuU();
            menuUtil(t);
        }
    }

    public void controllerSign() throws IOException, ClassNotFoundException {
        List<String> ret = new ArrayList<>();
        ViewUtilizador u = new ViewUtilizador();
        ret = u.signup();
        boolean b = s.criaConta(ret.get(0),ret.get(1));
        if(!b) {
            v.emailExistente();
            controllerSign();
        } else {
            this.idUtil = this.s.getUtilizadores().get(ret.get(0)).getId();
            v.mostraLista(this.s.listarEquipas());
            String equipa = u.escolheEquipa();
            this.s.escolheEquipa(equipa,ret.get(0));
            u.registoSucesso(this.idUtil);
            int t = u.menuU();
            menuUtil(t);

        }
    }

    public void menuUtil(int t) throws IOException, ClassNotFoundException {
        switch (t) {
            case 0:
                v.finish();
                s.grava("progresso"+this.idUtil);
                break;
            case 1:
                this.s.mudarTatica();
                ViewUtilizador u = new ViewUtilizador();
                int t1 = u.menuU();
                menuUtil(t1);
                break;
            case 2:
                ViewUtilizador u1 = new ViewUtilizador();
                v.mostraLista(this.s.listarEquipas());
                String equipa = u1.escolheEquipa();
                v.mostraLista(this.s.getEquipas().get(equipa).getJogadores());
                int numeroJogador = u1.escolheJogador();
                this.s.compra(equipa,numeroJogador);
                v.mostraLista(this.s.getEquipas().get(equipa).getJogadores());
                v.mostraLista(this.s.getEquipaUtil().getJogadores());
                int t2 = u1.menuU();
                menuUtil(t2);
                break;
            case 3:
                u1 = new ViewUtilizador();
                v.mostraLista(this.s.listarJogadoresUtilizador());
                int num = u1.escolheJogador();
                v.mostraLista(this.s.listarEquipas());
                String eq = u1.escolheEquipa();
                this.s.venda(eq,num);
                v.mostraLista(this.s.getEquipas().get(eq).getJogadores());
                v.mostraLista(this.s.getEquipaUtil().getJogadores());
                int t3 = u1.menuU();
                menuUtil(t3);
                break;
            case 4:
                v.mostraLista(this.s.listaJogosFeitos());
                u1 = new ViewUtilizador();
                t1 = u1.menuU();
                menuUtil(t1);
                break;
            case 5:
                u1 = new ViewUtilizador();
                this.s.geraJogos();
                u1.geradoSucesso();
                t1 = u1.menuU();
                menuUtil(t1);
                break;
            case 6:
                v.mostraLista(this.s.listaJogosFuturos());
                u1 = new ViewUtilizador();
                t1 = u1.menuU();
                menuUtil(t1);
                break;
            case 7:
                u1 = new ViewUtilizador();
                String resultado = this.s.simularJogo();
                u1.mostraResultado(resultado);
                t1 = u1.menuU();
                menuUtil(t1);
                break;
            case 8:
                v.mostraLista(this.s.listarJogadoresUtilizador());
                u1 = new ViewUtilizador();
                t1 = u1.menuU();
                menuUtil(t1);
                break;
            case 9:
                u1 = new ViewUtilizador();
                v.mostraLista(this.s.listarEquipas());
                String team = u1.escolheEquipa();
                v.mostraLista(this.s.listarJogadores(team));
                int nm = u1.escolheJogador();
                v.mostraLista(this.s.getHistoricoJog(nm,team));
                t1 = u1.menuU();
                menuUtil(t1);
                break;
        }
    }
}
