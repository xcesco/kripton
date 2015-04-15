/**
 * 
 */
package kripton15;

import java.io.IOException;
import java.util.Currency;
import java.util.Locale;

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
public class Issue15Test8 extends IssueBaseTest<Bean8<String, String, String>> {

	@Before
	public void setup()
	{
		beanInput=new Bean8<String, String, String>();
		
		Level1 level1_1=new Level1();
		level1_1.name="index0";
		level1_1.info=new Level2();
		level1_1.info.local=Locale.CANADA;
		level1_1.info.currency=Currency.getAvailableCurrencies().iterator().next();
		
		Level1 level1_2=new Level1();
		level1_2.name=null;
		level1_2.info=new Level2();
		level1_2.info.local=Locale.CANADA;
		level1_2.info.currency=Currency.getAvailableCurrencies().iterator().next();
		
		Level1 level1_3=new Level1();
		level1_3.name="index0";
		level1_3.info=new Level2();
		level1_3.info.local=null;
		level1_3.info.currency=null;
		
	//	beanInput.map.put(0, level1_1);
//		beanInput.map.put(1, level1_2);
		beanInput.mapField.put("ciao", "ss");
		//beanInput.map.put(3, level1_3);
		
		
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
