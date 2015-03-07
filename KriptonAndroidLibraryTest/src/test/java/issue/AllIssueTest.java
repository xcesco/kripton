/**
 * 
 */
package issue;

import issue.kripton_1.Issue1Test0;
import issue.kripton_1.Issue1Test1;
import issue.kripton_1.Issue1Test2;
import issue.kripton_1.Issue1Test3;
import issue.kripton_1.Issue1Test4;
import issue.kripton_1.Issue1Test5;
import issue.kripton_3.Issue3Test0;
import issue.kripton_4.Issue4Test0;
import issue.kripton_4.Issue4Test1;
import issue.kripton_4.Issue4Test2;
import issue.kripton_6.Issue6Test0;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author xcesco
 *
 */
//@formatter:off
@RunWith(Suite.class)
@SuiteClasses({
	Issue1Test0.class, Issue1Test1.class , Issue1Test2.class, Issue1Test3.class, Issue1Test4.class, Issue1Test5.class,
	Issue3Test0.class,
	Issue4Test0.class,Issue4Test1.class,Issue4Test2.class,
	Issue6Test0.class
	})
//@formatter:on
public class AllIssueTest {

}
