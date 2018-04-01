package sqlite.feature.schema.version2;

import com.abubusoft.kripton.annotation.BindType;

import sqlite.feature.schema.version2.Entity;

@BindType
public class Person extends Entity {

	public String surname;
	
	public String email;
}
