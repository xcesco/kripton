/**
 * 
 */
package issue.kripton_1;

import issue.IssueBaseTest;

import java.util.Calendar;

import org.junit.Before;


/**
 * @author xcesco
 *
 */
public class Issue1Test1 extends IssueBaseTest<Bean02> {

	@Before
	public void setup()
	{
		bean=new Bean02();
		
		bean.setName("Tonj");
		bean.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		bean.setBirthday(calendar.getTime());
		Integer[] array= {1, 2, 4};
		
		bean.setTickets(array);
	}
	

}
