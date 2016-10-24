/**
 * 
 */
package kripton07;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import com.abubusoft.kripton.android.kripton07.Bean3;
import com.abubusoft.kripton.android.kripton07.Bean3.SubBean03;

import all.IssueBaseTest;


/**
 * @author xcesco
 *
 */
public class Issue7Test2 extends IssueBaseTest<Bean3> {

	@Before
	public void setup()
	{
		beanInput=new Bean3();
		
		beanInput.setName("Tonj");
		beanInput.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		List<SubBean03> array=new ArrayList<SubBean03>();
		array.add(new SubBean03(new Date(), "ticket01"));
		array.add(new SubBean03(new Date(), "ticket02"));
		array.add(new SubBean03(new Date(), "ticket03"));
		beanInput.tickets=array;
	}

}
