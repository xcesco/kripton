/**
 * 
 */
package kripton_4;

import java.net.MalformedURLException;

import org.junit.Before;

import all.IssueBaseTest;

/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue4Test1 extends IssueBaseTest<Bean1> {
	

	@Before
	public void setup() throws MalformedURLException
	{
		beanInput=new Bean1();
		
		Bean0.fieldStatic="vaaa";
	}
}
