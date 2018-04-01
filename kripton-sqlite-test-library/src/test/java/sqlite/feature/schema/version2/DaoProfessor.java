package sqlite.feature.schema.version2;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.schema.version2.DaoBase;
import sqlite.feature.schema.version2.Professor;

@BindDao(Professor.class)
public interface DaoProfessor extends DaoBase<Professor> {
	
}
