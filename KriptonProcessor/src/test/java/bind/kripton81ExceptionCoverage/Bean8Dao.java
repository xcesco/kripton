package bind.kripton81ExceptionCoverage;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Bean8.class)
public interface Bean8Dao {

	@BindSqlSelect()
	public List<Bean8> selectAll();
}
