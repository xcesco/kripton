/**
 * 
 */
package kripton01;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;

import com.abubusoft.kripton.android.kripton01.Bean03;
import com.abubusoft.kripton.android.kripton01.Bean03.SubBean03;

import all.IssueBaseTest;


/**
 * @author xcesco
 *
 */
public class Issue1Test2 extends IssueBaseTest<Bean03> {

	@Before
	public void setup()
	{
		beanInput=new Bean03();
		
		beanInput.setName("Tonj");
		beanInput.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		SubBean03[] array= new SubBean03[3];
		array[0]=new SubBean03(new Date(), "ticket01");
		array[1]=new SubBean03(new Date(), "ticket02");
		array[2]=new SubBean03(new Date(), "ticket03");		
		
		beanInput.setTickets(array);
	}

}
