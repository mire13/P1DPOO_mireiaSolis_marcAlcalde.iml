package business;

import persistance.JugadorJsonDAO;
import java.util.ArrayList;

/**
 * Método que sirve para controlar y gestionar los jugadores
 */
public class JugadorsManager {

    private ArrayList<Jugador> jugadors;
    private JugadorJsonDAO jugadorDAO;

    /**
     * Constructor para inicializar la lista de jugadores y del DAO
     */
    public JugadorsManager(){
        this.jugadorDAO = new JugadorJsonDAO();
        this.jugadors = new ArrayList<Jugador>();
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
    public ArrayList<Jugador> getJugadors() {
        return jugadors;
    }
    public Jugador getJugador(int any, String nom) {
        for(Jugador j : jugadors)
            if (j.getAny() == any && j.getNom() == nom)
                return j;
        return null;
    }

    /**
     * Método que sirve para premiar a un jugador
     * @param any int con el año
     * @param nom String con el nombre
     * @param punts int con los puntos
     */
    public void premiar(int any, String nom, int punts) {
        getJugador(any, nom).premiar(punts);
    }

    /**
     * Método que sirve para penalizar a un jugador
     * @param any int con el año
     * @param nom String con el nombre
     * @param punts int con los puntos
     */
    public void penalitzar(int any, String nom, int punts){
        getJugador(any, nom).penalitzar(punts);
    }

    /**
     * Método que sirve para escribir en el fichero la informacion del jugador
     */
    public void escriure() {
        String[] info = new String[jugadors.size()];
        int i = 0;

        for (Jugador j : jugadors) {
            info[i] = j.getInfoCSV();
            i++;
        }

        jugadorDAO.escriure(info);
    }

    /**
     * Método que sirve para leer del fichero la informacion del jugador
     */
    public void llegir(boolean isCSV) {
        String[] lines;
        if (true){
            lines = jugadorDAO.llegirCSV();
            // Itera per cada linia de l'arxiu
            for (int i = 0; i < lines.length; i++) {
                // Separa la linia per cada coma
                String[] line = lines[i].split(",");

                // Converteix cada element en el tipus de dada corresponent
                String tipus = line[0];
                int any = Integer.parseInt(line[1]);
                String nom = line[2];
                int PI = Integer.parseInt(line[3]);

                if (tipus.equals("Enginyer"))
                    creaJugador(new Enginyer(any, nom, PI));
                if (tipus.equals("Master"))
                    creaJugador(new Master(any, nom, PI));
                if (tipus.equals("Doctor"))
                    creaJugador(new Doctor(any, nom, PI));
                //else if (tipus.equals("Master"))
            }
        } else if (false){
            lines = jugadorDAO.llegirJSON();
        }
    }
}