package com.abubusoft.kripton.android.commons;

import static com.abubusoft.kripton.android.commons.AesCbcWithIntegrity.decryptString;
import static com.abubusoft.kripton.android.commons.AesCbcWithIntegrity.encrypt;
import static com.abubusoft.kripton.android.commons.AesCbcWithIntegrity.generateKeyFromPassword;
import static com.abubusoft.kripton.android.commons.AesCbcWithIntegrity.saltString;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.commons.AesCbcWithIntegrity.CipherTextIvMac;
import com.abubusoft.kripton.common.Base64;


public class Encrypter {

	// Set up secret key spec for 128-bit AES encryption and decryption
	protected SecretKeySpec sks = null;
	
	protected AesCbcWithIntegrity.SecretKeys key;

	public Encrypter(String password) throws GeneralSecurityException {
		//String salt = saltString(generateSalt());
		String salt = saltString("WTRnVzFXam9QT2NXYkR3dHd2cmtKZz09OnJ2VHkvMUE0SjhhNUNQdVVQeWJLM1NQOVRpOTYySFk3WnR".getBytes());
        //If you generated the key from a password, you can store the salt and not the key.
        //Log.i(TAG, "Salt: " + salt);
        key = generateKeyFromPassword(password, salt);			
	}
	
	public String encode(String input) throws UnsupportedEncodingException, GeneralSecurityException
	{
		CipherTextIvMac output = encrypt(input, key);
		return Base64.encode(output.toString().getBytes());		
	}

	public String encode(byte[] inputBytes)
	{
		// Encode the original data with AES
        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, sks);
            encodedBytes = c.doFinal(inputBytes);
        } catch (Exception e) {
        	e.printStackTrace();
            Logger.error("AES encryption error "+e.getMessage());
        }
        return Base64.encode(encodedBytes);
	}

	public String decode(String input) throws UnsupportedEncodingException, GeneralSecurityException {
		
		//Use the constructor to re-create the CipherTextIvMac class from the string:
		CipherTextIvMac cipherTextIvMac = new CipherTextIvMac (new String(Base64.decode(input)));
		String plainText = decryptString(cipherTextIvMac, key);
		  
		return plainText;
	}
}
