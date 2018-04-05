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

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;
import sqlite.feature.rx.model.Person;
import sqlite.feature.rx.model.PhoneNumber;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Generated entity implementation for <code>PersonPhoneNumber</code>
 * </p>.
 */
@BindTable(
    name = "person_phone_number"
)
public class PersonPhoneNumber {
  
  /** Primary key. */
  @BindColumn(
      columnType = ColumnType.PRIMARY_KEY
  )
  public long id;

  /** Foreign key to Person model class. */
  @BindColumn(
      foreignKey = Person.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long personId;

  /** Foreign key to PhoneNumber model class. */
  @BindColumn(
      foreignKey = PhoneNumber.class,
      onDelete = ForeignKeyAction.CASCADE
  )
  public long phoneNumberId;
}
