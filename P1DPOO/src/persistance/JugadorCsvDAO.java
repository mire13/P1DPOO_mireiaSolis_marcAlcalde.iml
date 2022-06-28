package persistance;

import business.*;

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
     * @param info String con la informacion
     */
    public void escriure(LinkedList<Jugador> jugadors){
        String[] info = new String[jugadors.size()];
        int i = 0;

        for (Jugador p : jugadors) {
            String aux = "";

            aux += p.getType() + ",";
            aux += p.getAny() + ",";
            aux += p.getNom() + ",";
            aux += p.getPi();

            info[i] = aux;
            i++;
        }
        try {
            // Obre el fitxer en mode d'escriptura
            FileWriter file = new FileWriter(PATH);

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

    @Override
    /**
     * Método que sirve para leer del fichero CSV
     * @return String con la informacion
     **/
    public LinkedList<Jugador> llegir() {
        LinkedList<Jugador> jugadors = new LinkedList<>();
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

        // Itera per cada linia de l'arxiu
        for (int i = 0; i < linesArray.length; i++) {
            // Separa la linia per cada coma
            String[] line = linesArray[i].split(",");

            // Converteix cada element en el tipus de dada corresponent
            String tipus = line[0];
            int any = Integer.parseInt(line[1]);
            String nom = line[2];
            int PI = Integer.parseInt(line[3]);

            if (tipus.equals("Enginyer"))
                jugadors.add(new Enginyer(any, nom, PI));
            if (tipus.equals("Master"))
                jugadors.add(new Master(any, nom, PI));
            if (tipus.equals("Doctor"))
                jugadors.add(new Doctor(any, nom, PI));
        }
        return jugadors;
    }
}
