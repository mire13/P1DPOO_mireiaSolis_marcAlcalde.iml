package business;

import persistance.JugadorCsvDAO;
import persistance.JugadorDAO;
import persistance.JugadorJsonDAO;
import java.util.LinkedList;

/**
 * Método que sirve para controlar y gestionar los jugadores
 */
public class JugadorsManager {

    private LinkedList<Jugador> jugadors;
    private JugadorDAO jugadorDAO;

    /**
     * Constructor para inicializar la lista de jugadores y del DAO
     */
    public JugadorsManager(){
        this.jugadorDAO = new JugadorJsonDAO();
        this.jugadors = new LinkedList<>();
    }

    /**
     * Método para crear un jugador
     * @param jugador con la información del jugador
     */
    public void creaJugador(Jugador jugador) {
        jugadors.add(jugador);
    }

    /**
     * Método para eliminar un jugador
     * @param index int
     */
    public void eliminarJugador(int index) {
        jugadors.remove(index);
    }

    /**
     * Método para limpiar la lista de jugadores
     */
    public void netejarLlista() {
        jugadors.clear();
    }

    /**
     * Método para coger la informacion de los jugadores
     * @return ArrayList de jugadores
     */
    public LinkedList<Jugador> getJugadors() {
        return jugadors;
    }

    /**
     * Método que sirve para escribir en el fichero la informacion del jugador
     */
    public void escriure() {
        jugadorDAO.escriure(jugadors);
    }

    /**
     * Método que sirve para leer del fichero la informacion del jugador
     */
    public void llegir() {
        jugadors = jugadorDAO.llegir();
    }

    /**
     * Metodo que hace una nueva instancia del CsvDAO o JsonDAO en funcion de lo que escoja el usuario
     *
     * @param isCSV boolean para asignar el valor, true si es CSV false si es Json
     */
    public void setPersistanceType(boolean isCSV) {
        if (isCSV) {
            jugadorDAO = new JugadorCsvDAO();
        } else {
            jugadorDAO = new JugadorJsonDAO();
        }
    }
}