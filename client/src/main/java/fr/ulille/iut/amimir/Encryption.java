package fr.ulille.iut.amimir;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

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
			cipher = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
        try {
			cipher.init(Cipher.ENCRYPT_MODE, this.pubKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}  

        try {
			return new String(cipher.doFinal(message.getBytes()), StandardCharsets.UTF_8);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
        
        return null;
	}
}
