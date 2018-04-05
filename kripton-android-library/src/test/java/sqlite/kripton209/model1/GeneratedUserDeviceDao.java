/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
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
package sqlite.kripton209.model1;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindGeneratedDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface GeneratedUserDeviceDao.
 */
@BindDao(UserDevice.class)
@BindGeneratedDao(
    dao = UserDeviceDao.class
)
@BindDaoMany2Many(
    entity1 = User.class,
    entity2 = Device.class
)
public interface GeneratedUserDeviceDao extends UserDeviceDao {
  
  /**
   * Select by id.
   *
   * @param id the id
   * @return the user device
   */
  @BindSqlSelect(
      where = "id=${id}"
  )
  UserDevice selectById(@BindSqlParam("id") long id);

  /**
   * Select by user id.
   *
   * @param userId the user id
   * @return the list
   */
  @BindSqlSelect(
      where = "userId=${userId}"
  )
  List<UserDevice> selectByUserId(@BindSqlParam("userId") long userId);

  /**
   * Select by device id.
   *
   * @param deviceId the device id
   * @return the list
   */
  @BindSqlSelect(
      where = "deviceId=${deviceId}"
  )
  List<UserDevice> selectByDeviceId(@BindSqlParam("deviceId") long deviceId);

  /**
   * Delete by id.
   *
   * @param id the id
   * @return the int
   */
  @BindSqlDelete(
      where = "id=${id}"
  )
  int deleteById(@BindSqlParam("id") long id);

  /**
   * Delete by user id.
   *
   * @param userId the user id
   * @return the int
   */
  @BindSqlDelete(
      where = "userId=${userId}"
  )
  int deleteByUserId(@BindSqlParam("userId") long userId);

  /**
   * Delete by device id.
   *
   * @param deviceId the device id
   * @return the int
   */
  @BindSqlDelete(
      where = "deviceId=${deviceId}"
  )
  int deleteByDeviceId(@BindSqlParam("deviceId") long deviceId);

  /**
   * Insert.
   *
   * @param bean the bean
   * @return the int
   */
  @BindSqlInsert
  int insert(@BindSqlParam("bean") UserDevice bean);
}
