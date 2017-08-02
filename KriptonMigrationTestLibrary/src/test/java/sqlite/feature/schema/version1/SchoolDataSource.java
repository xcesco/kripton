package sqlite.feature.schema.version1;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName = "school", version = 2, daoSet = { DaoProfessor.class, DaoSeminar.class, DaoSeminar2Student.class, DaoStudent.class }, generateSchema=true)
public interface SchoolDataSource {

}
