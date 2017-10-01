package sqlite.feature.many2many.err2;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

import sqlite.feature.many2many.Entity;
import sqlite.feature.many2many.Person;

@BindType
public class PersonCityErr2 extends Entity {
	
	@BindColumn(foreignKey=Person.class)
	public long personId; 
	
	public long cityId;

}
