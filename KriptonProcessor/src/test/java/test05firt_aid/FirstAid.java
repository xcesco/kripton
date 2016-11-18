/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package test05firt_aid;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

/**
 * Created by xcesco on 09/06/2016.
 */
@BindType
public class FirstAid {

	@BindColumn(ColumnType.PRIMARY_KEY)
    public long id;
    
    @BindColumn(ColumnType.UNIQUE)
    public String uid;

    public String description;

    public String info;

    public Float longitude;

    public Float latitude;

    public String address;

    public String address2;

    public String city;

    public String phone;

    public int totalPatientCount;

    public int whiteWaitingPatients;

    public int whiteVisitingPatients;

    public String whiteAverageWaitingTime;

    public int greenWaitingPatients;

    public int greenVisitingPatients;

    public String greenAverageWaitingTime;

    public int yellowWaitingPatients;

    public int yellowVisitingPatients;

    public String yellowAverageWaitingTime;

    public int redWaitingPatients;

    public String redAverageWaitingTime;

}
