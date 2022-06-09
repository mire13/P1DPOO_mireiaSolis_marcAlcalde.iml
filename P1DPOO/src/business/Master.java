package business;

public class Master extends Jugador {
    public Master(int any, String nom, int PI) {
        super(any, nom, PI);
    }

    public String getType() { return "Master"; };

    public String getTitle() {
        return "Master " + nom;
    }

    public String getInfoCSV() {
        return "Master" + "," + any + "," + nom + "," + PI;
    }

    public void premiar(int quantitat){
        PI = PI + quantitat;
    }

    public void penalitzar(int quantitat){
        PI = PI - (int) Math.floor(quantitat / 2);
    }
}
