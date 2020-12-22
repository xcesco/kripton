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
package sqlite.feature.jql.kripton163;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;


/**
 * Created by xcesco on 30/08/2017.
 */
@BindDao(CollegeStudent.class)
public interface CollegeStudentDao {

   /**
    * Gets the students.
    *
    * @param firstName the first name
    * @return the students
    */
   // select a list of students with extended JQL
  @BindSqlSelect(jql="select * from CollegeStudent where firstName like ${firstName} || '%' ")
  List<CollegeStudent> getStudents(String firstName);

   /**
    * Gets the students raw.
    *
    * @param firstName the first name
    * @return the students raw
    */
   // select a list of students with extended JQL
   @BindSqlSelect(where="firstName like ${firstName} || '%' ")
   List<CollegeStudent> getStudentsRaw(String firstName);

}
