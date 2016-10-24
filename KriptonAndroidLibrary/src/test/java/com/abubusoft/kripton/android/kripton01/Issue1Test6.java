/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
public class Issue1Test6 extends IssueBaseTest<Bean07> {

	@Before
	public void setup()
	{
		Bean06 test=new Bean06();
		
		test.setName("Tonj");
		test.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		test.setBirthday(calendar.getTime());
		SubBean06[] array= new SubBean06[3];
		array[0]=new SubBean06(new Date(), "ticket01");
		array[1]=new SubBean06(new Date(), "ticket02");
		array[2]=new SubBean06(new Date(), "ticket03");		
		
		test.setTickets(array);
		
		beanInput=new Bean07();
		beanInput.vector=test;
	}
	

}
