package sqlite.feature.typeadapter;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.orm.OnReadBeanListener;

@BindDao(Contact.class)
public interface ContactDao {
	// -- SELECT MIXED
	@BindSqlSelect(where="surname=${dummyTest}")
	List<Contact> selectBySurnameWithAdapter(@BindSqlParam(value="dummyTest", adapter=PasswordAdapterType.class) String dummy);
	
	@BindSqlSelect(where="surname=${dummy}")
	List<Contact> selectBySurname(String dummy);

	//-- DELETE	
	@BindSqlDelete(where = "id=${bean.id} and type=${bean.type}")
	void deleteCompactBean(Contact bean);
	
	@BindSqlDelete(where="password=${password} and type=${type}")
	void deleteCompactRaw(@BindSqlParam(adapter=PasswordAdapterType.class) String password, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type);
		
	@BindSqlDelete(jql="DELETE FROM contact WHERE id=${bean.id} and type=${bean.type}")
	void deleteJQLBean(Contact bean);
	
	@BindSqlDelete(jql="DELETE FROM contact WHERE id=${id} and type=${type}")
	long deleteJQLRaw(long id, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type);

	//-- SELECT	
	@BindSqlSelect(where = "id=${bean.id}  and type=${bean.type}")
	List<Contact> selectCompactBean(Contact bean);
	
	@BindSqlSelect(where = "id=${bean.id} and password=${bean.password} and type=${bean.type}")
	void selectJQLBeanListener(Contact bean, OnReadBeanListener<Contact> listener);
	
	@BindSqlSelect(jql="SELECT birthDay, password, type FROM contact WHERE id=${bean.id} and password=${bean.password} and type=${bean.type}")
	List<Contact> selecJQLBean(Contact bean);
	
	@BindSqlSelect(jql="SELECT * FROM contact WHERE password=${password} and type=${type}")
	List<Contact> selectJQLRaw(@BindSqlParam(adapter=PasswordAdapterType.class) String password, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type);
	
	@BindSqlSelect(where="password=${password} and type=${type}")
	List<Contact> selectCompactRaw(@BindSqlParam(adapter=PasswordAdapterType.class) String password, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type);

	//--- UPDATE
	@BindSqlUpdate(fields={"id", "type"}, where = "id=${bean.id}  and password=${bean.password} and type=${bean.type}")
	long updateCompactBean(Contact bean);
	
	@BindSqlUpdate(where = "id=${id}")
	long updateCompactRaw1(String password, ContactType type, long id);	
	
	@BindSqlUpdate(where = "password=${password} and type=${type}")
	long updateCompactRaw2(Date birthDay, @BindSqlParam(adapter=PasswordAdapterType.class) String password, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type, long id);
	
	@BindSqlUpdate(jql = "UPDATE contact SET birthDay=${bean.birthDay}, password=${bean.password}, type=${bean.type} WHERE type=${bean.type}  and type=${bean.password}")	
	long updateJQLBean(Contact bean);
	
	@BindSqlUpdate(jql="UPDATE contact SET birthDay=${birthDay}, id=${id} WHERE password=${password} and type=${type}")
	long updateJQLRaw(@BindSqlParam(adapter=PasswordAdapterType.class) String password, Date birthDay, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type, long id);
	
	//-- INSERT
	@BindSqlInsert
	long insertCompactRaw(String password, ContactType type, long id);

	@BindSqlInsert(fields={"id", "type"}, includePrimaryKey=true)
	long insertCompactBean(Contact bean);
	
	@BindSqlInsert(jql = "INSERT INTO contact (password, type, id) VALUES (${bean.password}, ${bean.type}, ${bean.id})")	
	long insertJQLBean(Contact bean);

	@BindSqlInsert(jql = "INSERT INTO contact (password, type, id) VALUES (${password}, ${type}, ${id})")	
	long insertJQLRaw(String password, Date birthDay, ContactType type, long id);


}
