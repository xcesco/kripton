package orm.feature.schema.version2;

import com.abubusoft.kripton.annotation.BindType;

import sqlite.feature.schema.version2.Entity;

@BindType
public class Student extends Entity  {

	public String location;
}
