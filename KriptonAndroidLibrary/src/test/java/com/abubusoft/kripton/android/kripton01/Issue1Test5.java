/**
 * 
 */
package com.abubusoft.kripton.android.kripton01;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;

import com.abubusoft.kripton.android.kripton01.Bean06.SubBean06;

import com.abubusoft.kripton.android.all.IssueBaseTest;

/**
 * @author xcesco
 *
 */
public class Issue1Test5 extends IssueBaseTest<Bean06> {

	@Before
	public void setup()
	{
		beanInput=new Bean06();
		
		beanInput.setName("Tonj");
		beanInput.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		SubBean06[] array= new SubBean06[3];
		array[0]=new SubBean06(new Date(), "ticket01");
		array[1]=new SubBean06(new Date(), "ticket02");
		array[2]=new SubBean06(new Date(), "ticket03");		
		
		beanInput.setTickets(array);
	}
	

}
