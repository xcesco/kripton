package sqlite.feature.typeadapter.kripton180.raw.insertselect;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;

import sqlite.feature.typeadapter.kripton180.Employee;

@BindDao(Employee.class)
public interface EmployeeRawInsertSelectDao {

	@BindSqlInsert(jql = "INSERT INTO Employee (fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray) select fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray  from Employee where fieldBoolean=${fieldBoolean} and fieldByte=${fieldByte} and fieldCharacter=${fieldCharacter} and fieldShort=${fieldShort} and fieldInteger=${fieldInteger} and fieldLong=${fieldLong} and fieldFloat=${fieldFloat} and fieldDouble=${fieldDouble} and fieldString=${fieldString} and fieldByteArray=${fieldByteArray}")
	void insertJQL(String fieldBoolean, String fieldByte, String fieldCharacter, String fieldShort, String fieldInteger, String fieldLong, String fieldFloat, String fieldDouble, String fieldString, String fieldByteArray);

}
