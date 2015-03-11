/**
 * 
 */
package issue.kripton_12;

import issue.IssueBaseTest;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;


/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue12Test1 extends IssueBaseTest<Bean1> {

	@Before
	public void setup()
	{
		bean=new Bean1();
		
		bean.setName("Hello 'Tonj'");
		bean.setSurname("Hello \"Manero\"");
		
		bean.genericElement =new BeanGeneric("title0", 25);		
		bean.genericAttribute =125;
		bean.genericListAttribute=new ArrayList<Integer>();
		bean.genericListAttribute.add(12);
		bean.genericListAttribute.add(123);
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		bean.setBirthday(calendar.getTime());
		
	}
}
