package com.chapecoence;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Clase para leer archivos excel (xlsx)
 */

public class leerExcel {
    private String path;

    /**
     * Constructor que define el valor por defecto de path
     */
    leerExcel(){
        this.path = null;
    }

    /**
     * Constructor que define el valor de path
     * @param path es el string que tiene la dirección del archivo
     */
    leerExcel(String path){
        this.path = path;
    }

    /**
     * Dado un archivo .xlsx, Se convierte en un LinkedHashMap< Key, Arraylist>
     *
     * @return
     * @throws IOException
     */
    public LinkedHashMap<String, List<String>> getHashMapFromFile() throws IOException {
        LinkedHashMap<String, List<String>> map = new LinkedHashMap<>();
        File file = new File(this.path);

        try {
            InputStream inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            boolean flag = true;
            ArrayList<String> names = new ArrayList<>();

            int n = 0;

            for (Row row: sheet) {
                List<String> temp = new ArrayList<>();
                for (Cell cell:row) {
                    if (flag){
                        map.put(cell.toString(), new ArrayList<String>());
                        names.add(cell.toString());
                    }else {
                        temp.add(cell.toString());
                    }
                }

                if (flag) flag = false;
                else{
                    System.out.println(temp.toString());
                    for (int i = 0; i < names.size(); i++){
                        map.get((String) names.get(i)).add(temp.get(i));
                    }
                }
            }

        } catch (EncryptedDocumentException e) {
            System.err.println("File encrypted " + e.getMessage());
        }catch (FileNotFoundException e){
            throw new FileNotFoundException("File not found " + e.getMessage());
        }catch (IOException e){
            throw new IOException("File damaged" + e.getMessage());
        }
        return map;
    }

}
