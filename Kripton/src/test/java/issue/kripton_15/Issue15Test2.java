/**
 * 
 */
package issue.kripton_15;

import issue.IssueBaseTest;
import issue.kripton_15.KriptonMap.KriptonEntry;

import org.junit.Before;


/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue15Test2 extends IssueBaseTest<KriptonMap> {

	@Before
	public void setup()
	{
		beanInput=new KriptonMap();
		
		beanInput.list.add(new KriptonEntry("hello1", "content1"));
		beanInput.list.add(new KriptonEntry("hello2", "content2"));
		
	}

}
