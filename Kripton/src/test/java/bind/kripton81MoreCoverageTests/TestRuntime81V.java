package bind.kripton81MoreCoverageTests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime81V extends AbstractBaseTest {

	@Test
	public void testRuntime() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81VBindMap.class.getName()!=null);
		
		Bean81V bean=createBean();
		check(bean);
		
		check(bean);
	}

	private Bean81V createBean() {
		Bean81V result=new Bean81V();
		
		result.id=24;
		result.valueByteArray=new Byte[2];
		result.valueByteArray[0]=22;
		
		result.valueIntegerList=new ArrayList<>();
		result.valueIntegerList.add(22);
		
		result.valueMapStringInteger=new HashMap<>();
		result.valueMapStringInteger.put("ss", 22);
		
		return result;
	}
	
}
