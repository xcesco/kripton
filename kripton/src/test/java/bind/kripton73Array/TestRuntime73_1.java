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
package bind.kripton73Array;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime73_1 extends AbstractBaseTest {

	/**
	 * Test empty arrays
	 * @throws Exception 
	 */
	@Test
	public void testEmptyArraysRun() throws Exception
	{
		Assert.assertNotNull(new Bean73BindMap());
		
		Bean73 bean=new Bean73();
			
		bean.valueBeanArray=new Bean73[0];
		
		bean.valueBigDecimalArray=new BigDecimal[0];
		
		bean.valueBigIntegerArray=new BigInteger[0];
		
		bean.valueByteArray=new Byte[0];
		
		bean.valueCharacterArray=new Character[0];
		
		bean.valueDoubleArray=new Double[0];		
		
		bean.valueEnumArray=new Enum73[0];
				
		bean.valueFloatArray=new Float[0];
		
		bean.setValueIntArray(new Integer[0]);
		
		bean.valueLongArray=new Long[0];
		
		bean.valueShortArray=new Short[0];
		
		bean.valueStringArray=new String[0];
		
//		bean.zalueStringFinal="final";
		
		check(bean);
	}

	
	
}
