package issue;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.Options;
import com.abubusoft.kripton.BinderFactory.ReaderType;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

public abstract class IssueBaseTest<E> {

	protected Logger logger = Logger.getAnonymousLogger();

	protected E bean;

	public void check(E input, BinderWriter writer, BinderReader reader) throws WriterException, MappingException, ReaderException, IOException {

		// test string
		String buffer = writer.write(input);
		logger.log(Level.INFO, "\n"+buffer);
		Object output = reader.read(input.getClass(), buffer);
		String buffer2 = writer.write(output);
		logger.log(Level.INFO, "\n"+buffer2);

		// assert string and objects with reflection
		Assert.assertTrue(buffer.equals(buffer2));
		assertReflectionEquals(input, output);

		// test file
		File file = File.createTempFile(getClass().getSimpleName() + "-", "-" + String.valueOf((new Date()).getTime()) + ".txt");
		logger.log(Level.INFO, "test on file " + file.getAbsolutePath());
		file.deleteOnExit();
		FileOutputStream FOS = new FileOutputStream(file);
		writer.write(input, FOS);
		FOS.close();

		FileInputStream FIS = new FileInputStream(file);
		E beanWrited = (E) reader.read(bean.getClass(), FIS);
		FIS.close();
		assertReflectionEquals(input, beanWrited);
	}

	/**
	 * test JSON Unformatted
	 * 
	 * @throws WriterException
	 * @throws MappingException
	 * @throws ReaderException
	 * @throws IOException
	 */
	@Test
	public void testJSON_Packed() throws WriterException, MappingException, ReaderException, IOException {
		Options format=Options.build().indent(false).useApostrophe(true);
		BinderWriter writer = BinderFactory.getJSONWriter(format);
		BinderReader reader = BinderFactory.getJSONReader();

		check(bean, writer, reader);
	}
	
	/**
	 * test JSON formatted
	 * 
	 * @throws WriterException
	 * @throws MappingException
	 * @throws ReaderException
	 * @throws IOException
	 */
	@Test
	public void testJSON_Formatted() throws WriterException, MappingException, ReaderException, IOException {
		Options format=Options.build().indent(true).useApostrophe(true);
		BinderWriter writer = BinderFactory.getJSONWriter(format);
		BinderReader reader = BinderFactory.getJSONReader();

		check(bean, writer, reader);
	}

	/**
	 * 
	 * @throws WriterException
	 * @throws MappingException
	 * @throws ReaderException
	 * @throws IOException
	 */
	@Test
	public void testXML_PackedDOM() throws WriterException, MappingException, ReaderException, IOException {
		Options format=Options.build().indent(false).useApostrophe(true);
		BinderWriter writer = BinderFactory.getXMLWriter(format);
		BinderFactory.readerType = ReaderType.DOM;
		BinderReader reader = BinderFactory.getXMLReader(Options.build().indent(false));

		check(bean, writer, reader);
	}
	
	/**
	 * 
	 * @throws WriterException
	 * @throws MappingException
	 * @throws ReaderException
	 * @throws IOException
	 */
	@Test
	public void testXML_FormattedDOM() throws WriterException, MappingException, ReaderException, IOException {
		Options format=Options.build().indent(true).useApostrophe(true);
		BinderWriter writer = BinderFactory.getXMLWriter(format);
		BinderFactory.readerType = ReaderType.DOM;
		BinderReader reader = BinderFactory.getXMLReader(Options.build().indent(false));

		check(bean, writer, reader);
	}

	/**
	 * 
	 * @throws WriterException
	 * @throws MappingException
	 * @throws ReaderException
	 * @throws IOException
	 */
	@Test
	public void testXML_PackedSAXS() throws WriterException, MappingException, ReaderException, IOException {
		Options format=Options.build().indent(false);
		BinderWriter writer = BinderFactory.getXMLWriter(format);
		BinderFactory.readerType = ReaderType.SAX;
		BinderReader reader = BinderFactory.getXMLReader();

		check(bean, writer, reader);
	}
	
	/**
	 * 
	 * @throws WriterException
	 * @throws MappingException
	 * @throws ReaderException
	 * @throws IOException
	 */
	@Test
	public void testXML_FormattedSAXS() throws WriterException, MappingException, ReaderException, IOException {
		Options format=Options.build().indent(true);
		BinderWriter writer = BinderFactory.getXMLWriter(format);
		BinderFactory.readerType = ReaderType.SAX;
		BinderReader reader = BinderFactory.getXMLReader();

		check(bean, writer, reader);
	}
}
