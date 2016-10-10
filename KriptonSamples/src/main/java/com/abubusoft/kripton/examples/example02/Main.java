/**
 * 
 */
package com.abubusoft.kripton.examples.example02;

import java.util.Calendar;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

/**
 * @author xcesco
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws MappingException
	 * @throws WriterException
	 * @throws ReaderException
	 */
	public static void main(String[] args) throws WriterException, MappingException, ReaderException {
		Employee bean = new Employee();

		bean.setName("Tonaaaaaaaaaaj");
		bean.setSurname("Manero");

		Calendar calendar = Calendar.getInstance();
		calendar.set(1965, 6, 12);
		bean.setBirthday(calendar.getTime());

		//writeRead(bean, BinderFactory.getJSONReader(), BinderFactory.getJSONWriter(Options.build().indent(true)));
		//BinderFactory.readerType=ReaderType.SAX;
		writeRead(bean, BinderFactory.getXMLReader(), BinderFactory.getXMLWriter());

	}

	private static void writeRead(Object bean, BinderReader reader, BinderWriter writer) throws WriterException, MappingException, ReaderException {
		String buffer = writer.write(bean);
		System.out.println(buffer);

		Object bean2 = reader.read(bean.getClass(), buffer);
		String buffer2 = writer.write(bean2);
		System.out.println(buffer2);

	}

}
