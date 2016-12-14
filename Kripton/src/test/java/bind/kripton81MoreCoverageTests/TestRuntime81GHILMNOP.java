package bind.kripton81MoreCoverageTests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime81GHILMNOP extends AbstractBaseTest {

	private Bean81G createBeanG() {
		Bean81G result=new Bean81G();
		
		result.id=24;
		result.valueBigDecimal=BigDecimal.ONE;
		result.valueBigInteger=BigInteger.TEN;
		return result;
	}
	
	private Bean81H createBeanH() {
		Bean81H result=new Bean81H();
		
		result.id=24;
		result.valueBigDecimal=BigDecimal.ONE;
		result.valueBigInteger=BigInteger.TEN;
		return result;
	}
	
	private Bean81I createBeanI() {
		Bean81I result=new Bean81I();
		
		result.id=24;
		result.valueBigDecimal=BigDecimal.ONE;
		result.valueBigInteger=BigInteger.TEN;
		return result;
	}
	
	private Bean81L createBeanL() {
		Bean81L result=new Bean81L();
		
		result.id=24;
		result.valueBigDecimal=BigDecimal.ONE;
		result.valueBigInteger=BigInteger.TEN;
		return result;
	}
	
	private Bean81M createBeanM() {
		Bean81M result=new Bean81M();
		
		result.id=24;
		result.valueString1 ="value1";
		result.valueString2 ="value2";
		return result;
	}
	
	private Bean81N createBeanN() {
		Bean81N result=new Bean81N();
		
		result.id=24;
		result.valueString1 ="value1";
		result.valueString2 ="value2";
		return result;
	}
	
	private Bean81O createBeanO() {
		Bean81O result=new Bean81O();
		
		result.id=24;
		result.valueCurrency =Currency.getInstance(Locale.CANADA);
		result.valueLocale =Locale.CHINA;
		return result;
	}
	
	private Bean81P createBeanP() {
		Bean81P result=new Bean81P();
		
		result.id=24;
		result.valueCurrency =Currency.getInstance(Locale.CANADA);
		result.valueLocale =Locale.CHINA;
		return result;
	}

	@Test
	public void testRuntimeG() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81GBindMap.class.getName()!=null);
		
		Bean81G bean=createBeanG();
		check(bean);
		
		bean.valueBigDecimal=null;
		bean.valueBigInteger=null;
		
		check(bean);
	}
	
	@Test
	public void testRuntimeH() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81HBindMap.class.getName()!=null);
		
		Bean81H bean=createBeanH();
		check(bean);
		
		bean.valueBigDecimal=null;
		bean.valueBigInteger=null;
		
		check(bean);
	}
	
	@Test
	public void testRuntimeI() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81IBindMap.class.getName()!=null);
		
		Bean81I bean=createBeanI();
		check(bean);
		
		bean.valueBigDecimal=null;
		bean.valueBigInteger=null;
		
		check(bean);
	}
	
	@Test
	public void testRuntimeL() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81LBindMap.class.getName()!=null);
		
		Bean81L bean=createBeanL();
		check(bean);
		
		bean.valueBigDecimal=null;
		bean.valueBigInteger=null;
		
		check(bean);
	}
	
	
	@Test
	public void testRuntimeM() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81MBindMap.class.getName()!=null);
		
		Bean81M bean=createBeanM();
		check(bean);
		
		bean.valueString1=null;
		bean.valueString1=null;
		
		check(bean);
	}
	
	
	@Test
	public void testRuntimeN() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81NBindMap.class.getName()!=null);
		
		Bean81N bean=createBeanN();
		check(bean);
		
		bean.valueString1=null;
		bean.valueString1=null;
		
		check(bean);
	}
	
	@Test
	public void testRuntimeO() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81OBindMap.class.getName()!=null);
		
		Bean81O bean=createBeanO();
		check(bean);
		
		bean.valueCurrency=null;
		bean.valueLocale=null;
		
		check(bean);
	}
	
	@Test
	public void testRuntimeP() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81PBindMap.class.getName()!=null);
		
		Bean81P bean=createBeanP();
		check(bean);
		
		bean.valueCurrency=null;
		bean.valueLocale=null;
		
		check(bean);
	}
	
	@Test
	public void testRuntimeR() throws IOException, InstantiationException, IllegalAccessException {
		assertTrue(Bean81RBindMap.class.getName()!=null);
		
		Bean81R bean=createBeanR();
		check(bean);
		
		bean.id=23;
		bean.valueByteArray="hello".getBytes();
		bean.valueInteger=24;
		bean.valueMapStringInteger=new HashMap<>();
		bean.valueMapStringInteger.put("hello", 42);
		
		
		check(bean);
	}

	private Bean81R createBeanR() {
		Bean81R bean = new Bean81R();
		
		bean.id=23;
		bean.valueByteArray="hello".getBytes();
		bean.valueInteger=24;
		bean.valueMapStringInteger=new HashMap<>();
		bean.valueMapStringInteger.put("hello", 42);
		
		return bean;
	}
	
}
