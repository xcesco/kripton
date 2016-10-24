/**
 * 
 */
package kripton07;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import com.abubusoft.kripton.android.kripton07.Bean6;
import com.abubusoft.kripton.android.kripton07.Bean6.SubBean06;

import all.IssueBaseTest;

/**
 * @author xcesco
 *
 */
public class Issue7Test5 extends IssueBaseTest<Bean6> {

	@Before
	public void setup()
	{
		beanInput=new Bean6();
		
		beanInput.setName("Tonj");
		beanInput.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		List<SubBean06> array=new ArrayList<SubBean06>();
		array.add(new SubBean06(new Date(), "ticket01"));
		array.add(new SubBean06(new Date(), "ticket02"));
		array.add(new SubBean06(new Date(), "ticket03"));
		beanInput.tickets=array;
	}
	

}
