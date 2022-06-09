package business;

import persistance.EdicioDAO;

import java.util.ArrayList;
import java.util.LinkedList;

public class EdicionsManager {

    private LinkedList<Edicio> edicions;
    private EdicioDAO edicioDAO;

    public EdicionsManager() {
        this.edicioDAO = new EdicioDAO();
        this.edicions = new LinkedList<Edicio>();
    }

    public LinkedList<Edicio> getEdicions() {
        return edicions;
    }

    public void creaEdicio(Edicio edicio, boolean isCSV) {
        edicions.add(edicio);
        if (isCSV == true){
            escriure();
        } else {
            escriureJSON();
        }
    }

    /*
    public void addProvaToEdicio(Prova prova) {
        edicions.get(edicions.size() - 1).GetProves().add(prova);
    }*/

    public void duplicarEdicio(int opcio, int any, int numJugadors) {
        Edicio novaEdicio = new Edicio(any, numJugadors, edicions.get(opcio - 1).getTotalProves(), edicions.get(opcio - 1).getProves());
        edicions.add(novaEdicio);
        escriure();
    }

    public void eliminaEdicio(int i) {
        edicions.remove(i);
        escriure();
    }

    public boolean confirmacioAny(int anyAEliminar, int opcio) {
        return anyAEliminar == edicions.get(opcio).getAny();
    }

    public void actualitzaEdicions(int IDEdicioActual, boolean lost, Edicio edicioEnCurs) {
        edicions.remove(IDEdicioActual);
        if (!lost)
            edicions.add(IDEdicioActual, edicioEnCurs);
        escriure();
    }

    public boolean provaEnUs(String nom, LinkedList<Prova> proves) {
        for (Edicio e : edicions) {
            for (int i = 0; i < e.getProves().length; i++) {
                if (proves.get(e.getProves()[i]).getNom().equals(nom))
                    return true;
            }
        }
        return false;
    }

    public boolean anyRepetit(int any) {
        for (Edicio e : edicions) {
            if (any == e.getAny())
                return true;
        }
        return false;
    }

    public void escriureJSON(){
        edicioDAO.escriureJSON(edicions);
    }

    public void escriure() {
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

    public void llegir(LinkedList<Prova> provesList, boolean isCSV) {
        String[] lines;
        if (isCSV == true){
            lines = edicioDAO.llegir();
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
                creaEdicio(e, isCSV);
            }
        } else if (isCSV == false){
            Edicio[] e = edicioDAO.llegirJSON();
            if (e == null) {

            } else {
                for (int i = 0; i < e.length; i++) {
                    //e[i].setCurrentState(state);
                    creaEdicio(e[i], isCSV);
                }
            }
        }

    }
}
