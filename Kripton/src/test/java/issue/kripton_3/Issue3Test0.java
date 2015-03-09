/**
 * 
 */
package issue.kripton_3;

import issue.IssueBaseTest;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;

/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue3Test0 extends IssueBaseTest<Bean0> {
	

	@Before
	public void setup()
	{
		bean=new Bean0();
		
		bean.setName("Tonj");
		bean.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		bean.setBirthday(calendar.getTime());
		Bean0.SubBean01 [] array=new Bean0.SubBean01[4];
		array[0]=new Bean0.SubBean01(new Date(), "title0");
		array[1]=new Bean0.SubBean01(new Date(), "title1");
		array[2]=new Bean0.SubBean01(new Date(), "title0");
		array[3]=new Bean0.SubBean01(new Date(), "title0");
		
		bean.setTickets(array);
	}
}
