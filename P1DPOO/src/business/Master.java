package business;
/**
 * Clase generada por la herencia del Jugador con unos atributos diferentes
 */
public class Master extends Enginyer {
    /**
     * Constructor que genera un nuevo jugador de tipo Master
     * @param nom String que contiene el número del jugador
     * @param pi int que contiene la puntuacion del jugador
     * @param any int con el año
     */
    public Master(int any, String nom, int pi) {
        super(any, nom, pi);
    }

    /**
     * Método para coger el tipo de jugador Master
     * @return String con la palabra master
     */
    public String getType() { return "Master"; };

    /**
     * Método que sirve para tomar el nombre del master
     * @return String del nombre del master
     */
    public String getTitle() {
        return "Master " + nom;
    }

    /**
     * Método que sirve para coger la información
     * @return String con la información
     */
    public String getInfoCSV() {
        return "Master" + "," + any + "," + nom + "," + pi;
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
        pi = pi - (int) Math.floor(quantitat / 2);
        if (pi < 0) {
            pi = 0;
        }
    }
}