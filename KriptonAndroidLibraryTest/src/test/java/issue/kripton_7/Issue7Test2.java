/**
 * 
 */
package issue.kripton_7;

import issue.IssueBaseTest;
import issue.kripton_7.Bean3.SubBean03;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;


/**
 * @author xcesco
 *
 */
public class Issue7Test2 extends IssueBaseTest<Bean3> {

	@Before
	public void setup()
	{
		bean=new Bean3();
		
		bean.setName("Tonj");
		bean.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		bean.setBirthday(calendar.getTime());
		List<SubBean03> array=new ArrayList<SubBean03>();
		array.add(new SubBean03(new Date(), "ticket01"));
		array.add(new SubBean03(new Date(), "ticket02"));
		array.add(new SubBean03(new Date(), "ticket03"));
		bean.tickets=array;
	}

}