package sqlite.feature.rx.persistence;


import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;

import sqlite.feature.rx.model.Person;
import sqlite.feature.rx.model.PhoneNumber;

/**
 * Created by xcesco on 10/10/2017.
 */
@BindDaoMany2Many(entity1 = Person.class, entity2 = PhoneNumber.class)
public interface Person2PhoneDao {
}
