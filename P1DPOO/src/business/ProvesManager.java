package business;

import persistance.*;

import java.util.LinkedList;

/**
 * Método que sirve para controlar y gestionar las pruebas
 */
public class ProvesManager {

    private LinkedList<Prova> proves;
    private ProvaDAO provaDAO;

    /**
     * Constructor para inicializar la lista de pruebas y del DAO
     */
    public ProvesManager() {
        this.proves = new LinkedList<>();
    }

    /**
     * Método que sirve para recoger las pruebas
     * @return LinkedList de pruebas
     */
    public LinkedList<Prova> getProves() {
        LinkedList<Prova> list = proves;
        return list;
    }

    /**
     * Método que sirve para crear una prueva y añadirla a la lista
     * @param prova que contiene la informacion de prueva
     */
    public void creaProva(Prova prova) {
        if (prova.getClass().getSimpleName().equals("ProvaEstudiMaster")){
            // Afegeix la nova prova a la llista
            proves.add(new ProvaEstudiMaster(prova.getInfo()[0], prova.getInfo()[2], Integer.parseInt(prova.getInfo()[3]), Integer.parseInt(prova.getInfo()[4])));
        }
        if (prova.getClass().getSimpleName().equals("ProvaPresupost")){
            // Afegeix la nova prova a la llista
            proves.add(new ProvaPresupost(prova.getInfo()[0], prova.getInfo()[2], Integer.parseInt(prova.getInfo()[3])));
        }
        if (prova.getClass().getSimpleName().equals("ProvaPublicacio")){
            // Afegeix la nova prova a la llista
            proves.add(new ProvaPublicacio(prova.getInfo()[0], prova.getInfo()[2], prova.getInfo()[3], Integer.parseInt(prova.getInfo()[4]), Integer.parseInt(prova.getInfo()[5]), Integer.parseInt(prova.getInfo()[6])));
        }
        if (prova.getClass().getSimpleName().equals("ProvaTesiDoctoral")){
            // Afegeix la nova prova a la llista
            proves.add(new ProvaTesiDoctoral(prova.getInfo()[0], prova.getInfo()[2], Integer.parseInt(prova.getInfo()[3])));

        }
        provaDAO.escribir(proves);
    }

    /**
     * Método para saber si hay alguna prueba
     * @return boolean para saber si hay pruebas
     */
    public boolean absenceOfTrials() {
        //Si hi ha 0 proves retornem true
        return proves.size() < 1;
    }

    public boolean elsNomsCoincideixen(int opcio, String nom) {
        return nom.equals(proves.get(opcio).getNom());
    }

    /**
     * Método que sirve para eliminar una prueba
     * @param i int para saber cual eliminar
     */
    public void eliminaProva(int i) {
        provaDAO.elimina(proves, i);
        //proves.remove(i);
    }

    /**
     * Método para saber si ese nombre esta repetido
     * @param nom String con el nombre de la prueba
     * @return boolean para saber si esta o no repetido
     */
    public boolean nomRepetit(String nom) {
        //Busquem el nom entre la resta de noms de proves per tal de comprovar que no estigui repetit
        for (Prova p : this.proves) {
            if (nom.equals(p.getNom())) {
                return true;
            }
        }
        return nom.equals("");
    }

    /**
     * Método para escribir en la DAO todas las pruebas
     */
    public void escriure() {
        provaDAO.escribir(proves);
    }

    /**
     * Método para leer los ficheros de pruebas
     */
    public void llegir() {
        proves = provaDAO.leer();
    }

    /**
     * Metodo que hace una nueva instancia del CsvDAO o JsonDAO en funcion de lo que escoja el usuario
     *
     * @param isCSV boolean para asignar el valor, true si es CSV false si es Json
     */
    public void setPersistanceType(boolean isCSV) {
        if (isCSV) {
            provaDAO = new ProvaCsvDAO();
        } else {
            provaDAO = new ProvaJsonDAO();
        }
    }
}
