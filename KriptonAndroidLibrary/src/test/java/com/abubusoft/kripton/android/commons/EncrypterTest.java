package com.abubusoft.kripton.android.commons;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.os.Build;

@RunWith(RobolectricTestRunner.class)
@Config(sdk=Build.VERSION_CODES.LOLLIPOP, manifest=Config.NONE, shadows={ShadowLogger.class})
public class EncrypterTest {
	
	@Test
	public void test01() throws GeneralSecurityException, UnsupportedEncodingException
	{
		Encrypter encrypter=new Encrypter("passrw");
		
		String  input="prima prova un ble gioanro adi piggoa cosa vuodi che sia, sono aquai per fare atlebb cose che ma cazzo sd vodss";		
		String middle=encrypter.encode(input);
		String output=encrypter.decode(middle);
		
		System.out.println("Input: "+input);
		System.out.println("Middle: "+middle);
		System.out.println("Output: "+output);
	}
}

