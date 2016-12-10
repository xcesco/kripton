package bind.kripton70;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;


/**
 * Test bean field
 * @author xcesco
 *
 */
public class Test70A extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Bean70A.class, BeanEnum.class);
	}

	@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException {
		Assert.assertNotNull(new Bean70ABindMap());

		Bean70A bean = new Bean70A();

		bean.valueBean = new Bean70A();
		bean.valueBean.valueString = "hello2";		
		bean.valueString = "hello1";

		check(bean);
	}

}
