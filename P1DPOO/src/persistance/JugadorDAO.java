package persistance;

import business.Jugador;

import java.util.LinkedList;

/**
 * Interfície implementada en diferents clases per fer servir els mateixos mètodes per la execució
 */
public interface JugadorDAO {

    /**
     * Comprova que la direcció que s'ha assignat al ficher existeixi
     */
    void checkPath();
    /**
     * Método que sirve para chekear el path y escribir en el fichero
     * @param jugador LinkedLIST con la informacion a escribir
     */
    void escriure(LinkedList<Jugador> jugador);

    /**
     * Método que sirve para leer del fichero
     * @return String con la informacion
     */
    LinkedList<Jugador> llegir();

}
