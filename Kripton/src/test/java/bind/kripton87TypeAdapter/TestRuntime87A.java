package bind.kripton87TypeAdapter;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime87A extends AbstractBaseTest {

	@Test
	public void testRuntime1() throws Exception  {
		assertTrue(Bean87A_1BindMap.class.getName()!=null);
		
		Bean87A_1 bean=createBeanA();
		check(bean);
		bean.valueDate=null;
		bean.valueDescription=null;
		check(bean);
	}		
	
	private Bean87A_1 createBeanA() {
		Bean87A_1 result=new Bean87A_1();
		
		result.valueDate=new Date();
		result.valueDescription="hello";
		
		return result;
	}	
	
	@Test
	public void testRuntime2() throws Exception  {
		assertTrue(Bean87A_2BindMap.class.getName()!=null);
		
		Bean87A_2 bean=createBeanA_2();
		check(bean);
		bean.attributeURL=null;
		bean.dataURL=null;
		bean.elementURL=null;
		check(bean);
	}
	
	@Test
	public void testRuntime3() throws Exception  {
		assertTrue(Bean87A_3BindMap.class.getName()!=null);
		
		Bean87A_3 bean=createBeanA_3();
		check(bean);
		bean.attributeBoolean=null;
		bean.dataBoolean=null;
		bean.elementBoolean=null;
		bean.elementEnum=null;
		
		check(bean);
	}	
	
	@Test
	public void testRuntime5() throws Exception  {
		assertTrue(Bean87A_5BindMap.class.getName()!=null);
		
		Bean87A_5 bean=createBeanA_5();
		check(bean);
		bean.attributeBoolean=null;
		bean.dataBoolean=null;
		bean.elementBoolean=null;
		
		check(bean);
	}
	
	@Test
	public void testRuntime6() throws Exception  {
		assertTrue(Bean87A_6BindMap.class.getName()!=null);
		
		Bean87A_6 bean=createBeanA_6();
		check(bean);
		bean.attributeString=null;
		bean.dataString=null;
		bean.elementString=null;
		
		check(bean);
	}
	
	@Test
	public void testRuntime7() throws Exception  {
		assertTrue(Bean87A_7BindMap.class.getName()!=null);
		
		Bean87A_7 bean=createBeanA_7();
		check(bean);
		bean.attributeString=null;
		bean.dataString=null;
		bean.elementString=null;
		
		check(bean);
	}
	
	private Bean87A_2 createBeanA_2() throws MalformedURLException {
		Bean87A_2 result=new Bean87A_2();
		
		result.attributeURL=new URL("http://www.google.it");
		result.dataURL=new URL("http://www.google.it");
		result.elementURL=new URL("http://www.google.it");
		
		
		return result;
	}
	
	private Bean87A_3 createBeanA_3() throws MalformedURLException {
		Bean87A_3 result=new Bean87A_3();
		
		result.attributeBoolean=true;
		result.dataBoolean=true;
		result.elementBoolean=true;
		result.elementEnum=Enum87A.VALUE_2;
		
		return result;
	}
	
	private Bean87A_5 createBeanA_5() throws MalformedURLException {
		Bean87A_5 result=new Bean87A_5();
		
		result.attributeBoolean=true;
		result.dataBoolean=true;
		result.elementBoolean=true;
		
		return result;
	}
	
	private Bean87A_6 createBeanA_6() throws MalformedURLException {
		Bean87A_6 result=new Bean87A_6();
		
		result.attributeString="http://www.google.it";
		result.dataString="http://www.google.it";
		result.elementString="http://www.google.it";
		
		return result;
	}
	
	private Bean87A_7 createBeanA_7() throws MalformedURLException {
		Bean87A_7 result=new Bean87A_7();
		
		result.attributeString="http://www.google.it";
		result.dataString="http://www.google.it";
		result.elementString="http://www.google.it";
		
		return result;
	}
	
}
