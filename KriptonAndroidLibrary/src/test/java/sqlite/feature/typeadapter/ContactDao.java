package sqlite.feature.typeadapter;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;

@BindDao(Contact.class)
public interface ContactDao {

	@BindSqlUpdate(fields={"id", "type"}, where = "id=${bean.id}  and password=${bean.password} and type=${bean.type}")
	long updateCompactBean(Contact bean);
	/*
	@BindSqlUpdate(jql = "UPDATE contact SET birthDay=${bean.birthDay}, password=${bean.password}, type=${bean.type} WHERE type=${bean.type}  and type=${bean.password}")	
	long updateJQLBean(Contact bean);
	
	
	@BindSqlUpdate(where = "id=${id}")
	long updateCompactRaw1(String password, ContactType type, long id);
	
	@BindSqlUpdate(where = "password=${password} and type=${type}")
	long updateCompactRaw2(Date birthDay, @BindSqlParam(adapter=PasswordAdapterType.class) String password, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type, long id);
	
	@BindSqlUpdate(jql="UPDATE contact SET birthDay=${birthDay}, id=${id} WHERE password=${password} and type=${type}")
	long updateJQLRaw1(@BindSqlParam(adapter=PasswordAdapterType.class) String password, Date birthDay, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type, long id);
	
	@BindSqlUpdate(jql="UPDATE contact SET birthDay=${birthDay}, id=${id} WHERE password=${password} and type=${type}")
	long updateJQLRaw2(Date birthDay, @BindSqlParam(adapter=PasswordAdapterType.class) String password, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type, long id);*/

	//--------------------------
	//-- DONE
	
//	@BindSqlSelect(where = "id=${bean.id}  and type=${bean.type}")
//	List<Contact> selectCompactBean(Contact bean);
//	
//	@BindSqlSelect(where = "id=${bean.id} and password=${bean.password} and type=${bean.type}")
//	void selectJQLBeanListener(Contact bean, OnReadBeanListener<Contact> listener);
//	
//	@BindSqlSelect(jql="SELECT birthDay, password, type FROM contact WHERE id=${bean.id} and password=${bean.password} and type=${bean.type}")
//	List<Contact> selecJQLBean(Contact bean);
//	
//	@BindSqlSelect(jql="SELECT * FROM contact WHERE password=${password} and type=${type}")
//	List<Contact> selectJQLRaw(@BindSqlParam(adapter=PasswordAdapterType.class) String password, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type);
//	
//	@BindSqlSelect(where="password=${password} and type=${type}")
//	List<Contact> selectCompactRaw(@BindSqlParam(adapter=PasswordAdapterType.class) String password, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type);
	
	
	//--------------------------
	//-- TO DO
//	@BindSqlInsert
//	void insert(Contact bean);
//
//	@BindSqlUpdate(where = "id=${bean.id}")
//	long update(Contact bean);
//
//	@BindSqlDelete(where = "id=${bean.id}")
//	long delete(Contact bean);

	
}
