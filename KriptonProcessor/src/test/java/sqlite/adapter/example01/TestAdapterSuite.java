package sqlite.adapter.example01;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		TestAdapter01.class		
		 })
//@formatter:on
public class TestAdapterSuite extends BaseProcessorTest {

}
