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
package shared.kripton47;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import base.BaseAndroidTest;

public class Test47AndroidRun extends BaseAndroidTest {
	
	@Test
	public void testAppRun()
	{
		BindAppSharedPreferences shared=BindAppSharedPreferences.instance();
		shared.edit().putName("hello").commit();		
		assertTrue(shared.name().equals("hello"));
		log("shared.name() %s", shared.name());		
	}
	
	@Test
	public void testGenerated()
	{
		String value="testGenerated";
		BindSecuritySharedPreferences config=BindSecuritySharedPreferences.instance();
		
		UserIdentity userIdentity=new UserIdentity();
		userIdentity.setName(value);
		userIdentity.setEmail("a@a");
		
		DeviceAccessToken deviceAuthorizationToken=new DeviceAccessToken();
		deviceAuthorizationToken.setCreationTime(12);
		
		config.edit()
		  .putUserIdentity(userIdentity)
          .putAuthorizationToken(deviceAuthorizationToken)
          .putDeviceUid("aa")
          .commit();
		
		
		assertTrue(value.equals(config.userIdentity().getName()));
		
		log(config.userIdentity().getName());
		
	}
}
