package sqlite.feature.schema.data;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(fileName = "school", version = 1, daoSet = { DaoProfessor.class, DaoSeminar.class, DaoSeminar2Student.class, DaoStudent.class }, generateSchema=true)
public interface SchoolDataSource {

}
