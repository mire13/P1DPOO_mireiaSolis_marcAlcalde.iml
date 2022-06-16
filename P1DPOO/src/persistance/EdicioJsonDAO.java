package persistance;

import business.Edicio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Clase que contiene los métodos implementados por los ficheros de ediciones
 */
public class EdicioJsonDAO implements EdicioDAO{
    private final static String PATH_EDICIONS_JSON = "P1DPOO/files/edicions.json";

    /**
     * Constructor por defecto
     */
    public EdicioJsonDAO() {
        leer();
    }

    /**
     * Método que sirve para chekear el path y escribir en CSV
     * @param info String con la informacion
     */
    // Escriure
    public void escriure(String[] info) {
        escriureCSV(info);
    }

    /**
     * Método que sirve para escribir en CSV
     * @param info String con la informacion
     */
    public void escriureCSV(String[] info) {
        try {
            // Obre el fitxer en mode d'escriptura
            FileWriter file = new FileWriter(PATH_EDICIONS_CSV);

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
     * @param ed LinkedList de las ediciones
     */
    public void escriureJSON(LinkedList<Edicio> ed) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(PATH_EDICIONS_JSON);
            gson.toJson(ed, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que sirve para leer del fichero CSV
     * @return String con la informacion
     */
    public String[] llegirCSV() {
        // Comprova si l'arxiu existeix
        ArrayList<String> lines = new ArrayList<>();
        try {
            File f = new File(PATH_EDICIONS_CSV);

            if (f.exists()) {
                // Obre el fitxer per llegir
                FileReader file = new FileReader(PATH_EDICIONS_CSV);
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
     * @return Edicio[] con la informacion
     */
    public LinkedList<Edicio> llegirJSON() {
        return null;
    }

    @Override
    public void escribir(LinkedList<Edicio> edicioLlista) {

    }

    @Override
    public LinkedList<Edicio> leer() {
        try {
            FileReader fr = new FileReader(new File(PATH_EDICIONS_JSON));
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(fr);
            Edicio[] edicioArray = gson.fromJson(reader,Edicio[].class);
            return new LinkedList<>(Arrays.asList(edicioArray));
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}