package kripton71;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.CborBinderContext;
import com.abubusoft.kripton.binder2.context.PropertiesBinderContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.context.YamlBinderContext;

import base.BaseProcessorTest;

public class TestKripton71 extends BaseProcessorTest {
	
	@Before
	public void setup() {
		KriptonBinder2.registryBinder(new YamlBinderContext());
		KriptonBinder2.registryBinder(new PropertiesBinderContext());
		KriptonBinder2.registryBinder(new XmlBinderContext());
		KriptonBinder2.registryBinder(new CborBinderContext());
	}

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException
	{
		buildBindProcessorTest(BeanElement71.class, BeanEnum71.class);
	}
		
	@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException
	{
		Assert.assertNotNull(new BeanElement71BindMap());
		
		BeanElement71 bean=new BeanElement71();
			
		bean.valueBeanList=new LinkedList<>();
		bean.valueBeanList.add(new BeanElement71("hello"));				
		
		bean.valueBigDecimalList=new LinkedList<>();
		bean.valueBigDecimalList.add(null);
		bean.valueBigDecimalList.add(BigDecimal.ONE);
		bean.valueBigDecimalList.add(BigDecimal.TEN);
		bean.valueBigDecimalList.add(null);		
		
		bean.valueBigIntegerList=new LinkedList<>();
		bean.valueBigIntegerList.add(null);
		bean.valueBigIntegerList.add(BigInteger.ZERO);
		bean.valueBigIntegerList.add(BigInteger.ONE);
		bean.valueBigIntegerList.add(BigInteger.TEN);
		bean.valueBigIntegerList.add(null);
		
		bean.valueByteList=null;
		
		bean.valueCharacterList=new ArrayList<>();
		bean.valueCharacterList.add(null);
		bean.valueCharacterList.add('a');
		
		bean.valueDoubleList=new ArrayList<>();		
		
		bean.valueEnumList=new LinkedList<>();
		bean.valueEnumList.add(null);
		bean.valueEnumList.add(BeanEnum71.VALUE_1);
		bean.valueEnumList.add(BeanEnum71.VALUE_2);
				
		bean.valueFloatList=new ArrayList<>();
		bean.valueFloatList.add(1f);
		bean.valueFloatList.add(2f);
		
		bean.setValueIntList(new ArrayList<Integer>());
		bean.getValueIntList().add(1);
		bean.getValueIntList().add(2);
		
		bean.valueLongList=new ArrayList<>();
		bean.valueLongList.add(null);
		bean.valueLongList.add(null);

		bean.valueShortList=new LinkedList<>();
		bean.valueShortList.add((short)1);
		bean.valueShortList.add((short)2);

		bean.valueStringList=new ArrayList<>();
		bean.valueStringList.add("check1");
		bean.valueStringList.add("check2");
		
		bean.zalueStringFinal="final";
		
		check(bean);
	}


	
	
}
