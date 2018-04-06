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
package sqlite.test05firt_aid;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * Created by xcesco on 09/06/2016.
 */
@BindType
public class FirstAid {

	/** The id. */
	@BindColumn(columnType = ColumnType.PRIMARY_KEY)
    public long id;
    
    /** The uid. */
    @BindColumn(columnType = ColumnType.UNIQUE)
    public String uid;

    /** The description. */
    public String description;

    /** The info. */
    public String info;

    /** The longitude. */
    public Float longitude;

    /** The latitude. */
    public Float latitude;

    /** The address. */
    public String address;

    /** The address 2. */
    public String address2;

    /** The city. */
    public String city;

    /** The phone. */
    public String phone;

    /** The total patient count. */
    public int totalPatientCount;

    /** The white waiting patients. */
    public int whiteWaitingPatients;

    /** The white visiting patients. */
    public int whiteVisitingPatients;

    /** The white average waiting time. */
    public String whiteAverageWaitingTime;

    /** The green waiting patients. */
    public int greenWaitingPatients;

    /** The green visiting patients. */
    public int greenVisitingPatients;

    /** The green average waiting time. */
    public String greenAverageWaitingTime;

    /** The yellow waiting patients. */
    public int yellowWaitingPatients;

    /** The yellow visiting patients. */
    public int yellowVisitingPatients;

    /** The yellow average waiting time. */
    public String yellowAverageWaitingTime;

    /** The red waiting patients. */
    public int redWaitingPatients;

    /** The red average waiting time. */
    public String redAverageWaitingTime;

}
