package com.abubusoft.kripton.processor.test01;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.tools.JavaFileObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.BaseProcessorTest;
import com.abubusoft.kripton.processor.BinderDatabaseProcessor;
import com.abubusoft.kripton.processor.sqlite.exceptions.NoBindTypeElementsFound;
import com.google.testing.compile.CompileTester.CompilationResultsConsumer;
import com.google.testing.compile.CompileTester.GenerationClause;
import com.google.testing.compile.CompileTester.SuccessfulCompilationClause;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class Test01 extends BaseProcessorTest {

	/**
	 * No @BindType is put in bean definition
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test01() throws IOException {
		buildTest(Dummy01DatabaseSchema.class, Bean01.class);
	}

	/**
	 * No @BindType is put in bean definition
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test02() throws IOException {
		buildTest(Dummy02DatabaseSchema.class, Bean01.class, Bean02.class);

	}

	/**
	 * No database schema with @BindDatabaseSchema was found
	 * 
	 * @throws IOException
	 */
	@Test
	public void test03() throws IOException {
		buildTest(Dummy03DatabaseSchema.class, Bean01.class, Bean02.class);
	}

	/**
	 * No database schema with @BindDatabaseSchema was found
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test04() throws IOException {
		buildTest(Dummy04DatabaseSchema.class, Bean04.class);
	}

	/**
	 * No database schema with @BindDatabaseSchema was found
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test05() throws IOException {
		buildTest(Dummy05DatabaseSchema.class, Bean04.class);		
	}

	@Test(expected = AssertionError.class)
	public void test06() throws IOException {
		buildTest(Dummy06DatabaseSchema.class, Bean06.class);	
	}

	/**
	 * No primary key
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test07() throws IOException {
		buildTest(Dummy07DatabaseSchema.class, Bean07.class);					
	}

	/**
	 * No primary key
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test08() throws IOException {
		buildTest(Dummy08DatabaseSchema.class, Bean08.class);		
	}

	/**
	 * Primary key Long
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test09() throws IOException {
		buildTest(Dummy09DatabaseSchema.class, Bean09.class);		
	}

	/**
	 * Primary key twice
	 * 
	 * @throws IOException
	 */
	@Test(expected = AssertionError.class)
	public void test10() throws IOException {
		buildTest(Dummy10DatabaseSchema.class, Bean10.class);		
	}

}
