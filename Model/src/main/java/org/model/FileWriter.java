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

    public void write(byte[] bytesArray) throws Exception {
        try(FileOutputStream fis = new FileOutputStream(file)) {
            fis.write(bytesArray);
        }
    }

    public void writeBigInteger(BigInteger[] bigIntegers) throws Exception {
        try(BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(file))) {
            for(BigInteger bigInteger : bigIntegers){
                writer.write(bigInteger.toString());
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
