package sqlite.feature.many2many.case5.persistence;


import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;

import sqlite.feature.many2many.case5.model.Person;
import sqlite.feature.many2many.case5.model.PhoneNumber;

/**
 * Created by xcesco on 10/10/2017.
 */
@BindDaoMany2Many(entity1 = Person.class, entity2 = PhoneNumber.class, methods=false)
public interface Person2PhoneDao {
}
