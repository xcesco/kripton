/**
 * 
 */
package issue.kripton_3;

import issue.IssueBaseTest;
import issue.kripton_3.Bean2.SubBean01;
import issue.kripton_3.Bean2.SubBean01.SubBean02;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;


/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue3Test2 extends IssueBaseTest<Bean2> {

	@Before
	public void setup()
	{
		beanInput=new Bean2();
		
		beanInput.setName("Tonj");
		beanInput.setSurname("Manero");
		
		beanInput.bean2=new SubBean01(new Date(), "title0");
		beanInput.bean2.sbean2=new SubBean02();
		beanInput.bean2.sbean2.fieldLong=23L;
		beanInput.bean2.sbean2.fieldString="Hello!";
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		
	}
}
