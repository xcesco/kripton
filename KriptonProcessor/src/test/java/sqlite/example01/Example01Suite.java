package sqlite.example01;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		Example01Test.class
		 })
//@formatter:on
public class Example01Suite extends BaseProcessorTest {

}
