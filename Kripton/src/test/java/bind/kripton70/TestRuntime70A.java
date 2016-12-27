package bind.kripton70;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime70A extends AbstractBaseTest {
	
	@Test
	public void testRun70A_1() throws Exception {
		assertNotNull(Bean70ABindMap.class.toString());
		
		Bean70A bean = new Bean70A();
		bean.valueString = "this is a test";
		bean.valueBean = new Bean70A();

		check(bean);
	}

	/**
	 * test file bean empty
	 * @throws Exception 
	 */
	@Test
	public void testRun70A_2() throws Exception {
		Bean70A bean = new Bean70A();
		//bean.valueString = "this is a test";
		bean.valueBean = new Bean70A();
		//bean.valueBean.valueString = "this is a second test";

		check(bean);
	}
	
	/**
	 * test file bean null
	 * @throws Exception 
	 */
	@Test
	public void testRun70A_3() throws Exception {
		Bean70A bean = new Bean70A();
		//bean.valueString = "this is a test";
		//bean.valueBean = new Bean70A();
		//bean.valueBean.valueString = "this is a second test";

		check(bean);
	}
}
