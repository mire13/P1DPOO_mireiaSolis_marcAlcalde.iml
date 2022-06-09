package business;

public abstract class Jugador {
    public int any;
    public String nom;
    public int PI;

    public Jugador(int any, String nom, int PI){
        this.any = any;
        this.nom = nom;
        this.PI = PI;
    }

    public int getAny() { return any; };

    public String getNom() { return nom; };

    public abstract String getTitle();

    public int getPI() {
        return PI;
    }

    public abstract void premiar(int quantitat);
    public abstract void penalitzar(int quantitat);

    public abstract String getInfoCSV();

    public abstract String getType();
}
