package sqlite.test01;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		TestDatabase01.class
		 })
//@formatter:on
public class Test01Suite extends BaseProcessorTest {

}
