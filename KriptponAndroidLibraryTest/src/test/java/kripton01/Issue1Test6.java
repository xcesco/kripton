/**
 * 
 */
package kripton01;

import java.util.Calendar;
import java.util.Date;

import kripton01.Bean06.SubBean06;

import org.junit.Before;

import all.IssueBaseTest;

/**
 * @author xcesco
 *
 */
public class Issue1Test6 extends IssueBaseTest<Bean07> {

	@Before
	public void setup()
	{
		Bean06 test=new Bean06();
		
		test.setName("Tonj");
		test.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		test.setBirthday(calendar.getTime());
		SubBean06[] array= new SubBean06[3];
		array[0]=new SubBean06(new Date(), "ticket01");
		array[1]=new SubBean06(new Date(), "ticket02");
		array[2]=new SubBean06(new Date(), "ticket03");		
		
		test.setTickets(array);
		
		beanInput=new Bean07();
		beanInput.vector=test;
	}
	

}
