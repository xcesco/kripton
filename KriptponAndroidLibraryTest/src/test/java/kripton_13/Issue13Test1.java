/**
 * 
 */
package kripton_13;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import all.IssueBaseTest;

import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;


/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue13Test1 extends IssueBaseTest<Bean1> {

	@Before
	public void setup()
	{
		beanInput=new Bean1();
		
		beanInput.list.add(new Bean0("helo", 244));
		beanInput.list.add(new Bean0("helo", 244));
		
	}

	@Override
	@Test
	public void testJSON_Packed() throws WriterException, MappingException, ReaderException, IOException {
		super.testJSON_Packed();
		
		Assert.assertTrue(beanOutput.list.getClass()==LinkedList.class);
	}

	@Override
	@Test
	public void testJSON_Formatted() throws WriterException, MappingException, ReaderException, IOException {
		super.testJSON_Formatted();
		
		Assert.assertTrue(beanOutput.list.getClass()==LinkedList.class);
	}

	@Override
	@Test
	public void testXML_PackedDOM() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_PackedDOM();
		
		Assert.assertTrue(beanOutput.list.getClass()==LinkedList.class);
	}

	@Override
	@Test
	public void testXML_FormattedDOM() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_FormattedDOM();
		
		Assert.assertTrue(beanOutput.list.getClass()==LinkedList.class);
	}

	@Override
	@Test
	public void testXML_PackedSAXS() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_PackedSAXS();
		
		Assert.assertTrue(beanOutput.list.getClass()==LinkedList.class);
		
	}

	@Override
	@Test
	public void testXML_FormattedSAXS() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_FormattedSAXS();
		
		Assert.assertTrue(beanOutput.list.getClass()==LinkedList.class);
		
	}

}
