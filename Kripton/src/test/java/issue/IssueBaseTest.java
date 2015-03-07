package issue;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderFactory.ReaderType;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

public class IssueBaseTest<E> {
	
	protected Logger logger=Logger.getAnonymousLogger();
	
	protected E bean;		

	public void check(E input, BinderWriter writer, BinderReader reader) throws WriterException, MappingException, ReaderException {
		String buffer = writer.write(input);
		System.out.println(buffer);

		Object output = reader.read(input.getClass(), buffer);
		String buffer2 = writer.write(output);
		System.out.println(buffer2);

		// assert string and objects with reflection
		Assert.assertTrue(buffer.equals(buffer2));
		assertReflectionEquals(input, output);
	}
	
	@Test
	public void testStandard() throws WriterException, MappingException, ReaderException
	{
		// testJSON() 
		{
			BinderWriter writer=BinderFactory.getJSONWriter();
			BinderReader reader=BinderFactory.getJSONReader();
			
			check(bean, writer, reader);
		}

		// testXMLSAX()
		{
			BinderWriter writer=BinderFactory.getXMLWriter();
			BinderFactory.readerType=ReaderType.SAX;
			BinderReader reader=BinderFactory.getXMLReader();
			
			check(bean, writer, reader);
		}
		
		// testXMLDOM()
		{
			BinderWriter writer=BinderFactory.getXMLWriter();
			BinderFactory.readerType=ReaderType.DOM;
			BinderReader reader=BinderFactory.getXMLReader();

			check(bean, writer, reader);
		}
	}
}
