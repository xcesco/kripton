package bind.kripton70;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;


public class TestCompile70All extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Bean70All.class, BeanEnum.class);
	}

}
