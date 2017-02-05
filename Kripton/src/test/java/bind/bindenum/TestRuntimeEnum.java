package bind.bindenum;

import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntimeEnum extends AbstractBaseTest {

	@Test
	public void testRun() throws Exception {
		Bean bean = new Bean();
		bean.level = LogLevel.LOG_DEBUG;
		check(bean);
	}

}
