/**
 * 
 */
package sqlite.feature.typeadapter.kripton180;

import java.sql.Date;

import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.android.annotation.BindTable;

import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterBoolean;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterByte;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterByteArray;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterChar;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterDouble;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterFloat;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterInteger;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterLong;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterShort;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterString;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@BindTable(name="employees")
public class Employee {	
	public long id;
	
//	@BindSqlAdapter(adapter=TypeAdapterLastName.class, dataType=Double.class)
	public String lastName;
	public String firstName;
	public Date birthDate;
	public Date hireDate;
	public Address address;	
	
	@BindSqlAdapter(adapter=TypeAdapterBoolean.class, dataType=Boolean.class)
	public String fieldBoolean;
	
	@BindSqlAdapter(adapter=TypeAdapterByte.class, dataType=Byte.class)
	public String fieldByte;
	
	@BindSqlAdapter(adapter=TypeAdapterChar.class, dataType=Character.class)
	public String fieldCharacter;
	
	@BindSqlAdapter(adapter=TypeAdapterShort.class, dataType=Short.class)
	public String fieldShort;
	
	@BindSqlAdapter(adapter=TypeAdapterInteger.class, dataType=Integer.class)
	public String fieldInteger;
	
	@BindSqlAdapter(adapter=TypeAdapterLong.class, dataType=Long.class)
	public String fieldLong;
	
	@BindSqlAdapter(adapter=TypeAdapterFloat.class, dataType=Float.class)
	public String fieldFloat;
	
	@BindSqlAdapter(adapter=TypeAdapterDouble.class, dataType=Double.class)
	public String fieldDouble;
	
	@BindSqlAdapter(adapter=TypeAdapterString.class, dataType=String.class)
	public String fieldString;
	
	@BindSqlAdapter(adapter=TypeAdapterByteArray.class, dataType=byte[].class)
	public String fieldByteArray;
//	

		
}
