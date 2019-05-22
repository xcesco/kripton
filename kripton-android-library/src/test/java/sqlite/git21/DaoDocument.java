package sqlite.git21;

import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Document.class)
public interface DaoDocument {
	
	@BindSqlSelect(fields="fileName",where="fileName like :input")
	Set<String> findByFileName(String input);
	
	@BindSqlInsert
	void insert(String fileName);

	@BindSqlSelect
	List<Document> selectAll();
}
