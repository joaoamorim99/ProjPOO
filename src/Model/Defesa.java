package Model;

public class Defesa extends Jogador {
    public Defesa(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
    }

    public Defesa(Defesa d){
        super(d);
    }

    public static Defesa parse(String input){
        String[] campos = input.split(",");
        return new Defesa(campos[0], Integer.parseInt(campos[1]),
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
        t = getResistencia()+getCabeca();
        t = t/2 * 0.6;
        d = getVelocidade()+getImpulsao()+getDestreza()+getPasse()+getRemate();
        d = d/5 * 0.4 + t;
        return d;
    }

    public String toString(){
        return getHabilidade() + "\n";
    }

    public Defesa clone(){return new Defesa(this);}
}
