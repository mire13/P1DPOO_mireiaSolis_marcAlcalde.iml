package business;

import presentation.Vista;

import java.util.ArrayList;
import java.util.Random;

public class ProvaPublicacio extends Prova {
    private String journalName;
    private String quartile;
    private int acceptanceProbability;
    private int revisionProbability;
    private int rejectionProbability;

    public static final String TRIAL_TYPE = "Prova Publicacio";

    public ProvaPublicacio(String trialName, String journalName, String quartile, int acceptanceProbability, int revisionProbability, int rejectionProbability){
        this.nom = trialName;
        this.journalName = journalName;
        this.quartile = quartile;
        this.acceptanceProbability = acceptanceProbability;
        this.revisionProbability = revisionProbability;
        this.rejectionProbability = rejectionProbability;
    }

    public String getInfoCSV() {
        return TRIAL_TYPE + "," + getNom() + "," + journalName + "," + quartile + "," + acceptanceProbability + "," + revisionProbability + ","
                + rejectionProbability;
    }

    @Override
    public String getType() {
        return TRIAL_TYPE;
    }

    public String[] getInfo(){
        String[] info = new String[7];

        info[0] = nom;
        info[1] = TRIAL_TYPE;
        info[2] = journalName;
        info[3] = quartile;
        info[4] = Integer.toString(acceptanceProbability);
        info[5] = Integer.toString(revisionProbability);
        info[6] = Integer.toString(rejectionProbability);

        return info;
    }

    private int getPuntsPenalitzar() {
        switch (quartile) {
            case "Q1":
                return 5;
            case "Q2":
                return 4;
            case "Q3":
                return 3;
            case "Q4":
                return 2;
        }
        return 0;
    }

    private int getPuntsPremiar() {
        switch (quartile) {
            case "Q1":
                return 4;
            case "Q2":
                return 3;
            case "Q3":
                return 2;
            case "Q4":
                return 1;
        }
        return 0;
    }

    public void executarProva(int index, int totalJugadors, int any, ArrayList<Jugador> jugadors, Vista vista) {
        for(Jugador jugador : jugadors) {
            if (jugador.getAny() == any && jugador.getPI()>0) {
                // Executa la prova de publicacio per un jugador especific
                vista.showSubmission(jugador.getTitle());

                int value = 0;
                Random rand = new Random();

                // Repetim la prova sempre que random dongui a revisar
                do {
                    // Calcula el seguent random
                    value = rand.nextInt(100);
                    if (value < revisionProbability) {
                        System.out.print(" Revisions...");
                    } else if (value >= revisionProbability && value < (revisionProbability + rejectionProbability)) {
                        // Penalitzem per haver sigut rebutjat
                        jugador.penalitzar(getPuntsPenalitzar());
                        System.out.print(" Rejected. PI count: " + jugador.getPI() + "\n");
                    } else if (value >= (revisionProbability + rejectionProbability) && value < 100) {
                        // Premiem per haver sigut acceptat
                        jugador.premiar(getPuntsPremiar());
                        System.out.print(" Accepted! PI count: " + jugador.getPI() + "\n");
                    }
                } while (value < revisionProbability);
            }
        }
    }
}
