/**
 * 
 */
package kripton10;

import java.util.Calendar;

import org.junit.Before;

import com.abubusoft.kripton.android.kripton10.Bean01;

import all.IssueBaseTest;


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
