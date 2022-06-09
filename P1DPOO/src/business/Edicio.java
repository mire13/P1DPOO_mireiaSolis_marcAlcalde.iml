package business;

import java.util.ArrayList;

public class Edicio {
    private int any;
    private int totalJugadors;
    private int totalProves;
    private int[] proves;
    private int currentState;

    public Edicio(int any, int totalJugadors, int totalProves, int[] proves){
        this.any = any;
        this.totalJugadors = totalJugadors;
        this.totalProves = totalProves;
        this.proves = proves;
        this.currentState = 0;
    }

    public int getAny() {
        return any;
    }
    public int getTotalJugadors() {
        return totalJugadors;
    }
    public int getTotalProves() {
        return totalProves;
    }
    public int[] getProves() { return proves; }

    public void setTotalJugadors(int totalJugadors) {
        this.totalJugadors = totalJugadors;
    }
    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }
    public int getCurrentState() {
        return currentState;
    }
    public void updateProves(int index) {
        for(int i=0; i<proves.length; i++) {
            if (proves[i]>index)
                proves[i] = proves[i] -1;
        }
    }

    public String getInfoCSV(ArrayList<Prova> proves) {
        String info = "";

        info = any + "," + totalJugadors + "," + totalProves;
        return info;
    }

}
