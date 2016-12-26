package commons;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import commons.benchmark.TestBenchmarkCompileSuite;
import commons.kripton86.Test86CompileSuite;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
{ 
	Test86CompileSuite.class,
	TestBenchmarkCompileSuite.class
})
//@formatter:on
public class CommonsCompileSuite {

	

}
