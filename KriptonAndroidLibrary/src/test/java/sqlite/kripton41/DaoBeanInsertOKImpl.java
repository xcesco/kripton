package sqlite.kripton41;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBeanInsertOK</code>
 * </p>
 *
 *  @see Bean01
 *  @see DaoBeanInsertOK
 *  @see Bean01Table
 */
public class DaoBeanInsertOKImpl extends AbstractDao implements DaoBeanInsertOK {
  public DaoBeanInsertOKImpl(BindDummy04DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean01 (id, value) VALUES (${id}, ${value})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to column value <strong>id</strong>
   * @param value
   * 	is binded to column value <strong>value</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insertDistance(long id, Double value) {
    KriptonContentValues _contentValues=contentValues();

    _contentValues.put("id", id);
    if (value!=null) {
      _contentValues.put("value", value);
    } else {
      _contentValues.putNull("value");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:_contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO bean01 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.keySet()) {
      _contentValue=_contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    // generate SQL for insert
    String _sql=String.format("INSERT INTO bean01 (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
    // insert operation
    long result = KriptonDatabaseWrapper.insert(dataSource, _sql, _contentValues);
    return result!=-1;
  }
}
