package org.model;

import java.math.BigInteger;

public class RSA {

    public static BigInteger[] encode(BigInteger[] message, BigInteger e, BigInteger n){
        int wordCounter = message.length;
        BigInteger[] cipher = new BigInteger[wordCounter];
        for(int i = 0; i < wordCounter; i++){
            cipher[i] = message[i].modPow(e, n);
        }
        return cipher;
    }

    public static BigInteger[] decode(BigInteger[] cipher, BigInteger d, BigInteger n){
        int wordCounter = cipher.length;
        BigInteger[] plainMessage = new BigInteger[wordCounter];
        for (int i = 0; i < wordCounter; i++){
            plainMessage[i] = cipher[i].modPow(d, n);
        }
        return plainMessage;
    }
}
