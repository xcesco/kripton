/**
 * 
 */
package issue.kripton_15;

import issue.IssueBaseTest;

import org.junit.Before;


/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue15Test3 extends IssueBaseTest<Bean3> {

	@Before
	public void setup()
	{
		beanInput=new Bean3();
		
		beanInput.map.put(new Bean0("key1", 12), new Bean0("value1", 23));
		beanInput.map.put(new Bean0("key2", 23), new Bean0("value2", 34));
		beanInput.map.put(new Bean0("key3", 34), new Bean0("value3", 45));
		
		
	}

}
