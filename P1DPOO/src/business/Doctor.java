package business;

public class Doctor extends Jugador {
    public Doctor(int any, String nom, int PI) {
        super(any, nom, PI);
    }

    public String getType() { return "Doctor"; }

    public String getTitle() {
        return nom + ", PhD.";
    }

    public String getInfoCSV() {
        return "Doctor" + "," + any + "," + nom + "," + PI;
    }

    public void premiar(int quantitat){
        PI = PI + quantitat * 2;
    }

    public void penalitzar(int quantitat){
        PI = PI - (int) Math.floor(quantitat / 2);
    }
}
