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
public class Issue1Test4 extends IssueBaseTest<Bean05> {


	@Before
	public void setup()
	{
		beanInput=new Bean05();
		
		beanInput.setName("Tonj");
		beanInput.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		Integer[] array= {1, 2, 4};
		
		beanInput.setTickets(array);
	}

}
