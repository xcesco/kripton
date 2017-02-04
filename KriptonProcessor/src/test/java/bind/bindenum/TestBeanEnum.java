package bind.bindenum;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;


/**
 * Test bean field
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class TestBeanEnum extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Bean.class);
	}



}
