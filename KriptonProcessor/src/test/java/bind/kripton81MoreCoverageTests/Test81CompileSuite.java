package bind.kripton81MoreCoverageTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.abubusoft.kripton.processor.bind.transform.Test81DCompile;
import com.abubusoft.kripton.processor.sharedprefs.transform.Test81ECompile;
import com.abubusoft.kripton.processor.sqlite.transform.Test81FCompile;

import bind.AbstractBindTypeProcessorTest;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		Test81ACompile.class,
		Test81BCompile.class,
		Test81CCompile.class,
		Test81DCompile.class,
		Test81ECompile.class,
		Test81FCompile.class,
		Test81GHILMNOPCompile.class,
		 })
//@formatter:on
public class Test81CompileSuite extends AbstractBindTypeProcessorTest {

	

}
