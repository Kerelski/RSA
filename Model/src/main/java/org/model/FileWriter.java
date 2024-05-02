package org.model;

import java.io.*;
import java.math.BigInteger;

public class FileWriter {

    private String path;
    private File file;
    public FileWriter(String path){
        this.path = path;
        file = new File(path);

    }

    public void writeKeys(BigInteger[] keys) throws Exception {
        try(BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(file))) {
            for(BigInteger key : keys){
                writer.write(key.toString());
                writer.newLine();
            }
        }catch (IOException e){
            System.out.println("Blad zapisu do pliku");
        }
    }

    public void setPath(String path) {
        this.path = path;
    }
}
