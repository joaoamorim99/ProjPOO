package Model;

public class GuardaRedes extends Jogador{
    private int elasticidade;
    public GuardaRedes (String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p, int elast) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        elasticidade = elast;
    }

    public GuardaRedes(GuardaRedes g){
        super(g);
    }

    public static GuardaRedes parse(String input){
        String[] campos = input.split(",");
        return new GuardaRedes(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }

    public int getElasticidade() { return elasticidade;    }

    public double getHabilidade(){
        double d=0;
        double t=0;
        t = getImpulsao()+getElasticidade();
        t = t/2 * 0.6;
        d = getVelocidade()+getResistencia()+getDestreza()+getPasse()+getCabeca()+getRemate();
        d = d/6 * 0.4 + t;
        return d;
    }

    public String toString(){
        return getHabilidade() + "\n";
    }

    public GuardaRedes clone(){return new GuardaRedes(this);}
}
