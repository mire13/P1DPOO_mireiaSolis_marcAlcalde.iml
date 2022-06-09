package persistance;

import business.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;


public class ProvaDAO {
    private LinkedList<Prova> listProves;
    private final String PATH = System.getProperty("user.dir") + "/files/";

    private final static String PATH_PUBLICACIO_LLISTA = "P1DPOO/files/publicacioLlista.json";
    private final static String PATH_ESTUDI_LLISTA = "P1DPOO/files/estudiLlista.json";
    private final static String PATH_TESI_LLISTA = "P1DPOO/files/tesiLlista.json";
    private final static String PATH_PRESUPOST_LLISTA = "P1DPOO/files/presupostLlista.json";

    private LinkedList<ProvaPublicacio> provaPublicacio;
    private LinkedList<ProvaEstudiMaster> provaEstudiMaster;
    private LinkedList<ProvaTesiDoctoral> provaTesiDoctoral;
    private LinkedList<ProvaPresupost> provaPresupost;

    public ProvaDAO (boolean isCSV) {
        listProves = new LinkedList<>();
        if (!isCSV) {
            //Publicacio Article
            llegeixPublicacioJSON();

            // Estudi Master
            llegeixEstudiMasterJSON();

            // Defensa Tesi
            llegeixTesiDoctoralJSON();

            //Sollicitud Pressupost
            llegeixPresupostJSON();
        }
    }

    public void llegeixTesiDoctoralJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        provaTesiDoctoral = new LinkedList<>();
        try {
            ProvaTesiDoctoral[] arrayTesis = gson.fromJson(gson.newJsonReader(new FileReader(PATH_TESI_LLISTA)), ProvaTesiDoctoral[].class);
            if (arrayTesis != null) {
                provaTesiDoctoral = new LinkedList<>(Arrays.asList(arrayTesis));
                listProves.addAll(provaTesiDoctoral);
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void llegeixPresupostJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        provaPresupost = new LinkedList<>();
        try {
            ProvaPresupost[] arrayPres = gson.fromJson(gson.newJsonReader(new FileReader(PATH_PRESUPOST_LLISTA)), ProvaPresupost[].class);
            if (arrayPres != null) {
                provaPresupost = new LinkedList<>(Arrays.asList(arrayPres));
                listProves.addAll(provaPresupost);
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void llegeixEstudiMasterJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        provaEstudiMaster = new LinkedList<>();
        try {
            provaEstudiMaster = new LinkedList<>(Arrays.asList(gson.fromJson(gson.newJsonReader(new FileReader(PATH_ESTUDI_LLISTA)), ProvaEstudiMaster[].class)));
            listProves.addAll(provaEstudiMaster);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void llegeixPublicacioJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        provaPublicacio = new LinkedList<>();
        try {
            provaPublicacio = new LinkedList<>(Arrays.asList(gson.fromJson(gson.newJsonReader(new FileReader(PATH_PUBLICACIO_LLISTA)), ProvaPublicacio[].class)));
            listProves.addAll(provaPublicacio);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private void checkPath() {
        File dir = new File(PATH);
        if (!dir.exists())
            dir.mkdirs();
    }

    // Escriure
    public void escriure(String[] info) {
        checkPath();

        escriureCSV(info);
    }

    public void escriureCSV(String[] info) {
        try {
            // Obre el fitxer en mode d'escriptura
            FileWriter file = new FileWriter(PATH + "proves.csv");

            // Itera per cada linea donada i registrala a l'arxiu CSV
            for (int i = 0; i < info.length; i++)
                file.write(info[i] + "\n");

            // Tanca el fitxer
            file.close();

        } catch (IOException e) {
            System.out.println("An IO error occurred.");
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
            File f = new File(PATH + "proves.csv");

            if (f.exists()) {
                // Obre el fitxer per llegir
                FileReader file = new FileReader(PATH + "proves.csv");
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

        // Convertir la llista a un array i retornar
        String[] linesArray = new String[lines.size()];
        lines.toArray(linesArray);
        return linesArray;
    }


    public LinkedList<Prova> llegirJSON() {
        LinkedList<Prova> list = listProves;
        return list;
    }

    public void escriureJSON(LinkedList<Prova> proves) {
        String fileName = "";

        switch (proves.get(proves.size() - 1).getClass().getSimpleName()) {
            case "ProvaEstudiMaster" -> {
                fileName = PATH_ESTUDI_LLISTA;
                provaEstudiMaster.add((ProvaEstudiMaster) proves.get(proves.size() - 1));
            }
            case "ProvaPresupost" -> {
                fileName = PATH_PRESUPOST_LLISTA;
                provaPresupost.add((ProvaPresupost) proves.get(proves.size() - 1));
            }
            case "ProvaPublicacio" -> {
                fileName = PATH_PUBLICACIO_LLISTA;
                provaPublicacio.add((ProvaPublicacio) proves.get(proves.size() - 1));
            }
            case "ProvaTesiDoctoral" -> {
                fileName = PATH_TESI_LLISTA;
                provaTesiDoctoral.add((ProvaTesiDoctoral) proves.get(proves.size() - 1));
            }
        }
        actualizaJSON(fileName);

    }

    public void actualizaJSON (String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter fw = new FileWriter(filename);
            switch (filename) {
                case PATH_PUBLICACIO_LLISTA -> gson.toJson(provaPublicacio, fw);
                case PATH_ESTUDI_LLISTA -> gson.toJson(provaEstudiMaster, fw);
                case PATH_TESI_LLISTA -> gson.toJson(provaTesiDoctoral, fw);
                case PATH_PRESUPOST_LLISTA -> gson.toJson(provaPresupost, fw);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminaProvaJSON(int i) {
        String fileName = "";

        switch (listProves.get(i).getClass().getSimpleName()) {
            case "ProvaEstudiMaster" -> {
                fileName = PATH_ESTUDI_LLISTA;
                provaEstudiMaster.remove((ProvaEstudiMaster) listProves.get(i));
            }
            case "ProvaPresupost" -> {
                fileName = PATH_PRESUPOST_LLISTA;
                provaPresupost.remove((ProvaPresupost) listProves.get(i));
            }
            case "ProvaPublicacio" -> {
                fileName = PATH_PUBLICACIO_LLISTA;
                provaPublicacio.remove((ProvaPublicacio) listProves.get(i));
            }
            case "ProvaTesiDoctoral" -> {
                fileName = PATH_TESI_LLISTA;
                provaTesiDoctoral.remove((ProvaTesiDoctoral) listProves.get(i));
            }
        }
        listProves.remove(i);
        actualizaJSON(fileName);
    }
}
