package bind.kripton80ContextCollection;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;
import bind.kripton80ContextCollection.Bean80A;
import bind.kripton80ContextCollection.BeanEnum;

public class Test80ACompile extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Bean80A.class, BeanEnum.class);
	}
}
