package bind.feature.kotlin.err2;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(RssFeed.class)
public interface DaoRssFeed {

	@BindSqlSelect
	List<RssFeed> list();
	
	@BindSqlSelect(where = "uid=:uid")
    RssFeed selectOne(String uid);
}
