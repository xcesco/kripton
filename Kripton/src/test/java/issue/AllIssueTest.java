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
import issue.kripton_7.Issue7Test0;
import issue.kripton_7.Issue7Test1;
import issue.kripton_7.Issue7Test2;
import issue.kripton_7.Issue7Test3;
import issue.kripton_7.Issue7Test4;
import issue.kripton_7.Issue7Test5;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.abubusoft.kripton.android.JSONTest0;
import com.abubusoft.kripton.android.JSONTest1;
import com.abubusoft.kripton.android.XMLTest0;
import com.abubusoft.kripton.android.XMLTest1;

/**
 * @author xcesco
 *
 */
//@formatter:off
@RunWith(Suite.class)
@SuiteClasses({
	JSONTest0.class, JSONTest1.class , XMLTest0.class, XMLTest1.class ,
	Issue1Test0.class, Issue1Test1.class , Issue1Test2.class, Issue1Test3.class, Issue1Test4.class, Issue1Test5.class,
	Issue3Test0.class,
	Issue4Test0.class,Issue4Test1.class,Issue4Test2.class,
	Issue6Test0.class,
	Issue7Test0.class, Issue7Test1.class , Issue7Test2.class, Issue7Test3.class, Issue7Test4.class, Issue7Test5.class,
	})
//@formatter:on
public class AllIssueTest {

}
