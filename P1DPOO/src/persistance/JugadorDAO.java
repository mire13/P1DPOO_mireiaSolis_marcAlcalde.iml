package persistance;

import business.Edicio;
import business.Jugador;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que contiene los métodos implementados por los ficheros de jugadores
 */
public class JugadorDAO {

    private final String PATH = System.getProperty("user.dir") + "/files/";

    /**
     * Método que sirve para chekear el path
     */
    private void checkPath() {
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Método que sirve para chekear el path y escribir en CSV
     * @param info String con la informacion
     */
    // Escriure
    public void escriure(String[] info) {
        checkPath();
        escriureCSV(info);
    }

    /**
     * Método que sirve para escribir en CSV
     * @param info String con la informacion
     */
    public void escriureCSV(String[] info) {
        try {
            // Obre el fitxer en mode d'escriptura
            FileWriter file = new FileWriter(PATH + "jugadors.csv");

            // Itera per cada linea donada i registrala a l'arxiu CSV
            for (String s : info) {
                file.write(s + "\n");
            }
            // Tanca el fitxer
            file.close();

        } catch (IOException e) {
            System.out.println("An IO error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Método que sirve para escribir en JSON
     * @param info String con la información
     */
    public void escriureJSON(String[] info) {

    }

    /**
     * Método que sirve para leer del fichero CSV
     * @return String con la informacion
     */
    public String[] llegirCSV() {
        // Comprova si l'arxiu existeix
        ArrayList<String> lines = new ArrayList<>();
        try {
            File f = new File(PATH + "jugadors.csv");

            if (f.exists()) {
                // Obre el fitxer per llegir
                FileReader file = new FileReader(PATH + "jugadors.csv");
                Scanner scan = new Scanner(file);

                // Itera per cada linea i afegeix-la a la lista
                while (scan.hasNextLine()) {
                    lines.add(scan.nextLine());
                }

                // Tanca el fitxer i l'scanner
                file.close();
                scan.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Convert the list into an array and return
        String[] linesArray = new String[lines.size()];
        lines.toArray(linesArray);

        return linesArray;
    }

    /**
     * Método que sirve para leer del fichero JSON
     * @return String con la informacion
     */
    public String[] llegirJSON() {
        return null;
    }


}
