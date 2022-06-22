package business;

import presentation.Controller;

import java.util.LinkedList;

/**
 * Clase que contiene las pruebas de tipo tesi doctoral
 */
public class ProvaTesiDoctoral extends Prova {
    private String campEstudi;
    private int dificultat;

    public static final String TRIAL_TYPE = "Prova Tesi Doctoral";

    /**
     * Contructor que crea un objeto con los atributos correspondientes
     * @param trialName String con le nombre de la aprueba
     * @param campEstudi String con el nombre del campo de estudio
     * @param dificultat int con la dificultad
     */
    public ProvaTesiDoctoral(String trialName, String campEstudi, int dificultat){
        this.nom = trialName;
        this.campEstudi = campEstudi;
        this.dificultat = dificultat;
    }

    /**
     * Método para coger la informacion del master
     * @return String con la informacion del master
     */
    @Override
    public String[] getInfo() {
        String[] info = new String[4];

        info[0] = nom;
        info[1] = TRIAL_TYPE;
        info[2] = campEstudi;
        info[3] = Integer.toString(dificultat);

        return info;
    }

    /**
     * Método para coger la informacion del fichero
     * @return String con la informacion
     */
    @Override
    public String getInfoCSV() {
        return TRIAL_TYPE + "," + getNom() + "," + campEstudi + "," + dificultat;
    }

    /**
     * Método para coger el tipo de prueba
     * @return String con el tipo de prueba
     */
    public String getType() {
        return TRIAL_TYPE;
    }

    /**
     * Método para ejecutar una prueba
     * @param index int
     * @param totalJugadors int con el total de jugadores
     * @param any int con el año de la prueba
     * @param jugadors ArrayList con los jugadores
     * @param controller controlador
     */
    @Override
    public void executarProva(int index, int totalJugadors, int any, LinkedList<Jugador> jugadors, Controller controller) {
        for (Jugador jugador : jugadors) {
            if (jugador.getAny() == any && jugador.getPi() > 0) {

                // Calcula el quocient de la prova de la tesi doctoral
                float value = 0;
                for (int i = 1; i <= dificultat; i++)
                    value += 2 * i - 1;
                value = (float) Math.sqrt(value);

                if (jugador.getPi() > value) {
                    if (jugador.getType().equals("Master"))
                        jugador.premiar(10);
                    else
                        jugador.premiar(5);

                    controller.showDoctorSuperat(jugador.getNom(), jugador.getPi());

                } else {
                    jugador.penalitzar(5);
                    controller.showDoctorFallat(jugador.getNom(), jugador.getPi());
                }
            }
        }
    }
}
