package bind.kripton72;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;

public class TestCompile72A extends AbstractBindTypeProcessorTest {
	

	@Test
	public void testCompileA() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(Bean72A.class, Enum72.class);
	}
	
}
