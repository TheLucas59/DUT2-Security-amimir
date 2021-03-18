package fr.ulille.iut.amimir;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Encryption {
	private PublicKey pubKey;
	
	public Encryption(PublicKey publicKey) {
		this.pubKey = publicKey;
	}

	public String crypt(String message) {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
        try {
			cipher.init(Cipher.ENCRYPT_MODE, this.pubKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}  

        try {
			return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes()));
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
        
        return null;
	}
}
