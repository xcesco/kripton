package sqlite.feature.typeadapter.update.err2;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.typeadapter.Contact;
import sqlite.feature.typeadapter.PasswordAdapterType;

@BindDao(Contact.class)
public interface ErrContactDao {
	@BindSqlUpdate(fields={"id", "type"}, where = "id=${bean.id}  and password=${bean.password} and type=${bean.type}")
	long updateCompactBean(@BindSqlParam(adapter=PasswordAdapterType.class) Contact bean);
	
}
