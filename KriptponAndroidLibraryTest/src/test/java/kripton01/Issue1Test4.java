/**
 * 
 */
package kripton01;

import java.util.Calendar;

import org.junit.Before;

import all.IssueBaseTest;


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
