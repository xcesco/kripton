package bind.kripton80ContextCollection;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import bind.AbstractBindTypeProcessorTest;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		Test80ACompile.class,
		Test80ALLCompile.class
		 })
//@formatter:on
public class Test80CompileSuite extends AbstractBindTypeProcessorTest {

	

}
