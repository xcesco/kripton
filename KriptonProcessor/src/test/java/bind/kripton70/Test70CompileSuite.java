package bind.kripton70;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import bind.AbstractBindTypeProcessorTest;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		TestCompile70A.class,
		TestCompile70All.class
		 })
//@formatter:on
public class Test70CompileSuite extends AbstractBindTypeProcessorTest {

}
