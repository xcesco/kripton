package kripton71;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import base.BaseProcessorTest;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.PropertiesBinderContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.context.YamlBinderContext;

public class TestKripton71 extends BaseProcessorTest {
	
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
		buildBindProcessorTest(BeanElement71.class, BeanEnum71.class);
	}
		
	@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException
	{
		//buildBindProcessorTest(BeanElement71.class, BeanEnum71.class);
		//http://www.studytrails.com/java/xml/woodstox/java-xml-stax-woodstox-basic-parsing/
		
		Assert.assertNotNull(new BeanElement71BindMap());
		
		BeanElement71 bean=new BeanElement71();

		
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
		
		bean.valueStringList=new ArrayList<>();
		bean.valueStringList.add("hello1");
		bean.valueStringList.add(null);
		bean.valueStringList.add("hello2");
		
		bean.valueEnumList=new LinkedList<>();
		bean.valueEnumList.add(BeanEnum71.VALUE_1);
		bean.valueEnumList.add(null);
		bean.valueEnumList.add(BeanEnum71.VALUE_2);
		
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
