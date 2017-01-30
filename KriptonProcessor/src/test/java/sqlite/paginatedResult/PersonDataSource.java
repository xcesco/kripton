package sqlite.paginatedResult;

import com.abubusoft.kripton.android.annotation.BindDataSource;

import sqlite.paginatedResult.PersonDAO;

@BindDataSource(dao = { PersonDAO.class }, fileName = "person.db", log=true)
public interface PersonDataSource {

}
