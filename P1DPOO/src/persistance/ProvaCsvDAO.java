package persistance;

import business.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ProvaCsvDAO implements ProvaDAO{
    private final static String PATH_PROVES_CSV = "P1DPOO/files/proves.csv";

    public ProvaCsvDAO() {
        leer();
    }

    @Override
    public void escribir(LinkedList<Prova> proves) {
        String[] info = new String[proves.size()];

        for (int i = 0; i < proves.size(); i++) {
            info[i] = proves.get(i).getInfoCSV();
        }

        try {
            // Obre el fitxer en mode d'escriptura
            FileWriter file = new FileWriter(PATH_PROVES_CSV);

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

    @Override
    public LinkedList<Prova> leer() {
        LinkedList<Prova> proves = new LinkedList<>();
        // Comprova si l'arxiu existeix
        ArrayList<String> lines = new ArrayList<>();
        try {
            File f = new File(PATH_PROVES_CSV);

            if (f.exists()) {
                // Obre el fitxer per llegir
                FileReader file = new FileReader(PATH_PROVES_CSV);
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

        for (int i = 0; i < linesArray.length; i++) {
            // Separa la linia per cada coma
            String[] line = linesArray[i].split(",");

            // Converteix cada element en el tipus de dada corresponent
            String type = line[0];

            if (type.equals("Prova Publicacio")) {
                String name = line[1];
                String journal = line[2];
                String quartile = line[3];
                int acceptance = Integer.parseInt(line[4]);
                int revision = Integer.parseInt(line[5]);
                int rejection = Integer.parseInt(line[6]);

                // Afegeix la nova prova a la llista
                proves.add(new ProvaPublicacio(name, journal, quartile, acceptance, revision, rejection));

            } else if (type.equals("Prova Estudi Master")) {
                String name = line[1];
                String masterNom = line[2];
                int credits = Integer.parseInt(line[3]);
                int probabilitat = Integer.parseInt(line[4]);

                // Afegeix la nova prova a la llista
                proves.add(new ProvaEstudiMaster(name, masterNom, credits, probabilitat));

            } else if (type.equals("Prova Tesi Doctoral")) {
                String name = line[1];
                String camp = line[2];
                int dificultat = Integer.parseInt(line[3]);

                // Afegeix la nova prova a la llista
                proves.add(new ProvaTesiDoctoral(name, camp, dificultat));

            } else if (type.equals("Prova Presupost")) {
                String name = line[1];
                String entitat = line[2];
                int presupost = Integer.parseInt(line[3]);

                // Afegeix la nova prova a la llista
                proves.add(new ProvaPresupost(name, entitat, presupost));
            }
        }
        return proves;
    }

    @Override
    public void elimina(LinkedList<Prova> proves, int i) {
        proves.remove(i);
        escribir(proves);
    }


}
