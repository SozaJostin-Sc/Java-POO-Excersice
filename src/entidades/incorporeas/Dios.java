package entidades.incorporeas;

import entidades.fisicas.Humano;
import subClases.Incorporeo;

public class Dios extends Incorporeo {
    private int numAngeles;
    private int numDemonios;

    public Dios(int numAngeles, int numDemonios) {
        this.numAngeles = numAngeles;
        this.numDemonios = numDemonios;
    }

    @Override
    public double enganiar(Humano h, Angel a) {
        return 0;
    }

    @Override
    public double guiar(Humano h) {
        return 0;
    }

    @Override
    public double tentar(Humano h) {
        return 0;
    }

    @Override
    public double seducir(Humano h) {
        return 0;
    }

    @Override
    public double proteger(Humano h) {
        return 0;
    }

    @Override
    public double probar(Humano h) {
        return 0;
    }

    public int getNumAngeles() {
        return numAngeles;
    }

    public void setNumAngeles(int numAngeles) {
        this.numAngeles = numAngeles;
    }

    public int getNumDemonios() {
        return numDemonios;
    }

    public void setNumDemonios(int numDemonios) {
        this.numDemonios = numDemonios;
    }
}
