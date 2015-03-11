/**
 * 
 */
package issue.kripton_11;

import issue.IssueBaseTest;
import issue.kripton_11.Bean1.SubBean01;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;


/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue11Test1 extends IssueBaseTest<Bean1> {

	@Before
	public void setup()
	{
		beanInput=new Bean1();
		
		beanInput.setName("Hello 'Tonj'");
		beanInput.setSurname("Hello \"Manero\"");
		
		beanInput.bean1=new SubBean01(new Date(), "title0");		
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		
	}
}
