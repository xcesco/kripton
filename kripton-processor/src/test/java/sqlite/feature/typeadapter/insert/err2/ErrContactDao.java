package sqlite.feature.typeadapter.insert.err2;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;

import sqlite.feature.typeadapter.Contact;
import sqlite.feature.typeadapter.PasswordAdapterType;

@BindDao(Contact.class)
public interface ErrContactDao {
	@BindSqlInsert
	long insertCompactRaw(@BindSqlParam(adapter=PasswordAdapterType.class) Contact bean);
	
}
