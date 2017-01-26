package sqlite.dynamic.select;

import com.abubusoft.kripton.android.annotation.BindDataSource;

import sqlite.dynamic.select.PersonDAO;

@BindDataSource(dao = { PersonDAO.class }, fileName = "person.db", log=true)
public interface PersonDataSource {

}
