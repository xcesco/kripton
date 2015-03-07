/**
 * 
 */
package issue.kripton_7;

import issue.IssueBaseTest;
import issue.kripton_7.Bean3.SubBean03;
import issue.kripton_7.Bean6.SubBean06;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;

/**
 * @author xcesco
 *
 */
public class Issue7Test5 extends IssueBaseTest<Bean6> {

	@Before
	public void setup()
	{
		bean=new Bean6();
		
		bean.setName("Tonj");
		bean.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		bean.setBirthday(calendar.getTime());
		List<SubBean06> array=new ArrayList<SubBean06>();
		array.add(new SubBean06(new Date(), "ticket01"));
		array.add(new SubBean06(new Date(), "ticket02"));
		array.add(new SubBean06(new Date(), "ticket03"));
		bean.tickets=array;
	}
	

}
