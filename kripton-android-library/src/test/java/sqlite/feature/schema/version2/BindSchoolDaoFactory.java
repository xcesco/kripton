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
package sqlite.feature.schema.version2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Represents dao factory interface for SchoolDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see SchoolDataSource
 * @see DaoProfessor
 * @see DaoProfessorImpl
 * @see Professor
 * @see DaoSeminar
 * @see DaoSeminarImpl
 * @see Seminar
 * @see DaoSeminar2Student
 * @see DaoSeminar2StudentImpl
 * @see Seminar2Student
 * @see DaoStudent
 * @see DaoStudentImpl
 * @see Student
 */
public interface BindSchoolDaoFactory extends BindDaoFactory {
  
  /**
   * retrieve dao DaoProfessor.
   *
   * @return the dao professor
   */
  DaoProfessorImpl getDaoProfessor();

  /**
   * retrieve dao DaoSeminar.
   *
   * @return the dao seminar
   */
  DaoSeminarImpl getDaoSeminar();

  /**
   * retrieve dao DaoSeminar2Student.
   *
   * @return the dao seminar 2 student
   */
  DaoSeminar2StudentImpl getDaoSeminar2Student();

  /**
   * retrieve dao DaoStudent.
   *
   * @return the dao student
   */
  DaoStudentImpl getDaoStudent();
}
