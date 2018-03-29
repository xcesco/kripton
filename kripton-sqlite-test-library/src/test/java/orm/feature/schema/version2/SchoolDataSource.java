package orm.feature.schema.version2;

import com.abubusoft.kripton.android.annotation.BindDataSource;

import sqlite.feature.schema.version2.DaoProfessor;
import sqlite.feature.schema.version2.DaoSeminar;
import sqlite.feature.schema.version2.DaoSeminar2Student;
import sqlite.feature.schema.version2.DaoStudent;

@BindDataSource(fileName = "school", version = 2, daoSet = { DaoProfessor.class, DaoSeminar.class, DaoSeminar2Student.class, DaoStudent.class }, schema=true)
public interface SchoolDataSource {

}
