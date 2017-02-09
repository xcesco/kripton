package bind.generichierarchy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import bind.generichierarchy.case1.TestGenericHierarchyCase1;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{
			TestGenericHierarchy.class,
			TestGenericHierarchyCase1.class
		 })
//@formatter:on
public class TestGenericHierarchySuite {

}