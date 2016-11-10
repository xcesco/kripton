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
package kripton12;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;

import all.IssueBaseTest;

import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;


/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue12Test1 extends IssueBaseTest<Bean1> {

	@Before
	public void setup()
	{
		beanInput=new Bean1();
		
		beanInput.setName("Hello 'Tonj'");
		beanInput.setSurname("Hello \"Manero\"");
		
		beanInput.genericElement =new BeanGeneric("title0", 25);		
		beanInput.genericAttribute =125;
		beanInput.genericListAttribute=new ArrayList<Integer>();
		beanInput.genericListAttribute.add(12);
		beanInput.genericListAttribute.add(123);
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		
	}

	@Override
	public void testJSON_Packed() throws WriterException, MappingException, ReaderException, IOException {
		// TODO Auto-generated method stub
		super.testJSON_Packed();
	}

	@Override
	public void testJSON_Formatted() throws WriterException, MappingException, ReaderException, IOException {
		// TODO Auto-generated method stub
		super.testJSON_Formatted();
	}

	@Override
	public void testXML_PackedDOM() throws WriterException, MappingException, ReaderException, IOException {
		// TODO Auto-generated method stub
		super.testXML_PackedDOM();
	}

	@Override
	public void testXML_FormattedDOM() throws WriterException, MappingException, ReaderException, IOException {
		// TODO Auto-generated method stub
		super.testXML_FormattedDOM();
	}

	@Override
	public void testXML_PackedSAXS() throws WriterException, MappingException, ReaderException, IOException {
		// TODO Auto-generated method stub
		super.testXML_PackedSAXS();
	}

	@Override
	public void testXML_FormattedSAXS() throws WriterException, MappingException, ReaderException, IOException {
		// TODO Auto-generated method stub
		super.testXML_FormattedSAXS();
	}
}
