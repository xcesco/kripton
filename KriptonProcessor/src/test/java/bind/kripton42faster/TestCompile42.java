package bind.kripton42faster;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;
import bind.kripton70.Bean70All;
import bind.kripton70.BeanEnum;

public class TestCompile42 extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Bean70All.class, BeanEnum.class);
	}


}
