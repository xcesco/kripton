package sqlite.kripton41;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBeanDeleteOK</code>
 * </p>
 *
 *  @see Bean01
 *  @see DaoBeanDeleteOK
 *  @see Bean01Table
 */
public class DaoBeanDeleteOKImpl extends AbstractDao implements DaoBeanDeleteOK {
  public DaoBeanDeleteOKImpl(BindDummy08DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean01 WHERE id=${value}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${value}</dt><dd>is mapped to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as where parameter <strong>${value}</strong>
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteDistance(double value) {
    String[] whereConditionsArray={String.valueOf(value)};

    Logger.info(StringUtils.formatSQL("DELETE bean01 WHERE id=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean01", "id=?", whereConditionsArray);
    return result!=0;
  }
}
