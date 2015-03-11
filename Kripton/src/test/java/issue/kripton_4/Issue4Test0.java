/**
 * 
 */
package issue.kripton_4;

import issue.IssueBaseTest;

import java.net.MalformedURLException;

import org.junit.Before;

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
