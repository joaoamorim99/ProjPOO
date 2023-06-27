package Model;

public class Lateral extends Jogador{
    private int cruzamento;
    public Lateral(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p, int cruz) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        cruzamento = cruz;
    }

    public Lateral(Lateral l){
        super(l);
    }

    public static Lateral parse(String input){
        String[] campos = input.split(",");
        return new Lateral(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }
    public int getCruzamento() { return cruzamento;    }

    public double getHabilidade(){
        double d=0;
        double t=0;
        t = getVelocidade()+getCruzamento();
        t = t/2 * 0.6;
        d = getImpulsao()+getResistencia()+getDestreza()+getPasse()+getCabeca()+getRemate();
        d = d/6 * 0.4 + t;
        return d;
    }

    public String toString(){
        return getHabilidade() + "\n";
    }
    public Lateral clone(){return new Lateral(this);}
}
