package bind.bugs;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import bind.bugs.git46.TestRuntimeGit46Suite;
import bind.bugs.git47.TestRuntimeGit47;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses({ 
	TestRuntimeGit46Suite.class,
	TestRuntimeGit47.class
	})
//@formatter:on
public class TestRuntimeBugsSuite {

}
