package persistance;

import business.Edicio;
import java.util.LinkedList;

/**
 * Interfície implementada en diferents clases per fer servir els mateixos mètodes per la llista de edicions
 */
public interface EdicioDAO {
    /**
     * Mètode que serveix per escriure en el ficher a la llista de edicions
     * @param edicioLlista llista que conté les edicions
     */
    void escribir(LinkedList<Edicio> edicioLlista);

    /**
     * Mètode que serveix per llegir la llista de edicions del ficher
     * @return llista de edicions
     */
    LinkedList<Edicio> leer();
}
