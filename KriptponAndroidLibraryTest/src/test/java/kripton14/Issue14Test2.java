/**
 * 
 */
package kripton14;

import org.junit.Before;

import com.abubusoft.kripton.android.kripton14.Bean0;
import com.abubusoft.kripton.android.kripton14.Bean2;

import all.IssueBaseTest;


/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue14Test2 extends IssueBaseTest<Bean2> {

	@Before
	public void setup()
	{
		beanInput=new Bean2();
		
		beanInput.set.add(new Bean0("helo", 244));
		beanInput.set.add(new Bean0("helo", 244));
		
	}

}
