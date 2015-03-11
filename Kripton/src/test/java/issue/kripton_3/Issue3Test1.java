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
		beanInput=new Bean1();
		
		beanInput.setName("Tonj");
		beanInput.setSurname("Manero");
		
		beanInput.bean1=new SubBean01(new Date(), "title0");
		beanInput.bean1.bean2=new SubBean02();
		beanInput.bean1.bean2.fieldLong=23L;
		beanInput.bean1.bean2.fieldString="Hello!";
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		
	}
}
