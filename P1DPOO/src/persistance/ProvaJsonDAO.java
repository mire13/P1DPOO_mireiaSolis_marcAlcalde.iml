package persistance;

import business.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Clase que contiene los métodos implementados por los ficheros de pruebas
 */
public class ProvaJsonDAO implements ProvaDAO{
    private LinkedList<Prova> listProves;

    private final static String PATH_PUBLICACIO_LLISTA = "P1DPOO/files/publicacioLlista.json";
    private final static String PATH_ESTUDI_LLISTA = "P1DPOO/files/estudiLlista.json";
    private final static String PATH_TESI_LLISTA = "P1DPOO/files/tesiLlista.json";
    private final static String PATH_PRESUPOST_LLISTA = "P1DPOO/files/presupostLlista.json";

    private LinkedList<ProvaPublicacio> provaPublicacio;
    private LinkedList<ProvaEstudiMaster> provaEstudiMaster;
    private LinkedList<ProvaTesiDoctoral> provaTesiDoctoral;
    private LinkedList<ProvaPresupost> provaPresupost;

    /**
     * Constructor por defecto
     */
    public ProvaJsonDAO() {
        listProves = new LinkedList<>();

        //Publicacio Article
        llegeixPublicacioJSON();

        // Estudi Master
        llegeixEstudiMasterJSON();

        // Defensa Tesi
        llegeixTesiDoctoralJSON();

        //Sollicitud Pressupost
        llegeixPresupostJSON();

    }

    /**
     * Método que sirve para leer del fichero tesi doctoral JSON
     * @return String con la informacion
     */
    private void llegeixTesiDoctoralJSON() {
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

    /**
     * Método que sirve para leer del fichero presupost JSON
     * @return String con la informacion
     */
    private void llegeixPresupostJSON() {
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

    /**
     * Método que sirve para leer del fichero estudi master JSON
     * @return String con la informacion
     */
    private void llegeixEstudiMasterJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        provaEstudiMaster = new LinkedList<>();
        try {
            provaEstudiMaster = new LinkedList<>(Arrays.asList(gson.fromJson(gson.newJsonReader(new FileReader(PATH_ESTUDI_LLISTA)), ProvaEstudiMaster[].class)));
            listProves.addAll(provaEstudiMaster);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Método que sirve para leer del fichero publicacion JSON
     * @return String con la informacion
     */
    private void llegeixPublicacioJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        provaPublicacio = new LinkedList<>();
        try {
            provaPublicacio = new LinkedList<>(Arrays.asList(gson.fromJson(gson.newJsonReader(new FileReader(PATH_PUBLICACIO_LLISTA)), ProvaPublicacio[].class)));
            listProves.addAll(provaPublicacio);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Método que sirve para actualizar el JSON
     * @param filename String con el nombre del fichero
     */
    private void actualizaJSON (String filename) {
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

    @Override
    public void escribir(LinkedList<Prova> proves) {
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

    @Override
    public LinkedList<Prova> leer() {
        LinkedList<Prova> list = listProves;
        return list;
    }

    @Override
    public void elimina(LinkedList<Prova> proves, int i) {
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
