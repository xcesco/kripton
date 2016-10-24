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
