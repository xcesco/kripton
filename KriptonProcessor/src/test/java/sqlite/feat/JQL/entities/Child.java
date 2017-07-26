package sqlite.feat.JQL.entities;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

import sqlite.feat.JQL.entities.Bean;
import sqlite.feat.JQL.entities.Person;

@BindType
public class Child extends Bean {

	@BindColumn(foreignKey=Person.class)
	public long parentId;
}
