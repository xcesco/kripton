package org.abubu.kripton.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Test;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.binder.BinderReader;
import com.abubusoft.kripton.binder.BinderWriter;
import com.abubusoft.kripton.binder.Format;
import com.abubusoft.kripton.util.Base64;

public class BinderTest {

	@Test
	public void testChatWriter() {
		
		Format format=new Format(true);
		BinderWriter writer = BinderFactory.getJSONWriter();
		try {

			File file = new File("src/test/java/list_message.json");

			ChatMessage message = new ChatMessage();
			//message.rawValue="ciao".getBytes();
			message.setType(ChatMessageType.SAY);
			message.setUid("asdfa");
			message.setType(ChatMessageType.LIST);
			//message.rawValue=Base64.encode("bla bla bla".getBytes());

			ChatMessageArray array = new ChatMessageArray();
			array.add(message);
			array.add(message);

			System.out.println(writer.write(array));

			/*
			 * File fileOutput=new
			 * File("src/test/java/argon_settings_test.xml"); BinderWriter
			 * writer = BinderFactory.getXMLWriter(); writer.write(settings, new
			 * FileOutputStream(fileOutput));
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
