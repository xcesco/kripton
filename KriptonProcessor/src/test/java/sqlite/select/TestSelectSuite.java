package sqlite.select;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;
import sqlite.select.scalar.TestSelectScalar;
import sqlite.select.scalarlist.TestSelectScalarList;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		TestSelectScalar.class,
		TestSelectScalarList.class
		 })
//@formatter:on
public class TestSelectSuite extends BaseProcessorTest {

}
