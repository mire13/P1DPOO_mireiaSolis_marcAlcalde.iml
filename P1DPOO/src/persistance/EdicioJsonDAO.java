package persistance;

import business.Edicio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Clase que contiene los métodos implementados por los ficheros de ediciones
 */
public class EdicioJsonDAO implements EdicioDAO{
    private final static String PATH_EDICIONS_JSON = "P1DPOO/files/edicions.json";

    /**
     * Constructor que lee las ediciones
     */
    public EdicioJsonDAO() {
        leer();
    }

    @Override
    public void escribir(LinkedList<Edicio> edicions) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(PATH_EDICIONS_JSON);
            gson.toJson(edicions, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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