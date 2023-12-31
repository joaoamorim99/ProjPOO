package Model;


public class Avancado extends Jogador {
    public Avancado(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
    }

    public Avancado(Avancado a){
        super(a);
    }

    public static Avancado parse(String input){
        String[] campos = input.split(",");
        return new Avancado(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]));
    }

    public double getHabilidade(){
        double d=0;
        double t=0;
        t = getResistencia()+getRemate();
        t = t/2 * 0.6;
        d = getVelocidade()+getImpulsao()+getDestreza()+getPasse()+getCabeca();
        d = d/5 * 0.4 + t;
        return d;
    }

    public String toString(){
        return getHabilidade() + "\n";
    }
    public Avancado clone(){return new Avancado(this);}
}
