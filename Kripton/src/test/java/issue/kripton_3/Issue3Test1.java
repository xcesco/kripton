/**
 * 
 */
package issue.kripton_3;

import issue.IssueBaseTest;
import issue.kripton_3.Bean1.SubBean01;
import issue.kripton_3.Bean1.SubBean01.SubBean02;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;


/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue3Test1 extends IssueBaseTest<Bean1> {

	@Before
	public void setup()
	{
		bean=new Bean1();
		
		bean.setName("Tonj");
		bean.setSurname("Manero");
		
		bean.bean1=new SubBean01(new Date(), "title0");
		bean.bean1.bean2=new SubBean02();
		bean.bean1.bean2.fieldLong=23L;
		bean.bean1.bean2.fieldString="Hello!";
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		bean.setBirthday(calendar.getTime());
		
	}
}
