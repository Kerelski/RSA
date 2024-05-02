package org.model;

import java.util.*;
import java.math.BigInteger;

public class Keys {

    private BigInteger e;
    private BigInteger n;
    private BigInteger d;

    public Keys() {
        generateKeys();
    }

    public void generateKeys(){

        BigInteger p = BigInteger.probablePrime(1024,new Random());
        BigInteger q = BigInteger.probablePrime(1024,new Random());
        n = p.multiply(q);

        BigInteger pPhi = p.subtract(BigInteger.ONE);
        BigInteger qPhi = q.subtract(BigInteger.ONE);
        BigInteger phi = pPhi.multiply(qPhi);

        e = BigInteger.probablePrime(1024, new Random());
        while(!e.gcd(phi).equals(BigInteger.ONE)){
            e = e.nextProbablePrime();
        }
        d = e.modInverse(phi);

    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }
}
