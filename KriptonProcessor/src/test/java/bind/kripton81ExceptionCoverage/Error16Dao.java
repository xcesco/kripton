package bind.kripton81ExceptionCoverage;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Error16Bean.class)
public interface Error16Dao {

	@BindSqlSelect(excludedFields = { "date1" }, where = "date=${date}")
	public List<Error16Bean> selectAll(Date date);
}
