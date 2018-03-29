package sqlite.feature.many2many.case4.persistence;


import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.many2many.case4.model.Person;
import sqlite.feature.many2many.case4.persistence.AbstractDao;

/**
 * Created by xcesco on 26/02/2017.
 */
@BindDao(Person.class)
public interface PersonDao extends AbstractDao<Person> {
    
}
