package sqlite.feature.rx.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SQLiteModification;
import com.abubusoft.kripton.common.StringUtils;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import sqlite.feature.rx.model.PrefixConfig;

/**
 * <p>
 * DAO implementation for entity <code>PrefixConfig</code>, based on interface <code>PrefixConfigDao</code>
 * </p>
 *
 *  @see PrefixConfig
 *  @see PrefixConfigDao
 *  @see sqlite.feature.rx.model.PrefixConfigTable
 */
public class PrefixConfigDaoImpl extends AbstractDao implements PrefixConfigDao {
  protected final PublishSubject<SQLiteModification> subject = PublishSubject.create();

  public PrefixConfigDaoImpl(BindXenoDataSource dataSet) {
    super(dataSet);
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
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.defaultCountry!=null) {
      contentValues.put("default_country", bean.defaultCountry);
    } else {
      contentValues.putNull("default_country");
    }
    if (bean.dualBillingPrefix!=null) {
      contentValues.put("dual_billing_prefix", bean.dualBillingPrefix);
    } else {
      contentValues.putNull("dual_billing_prefix");
    }
    contentValues.put("enabled", bean.enabled);
    contentValues.put("dialog_timeout", bean.dialogTimeout);

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT OR REPLACE INTO prefix_config (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    // conflict algorithm REPLACE
    long result = database().insertWithOnConflict("prefix_config", null, contentValues, 5);
    subject.onNext(SQLiteModification.createInsert(result));
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
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, default_country, dual_billing_prefix, enabled, dialog_timeout FROM prefix_config");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      PrefixConfig resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("default_country");
        int index2=cursor.getColumnIndex("dual_billing_prefix");
        int index3=cursor.getColumnIndex("enabled");
        int index4=cursor.getColumnIndex("dialog_timeout");

        resultBean=new PrefixConfig();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.defaultCountry=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.dualBillingPrefix=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.enabled=cursor.getInt(index3)==0?false:true; }
        if (!cursor.isNull(index4)) { resultBean.dialogTimeout=cursor.getLong(index4); }

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
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM prefix_config WHERE id = ?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("prefix_config", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    subject.onNext(SQLiteModification.createDelete(result));
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
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM prefix_config WHERE id = ?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("prefix_config", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    subject.onNext(SQLiteModification.createDelete(result));
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
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, default_country, dual_billing_prefix, enabled, dialog_timeout FROM prefix_config");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    String _sqlWhereStatement="";

    // build where condition
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      PrefixConfig resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("default_country");
        int index2=cursor.getColumnIndex("dual_billing_prefix");
        int index3=cursor.getColumnIndex("enabled");
        int index4=cursor.getColumnIndex("dialog_timeout");

        resultBean=new PrefixConfig();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.defaultCountry=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.dualBillingPrefix=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.enabled=cursor.getInt(index3)==0?false:true; }
        if (!cursor.isNull(index4)) { resultBean.dialogTimeout=cursor.getLong(index4); }

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
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.defaultCountry!=null) {
      contentValues.put("default_country", bean.defaultCountry);
    } else {
      contentValues.putNull("default_country");
    }
    if (bean.dualBillingPrefix!=null) {
      contentValues.put("dual_billing_prefix", bean.dualBillingPrefix);
    } else {
      contentValues.putNull("dual_billing_prefix");
    }
    contentValues.put("enabled", bean.enabled);
    contentValues.put("dialog_timeout", bean.dialogTimeout);

    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id = ?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE prefix_config SET default_country=:defaultCountry, dual_billing_prefix=:dualBillingPrefix, enabled=:enabled, dialog_timeout=:dialogTimeout WHERE id = ? ");

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().update("prefix_config", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
    subject.onNext(SQLiteModification.createUpdate(result));
    return result;
  }

  public PublishSubject<SQLiteModification> subject() {
    return subject;
  }
}
