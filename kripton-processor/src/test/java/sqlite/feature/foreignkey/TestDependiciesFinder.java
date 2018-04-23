/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
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
package sqlite.feature.foreignkey;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.sqlite.core.EntityUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class TestDependiciesFinder.
 */
public class TestDependiciesFinder {

	/**
	 * The Class Dummy.
	 */
	public static class Dummy {
		
		/**
		 * Instantiates a new dummy.
		 *
		 * @param name the name
		 */
		public Dummy(String name) {
			this.name = name;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Dummy[typeName=" + name + "]";
		}

		/** The name. */
		public final String name;

		/** The dependencies. */
		public final ArrayList<Dummy> dependencies = new ArrayList<>();
		
		/**
		 * Dependencies.
		 *
		 * @param deps the deps
		 */
		public void dependencies(Dummy ... deps)
		{
			for (Dummy item: deps)
			{
				dependencies.add(item);
			}
			
		}
		
		
	}

	/**
	 * Test 01.
	 */
	@Test
	public void test01() {
		Dummy a = new Dummy("a");
		Dummy b = new Dummy("b");

		// test 1
		List<Dummy> input = new ArrayList<Dummy>();
		input.add(a);
		input.add(b);

		check(input);
	}
	
	/**
	 * Test 02.
	 */
	@Test
	public void test02() {
		Dummy a = new Dummy("a");
		Dummy b = new Dummy("b");
		
		a.dependencies(b);

		// test 1
		List<Dummy> input = new ArrayList<Dummy>();
		input.add(a);
		input.add(b);

		check(input);
	}
	
	/**
	 * Test 03.
	 */
	@Test(expected=RuntimeException.class)
	public void test03() {
		Dummy a = new Dummy("a");
		Dummy b = new Dummy("b");
		
		a.dependencies.add(b);
		b.dependencies.add(a);

		// test 1
		List<Dummy> input = new ArrayList<Dummy>();
		input.add(a);
		input.add(b);

		check(input);
	}
	
	/**
	 * Test 04.
	 */
	@Test
	public void test04() {
		Dummy a = new Dummy("a");
		Dummy b = new Dummy("b");
		Dummy c = new Dummy("c");
		Dummy d = new Dummy("d");
		
		a.dependencies(b);
		//b.dependencies(a);

		// test 1
		List<Dummy> input = new ArrayList<Dummy>();
		input.add(a);
		input.add(b);
		input.add(c);
		input.add(d);

		check(input);
	}
	
	/**
	 * Test 05.
	 */
	@Test
	public void test05() {
		Dummy a = new Dummy("a");
		Dummy b = new Dummy("b");
		Dummy c = new Dummy("c");
		Dummy d = new Dummy("d");
		
		a.dependencies(b, c);
		c.dependencies(d);

		// test 1
		List<Dummy> input = new ArrayList<Dummy>();
		input.add(a);
		input.add(b);
		input.add(c);
		input.add(d);

		check(input);
	}
	
	/**
	 * Test 06.
	 */
	@Test(expected=RuntimeException.class)
	public void test06() {
		Dummy a = new Dummy("a");
		Dummy b = new Dummy("b");
		Dummy c = new Dummy("c");
		Dummy d = new Dummy("d");
		
		a.dependencies(c);
		c.dependencies(a);

		// test 1
		List<Dummy> input = new ArrayList<Dummy>();
		input.add(a);
		input.add(b);
		input.add(c);
		input.add(d);

		check(input);
	}
	
	/**
	 * Test 07.
	 */
	@Test
	public void test07() {
		Dummy a = new Dummy("a");
		Dummy b = new Dummy("b");
		Dummy c = new Dummy("c");
		Dummy d = new Dummy("d");
		
		a.dependencies(b);
		b.dependencies(c);
		c.dependencies(d);
		//c.dependencies(a);

		// test 1
		List<Dummy> input = new ArrayList<Dummy>();
		input.add(a);
		input.add(b);
		input.add(c);
		input.add(d);

		check(input);
	}


	/**
	 * Check.
	 *
	 * @param input the input
	 */
	public void check(List<Dummy> input) {
		//System.out.println("Input: " + input);
		EntityUtility<Dummy> sorder = new EntityUtility<TestDependiciesFinder.Dummy>(input) {

			@Override
			public List<Dummy> getDependencies(Dummy item) {
				return item.dependencies;
			}

			@Override
			public void generateError(Dummy item) {
				throw new KriptonRuntimeException("circular path on " + item.name);
			}
		};

		sorder.order();
		//System.out.println("Output: " + output);
	}
}
