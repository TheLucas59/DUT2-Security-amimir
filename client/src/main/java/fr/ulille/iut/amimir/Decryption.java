package fr.ulille.iut.amimir;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Decryption {
	private PublicKey pubKey;
	
	public Decryption(PublicKey pubKey) {
		this.pubKey = pubKey;
	}
	
	public byte[] decrypt(String message) {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
        try {
			cipher.init(Cipher.DECRYPT_MODE, this.pubKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}  

        try {
			return cipher.doFinal(message.getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
        
        return null;
	}
}
