package bind.kripton70;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;


/**
 * Test bean field
 * @author xcesco
 *
 */
public class TestCompile70A extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Bean70A.class, BeanEnum.class);
	}



}
