package sqlite.example02;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		Example02_1Test.class
		 })
//@formatter:on
public class Example02Suite extends BaseProcessorTest {

}
