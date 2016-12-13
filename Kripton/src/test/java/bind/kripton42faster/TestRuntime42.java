package bind.kripton42faster;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime42 extends AbstractBaseTest {

	@Test
	public void testRun() throws IOException, InstantiationException, IllegalAccessException {
		Assert.assertNotNull(new RestaurantBindMap());

		Restaurant bean = new Restaurant();

		bean.address="long address string";
		bean.id=1;
		bean.latitude=25.0;
		bean.longitude=34.0;
		bean.name="name of the restaurant";

		check(bean);
	}

}
