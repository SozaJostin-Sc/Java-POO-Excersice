package entidades.incorporeas;

import entidades.fisicas.Humano;

public class DiosCristiano extends Dios{
    public DiosCristiano(int numAngeles, int numDemonios) {
        super(numAngeles, numDemonios);
    }

    public boolean esBuenHombre(Humano h) {
        final double UMBRAL_FE = 60.0;
        boolean moralPositiva = h.getBondad() > h.getMaldad();
        boolean feSuficiente = h.getFe() >= UMBRAL_FE;
        return moralPositiva && feSuficiente;
    }
}
