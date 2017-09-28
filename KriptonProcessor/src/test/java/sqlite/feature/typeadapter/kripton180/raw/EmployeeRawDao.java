package sqlite.feature.typeadapter.kripton180.raw;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.typeadapter.kripton180.Employee;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterByte;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterChar;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterByteArray;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterBoolean;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterDouble;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterFloat;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterInteger;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterLong;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterShort;
import sqlite.feature.typeadapter.kripton180.adapters.TypeAdapterString;

@BindDao(Employee.class)
public interface EmployeeRawDao {

	@BindSqlSelect(where = "id=${id}")
	Employee selectById(long id);

	@BindSqlSelect(jql = "SELECT id, lastName, firstName, birthDate, hireDate, address, fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray FROM Employee WHERE id=${id}")
	Employee selectByIdJQL(long id);

	@BindSqlSelect(where = "id=${id} and fieldBoolean=${fieldBoolean} and fieldByte=${fieldByte} and fieldCharacter=${fieldCharacter} and fieldShort=${fieldShort} and fieldInteger=${fieldInteger} and fieldLong=${fieldLong} and fieldFloat=${fieldFloat} and fieldDouble=${fieldDouble} and fieldString=${fieldString} and fieldByteArray=${fieldByteArray}")
	Employee selectByAllWithAdapter(long id,String fieldBoolean, String fieldByte, String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray);
	
	@BindSqlSelect(where = "id=${id} and fieldBoolean=${fieldBoolean} and fieldByte=${fieldByte} and fieldCharacter=${fieldCharacter} and fieldShort=${fieldShort} and fieldInteger=${fieldInteger} and fieldLong=${fieldLong} and fieldFloat=${fieldFloat} and fieldDouble=${fieldDouble} and fieldString=${fieldString} and fieldByteArray=${fieldByteArray}")
	Employee selectByAll(long id,@BindSqlParam(adapter=TypeAdapterBoolean.class) String fieldBoolean, @BindSqlParam(adapter=TypeAdapterByte.class) String fieldByte,@BindSqlParam(adapter=TypeAdapterChar.class)  String fieldCharacter, @BindSqlParam(adapter=TypeAdapterShort.class) String fieldShort, @BindSqlParam(adapter=TypeAdapterInteger.class) String fieldInteger, @BindSqlParam(adapter=TypeAdapterLong.class) String fieldLong, @BindSqlParam(adapter=TypeAdapterFloat.class) String fieldFloat, @BindSqlParam(adapter=TypeAdapterDouble.class) String fieldDouble, @BindSqlParam(adapter=TypeAdapterString.class) String fieldString, @BindSqlParam(adapter=TypeAdapterByteArray.class) String fieldByteArray);

	@BindSqlSelect(jql = "SELECT id, lastName, firstName, birthDate, hireDate, address, fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray FROM Employee WHERE id=${id} and fieldBoolean=${fieldBoolean} and fieldByte=${fieldByte} and fieldByte=${fieldByte} and fieldCharacter=${fieldCharacter} and fieldShort=${fieldShort} and fieldInteger=${fieldInteger} and fieldLong=${fieldLong} and fieldFloat=${fieldFloat} and fieldDouble=${fieldDouble} and fieldString=${fieldString} and fieldByteArray=${fieldByteArray}")
	Employee selectByAllJQL(long id,String fieldBoolean, String fieldByte, String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray);

	@BindSqlSelect(jql = "SELECT id, lastName, firstName, birthDate, hireDate, address, fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray FROM Employee WHERE id=${id} and fieldBoolean=${fieldBoolean} and fieldByte=${fieldByte} and fieldByte=${fieldByte} and fieldCharacter=${fieldCharacter} and fieldShort=${fieldShort} and fieldInteger=${fieldInteger} and fieldLong=${fieldLong} and fieldFloat=${fieldFloat} and fieldDouble=${fieldDouble} and fieldString=${fieldString} and fieldByteArray=${fieldByteArray}")
	Employee selectByAllJQLWithAdapter(long id,@BindSqlParam(adapter=TypeAdapterBoolean.class) String fieldBoolean, @BindSqlParam(adapter=TypeAdapterByte.class) String fieldByte,@BindSqlParam(adapter=TypeAdapterChar.class)  String fieldCharacter, @BindSqlParam(adapter=TypeAdapterShort.class) String fieldShort, @BindSqlParam(adapter=TypeAdapterInteger.class) String fieldInteger, @BindSqlParam(adapter=TypeAdapterLong.class) String fieldLong, @BindSqlParam(adapter=TypeAdapterFloat.class) String fieldFloat, @BindSqlParam(adapter=TypeAdapterDouble.class) String fieldDouble, @BindSqlParam(adapter=TypeAdapterString.class) String fieldString, @BindSqlParam(adapter=TypeAdapterByteArray.class) String fieldByteArray);

	@BindSqlInsert
	long insert(String fieldBoolean, String fieldByte, String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray);
	
	@BindSqlInsert
	long insertWithAdapter(String fieldBoolean, String fieldByte, String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray);
/*
	@BindSqlInsert(jql = "INSERT INTO Employee (fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray) VALUES (${bean.fieldBoolean}, ${bean.fieldByte}, ${bean.fieldCharacter}, ${bean.fieldShort}, ${bean.fieldInteger}, ${bean.fieldLong}, ${bean.fieldFloat}, ${bean.fieldDouble}, ${bean.fieldString}, ${bean.fieldByteArray})")
	long insertJQL(Employee bean);

	@BindSqlUpdate(where = "id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long update(Employee bean);

	@BindSqlUpdate(where = "id=${bean.id}")
	long updateById(Employee bean);

	@BindSqlUpdate(jql = "UPDATE Employee SET lastName=${lastName}, firstName=${firstName}, birthDate=${birthDate}, hireDate=${hireDate}, address=${address} WHERE id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long updateJQL(Employee bean);

	@BindSqlUpdate(jql = "UPDATE Employee SET lastName=${lastName}, firstName=${firstName}, birthDate=${birthDate}, hireDate=${hireDate}, address=${address} WHERE id=${bean.id}")
	long updateByIdJQL(Employee bean);

	@BindSqlDelete(where = "id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long delete(Employee bean);

	@BindSqlDelete(jql = "DELETE FROM employee WHERE id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long deleteJQL(Employee bean);

	@BindSqlDelete(where = "id=${bean.id}")
	long deleteById(Employee bean);*/

}
