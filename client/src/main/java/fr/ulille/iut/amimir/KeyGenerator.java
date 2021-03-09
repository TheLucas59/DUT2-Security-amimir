package fr.ulille.iut.amimir;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyGenerator {
	
	public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);    
 
        return keyPairGenerator.genKeyPair();
    }
	
}
