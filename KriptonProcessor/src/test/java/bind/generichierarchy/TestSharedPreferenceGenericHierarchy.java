package bind.generichierarchy;

import java.io.IOException;

import org.junit.Test;

import shared.AbstractBindSharedPreferenceProcessorTest;

/**
 * Test bean field
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class TestSharedPreferenceGenericHierarchy extends AbstractBindSharedPreferenceProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildSharedPreferencesProcessorTest(Channel.class, ChannelListResponse.class, RestListEntity.class, RestResponse.class, ServiceStatusType.class, UIDObject.class);
	}
	

}
