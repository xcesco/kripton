package bind.kripton81ExceptionCoverage;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;

@BindDao(ErrorPK1Bean.class)
public interface ErrorPK1Dao {
	
	@BindSqlInsert(includePrimaryKey=true)
	void insert(String description);

}
