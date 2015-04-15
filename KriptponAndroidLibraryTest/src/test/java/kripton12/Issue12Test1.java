/**
 * 
 */
package kripton12;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;

import all.IssueBaseTest;


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
		beanInput=new Bean1();
		
		beanInput.setName("Hello 'Tonj'");
		beanInput.setSurname("Hello \"Manero\"");
		
		beanInput.genericElement =new BeanGeneric("title0", 25);		
		beanInput.genericAttribute =125;
		beanInput.genericListAttribute=new ArrayList<Integer>();
		beanInput.genericListAttribute.add(12);
		beanInput.genericListAttribute.add(123);
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		
	}
}
