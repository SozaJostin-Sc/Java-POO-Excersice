package subClases;

import entidades.fisicas.Humano;
import entidades.incorporeas.Angel;
import superclase.Ser;

public abstract class Incorporeo extends Ser {
    private int fe;
    private int bondad;
    private int maldad;

    public abstract double enganiar(Humano h, Angel a);
    public abstract double guiar(Humano h);
    public abstract double tentar(Humano h);
    public abstract double seducir (Humano h);
    public abstract double proteger(Humano h);
    public abstract double probar (Humano h);

    public int luchar(){
        return (int) (Math.random() * 6);
    }

    public Incorporeo(int fe, int bondad, int maldad) {
        this.fe = fe;
        this.bondad = bondad;
        this.maldad = maldad;
    }

    public Incorporeo() {
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public int getBondad() {
        return bondad;
    }

    public void setBondad(int bondad) {
        this.bondad = bondad;
    }

    public int getMaldad() {
        return maldad;
    }

    public void setMaldad(int maldad) {
        this.maldad = maldad;
    }
}
