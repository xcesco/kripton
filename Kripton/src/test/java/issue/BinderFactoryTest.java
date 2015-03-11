/**
 * 
 */
package issue;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import org.junit.Test;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderFactory.ReaderType;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.Options;

/**
 * @author xcesco
 *
 */
public class BinderFactoryTest {

	@Test
	public void test() {
		{
			BinderWriter writer1 = BinderFactory.getJSONWriter();
			BinderWriter writer2 = BinderFactory.getJSONWriter(Options.build());

			assertReflectionEquals(writer1, writer2);
		}

		{
			BinderWriter writer1 = BinderFactory.getXMLWriter();
			BinderWriter writer2 = BinderFactory.getXMLWriter(Options.build());

			assertReflectionEquals(writer1, writer2);
		}
		
		{
			BinderReader reader1 = BinderFactory.getJSONReader();
			BinderReader reader2 = BinderFactory.getJSONReader(Options.build().encoding(Options.ENCODING_UTF_8));

			assertReflectionEquals(reader1, reader2);
		}

		{
			BinderReader reader1 = BinderFactory.getXMLReader();
			BinderReader reader2 = BinderFactory.getXMLReader(Options.build());

			assertReflectionEquals(reader1, reader2);
		}
		
		{
			BinderFactory.readerType=ReaderType.DOM;
			BinderReader reader1 = BinderFactory.getXMLReader();
			BinderReader reader2 = BinderFactory.getXMLReader(Options.build());

			assertReflectionEquals(reader1, reader2);
		}
		
		{
			BinderFactory.readerType=ReaderType.SAX;
			BinderReader reader1 = BinderFactory.getXMLReader();
			BinderReader reader2 = BinderFactory.getXMLReader(Options.build().encoding(Options.ENCODING_UTF_8));

			assertReflectionEquals(reader1, reader2);
		}

	}
}
