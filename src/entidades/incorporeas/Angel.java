package entidades.incorporeas;

import entidades.fisicas.Humano;
import subClases.Espiritual;
import subClases.Incorporeo;

import java.util.Random;

/**
 * Representa a un Ángel dentro del sistema "La Batalla de las Almas".
 * <p>
 * Los Ángeles son seres incorpóreos alineados al bien y poseen valores de fe,
 * bondad y maldad generados aleatoriamente dentro de los rangos definidos por
 * el enunciado. Esta clase implementa las habilidades específicas de un Ángel
 * durante los conflictos morales contra los Demonios.
 * </p>
 *
 * <p><b>Extiende:</b> Incorporeo<br>
 * <b>Implementa:</b> Espiritual</p>
 */
public class Angel extends Incorporeo implements Espiritual {

    /** Generador global de números aleatorios para los atributos del Ángel. */
    private static final Random rd = new Random();

    /** Valor máximo de fe permitido para un Ángel. */
    private static final int MAX_FE = 200;

    /** Valor máximo de bondad permitido para un Ángel. */
    private static final int MAX_BONDAD = 10;

    /** Valor máximo de maldad permitido para un Ángel. */
    private static final int MAX_MALDAD = 200;

    /**
     * Constructor del Ángel.
     * <p>
     * Aunque recibe parámetros de fe, bondad y maldad, estos no se utilizan.
     * En su lugar, el Ángel se inicializa con valores aleatorios dentro de
     * los rangos permitidos, detallados en el enunciado del proyecto.
     * </p>
     */
    public Angel() {
        super(rd.nextInt(MAX_FE), rd.nextInt(MAX_BONDAD), rd.nextInt(MAX_MALDAD));
    }


    /**
     * Implementación del método rezar para un Ángel.
     * <p>
     * Los Ángeles siempre pueden comunicarse con Dios, por lo que retornan true.
     * </p>
     *
     * @return siempre true, indicando que el Ángel puede rezar.
     */
    @Override
    public boolean rezar() {
        return true;
    }

    /**
     * Implementación del método engañar().
     * <p>
     * Los Ángeles no engañan a los Humanos según la lógica del sistema,
     * por lo que este método siempre devuelve 0.
     * </p>
     *
     * @param h Humano objetivo.
     * @param a Angel enemigo (uso en demonios)
     * @return siempre 0, indicando que el Ángel no utiliza esta habilidad.
     */
    @Override
    public double enganiar(Humano h, Angel a) {
        return 0;
    }


    /**
     * Habilidad de un Ángel para guiar moralmente a un Humano.
     * <p>
     * La fórmula depende de atributos del Humano: bondad, maldad,
     * inteligencia y fe. Si el denominador se aproxima a cero, puede
     * producir valores grandes o indefinidos, por lo que se recomienda
     * validar antes del uso en el motor de batalla.
     * </p>
     *
     * <pre>
     *         sqrt( bondad^2 - maldad^2 - inteligencia )
     *   --------------------------------------------------------
     *            ( fe^2 - maldad )
     * </pre>
     *
     * @param h Humano a guiar.
     * @return valor numérico de la guía espiritual.
     */
    @Override
    public double guiar(Humano h) {
        return Math.sqrt(
                Math.pow(h.getBondad(), 2)
                        - Math.pow(h.getMaldad(), 2)
                        - h.getInteligencia()
        ) / (Math.pow(h.getFe(), 2) - h.getMaldad());
    }

    /**
     * Implementación del método tentar().
     * <p>
     * Los Ángeles no tientan al Humano; esta acción es exclusiva de los Demonios.
     * Por lo tanto, siempre retorna 0.
     * </p>
     *
     * @param h Humano objetivo.
     * @return siempre 0.
     */
    @Override
    public double tentar(Humano h) {
        return 0;
    }

    /**
     * Implementación del método seducir().
     * <p>
     * Los Ángeles tampoco seducen a los Humanos, así que retorna 0.
     * </p>
     *
     * @param h Humano objetivo.
     * @return siempre 0.
     */
    @Override
    public double seducir(Humano h) {
        return 0;
    }

    /**
     * Habilidad del Ángel para proteger a un Humano.
     * <p>
     * La protección depende de la fe del Humano y su diferencia moral
     * entre bondad y maldad, ajustado por la fe del Ángel.
     * </p>
     *
     * <pre>
     *      fe_humano * ( ( bondad_humano - maldad_humano ) / fe_angel )
     * </pre>
     *
     * @param h Humano a proteger.
     * @return valor numérico correspondiente a la protección.
     */
    @Override
    public double proteger(Humano h) {
        return h.getFe() * ((double) (h.getBondad() - h.getMaldad()) / this.getFe());
    }

    /**
     * Habilidad del Ángel para probar moralmente a un Humano.
     * <p>
     * Esta habilidad evalúa cómo el Humano enfrenta conflictos internos,
     * comparando su fe y su maldad relativa a la inteligencia.
     * </p>
     *
     * <pre>
     *       fe_humano - ( maldad_humano^2 / inteligencia_humano )
     *   --------------------------------------------------------------
     *        sqrt( bondad_angel^2 - maldad_angel^2 )
     * </pre>
     *
     * @param h Humano sometido a prueba moral.
     * @return valor numérico del resultado de la prueba.
     */
    @Override
    public double probar(Humano h) {
        return (h.getFe() - Math.pow(h.getMaldad(), 2) / h.getInteligencia()) /
                Math.sqrt(Math.pow(this.getBondad(), 2) - Math.pow(this.getMaldad(), 2));
    }
}
