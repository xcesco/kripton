package bind.kripton81ExceptionCoverage;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Error10Bean.class)
public interface Error10Dao {

	@BindSqlSelect(value = { "date" }, excludedFields = { "date" }, where = "date=${date}")
	public List<Error10Bean> selectAll(Date date);
}
