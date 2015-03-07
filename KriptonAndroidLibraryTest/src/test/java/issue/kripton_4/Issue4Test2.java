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
public class Issue4Test2 extends IssueBaseTest<Bean2> {
	

	@Before
	public void setup() throws MalformedURLException
	{
		bean=new Bean2();
		
		Bean0.fieldStatic="vaaa";
	}
}
