package business;

/**
 * Clase generada por la herencia del Jugador con unos atributos diferentes
 */
public class Enginyer extends Jugador {

    /**
     * Constructor que genera un nuevo jugador de tipo Ingeniero
     * @param nom String que contiene el número del jugador
     * @param pi int que contiene la puntuacion del jugador
     * @param any int con el año
     */
    public Enginyer(int any, String nom, int pi) {
        super(any, nom, pi);
    }

    /**
     * Método para coger el tipo de jugador Ingeniero
     * @return String con la palabra ingeniero
     */
    public String getType() { return "Enginyer"; }

    /**
     * Método que sirve para tomar el nombre del ingeniero
     * @return String del nombre del ingeniero
     */
    @Override
    public String getTitle() {
        return nom;
    }

    /**
     * Método que sirve para coger la información
     * @return String con la información
     */
    public String getInfoCSV() {
        return "Enginyer" + "," + any + "," + nom + "," + pi;
    }

    /**
     * Método que sirve para calcular la experiencia cuando premiamos
     * @param quantitat int con la cantidad
     */
    public void premiar(int quantitat){
        pi = pi + quantitat;
    }

    /**
     * Método que sirve para calcular la experiencia cuando penalizamos
     * @param quantitat int con la cantidad
     */
    public void penalitzar(int quantitat){
        pi = pi - quantitat;
    }
}
