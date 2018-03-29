package sqlite.feature.many2many.case5.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindGeneratedDao;
import sqlite.feature.many2many.case5.model.Person;
import sqlite.feature.many2many.case5.model.PhoneNumber;

@BindDao(PersonPhoneNumber.class)
@BindGeneratedDao(
    dao = Person2PhoneDao.class
)
@BindDaoMany2Many(
    entity1 = Person.class,
    entity2 = PhoneNumber.class
)
public interface GeneratedPerson2PhoneDao extends Person2PhoneDao {
}
