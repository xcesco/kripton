package bind.kripton72;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;

public class TestCompile72 extends AbstractBindTypeProcessorTest {
	

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(Bean72.class, Enum72.class);
	}
	
	
}
