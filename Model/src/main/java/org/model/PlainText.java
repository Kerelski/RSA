package org.model;

import java.math.BigInteger;
import java.util.Arrays;

public class PlainText {

    private byte[] messageInBytes;
    private BigInteger[] messageInBigIntegers;

    public PlainText(byte[] message, int nLength){
        messageInBytes = message;

        int wordLength = (nLength-1)/8;
        int wordCounter = (int) Math.ceil((double)message.length/wordLength) ;
        messageInBigIntegers = new BigInteger[wordCounter];

        int startIndex = 0;
        int endIndex = wordLength;
        if(endIndex > message.length) endIndex = message.length;
        for(int i =0; i<wordCounter; i++) {
            byte[] temp = Arrays.copyOfRange(message, startIndex, endIndex);
            messageInBigIntegers[i] = new BigInteger(temp);
            startIndex = endIndex;
            endIndex += wordLength;
            if (endIndex > message.length) endIndex = message.length;
        }
    }


    public byte[] getMessageInBytes() {
        return messageInBytes;
    }

    public BigInteger[] getMessageInBigIntegers() {
        return messageInBigIntegers;
    }
}
