package sqlite.featJQL.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.featJQL.entities.Child;

@BindDao(Child.class)
public interface DaoChild extends DaoBean<Child> {

}
