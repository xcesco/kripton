package sqlite.feat.JQL.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

import sqlite.feat.JQL.persistence.DaoChild;
import sqlite.feat.JQL.persistence.DaoPerson;

@BindDataSource(dao={DaoChild.class, DaoPerson.class}, fileName = "familiy")
public interface FamilyDataSource {

}
