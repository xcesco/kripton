package sqlite.dynamic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		SelectTest.class,
		UpdateTest.class
		 })
//@formatter:on
public class TestIndexesSuite extends BaseProcessorTest {

}
