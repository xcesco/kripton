package bind.kripton81MoreCoverageTests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime81A extends AbstractBaseTest {

	@Test
	public void testRuntime() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81ABindMap.class.getName()!=null);
		
		Bean81A bean=createBean();
		check(bean);
		
		bean.valueEnum=null;
		bean.valueBidDecimal=null;
		bean.valueBidInteger=null;
		check(bean);
	}

	private Bean81A createBean() {
		Bean81A result=new Bean81A();
		
		result.id=24;
		result.valueEnum=Bean81Enum.VALUE_1;
		result.valueBidDecimal=BigDecimal.ONE;
		result.valueBidInteger=BigInteger.TEN;
		return result;
	}
	
}
