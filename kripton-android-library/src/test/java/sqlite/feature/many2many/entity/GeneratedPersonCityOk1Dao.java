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
package sqlite.feature.many2many.entity;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindGeneratedDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import java.util.List;
import sqlite.feature.many2many.City;
import sqlite.feature.many2many.Person;

// TODO: Auto-generated Javadoc
/**
 * The Interface GeneratedPersonCityOk1Dao.
 */
@BindDao(PersonCityOk1.class)
@BindGeneratedDao(
    dao = PersonCityOk1Dao.class
)
@BindDaoMany2Many(
    entity1 = Person.class,
    entity2 = City.class
)
public interface GeneratedPersonCityOk1Dao extends PersonCityOk1Dao {
  
  /**
   * Select by id.
   *
   * @param id the id
   * @return the person city ok 1
   */
  @BindSqlSelect(
      where = "id=${id}"
  )
  PersonCityOk1 selectById(@BindSqlParam("id") long id);

  /**
   * Select by person id.
   *
   * @param personId the person id
   * @return the list
   */
  @BindSqlSelect(
      where = "personId=${personId}"
  )
  List<PersonCityOk1> selectByPersonId(@BindSqlParam("personId") long personId);

  /**
   * Select by city id.
   *
   * @param cityId the city id
   * @return the list
   */
  @BindSqlSelect(
      where = "cityId=${cityId}"
  )
  List<PersonCityOk1> selectByCityId(@BindSqlParam("cityId") long cityId);

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
   * Delete by city id.
   *
   * @param cityId the city id
   * @return the int
   */
  @BindSqlDelete(
      where = "cityId=${cityId}"
  )
  int deleteByCityId(@BindSqlParam("cityId") long cityId);

  /**
   * Insert.
   *
   * @param bean the bean
   * @return the int
   */
  @BindSqlInsert
  int insert(@BindSqlParam("bean") PersonCityOk1 bean);
}
