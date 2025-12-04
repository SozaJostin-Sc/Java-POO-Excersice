package entidades.fisicas;

import entidades.incorporeas.Angel;
import entidades.incorporeas.Demonio;
import subClases.*;

import java.util.Random;

/**
 * Representa a una entidad Humana en la simulación "La Batalla de las Almas".
 * <p>
 * Un Humano es una entidad Física que implementa la interfaz Espiritual,
 * poseyendo atributos morales y cognitivos que cambian a lo largo de los turnos de batalla.
 * </p>
 * <p>
 * Extiende la clase Fisico y debe
 * tener todos los métodos de las interfaces que implemente.
 * </p>
 */
public class Humano extends Fisico implements Espiritual {

    // --- Atributos de Humano ---

    /** Nivel de inteligencia del Humano. Influye en ciertas fórmulas de habilidad. */
    private double inteligencia;
    /** Nivel de fe del Humano. Aumenta con el rezo, disminuye si no reza con éxito. */
    private double fe;
    /** Nivel de bondad del Humano. Aumenta si gana el Ángel, disminuye si gana el Demonio. */
    private double bondad;
    /** Nivel de maldad del Humano. Aumenta si gana el Demonio, disminuye si gana el Ángel. */
    private double maldad;
    /** Valor general que representa el estado del alma. (No usado directamente en el conflicto moral provisto). */
    private double alma;

    // --- Constantes para la Inicialización ---

    /** Generador de números aleatorios para la asignación inicial de atributos. */
    private static final Random rd = new Random();
    /** Valor máximo posible para el atributo inteligencia. */
    private static final int MAX_INTELIGENCIA = 200;
    /** Valor máximo posible para el atributo fe. */
    private static final int MAX_FE = 100;
    /** Valor máximo posible para el atributo bondad. */
    private static final int MAX_BONDAD = 100;
    /** Valor máximo posible para el atributo maldad. */
    private static final int MAX_MALDAD = 100;
    /** Valor máximo posible para el atributo almo. */
    private static final int MAX_ALMA = 200;

    /**
     * Constructor de la clase Humano.
     * <p>
     * Inicializa todos los atributos (inteligencia, fe, bondad, maldad, alma)
     * con valores aleatorios dentro de los rangos definidos por las constantes MAX_*.
     * </p>
     */
    public Humano() {
        this.inteligencia = rd.nextInt(MAX_INTELIGENCIA);
        this.fe = rd.nextInt(MAX_FE);
        this.bondad = rd.nextInt(MAX_BONDAD);
        this.maldad = rd.nextInt(MAX_MALDAD);
        this.alma = rd.nextInt(MAX_ALMA);
    }

    // -------------------------------------------------------------------------
    /// --- Lógica del Combate y Conflicto Moral ---
    // -------------------------------------------------------------------------

    /**
     * Someter a este Humano a un conflicto moral entre un Ángel y un Demonio.
     * <p>
     * Este método actúa como orquestador del turno: selecciona habilidades al azar,
     * calcula las puntuaciones de cada ente incorpóreo mediante {@code golpear()},
     * determina el ganador y aplica los cambios resultantes a la moralidad (bondad/maldad)
     * y a la fe del Humano.
     * </p>
     *
     * @param a El Ángel participante en el conflicto (el "Bien").
     * @param d El Demonio participante en el conflicto (el "Mal").
     */
    public void conflictoMoral(Angel a, Demonio d) {

        // 1. Selección de Habilidades (mediante el método luchar())
        int habilidadAngel = a.luchar();
        int habilidadDemonio = d.luchar();

        // 2. Llamada a golpear() para obtener las puntuaciones
        // Se pasa el Ángel 'a' como contexto adicional (angelEnemigo) para que las fórmulas del Demonio
        // que dependen de la Fe del Ángel puedan ejecutarse.
        double puntajeAngel = this.golpear(a, habilidadAngel, a);
        double puntajeDemonio = this.golpear(d, habilidadDemonio, a);

        System.out.println("  -> Conflicto: Ángel (" + habilidadAngel + ") vs Demonio (" + habilidadDemonio + ")");
        System.out.printf("  -> Puntuaciones: Ángel=%.2f, Demonio=%.2f%n", puntajeAngel, puntajeDemonio);

        // 3. Determinar el ganador y aplicar las modificaciones a Bondad/Maldad.
        if (puntajeAngel > puntajeDemonio) {
            // Gana el Ángel: La moralidad se inclina hacia el Bien
            this.bondad += 1.0;
            // Asegura que la maldad nunca caiga por debajo de 0
            this.maldad = Math.max(0.0, this.maldad - 1.0);
            System.out.println("  -> ¡Gana el Ángel! Bondad++, Maldad--");

        } else if (puntajeDemonio > puntajeAngel) {
            // Gana el Demonio: La moralidad se inclina hacia el Mal
            // Asegura que la bondad nunca caiga por debajo de 0
            this.bondad =  Math.max(0.0, this.bondad - 1.0);
            this.maldad += 1.0;
            System.out.println("  -> ¡Gana el Demonio! Bondad--, Maldad++");

        } else {
            // Empate
            System.out.println("  -> Empate. No hay cambios morales.");
        }

        // 4. Intento de Rezo y ajuste de Fe
        if (this.rezar()) { // Llama a la lógica de rezo del Humano
            this.fe += 1.0;
            System.out.println("  -> El Humano reza con éxito. Fe++");
        } else {
            // Asegura que la fe nunca caiga por debajo de 0
            this.fe = Math.max(0.0, this.fe - 1.0);
            System.out.println("  -> El Humano no puede rezar. Fe--");
        }

        // 5. Aplicar límites de atributos (100.0)
        this.bondad = Math.min(MAX_BONDAD, this.bondad);
        this.maldad = Math.min(MAX_MALDAD, this.maldad);
        this.fe = Math.min(MAX_FE, this.fe);
    }

    /**
     * Ejecuta una habilidad específica de un ente Incorpóreo (Ángel o Demonio)
     * sobre este Humano y obtiene la puntuación del ataque/prueba.
     * <p>
     * Utiliza {@code instanceof} para determinar si el ente es un Ángel o Demonio y
     * llama al método de habilidad correspondiente.
     * </p>
     *
     * @param s El ente Incorpóreo que aplica la habilidad.
     * @param habilidad El índice de la habilidad a ejecutar (0-5).
     * @param angelEnemigo El Ángel que participa en el conflicto (usado por la fórmula de {@code enganiar} del Demonio).
     * @return La puntuación obtenida por el Incorpóreo tras aplicar la habilidad (double).
     */
    public double golpear(Incorporeo s, int habilidad, Angel angelEnemigo){

        // Ejecución de habilidades del Ángel
        if(s instanceof Angel angel){
            return switch (habilidad) {
                // Habilidad 0 (enganar) requiere el Ángel como argumento (se pasa a sí mismo)
                case 0 -> angel.enganiar(this, angel);
                case 1 -> angel.guiar(this);
                case 2 -> angel.proteger(this);
                case 3 -> angel.tentar(this);
                case 4 -> angel.seducir(this);
                case 5 -> angel.probar(this);
                default -> 0.0;
            };
            // Ejecución de habilidades del Demonio
        }else if (s instanceof Demonio demonio) {
            return switch (habilidad) {
                // Habilidad 0 (enganar) requiere el Ángel del conflicto (angelEnemigo) para el cálculo de la fórmula.
                case 0 -> demonio.enganiar(this, angelEnemigo);
                case 1 -> demonio.guiar(this);
                case 2 -> demonio.proteger(this);
                case 3 -> demonio.tentar(this);
                case 4 -> demonio.seducir(this);
                case 5 -> demonio.probar(this);
                default -> 0.0;
            };
        }
        return 0.0; // Ente Incorpóreo inválido
    }

    // -------------------------------------------------------------------------
    // ========= Implementación de Interfaz Espiritual ============
    // -------------------------------------------------------------------------

    /**
     * Implementación del método rezar() definido en la interfaz Espiritual.
     * <p>
     * Determina si el Humano reza con éxito. En este caso, la lógica es:
     * El Humano reza si su Fe es mayor a 50 O si su Bondad es mayor a 60.
     * </p>
     *
     * @return true si el Humano reza con éxito, false en caso contrario.
     */
    @Override
    public boolean rezar() {
        return getFe() > 50 || getBondad() > 60;
    }

    /// GETTER Y SETTERS
    public double getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(double inteligencia) {
        this.inteligencia = inteligencia;
    }

    public double getFe() {
        return fe;
    }

    public void setFe(double fe) {
        this.fe = fe;
    }

    public double getBondad() {
        return bondad;
    }

    public void setBondad(double bondad) {
        this.bondad = bondad;
    }

    public double getMaldad() {
        return maldad;
    }

    public void setMaldad(double maldad) {
        this.maldad = maldad;
    }

    public double getAlma() {
        return alma;
    }

    public void setAlma(double alma) {
        this.alma = alma;
    }

}
