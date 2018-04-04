package sqlite.stack44633883;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(value=SchoolLunch.class)
public interface SchoolLunchDAO {
	
	@BindSqlSelect(jql="SELECT * FROM SchoolLunch ORDER BY fruits COLLATE LOCALIZED")
    List<SchoolLunch> get1();


    @BindSqlSelect
    List<SchoolLunch> getAll();

    @BindSqlInsert
    void insertAll(SchoolLunch schoolLunches);

    @BindSqlDelete
    void deleteAll();
}