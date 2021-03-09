package java;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Encryption {
	private PublicKey publicKey;
	
	public Encryption(String key) {
		byte[] keyBytes = key.getBytes();
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		
		KeyFactory kfPub = null;
		try {
			kfPub = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PublicKey pubKey = null;
		try {
			pubKey = kfPub.generatePublic(spec);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.publicKey = pubKey;
	}
	
	public byte[] crypt(String message) {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
        try {
			cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
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
