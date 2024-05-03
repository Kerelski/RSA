package org.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private String path;
    private byte[] bytes;

    private List<BigInteger> bigIntegers = new ArrayList<>();
    private File file;

    public FileReader(String path){
        this.path = path;
        file = new File(path);
        bytes = new byte[(int) file.length()];
    }

    public void read() throws Exception {
        try(FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytes);
        }

    }

    public void readBigIntegers() throws Exception{
        try(BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String row;
            while((row = reader.readLine())!=null){
                bigIntegers.add(new BigInteger(row));
            }
        }catch (IOException e){
            System.out.println("Blad wczytywania klucza");
        }

    }

    public byte[] getBytes() {
        return bytes;
    }

    public List<BigInteger> getBigIntegers() {
        return bigIntegers;
    }
}
