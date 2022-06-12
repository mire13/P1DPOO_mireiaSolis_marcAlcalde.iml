package business;

import persistance.ProvaDAO;
import java.util.LinkedList;

/**
 * Método que sirve para controlar y gestionar las pruebas
 */
public class ProvesManager {

    private LinkedList<Prova> proves;
    private ProvaDAO provaDAO;
    private boolean isCSV;

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
     * @param isCSV boolean para saber si es CSV o JSON
     */
    public void creaProva(Prova prova, boolean isCSV) {
        this.isCSV = isCSV;
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
        if (this.isCSV){
            escriure();
        } else {
            provaDAO.escriureJSON(proves);
        }
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
        if (isCSV()) {
            proves.remove(i);
            escriure();
        } else {
            provaDAO.eliminaProvaJSON(i);
        }
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
     * Método para escribir en CSV en el fichero de pruebas
     */
    public void escriure() {
        String[] info = new String[proves.size()];

        for (int i = 0; i < proves.size(); i++)
            info[i] = proves.get(i).getInfoCSV();

        provaDAO.escriure(info);
    }

    /**
     * Método para leer los ficheros de pruebas
     * @param isCSV boolean para saber si leer CSV o JSON
     */
    public void llegir(boolean isCSV) {
        provaDAO = new ProvaDAO(isCSV);
        this.isCSV = isCSV;
        String[] lines;
        if (this.isCSV) {
             lines = provaDAO.llegirCSV();
            // Itera per cada linia de l'arxiu
            for (int i = 0; i < lines.length; i++) {
                // Separa la linia per cada coma
                String[] line = lines[i].split(",");

                // Converteix cada element en el tipus de dada corresponent
                String type = line[0];

                if (type.equals("Prova Publicacio")) {
                    String name = line[1];
                    String journal = line[2];
                    String quartile = line[3];
                    int acceptance = Integer.parseInt(line[4]);
                    int revision = Integer.parseInt(line[5]);
                    int rejection = Integer.parseInt(line[6]);

                    // Afegeix la nova prova a la llista
                    creaProva(new ProvaPublicacio(name, journal, quartile, acceptance, revision, rejection), this.isCSV);

                } else if (type.equals("Prova Estudi Master")) {
                    String name = line[1];
                    String masterNom = line[2];
                    int credits = Integer.parseInt(line[3]);
                    int probabilitat = Integer.parseInt(line[4]);

                    // Afegeix la nova prova a la llista
                    creaProva(new ProvaEstudiMaster(name, masterNom, credits, probabilitat), this.isCSV);

                } else if (type.equals("Prova Tesi Doctoral")) {
                    String name = line[1];
                    String camp = line[2];
                    int dificultat = Integer.parseInt(line[3]);

                    // Afegeix la nova prova a la llista
                    creaProva(new ProvaTesiDoctoral(name, camp, dificultat), this.isCSV);

                } else if (type.equals("Prova Presupost")) {
                    String name = line[1];
                    String entitat = line[2];
                    int presupost = Integer.parseInt(line[3]);

                    // Afegeix la nova prova a la llista
                    creaProva(new ProvaPresupost(name, entitat, presupost), this.isCSV);
                }
            }
        } else {
            proves = provaDAO.llegirJSON();
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
    }
}
