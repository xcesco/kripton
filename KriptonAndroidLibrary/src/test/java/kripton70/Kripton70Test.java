package kripton70;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;

import kripton70.core.Binder2PropertyImpl;
import kripton70.core.Binder2YamlImpl;
import kripton70.core.BinderType;
import kripton70.core.KriptonLibrary2;

public class Kripton70Test {

	private Bean bean;

	@Before
	public void setup() {
		bean = new Bean();
		bean.id = 25;
		bean.description = "hello";
		bean.valueByteType = 12;
		bean.valueCharType = 'a';
		bean.valueShortType = 13;
		bean.valueBean = new Bean();
		bean.valueBean.id = 25;
		bean.valueBean.description = "hello";
	}

	@Test
	public void test() throws IOException {
		KriptonLibrary2.registryBinder(new Binder2YamlImpl());
		KriptonLibrary2.registryBinder(new Binder2PropertyImpl());
		{
			Bean bean2 = checkFormat(bean, BinderType.JSON);
			Assert.assertTrue(bean2.equals(bean));
		}

		{
			Bean bean2 = checkFormat(bean, BinderType.YAML);
			Assert.assertTrue(bean2.equals(bean));
		}

		{
			Bean bean2 = checkFormat(bean, BinderType.PROPERTIES);
			Assert.assertTrue(bean2.equals(bean));
		}
	}
	
	@Test
	public void testRuntimeVsCompile() throws IOException {
		int COUNT=1000000;
		long sum=0;
		for(int i=0; i< COUNT;i++){
			long start=System.currentTimeMillis();
			Bean bean2 = checkFormat(bean, BinderType.JSON);
			long duration=System.currentTimeMillis()-start;
			
			sum+=duration;
		}
		System.out.println("media compiled ms: "+(sum/(COUNT*1.0)));

		BinderWriter writer=BinderFactory.getJsonWriter(BinderOptions.build());
		BinderReader reader=BinderFactory.getJsonReader();
		sum=0;
		for(int i=0; i< COUNT;i++){
			long start=System.currentTimeMillis();
			Bean bean2 = checkFormat(bean, writer, reader);
			long duration=System.currentTimeMillis()-start;
			
			sum+=duration;
		}
		System.out.println("media runtime  ms: "+(sum/(COUNT*1.0)));
		//Assert.assertTrue(bean2.equals(bean));
	}

	public Bean checkFormat(Bean bean, BinderType type) throws IOException {
		String output = KriptonLibrary2.getBinder(type).serialize(bean);
		//System.out.println(output);
		Bean bean2 = KriptonLibrary2.getBinder(type).parse(output, Bean.class);
		return bean2;
	}
	
	/**
	 * @param bean
	 * @return
	 * @throws IOException
	 */
	public Bean checkFormat(Bean bean, BinderWriter writer, BinderReader reader) throws IOException {
		String output = writer.write(bean);
		//System.out.println(output);
		Bean bean2 = reader.read(Bean.class, output);
		return bean2;
	}
}
