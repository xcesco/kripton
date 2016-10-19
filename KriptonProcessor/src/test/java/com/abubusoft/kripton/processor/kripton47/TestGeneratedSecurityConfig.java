package com.abubusoft.kripton.processor.kripton47;

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
