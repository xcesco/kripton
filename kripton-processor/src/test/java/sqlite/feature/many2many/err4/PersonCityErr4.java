package sqlite.feature.many2many.err4;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

import sqlite.feature.many2many.City;
import sqlite.feature.many2many.Entity;
import sqlite.feature.many2many.Person;

@BindType
public class PersonCityErr4 extends Entity {
	
	@BindColumn(foreignKey=Person.class)
	public long personId; 
	
	@BindColumn(foreignKey=City.class)
	public long cityId;

}
