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
package com.abubusoft.kripton.android.kripton15;

import java.util.Currency;
import java.util.Locale;

import org.junit.Before;

import com.abubusoft.kripton.android.all.IssueBaseTest;


/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue15Test6 extends IssueBaseTest<Bean6> {

	@Before
	public void setup()
	{
		beanInput=new Bean6();
		
		Level1 level1_1=new Level1();
		level1_1.name="index0";
		level1_1.info=new Level2();
		level1_1.info.local=Locale.CANADA;
		level1_1.info.currency=Currency.getAvailableCurrencies().iterator().next();
		
		Level1 level1_2=new Level1();
		level1_2.name=null;
		level1_2.info=new Level2();
		level1_2.info.local=Locale.CANADA;
		level1_2.info.currency=Currency.getAvailableCurrencies().iterator().next();
		
		Level1 level1_3=new Level1();
		level1_3.name="index0";
		level1_3.info=new Level2();
		level1_3.info.local=null;
		level1_3.info.currency=null;
		
	//	beanInput.map.put(0, level1_1);
//		beanInput.map.put(1, level1_2);
		beanInput.map.put(2, null);
		//beanInput.map.put(3, level1_3);
		
		
	}

}
