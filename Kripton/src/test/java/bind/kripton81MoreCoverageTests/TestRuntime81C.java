package bind.kripton81MoreCoverageTests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime81C extends AbstractBaseTest {

	@Test
	public void testRuntime() throws Exception {
		assertTrue(Bean81CBindMap.class.getName()!=null);
		
		Bean81C bean=createBean();
		check(bean);
		
		bean.valueEnum=null;
		check(bean);
	}

	private Bean81C createBean() {
		Bean81C result=new Bean81C();
		
		result.id=24;
		result.valueEnum=Bean81Enum.VALUE_1;
		return result;
	}
	
}
