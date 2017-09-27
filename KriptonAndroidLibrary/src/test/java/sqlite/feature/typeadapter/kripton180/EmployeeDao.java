package sqlite.feature.typeadapter.kripton180;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(Employee.class)
public interface EmployeeDao {

	@BindSqlSelect(where="id=${bean.id}")
	List<Employee> selectById(Employee bean);
		
	@BindSqlSelect(jql="SELECT id, lastName, firstName, birthDate, hireDate, address, fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray FROM Employee WHERE id=${bean.id}")
	List<Employee> selectByIdJQL(Employee bean);
	
	@BindSqlSelect(where="id=${bean.id} and fieldByte=${bean.fieldByte} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	List<Employee> selectByAll(Employee bean);
	
	@BindSqlSelect(jql="SELECT id, lastName, firstName, birthDate, hireDate, address, fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray FROM Employee WHERE id=${bean.id} and fieldByte=${bean.fieldByte} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	List<Employee> selectByAllJQL(Employee bean);
	
	@BindSqlInsert
	long insert(Employee bean);
	
	@BindSqlInsert(jql="INSERT INTO Employee (fieldBoolean, fieldByte, fieldCharacter, fieldShort, fieldInteger, fieldLong, fieldFloat, fieldDouble, fieldString, fieldByteArray) VALUES (${bean.fieldBoolean}, ${bean.fieldByte}, ${bean.fieldCharacter}, ${bean.fieldShort}, ${bean.fieldInteger}, ${bean.fieldLong}, ${bean.fieldFloat}, ${bean.fieldDouble}, ${bean.fieldString}, ${bean.fieldByteArray})")
	long insertJQL(Employee bean);	
	
	@BindSqlUpdate(where="id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long update(Employee bean);
	
	@BindSqlUpdate(where="id=${bean.id}")
	long updateById(Employee bean);
	
	@BindSqlUpdate(jql="UPDATE Employee SET lastName=${lastName}, firstName=${firstName}, birthDate=${birthDate}, hireDate=${hireDate}, address=${address} WHERE id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long updateJQL(Employee bean);
	
	@BindSqlUpdate(jql="UPDATE Employee SET lastName=${lastName}, firstName=${firstName}, birthDate=${birthDate}, hireDate=${hireDate}, address=${address} WHERE id=${bean.id}")
	long updateByIdJQL(Employee bean);
	
	@BindSqlDelete(where="id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long delete(Employee bean);
	
	@BindSqlDelete(jql="DELETE FROM employee WHERE id=${bean.id} and fieldByte=${bean.fieldByte} and fieldCharacter=${bean.fieldCharacter} and fieldShort=${bean.fieldShort} and fieldInteger=${bean.fieldInteger} and fieldLong=${bean.fieldLong} and fieldFloat=${bean.fieldFloat} and fieldDouble=${bean.fieldDouble} and fieldString=${bean.fieldString} and fieldByteArray=${bean.fieldByteArray}")
	long deleteJQL(Employee bean);
	
	@BindSqlDelete(where="id=${bean.id}")
	long deleteById(Employee bean);

}
