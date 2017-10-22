package sqlite.feature.rx.persistence;


import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.rx.model.Person;

/**
 * Created by xcesco on 26/02/2017.
 */
@BindDao(Person.class)
public interface PersonDao extends AbstractDao<Person> {
    
}
