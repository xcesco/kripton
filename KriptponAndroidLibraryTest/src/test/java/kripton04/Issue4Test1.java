/**
 * 
 */
package kripton04;

import java.net.MalformedURLException;

import org.junit.Before;

import com.abubusoft.kripton.android.kripton04.Bean0;
import com.abubusoft.kripton.android.kripton04.Bean1;

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
