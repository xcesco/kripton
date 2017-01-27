package sqlite.kripton41;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBeanUpdateOK</code>
 * </p>
 *
 *  @see Bean01
 *  @see DaoBeanUpdateOK
 *  @see Bean01Table
 */
public class DaoBeanUpdateOKImpl extends AbstractDao implements DaoBeanUpdateOK {
  public DaoBeanUpdateOKImpl(BindDummy06DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean01 SET id=${id}, value=${value} WHERE id=${test}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${test}</dt><dd>is mapped to method's parameter <strong>test</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param value
   * 	is used as updated field <strong>value</strong>
   * @param test
   * 	is used as where parameter <strong>${test}</strong>
   *
   * @return <code>true</code> if record is updated, <code>false</code> otherwise
   */
  @Override
  public boolean updateDistance(long id, Double value, long test) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);
    if (value!=null) {
      contentValues.put("value", value);
    } else {
      contentValues.putNull("value");
    }

    String[] whereConditionsArray={String.valueOf(test)};

    Logger.info(StringUtils.formatSQL("UPDATE bean01 SET id='"+StringUtils.checkSize(contentValues.get("id"))+"', value='"+StringUtils.checkSize(contentValues.get("value"))+"' WHERE id=%s", (Object[])whereConditionsArray));
    int result = database().update("bean01", contentValues, "id=?", whereConditionsArray);
    return result!=0;
  }
}
