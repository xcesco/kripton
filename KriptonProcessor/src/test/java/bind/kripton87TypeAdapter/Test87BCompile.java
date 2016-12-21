package bind.kripton87TypeAdapter;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.IncompatibleAnnotationException;

import bind.AbstractBindTypeProcessorTest;

public class Test87BCompile extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile_1() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(IncompatibleAnnotationException.class);
		buildBindProcessorTest(Bean87A_3.class);
	}
	
//	@Test
//	public void testCompile_2() throws IOException, InstantiationException, IllegalAccessException {
//		this.expectedException(IncompatibleAnnotationException.class);
//		buildBindProcessorTest(Bean87B_2.class);
//	}
}
