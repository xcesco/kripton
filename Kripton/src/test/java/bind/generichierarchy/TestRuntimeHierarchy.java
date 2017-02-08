package bind.generichierarchy;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import bind.AbstractBaseTest;

public class TestRuntimeHierarchy extends AbstractBaseTest {

	@Test
	public void testRun() throws Exception {
		Channel channel=new Channel();
		channel.uid="25";
		channel.imageSize=22;
		channel.name="dyumm";
		
		ChannelListResponse bean=new ChannelListResponse();
		bean.list=new ArrayList<>();
		bean.map=new HashMap<>();
		
		bean.bean=channel;
		bean.list.add(channel);
		bean.map.put("ca", channel);
		
		check(bean);
	}

}
