package com.abubusoft.kripton.processor.test05firt_aid;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.annotation.BindColumn;
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
