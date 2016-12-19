package bind.kripton81MoreCoverageTests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime81V extends AbstractBaseTest {

	@Test
	public void testRuntime0() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81VBindMap.class.getName()!=null);
		
		Bean81V bean=createBean0();
		check(bean);
		bean.valueByteArray=null;
		bean.valueIntegerList=null;
		bean.valueMapStringInteger=null;		
		check(bean);
	}
	
	@Test
	public void testRuntime2() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81V2BindMap.class.getName()!=null);
		
		Bean81V2 bean=createBean2();
		check(bean);
		bean.valueByteArray=null;
		bean.valueIntegerList=null;
		bean.valueMapStringInteger=null;		
		check(bean);
	}
	
	@Test
	public void testRuntime3() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81V3BindMap.class.getName()!=null);
		
		Bean81V3 bean=createBean3();
		check(bean);
		bean.valueByteArray=null;
		bean.valueIntegerList=null;
		bean.valueMapStringInteger=null;		
		check(bean);
	}
	
	@Test
	public void testRuntime4() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81V4BindMap.class.getName()!=null);
		
		Bean81V4 bean=createBean4();
		check(bean);
		bean.valueByteArray=null;
		bean.valueIntegerList=null;
		bean.valueMapStringInteger=null;		
		check(bean);
	}
	
	private Bean81V createBean0() {
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
	
	private Bean81V2 createBean2() {
		Bean81V2 result=new Bean81V2();
		
		result.id=24;
		result.valueByteArray=new Byte[2];
		result.valueByteArray[0]=22;
		
		result.valueIntegerList=new ArrayList<>();
		result.valueIntegerList.add(22);
		
		result.valueMapStringInteger=new HashMap<>();
		result.valueMapStringInteger.put("ss", 22);
		
		return result;
	}
	
	private Bean81V3 createBean3() {
		Bean81V3 result=new Bean81V3();
		
		result.id=24;
		result.valueByteArray=new Byte[2];
		result.valueByteArray[0]=22;
		
		result.valueIntegerList=new ArrayList<>();
		result.valueIntegerList.add(22);
		
		result.valueMapStringInteger=new HashMap<>();
		result.valueMapStringInteger.put("ss", 22);
		
		return result;
	}
	
	private Bean81V4 createBean4() {
		Bean81V4 result=new Bean81V4();
		
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
