package business;

import java.util.ArrayList;

/**
 * Clase que contiene el métodos y atributos de la Edicio
 */
public class Edicio {
    private int any;
    private int totalJugadors;
    private int totalProves;
    private int[] proves;
    private int currentState;

    /**
     * Constructor para inicializar los datos de una edición
     */
    public Edicio(int any, int totalJugadors, int totalProves, int[] proves){
        this.any = any;
        this.totalJugadors = totalJugadors;
        this.totalProves = totalProves;
        this.proves = proves;
        this.currentState = 0;
    }

    /**
     * Método que sirve para coger el año de la edición
     * @return int con el año de aquella edicion correspondiente
     */
    public int getAny() {
        return any;
    }

    /**
     * Método que sirve para coger el número de jugadores de la edición
     * @return int con el número de jugadores correspondiente
     */
    public int getTotalJugadors() {
        return totalJugadors;
    }

    /**
     * Método que sirve para coger el número de pruebas
     * @return int con el número de pruebas
     */
    public int getTotalProves() {
        return totalProves;
    }

    /**
     * Método que sirve para coger las pruebas
     * @return String con el nombre de las pruebas
     */
    public int[] getProves() { return proves; }

    /**
     * Método que sirve para saber el estado actual de la edicion
     * @param currentState int con el estado actual
     */
    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    /**
     * Método que sirve para devolver el estado actual de la edicion
     * @return currentState int con el estado actual
     */
    public int getCurrentState() {
        return currentState;
    }

    /**
     * Método que sirve para actualizar pruevas
     * @param index int
     */
    public void updateProves(int index) {
        for(int i=0; i<proves.length; i++) {
            if (proves[i]>index)
                proves[i] = proves[i] -1;
        }
    }

}