package sqlite.kripton56.persistence;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import sqlite.kripton56.entities.OwnerType;

/**
 * <p>
 * DAO implementation for entity <code>MessageEntity</code>, based on interface <code>DaoMessage</code>
 * </p>
 *
 *  @see sqlite.kripton56.entities.MessageEntity
 *  @see DaoMessage
 *  @see sqlite.kripton56.entities.MessageEntityTable
 */
public class DaoMessageImpl extends AbstractDao implements DaoMessage {
  public DaoMessageImpl(BindWhisperDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE message SET owner_type=:ownerType WHERE id = ${id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>owner_type</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param ownerType
   * 	is used as updated field <strong>ownerType</strong>
   *
   * @return <code>true</code> if record is updated, <code>false</code> otherwise
   */
  @Override
  public boolean updateById(long id, OwnerType ownerType) {
    KriptonContentValues _contentValues=contentValues();
    if (ownerType!=null) {
      _contentValues.put("owner_type", ownerType.toString());
    } else {
      _contentValues.putNull("owner_type");
    }

    _contentValues.addWhereArgs(String.valueOf(id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // generate sql
    String _sql="UPDATE message SET owner_type=? WHERE id = ?";

    // display log
    Logger.info("UPDATE message SET owner_type=:ownerType WHERE id = ?");

    // log for content values -- BEGIN
    Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
    for (int i = 0; i < _contentValues.size(); i++) {
      _contentValue = _contentValues.get(i);
      if (_contentValue.value1==null) {
        Logger.info("==> :%s = <null>", _contentValue.value0);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
      }
    }
    // log for content values -- END

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, _sql, _contentValues);
    return result!=0;
  }

  public void clearCompiledStatements() {
  }
}
