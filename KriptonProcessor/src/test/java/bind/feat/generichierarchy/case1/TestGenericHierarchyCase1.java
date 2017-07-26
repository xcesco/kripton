/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package bind.feat.generichierarchy.case1;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;
import bind.feat.generichierarchy.case1.model.ChannelUser;
import bind.feat.generichierarchy.case1.model.Message;
import bind.feat.generichierarchy.case1.transfer.ChannelUserListResponse;
import bind.feat.generichierarchy.case1.transfer.MessageListResponse;
import bind.feat.generichierarchy.case1.transfer.RestListEntity;
import bind.feat.generichierarchy.case1.transfer.RestResponse;
import bind.feat.generichierarchy.case1.transfer.ServiceStatusType;

/**
 * Test bean field
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class TestGenericHierarchyCase1 extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Message.class, MessageListResponse.class, ChannelUserListResponse.class, RestListEntity.class, RestResponse.class, ChannelUser.class, ServiceStatusType.class);
	}
	

}
