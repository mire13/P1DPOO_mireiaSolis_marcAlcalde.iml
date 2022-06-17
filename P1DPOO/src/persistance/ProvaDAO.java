package persistance;

import business.Edicio;
import business.Prova;

import java.util.LinkedList;

/**
 * Interfície implementada en diferents clases per fer servir els mateixos mètodes per la llista de proves
 */
public interface ProvaDAO {
    /**
     * Mètode que serveix per escriure en el ficher a la llista de proves
     * @param proves llista que conté les proves
     */
    void escribir(LinkedList<Prova> proves);

    /**
     * Mètode que serveix per llegir la llista de proves del ficher
     * @return llista de proves
     */
    LinkedList<Prova> leer();

    /**
     * Mètode que serveix per eliminar una prova del ficher
     *
     * @param proves llista de proves
     * @param i posicio de la prova a eliminar de la llista
     */
    void elimina(LinkedList<Prova> proves, int i);
}
