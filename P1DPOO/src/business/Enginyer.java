package business;

public class Enginyer extends Jugador {

    public Enginyer(int any, String nom, int PI) {
        super(any, nom, PI);
    }

    public String getType() { return "Enginyer"; }

    @Override
    public String getTitle() {
        return nom;
    }

    public String getInfoCSV() {
        return "Enginyer" + "," + any + "," + nom + "," + PI;
    }

    public void premiar(int quantitat){
        PI = PI + quantitat;
    }

    public void penalitzar(int quantitat){
        PI = PI - quantitat;
    }
}
