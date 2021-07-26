package sqlite.git104;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Document.class)
public interface DocumentDao extends AbstractDao<Document> {

  @BindSqlSelect(jql = "SELECT fileName, secret, info FROM Document WHERE id=:id and fileName is not null")
  List<FileInfo> selectFilesForAssistito(long id);

}
