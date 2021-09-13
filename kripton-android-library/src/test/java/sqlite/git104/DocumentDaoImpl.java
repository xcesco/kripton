package sqlite.git104;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Document</code>, based on interface <code>DocumentDao</code>
 * </p>
 *
 *  @see Document
 *  @see DocumentDao
 *  @see DocumentTable
 */
public class DocumentDaoImpl extends Dao implements DocumentDao {
  /**
   * SQL definition for method selectFilesForAssistito
   */
  private static final String SELECT_FILES_FOR_ASSISTITO_SQL1 = "SELECT file_name, secret, info FROM documents WHERE id=? and file_name is not null";

  public DocumentDaoImpl(BindAppDaoFactory daoFactory) {
    super(daoFactory.getContext());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT file_name, secret, info FROM documents WHERE id=:id and file_name is not null</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link FileInfo}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>file_name</dt><dd>is associated to bean's property <strong>fileName</strong></dd>
   * 	<dt>secret</dt><dd>is associated to bean's property <strong>secret</strong></dd>
   * 	<dt>info</dt><dd>is associated to bean's property <strong>info</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @return collection of bean or empty collection. If result type is List, it will be generated as <strong>immutable list</strong>.
   */
  @Override
  public List<FileInfo> selectFilesForAssistito(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_FILES_FOR_ASSISTITO_SQL1;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = getDatabase().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<FileInfo> resultList=new ArrayList<FileInfo>(_cursor.getCount());
      FileInfo resultBean=null;

      // initialize temporary variable for immutable POJO
      // immutable object: initialize temporary variables for properties
      String __fileName=null;
      String __secret=null;
      DocumentInfo __info=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("file_name");
        int index1=_cursor.getColumnIndex("secret");
        int index2=_cursor.getColumnIndex("info");

        do
         {
          // reset temporary variable for immutable POJO
          // immutable object: initialize temporary variables for properties
          __fileName=null;
          __secret=null;
          __info=null;
          if (!_cursor.isNull(index0)) { __fileName=_cursor.getString(index0); }
          if (!_cursor.isNull(index1)) { __secret=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { __info=DocumentTable.parseInfo(_cursor.getBlob(index2)); }

          // define immutable POJO
          // immutable object: inizialize object
          resultBean=new FileInfo(__fileName,__secret,__info);
          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return (resultList==null ? null : Collections.unmodifiableList(resultList));
    }
    // Specialized part - SelectBeanListHelper - END
  }

  public static void clearCompiledStatements() {
  }
}
