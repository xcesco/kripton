package bind.kripton81MoreCoverageTests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime81B extends AbstractBaseTest {

	@Test
	public void testRuntime() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81BBindMap.class.getName()!=null);
		
		Bean81B bean=createBean();
		check(bean);
		
		bean.valueEnum=null;
		check(bean);
	}

	private Bean81B createBean() {
		Bean81B result=new Bean81B();
		
		result.id=24;
		result.valueEnum=Bean81Enum.VALUE_1;
		return result;
	}
	
}
