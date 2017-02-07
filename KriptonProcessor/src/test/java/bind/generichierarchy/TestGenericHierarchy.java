package bind.generichierarchy;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;

/**
 * Test bean field
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class TestGenericHierarchy extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Channel.class, ChannelListResponse.class, RestListEntity.class, RestResponse.class, ServiceStatusType.class, UIDObject.class);
	}


}
