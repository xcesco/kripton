package bind.kripton81ExceptionCoverage;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Error7_1Bean.class)
public interface Error7_1Dao {

	@BindSqlSelect()
	public List<Error7_1Bean> selectAll();
}
