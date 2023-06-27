package Model;

public class Medio extends Jogador {
    private int recuperacao;
    public Medio(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p, int rec) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        recuperacao = rec;
    }

    public Medio(Medio m){
        super(m);
    }

    public static Medio parse(String input){
        String[] campos = input.split(",");
        return new Medio(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }

    public int getRecuperacao() { return recuperacao;    }

    public double getHabilidade(){
        double d=0;
        double t=0;
        t = getPasse()+getRecuperacao();
        t = t/2 * 0.6;
        d = getVelocidade()+getResistencia()+getDestreza()+getImpulsao()+getCabeca()+getRemate();
        d = d/6 * 0.4 + t;
        return d;
    }
    public String toString(){
        return  getHabilidade() + "\n";
    }
    public Medio clone(){return new Medio(this);}

}
