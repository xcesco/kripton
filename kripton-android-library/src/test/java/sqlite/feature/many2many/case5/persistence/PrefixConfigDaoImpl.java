package sqlite.feature.many2many.case5.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import sqlite.feature.many2many.case5.model.PrefixConfig;

/**
 * <p>
 * DAO implementation for entity <code>PrefixConfig</code>, based on interface <code>PrefixConfigDao</code>
 * </p>
 *
 *  @see PrefixConfig
 *  @see PrefixConfigDao
 *  @see sqlite.feature.many2many.case5.model.PrefixConfigTable
 */
public class PrefixConfigDaoImpl extends Dao implements PrefixConfigDao {
  private static SQLiteStatement insertPreparedStatement0;

  private static final String SELECT_BY_ID_SQL4 = "SELECT id, default_country, dual_billing_prefix, enabled, dialog_timeout FROM prefix_config WHERE id = ?";

  private static SQLiteStatement deleteByIdPreparedStatement1;

  private static SQLiteStatement updateByIdPreparedStatement2;

  private static final String SELECT_ONE_SQL5 = "SELECT id, default_country, dual_billing_prefix, enabled, dialog_timeout FROM prefix_config";

  private static SQLiteStatement updatePreparedStatement3;

  public PrefixConfigDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO prefix_config (default_country, dual_billing_prefix, enabled, dialog_timeout) VALUES (${defaultCountry}, ${dualBillingPrefix}, ${enabled}, ${dialogTimeout})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>default_country</dt><dd>is mapped to <strong>${bean.defaultCountry}</strong></dd>
   * 	<dt>dual_billing_prefix</dt><dd>is mapped to <strong>${bean.dualBillingPrefix}</strong></dd>
   * 	<dt>enabled</dt><dd>is mapped to <strong>${bean.enabled}</strong></dd>
   * 	<dt>dialog_timeout</dt><dd>is mapped to <strong>${bean.dialogTimeout}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(PrefixConfig bean) {
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT OR REPLACE INTO prefix_config (default_country, dual_billing_prefix, enabled, dialog_timeout) VALUES (?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("default_country", bean.defaultCountry);
    _contentValues.put("dual_billing_prefix", bean.dualBillingPrefix);
    _contentValues.put("enabled", bean.enabled);
    _contentValues.put("dialog_timeout", bean.dialogTimeout);

    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT OR REPLACE INTO prefix_config (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
      // log for insert -- END 


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement0, _contentValues);
    bean.id=result;

    return (int)result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, default_country, dual_billing_prefix, enabled, dialog_timeout FROM prefix_config WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>default_country</dt><dd>is associated to bean's property <strong>defaultCountry</strong></dd>
   * 	<dt>dual_billing_prefix</dt><dd>is associated to bean's property <strong>dualBillingPrefix</strong></dd>
   * 	<dt>enabled</dt><dd>is associated to bean's property <strong>enabled</strong></dd>
   * 	<dt>dialog_timeout</dt><dd>is associated to bean's property <strong>dialogTimeout</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public PrefixConfig selectById(long id) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL4;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
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
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      PrefixConfig resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("default_country");
        int index2=_cursor.getColumnIndex("dual_billing_prefix");
        int index3=_cursor.getColumnIndex("enabled");
        int index4=_cursor.getColumnIndex("dialog_timeout");

        resultBean=new PrefixConfig();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.defaultCountry=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.dualBillingPrefix=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { resultBean.enabled=_cursor.getInt(index3)==0?false:true; }
        if (!_cursor.isNull(index4)) { resultBean.dialogTimeout=_cursor.getLong(index4); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM prefix_config WHERE id = ${id}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteById(long id) {
    if (deleteByIdPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM prefix_config WHERE id = ?";
      deleteByIdPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByIdPreparedStatement1);
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM prefix_config WHERE id = ?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteByIdPreparedStatement1, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM prefix_config WHERE id = ${bean.id}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean updateById(PrefixConfig bean) {
    if (updateByIdPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM prefix_config WHERE id = ?";
      updateByIdPreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateByIdPreparedStatement2);
    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM prefix_config WHERE id = ?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(updateByIdPreparedStatement2, _contentValues);
    return result!=0;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, default_country, dual_billing_prefix, enabled, dialog_timeout FROM prefix_config</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>default_country</dt><dd>is associated to bean's property <strong>defaultCountry</strong></dd>
   * 	<dt>dual_billing_prefix</dt><dd>is associated to bean's property <strong>dualBillingPrefix</strong></dd>
   * 	<dt>enabled</dt><dd>is associated to bean's property <strong>enabled</strong></dd>
   * 	<dt>dialog_timeout</dt><dd>is associated to bean's property <strong>dialogTimeout</strong></dd>
   * </dl>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public PrefixConfig selectOne() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL5;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
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
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      PrefixConfig resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("default_country");
        int index2=_cursor.getColumnIndex("dual_billing_prefix");
        int index3=_cursor.getColumnIndex("enabled");
        int index4=_cursor.getColumnIndex("dialog_timeout");

        resultBean=new PrefixConfig();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.defaultCountry=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.dualBillingPrefix=_cursor.getString(index2); }
        if (!_cursor.isNull(index3)) { resultBean.enabled=_cursor.getInt(index3)==0?false:true; }
        if (!_cursor.isNull(index4)) { resultBean.dialogTimeout=_cursor.getLong(index4); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE prefix_config SET default_country=:defaultCountry, dual_billing_prefix=:dualBillingPrefix, enabled=:enabled, dialog_timeout=:dialogTimeout WHERE id = ${bean.id} </pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>default_country</dt><dd>is mapped to <strong>${bean.defaultCountry}</strong></dd>
   * 	<dt>dual_billing_prefix</dt><dd>is mapped to <strong>${bean.dualBillingPrefix}</strong></dd>
   * 	<dt>enabled</dt><dd>is mapped to <strong>${bean.enabled}</strong></dd>
   * 	<dt>dialog_timeout</dt><dd>is mapped to <strong>${bean.dialogTimeout}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public int update(PrefixConfig bean) {
    if (updatePreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="UPDATE prefix_config SET default_country=?, dual_billing_prefix=?, enabled=?, dialog_timeout=? WHERE id = ? ";
      updatePreparedStatement3 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement3);
    _contentValues.put("default_country", bean.defaultCountry);
    _contentValues.put("dual_billing_prefix", bean.dualBillingPrefix);
    _contentValues.put("enabled", bean.enabled);
    _contentValues.put("dialog_timeout", bean.dialogTimeout);

    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE prefix_config SET default_country=:default_country, dual_billing_prefix=:dual_billing_prefix, enabled=:enabled, dialog_timeout=:dialog_timeout WHERE id = ? ");

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
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(updatePreparedStatement3, _contentValues);
    return result;
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
    if (deleteByIdPreparedStatement1!=null) {
      deleteByIdPreparedStatement1.close();
      deleteByIdPreparedStatement1=null;
    }
    if (updateByIdPreparedStatement2!=null) {
      updateByIdPreparedStatement2.close();
      updateByIdPreparedStatement2=null;
    }
    if (updatePreparedStatement3!=null) {
      updatePreparedStatement3.close();
      updatePreparedStatement3=null;
    }
  }
}
