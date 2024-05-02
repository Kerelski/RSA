package org.model;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Arrays;

public class FileReader {

    private String path;
    private byte[] bytes;

    private BigInteger[] message;
    private File file;

    public FileReader(String path){
        this.path = path;
        file = new File(path);
        bytes = new byte[(int) file.length()];
    }

    public void read(int nLength) throws Exception {
        try(FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytes);
        }
        int wordLength = (nLength-1)/8;
        int wordCounter = (int) Math.ceil((double)bytes.length/wordLength) ;
        message = new BigInteger[wordCounter];

        int startIndex = 0;
        int endIndex = wordLength;
        if(endIndex > bytes.length) endIndex = bytes.length;
        for(int i =0; i<wordCounter; i++){
            byte[] temp = Arrays.copyOfRange(bytes, startIndex, endIndex);
            message[i] = new BigInteger(1, temp);
            startIndex = endIndex;
            endIndex += wordLength;
            if(endIndex > bytes.length) endIndex = bytes.length;
        }

    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public BigInteger[] getMessage() {
        return message;
    }
}
