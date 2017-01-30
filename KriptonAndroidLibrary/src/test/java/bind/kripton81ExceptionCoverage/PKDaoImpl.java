package bind.kripton81ExceptionCoverage;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;

/**
 * <p>
 * DAO implementation for entity <code>PKBean</code>, based on interface <code>PKDao</code>
 * </p>
 *
 *  @see PKBean
 *  @see PKDao
 *  @see PKBeanTable
 */
public class PKDaoImpl extends AbstractDao implements PKDao {
  public PKDaoImpl(BindPKDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO p_k_bean (id, description) VALUES (${bean.id}, ${bean.description})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>description</dt><dd>is mapped to <strong>${bean.description}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   *
   */
  @Override
  public void insert(PKBean bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", bean.id);

    if (bean.description!=null) {
      contentValues.put("description", bean.description);
    } else {
      contentValues.putNull("description");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO p_k_bean (id, description) VALUES ('"+StringUtils.checkSize(contentValues.get("id"))+"', '"+StringUtils.checkSize(contentValues.get("description"))+"')"));
    long result = database().insert("p_k_bean", null, contentValues);
    bean.id=result;
  }
}
