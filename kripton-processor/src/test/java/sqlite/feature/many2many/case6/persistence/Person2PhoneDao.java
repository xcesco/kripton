package sqlite.feature.many2many.case6.persistence;


import java.sql.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.many2many.case6.model.Person;
import sqlite.feature.many2many.case6.model.PersonPhone;
import sqlite.feature.many2many.case6.model.PhoneNumber;

/**
 * Created by xcesco on 10/10/2017.
 */
@BindDao(PersonPhone.class)
@BindDaoMany2Many(entity1 = Person.class, entity2 = PhoneNumber.class )
public interface Person2PhoneDao {
	
	@BindSqlSelect(where="buyDate>${since}")
	List<PersonPhone> selectByDate(Date since);
}
