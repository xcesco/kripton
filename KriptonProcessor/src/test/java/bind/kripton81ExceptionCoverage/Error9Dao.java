package bind.kripton81ExceptionCoverage;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Error9Bean.class)
public interface Error9Dao {

	@BindSqlSelect()
	public List<Error9Bean> selectAll();
}
