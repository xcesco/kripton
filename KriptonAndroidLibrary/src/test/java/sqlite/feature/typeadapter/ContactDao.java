package sqlite.feature.typeadapter;

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

//	@BindSqlInsert
//	void insert(Contact bean);
//
//	@BindSqlUpdate(where = "id=${bean.id}")
//	long update(Contact bean);
//
//	@BindSqlDelete(where = "id=${bean.id}")
//	long delete(Contact bean);

	@BindSqlUpdate(where = "id=${bean.id}  and type=${bean.type}")
	long update(Contact bean);

	
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
}
