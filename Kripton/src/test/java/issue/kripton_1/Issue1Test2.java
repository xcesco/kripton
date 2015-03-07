/**
 * 
 */
package issue.kripton_1;

import issue.IssueBaseTest;
import issue.kripton_1.Bean03.SubBean03;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;


/**
 * @author xcesco
 *
 */
public class Issue1Test2 extends IssueBaseTest<Bean03> {

	@Before
	public void setup()
	{
		bean=new Bean03();
		
		bean.setName("Tonj");
		bean.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		bean.setBirthday(calendar.getTime());
		SubBean03[] array= new SubBean03[3];
		array[0]=new SubBean03(new Date(), "ticket01");
		array[1]=new SubBean03(new Date(), "ticket02");
		array[2]=new SubBean03(new Date(), "ticket03");		
		
		bean.setTickets(array);
	}

}
