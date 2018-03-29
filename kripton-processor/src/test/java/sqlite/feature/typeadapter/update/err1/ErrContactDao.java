package sqlite.feature.typeadapter.update.err1;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.typeadapter.Contact;
import sqlite.feature.typeadapter.ContactType;
import sqlite.feature.typeadapter.DateAdapterType;
import sqlite.feature.typeadapter.EnumAdapterType;
import sqlite.feature.typeadapter.PasswordAdapterType;

@BindDao(Contact.class)
public interface ErrContactDao {
	@BindSqlUpdate(jql="UPDATE contact SET birthDay=${birthDay}, id=${id} WHERE password=${password} and type=${type}")
	long updateJQLRaw1(@BindSqlParam(adapter=PasswordAdapterType.class) String password, @BindSqlParam(adapter=DateAdapterType.class) Date birthDay, @BindSqlParam(adapter=EnumAdapterType.class) ContactType type, long id);
	
}
