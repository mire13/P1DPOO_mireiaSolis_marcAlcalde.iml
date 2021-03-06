package business;

import presentation.Controller;

import java.util.LinkedList;
import java.util.Random;
/**
 * Clase que contiene las pruebas de tipo publicacion
 */
public class ProvaPublicacio extends Prova {
    private String journalName;
    private String quartile;
    private int acceptanceProbability;
    private int revisionProbability;
    private int rejectionProbability;

    public static final String TRIAL_TYPE = "Prova Publicacio";

    /**
     * Constructor que crea un objeto con los atributos correspondientes
     * @param trialName String que contiene el nombre de la prueba
     * @param journalName String que contiene el nombre de la publicacion
     * @param quartile String que contiene el quartil
     * @param acceptanceProbability int que contiene la probabilidad de aceptacion
     * @param revisionProbability int que contiene la probabilidad de revision
     * @param rejectionProbability int que contiene la probabilidad de rejeccion
     */
    public ProvaPublicacio(String trialName, String journalName, String quartile, int acceptanceProbability, int revisionProbability, int rejectionProbability){
        this.nom = trialName;
        this.journalName = journalName;
        this.quartile = quartile;
        this.acceptanceProbability = acceptanceProbability;
        this.revisionProbability = revisionProbability;
        this.rejectionProbability = rejectionProbability;
    }

    /**
     * Método para coger la informacion del fichero
     * @return String con la informacion
     */
    public String getInfoCSV() {
        return TRIAL_TYPE + "," + getNom() + "," + journalName + "," + quartile + "," + acceptanceProbability + "," + revisionProbability + ","
                + rejectionProbability;
    }

    /**
     * Método para coger el tipo de prueba
     * @return String con el tipo de prueba
     */
    @Override
    public String getType() {
        return TRIAL_TYPE;
    }

    /**
     * Método para coger la informacion del master
     * @return String con la informacion del master
     */
    public String[] getInfo(){
        String[] info = new String[7];

        info[0] = nom;
        info[1] = TRIAL_TYPE;
        info[2] = journalName;
        info[3] = quartile;
        info[4] = Integer.toString(acceptanceProbability);
        info[5] = Integer.toString(revisionProbability);
        info[6] = Integer.toString(rejectionProbability);

        return info;
    }

    /**
     * Método para coger los puntos a penalizar
     * @return int con los puntos
     */
    private int getPuntsPenalitzar() {
        switch (quartile) {
            case "Q1":
                return 5;
            case "Q2":
                return 4;
            case "Q3":
                return 3;
            case "Q4":
                return 2;
        }
        return 0;
    }

    /**
     * Método para coger los puntos a premiar
     * @return int con los puntos
     */
    private int getPuntsPremiar() {
        switch (quartile) {
            case "Q1":
                return 4;
            case "Q2":
                return 3;
            case "Q3":
                return 2;
            case "Q4":
                return 1;
        }
        return 0;
    }
    /**
     * Método para ejecutar una prueba
     * @param index int
     * @param totalJugadors int con el total de jugadores
     * @param any int con el año de la prueba
     * @param jugadors ArrayList con los jugadores
     * @param controller controlador
     */
    public void executarProva(int index, int totalJugadors, int any, LinkedList<Jugador> jugadors, Controller controller) {
        for(Jugador jugador : jugadors) {
            if (jugador.getAny() == any && jugador.getPi()>0) {
                // Executa la prova de publicacio per un jugador especific
                controller.showSubmission(jugador.getTitle());

                int value = 0;
                Random rand = new Random();

                // Repetim la prova sempre que random dongui a revisar
                do {
                    // Calcula el seguent random
                    value = rand.nextInt(100);
                    if (value < revisionProbability) {
                        System.out.print(" Revisions...");
                    } else if (value >= revisionProbability && value < (revisionProbability + rejectionProbability)) {
                        // Penalitzem per haver sigut rebutjat
                        jugador.penalitzar(getPuntsPenalitzar());
                        System.out.print(" Rejected. PI count: " + jugador.getPi() + "\n");
                    } else if (value >= (revisionProbability + rejectionProbability) && value < 100) {
                        // Premiem per haver sigut acceptat
                        jugador.premiar(getPuntsPremiar());
                        System.out.print(" Accepted! PI count: " + jugador.getPi() + "\n");
                    }
                } while (value < revisionProbability);
            }
        }
    }
}
