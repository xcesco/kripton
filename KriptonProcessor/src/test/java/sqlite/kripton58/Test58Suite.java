package sqlite.kripton58;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		Test58Array2Compile.class,
		Test58ArrayCompile.class,
		Test58ListCompile.class
		 })
//@formatter:on
public class Test58Suite extends BaseProcessorTest {

}
