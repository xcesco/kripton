package bind.kripton81MoreCoverageTests;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;

public class Test81VCompile extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Bean81V.class, Bean81V2.class, Bean81V3.class, Bean81V4.class);
		//buildBindProcessorTest(Bean81V2.class);
	}
}
