package bind.kripton73Array;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;

public class TestCompile73 extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(Bean73.class, Enum73.class);
	}
	
}
