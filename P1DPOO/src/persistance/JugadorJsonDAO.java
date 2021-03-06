package persistance;

import business.Edicio;
import business.Enginyer;
import business.Jugador;
import business.ProvaTesiDoctoral;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Clase que contiene los métodos implementados por los ficheros de jugadores
 */
public class JugadorJsonDAO implements JugadorDAO{
    private LinkedList<Jugador> lista;
    private final String PATH = "P1DPOO/files/jugadors.json";

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

    @Override
    public void escriure(LinkedList<Jugador> jugador) {
        checkPath();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter(PATH);
            gson.toJson(jugador, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LinkedList<Jugador> llegir() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        lista = new LinkedList<>();
        try {
            Enginyer[] jugadores = gson.fromJson(gson.newJsonReader(new FileReader(PATH)), Enginyer[].class);
            if (jugadores != null) {
                lista = new LinkedList<>(Arrays.asList(jugadores));
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        /*
        try {
            FileReader fr = new FileReader((PATH));
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(fr);
            Jugador[] info = gson.fromJson(reader,Jugador[].class);
            if (info != null) {
                return new LinkedList<>(Arrays.asList(info));
            } else {
                return new LinkedList<>();
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;*/

       return lista;

    }
}
