/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.kripton209;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.kripton209.model2.BindApp2DaoFactory;
import sqlite.kripton209.model2.BindApp2DataSource;
import sqlite.kripton209.model2.BindApp2DataSource.Transaction;
import sqlite.kripton209.model2.Device;
import sqlite.kripton209.model2.User;
import sqlite.kripton209.model2.UserDevice;

// TODO: Auto-generated Javadoc
/**
 * The Class Test209Model2Runtime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class Test209Model2Runtime extends BaseAndroidTest {

	/**
	 * Test run sqlite 1.
	 */
	@Test
	public void testRunSqlite1() {
		BindApp2DataSource ds=BindApp2DataSource.getInstance();
		
		ds.execute(new Transaction() {
			
			@Override
			public TransactionResult onExecute(BindApp2DaoFactory daoFactory) {	
				Device device=new Device();
				device.name="device-test";				
				daoFactory.getDeviceDao().insert(device);
				
				User user=new User();
				user.userName ="user-test";				
				daoFactory.getUserDao().insert(user);
				
				UserDevice userDevice=new UserDevice(0, user.id, device.id);				
				daoFactory.getUserDeviceDao().insert(userDevice);
				
				List<Device> devices=daoFactory.getDeviceDao().getUserDevices(user.id);
				
				Assert.assertTrue(devices.size()==1);
				
				return TransactionResult.ROLLBACK;
			}
		});
	}

}
