package bind.kripton42faster;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntime42 extends AbstractBaseTest {

	@Test
	public void testRun() throws Exception {
		Assert.assertNotNull(new RestaurantBindMap());

		Restaurant bean = new Restaurant();

		bean.address="love is in the air";
		bean.id=1;
		bean.latitude=25.0;
		bean.longitude=34.0;
		bean.name="name of the restaurant";

		check(bean);
	}

}
