package entidades.incorporeas;

import entidades.fisicas.Humano;
import subClases.Espiritual;
import subClases.Incorporeo;
import java.util.Random;

/**
 * Representa a un Demonio dentro del sistema "La Batalla de las Almas".
 * <p>
 * Los Demonios son seres incorpóreos alineados al mal y poseen valores de fe,
 * bondad y maldad generados aleatoriamente dentro de los rangos establecidos
 * por el enunciado. Un Demonio surge con muy poca bondad, alta maldad y un nivel
 * de fe variable, lo que refleja su naturaleza espiritual oscura.
 * </p>
 *
 * <p>
 * Cada Demonio está asociado a un Ángel específico, lo que puede representar
 * una relación de contraparte espiritual, origen o rivalidad directa. Esta
 * conexión influye en ciertos comportamientos del Demonio definidos en sus
 * métodos.
 * </p>
 *
 * <p><b>Extiende:</b> Incorporeo<br>
 * <b>Implementa:</b> Espiritual</p>
 */
public class Demonio extends Incorporeo implements Espiritual {

    // Generador de números aleatorios para asignar los valores espirituales
    private static final Random rd = new Random();

    // Valores máximos posibles para los atributos espirituales de un demonio
    private static final int MAX_FE = 200;      // Los demonios pueden tener algo de "fe", aunque no sea positiva
    private static final int MAX_BONDAD = 10;   // Bondad extremadamente baja
    private static final int MAX_MALDAD = 200;  // Maldad muy alta (propia de un demonio)

    /**
     * Constructor del Demonio.
     * Se generan aleatoriamente los valores de fe, bondad y maldad dentro de sus rangos,
     * usando las constantes definidas arriba.
     *
     */
    public Demonio() {
        // Llama al constructor de Incorporeo asignando los valores espirituales
        super(rd.nextInt(MAX_FE),       // fe generada aleatoriamente
                rd.nextInt(MAX_BONDAD),   // bondad generada aleatoriamente
                rd.nextInt(MAX_MALDAD));  // maldad generada aleatoriamente
    }

    /**
     * Implementación del método rezar() definido en la interfaz Espiritual.
     * <p>
     * En el caso de los Demonios, este método siempre devuelve false ya que
     * estas entidades no establecen comunicación con Dios según la lógica
     * del sistema de "La Batalla de las Almas".
     * </p>
     *
     * @return false siempre, indicando que un Demonio no puede rezar.
     */
    @Override
    public boolean rezar() {
        return false;
    }


    /**
     * Implementación del método engañar() heredado de Incorporeo.
     * <p>
     * Este método calcula la efectividad con la que un Demonio puede engañar
     * a un Humano. La fórmula sigue los lineamientos del enunciado del ejercicio,
     * combinando la fe y balance moral del Humano frente a la fe del Ángel que lo protege.
     * </p>
     * <p>
     * La fórmula utilizada es:
     * <pre>
     *  ( fe_humano / fe_angel ) * ( (bondad_humano - maldad_humano) / fe_angel )
     * </pre>
     * </p>
     *
     * @param h Humano objetivo sobre el cual se aplica la habilidad.
     * @param angel Angel enemigo.
     * @return valor numérico que representa la efectividad del engaño.
     */
    @Override
    public double enganiar(Humano h, Angel angel) {
        return (h.getFe() / angel.getFe())
                * ((h.getBondad() - h.getMaldad()) / angel.getFe());
    }



    /**
     * Implementación del método guiar() para Demonios.
     * <p>
     * Los Demonios no pueden guiar positivamente a un Humano, por lo que
     * este método siempre retorna 0 según lo especificado en el enunciado.
     * </p>
     *
     * @param h Humano que supuestamente sería guiado.
     * @return siempre 0, indicando que la habilidad no aplica para Demonios.
     */
    @Override
    public double guiar(Humano h) {
        return 0;
    }



    /**
     * Implementación del método tentar() heredado de Incorporeo.
     * <p>
     * Este método calcula la fuerza con la que un Demonio puede tentar a un Humano.
     * La fórmula combina la fe del Humano, su maldad relativa a la inteligencia,
     * y los valores de bondad/maldad del Demonio, siguiendo el modelo matemático
     * especificado en el enunciado.
     * </p>
     * <p>
     * Fórmula:
     * <pre>
     *   (-fe_humano + maldad_humano / inteligencia_humano)
     *   ---------------------------------------------------
     *         sqrt( bondad_demonio^2 - maldad_demonio^2 )
     * </pre>
     * </p>
     *
     * @param h Humano al que se intenta tentar.
     * @return valor numérico que representa la efectividad de la tentación.
     */
    @Override
    public double tentar(Humano h) {
        return (-h.getFe() + h.getMaldad() / h.getInteligencia()) /
                Math.sqrt(Math.pow(getBondad(), 2) - Math.pow(getMaldad(), 2));
    }



    /**
     * Aplica la habilidad de Seducir sobre un Humano.
     * Esta es una habilidad ofensiva que calcula el resultado de la prueba
     * basándose en la bondad, maldad, inteligencia y fe del Humano.
     * Cuanto mayor sea el valor devuelto, más exitosa es la prueba del Demonio.
     * Fórmula aplicada:
     * <pre>
     *        sqrt(bondad^2 - maldad^2 - inteligencia)
     *   -------------------------------------------------
     *                fe^2 - bondad^2
     * </pre>
     * @param h El objeto Humano sobre el cual se aplica la prueba.
     * @return El resultado numérico de la prueba de seducción (double).
     */
    @Override
    public double seducir(Humano h) {
        return Math.sqrt(Math.pow(h.getBondad(), 2) - Math.pow(h.getMaldad(), 2) - h.getInteligencia())
                / (Math.pow(h.getFe(), 2) - Math.pow(h.getBondad(), 2));
    }


    /**
     * Aplica la habilidad de Proteger sobre un Humano.
     * Dado que Proteger es una habilidad nativa de los Ángeles,
     * este método no tiene un efecto sustancial o fórmula asociada para el Demonio,
     * y por lo tanto, devuelve un valor neutral (cero) en la prueba.
     * @param h El objeto Humano sobre el cual se aplica la prueba.
     * @return 0.0, indicando que la prueba de protección del Demonio no tiene resultado.
     */
    @Override
    public double proteger(Humano h) {
        return 0;
    }

    /**
     * Aplica la habilidad de Probar sobre un Humano.
     * Dado que Probar es una habilidad nativa de los Ángeles,
     * este método no tiene un efecto sustancial o fórmula asociada para el Demonio,
     * y por lo tanto, devuelve un valor neutral (cero) en la prueba.
     * @param h El objeto Humano sobre el cual se aplica la prueba.
     * @return 0.0, indicando que la prueba de 'probar' del Demonio no tiene resultado.
     */
    @Override
    public double probar(Humano h) {
        return 0;
    }
}
