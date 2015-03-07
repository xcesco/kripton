/**
 * 
 */
package issue.kripton_7;

import issue.IssueBaseTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;

/**
 * @author xcesco
 *
 */
public class Issue7Test0 extends IssueBaseTest<Bean1> {

	@Before
	public void setup()
	{
		bean=new Bean1();
		
		bean.setName("Tonj");
		bean.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		bean.setBirthday(calendar.getTime());
		
		List<Integer> array=new ArrayList<Integer>();
		array.add(1);
		array.add(2);
		array.add(4);
		bean.tickets=array;
	}
	

}
