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
package kripton07;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kripton07.Bean6.SubBean06;

import org.junit.Before;

import all.IssueBaseTest;

/**
 * @author xcesco
 *
 */
public class Issue7Test6 extends IssueBaseTest<Bean6> {

	@Before
	public void setup()
	{
		beanInput=new Bean6();
		
		beanInput.setName("Tonj");
		beanInput.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		List<SubBean06> array=new ArrayList<SubBean06>();
		array.add(new SubBean06(new Date(), "ticket01"));
		array.add(new SubBean06(new Date(), "ticket02"));
		array.add(new SubBean06(new Date(), "ticket03"));
		beanInput.tickets=array;
	}
	

}
