package com.abubusoft.kripton.processor.test03;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.BaseProcessorTest;

/**
 * @author xcesco
 *
 */
@RunWith(JUnit4.class)
public class TestBean01 extends BaseProcessorTest {

	/**
	 *  No DAO definition with @BindDaoDefinition annotation was found for class Dummy01DatabaseSchema with @BindDatabaseSchema annotation
	 * 
	 * @throws IOException
	 */
	@Test
	public void test01() throws IOException {
		buildTest(Dummy01Database.class, DaoBean01.class, Bean01.class, Bean02.class);
	}

}
