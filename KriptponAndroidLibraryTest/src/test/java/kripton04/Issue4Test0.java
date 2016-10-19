/**
 * 
 */
package kripton04;

import java.net.MalformedURLException;

import org.junit.Before;

import all.IssueBaseTest;

/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue4Test0 extends IssueBaseTest<Bean0> {
	

	@Before
	public void setup() throws MalformedURLException
	{
		beanInput=new Bean0();
		
		Bean0.fieldStatic="vaaa";
	}
}
