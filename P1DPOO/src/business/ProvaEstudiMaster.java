package business;

import presentation.Vista;

import java.util.ArrayList;
import java.util.Random;

public class ProvaEstudiMaster extends Prova {

    private String nomMaster;
    private int credits;
    private int probabilitat;

    public static final String TRIAL_TYPE = "Prova Estudi Master";

    public ProvaEstudiMaster(String trialName, String master, int credits, int probabilitat){
        this.nom = trialName;
        this.nomMaster = master;
        this.credits = credits;
        this.probabilitat = probabilitat;
    }

    @Override
    public String[] getInfo() {
        String[] info = new String[5];

        info[0] = nom;
        info[1] = TRIAL_TYPE;
        info[2] = nomMaster;
        info[3] = Integer.toString(credits);
        info[4] = Integer.toString(probabilitat);

        return info;
    }

    @Override
    public String getInfoCSV() {
        return TRIAL_TYPE + "," + getNom() + "," + nomMaster + "," + credits + "," + probabilitat;
    }

    public String getType() {
        return TRIAL_TYPE;
    }

    @Override
    public void executarProva(int index, int totalJugadors, int any, ArrayList<Jugador> jugadors, Vista vista) {
        for (Jugador jugador : jugadors) {
            if (jugador.getAny() == any && jugador.getPI() > 0) {
                // Calcul de com aprobar cada credit
                Random rand = new Random();
                int creditsSuperats = 0;
                for (int i = 0; i < credits; i++) {
                    if (rand.nextInt(100) <= probabilitat)
                        creditsSuperats++;
                }

                vista.showMasterStudy(jugador.getTitle(), creditsSuperats, credits);

                float ratio = (float) creditsSuperats / (float) credits;
                if (ratio < .5) {
                    jugador.penalitzar(3);
                    vista.showMasterFallat(jugador.getPI());
                } else {
                    if (jugador.getType().equals("Enginyer"))
                        jugador.premiar(10);
                    else
                        jugador.premiar(3);
                    vista.showMasterSuperat(jugador.getPI());
                }
            }
        }
    }
}
