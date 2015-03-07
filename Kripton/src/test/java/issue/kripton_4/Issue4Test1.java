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
public class Issue4Test1 extends IssueBaseTest<Bean1> {
	

	@Before
	public void setup() throws MalformedURLException
	{
		bean=new Bean1();
		
		Bean0.fieldStatic="vaaa";
	}
}
