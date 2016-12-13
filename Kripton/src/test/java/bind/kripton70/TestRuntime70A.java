package bind.kripton70;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.abubusoft.kripton.binder.BinderType;

import bind.AbstractBaseTest;

public class TestRuntime70A extends AbstractBaseTest {
	
	@Test
	public void testRun70A() {
		assertNotNull(Bean70ABindMap.class);
	}

	@Test
	public void testRun70A_1() {
		Bean70A bean = new Bean70A();
		bean.valueString = "this is a test";
		bean.valueBean = new Bean70A();
		bean.valueBean.valueString = "this is a second test";

		check(bean);
	}

	/**
	 * test file bean empty
	 */
	@Test
	public void testRun70A_2() {
		Bean70A bean = new Bean70A();
		//bean.valueString = "this is a test";
		bean.valueBean = new Bean70A();
		//bean.valueBean.valueString = "this is a second test";

		check(bean);
	}
	
	/**
	 * test file bean null
	 */
	@Test
	public void testRun70A_3() {
		Bean70A bean = new Bean70A();
		//bean.valueString = "this is a test";
		//bean.valueBean = new Bean70A();
		//bean.valueBean.valueString = "this is a second test";

		check(bean);
	}
}
