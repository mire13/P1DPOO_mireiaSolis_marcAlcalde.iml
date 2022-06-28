package persistance;

import business.Edicio;
import business.Jugador;
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

    /**
     * Método que sirve para chekear el path y escribir en CSV
     * @param jugador LinkedList con la informacion
     */
    // Escriure
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

    /**
     * Método que sirve para leer del fichero JSON
     * @return String con la informacion
     */
    public LinkedList<Jugador> llegir() {
        try {
            FileReader fr = new FileReader((PATH));
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(fr);
            Jugador[] info = gson.fromJson(reader,Jugador[].class);
            if (info != null) {
                return new LinkedList<>(Arrays.asList(info));
            } else {
                return null;
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
