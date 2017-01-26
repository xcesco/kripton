package sqlite.dynamic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;
import sqlite.dynamic.select.SelectTest;
import sqlite.dynamic.update.UpdateTest;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		SelectTest.class,
		UpdateTest.class
		 })
//@formatter:on
public class TestDynamicSuite extends BaseProcessorTest {

}
