package bind;

import org.junit.Test;

import bind.kripton70.Bean70A;

public class Test70 extends AbstractBaseTest {

	@Test
	public void testRun70A() {
		Bean70A bean = new Bean70A();
		bean.valueString = "this is a test";
		bean.valueBean = new Bean70A();
		bean.valueBean.valueString = "this is a second test";

		check(bean);
	}
}
