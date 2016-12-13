package bind.kripton70;

import org.junit.Test;

import com.abubusoft.kripton.binder.BinderType;

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

	@Test
	public void testRun70A_1() {
		Bean70A bean = new Bean70A();
		// bean.valueString = "this is a test";
		bean.valueBean = new Bean70A();
		// bean.valueBean.valueString = "this is a second test";

		check(bean, BinderType.PROPERTIES);
	}
}
