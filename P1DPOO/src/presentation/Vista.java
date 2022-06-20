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

    /**
     * Método que sirve para pedir un carácter
     * @return char de el carácter introducido
     */
    private char askForChar() {
        String line = null;
        do {
            if (line != null) {
                System.out.print("Char value was expected: ");
            }

            line = scanner.nextLine();
        } while (line.length() != 1);

        return Character.toUpperCase(line.charAt(0));
    }

    /**
     * Método que sirve para pedir un int
     * @return int introducido
     */
    private int askForInteger(){
        while (!scanner.hasNextInt()) {
            System.out.print("Integer input expected. Please try again: ");
            scanner.next();
        }

        int option = scanner.nextInt();
        scanner.nextLine();

        return option;
    }

    /**
     * Método que sirve para pedir un int para el valor de acceptance
     * @return int con el valor de acceptance introducido
     */
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

    /**
     * Método que sirve para pedir un int para el valor de revision
     * @return int con el valor de revision introducido
     */
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

    /**
     * Método que sirve para pedir un int para el valor de rejection
     * @return int con el valor de rejection introducido
     */
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

    /**
     * Método que sirve para pedir por pantalla el nombre de una prueva
     * @return String del nombre de la prueba
     */
    public String askTrialName(){
        show(TRIAL_NAME);
        return scanner.nextLine();
    }

    /**
     * Método que sirve para pedir el nombre de la publicacion
     * @return String del nombre
     */
    public String askJournalName(){
        show(JOURNAL_NAME);
        return scanner.nextLine();
    }

    /**
     * Método que sirve para pedir el quartil
     * @return String del quartil
     */
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

    /**
     * Método que sirve para pedir la confirmacion del nombre de la prueba que queremos borrar
     * @return String con el nombre
     */
    public String askDeleteTrialName(){
        show(NAME_CONFIRMAITON);
        return scanner.nextLine();
    }

    /**
     * Método que sirve para pedir el rol del programa
     * @return int con el rol
     */
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

    /**
     * Método que sirve para pedir las opciones del menu
     * @return int con la opcion
     */
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

    /**
     * Método que sirve para pedir la opcion del menu de pruevas
     * @return char con la opcion
     */
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

    /**
     * Método que sirve para escoger el tipo de prueva
     * @return int con el tipo escogido
     */
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

    /**
     * Método que sirve para escoger una opcion de pruevas
     * @param numProves int con el numero de pruevas
     * @return int de la opcion escogida
     */
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

    /**
     * Método que sirve para escoger una opcion de ediciones
     * @param numEdicions int con el numero de ediciones
     * @return int de la opcion escogida
     */
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

    /**
     * Método que sirve para escoger la opcion del menu de ediciones
     * @return char con la opcion escogida
     */
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

    /**
     * Método que sirve para pedir y devolver el año de creacion
     * @param current_year int del año actual
     * @return int del año introducido
     */
    public int askCreationYear(int current_year){
        show(CREATION_YEAR);
        return askEditionYear(current_year);
    }

    /**
     * Método que sirve para coger y comprobar el año introducido con el actual
     * @param current_year int del año actual
     * @return int del año recogido
     */
    private int askEditionYear(int current_year){
        int year;
        do {
            year = askForInteger();
            if(year < current_year){
                showError();
            }
        } while (year < current_year);
        return year;
    }

    /**
     * Método que sirve para pedir los jugadores iniciales
     * @return int de los jugadores
     */
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

    /**
     * Método que sirve para pedir el numero de pruebas
     * @return int del numero de pruebas
     */
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


    //ToDo -> borrar si no se usa
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

    /**
     * Método que sirve para pedir y devolver el año de duplicacion
     * @param current_year int del año actual
     * @return int de el año introducido
     */
    public int askDuplicationYear(int current_year){
        show(DUPLICATION_YEAR);
        return askEditionYear(current_year);
    }

    /**
     * Método que sirve para pedir y devolver los jugadores duplicados
     * @return int de los jugadores introducidos
     */
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

    /**
     * Método que sirve para pedir el año para borrar una edicion
     * @param current_year int del año actual
     * @return int del año introducido
     */
    public int askDeleteEditionYear(int current_year){
        show(EDITION_YEAR_DELETE);
        return askEditionYear(current_year);
    }

    /**
     * Método que sirve para mostrar el mensaje de que entramos en el mode management
     */
    public void showManagement(){
        show(MANAGEMENT);
    }

    /**
     * Método que sirve para mostrar el mensaje de que cerramos el programa
     */
    public void showShutDown(){
        show(SHUTTING_DOWN);
    }
    //todo -> si no se usa se borra
    public void showExecution(){
        show(EXECUTION);
    }

    /**
     * Método que sirve para mostrar un String
     * @param cadena String a mostrar
     */
    private void show(String cadena){
        System.out.print(cadena);
    }

    /**
     * Método que sirve para mostrar el banner principal del juego
     */
    public void showBanner(){
        show(BANNER);
    }

    /**
     * Método que sirve para mostrar errores
     */
    public void showError(){
        show(ERROR);
    }

    /**
     * Método que sirve para mostrar un error de no coincidencia de prueba
     */
    public void showTrialMatchError(){
        show(TRIAL_MATCH_ERROR);
    }

    /**
     * Método que sirve para mostrar un error de no coincidencia de edicion
     */
    public void showEditionMatchError(){
        show(EDITION_MATCH_ERROR);
    }

    /**
     * Método que sirve para mostrar un error de un nombre
     */
    public void showNameError(){
        show(NAME_ERROR);
    }

    /**
     * Método que sirve para mostrar un error de un año
     */
    public void showYearError(){
        show(YEAR_ERROR);
    }

    /**
     * Método que sirve para mostrar un error de que no hay pruebas
     */
    public void showAbsenceOfTrials(){
        show(ABSENCE_OF_TRIALS);
    }

    /**
     * Método que sirve para mostrar un error de que no hay ediciones
     */
    public void showAbsenceOfEditions(int year){
        System.out.printf(ABSENCE_OF_EDITIONS_FORMAT, year);
    }

    /**
     * Método que sirve para mostrar un mensaje de pruebas actuales antes de listar estas
     */
    public void showTrialsMsg(){
        show(CURRENT_TRIALS);
    }

    /**
     * Método que sirve para mostrar las pruebas actuales
     * @param prova LinkedList de tipo prueba con la informacion de las pruebas que vamos a mostrar
     */
    public void showCurrentTrials(LinkedList<Prova> prova){
        int i;

        for(i = 0; i < prova.size() ;i++){
            Prova p = prova.get(i);

            show("\n\t" + (i + 1) + ") " + p.getNom());
        }
        show("\n\n\t" + (i + 1) + ") Back");
    }

    /**
     * Método que sirve para mostrar la prueba seleccionada
     * @param selectedProva la prueba seleccionada que queremos mostrar
     */
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

    /**
     * Método que sirve para mostrar el banner de las pruebas
     */
    public void showTrialsBanner(){
        show(TRIALS_BANNER);
    }

    /**
     * Método que sirve para mostrar un mensaje de que la prueba se ha creado correctamente
     */
    public void showTrialSuccessful(){
        show(TRIAL_SUCCESSFUL);
    }

    /**
     * Método que sirve para preguntar que prueba queremos borrar
     */
    public void showTrialDelete(){
        show(TRIAL_DELETE);
    }

    /**
     * Método que sirve para mostrar un mensaje de que la prueba se ha borrado correctamente
     */
    public void showTrialDeleted(){
        show(TRIAL_DELETED);
    }

    /**
     * Método que sirve para avisar de que la prueba que queremos borrar esta en uso y por lo tanto no la podemos borrar
     */
    public void showProvaEnUs(){
        show(TRIAL_BEEING_USED);
    }

    /**
     * Método que sivre para mostrar un mensaje de que la edicion se ha creado
     */
    public void showEditionSuccessful(){
        show(EDITION_SUCCESSFUL);
    }

    /**
     * Método que sirve para mostrar un mensaje de ediciones actuales antes de listar estas
     */
    public void showEditionMsg(){
        show(CURRENT_EDITIONS);
    }

    /**
     * Método que sirve para mostrar las ediciones actuales
     * @param edicions LinkedList de tipo edicio con la informacion de las ediciones que vamos a mostrar
     */
    public void showCurrenEditions(LinkedList<Edicio> edicions){
        int i;

        for(i = 0; i < edicions.size() ; i++){
            int any = edicions.get(i).getAny();

            show("\n\t" + (i + 1) + ") The Trials " + any);
        }
        show("\n\n\t" + (i + 1) + ") Back");
    }

    /**
     * Método que sirve para mostrar la edicion seleccionada
     * @param selectedEdition la prueba seleccionada que queremos mostrar
     * @param proves LinkedList de las pruebas
     */
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

    /**
     * Método que sirve para preguntar qué edicion queremos clonar
     */
    public void showCloneEdition(){
        show(EDITION_CLONE);
    }

    /**
     * Método que sirve para mostrar un mensaje de que la edicion se ha clonado satisfactoriamente
     */
    public void showCloneSuccessful(){
        show(CLONE_SUCCESSFUL);
    }

    /**
     * Método que sirve para preguntar qué edicion quiere eliminar
     */
    public void showEditionDelete(){
        show(EDITION_DELETE);
    }

    /**
     * Método que sirve para mostrar un mensaje de que la edicion se ha eliminado
     */
    public void showEditionDeleted(){
        show(EDITION_DELETED);
    }

    /**
     * Método que sirve para escoger una prueba y devolverla
     * @param proves LinkedList de tipo prueba
     * @param numProves int con el numero de pruebas
     * @return int[] array de ints
     */
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

    /**
     * Método que sirve para mostrar el banner de las pruebas
     * @param year int con el año para mostrarlo
     */
    public void showTrialsTimeBanner(int year){
        System.out.printf(TRIALS_TIME_BANNER_FORMAT, year);
    }

    /**
     * Método que sirve para mostrar la prueba que se esta ejecutando
     * @param num int con el estado
     * @param nom String con el nombre
     */
    public void showRunningTrial(int num, String nom){
        System.out.printf(TRIALS_RUNNING_FORMAT, num, nom);
    }

    /**
     * Método que sirve para mostrar el nombre del jugador
     * @param nom String con el nombre
     */
    public void showSubmission(String nom){
        System.out.printf(SUBMISSION_FORMAT, nom);
    }
    //todo -> borrar si no se usa
    public void showAccepted(int nousPI){
        System.out.printf(ACCEPTED_FORMAT, nousPI);
    }
    //todo -> borrar si no se usa
    public void showRevised(){
        show(REVISED);
    }
    //todo -> borrar si no se usa
    public void showRejected(int nousPI){
        if (nousPI < 0){
            nousPI = 0;
        }
        System.out.printf(REJECTED_FORMAT, nousPI);
        if(nousPI <= 0){
            show(PLAYER_DISCUALIFIED);
        }
    }

    /**
     * Método que sirve para preguntar el nombre del jugador
     * @param index int con el indice
     * @param total int con el total de jugadores
     * @return String con el nombre
     */
    public String askPlayersName(int index, int total){
        System.out.printf(PLAYERS_NAME, index, total);
        return scanner.nextLine();
    }

    /**
     * Método que sirve para preguntar si se quiere continuar
     * @return boolean para saber si debe continuar
     */
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

    /**
     * Método que sirve para mostrar un mensaje anunciando los jugadores que han perdido
     * @param any año de la prueba
     */
    public void showPlayersLost(int any){
        System.out.printf(LOST_FORMAT, any);
    }

    /**
     * Método que sirve para mostrar un mensaje anunciando los jugadores que han ganado
     * @param any año de la prueba
     */
    public void showPlayersWon(int any){
        System.out.printf(WIN_FORMAT, any);
    }

    /**
     * Método para anunciar que el juego se ha parado
     */
    public void showGameStopped(){
        show(GAME_STOPPED);
    }

    /**
     * Método para mostrar el cambio de ingeniero a master
     * @param nom String con el nombre
     */
    public void showEnginyerCanviaMaster(String nom) {
        System.out.printf(ENGINEER_TO_MASTER, nom);
    }

    /**
     * Método que sirve para mostrar el cambio de master a doctor
     * @param nom String con el nombre
     */
    public void showMasterCanviaDoctor(String nom) {
        System.out.printf(MASTER_TO_DOCTOR, nom);
    }

    /**
     * Método que sirve para preguntar y recoger el nombre del master
     * @return String con el nombre
     */
    public String askMasterName() {
        show(MASTER_NAME);
        return scanner.nextLine();
    }

    /**
     * Método que sirve para preguntar los creditos
     * @return int con el numero de créditos
     */
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

    /**
     * Método que sirve para preguntar y devolver la probabilidad
     * @return int con la probabilidad
     */
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

    /**
     * Método que sirve para preguntar y devolver el campo del doctorado
     * @return String con el campo del doctorado
     */
    public String askDoctoralField() {
        show(DOCTOR_FIELD);
        return scanner.nextLine();
    }

    /**
     * Método que sirve para preguntar la dificultad
     * @return int con la dificultad
     */
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

    /**
     * Método que sirve para pedir y recoger la entidad
     * @return String con la entidad
     */
    public String askEntitat() {
        show(BUDGET_ENTITY);
        return scanner.nextLine();
    }

    /**
     * Método que sirve para preguntar y recoger el presupuesto
     * @return int con el presupuesto
     */
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

    /**
     * Método que sirve para mostrar resultados del master
     * @param nom String con el nombre
     * @param creditsSuperats int con los creditos superados
     * @param creditsTotals int con los creditos totales
     */
    public void showMasterStudy(String nom, int creditsSuperats, int creditsTotals) {
        System.out.printf(MASTER_FORMAT, nom, creditsSuperats, creditsTotals);
    }

    /**
     * Método que sirve para mostrar los puntos cuando ha fallado el master
     * @param pi int con la puntuacion
     */
    public void showMasterFallat(int pi) {
        System.out.printf(MASTER_FAIL, pi);
    }

    /**
     * Método que sirve para mostrar los puntos cuando ha superado el master
     * @param pi int con la puntuacion
     */
    public void showMasterSuperat(int pi) {
        System.out.printf(MASTER_SUCCESS, pi);
    }

    /**
     * Método que sirve para mostrar los puntos cuando ha superado el doctorado
     * @param pi int con la puntuacion
     * @param nom String con el nombre
     */
    public void showDoctorSuperat(String nom, int pi) {
        System.out.printf(DOCTOR_SUCCESS, nom, pi);
    }

    /**
     * Método que sirve para mostrar los puntos cuando ha fallado el doctorado
     * @param pi int con la puntuacion
     * @param nom String con el nombre
     */
    public void showDoctorFallat(String nom, int pi) {
        System.out.printf(DOCTOR_FAIL, nom, pi);
    }

    /**
     * Método que sirve para anunciar la superacion del presupuesto
     */
    public void showPresupostSuperat() {
        show(PRESUPOST_APROVAT);
    }

    /**
     * Método que sirve para anunciar que el presupuesto ha sido fallado
     */
    public void showPresupostFallat() {
        show(PRESUPOST_REBUTJAT);
    }

    /**
     * Método que sirve para mostrar el nombre y los puntos ganados del presupuesto
     * @param nom String con el nombre
     * @param pi int con los puntos
     */
    public void showPresupostPuntsGuanyats(String nom, int pi) {
        System.out.printf(PRESUPOST_PUNTS, nom, pi);
    }
}