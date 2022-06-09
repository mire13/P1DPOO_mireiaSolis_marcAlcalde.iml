package business;

/**
 * Clase generada por la herencia del Jugador con unos atributos diferentes
 */
public class Doctor extends Jugador {

    /**
     * Constructor para crear un nuevo jugador tipo Doctor
     * @param any int con el año
     * @param nom String del nombre del jugador
     * @param PI int con la experiencia
     */
    public Doctor(int any, String nom, int PI) {
        super(any, nom, PI);
    }

    /**
     * Método que sirve para coger el tipo Doctor
     * @return String "Doctor"
     */
    public String getType() { return "Doctor"; }

    /**
     * Método que sirve para coger el nombre
     * @return String con el nombre
     */
    public String getTitle() {
        return nom + ", PhD.";
    }

    /**
     * Método que sirve para coger la información
     * @return String con la información
     */
    public String getInfoCSV() {
        return "Doctor" + "," + any + "," + nom + "," + PI;
    }

    /**
     * Método que sirve para calcular la experiencia cuando premiamos
     * @param quantitat int con la cantidad
     */
    public void premiar(int quantitat){
        PI = PI + quantitat * 2;
    }

    /**
     * Método que sirve para calcular la experiencia cuando penalizamos
     * @param quantitat int con la cantidad
     */
    public void penalitzar(int quantitat){
        PI = PI - (int) Math.floor(quantitat / 2);
    }
}