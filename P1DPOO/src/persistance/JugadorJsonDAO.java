package persistance;

import business.Edicio;
import business.Jugador;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Clase que contiene los métodos implementados por los ficheros de jugadores
 */
public class JugadorJsonDAO implements JugadorDAO{

    private final String PATH = System.getProperty("user.dir") + "/files/";

    /**
     * Método que sirve para chekear el path
     */
    @Override
    public void checkPath() {
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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(PATH);
            gson.toJson(info, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que sirve para leer del fichero JSON
     * @return String con la informacion
     */
    public String[] llegir() {
        try {
            FileReader fr = new FileReader(new File(PATH));
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(fr);
            String[] info = gson.fromJson(reader,String[].class);
            return info;
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }


}
