/**
 * 
 */
package issue.kripton_10;

import issue.IssueBaseTest;

import java.util.Calendar;

import org.junit.Before;


/**
 * @author xcesco
 *
 */
public class Issue10Test1 extends IssueBaseTest<Bean01> {

	@Before
	public void setup()
	{
		beanInput=new Bean01();
		
		beanInput.setName("Tonj");
		beanInput.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		int[] array= {1, 2, 4};
		
		beanInput.setTickets(array);
	}
	

}
