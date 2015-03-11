/**
 * 
 */
package issue.kripton_14;

import issue.IssueBaseTest;

import org.junit.Before;


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

}
