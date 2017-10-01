package sqlite.feature.many2many.entity;

import com.abubusoft.kripton.annotation.BindType;

import sqlite.feature.many2many.Entity;

@BindType
public class PersonCityOk1 extends Entity {
	
	public long personId; 
	
	public long cityId;

}
