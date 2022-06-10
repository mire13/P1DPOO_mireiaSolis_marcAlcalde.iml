package business;
/**
 * Clase que contiene los métodos y atributos del jugador
 */
public abstract class Jugador {
    public int any;
    public String nom;
    public int pi;

    /**
     * Constructor con parámetros de la clase jugador
     * @param nom String con el nombre del jugador
     * @param pi int con la puntuacion actualizada del jugador
     */
    public Jugador(int any, String nom, int pi){
        this.any = any;
        this.nom = nom;
        this.pi = pi;
    }

    public int getAny() { return any; };

    /**
     * Método que sirve para tomar el nombre del jugador
     * @return String del nombre del ingeniero
     */
    public String getNom() { return nom; };

    /**
     * Método que sirve para tomar el nombre de jugador
     * @return String del nombre del jugador
     */
    public abstract String getTitle();

    /**
     * Método para coger el PI del jugador
     * @return int con el PI del jugador
     */
    public int getPi() {
        return pi;
    }

    /**
     * Método que sirve para calcular la experiencia cuando premiamos
     * @param quantitat int con la cantidad
     */
    public abstract void premiar(int quantitat);

    /**
     * Método que sirve para calcular la experiencia cuando penalizamos
     * @param quantitat int con la cantidad
     */
    public abstract void penalitzar(int quantitat);

    /**
     * Método que sirve para coger la información
     * @return String con la información
     */
    public abstract String getInfoCSV();

    /**
     * Método para coger el tipo de jugador
     * @return String con la palabra de tipo de jugador
     */
    public abstract String getType();
}
