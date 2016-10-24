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
package com.abubusoft.kripton.android.kripton13;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

import com.abubusoft.kripton.android.all.IssueBaseTest;

/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue13Test1 extends IssueBaseTest<Bean1> {

	@Before
	public void setup()
	{
		beanInput=new Bean1();
		
		beanInput.list.add(new Bean0("helo", 244));
		beanInput.list.add(new Bean0("helo", 244));
		
	}

	@Override
	@Test
	public void testJSON_Packed() throws WriterException, MappingException, ReaderException, IOException {
		super.testJSON_Packed();
		
		Assert.assertTrue(beanOutput.list.getClass()==LinkedList.class);
	}

	@Override
	@Test
	public void testJSON_Formatted() throws WriterException, MappingException, ReaderException, IOException {
		super.testJSON_Formatted();
		
		Assert.assertTrue(beanOutput.list.getClass()==LinkedList.class);
	}

	@Override
	@Test
	public void testXML_PackedDOM() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_PackedDOM();
		
		Assert.assertTrue(beanOutput.list.getClass()==LinkedList.class);
	}

	@Override
	@Test
	public void testXML_FormattedDOM() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_FormattedDOM();
		
		Assert.assertTrue(beanOutput.list.getClass()==LinkedList.class);
	}

	@Override
	@Test
	public void testXML_PackedSAXS() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_PackedSAXS();
		
		Assert.assertTrue(beanOutput.list.getClass()==LinkedList.class);
		
	}

	@Override
	@Test
	public void testXML_FormattedSAXS() throws WriterException, MappingException, ReaderException, IOException {
		super.testXML_FormattedSAXS();
		
		Assert.assertTrue(beanOutput.list.getClass()==LinkedList.class);
		
	}

}
