/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package kripton47;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import com.abubusoft.kripton.android.KriptonLibrary;

@RunWith(RobolectricTestRunner.class)
public class TestGeneratedSecurityConfig {
	@Test
	public void testGenerated()
	{
		KriptonLibrary.init(RuntimeEnvironment.application);
		BindGeneratedSecurityConfig config=BindGeneratedSecurityConfig.instance();
		
		UserIdentity userIdentity=new UserIdentity();
		userIdentity.setName("anotno");
		userIdentity.setEmail("a@a");
		
		DeviceAccessToken deviceAuthorizationToken=new DeviceAccessToken();
		deviceAuthorizationToken.setCreationTime(12);
		
		config.edit()
		  .putUserIdentity(userIdentity)
          .putAuthorizationToken(deviceAuthorizationToken)
          .putDeviceUid("aa")
          .commit();
		
		config.userIdentity().getName();
		
	}
}
