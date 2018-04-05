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
package bind.kripton72;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

// TODO: Auto-generated Javadoc
/**
 * The Class TestRuntime72_1.
 */
public class TestRuntime72_1 extends AbstractBaseTest {

	/**
	 * Test all elements filled.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testAllFilledRun() throws Exception
	{
		Assert.assertNotNull(new Bean72BindMap());
		
		Bean72 bean=new Bean72();
			
		bean.valueBeanSet=new HashSet<Bean72>();
		bean.valueBeanSet.add(new Bean72("helloSet1"));				
		bean.valueBeanSet.add(new Bean72("helloSet2"));
		bean.valueBeanSet.add(new Bean72("helloSet3"));
		
		bean.valueBigDecimalSet=new HashSet<BigDecimal>();
		bean.valueBigDecimalSet.add(BigDecimal.ONE);
		bean.valueBigDecimalSet.add(BigDecimal.TEN);
		bean.valueBigDecimalSet.add(BigDecimal.ZERO);
		
		bean.valueBigIntegerSet=new HashSet<BigInteger>();
		bean.valueBigIntegerSet.add(BigInteger.ZERO);
		bean.valueBigIntegerSet.add(BigInteger.TEN);
		bean.valueBigIntegerSet.add(BigInteger.ZERO);
		
		bean.valueByteSet=new HashSet<>();
		bean.valueByteSet.add((byte)1);
		bean.valueByteSet.add((byte)2);
		bean.valueByteSet.add((byte)3);
		
		bean.valueCharacterSet=new HashSet<Character>();
		bean.valueCharacterSet.add('a');
		bean.valueCharacterSet.add('b');
		bean.valueCharacterSet.add('c');
		
		bean.valueDoubleSet=new TreeSet<Double>();		
		bean.valueDoubleSet.add(23.0);
		bean.valueDoubleSet.add(24.0);
		
		bean.valueEnumSet=new HashSet<Enum72>();
		bean.valueEnumSet.add(Enum72.VALUE_1);
		bean.valueEnumSet.add(Enum72.VALUE_2);
				
		bean.valueFloatSet=new HashSet<Float>();
		bean.valueFloatSet.add(1f);		
		
		bean.setValueIntSet(new HashSet<Integer>());
		bean.getValueIntSet().add(1);
		
		bean.valueLongSet=new HashSet<Long>();
		bean.valueLongSet.add(23L);

		bean.valueShortSet=new HashSet<Short>();
		bean.valueShortSet.add((short)1);

		bean.valueStringSet=new HashSet<String>();
		bean.valueStringSet.add("check1");
		
		bean.zalueStringFinal="final";
		
		check(bean);
	}

	
	
	
}
