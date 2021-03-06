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
package sqlite.kripton209.model2;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.List;


/**
 * The Interface DeviceDao.
 */
@BindDao(Device.class)
public interface DeviceDao {
	
	/**
	 * Insert.
	 *
	 * @param device the device
	 */
	@BindSqlInsert
	void insert(Device device);

    /**
     * Gets the all devices.
     *
     * @return the all devices
     */
    @BindSqlSelect
    List<Device> getAllDevices();

    /**
     * Gets the user devices.
     *
     * @param userId the user id
     * @return the user devices
     */
    // List all devices by userId
    @BindSqlSelect(jql="select * from device inner join userdevice on device.id = userdevice.deviceId  where userdevice.userId = ${userId}")
    List<Device> getUserDevices(Long userId);
}
