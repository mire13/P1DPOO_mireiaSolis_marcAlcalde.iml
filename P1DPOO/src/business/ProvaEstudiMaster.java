package business;

import presentation.Controller;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que contiene las pruebas de tipo Estudi Master
 */
public class ProvaEstudiMaster extends Prova {

    private String nomMaster;
    private int credits;
    private int probabilitat;

    public static final String TRIAL_TYPE = "Prova Estudi Master";

    /**
     * Constructor que crea un objeto con los atributos correspondientes
     * @param trialName String que contiene el nombre de la prueba
     * @param master String que contiene el nombre del master
     * @param credits int que contiene el número de créditos
     * @param probabilitat int que contiene la probabilidad
     */
    public ProvaEstudiMaster(String trialName, String master, int credits, int probabilitat){
        this.nom = trialName;
        this.nomMaster = master;
        this.credits = credits;
        this.probabilitat = probabilitat;
    }

    /**
     * Método para coger la informacion del master
     * @return String con la informacion del master
     */
    @Override
    public String[] getInfo() {
        String[] info = new String[5];

        info[0] = nom;
        info[1] = TRIAL_TYPE;
        info[2] = nomMaster;
        info[3] = Integer.toString(credits);
        info[4] = Integer.toString(probabilitat);

        return info;
    }

    /**
     * Método para coger la informacion del fichero
     * @return String con la informacion
     */
    @Override
    public String getInfoCSV() {
        return TRIAL_TYPE + "," + getNom() + "," + nomMaster + "," + credits + "," + probabilitat;
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
    public void executarProva(int index, int totalJugadors, int any, ArrayList<Jugador> jugadors, Controller controller) {
        for (Jugador jugador : jugadors) {
            if (jugador.getAny() == any && jugador.getPi() > 0) {
                // Calcul de com aprobar cada credit
                Random rand = new Random();
                int creditsSuperats = 0;
                for (int i = 0; i < credits; i++) {
                    if (rand.nextInt(100) <= probabilitat)
                        creditsSuperats++;
                }

                controller.showMasterStudy(jugador.getTitle(), creditsSuperats, credits);

                float ratio = (float) creditsSuperats / (float) credits;
                if (ratio < .5) {
                    jugador.penalitzar(3);
                    controller.showMasterFallat(jugador.getPi());
                } else {
                    if (jugador.getType().equals("Enginyer"))
                        jugador.premiar(10);
                    else
                        jugador.premiar(3);
                    controller.showMasterSuperat(jugador.getPi());
                }
            }
        }
    }
}
