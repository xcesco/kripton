/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package shared.kripton45;


import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import base.BaseAndroidTest;
import junit.framework.AssertionFailedError;

public class Test45Runtime extends BaseAndroidTest {
	
	public AppPreferences createBean()
	{
		AppPreferences bean=new AppPreferences();
		
		bean.setDescription("description");
		bean.setStringArray(new String[2]);
		bean.valueBoolean=true;
		bean.valueFloat=12;
		bean.valueInt=23;
		bean.valueLong=1L;
		
		return bean;
	}
	
	
	@Test
	public void testAppRun()
	{
		AppPreferences bean=createBean();		
		BindAppPreferences prefs = BindAppPreferences.instance();		
		prefs.write(bean);
		
		AppPreferences bean2=prefs.read();				
		ReflectionAssert.assertReflectionEquals(bean, bean2, ReflectionComparatorMode.LENIENT_ORDER);				
	}
	
	@Test(expected=AssertionFailedError.class)
	public void testAppRunFail()
	{
		AppPreferences bean=createBean();		
		BindAppPreferences prefs = BindAppPreferences.instance();		
		prefs.write(bean);
		
		bean.description="fail";
		
		AppPreferences bean2=prefs.read();				
		ReflectionAssert.assertReflectionEquals(bean, bean2, ReflectionComparatorMode.LENIENT_ORDER);			
	}
	
	
	
	
	
	
}
