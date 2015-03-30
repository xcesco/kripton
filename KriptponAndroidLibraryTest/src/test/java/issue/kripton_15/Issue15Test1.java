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
public class Issue15Test1 extends IssueBaseTest<Bean1> {

	@Before
	public void setup()
	{
		beanInput=new Bean1();
		
		beanInput.map.put("<hello1>", "content1");
		beanInput.map.put("<hello2>", "content2");
		beanInput.map.put("hello3", "<content3>");
		
	}

}
