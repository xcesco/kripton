package sqlite.git21;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Document.class)
public interface DaoDocument {

	@BindSqlSelect
	List<Document> selectAll();
}
