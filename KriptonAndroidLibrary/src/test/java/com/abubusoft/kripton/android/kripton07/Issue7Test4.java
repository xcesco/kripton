/**
 * 
 */
package com.abubusoft.kripton.android.kripton07;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;

import com.abubusoft.kripton.android.all.IssueBaseTest;


/**
 * @author xcesco
 *
 */
public class Issue7Test4 extends IssueBaseTest<Bean5> {


	@Before
	public void setup()
	{
		beanInput=new Bean5();
		
		beanInput.setName("Tonj");
		beanInput.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		List<Integer> array=new ArrayList<Integer>();
		array.add(1);
		array.add(2);
		array.add(4);
		beanInput.tickets=array;
	}

}
