package bind.kripton75ByteArray;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;

public class TestCompile75 extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws Throwable
	{
		buildBindProcessorTest(Bean75.class);
	}
	
}
