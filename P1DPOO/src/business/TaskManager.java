package business;

import presentation.Vista;

import java.util.ArrayList;
import java.util.LinkedList;

public class TaskManager {

    private final ProvesManager provesManager;
    private final EdicionsManager edicionsManager;
    private final JugadorsManager jugadorsManager;
    private boolean isCSV;

    public TaskManager() {
        provesManager = new ProvesManager();
        edicionsManager = new EdicionsManager();
        jugadorsManager = new JugadorsManager();
    }

    public void leerCSV(){
        provesManager.llegir(true);
        edicionsManager.llegir(provesManager.getProves(), true);
        jugadorsManager.llegir(true);
        isCSV = true;
    }

    public void leerJSON() {
        provesManager.llegir(false);
        edicionsManager.llegir(provesManager.getProves(), false);
        jugadorsManager.llegir(false);
        isCSV = false;
    }

    public LinkedList<Prova> getProves() {
        return provesManager.getProves();
    }

    public void creaProva(Prova prova) {
        provesManager.creaProva(prova, isCSV);
    }

    public boolean absenceOfTrials() {
        return provesManager.absenceOfTrials();
    }

    public void eliminaProva(int i) {
        provesManager.eliminaProva(i);
        for(Edicio e : edicionsManager.getEdicions()) {
            e.updateProves(i);
        }
        edicionsManager.escriure();
    }

    public boolean provaEnUs(String nom) {
        return edicionsManager.provaEnUs(nom, provesManager.getProves());
    }

    public boolean elsNomsCoincideixen(int opcio, String nom) {
        return provesManager.elsNomsCoincideixen(opcio, nom);
    }

    public boolean nomRepetit(String nom) {
        return provesManager.nomRepetit(nom);
    }

    public LinkedList<Edicio> getEdicions() {
        return edicionsManager.getEdicions();
    }

    /*public void addProvaToEdicio(int seleccio) {
        edicionsManager.AddProvaToEdicio(GetProves().get(seleccio));
    }*/

    public void crearEdicio(Edicio edicio) {
        edicionsManager.creaEdicio(edicio, isCSV);
    }

    public void duplicarEdicio(int opcio, int any, int numJugadors) {
        edicionsManager.duplicarEdicio(opcio, any, numJugadors);
    }

    public boolean confirmacioAny(int anyAEliminar, int opcio) {
        return edicionsManager.confirmacioAny(anyAEliminar, opcio);
    }

    public void eliminaEdicio(int i) {
        edicionsManager.eliminaEdicio(i);
    }

    public boolean anyRepetit(int any) {
        return edicionsManager.anyRepetit(any);
    }

    public int edicioEnCurs(int anyActual) {
        int i=0;
        for(Edicio e : edicionsManager.getEdicions()) {
            if (e.getAny() == anyActual) return i;
            i++;
        }
        return -1;
    }

    // JUGADORS
    public void creaJugador(Jugador jugador) {
        jugadorsManager.creaJugador(jugador);
    }

    public boolean quedenJugadorsAmbPunts(int anyActual) {
        int jugadorsVius = 0;
        for(Jugador j : jugadorsManager.getJugadors())
            if (j.getAny()==anyActual && j.getPI()>0)
                jugadorsVius++;
        return jugadorsVius > 0;

    }

    public void escriure() {
        provesManager.escriure();
        edicionsManager.escriure();
        jugadorsManager.Escriure();
    }

    public void cambiarTipusJugadors(int anyActual, Vista vista) {
        for(int i = 0; i<jugadorsManager.getJugadors().size(); i++) {
            Jugador j = jugadorsManager.getJugadors().get(i);
            if (j.getAny() == anyActual && j.getPI() >= 10 && !(j instanceof Doctor)) {
                if (j instanceof Enginyer) {
                    vista.showEnginyerCanviaMaster(j.getNom());
                    Master m = new Master(anyActual, j.getNom(), 5);
                    creaJugador(m);
                }
                if (j instanceof Master) {
                    vista.showMasterCanviaDoctor(j.getNom());
                    Doctor m = new Doctor(anyActual, j.getNom(), 5);
                    creaJugador(m);
                }
                jugadorsManager.eliminarJugador(i);
                i--;
            }
        }
    }

    public ArrayList<Jugador> getJugadors() {
        return jugadorsManager.getJugadors();
    }

    public void reiniciarEdicio(int anyActual) {
        for(Edicio e : edicionsManager.getEdicions())
            if (e.getAny() == anyActual) e.setCurrentState(0);
        jugadorsManager.netejarLlista();
    }
}