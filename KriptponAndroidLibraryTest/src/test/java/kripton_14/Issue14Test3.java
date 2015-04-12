/**
 * 
 */
package kripton_14;

import java.io.IOException;

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
public class Issue14Test3 extends IssueBaseTest<Bean3> {

	@Before
	public void setup()
	{
		beanInput=new Bean3();
		
		beanInput.set.add(new Bean0("helo", 244));
		beanInput.set.add(new Bean0("helo", 244));		
	}

	/* (non-Javadoc)
	 * @see issue.IssueBaseTest#testJSON_Packed()
	 */
	@Override
	@Test(expected=MappingException.class)
	public void testJSON_Packed() throws WriterException, MappingException, ReaderException, IOException {
		super.testJSON_Packed();
	}

	/* (non-Javadoc)
	 * @see issue.IssueBaseTest#testJSON_Formatted()
	 */
	@Override
	@Test(expected=MappingException.class)
	public void testJSON_Formatted() throws WriterException, MappingException, ReaderException, IOException {
		super.testJSON_Formatted();
	}

	/* (non-Javadoc)
	 * @see issue.IssueBaseTest#testXML_PackedDOM()
	 */
	@Override
	@Test(expected=MappingException.class)
	public void testXML_PackedDOM() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_PackedDOM();
	}

	/* (non-Javadoc)
	 * @see issue.IssueBaseTest#testXML_FormattedDOM()
	 */
	@Override
	@Test(expected=MappingException.class)
	public void testXML_FormattedDOM() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_FormattedDOM();
	}

	/* (non-Javadoc)
	 * @see issue.IssueBaseTest#testXML_PackedSAXS()
	 */
	@Override
	@Test(expected=MappingException.class)
	public void testXML_PackedSAXS() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_PackedSAXS();
	}

	/* (non-Javadoc)
	 * @see issue.IssueBaseTest#testXML_FormattedSAXS()
	 */
	@Override
	@Test(expected=MappingException.class)
	public void testXML_FormattedSAXS() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_FormattedSAXS();
	}
	
	

}
