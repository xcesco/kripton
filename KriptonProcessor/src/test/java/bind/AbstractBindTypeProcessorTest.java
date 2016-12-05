package bind;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.CborBinderContext;
import com.abubusoft.kripton.binder2.context.PropertiesBinderContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.context.YamlBinderContext;

import base.BaseProcessorTest;
import base.MyByteArrayOutputStream;

public class AbstractBindTypeProcessorTest extends BaseProcessorTest {

	@Before
	public void setup() {
		KriptonBinder2.registryBinder(new YamlBinderContext());
		KriptonBinder2.registryBinder(new PropertiesBinderContext());
		KriptonBinder2.registryBinder(new XmlBinderContext());
		KriptonBinder2.registryBinder(new CborBinderContext());
		
		display=true;
	}
	
	protected void check(Object bean) {
		int xmlSize=serializeAndParse(bean, BinderType.XML);
		int cborSize=serializeAndParseBinary(bean, BinderType.CBOR);
		int jsonSize=serializeAndParse(bean, BinderType.JSON);
		int yamlSize=serializeAndParse(bean, BinderType.YAML);
		int propertySize=serializeAndParse(bean, BinderType.PROPERTIES);
		
		double cborPerc=cborSize*100.0/xmlSize;
		double jsonPerc=jsonSize*100.0/xmlSize;
		double yamlPerc=yamlSize*100.0/xmlSize;
		double propertyPerc=propertySize*100.0/xmlSize;
		
		System.out.println(String.format("xml: %s, cbor: %s (%.0f%%), json: %s (%.0f%%), yaml: %s (%.0f%%), property: %s (%.0f%%)", xmlSize, cborSize, cborPerc, jsonSize, jsonPerc,yamlSize, yamlPerc, propertySize, propertyPerc));	
	}
	
	protected <E> void checkCollection(Collection<E> collection, Class<E> beanClazz) {
		//int xmlSize=serializeAndParseCollection(collection, beanClazz, BinderType.XML);
		int cborSize=serializeAndParseCollectionBinary(collection, beanClazz, BinderType.CBOR);
		int jsonSize=serializeAndParseCollection(collection, beanClazz, BinderType.JSON);
		int yamlSize=serializeAndParseCollection(collection, beanClazz, BinderType.YAML);
		int propertySize=serializeAndParseCollection(collection, beanClazz, BinderType.PROPERTIES);
		
		int xmlSize=jsonSize;
		
		double cborPerc=cborSize*100.0/xmlSize;
		double jsonPerc=jsonSize*100.0/xmlSize;
		double yamlPerc=yamlSize*100.0/xmlSize;
		double propertyPerc=propertySize*100.0/xmlSize;
		
		System.out.println(String.format("xml: %s, cbor: %s (%.0f%%), json: %s (%.0f%%), yaml: %s (%.0f%%), property: %s (%.0f%%)", xmlSize, cborSize, cborPerc, jsonSize, jsonPerc,yamlSize, yamlPerc, propertySize, propertyPerc));	
	}


	/**
	 * @param bean
	 * @param type
	 * @return 
	 */
	public int serializeAndParse(Object bean, BinderType type) {
		String output1=KriptonBinder2.getBinder(type).serialize(bean);
		if (display) System.out.println(output1);
		
		Object bean2=KriptonBinder2.getBinder(type).parse(output1, bean.getClass());				
		
		String output2=KriptonBinder2.getBinder(type).serialize(bean2);
		if (display) System.out.println(output2);
		
		Assert.assertTrue(type.toString(), output1.length()==output2.length());		
		
		ReflectionAssert.assertReflectionEquals(bean, bean2, ReflectionComparatorMode.LENIENT_ORDER);
		
		return output2.length();
	}
	
	public <E> int serializeAndParseCollection(Collection<E> list, Class<E> clazz, BinderType type) {
		String output1=KriptonBinder2.getBinder(type).serializeCollection(list, clazz);
		if (display) System.out.println(output1);
		
//		List<E> bean2=KriptonBinder2.getBinder(type).parseCollection(output1, clazz);				
//		
//		String output2=KriptonBinder2.getBinder(type).serializeCollection(bean2, clazz);
//		if (display) System.out.println(output2);
//		
//		Assert.assertTrue(type.toString(), output1.length()==output2.length());		
//		
//		ReflectionAssert.assertReflectionEquals(list, bean2, ReflectionComparatorMode.LENIENT_ORDER);
//		
//		return output2.length();
		return 0;
	}
	
	public <E> int serializeAndParseCollectionBinary(Collection<E> list, Class<E> clazz, BinderType type) {
		MyByteArrayOutputStream bar = new MyByteArrayOutputStream();
		KriptonBinder2.getBinder(type).serializeCollection(list, clazz, bar);
		String value1=toString(bar.getBuf());

		if (display) System.out.println(value1);
		Object bean2 = KriptonBinder2.getBinder(type).parseCollection(new ArrayList<E>(), clazz, value1);
//
//		MyByteArrayOutputStream bar2 = new MyByteArrayOutputStream();
//		KriptonBinder2.getBinder(type).serialize(bean2, bar2);
//		String value2=toString(bar2.getBuf());
//		if (display) System.out.println(value2);
//
//		Assert.assertTrue(value1.length()==value2.length());		
//		//ReflectionAssert.assertReflectionEquals(type.toString(), bean, bean2);
//		
//		return bar.getCount();
		return 0;
	}

	public int serializeAndParseBinary(Object bean, BinderType type) {
		MyByteArrayOutputStream bar = new MyByteArrayOutputStream();
		KriptonBinder2.getBinder(type).serialize(bean, bar);
		String value1=toString(bar.getBuf());

		if (display) System.out.println(value1);
		Object bean2 = KriptonBinder2.getBinder(type).parse(new ByteArrayInputStream(bar.getBuf()), bean.getClass());

		MyByteArrayOutputStream bar2 = new MyByteArrayOutputStream();
		KriptonBinder2.getBinder(type).serialize(bean2, bar2);
		String value2=toString(bar2.getBuf());
		if (display) System.out.println(value2);

		Assert.assertTrue(value1.length()==value2.length());		
		//ReflectionAssert.assertReflectionEquals(type.toString(), bean, bean2);
		
		return bar.getCount();
	}
	

	String toString(byte[] input) {
		StringBuilder buffer = new StringBuilder();
		for (int j = 0; j < input.length; j++) {
			buffer.append(String.format("%02X", input[j]));
		}
		return buffer.toString();
	}
}
