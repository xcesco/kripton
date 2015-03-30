/**
 * 
 */
package issue.kripton_15;

import java.util.Currency;
import java.util.Locale;

import issue.IssueBaseTest;

import org.junit.Before;


/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue15Test4 extends IssueBaseTest<Bean4> {

	@Before
	public void setup()
	{
		beanInput=new Bean4();
		
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
		
		beanInput.map.put("key0", level1_1);
		beanInput.map.put("key1", level1_2);
		beanInput.map.put("key3", level1_3);
		
		beanInput.list.add(level1_1);
		beanInput.list.add(level1_3);
		
		beanInput.set.add(level1_1);
		beanInput.set.add(level1_3);
		
		
	}

}
