package fr.ulille.iut.amimir;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Encryption {
	private PrivateKey privKey;
	
	public Encryption(PrivateKey privKey) {
		this.privKey = privKey;
	}

	public byte[] crypt(String message) {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
        try {
			cipher.init(Cipher.ENCRYPT_MODE, this.privKey);
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
