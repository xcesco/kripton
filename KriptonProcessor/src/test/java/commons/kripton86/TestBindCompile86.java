package commons.kripton86;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;
import com.abubusoft.kripton.processor.exceptions.PropertyVisibilityException;

import bind.AbstractBindTypeProcessorTest;
import commons.kripton86.test3.Bean3_1;
import commons.kripton86.test3.Bean3_2;
import commons.kripton86.test3.Bean3_3;
import commons.kripton86.test3.Bean3_4;
import commons.kripton86.test6.Bean6_1;
import commons.kripton86.test6.Bean6_2;

public class TestBindCompile86 extends AbstractBindTypeProcessorTest {

	/**
	 * Test IncompatibleAttributesInAnnotationException
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test1Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(Bean3_1.class);
	}
	
	/**
	 * Test IncompatibleAttributesInAnnotationException
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test2Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(Bean3_2.class);
	}
	
	/**
	 * Test IncompatibleAttributesInAnnotationException
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test3Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(Bean3_3.class);
	}

	/**
	 * Test IncompatibleAttributesInAnnotationException
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test4Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(Bean3_4.class);
	}
	
	@Test
	public void test6_1Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(PropertyVisibilityException.class);
		buildBindProcessorTest(Bean6_1.class);
	}
	
	@Test
	public void test6_2Compile() throws InstantiationException, IllegalAccessException, IOException {
		this.expectedException(PropertyVisibilityException.class);
		buildBindProcessorTest(Bean6_2.class);
	}
}
