package sqlite.feature.childselect.error5;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlChildSelect;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;


@BindDao(Channel.class)
public interface DaoChannel extends DaoBase<Channel> {
	@BindSqlSelect(childrenSelects={
			@BindSqlChildSelect(field="articles", method = "selectAll")
	})
	List<Channel> selectAll();
}
