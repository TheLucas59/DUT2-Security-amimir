package fr.ulille.iut.amimir;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;

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
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
        try {
			cipher.init(Cipher.DECRYPT_MODE, this.privKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}  

        try {
        	System.out.println("trying to decrypt :\n" + message);
			return cipher.doFinal(Base64.getDecoder().decode(message));
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
        
        return null;
	}
}
