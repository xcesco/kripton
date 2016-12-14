package bind.kripton81MoreCoverageTests;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;

public class Test81GHILMNOPCompile extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Bean81G.class, Bean81H.class, Bean81I.class, Bean81L.class, Bean81M.class, Bean81N.class, Bean81O.class, Bean81P.class,
				Bean81U.class, Bean81R.class, Bean81S.class, Bean81T.class, 
				Bean81Enum.class);
	}
}
