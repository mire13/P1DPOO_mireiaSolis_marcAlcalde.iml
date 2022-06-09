package business;

import presentation.Vista;

import java.util.ArrayList;

public class ProvaTesiDoctoral extends Prova {
    private String campEstudi;
    private int dificultat;

    public static final String TRIAL_TYPE = "Prova Tesi Doctoral";

    public ProvaTesiDoctoral(String trialName, String campEstudi, int dificultat){
        this.nom = trialName;
        this.campEstudi = campEstudi;
        this.dificultat = dificultat;
    }

    @Override
    public String[] getInfo() {
        String[] info = new String[4];

        info[0] = nom;
        info[1] = TRIAL_TYPE;
        info[2] = campEstudi;
        info[3] = Integer.toString(dificultat);

        return info;
    }

    @Override
    public String getInfoCSV() {
        return TRIAL_TYPE + "," + getNom() + "," + campEstudi + "," + dificultat;
    }

    public String getType() {
        return TRIAL_TYPE;
    }
    @Override
    public void executarProva(int index, int totalJugadors, int any, ArrayList<Jugador> jugadors, Vista vista) {
        for (Jugador jugador : jugadors) {
            if (jugador.getAny() == any && jugador.getPI() > 0) {

                // Calcula el quocient de la prova de la tesi doctoral
                float value = 0;
                for (int i = 1; i <= dificultat; i++)
                    value += 2 * i - 1;
                value = (float) Math.sqrt(value);

                if (jugador.getPI() > value) {
                    if (jugador.getType().equals("Master"))
                        jugador.premiar(10);
                    else
                        jugador.premiar(5);

                    vista.showDoctorSuperat(jugador.getNom(), jugador.getPI());

                } else {
                    jugador.penalitzar(5);
                    vista.showDoctorFallat(jugador.getNom(), jugador.getPI());
                }
            }
        }
    }
}
