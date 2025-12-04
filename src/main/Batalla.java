package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entidades.fisicas.*;
import entidades.incorporeas.*;


public class Batalla {

    private static final Random RANDOM = new Random();

    // --- Códigos de Colores ANSI ---
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    // ---------------------------------

    // --- Definición de Constantes de la Simulación ---
    private static final int NUM_HUMANOS = 5;
    private static final int NUM_ANGELES = 3;
    private static final int NUM_DEMONIOS = 3;
    private static final int NUM_TURNOS = 10;
    // -------------------------------------------------

    /**
     * Método principal (main) para iniciar la simulación "La Batalla de las Almas".
     * Los parámetros de la simulación están definidos como constantes dentro de la clase.
     */
    public static void main(String[] args) {

        final int numHumanos = NUM_HUMANOS;
        final int numAngeles = NUM_ANGELES;
        final int numDemonios = NUM_DEMONIOS;
        final int numTurnos = NUM_TURNOS;

        // Listas para almacenar las entidades
        List<Humano> humanos = new ArrayList<>();
        List<Angel> angeles = new ArrayList<>();
        List<Demonio> demonios = new ArrayList<>();


        // Construir el objeto DiosCristiano
        DiosCristiano dios = new DiosCristiano(numAngeles, numDemonios);

        System.out.println(ANSI_CYAN + "\n========== Inicializando el Mundo de la Batalla ==========" + ANSI_RESET);

        // Creación de las entidades
        for (int i = 0; i < numAngeles; i++) {
            angeles.add(new Angel());
        }
        for (int i = 0; i < numDemonios; i++) {
            demonios.add(new Demonio());
        }
        for (int i = 0; i < numHumanos; i++) {
            humanos.add(new Humano());
        }

        System.out.printf(ANSI_GREEN + "Creados %d Humanos, %d Ángeles, %d Demonios." + ANSI_RESET + "%n",
                numHumanos, numAngeles, numDemonios);

        // --- Bucle Principal de Turnos ---
        System.out.println(ANSI_PURPLE + "\n--- Comienza la Simulación (Turnos: " + numTurnos + ") ---" + ANSI_RESET);

        for (int t = 1; t <= numTurnos; t++) {
            System.out.printf(ANSI_CYAN + "\n===== TURNO %d/%d =====%n" + ANSI_RESET, t, numTurnos);

            // Iterar sobre cada hombre
            for (int i = 0; i < humanos.size(); i++) {
                Humano h = humanos.get(i);

                // Seleccionar un Ángel y un Demonio al azar
                Angel angelSeleccionado = angeles.get(RANDOM.nextInt(angeles.size()));
                Demonio demonioSeleccionado = demonios.get(RANDOM.nextInt(demonios.size()));

                // Mostrar estado inicial (Amarillo)
                System.out.printf(ANSI_YELLOW + "[Humano %d] Inicia (B:%.0f, M:%.0f, F:%.0f)" + ANSI_RESET + "%n",
                        i,  h.getBondad(),  h.getMaldad(), h.getFe());

                // Ejecutar el conflicto moral
                h.conflictoMoral(angelSeleccionado, demonioSeleccionado);

                // Mostrar estado final (Amarillo)
                System.out.printf(ANSI_YELLOW + "[Humano %d] Termina (B:%.0f, M:%.0f, F:%.0f)" + ANSI_RESET + "%n",
                        i,  h.getBondad(),  h.getMaldad(), h.getFe());

                System.out.println(ANSI_PURPLE + "-------------------------------------------" + ANSI_RESET);
                /// ---- Intervalo de tiempo para los turnos
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
            System.out.println(ANSI_PURPLE + "===================================================" + ANSI_RESET);


        }


        // --- Resultado Final ---
        System.out.println(ANSI_PURPLE + "\n--- Fin de la Batalla ---" + ANSI_RESET);
        int humanosSalvados = 0;

        for (int i = 0; i < humanos.size(); i++) {
            Humano h = humanos.get(i);
            String resultadoColor;
            String resultadoTexto;

            if (dios.esBuenHombre(h)) {
                humanosSalvados++;
                resultadoColor = ANSI_GREEN;
                resultadoTexto = "SALVADO";
            } else {
                resultadoColor = ANSI_RED;
                resultadoTexto = "EN PELIGRO";
            }

            System.out.printf("Humano %d: " + resultadoColor + "%s" + ANSI_RESET + " (Bondad=%.0f, Maldad=%.0f)%n",
                    i, resultadoTexto, h.getBondad(), h.getMaldad());
        }

        System.out.printf(ANSI_PURPLE + "\nRESUMEN: " + ANSI_GREEN + "%d Humanos salvados" + ANSI_PURPLE + " / %d totales." + ANSI_RESET + "%n", humanosSalvados, numHumanos);
    }
}