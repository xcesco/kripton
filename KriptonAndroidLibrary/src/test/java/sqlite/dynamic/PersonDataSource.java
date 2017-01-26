package sqlite.dynamic;

import com.abubusoft.kripton.android.annotation.BindDataSource;

import sqlite.dynamic.PersonDAO;

@BindDataSource(dao = { PersonDAO.class }, fileName = "person.db", log=true)
public interface PersonDataSource {

}
