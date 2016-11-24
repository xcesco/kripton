package kripton72;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.PropertiesBinderContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.context.YamlBinderContext;

import base.BaseProcessorTest;

public class TestKripton72 extends BaseProcessorTest {
	
	@Before
	public void setup()
	{
		KriptonBinder2.registryBinder(new YamlBinderContext());
		KriptonBinder2.registryBinder(new PropertiesBinderContext());
		KriptonBinder2.registryBinder(new XmlBinderContext());
	}

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(BeanElement72.class, BeanEnum72.class);
	}
		
	@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException
	{
		//buildBindProcessorTest(BeanElement71.class, BeanEnum71.class);
		//http://www.studytrails.com/java/xml/woodstox/java-xml-stax-woodstox-basic-parsing/
		
		Assert.assertNotNull(new BeanElement72BindMap());
		
		BeanElement72 bean=new BeanElement72();

		
		//bean.valueEnum=BeanEnum.VALUE_2;
		
		//bean.valueStringList=new ArrayList<String>();
//bean.valueStringList.add("hello1");
		
		//bean.valueBigDecimalList=new ArrayList<>();
		//bean.valueBigDecimalList.add(BigDecimal.valueOf(1));
		
//		bean.valueDoubleList=new ArrayList<>();
//		bean.valueDoubleList.add(1.0);
//		bean.valueDoubleList.add(null);
//		bean.valueDoubleList.add(null);
//		bean.valueDoubleList.add(2.0);
//		bean.valueDoubleList.add(3.0);
		
		bean.valueStringSet=new HashSet<>();
		bean.valueStringSet.add("hello1");
		bean.valueStringSet.add(null);
		bean.valueStringSet.add("hello2");
		
		bean.valueEnumSet=new LinkedHashSet<>();
		bean.valueEnumSet.add(BeanEnum72.VALUE_1);
		bean.valueEnumSet.add(null);
		bean.valueEnumSet.add(BeanEnum72.VALUE_2);
		
		bean.valueDoubleSet=new HashSet<>();
		bean.valueDoubleSet.add(34.0);
		bean.valueDoubleSet.add(null);
		bean.valueDoubleSet.add(36.0);
		
		/*
		bean.valueCalendar=Calendar.getInstance();
		bean.valueCurrency=Currency.getInstance(Locale.ITALY);
		bean.valueDate=new Date();
		bean.valueLocale=Locale.ITALY;
		bean.valueTime=new Time(0);
		bean.valueTimeZone=TimeZone.getDefault();
		bean.valueUrl=new URL("http://github.com");
		*/
		
		/*
		bean.id=25;
		bean.valueBean=new BeanElement70();
		bean.valueBean.id=45;
		bean.valueBoolType=true;
		bean.valueBool=true;
		bean.valueByteType=4;
		bean.valueByte=8;
		
		bean.valueShortType=25;
		bean.valueShort=25;
		bean.valueCharType='a';
		bean.valueChar='a';
		
		bean.valueIntType=12;
		bean.valueInt=12;
		bean.valueLongType=24;
		bean.valueLong=24L;
		bean.valueFloatType=24f;
		bean.valueFloat=24f;
		bean.valueDoubleType=24.0;
		bean.valueDouble=24.0;
		*/
		
		//bean.valueString="\"ciao";
		
		//bean.valueCDataString="qq";
		
		serializeAndParse(bean, BinderType.XML);
		serializeAndParse(bean, BinderType.JSON);
		serializeAndParse(bean, BinderType.YAML);
		serializeAndParse(bean, BinderType.PROPERTIES);
	}

	/**
	 * @param bean
	 * @param type
	 */
	public void serializeAndParse(Object bean, BinderType type) {
		String output1=KriptonBinder2.getBinder(type).serialize(bean);
		System.out.println(output1);
		
		Object bean2=KriptonBinder2.getBinder(type).parse(output1, bean.getClass());				
		
		String output2=KriptonBinder2.getBinder(type).serialize(bean2);
		System.out.println(output2);
		
		Assert.assertTrue(output1.equals(output2));
	}
	
	
}
