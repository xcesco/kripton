package bind.kripton81ExceptionCoverage;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Error11Bean.class)
public interface Error11Dao {

	@BindSqlSelect(value = { "date1" }, where = "date=${date}")
	public List<Error11Bean> selectAll(Date date);
}
