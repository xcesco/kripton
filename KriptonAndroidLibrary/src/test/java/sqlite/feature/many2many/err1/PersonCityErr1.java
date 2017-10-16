package sqlite.feature.many2many.err1;

import com.abubusoft.kripton.annotation.BindType;

import sqlite.feature.many2many.Entity;

@BindType
public class PersonCityErr1 extends Entity {
	
	public long personId; 
	
	public long cityId;

}
