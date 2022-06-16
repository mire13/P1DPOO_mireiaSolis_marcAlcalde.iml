package persistance;

import business.Edicio;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class EdicioCsvDAO implements EdicioDAO{
    private final static String PATH_EDICIONS_CSV = "P1DPOO/files/edicions.csv";


    @Override
    public void escribir(LinkedList<Edicio> edicioLlista) {

    }

    @Override
    public LinkedList<Edicio> leer() {
        LinkedList<Edicio> edicions = new LinkedList<>();
        ArrayList<String> lines = new ArrayList<>();
        try {
            File f = new File(PATH_EDICIONS_CSV);

            if (f.exists()) {
                // Obre el fitxer per llegir
                FileReader file = new FileReader(PATH_EDICIONS_CSV);
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
            int any = Integer.parseInt(line[0]);
            int jugNum = Integer.parseInt(line[1]);
            int state = Integer.parseInt(line[2]);
            int provesNum = Integer.parseInt(line[3]);

            int[] proves = new int[provesNum];
            for(int p=0; p<provesNum; p++) {
                proves[p] = Integer.parseInt(line[4+p]);
            }

            // Afegeix la nova prova a la llista
            Edicio e = new Edicio(any, jugNum, provesNum, proves);
            e.setCurrentState(state);
            edicions.add(e);
        }
        return edicions;
    }
}
