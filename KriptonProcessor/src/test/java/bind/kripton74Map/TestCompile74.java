package bind.kripton74Map;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;

public class TestCompile74 extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Bean74.class, Enum74.class);
	}

}
