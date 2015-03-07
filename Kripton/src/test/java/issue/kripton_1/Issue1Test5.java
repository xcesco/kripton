/**
 * 
 */
package issue.kripton_1;

import issue.IssueBaseTest;
import issue.kripton_1.Bean06.SubBean06;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;

/**
 * @author xcesco
 *
 */
public class Issue1Test5 extends IssueBaseTest<Bean06> {

	@Before
	public void setup()
	{
		bean=new Bean06();
		
		bean.setName("Tonj");
		bean.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		bean.setBirthday(calendar.getTime());
		SubBean06[] array= new SubBean06[3];
		array[0]=new SubBean06(new Date(), "ticket01");
		array[1]=new SubBean06(new Date(), "ticket02");
		array[2]=new SubBean06(new Date(), "ticket03");		
		
		bean.setTickets(array);
	}
	

}
