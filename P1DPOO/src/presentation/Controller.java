package presentation;

import business.*;

import java.util.Date;
import java.util.Calendar;

/**
 * Clase encargada de llevar el control de las acciones del programa
 */
public class Controller {
    private Vista vista;
    private TaskManager taskManager;
    private int CURRENT_YEAR;

    /**
     * Constructor por defecto
     */
    public Controller() {
        this.vista = new Vista();
        this.taskManager = new TaskManager();

        // Set the current year
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        CURRENT_YEAR = calendar.get(Calendar.YEAR);
    }

    /**
     * Método que consiste en el cuerpo de la ejecucion
     */
    public void run() {
        char opcio;
        int fichero = vista.pedirFichero();
        if (fichero == 1) {
            taskManager.setCSV(true);
            taskManager.leerCSV();
        } else if (fichero == 2){
            taskManager.setCSV(false);
            taskManager.leerJSON();
        }
        vista.showBanner();
        opcio = (char) vista.askForRole();
        executarOpcio(opcio);
    }

    /**
     * Método que sirve para ejecutar la opcion
     * @param opcio char de la opcion
     */
    private void executarOpcio(char opcio) {

        switch (opcio) {
            case 'A':
                compositor();
                break;
            case 'B':
                conductor();
                break;
            default:
                vista.showError();
                break;
        }
    }

    /**
     * Método para gestionar las ediciones y las pruebas
     */
    private void compositor() {
        vista.showManagement();
        int administracio;

        do {
            administracio = vista.askManagementOptions();

            switch (administracio) {
                case 1:
                    administraProves();
                    break;
                case 2:
                    administraEdicions();
                    break;
                case 3:
                    vista.showShutDown();
                    break;
                default:
                    vista.showError();
                    break;
            }
        } while (administracio != 3);

    }

    /**
     * Método que sirve para dministrar las pruebas
     */
    private void administraProves() {
        char opcioProva;

        do {
            opcioProva = vista.askTrialOption();

            switch (opcioProva) {
                case 'A':
                    creaProva();
                    break;
                case 'B':
                    llistaProves();
                    break;
                case 'C':
                    eliminarProva();
                    break;
                case 'D':
                    break;
                default:
                    vista.showError();
                    break;
            }
        } while (opcioProva != 'D');
    }

    /**
     * Método que sirve para crear una prueba
     */
    private void creaProva() {
        String trialName;
        int tryalType = vista .askTrialType();

        //Demanem el nom de la prova fins que aquest no estigui repetit o sigui un \n
        do {
            trialName = vista .askTrialName();

            if (taskManager.nomRepetit(trialName)){
                vista.showNameError();
            }
        }while(taskManager.nomRepetit(trialName));

        switch(tryalType){
            case 1:
                //Si es tipus ProvaPublicacio demanem les dades necessaries pre crear-la
                String journalName = vista .askJournalName();
                String quartile = vista .askQuartile();
                int acceptanceProbability, revisionProbability, rejectionProbability;
                do {
                    acceptanceProbability = vista .askAcceptance();
                    revisionProbability = vista .askRevision();
                    rejectionProbability = vista .askRejection();
                    //Comprovem que les provabilitats sumin 100 y no siugin inferiors a 0
                }while((acceptanceProbability + revisionProbability + rejectionProbability) != 100 || acceptanceProbability < 0 || revisionProbability < 0 || rejectionProbability < 0);

                taskManager.creaProva(new ProvaPublicacio(trialName, journalName, quartile, acceptanceProbability, revisionProbability, rejectionProbability));
                break;
            case 2:
                String masterName = vista.askMasterName();
                int credits = vista.askCredits();
                int probabilitat = vista.askCreditsProbability();
                taskManager.creaProva(new ProvaEstudiMaster(trialName, masterName, credits, probabilitat));
                break;
            case 3:
                String camp = vista.askDoctoralField();
                int dificultat = vista.askDificultat();
                taskManager.creaProva(new ProvaTesiDoctoral(trialName, camp, dificultat));
                break;
            case 4:
                String entitat = vista.askEntitat();
                int presupost = vista.askPresupost();
                taskManager.creaProva(new ProvaPresupost(trialName, entitat, presupost));
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + tryalType);
        }
        //Nomes pot ser succesfull ja que sino tornariem a demanar les dades
        vista.showTrialSuccessful();
    }

    /**
     * Método que sirve para listar las pruebas
     */
    private void llistaProves() {
        int opcio;
        //En cas que no hi hagi proves a mostrar printem error
        if (taskManager.absenceOfTrials()){
            vista.showAbsenceOfTrials();
        }else {
            vista.showTrialsMsg();

            vista.showCurrentTrials(taskManager.getProves());

            //Demanem la prova en que volem indagar
            opcio = vista .askCurrentTrialsOption(taskManager.getProves().size());

            if(opcio != taskManager.getProves().size() + 1) {
                vista.showSelectedListedTrial(taskManager.getProves().get(opcio - 1));
            }
        }
    }

    /**
     * Método que sirve para eliminar una prueba
     */
    private void eliminarProva() {
        int opcio;
        String nomProvaAEliminar;

        vista.showTrialDelete();
        vista.showCurrentTrials(taskManager.getProves());
        opcio = vista .askCurrentTrialsOption(taskManager.getProves().size());

        if(opcio != taskManager.getProves().size() + 1) {
            //Demanem el nom de la prova a eliminar
            nomProvaAEliminar = vista .askDeleteTrialName();

            if (taskManager.provaEnUs(nomProvaAEliminar)) {
                vista.showProvaEnUs();
            } else
                //Si coincideix la eliminem, si no coincideix sortim per si l'usuari ha fet missclic i no volia borrar aquesta
                if (taskManager.elsNomsCoincideixen(opcio-1, nomProvaAEliminar)) {
                    taskManager.eliminaProva(opcio - 1, nomProvaAEliminar);
                    vista.showTrialDeleted();
                } else {
                    vista.showTrialMatchError();
                }
        }
    }

    /**
     * Método que sirve para administrar ediciones
     */
    private void administraEdicions() {
        char opcio;

        do {
            opcio = vista.askEditionOption();

            switch (opcio) {
                case 'A':
                    creaEdicio();
                    break;
                case 'B':
                    llistarEdicions();
                    break;
                case 'C':
                    duplicarEdicio();
                    break;
                case 'D':
                    eliminarEdicio();
                    break;
                case 'E':
                    break;
                default:
                    vista.showError();
                    break;
            }
        } while(opcio!='E');
    }

    /**
     * Método que sirve para crear una edicion
     */
    private void creaEdicio() {
        int anyEdicio, numJugadorsInicials, numProves;
        Edicio novaEdicio;

        //Si no hi ha proves no podem crear cap edicio
        if (taskManager.absenceOfTrials()){
            vista.showAbsenceOfTrials();
        }else {
            do {
                //Demanem l'any fins que no estigui repetit o sigui superior a l'any actual
                anyEdicio = vista .askCreationYear(CURRENT_YEAR);

                if (taskManager.anyRepetit(anyEdicio)) {
                    vista.showYearError();
                }
            } while (taskManager.anyRepetit(anyEdicio));

            numJugadorsInicials = vista .askInitialPlayers();
            numProves = vista .askNumTrials();
            //Donem a escollir entre les proves existents per afegir-les a la edicio
            vista.showTrialsBanner();
            int[] proves = vista.showPickTrials(taskManager.getProves(), numProves);

            //Si es l'edicio de l'any actual creem la edició en curs, sino afegim una edicio a edicionsManager
            novaEdicio = new Edicio(anyEdicio, numJugadorsInicials, numProves, proves);

            taskManager.crearEdicio(novaEdicio);
            vista.showEditionSuccessful();
        }
    }

    /**
     * Método que sirve para listar las ediciones
     */
    private void llistarEdicions(){
        int opcio;

        vista.showEditionMsg();

        vista.showCurrenEditions(taskManager.getEdicions());

        opcio = vista .askCurrentEditionsOption(taskManager.getEdicions().size());

        if(opcio != taskManager.getEdicions().size() + 1) {
            vista.showSelectedListedEdition(taskManager.getEdicions().get(opcio - 1), taskManager.getProves());
        }
    }

    /**
     * Método que sirve para duplicarr las ediciones
     */
    private void duplicarEdicio() {
        int opcio, any, numJugadors;

        vista.showCloneEdition();

        vista.showCurrenEditions(taskManager.getEdicions());

        opcio = vista .askCurrentEditionsOption(taskManager.getEdicions().size());

        if(opcio != taskManager.getEdicions().size() + 1) {
            do {
                any = vista .askDuplicationYear(CURRENT_YEAR);

                if (taskManager.anyRepetit(any)) {
                    vista.showYearError();
                }
            } while (taskManager.anyRepetit(any));

            numJugadors = vista .askDuplicationPlayers();

            taskManager.duplicarEdicio(opcio, any, numJugadors);

            vista.showCloneSuccessful();
        }
    }

    /**
     * Método que sirve para eliminar las ediciones
     */
    private void eliminarEdicio(){
        int opcio, anyAEliminar;

        vista.showEditionDelete();

        vista.showCurrenEditions(taskManager.getEdicions());

        opcio = vista .askCurrentEditionsOption(taskManager.getEdicions().size());

        if(opcio != taskManager.getEdicions().size() + 1){
            anyAEliminar = vista .askDeleteEditionYear(CURRENT_YEAR);

            if (taskManager.confirmacioAny(anyAEliminar, opcio - 1)){
                taskManager.eliminaEdicio(opcio - 1);
                vista.showEditionDeleted();
            } else {
                vista.showEditionMatchError();
            }
        }
    }

    /**
     * Método que sirve para ejecutar la edicion actual
     */
    private void conductor(){
        // Comprova si n'hi ha una edició per aquest any
        if (taskManager.edicioEnCurs(CURRENT_YEAR)!=-1) {
            vista.showTrialsTimeBanner(CURRENT_YEAR);

            // Agafa l'edicio actual
            Edicio e = taskManager.getEdicions().get(taskManager.edicioEnCurs(CURRENT_YEAR));
            int state = e.getCurrentState();
            int totalJugadors = e.getTotalJugadors();

            // Si es el primer cop que executem l'edicio registra els jugadors
            if (e.getCurrentState() == 0) {
                System.out.println();
                for (int i = 0; i < totalJugadors; i++) {
                    String name = vista .askPlayersName(i+1, totalJugadors);

                    Jugador jugador = new Enginyer(CURRENT_YEAR, name, 5);
                    taskManager.creaJugador(jugador);
                }
                taskManager.escriure();
            }

            // Comprova si n'hi ha jugadors amb PI positius
            if (taskManager.quedenJugadorsAmbPunts(CURRENT_YEAR)) {
                int[] proves = e.getProves();
                for (; state < proves.length; state++) {
                    // Executa la prova
                    Prova p = taskManager.getProves().get(proves[state]);
                    vista.showRunningTrial(state + 1, p.getNom());
                    p.executarProva(state + 1, totalJugadors, CURRENT_YEAR, taskManager.getJugadors(), this);
                    System.out.println();
                    taskManager.cambiarTipusJugadors(CURRENT_YEAR, this);
                    e.setCurrentState(e.getCurrentState() + 1);
                    taskManager.escriure();

                    if (!taskManager.quedenJugadorsAmbPunts(CURRENT_YEAR)) {
                        vista.showPlayersLost(CURRENT_YEAR);
                        break;
                    } else if (state < proves.length - 1) {
                        if (!vista.askIfContinue()) {
                            vista.showGameStopped();
                            break;
                        }
                    }
                }

                if (taskManager.quedenJugadorsAmbPunts(CURRENT_YEAR) && state == proves.length) {
                    vista.showPlayersWon(CURRENT_YEAR);
                    taskManager.reiniciarEdicio(CURRENT_YEAR);
                }

                // Save the status of the edition and players
                taskManager.escriure();
            } else {
                vista.showPlayersLost(CURRENT_YEAR);
                taskManager.reiniciarEdicio(CURRENT_YEAR);
            }
        } else {
            vista.showAbsenceOfEditions(CURRENT_YEAR);
        }
    }

    /**
     * Método que sirve para mostrar el master
     * @param title String con el título del master
     * @param creditsSuperats int con los creditos superados
     * @param credits int con los creditos totaltes
     */
    public void showMasterStudy(String title, int creditsSuperats, int credits){
        vista.showMasterStudy(title, creditsSuperats, credits);
    }

    /**
     * Método que sirve para mostrar el master fallado
     * @param pi int con la puntuacion
     */
    public void showMasterFallat(int pi){
        vista.showMasterFallat(pi);
    }

    /**
     * Método que sirve para mostrar el master superado
     * @param pi  int con la puntuacion
     */
    public void showMasterSuperat(int pi){
        vista.showMasterSuperat(pi);
    }
    /**
     * Método que sirve para mostrar el presupuesto superado
     */
    public void showPresupostSuperat(){
        vista.showPresupostSuperat();
    }
    /**
     * Método que sirve para mostrar el presupuesto fallado
     */
    public void showPresupostFallat(){
        vista.showPresupostFallat();
    }

    /**
     * Método que sirve para mostrar los puntos ganados
     * @param nom String con el nombre
     * @param pi int con la puntuacion
     */
    public void showPresupostPuntsGuanyats(String nom, int pi){
        vista.showPresupostPuntsGuanyats(nom, pi);
    }

    /**
     * Método que sirve para mostrar el doctor superado
     * @param nom String con el nombre
     * @param pi int con la puntuacion
     */
    public void showDoctorSuperat(String nom, int pi){
        vista.showDoctorSuperat(nom, pi);
    }

    /**
     * Método que sirve para mostrar el doctor fallado
     * @param nom String con el nombre
     * @param pi int con la puntuacion
     */
    public void showDoctorFallat(String nom, int pi){
        vista.showDoctorFallat(nom, pi);
    }

    /**
     * Método que sirve para mostrar la submission
     * @param title String con el titulo
     */
    public void showSubmission(String title){
        vista.showSubmission(title);
    }

    /**
     * Método que sirve para mostrar cambio a master
     * @param nom String con el nombre
     */
    public void showEnginyerCanviaMaster(String nom){
        vista.showEnginyerCanviaMaster(nom);
    }

    /**
     * Método que sirve para mostrar cambio a doctor
     * @param nom String con el nombre
     */
    public void showMasterCanviaDoctor(String nom){
        vista.showMasterCanviaDoctor(nom);
    }
}