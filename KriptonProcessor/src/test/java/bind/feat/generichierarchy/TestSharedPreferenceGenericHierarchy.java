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
package bind.feat.generichierarchy;

import java.io.IOException;

import org.junit.Test;

import bind.generichierarchy.Channel;
import bind.generichierarchy.ChannelListResponse;
import bind.generichierarchy.RestListEntity;
import bind.generichierarchy.RestResponse;
import bind.generichierarchy.ServiceStatusType;
import bind.generichierarchy.UIDObject;
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
