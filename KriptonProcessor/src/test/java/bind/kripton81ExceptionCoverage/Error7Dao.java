package bind.kripton81ExceptionCoverage;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Error7Bean.class)
public interface Error7Dao {

	@BindSqlSelect()
	public List<Error7Bean> selectAll();
}
