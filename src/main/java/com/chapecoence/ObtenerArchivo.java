package com.chapecoence;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase para obtener el archivo .equ
 */
public class ObtenerArchivo {
    private ArrayList<String> ecuaciones;

    /**
     * Constructor de la clase
     */
    public ObtenerArchivo() {
        this.ecuaciones = new ArrayList<>();
    }

    public FileReader getFile(String ruta) throws FileNotFoundException {
        File archivo = new File(ruta);
        FileReader fr = new FileReader(archivo);
        return fr;
    }

    public ArrayList<String> leerEqu(String ruta) throws FileNotFoundException {

        FileReader fr = this.getFile(ruta);
        BufferedReader br = new BufferedReader(fr);
        String linea=null;

        while(true) {
            try {
                linea = br.readLine();
                if (linea!= null) {
                    this.ecuaciones.add(linea);
                } else break;
            } catch (IOException e) {
                System.err.println("El archivo está dañado.");
                e.printStackTrace();
            }
        }
        return this.ecuaciones;
    }

}
