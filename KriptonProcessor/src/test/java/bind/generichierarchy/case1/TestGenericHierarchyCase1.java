package bind.generichierarchy.case1;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;
import bind.generichierarchy.case1.model.Message;
import bind.generichierarchy.case1.transfer.ChannelUserListResponse;
import bind.generichierarchy.case1.transfer.MessageListResponse;
import bind.generichierarchy.case1.transfer.RestListEntity;
import bind.generichierarchy.case1.transfer.RestResponse;
import bind.generichierarchy.case1.transfer.ServiceStatusType;

/**
 * Test bean field
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class TestGenericHierarchyCase1 extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Message.class, MessageListResponse.class, ChannelUserListResponse.class, RestListEntity.class, RestResponse.class, ServiceStatusType.class);
	}
	

}
