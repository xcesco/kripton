package sqlite.feature.livedata.paginated.case2;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;

@BindDao(Group.class)
public interface DaoGroup {

	@BindSqlInsert
	void insert(Group bean);
}
