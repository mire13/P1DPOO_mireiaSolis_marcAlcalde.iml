package persistance;

import business.Edicio;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EdicioCsvDAO implements EdicioDAO{
    private final static String PATH_EDICIONS_CSV = "P1DPOO/files/edicions.csv";

    @Override
    public void escribir(ArrayList<Edicio> edicioLlista) {

    }

    @Override
    public void leer(ArrayList<Edicio> edicioLlista) {

    }
}
