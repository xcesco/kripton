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
package com.abubusoft.kripton.android.kripton55;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderJsonReader;
import com.abubusoft.kripton.BinderJsonWriter;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.binder.json.JsonReader;
import com.abubusoft.kripton.binder.json.JsonWriter;
import com.abubusoft.kripton.common.CollectionUtility;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.exception.WriterException;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * Test for support list and array generation for json
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class Test55 {

	@Test
	public void testWriteJsonList() throws MappingException, WriterException {
		List<Bean> list = new ArrayList<>();

		list.add(new Bean(1, "ciao"));
		list.add(new Bean(2, "ciao2"));

		BinderJsonWriter writer = BinderFactory.getJsonWriter();

		String result = writer.writeCollection(list);

		System.out.println(result);
	}

	@Test
	public void testWriteJsonArray() throws MappingException, WriterException {
		Bean[] list = new Bean[2];

		list[0] = new Bean(1, "ciao");
		list[1] = new Bean(2, "ciao2");

		BinderJsonWriter writer = BinderFactory.getJsonWriter();

		String result = writer.writeCollection(Arrays.asList(list));

		System.out.println(result);
	}

	@Test
	public void testWriteJsonArrayLong() throws MappingException, WriterException {
		Long[] list = new Long[2];

		list[0] = 1L;
		list[1] = 2L;

		BinderJsonWriter writer = BinderFactory.getJsonWriter();

		String result = writer.writeCollection(Arrays.asList(list));// CollectionUtils.convertArray(list, ArrayList.class));

		System.out.println(result);
	}

	@Test
	public void testWriteJsonArrayFloat() throws MappingException, WriterException, ReaderException {
		float[] list = new float[2];

		list[0] = 1.1f;
		list[1] = 2.2f;

		BinderJsonWriter writer = BinderFactory.getJsonWriter();
		//String middle = writer.writeList(CollectionUtility.toList(list, LinkedList.class));
		//String middle = writer.writeList(CollectionUtility.toList(list, LinkedList.class));
		String middle = writer.writeCollection(CollectionUtility.asList(list, LinkedList.class));
		System.out.println(middle);

		BinderJsonReader reader = (BinderJsonReader) BinderFactory.getJsonReader();
		List<Float> result = reader.readCollection(new ArrayList(), Float.TYPE, middle);
		System.out.println(result.toArray(new Float[result.size()]));
	}

	@Test
	public void testWriteJsonArrayDate() throws MappingException, WriterException, ReaderException {
		Long[] list = new Long[2];

		list[0] = 1L;
		list[1] = 2L;

		BinderJsonWriter writer = BinderFactory.getJsonWriter();
		String middle = writer.writeCollection(Arrays.asList(list));
		System.out.println(middle);

		BinderJsonReader reader = (BinderJsonReader) BinderFactory.getJsonReader();
		List<Long> result = reader.readList(Long.TYPE, middle);
		System.out.println(result);
	}

	public static final String input = "[{\"codicePrestazione\":\"P2980\",\"descrizione\":\"Visita andrologica - 89.7C.2\",\"tags\":[],\"prenotabilita\":{\"onLine\":true,\"callCenter\":true,\"sportelliCup\":true}},{\"codicePrestazione\":\"P2985P3264\",\"descrizione\":\"Visita cardiologica + Elettrocardiogramma\",\"tags\":[],\"prenotabilita\":{\"onLine\":true,\"callCenter\":true,\"sportelliCup\":true}},{\"codicePrestazione\":\"P2985\",\"descrizione\":\"Visita cardiologica - 89.7A.3\",\"tags\":[],\"prenotabilita\":{\"onLine\":true,\"callCenter\":true,\"sportelliCup\":true}},{\"codicePrestazione\":\"P2991\",\"descrizione\":\"Visita chirurgica - 89.7A.4\",\"tags\":[],\"prenotabilita\":{\"onLine\":false,\"callCenter\":true,\"sportelliCup\":true}}]";

	@BindType
	public static class Parent {
		@Override
		public String toString() {
			return "Parent [code=" + code + ", description=" + description + ", tags=" + java.util.Arrays.toString(tags) + ", availability=" + availability + "]";
		}

		@Bind("codicePrestazione")
		public String code;

		@Bind("descrizione")
		public String description;

		public String[] tags;

		@Bind("prenotabilita")
		public Child availability;
	}

	@BindType
	public static class Child {
		public boolean onLine;

		@Override
		public String toString() {
			return "Child [onLine=" + onLine + ", callCenter=" + callCenter + ", cup=" + cup + "]";
		}

		public boolean callCenter;

		@Bind("sportelliCup")
		public boolean cup;
	}

	@Test
	public void testWriteArrayPrestazione() throws MappingException, WriterException, ReaderException {
		BinderJsonReader reader = (BinderJsonReader) BinderFactory.getJsonReader();
		List<Parent> result = reader.readList(Parent.class, input);
		System.out.println(result);
	}

	@Test
	public void testObjectOrList() throws MappingException, WriterException, ReaderException {
		JsonWriter writer = (JsonWriter) BinderFactory.getJsonWriter();
		JsonReader reader = (JsonReader) BinderFactory.getJsonReader();

		Bean bean = new Bean();
		bean.id = 235;
		bean.text = "test";

		{
			String value = writer.write(bean);
			Assert.assertEquals(value, "{\"id\":235,\"text\":\"test\"}");
			Bean result = (Bean) reader.read(Bean.class, value);
			Assert.assertEquals(bean, result);
			System.out.println(value);
		}
		

		
	}

}
