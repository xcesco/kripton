/**
 * 
 */
package sqlite.feature.typeadapter.kripton180;

import java.sql.Date;

import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.android.annotation.BindTable;

import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterAddress;
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
@BindTable(name = "employees")
public class Employee {
	public long id;

	//
	public String lastName;
	public String firstName;
	public Date birthDate;
	public Date hireDate;

	@BindSqlAdapter(adapter = TypeAdapterAddress.class)
	public Address address;

	@BindSqlAdapter(adapter = TypeAdapterBoolean.class)
	public String fieldBoolean;

	@BindSqlAdapter(adapter = TypeAdapterByte.class)
	public String fieldByte;

	@BindSqlAdapter(adapter = TypeAdapterChar.class)
	public String fieldCharacter;

	@BindSqlAdapter(adapter = TypeAdapterShort.class)
	public String fieldShort;

	@BindSqlAdapter(adapter = TypeAdapterInteger.class)
	public String fieldInteger;

	@BindSqlAdapter(adapter = TypeAdapterLong.class)
	public String fieldLong;

	@BindSqlAdapter(adapter = TypeAdapterFloat.class)
	public String fieldFloat;

	@BindSqlAdapter(adapter = TypeAdapterDouble.class)
	public String fieldDouble;

	@BindSqlAdapter(adapter = TypeAdapterString.class)
	public String fieldString;

	@BindSqlAdapter(adapter = TypeAdapterByteArray.class)
	public String fieldByteArray;
	//

}
