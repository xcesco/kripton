package bind.kripton1110.model;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;
import bind.kripton1110.model.stage1.Friend;
import bind.kripton1110.model.stage1.Image;
import bind.kripton1110.model.stage1.Name;
import bind.kripton1110.model.stage1.Response;
import bind.kripton1110.model.stage1.User;

public class TestCompile110 extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Friend.class, Image.class, Name.class, Response.class, User.class, bind.kripton1110.model.stage2.Name.class,bind.kripton1110.model.stage2.Native.class,bind.kripton1110.model.stage2.Nld.class, bind.kripton1110.model.stage2.Pap.class);
	}


}
