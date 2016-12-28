package sqlite.foreignKey;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		TestForeignKeyCompile.class,
		TestDependiciesFinder.class
		 })
//@formatter:on
public class TestForeignKeySuite extends BaseProcessorTest {

}
