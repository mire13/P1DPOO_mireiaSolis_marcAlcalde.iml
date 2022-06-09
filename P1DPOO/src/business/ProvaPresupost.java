package business;

import presentation.Vista;

import java.util.ArrayList;

public class ProvaPresupost extends Prova {
    private String entitat;
    private int presupost;

    public static final String TRIAL_TYPE = "Prova Presupost";


    public ProvaPresupost(String trialName, String entitat, int presupost){
        this.nom = trialName;
        this.entitat = entitat;
        this.presupost = presupost;
    }

    @Override
    public String[] getInfo() {
        String[] info = new String[4];

        info[0] = nom;
        info[1] = TRIAL_TYPE;
        info[2] = entitat;
        info[3] = Integer.toString(presupost);

        return info;
    }

    @Override
    public String getInfoCSV() {
        return TRIAL_TYPE + "," + getNom() + "," + entitat + "," + presupost;
    }
    public String getType() {
        return TRIAL_TYPE;
    }

    @Override
    public void executarProva(int index, int totalJugadors, int any, ArrayList<Jugador> jugadors, Vista vista) {

        // Calcula els punts totals
        int totalPI = 0;
        for(Jugador jugador : jugadors)
            if (jugador.getPI() > 0 && jugador.getAny()==any)
                totalPI += jugador.getPI();

        float value = (float) (Math.log(presupost) / Math.log(2));

        if (totalPI > value) {
            vista.showPresupostSuperat();
        } else {
            vista.showPresupostFallat();
        }

        for(Jugador jugador : jugadors) {
            if (jugador.getPI() > 0) {
                if (totalPI > value) {
                    jugador.premiar((int) Math.ceil(jugador.getPI() / 2));
                } else {
                    jugador.penalitzar(2);
                }

                vista.showPresupostPuntsGuanyats(jugador.getNom(), jugador.getPI());
            }
        }
    }
}
