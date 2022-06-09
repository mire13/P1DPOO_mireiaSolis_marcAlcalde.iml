package presentation;

import business.*;

import java.util.*;
/**
 * Es una clase que contiene la información que se pide al usuario por pantalla
 */
public class Vista {

    private final static String TIPO_FICHERO = "\nThe IEE needs to know where your allegiance lies.\n\n\tI) People's Front of Engineering (CSV)\n\tII) Engineering People's Front (JSON)\n\n";
    private final static String WELCOME = "\nWelcome to the trials. Who are you? \n\n\tA) The Composer\n\tB) This year's Conductor\n\nEnter a role: ";
    private final static String BANNER = """
                  ________            ______     _       __   
                 /_  __/ /_  ___     /_  __/____(_)___ _/ /____
                  / / / __ \\/ _ \\     / / / ___/ / __ `/ / ___/
                 / / / / / /  __/    / / / /  / / /_/ / (__  ) 
                /_/ /_/ /_/\\___/    /_/ /_/  /_/\\__,_/_/____/  
                """;
    //Missatges d'Error
    private final static String ERROR = "Invalid value, please try again: ";
    private final static String NAME_ERROR = "\nThis name is invalid or used\n";
    private final static String YEAR_ERROR = "\nThis year is invalid or used\n";
    private final static String TRIAL_MATCH_ERROR = "\nThe trial names doesn't match.\n";
    private final static String EDITION_MATCH_ERROR = "\nThe trial names doesn't match.\n";
    private final static String ABSENCE_OF_TRIALS= "\nThere are no trials in the system.\n";
    private final static String ABSENCE_OF_EDITIONS_FORMAT = "\nNo edition is defined for the current year (%d).\n\nShutting down...";

    private final static String MANAGEMENT = "\nEntering management mode...\n";
    private final static String MANAGEMENT_OPTIONS = "\n\t1) Manage Trials\n" + "\t2) Manage Editions\n\n" + "\t3) Exit" + "\n\nEnter an option: ";
    private final static String SHUTTING_DOWN = "\nShutting down...\n";
    private final static String EXECUTION = "\nEntering execution mode...\n";

    //Missatges de Prova
    private final static String TRIAL_OPTION = "\n\ta) Create Trial\n \tb) List Trials\n \tc) Delete Trial\n \n\td) Back\n\nEnter an option: ";
    private final static String TRIAL_TYPE = "\n\t--- Trial types ---\n\n\t1) Paper publication\n\t2) Master studies\n\t3) Doctoral thesis defense\n\t4) Budget request\n\nEnter the trial’s type: ";
    private final static String TRIAL_NAME = "Enter the trial’s name: ";
    private final static String JOURNAL_NAME = "Enter the journal’s name: ";
    private final static String QUARTILE_TYPE = "Enter the journal’s quartile: ";
    private final static String ACCEPTANCE = "Enter the acceptance probability: ";
    private final static String REVISION = "Enter the revision probability: ";
    private final static String REJECTION = "Enter the rejection probability: ";
    private final static String TRIAL_SUCCESSFUL = "\nThe trial was created successfully!\n";
    private final static String CURRENT_TRIALS = "\nHere are the current trials, do you want to see more details or go back?\n";
    private final static String TRIAL_DELETE = "\nWhich trial do you want to delete?\n";
    private final static String NAME_CONFIRMAITON = "\nEnter the trial’s name for confirmation: ";
    private final static String TRIAL_DELETED = "\nThe trial was successfully deleted.\n";
    private final static String TRIAL_BEEING_USED = "\nCan not delete the trial because it is being used.\n";
    private final static String MASTER_NAME = "Enter the masters’s name: ";
    private final static String MASTER_CREDITS = "Enter the masters’s ECTS number: ";
    private final static String MASTER_PROBABILITY = "Enter the masters’s pass probability: ";
    private final static String DOCTOR_FIELD = "Enter the thesis field of study: ";
    private final static String DOCTOR_DIFFICULTY = "Enter the defense difficulty: ";
    private final static String BUDGET_ENTITY = "Enter the entity’s name: ";
    private final static String BUDGET_AMOUNT = "Enter the budget amount: ";

    //Missatges d'Edicions
    private final static String EDITION_OPTIONS = "\n\ta) Create Edition\n \tb) List Editions\n \tc) Duplicate Edition\n \td) Delete Edition\n \n\te) Back\n\nEnter an option: ";
    private final static String CREATION_YEAR = "\nEnter the edition’s year: ";
    private final static String INITIAL_PLAYERS = "Enter the initial number of players: ";
    private final static String NUM_TRIALS = "Enter the number of trials: ";
    private final static String TRIALS_BANNER = "\n\t--- Trials ---\n";
    private final static String TRIALS_TIME_BANNER_FORMAT = "\n\t--- The Trials %d ---\n";
    private final static String EDITION_SUCCESSFUL = "\n\nThe edition was created successfully!\n";
    private final static String CURRENT_EDITIONS = "\nHere are the current editions, do you want to see more details or go back?\n";
    private final static String EDITION_CLONE = "\nWhich edition do you want to clone?\n";
    private final static String DUPLICATION_YEAR = "Enter the new edition’s year: ";
    private final static String DUPLICATION_PLAYERS= "Enter the new edition’s initial number of players: ";
    private final static String CLONE_SUCCESSFUL = "\nThe edition was cloned successfully!";
    private final static String EDITION_DELETE= "\nWhich edition do you want to delete?";
    private final static String EDITION_YEAR_DELETE = "\nEnter the edition’s year for confirmation: ";
    private final static String EDITION_DELETED = "\nThe edition was successfully deleted.\n";
    private final static String PICK_TRIAL = "Pick a trial (%d/%d): ";

    private final static String REVISED= " Revisions...";
    private final static String PLAYER_DISCUALIFIED= " - Discualified! ";

    private final static String TRIAL_PUBLICACIO_INFO_FORMAT = "\nTrial: %s (%s)\nJournal: %s (%s)\nChances: %s%% acceptance, %s%% revision, %s%% rejection\n";
    private final static String TRIAL_MASTER_INFO_FORMAT = "\nTrial: %s (%s)\nMaster: %s\nECTS: %s, with a %s%% chance to pass each one\n";
    private final static String TRIAL_DOCTOR_INFO_FORMAT = "\nTrial: %s (%s)\nField: %s\nDifficulty: %s\n";
    private final static String TRIAL_BUDGET_INFO_FORMAT = "\nTrial: %s (%s)\nEntity: %s\nBudget: %s€\n";
    private final static String TRIAL_PICK_FORMAT = "\nPick a trial (%d/%d): ";
    private final static String EDITION_INFO_FORMAT = "\nYear: %d\nPlayers: %s\nTrials";
    private final static String TRIALS_RUNNING_FORMAT = "\nTrial #%d - %s\n\n";
    private final static String SUBMISSION_FORMAT = "\t%s is submitting...";
    private final static String ACCEPTED_FORMAT = " Accepted! PI count: %d";
    private final static String REJECTED_FORMAT = " Rejected. PI count: %d";
    private final static String LOST_FORMAT = "\nTHE TRIALS %d HAVE ENDED - PLAYERS LOST\n";
    private final static String WIN_FORMAT = "\nTHE TRIALS %d HAVE ENDED - PLAYERS WON\n";
    private final static String GAME_STOPPED  = "\nSaving & Shutting down...\n";
    private final static String ENGINEER_TO_MASTER  = "%s is now a master (with 5 PI). ";
    private final static String MASTER_TO_DOCTOR  = "%s is now a doctor (with 5 PI). ";
    private final static String MASTER_FORMAT  = "\t%s passed %d/%d ECTS.";
    private final static String MASTER_FAIL  = "Sorry... PI count: %d\n";
    private final static String MASTER_SUCCESS  = "Congrats! PI count: %d\n";
    private final static String DOCTOR_SUCCESS  = "\t%s was successful. Congrats! PI count: %d\n";
    private final static String DOCTOR_FAIL  = "\t%s was not successful. Sorry... PI count: %d\n";
    private final static String PRESUPOST_APROVAT  = "\tThe research group got the budget!";
    private final static String PRESUPOST_REBUTJAT  = "\tThe research group failed on getting the budget...";
    private final static String PRESUPOST_PUNTS  = "\t%s PI count: %d\n";

    //CONDUCTOR
    private final static String PLAYERS_NAME= "Enter the player's name (%d/%d): ";
    private final static String CONTINUE_EXECUTION= "Continue the execution? [yes/no]: ";

    private Scanner scanner;

    /**
     * Constructor por defecto de la clase UI
     */
    public Vista(){
        scanner =  new Scanner(System.in);
    }

    /**
     * Método que sirve para pedir al usuario en que formato quiere cargar el fichero (CSV o JSON)
     * @return seleccion int para reconocer si es CSV o JSON
     */
    public int pedirFichero(){
        int seleccion = 0;
        boolean correct = false;
        String opcio = null;

        show(TIPO_FICHERO);

        while(!correct){
            System.out.print("Pick a faction: ");
            opcio = scanner.nextLine();

            if(Objects.equals(opcio, "I")){
                seleccion = 1;
                correct = true;
            } else if(Objects.equals(opcio, "II")){
                seleccion = 2;
                correct = true;
            }
            if(seleccion == 0) {
                System.out.println("\nERROR! This option is incorrect!\n");
            }
            if(correct){
                if(seleccion == 1){
                    System.out.println("\nLoading data from CSV files...\n");
                }
                if(seleccion == 2){
                    System.out.println("\nLoading data from JSON files...\n");
                }
            }
        }
        return seleccion;
    }

    public char askForChar() {
        String line = null;
        do {
            if (line != null) {
                System.out.print("Char value was expected: ");
            }

            line = scanner.nextLine();
        } while (line.length() != 1);

        return Character.toUpperCase(line.charAt(0));
    }

    public int askForInteger(){
        while (!scanner.hasNextInt()) {
            System.out.print("Integer input expected. Please try again: ");
            scanner.next();
        }

        int option = scanner.nextInt();
        scanner.nextLine();

        return option;
    }
    public int askAcceptance(){
        while(true) {
            try {
                show(ACCEPTANCE);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                showError();
                return -1;
            } finally {
                scanner.nextLine();
            }
        }
    }
    public int askRevision(){
        while(true) {
            try {
                show(REVISION);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                showError();
                return -1;
            } finally {
                scanner.nextLine();
            }
        }
    }
    public int askRejection(){
        while(true) {
            try {
                show(REJECTION);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                showError();
                return -1;
            } finally {
                scanner.nextLine();
            }
        }
    }

    public String askTrialName(){
        show(TRIAL_NAME);
        return scanner.nextLine();
    }
    public String askJournalName(){
        show(JOURNAL_NAME);
        return scanner.nextLine();
    }
    public String askQuartile(){
        String quartile;
        show(QUARTILE_TYPE);
        do {
            quartile = scanner.nextLine().toUpperCase();
            if(!Objects.equals(quartile, "Q1") && !Objects.equals(quartile, "Q2") && !Objects.equals(quartile, "Q3") && !Objects.equals(quartile, "Q4")){
                showError();
            }
        } while (!Objects.equals(quartile, "Q1") && !Objects.equals(quartile, "Q2") && !Objects.equals(quartile, "Q3") && !Objects.equals(quartile, "Q4"));
        return quartile;
    }
    public String askDeleteTrialName(){
        show(NAME_CONFIRMAITON);
        return scanner.nextLine();
    }

    public int askForRole() {
        int rol;

        show(WELCOME);
        do {
            rol = askForChar();
            if(rol != 'A' && rol != 'B'){
                showError();
            }
        } while (rol != 'A' && rol != 'B');
        return rol;
    }

    public int askManagementOptions(){
        int option;
        show(MANAGEMENT_OPTIONS);
        do {
            option = askForInteger();
            if(option < 1 || option > 3){
                showError();
            }
        } while (option < 1 || option > 3);
        return option;
    }

    //Ask de Proves
    public char askTrialOption(){
        char option;
        show(TRIAL_OPTION);
        do {
            option = askForChar();
            if(option != 'A' && option != 'B' && option != 'C' && option != 'D'){
                showError();
            }
        } while (option != 'A' && option != 'B' && option != 'C' && option != 'D');
        return option;
    }

    public int askTrialType(){
        int option;

        //Demanem el tipus de prova, mentre no sigui una de les 4 tornem a demanar
        show(TRIAL_TYPE);
        do {
            option = askForInteger();
            if(option < 1 || option > 4){
                showError();
            }
        } while (option < 1 || option > 4);
        return option;
    }
    public int askCurrentTrialsOption(int numProves){
        int option;

        show("\n\nEnter an option: ");
        do {
            option = askForInteger();
            if(option > numProves + 1 || option < 1){
                showError();
            }
        } while (option > numProves + 1 || option < 1);
        return option;
    }

    public int askCurrentEditionsOption(int numEdicions){
        int option;

        show("\n\nEnter an option: ");
        do {
            option = askForInteger();
            if(option > (numEdicions + 1) || option < 1){
                showError();
            }
        } while (option > (numEdicions + 1) || option < 1);
        return option;
    }

    //Ask d'Edicions
    public char askEditionOption(){
        char option;
        show(EDITION_OPTIONS);
        do {
            option = askForChar();
            if(option != 'A' && option != 'B' && option != 'C' && option != 'D' && option != 'E'){
                showError();
            }
        } while (option != 'A' && option != 'B' && option != 'C' && option != 'D' && option != 'E');
        return option;
    }

    public int askCreationYear(int current_year){
        show(CREATION_YEAR);
        return askEditionYear(current_year);
    }

    public int askEditionYear(int current_year){
        int year;
        do {
            year = askForInteger();
            if(year < current_year){
                showError();
            }
        } while (year < current_year);
        return year;
    }

    public int askInitialPlayers(){
        int players;
        show(INITIAL_PLAYERS);
        do {
            players = askForInteger();
            if(players < 1 || players > 5 ){
                showError();
            }
        } while (players < 1 || players > 5 );
        return players;
    }

    public int askNumTrials(){
        int numTrials;
        show(NUM_TRIALS);
        do {
            numTrials = askForInteger();
            if(numTrials < 3 || numTrials > 12){
                showError();
            }
        } while (numTrials < 3 || numTrials > 12);
        return numTrials;
    }

    public int askCurrentTrialsSelection(int numProves, int i){
        int trialPicked;

        do {
            System.out.printf(TRIAL_PICK_FORMAT, (i + 1), numProves);
            trialPicked = askForInteger();
            if(trialPicked > numProves || trialPicked < numProves){
                showError();
            }
        } while (trialPicked > numProves || trialPicked < 1);
        return trialPicked;
    }

    public int askDuplicationYear(int current_year){
        show(DUPLICATION_YEAR);
        return askEditionYear(current_year);
    }

    public int askDuplicationPlayers(){
        int players;
        show(DUPLICATION_PLAYERS);
        do {
            players = askForInteger();
            if(players < 1 || players > 5 ){
                showError();
            }
        } while (players < 1 || players > 5 );
        return players;
    }

    public int askDeleteEditionYear(int current_year){
        show(EDITION_YEAR_DELETE);
        return askEditionYear(current_year);
    }

    //Show COMPOSITOR
    public void showManagement(){
        show(MANAGEMENT);
    }
    public void showShutDown(){
        show(SHUTTING_DOWN);
    }
    public void showExecution(){
        show(EXECUTION);
    }

    //Show d'Errors
    public void show(String cadena){
        System.out.print(cadena);
    }
    public void showBanner(){
        show(BANNER);
    }
    public void showError(){
        show(ERROR);
    }
    public void showTrialMatchError(){
        show(TRIAL_MATCH_ERROR);
    }
    public void showEditionMatchError(){
        show(EDITION_MATCH_ERROR);
    }
    public void showNameError(){
        show(NAME_ERROR);
    }
    public void showYearError(){
        show(YEAR_ERROR);
    }
    public void showAbsenceOfTrials(){
        show(ABSENCE_OF_TRIALS);
    }
    public void showAbsenceOfEditions(int year){
        System.out.printf(ABSENCE_OF_EDITIONS_FORMAT, year);
    }

    //Show de Proves
    public void showTrialsMsg(){
        show(CURRENT_TRIALS);
    }
    public void showCurrentTrials(LinkedList<Prova> prova){
        int i;

        for(i = 0; i < prova.size() ;i++){
            Prova p = prova.get(i);

            show("\n\t" + (i + 1) + ") " + p.getNom());
        }
        show("\n\n\t" + (i + 1) + ") Back");
    }

    public void showSelectedListedTrial(Prova selectedProva){
        String[] info = selectedProva.getInfo();
        if (selectedProva instanceof ProvaPublicacio)
            System.out.printf(TRIAL_PUBLICACIO_INFO_FORMAT, info[0], info[1], info[2], info[3], info[4], info[5], info[6]);

        if (selectedProva instanceof ProvaEstudiMaster)
            System.out.printf(TRIAL_MASTER_INFO_FORMAT, info[0], info[1], info[2], info[3], info[4]);

        if (selectedProva instanceof ProvaTesiDoctoral)
            System.out.printf(TRIAL_DOCTOR_INFO_FORMAT, info[0], info[1], info[2], info[3]);

        if (selectedProva instanceof ProvaPresupost)
            System.out.printf(TRIAL_BUDGET_INFO_FORMAT, info[0], info[1], info[2], info[3]);

    }
    public void showTrialsBanner(){
        show(TRIALS_BANNER);
    }
    public void showTrialSuccessful(){
        show(TRIAL_SUCCESSFUL);
    }
    public void showTrialDelete(){
        show(TRIAL_DELETE);
    }
    public void showTrialDeleted(){
        show(TRIAL_DELETED);
    }
    public void showProvaEnUs(){
        show(TRIAL_BEEING_USED);
    }


    //Show d'Edicions
    public void showEditionSuccessful(){
        show(EDITION_SUCCESSFUL);
    }
    public void showEditionMsg(){
        show(CURRENT_EDITIONS);
    }
    public void showCurrenEditions(LinkedList<Edicio> edicions){
        int i;

        for(i = 0; i < edicions.size() ; i++){
            int any = edicions.get(i).getAny();

            show("\n\t" + (i + 1) + ") The Trials " + any);
        }
        show("\n\n\t" + (i + 1) + ") Back");
    }
    public void showSelectedListedEdition(Edicio selectedEdition, LinkedList<Prova> proves){
        System.out.printf(EDITION_INFO_FORMAT, selectedEdition.getAny(), selectedEdition.getTotalJugadors());
        int i;
        int[] editionProves = selectedEdition.getProves();

        for(i = 0; i < selectedEdition.getProves().length ; i++){
            Prova p = proves.get(editionProves[i]);

            show("\n\t" + (i + 1) + "- " + p.getNom() + " (" + p.getType() + ")");
        }

        show("\n\n");
    }
    public void showCloneEdition(){
        show(EDITION_CLONE);
    }
    public void showCloneSuccessful(){
        show(CLONE_SUCCESSFUL);
    }
    public void showEditionDelete(){
        show(EDITION_DELETE);
    }
    public void showEditionDeleted(){
        show(EDITION_DELETED);
    }
    public int[] showPickTrials(LinkedList<Prova> proves, int numProves){
        // Mostra proves disponibles
        for(int i = 0; i < proves.size() ;i++) {
            show("\n\t" + (i + 1) + ") " + proves.get(i).getNom());
        }

        show("\n");

        // Escollir entre les proves
        int[] p = new int[numProves];

        for (int i = 0; i < numProves; i++) {
            int tId = 0;
            System.out.printf(PICK_TRIAL, i+1, numProves);
            do {
                tId = askForInteger();
                if (tId < 1 || tId > proves.size()) {
                    showError();
                }
            } while (tId < 1 || tId > proves.size());
            p[i] = tId-1;
        }

        return p;
    }

    //CONDUCTOR
    public void showTrialsTimeBanner(int year){
        System.out.printf(TRIALS_TIME_BANNER_FORMAT, year);
    }
    public void showRunningTrial(int num, String nom){
        System.out.printf(TRIALS_RUNNING_FORMAT, num, nom);
    }
    public void showSubmission(String nom){
        System.out.printf(SUBMISSION_FORMAT, nom);
    }
    public void showAccepted(int nousPI){
        System.out.printf(ACCEPTED_FORMAT, nousPI);
    }
    public void showRevised(){
        show(REVISED);
    }
    public void showRejected(int nousPI){
        if (nousPI < 0){
            nousPI = 0;
        }
        System.out.printf(REJECTED_FORMAT, nousPI);
        if(nousPI <= 0){
            show(PLAYER_DISCUALIFIED);
        }
    }

    public String askPlayersName(int index, int total){
        System.out.printf(PLAYERS_NAME, index, total);
        return scanner.nextLine();
    }

    public boolean askIfContinue(){
        String response;
        boolean continuar = false;
        show(CONTINUE_EXECUTION);
        do {
            response = scanner.nextLine().toUpperCase();
            
            if (Objects.equals(response, "YES")){
                continuar = true;
            }else if(Objects.equals(response, "NO")){
                continuar = false;
            }else{
                showError();
            }
            
        }while(!Objects.equals(response, "YES") && !Objects.equals(response, "NO"));
        return continuar;
    }
    public void showPlayersLost(int any){
        System.out.printf(LOST_FORMAT, any);
    }
    public void showPlayersWon(int any){
        System.out.printf(WIN_FORMAT, any);
    }
    public void showGameStopped(){
        show(GAME_STOPPED);
    }
    public void showEnginyerCanviaMaster(String nom) {
        System.out.printf(ENGINEER_TO_MASTER, nom);
    }
    public void showMasterCanviaDoctor(String nom) {
        System.out.printf(MASTER_TO_DOCTOR, nom);
    }

    public String askMasterName() {
        show(MASTER_NAME);
        return scanner.nextLine();
    }

    public int askCredits() {
        show(MASTER_CREDITS);
        int credits = 0;
        do {
            credits = askForInteger();
            if (credits < 60 || credits > 120)
                showError();
        } while(credits < 60 || credits > 120);

        return credits;
    }

    public int askCreditsProbability() {
        show(MASTER_PROBABILITY);
        int probabilitat = 0;
        do {
            probabilitat = askForInteger();
            if (probabilitat < 0 || probabilitat > 100)
                showError();
        } while(probabilitat < 0 || probabilitat > 100);

        return probabilitat;
    }

    public String askDoctoralField() {
        show(DOCTOR_FIELD);
        return scanner.nextLine();
    }

    public int askDificultat() {
        show(DOCTOR_DIFFICULTY);
        int dificultat = 0;
        do {
            dificultat = askForInteger();
            if (dificultat < 1 || dificultat > 10)
                showError();
        } while(dificultat < 1 || dificultat > 10);
        return dificultat;

    }

    public String askEntitat() {
        show(BUDGET_ENTITY);
        return scanner.nextLine();
    }

    public int askPresupost() {
        show(BUDGET_AMOUNT);
        int presupost = 0;
        do {
            presupost = askForInteger();
            if (presupost < 1000 || presupost > 2000000000)
                showError();
        } while(presupost < 1000 || presupost > 2000000000);
        return presupost;
    }

    public void showMasterStudy(String nom, int creditsSuperats, int creditsTotals) {
        System.out.printf(MASTER_FORMAT, nom, creditsSuperats, creditsTotals);
    }

    public void showMasterFallat(int pi) {
        System.out.printf(MASTER_FAIL, pi);
    }

    public void showMasterSuperat(int pi) {
        System.out.printf(MASTER_SUCCESS, pi);
    }

    public void showDoctorSuperat(String nom, int pi) {
        System.out.printf(DOCTOR_SUCCESS, nom, pi);
    }

    public void showDoctorFallat(String nom, int pi) {
        System.out.printf(DOCTOR_FAIL, nom, pi);
    }

    public void showPresupostSuperat() {
        show(PRESUPOST_APROVAT);
    }

    public void showPresupostFallat() {
        show(PRESUPOST_REBUTJAT);
    }

    public void showPresupostPuntsGuanyats(String nom, int pi) {
        System.out.printf(PRESUPOST_PUNTS, nom, pi);
    }
}






















