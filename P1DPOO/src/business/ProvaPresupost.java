package business;

import presentation.Controller;
import presentation.Vista;

import java.util.ArrayList;
/**
 * Clase que contiene las pruebas de tipo Presupuesto
 */
public class ProvaPresupost extends Prova {
    private String entitat;
    private int presupost;

    public static final String TRIAL_TYPE = "Prova Presupost";

    /**
     * Constructor que crea un objeto con los atributos correspondientes
     * @param trialName String que contiene el nombre de la prueba
     * @param entitat String que contiene el nombre de la entidad
     * @param presupost int que contiene el presupuesto
     */
    public ProvaPresupost(String trialName, String entitat, int presupost){
        this.nom = trialName;
        this.entitat = entitat;
        this.presupost = presupost;
    }

    /**
     * Método para coger la informacion de la prueba
     * @return String con la informacion de la prueba
     */
    @Override
    public String[] getInfo() {
        String[] info = new String[4];

        info[0] = nom;
        info[1] = TRIAL_TYPE;
        info[2] = entitat;
        info[3] = Integer.toString(presupost);

        return info;
    }

    /**
     * Método para coger la informacion del fichero
     * @return String con la informacion
     */
    @Override
    public String getInfoCSV() {
        return TRIAL_TYPE + "," + getNom() + "," + entitat + "," + presupost;
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

        // Calcula els punts totals
        int totalPI = 0;
        for(Jugador jugador : jugadors)
            if (jugador.getPi() > 0 && jugador.getAny()==any)
                totalPI += jugador.getPi();

        float value = (float) (Math.log(presupost) / Math.log(2));

        if (totalPI > value) {
            controller.showPresupostSuperat();
        } else {
            controller.showPresupostFallat();
        }

        for(Jugador jugador : jugadors) {
            if (jugador.getPi() > 0) {
                if (totalPI > value) {
                    jugador.premiar((int) Math.ceil(jugador.getPi() / 2));
                } else {
                    jugador.penalitzar(2);
                }

                controller.showPresupostPuntsGuanyats(jugador.getNom(), jugador.getPi());
            }
        }
    }
}
