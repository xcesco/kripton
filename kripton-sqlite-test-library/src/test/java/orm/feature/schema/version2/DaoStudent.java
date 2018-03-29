package orm.feature.schema.version2;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.schema.version2.DaoBase;
import sqlite.feature.schema.version2.Student;

@BindDao(Student.class)
public interface DaoStudent extends DaoBase<Student>  {

}
