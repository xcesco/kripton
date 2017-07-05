package sqlite.featRawJQL.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.featRawJQL.entities.Child;

@BindDao(Child.class)
public interface DaoChild extends DaoBean<Child> {

}
