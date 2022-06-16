package persistance;

import business.Edicio;

import java.util.ArrayList;
/**
 * Interfície implementada en diferents clases per fer servir els mateixos mètodes per la llista de edicioons
 */
public interface EdicioDAO {
    /**
     * Mètode que serveix per escriure en el ficher a la llista de edicions
     * @param edicioLlista llista que conté les edicions
     */
    void escribir(ArrayList<Edicio> edicioLlista);

    /**
     * Mètode que serveix per llegir la llista de edicions del ficher
     * @param edicioLlista llista que conté les edicions
     */
    void leer(ArrayList<Edicio> edicioLlista);
}
