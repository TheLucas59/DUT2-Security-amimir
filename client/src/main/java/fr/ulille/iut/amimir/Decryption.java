package fr.ulille.iut.amimir;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Decryption {
	private PrivateKey privKey;
	
	public Decryption(PrivateKey privKey) {
		this.privKey = privKey;
	}
	
	public byte[] decrypt(String message) {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
        try {
			cipher.init(Cipher.DECRYPT_MODE, this.privKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}  

        try {
        	System.out.println("trying to decrypt :\n" + message.getBytes());
			return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
        
        return null;
	}
}
