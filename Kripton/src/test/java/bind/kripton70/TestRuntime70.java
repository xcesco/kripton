package bind.kripton70;

import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime70 extends AbstractBaseTest {

	@Test
	public void testRun70A() {
		Bean70A bean = new Bean70A();
		bean.valueString = "this is a test";
		bean.valueBean = new Bean70A();
		bean.valueBean.valueString = "this is a second test";

		check(bean);
	}
}
