package persistance;

import business.Edicio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;

public class EdicioDAO {
    private File file;
    private final String PATH = System.getProperty("user.dir") + "/files/";

    private void checkPath() {
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Escriure
    public void escriure(String[] info) {
        checkPath();
        escriureCSV(info);
    }

    public void escriureCSV(String[] info) {
        try {
            // Obre el fitxer en mode d'escriptura
            FileWriter file = new FileWriter(PATH + "edicions.csv");

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


    public void escriureJSON(LinkedList<Edicio> ed) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(PATH+"edicions.json");
            gson.toJson(ed, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Llegir
    public String[] llegir() {
        return llegirCSV();
    }

    public String[] llegirCSV() {
        // Comprova si l'arxiu existeix
        ArrayList<String> lines = new ArrayList<>();
        try {
            File f = new File(PATH + "edicions.csv");

            if (f.exists()) {
                // Obre el fitxer per llegir
                FileReader file = new FileReader(PATH + "edicions.csv");
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


    public Edicio[] llegirJSON() {
        try {
            FileReader fr = new FileReader(new File("P1DPOO/files/edicions.json"));
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(fr);
            Edicio[] edicioArray = gson.fromJson(reader,Edicio[].class);
            return edicioArray;


        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        return null;
    }
}
