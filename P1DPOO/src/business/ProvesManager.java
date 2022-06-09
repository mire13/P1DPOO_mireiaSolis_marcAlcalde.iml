package business;

import persistance.ProvaDAO;

import java.util.LinkedList;

public class ProvesManager {

    private LinkedList<Prova> proves;
    private ProvaDAO provaDAO;

    public ProvesManager() {
        this.provaDAO = new ProvaDAO(false); // TODO remove false put isCSV
        this.proves = new LinkedList<Prova>();
    }

    public LinkedList<Prova> getProves() {
        return provaDAO.llegirJSON();
    }

    public void creaProva(Prova prova, boolean isCSV) {
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
        if (isCSV == true){
            escriure();
        } else {
            provaDAO.escriureJSON(proves);
        }
    }

    public boolean absenceOfTrials() {
        //Si hi ha 0 proves retornem true
        return proves.size() < 1;
    }

    public boolean elsNomsCoincideixen(int opcio, String nom) {
        return nom.equals(proves.get(opcio).getNom());
    }

    public void eliminaProva(int i) {
        provaDAO.eliminaProvaJSON(i);
    }

    public boolean nomRepetit(String nom) {
        //Busquem el nom entre la resta de noms de proves per tal de comprovar que no estigui repetit
        for (Prova p : this.proves) {
            if (nom.equals(p.getNom())) {
                return true;
            }
        }
        return nom.equals("");
    }

    public void escriure() {
        String[] info = new String[proves.size()];

        for (int i = 0; i < proves.size(); i++)
            info[i] = proves.get(i).getInfoCSV();

        provaDAO.escriure(info);
    }

    public void llegir(boolean isCSV) {
        String[] lines;
        if (isCSV){
             lines = provaDAO.llegir();
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
                    creaProva(new ProvaPublicacio(name, journal, quartile, acceptance, revision, rejection), isCSV);

                } else if (type.equals("Prova Estudi Master")) {
                    String name = line[1];
                    String masterNom = line[2];
                    int credits = Integer.parseInt(line[3]);
                    int probabilitat = Integer.parseInt(line[4]);

                    // Afegeix la nova prova a la llista
                    creaProva(new ProvaEstudiMaster(name, masterNom, credits, probabilitat), isCSV);

                } else if (type.equals("Prova Tesi Doctoral")) {
                    String name = line[1];
                    String camp = line[2];
                    int dificultat = Integer.parseInt(line[3]);

                    // Afegeix la nova prova a la llista
                    creaProva(new ProvaTesiDoctoral(name, camp, dificultat), isCSV);

                } else if (type.equals("Prova Presupost")) {
                    String name = line[1];
                    String entitat = line[2];
                    int presupost = Integer.parseInt(line[3]);

                    // Afegeix la nova prova a la llista
                    creaProva(new ProvaPresupost(name, entitat, presupost), isCSV);
                }
            }
        } else {
            proves = provaDAO.llegirJSON();
        }
    }
}
