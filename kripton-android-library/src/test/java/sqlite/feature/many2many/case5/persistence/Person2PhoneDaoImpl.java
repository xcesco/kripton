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
package sqlite.feature.many2many.case5.persistence;

import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.SQLContext;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * DAO implementation for entity <code>PersonPhoneNumber</code>, based on interface <code>GeneratedPerson2PhoneDao</code>
 * </p>.
 *
 * @see PersonPhoneNumber
 * @see GeneratedPerson2PhoneDao
 * @see PersonPhoneNumberTable
 */
public class Person2PhoneDaoImpl extends Dao implements GeneratedPerson2PhoneDao {
  
  /**
   * Instantiates a new person 2 phone dao impl.
   *
   * @param context the context
   */
  public Person2PhoneDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * Clear compiled statements.
   */
  public static void clearCompiledStatements() {
  }
}
