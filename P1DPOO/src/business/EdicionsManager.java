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

    private boolean isCSV;

    /**
     * Constructor para inicializar la lista de ediciones
     */
    public EdicionsManager(boolean isCSV) {
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
        if (isCSV){
            escriureCSV();
        } else {
            escriureJSON();
        }
    }

    /*
    public void addProvaToEdicio(Prova prova) {
        edicions.get(edicions.size() - 1).GetProves().add(prova);
    }*/

    /**
     * Método que sirve para duplicar una edicion
     * @param opcio int per indicar quina es vol duplicar
     * @param any int amb l'any de la edicio
     * @param numJugadors int amb el nombre de jugadors
     */
    public void duplicarEdicio(int opcio, int any, int numJugadors) {
        Edicio novaEdicio = new Edicio(any, numJugadors, edicions.get(opcio - 1).getTotalProves(), edicions.get(opcio - 1).getProves());
        edicions.add(novaEdicio);
        if (isCSV()) {
            escriureCSV();
        } else {
            escriureJSON();
        }
    }

    /**
     * Método que sirve para eliminar una edición
     * @param i int para saber cual eliminar
     */
    public void eliminaEdicio(int i) {
        edicions.remove(i);
        if (isCSV()) {
            escriureCSV();
        } else {
            escriureJSON();
        }
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
     * Método que sirve para actualizar ediciones
     * @param IDEdicioActual int con el ID de la edicion
     * @param lost boolean
     * @param edicioEnCurs informacion de la edicion en curso
     */
    public void actualitzaEdicions(int IDEdicioActual, boolean lost, Edicio edicioEnCurs) {
        edicions.remove(IDEdicioActual);
        if (!lost)
            edicions.add(IDEdicioActual, edicioEnCurs);
        escriureCSV();
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
     * Método para escribir en JSON en el fichero de ediciones
     */
    public void escriureJSON(){
        edicioDAO.escribir(edicions);
    }

    /**
     * Método para escribir en CVS en el fichero de ediciones
     */
    public void escriureCSV() {
        String[] info = new String[edicions.size()];
        int i = 0;

        for (Edicio e : edicions) {
            String aux = "";

            aux += e.getAny() + ",";
            aux += e.getTotalJugadors() + ",";
            aux += e.getCurrentState() + ",";
            aux += e.getTotalProves();

            for (int j = 0; j < e.getProves().length; j++) {
                aux += ",";
                aux += e.getProves()[j];
            }
            info[i] = aux;
            i++;
        }

        edicioDAO.escriure(info);
    }

    /**
     * Método para leer los ficheros de ediciones
     * @param provesList LinkedList de tipo Prueba
     * @param isCSV boolean para saber si leer CSV o JSON
     */
    public void llegir(LinkedList<Prova> provesList, boolean isCSV) {
        setCSV(isCSV);
        String[] lines;
        edicioDAO = new EdicioJsonDAO(isCSV());
        if (isCSV()){
            lines = edicioDAO.llegirCSV();
            // Itera per cada linia de l'arxiu
            for (int i = 0; i < lines.length; i++) {
                // Separa la linia per cada coma
                String[] line = lines[i].split(",");

                // Converteix cada element en el tipus de dada corresponent
                int any = Integer.parseInt(line[0]);
                int jugNum = Integer.parseInt(line[1]);
                int state = Integer.parseInt(line[2]);
                int provesNum = Integer.parseInt(line[3]);

                int[] proves = new int[provesNum];
                for(int p=0; p<provesNum; p++) {
                    proves[p] = Integer.parseInt(line[4+p]);
                }

                // Afegeix la nova prova a la llista
                Edicio e = new Edicio(any, jugNum, provesNum, proves);
                e.setCurrentState(state);
                creaEdicio(e);
            }
        } else if (!isCSV()){
            LinkedList<Edicio> e = edicioDAO.leer();
            if (e == null) {
                // TODO deberia mostrar un mensaje de error pero creo que no pueden haber souts en el manager
            } else {
                for (int i = 0; i < e.size(); i++) {
                    //e[i].setCurrentState(state);
                    creaEdicio(e.get(i));
                }
            }
        }
    }

    /**
     * Getter para saber si ha escogido CSV o no como opcion de persistencia
     *
     * @return true si es CSV o false si no
     */
    public boolean isCSV() {
        return isCSV;
    }

    /**
     * Setter para asignar true si es CSV o false si es Json
     *
     * @param CSV boolean para asignar el valor
     */
    public void setCSV(boolean CSV) {
        isCSV = CSV;
        if (isCSV) {
            edicioDAO = new EdicioCsvDAO();
        } else {
            edicioDAO = new EdicioJsonDAO();
        }
    }
}