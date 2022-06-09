package business;

import persistance.JugadorDAO;

import java.util.ArrayList;

public class JugadorsManager {

    private ArrayList<Jugador> jugadors;
    private JugadorDAO jugadorDAO;

    public JugadorsManager(){
        this.jugadorDAO = new JugadorDAO();
        this.jugadors = new ArrayList<Jugador>();
    }

    public void creaJugador(Jugador jugador) {
        jugadors.add(jugador);
    }

    public void eliminarJugador(int index) {
        jugadors.remove(index);
    }

    public void netejarLlista() {
        jugadors.clear();
    }


    public ArrayList<Jugador> getJugadors() {
        return jugadors;
    }
    public Jugador getJugador(int any, String nom) {
        for(Jugador j : jugadors)
            if (j.getAny() == any && j.getNom() == nom)
                return j;
        return null;
    }

    public void premiar(int any, String nom, int punts) {
        getJugador(any, nom).premiar(punts);
    }
    public void penalitzar(int any, String nom, int punts){
        getJugador(any, nom).penalitzar(punts);
    }



    public void Escriure() {
        String[] info = new String[jugadors.size()];
        int i = 0;

        for (Jugador j : jugadors) {
            info[i] = j.getInfoCSV();
            i++;
        }

        jugadorDAO.escriure(info);
    }

    public void llegir(boolean isCSV) {
        String[] lines;
        if (true){
            lines = jugadorDAO.llegir();
            // Itera per cada linia de l'arxiu
            for (int i = 0; i < lines.length; i++) {
                // Separa la linia per cada coma
                String[] line = lines[i].split(",");

                // Converteix cada element en el tipus de dada corresponent
                String tipus = line[0];
                int any = Integer.parseInt(line[1]);
                String nom = line[2];
                int PI = Integer.parseInt(line[3]);

                if (tipus.equals("Enginyer"))
                    creaJugador(new Enginyer(any, nom, PI));
                if (tipus.equals("Master"))
                    creaJugador(new Master(any, nom, PI));
                if (tipus.equals("Doctor"))
                    creaJugador(new Doctor(any, nom, PI));
                //else if (tipus.equals("Master"))
            }
        } else if (false){
            lines = jugadorDAO.llegirJSON();
        }
    }

}
