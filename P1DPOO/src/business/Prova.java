package business;

import presentation.Controller;
import presentation.Vista;

import java.util.ArrayList;
/**
 * Clase que contiene el métodos y atributos de la Prueba
 */
public abstract class Prova {
    protected String nom;

    /**
     * Método para coger el nombre de la prueba
     * @return String con el nombre de la prueba
     */
    public String getNom() {
        return nom;
    }

    /**
     * Método para coger el tipo de prueba
     * @return String con el tipo
     */
    public abstract String getType();

    /**
     * Método para coger la información de la prueba
     * @return String[] con la informacion de la prueba
     */
    public abstract String[] getInfo();

    /**
     * Método para coger la informacion del fichero
     * @return String con la informacion del fichero
     */
    public abstract String getInfoCSV();

    /**
     * Método para ejecutar la prueba
     * @param index int
     * @param totalJugadors int con el total de jugadores
     * @param any int con el año de la prueba
     * @param jugadors ArrayList con los jugadores
     */
    public abstract void executarProva(int index, int totalJugadors, int any, ArrayList<Jugador> jugadors, Controller controller);

}



