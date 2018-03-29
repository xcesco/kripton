package sqlite.feature.many2many.case4.persistence;


import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;

import sqlite.feature.many2many.case4.model.Person;
import sqlite.feature.many2many.case4.model.PhoneNumber;

/**
 * Created by xcesco on 10/10/2017.
 */
@BindDaoMany2Many(entity1 = Person.class, entity2 = PhoneNumber.class)
public interface Person2PhoneDao {
}
