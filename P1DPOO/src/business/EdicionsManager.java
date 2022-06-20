package business;

import persistance.EdicioCsvDAO;
import persistance.EdicioDAO;
import persistance.EdicioJsonDAO;

import java.util.LinkedList;

/**
 * Método que sirve para controlar y gestionar las ediciones
 */
public class EdicionsManager {

    private LinkedList<Edicio> edicions;
    private EdicioDAO edicioDAO;

    /**
     * Constructor para inicializar la lista de ediciones
     */
    public EdicionsManager() {
        this.edicions = new LinkedList<>();
    }

    /**
     * Método que sirve para recoger las ediciones
     * @return LinkedList de ediciones
     */
    public LinkedList<Edicio> getEdicions() {
        LinkedList<Edicio> list = edicions;
        return list;
    }

    /**
     * Método que sirve para crear una edición y añadirla a la lista
     * @param edicio que contiene la informacion de edicion
     */
    public void creaEdicio(Edicio edicio) {
        edicions.add(edicio);
        escriure();
    }

    public void escriure() {
        edicioDAO.escribir(edicions);
    }

    /**
     * Método que sirve para duplicar una edicion
     * @param opcio int per indicar quina es vol duplicar
     * @param any int amb l'any de la edicio
     * @param numJugadors int amb el nombre de jugadors
     */
    public void duplicarEdicio(int opcio, int any, int numJugadors) {
        Edicio novaEdicio = new Edicio(any, numJugadors, edicions.get(opcio - 1).getTotalProves(), edicions.get(opcio - 1).getProves());
        edicions.add(novaEdicio);
        escriure();
    }

    /**
     * Método que sirve para eliminar una edición
     * @param i int para saber cual eliminar
     */
    public void eliminaEdicio(int i) {
        edicions.remove(i);
        escriure();
    }

    /**
     * Método qeu sirve para confirmar el año de la edicion a eliminar
     * @param anyAEliminar int con el año a eliminar
     * @param opcio int con la opcion de la edicion a eliminar
     * @return boolean que nos dice si el año de confirmacion es correcto
     */
    public boolean confirmacioAny(int anyAEliminar, int opcio) {
        return anyAEliminar == edicions.get(opcio).getAny();
    }

    /**
     * Método que sirve para saber si la prueba está en curso
     * @param nom nombre de la edicion
     * @param proves LinkedList con las pruebas
     * @return boolean
     */
    public boolean provaEnUs(String nom, LinkedList<Prova> proves) {
        for (Edicio e : edicions) {
            for (int i = 0; i < e.getProves().length; i++) {
                if (proves.get(e.getProves()[i]).getNom().equals(nom))
                    return true;
            }
        }
        return false;
    }

    /**
     * Método que sirve para saber si el año está repetido
     * @param any int con el año de la edicion
     * @return boolean para saber si el año esta repetido
     */
    public boolean anyRepetit(int any) {
        for (Edicio e : edicions) {
            if (any == e.getAny())
                return true;
        }
        return false;
    }


    /**
     * Método para leer los ficheros de ediciones i crear las ediciones
     */
    public void llegir() {
        LinkedList<Edicio> edicionsDAO = edicioDAO.leer();
        if (edicionsDAO == null) {
            // TODO deberia mostrar un mensaje de error pero creo que no pueden haber souts en el manager
        } else {
            for (int i = 0; i < edicionsDAO.size(); i++) {
                //e[i].setCurrentState(state);
                creaEdicio(edicionsDAO.get(i));
            }
        }
    }

    /**
     * Metodo que hace una nueva instancia del CsvDAO o JsonDAO en funcion de lo que escoja el usuario
     *
     * @param isCSV boolean para asignar el valor, true si es CSV false si es Json
     */
    public void setPersistanceType(boolean isCSV) {
        if (isCSV) {
            edicioDAO = new EdicioCsvDAO();
        } else {
            edicioDAO = new EdicioJsonDAO();
        }
    }
}