/**
 * 
 */
package com.abubusoft.kripton.android.kripton03;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;

import com.abubusoft.kripton.android.all.IssueBaseTest;

/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue3Test0 extends IssueBaseTest<Bean0> {
	

	@Before
	public void setup()
	{
		beanInput=new Bean0();
		
		beanInput.setName("Tonj");
		beanInput.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		Bean0.SubBean01 [] array=new Bean0.SubBean01[4];
		array[0]=new Bean0.SubBean01(new Date(), "title0");
		array[1]=new Bean0.SubBean01(new Date(), "title1");
		array[2]=new Bean0.SubBean01(new Date(), "title0");
		array[3]=new Bean0.SubBean01(new Date(), "title0");
		
		beanInput.setTickets(array);
	}
}
