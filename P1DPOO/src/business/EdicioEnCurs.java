package business;

import java.util.ArrayList;

/**
 * Clase para las ediciones en curso que extiende de edicion
 */
public class EdicioEnCurs extends Edicio{
    public EdicioEnCurs(int any, int totalJugadors, int totalProves, int[] proves) {
        super(any, totalJugadors, totalProves, proves);
    }
/*
    private ArrayList<Jugador> jugadors;

    public EdicioEnCurs(int any, int totalJugadors, int totalProves, int[] proves) {
        super(any, totalJugadors, totalProves, proves);
        this.jugadors = new ArrayList<Jugador>();
    }
    public void setNousJugadors(ArrayList<Jugador> nousJugadors) {
        this.jugadors = nousJugadors;
    }
    public boolean edicioSenseComencar(){
        return this.GetCurrentState() == 0;
    }
    public boolean demanaNom(int i){
        return i < jugadors.size();
    }
    public boolean seguentProva(int j){
        return j < GetProves().length;
    }
    public String GetNomProvaEnCurs(int j, ArrayList<Prova> proves){
        return proves.get(GetProves()[j]).GetNom();
    }
    public boolean provaTipusPublicacio(int j, ArrayList<Prova> proves){
        return proves.get(GetProves()[j]) instanceof ProvaPublicacio;
    }
    public void actualitzaTotalJugadors(){
        SetTotalJugadors(jugadors.size());
    }
    public boolean seguentJugador(int i){
        return i < GetTotalJugadors();
    }
    public String GetNomJugadorEnCurs(int i){
        return GetJugadors().get(i).GetNom();
    }
    public boolean accepted(int randomNum, int j, ArrayList<Prova> proves){
        return proves.get(GetProves()[j]).accepted(randomNum);
    }
    public boolean revised(int randomNum, int j, ArrayList<Prova> proves){
        return proves.get(GetProves()[j]).revised(randomNum);
    }
    public void actualitzaCurrentState(int currentState){
        this.SetCurrentState(currentState);
    }

    public void setJugadors(ArrayList<Jugador> jugadors) {
        this.jugadors = jugadors;
    }

    public ArrayList<Jugador> GetJugadors() {
        return jugadors;
    }
    public int premiar(int j, int i, ArrayList<Prova> proves){
        int quantitatPI = 0;
        ProvaPublicacio p = (ProvaPublicacio) proves.get(GetProves()[j]);

        switch (p.GetQuartile()) {
            case "Q1" -> quantitatPI = 4;
            case "Q2" -> quantitatPI = 3;
            case "Q3" -> quantitatPI = 2;
            case "Q4" -> quantitatPI = 1;
        }
        return GetJugadors().get(i).remiar(quantitatPI);
    }
    public int penalitzar(int j, int i, ArrayList<Prova> proves){
        int quantitatPI = 0, novaPuntuacio;
        ProvaPublicacio p = (ProvaPublicacio) proves.get(GetProves()[j]);

        switch (p.GetQuartile()) {
            case "Q1" -> quantitatPI = 5;
            case "Q2" -> quantitatPI = 4;
            case "Q3" -> quantitatPI = 3;
            case "Q4" -> quantitatPI = 2;
        }
        novaPuntuacio = GetJugadors().get(i).penalitzar(quantitatPI);

        if(novaPuntuacio <= 0){
            jugadors.remove(i);
        }
        return novaPuntuacio;
    }
    public boolean quedenJugadors(){
        return jugadors.size() > 0;
    }
*/
}