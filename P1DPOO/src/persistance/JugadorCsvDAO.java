package persistance;

import business.Edicio;
import business.Jugador;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class JugadorCsvDAO implements JugadorDAO{

    private final String PATH = "P1DPOO/files/jugadors.csv";

    @Override
    public void checkPath(){
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    /**
     * Método que sirve para escribir en CSV
     * @param jugadors LinkedList con la informacion
     */
    public void escriure(LinkedList<Jugador> jugadors){
        checkPath();
        try {
            // Obre el fitxer en mode d'escriptura
            FileWriter file = new FileWriter(PATH);

            // Itera per cada linea donada i registrala a l'arxiu CSV
            for (Jugador s : jugadors) {
                file.write(s + "\n");
            }
            // Tanca el fitxer
            file.close();

        } catch (IOException e) {
            System.out.println("An IO error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    /**
     * Método que sirve para leer del fichero CSV
     * @return LinkedList con la informacion
     */
    public LinkedList<Jugador> llegir() {
        checkPath();
        // Comprova si l'arxiu existeix
        ArrayList<String> lines = new ArrayList<>();
        try {
            File f = new File(PATH);

            if (f.exists()) {
                // Obre el fitxer per llegir
                FileReader file = new FileReader(PATH);
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

}
