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
package bind.kripton71list;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

// TODO: Auto-generated Javadoc
/**
 * The Class TestRuntime71.
 */
public class TestRuntime71 extends AbstractBaseTest {
			
	/**
	 * Test run.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRun() throws Exception
	{
		Assert.assertNotNull(new Bean71BindMap());
		
		Bean71 bean=new Bean71();
			
		bean.valueBeanList=new LinkedList<>();
		bean.valueBeanList.add(new Bean71("hello"));				
		
		bean.valueBigDecimalList=new LinkedList<>();
		bean.valueBigDecimalList.add(null);
		bean.valueBigDecimalList.add(BigDecimal.ONE);
		bean.valueBigDecimalList.add(BigDecimal.TEN);
		bean.valueBigDecimalList.add(null);		
		
		bean.valueBigIntegerList=new LinkedList<>();
		bean.valueBigIntegerList.add(null);
		bean.valueBigIntegerList.add(BigInteger.ZERO);
		bean.valueBigIntegerList.add(BigInteger.ONE);
		bean.valueBigIntegerList.add(BigInteger.TEN);
		bean.valueBigIntegerList.add(null);
		
		bean.valueByteList=null;
		
		bean.valueCharacterList=new ArrayList<>();
		bean.valueCharacterList.add(null);
		bean.valueCharacterList.add('a');
		
		bean.valueDoubleList=new ArrayList<>();		
		
		bean.valueEnumList=new LinkedList<>();
		bean.valueEnumList.add(null);
		bean.valueEnumList.add(Enum71.VALUE_1);
		bean.valueEnumList.add(Enum71.VALUE_2);
				
		bean.valueFloatList=new ArrayList<>();
		bean.valueFloatList.add(1f);
		bean.valueFloatList.add(2f);
		
		bean.setValueIntList(new ArrayList<Integer>());
		bean.getValueIntList().add(1);
		bean.getValueIntList().add(2);
		
		bean.valueLongList=new ArrayList<>();
		bean.valueLongList.add(null);
		bean.valueLongList.add(null);

		bean.valueShortList=new LinkedList<>();
		bean.valueShortList.add((short)1);
		bean.valueShortList.add((short)2);

		bean.valueStringList=new ArrayList<>();
		bean.valueStringList.add("check1");
		bean.valueStringList.add("check2");
		
		bean.zalueStringFinal="final";
		
		check(bean);
	}


	
	
}
