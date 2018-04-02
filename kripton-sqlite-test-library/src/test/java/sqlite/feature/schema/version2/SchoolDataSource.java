package sqlite.feature.schema.version2;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName = "school", version = 2, daoSet = { DaoProfessor.class, DaoSeminar.class, DaoSeminar2Student.class, DaoStudent.class }, schema=true)
public interface SchoolDataSource {

}
