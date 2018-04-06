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

// TODO: Auto-generated Javadoc
/**
 * The Class TestRuntime73.
 */
public class TestRuntime73 extends AbstractBaseTest {

	/**
	 * Test run.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRun() throws Exception
	{
		Assert.assertNotNull(new Bean73BindMap());
		
		Bean73 bean=new Bean73();
			
		bean.valueBeanArray=new Bean73[1];
		bean.valueBeanArray[0]=new Bean73("hello");				
		
		bean.valueBigDecimalArray=new BigDecimal[4];
		bean.valueBigDecimalArray[0]=null;
		bean.valueBigDecimalArray[1]=BigDecimal.ONE;
		bean.valueBigDecimalArray[2]=BigDecimal.TEN;
		bean.valueBigDecimalArray[3]=null;		
		
		bean.valueBigIntegerArray=new BigInteger[5];
		bean.valueBigIntegerArray[0]=null;
		bean.valueBigIntegerArray[1]=BigInteger.ZERO;
		bean.valueBigIntegerArray[2]=BigInteger.ONE;
		bean.valueBigIntegerArray[3]=BigInteger.TEN;
		bean.valueBigIntegerArray[4]=null;
		
		bean.valueByteArray=null;
		
		bean.valueCharacterArray=new Character[2];
		bean.valueCharacterArray[0]=null;
		bean.valueCharacterArray[1]='a';
		
		bean.valueDoubleArray=new Double[0];		
		
		bean.valueEnumArray=new Enum73[3];
		bean.valueEnumArray[0]=null;
		bean.valueEnumArray[1]=Enum73.VALUE_1;
		bean.valueEnumArray[2]=Enum73.VALUE_2;
				
		bean.valueFloatArray=new Float[2];
		bean.valueFloatArray[0]=1f;
		bean.valueFloatArray[1]=2f;
		
		bean.setValueIntArray(new Integer[2]);
		bean.getValueIntArray()[0]=1;
		bean.getValueIntArray()[1]=2;
		
		bean.valueLongArray=new Long[2];
		bean.valueLongArray[0]=null;
		bean.valueLongArray[1]=null;

		bean.valueShortArray=new Short[2];
		bean.valueShortArray[0]=(short)1;
		bean.valueShortArray[1]=(short)2;

		bean.valueStringArray=new String[2];
		bean.valueStringArray[0]="check1";
		bean.valueStringArray[1]="check2";
		
//		bean.zalueStringFinal="final";
		
		check(bean);
	}

	
	
}
