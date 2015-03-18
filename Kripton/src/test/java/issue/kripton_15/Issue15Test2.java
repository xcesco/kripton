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
public class Issue15Test2 extends IssueBaseTest<Bean2> {

	@Before
	public void setup()
	{
		beanInput=new Bean2();
		
		beanInput.map.put(1, "content1");
		beanInput.map.put(2, "content2");
		
	}

}
