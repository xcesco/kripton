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
		bean=new Bean1();
		
		bean.setName("Hello 'Tonj'");
		bean.setSurname("Hello \"Manero\"");
		
		bean.bean1=new SubBean01(new Date(), "title0");		
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		bean.setBirthday(calendar.getTime());
		
	}
}
