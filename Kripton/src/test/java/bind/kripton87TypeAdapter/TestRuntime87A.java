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
	public void testRuntime1() throws IOException, InstantiationException, IllegalAccessException {
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
	public void testRuntime2() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean87A_2BindMap.class.getName()!=null);
		
		Bean87A_2 bean=createBeanA_2();
		check(bean);
		bean.attributeURL=null;
		bean.dataURL=null;
		bean.elementURL=null;
		check(bean);
	}
	
	@Test
	public void testRuntime3() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean87A_3BindMap.class.getName()!=null);
		
		Bean87A_3 bean=createBeanA_3();
		check(bean);
		bean.attributeBoolean=null;
		bean.dataBoolean=null;
		bean.elementBoolean=null;
		bean.elementEnum=null;
		
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
	
}
