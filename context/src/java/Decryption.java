package java;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Decryption {
	private PrivateKey privateKey;
	
	public Decryption(String key) {
		byte[] keyBytes = key.getBytes();
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		
		KeyFactory kfPriv = null;
		try {
			kfPriv = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrivateKey privKey = null;
		try {
			privKey = kfPriv.generatePrivate(spec);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.privateKey = privKey;
	}
	
	public byte[] decrypt(String message) {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
        try {
			cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
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
