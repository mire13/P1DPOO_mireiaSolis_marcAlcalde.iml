package business;

import presentation.Vista;

import java.util.ArrayList;

public abstract class Prova {
    protected String nom;

    public String getNom() {
        return nom;
    }

    public abstract String getType();

    public abstract String[] getInfo();

    public abstract String getInfoCSV();

    public abstract void executarProva(int index, int totalJugadors, int any, ArrayList<Jugador> jugadors, Vista vista);
}



