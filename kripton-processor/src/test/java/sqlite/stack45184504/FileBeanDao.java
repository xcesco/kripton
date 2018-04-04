package sqlite.stack45184504;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(FileBean.class)
public interface FileBeanDao {

	@BindSqlInsert
	long insert(FileBean bean);
	
	@BindSqlInsert
	long insert(String name, String contentType, byte[] content);
	
	@BindSqlSelect(where="id=${id}")
	List<FileBean> selectById(long id);
}
