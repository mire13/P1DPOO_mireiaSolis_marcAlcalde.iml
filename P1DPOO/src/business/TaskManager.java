package business;

import presentation.Controller;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Clase que controla y gestiona a los managers
 */
public class TaskManager {

    private final ProvesManager provesManager;
    private final EdicionsManager edicionsManager;
    private final JugadorsManager jugadorsManager;
    private boolean isCSV;

    /**
     * Contructor por defecto que crea a los demás managers
     */
    public TaskManager() {
        provesManager = new ProvesManager();
        edicionsManager = new EdicionsManager();
        jugadorsManager = new JugadorsManager();
    }

    /**
     * Método que sirve para leer del fichero CSV al principio
     */
    public void leerCSV(){
        provesManager.llegir(true);
        edicionsManager.llegir(provesManager.getProves(), true);
        jugadorsManager.llegir(true);
        isCSV = true;
    }

    /**
     * Método que sirve para leer del fichero JSON al principio
     */
    public void leerJSON() {
        provesManager.llegir(false);
        edicionsManager.llegir(provesManager.getProves(), false);
        jugadorsManager.llegir(false);
        isCSV = false;
    }

    /**
     * Método que sirve para coger las pruebas
     * @return LinkedList de las pruebas
     */
    public LinkedList<Prova> getProves() {
        return provesManager.getProves();
    }

    /**
     * Método que sirve para crear una prueba
     * @param prova contiene la informacion de la prueba
     */
    public void creaProva(Prova prova) {
        provesManager.creaProva(prova, isCSV);
    }

    /**
     * Método para saber si hay pruebas
     * @return boolean que dice si hay o no pruebas
     */
    public boolean absenceOfTrials() {
        return provesManager.absenceOfTrials();
    }

    /**
     * Método que sirve para eliminar pruebas
     * @param i int con la prueba a eliminar
     */
    public void eliminaProva(int i, String nomProva) {
        provesManager.eliminaProva(i);
        for(Edicio e : edicionsManager.getEdicions()) {
            e.updateProves(i);
        }
        edicionsManager.escriureCSV();
    }

    /**
     * Método que sirve para la prueba en curso
     * @param nom String con el nombre de la prueba
     * @return boolean
     */
    public boolean provaEnUs(String nom) {
        return edicionsManager.provaEnUs(nom, provesManager.getProves());
    }

    /**
     * Método que sirve para saber si coinciden los nombres
     * @param opcio int con la opcion
     * @param nom String con el nombre de la prueba
     * @return boolean para saber si coinciden
     */
    public boolean elsNomsCoincideixen(int opcio, String nom) {
        return provesManager.elsNomsCoincideixen(opcio, nom);
    }

    /**
     * Método que sirve para saber si ese nombre ya existe
     * @param nom String con el nombre de la prueba
     * @return boolean que nos dice si ya existe
     */
    public boolean nomRepetit(String nom) {
        return provesManager.nomRepetit(nom);
    }

    /**
     * Método que sirve para coger las ediciones
     * @return LinkedList de las ediciones
     */
    public LinkedList<Edicio> getEdicions() {
        return edicionsManager.getEdicions();
    }

    /*public void addProvaToEdicio(int seleccio) {
        edicionsManager.AddProvaToEdicio(GetProves().get(seleccio));
    }*/

    /**
     * Método que sirve para crear una edicion
     * @param edicio contiene la informacion de la edicion
     */
    public void crearEdicio(Edicio edicio) {
        edicionsManager.creaEdicio(edicio, isCSV);
    }

    /**
     * Método que sirve para duplicar una edicion
     * @param opcio int con la opcion
     * @param any int con el año de la edicion
     * @param numJugadors int con el nombre total de jugadores
     */
    public void duplicarEdicio(int opcio, int any, int numJugadors) {
        edicionsManager.duplicarEdicio(opcio, any, numJugadors);
    }

    /**
     * Método que sirve para confirmar el año a eliminar
     * @param anyAEliminar int con el año a eliminar
     * @param opcio int con la opcion
     * @return boolean para saber si se ha eliminado
     */
    public boolean confirmacioAny(int anyAEliminar, int opcio) {
        return edicionsManager.confirmacioAny(anyAEliminar, opcio);
    }

    /**
     * Método que sirve para eliminar una edicion
     * @param i int con la edicion a eliminar
     */
    public void eliminaEdicio(int i) {
        edicionsManager.eliminaEdicio(i);
    }

    /**
     * Método que sirve para saber si un año esta repetido
     * @param any int con el año
     * @return boolean que nos dice si esta repetido
     */
    public boolean anyRepetit(int any) {
        return edicionsManager.anyRepetit(any);
    }

    /**
     * Método que sirve para las ediciones en curso
     * @param anyActual int con el año actual
     * @return int
     */
    public int edicioEnCurs(int anyActual) {
        int i=0;
        for(Edicio e : edicionsManager.getEdicions()) {
            if (e.getAny() == anyActual) return i;
            i++;
        }
        return -1;
    }

    /**
     * Método que sirve para crear un jugador
     * @param jugador contiene la informacion del jugador
     */
    // JUGADORS
    public void creaJugador(Jugador jugador) {
        jugadorsManager.creaJugador(jugador);
    }

    /**
     * Método que sirve para saber si quedan jugadores con puntos
     * @param anyActual int con el año actual
     * @return boolean para saber si quedan o no jugadores
     */
    public boolean quedenJugadorsAmbPunts(int anyActual) {
        int jugadorsVius = 0;
        for(Jugador j : jugadorsManager.getJugadors())
            if (j.getAny()==anyActual && j.getPi()>0)
                jugadorsVius++;
        return jugadorsVius > 0;

    }

    /**
     * Método que sirve para escribir en los ficheros
     */
    public void escriure() {
        provesManager.escriure();
        edicionsManager.escriureCSV();
        jugadorsManager.escriure();
    }

    /**
     * Método que sirve para cambiar el tipo de jugador
     * @param anyActual int con el año actual
     * @param controller controlador
     */
    public void cambiarTipusJugadors(int anyActual, Controller controller) {
        for(int i = 0; i<jugadorsManager.getJugadors().size(); i++) {
            Jugador j = jugadorsManager.getJugadors().get(i);
            if (j.getAny() == anyActual && j.getPi() >= 10 && !(j instanceof Doctor)) {
                if (j instanceof Enginyer) {
                    controller.showEnginyerCanviaMaster(j.getNom());
                    Master m = new Master(anyActual, j.getNom(), 5);
                    creaJugador(m);
                }
                if (j instanceof Master) {
                    controller.showMasterCanviaDoctor(j.getNom());
                    Doctor m = new Doctor(anyActual, j.getNom(), 5);
                    creaJugador(m);
                }
                jugadorsManager.eliminarJugador(i);
                i--;
            }
        }
    }

    /**
     * Método que sirve para coger la informacion de los jugadores
     * @return ArrayList de jugadores
     */
    public ArrayList<Jugador> getJugadors() {
        return jugadorsManager.getJugadors();
    }

    /**
     * Método que sirve para reiniciar la edicion
     * @param anyActual int con el año actual
     */
    public void reiniciarEdicio(int anyActual) {
        for(Edicio e : edicionsManager.getEdicions())
            if (e.getAny() == anyActual) e.setCurrentState(0);
        jugadorsManager.netejarLlista();
    }

    /**
     * Setter para asignar si ha escogido csv o json como opcion de persistencia
     *
     * @param isCSV true si es csv false si es json
     */
    public void setCSV(boolean isCSV) {
        provesManager.setCSV(isCSV);
    }
}