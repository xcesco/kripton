package all;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderFactory.ReaderType;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

public abstract class IssueBaseTest<E> extends BaseTest {

	protected E beanInput;
	
	protected E beanOutput;

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
		beanOutput = (E) reader.read(beanInput.getClass(), FIS);
		FIS.close();
		assertReflectionEquals(input, beanOutput);
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
		BinderOptions format=BinderOptions.build().indent(false).useApostrophe(true);
		BinderWriter writer = BinderFactory.getJSONWriter(format);
		BinderReader reader = BinderFactory.getJSONReader();

		check(beanInput, writer, reader);
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
		BinderOptions format=BinderOptions.build().indent(true).useApostrophe(true);
		BinderWriter writer = BinderFactory.getJSONWriter(format);
		BinderReader reader = BinderFactory.getJSONReader();

		check(beanInput, writer, reader);
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
		BinderOptions format=BinderOptions.build().indent(false).useApostrophe(true);
		BinderWriter writer = BinderFactory.getXMLWriter(format);
		BinderFactory.readerType = ReaderType.DOM;
		BinderReader reader = BinderFactory.getXMLReader(BinderOptions.build().indent(false));

		check(beanInput, writer, reader);
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
		BinderOptions format=BinderOptions.build().indent(true).useApostrophe(true);
		BinderWriter writer = BinderFactory.getXMLWriter(format);
		BinderFactory.readerType = ReaderType.DOM;
		BinderReader reader = BinderFactory.getXMLReader(BinderOptions.build().indent(false));

		check(beanInput, writer, reader);
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
		BinderOptions format=BinderOptions.build().indent(false);
		BinderWriter writer = BinderFactory.getXMLWriter(format);
		BinderFactory.readerType = ReaderType.SAX;
		BinderReader reader = BinderFactory.getXMLReader();

		check(beanInput, writer, reader);
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
		BinderOptions format=BinderOptions.build().indent(true);
		BinderWriter writer = BinderFactory.getXMLWriter(format);
		BinderFactory.readerType = ReaderType.SAX;
		BinderReader reader = BinderFactory.getXMLReader();

		check(beanInput, writer, reader);
	}
}
