package sqlite.feature.many2many.case5.persistence;


import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.many2many.case5.model.Person;
import sqlite.feature.many2many.case5.persistence.AbstractDao;

/**
 * Created by xcesco on 26/02/2017.
 */
@BindDao(Person.class)
public interface PersonDao extends AbstractDao<Person> {
    
}
