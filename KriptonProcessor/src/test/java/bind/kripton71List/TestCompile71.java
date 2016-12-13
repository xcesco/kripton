package bind.kripton71List;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;

public class TestCompile71 extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(Bean71.class, Enum71.class);
	}
	
}
