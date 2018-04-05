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
package sqlite.feature.javadoc;

import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class Person.
 */
@BindType
public class Person {
	
	/** The id. */
	public long id;

	/** The person name. */
	protected String personName;
	
	/**
	 * Gets the person name.
	 *
	 * @return the person name
	 */
	public String getPersonName() {
		return personName;
	}

	/**
	 * Sets the person name.
	 *
	 * @param personName the new person name
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	/**
	 * Gets the person surname.
	 *
	 * @return the person surname
	 */
	public String getPersonSurname() {
		return personSurname;
	}

	/**
	 * Sets the person surname.
	 *
	 * @param personSurname the new person surname
	 */
	public void setPersonSurname(String personSurname) {
		this.personSurname = personSurname;
	}

	/**
	 * Checks if is student.
	 *
	 * @return true, if is student
	 */
	public boolean isStudent() {
		return student;
	}

	/**
	 * Sets the student.
	 *
	 * @param student the new student
	 */
	public void setStudent(boolean student) {
		this.student = student;
	}

	/** The person surname. */
	protected String personSurname;
	
	/** The student. */
	protected boolean student;
	
}
