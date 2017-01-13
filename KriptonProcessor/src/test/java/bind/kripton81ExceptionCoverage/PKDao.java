package bind.kripton81ExceptionCoverage;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;

@BindDao(PKBean.class)
public interface PKDao {
	
	@BindSqlInsert(includePrimaryKey=true)
	void insert(PKBean bean);

}
