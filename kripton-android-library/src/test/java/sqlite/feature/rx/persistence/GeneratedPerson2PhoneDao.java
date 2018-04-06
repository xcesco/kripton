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
package sqlite.feature.rx.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindGeneratedDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import java.util.List;
import sqlite.feature.rx.model.Person;
import sqlite.feature.rx.model.PhoneNumber;

// TODO: Auto-generated Javadoc
/**
 * The Interface GeneratedPerson2PhoneDao.
 */
@BindDao(PersonPhoneNumber.class)
@BindGeneratedDao(
    dao = Person2PhoneDao.class
)
@BindDaoMany2Many(
    entity1 = Person.class,
    entity2 = PhoneNumber.class
)
public interface GeneratedPerson2PhoneDao extends Person2PhoneDao {
  
  /**
   * Select by id.
   *
   * @param id the id
   * @return the person phone number
   */
  @BindSqlSelect(
      where = "id=${id}"
  )
  PersonPhoneNumber selectById(@BindSqlParam("id") long id);

  /**
   * Select by person id.
   *
   * @param personId the person id
   * @return the list
   */
  @BindSqlSelect(
      where = "personId=${personId}"
  )
  List<PersonPhoneNumber> selectByPersonId(@BindSqlParam("personId") long personId);

  /**
   * Select by phone number id.
   *
   * @param phoneNumberId the phone number id
   * @return the list
   */
  @BindSqlSelect(
      where = "phoneNumberId=${phoneNumberId}"
  )
  List<PersonPhoneNumber> selectByPhoneNumberId(@BindSqlParam("phoneNumberId") long phoneNumberId);

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
   * Delete by person id.
   *
   * @param personId the person id
   * @return the int
   */
  @BindSqlDelete(
      where = "personId=${personId}"
  )
  int deleteByPersonId(@BindSqlParam("personId") long personId);

  /**
   * Delete by phone number id.
   *
   * @param phoneNumberId the phone number id
   * @return the int
   */
  @BindSqlDelete(
      where = "phoneNumberId=${phoneNumberId}"
  )
  int deleteByPhoneNumberId(@BindSqlParam("phoneNumberId") long phoneNumberId);

  /**
   * Insert.
   *
   * @param bean the bean
   * @return the int
   */
  @BindSqlInsert
  int insert(@BindSqlParam("bean") PersonPhoneNumber bean);
}
