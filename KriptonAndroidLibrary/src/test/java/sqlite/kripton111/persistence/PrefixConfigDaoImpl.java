package sqlite.kripton111.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
import sqlite.kripton111.model.PrefixConfig;

/**
 * <p>
 * DAO implementation for entity <code>PrefixConfig</code>, based on interface <code>PrefixConfigDao</code>
 * </p>
 *
 *  @see PrefixConfig
 *  @see PrefixConfigDao
 *  @see sqlite.kripton111.model.PrefixConfigTable
 */
public class PrefixConfigDaoImpl extends AbstractDao implements PrefixConfigDao {
  public PrefixConfigDaoImpl(BindXenoDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO prefix_config (default_country, dual_billing_prefix, enabled, dialog_timeout) VALUES (${bean.defaultCountry}, ${bean.dualBillingPrefix}, ${bean.enabled}, ${bean.dialogTimeout})</pre>
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

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT OR REPLACE INTO prefix_config (default_country, dual_billing_prefix, enabled, dialog_timeout) VALUES ('"+StringUtils.checkSize(contentValues.get("default_country"))+"', '"+StringUtils.checkSize(contentValues.get("dual_billing_prefix"))+"', '"+StringUtils.checkSize(contentValues.get("enabled"))+"', '"+StringUtils.checkSize(contentValues.get("dialog_timeout"))+"')"));
    // use SQLiteDatabase conflicts algorithm
    long result = database().insertWithOnConflict("prefix_config", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
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
    // build where condition
    String[] _args={String.valueOf(id)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, default_country, dual_billing_prefix, enabled, dialog_timeout FROM prefix_config WHERE id = '%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, default_country, dual_billing_prefix, enabled, dialog_timeout FROM prefix_config WHERE id = ?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      PrefixConfig resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("default_country");
        int index2=cursor.getColumnIndex("dual_billing_prefix");
        int index3=cursor.getColumnIndex("enabled");
        int index4=cursor.getColumnIndex("dialog_timeout");

        resultBean=new PrefixConfig();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.defaultCountry=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.dualBillingPrefix=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.enabled=cursor.getInt(index3)==0?false:true; }
        if (!cursor.isNull(index4)) { resultBean.dialogTimeout=cursor.getLong(index4); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE prefix_config WHERE id = ${id}</pre></pre>
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
    String[] whereConditionsArray={String.valueOf(id)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE prefix_config WHERE id = %s", (Object[])whereConditionsArray));
    int result = database().delete("prefix_config", "id = ?", whereConditionsArray);
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
    // build where condition
    String[] _args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, default_country, dual_billing_prefix, enabled, dialog_timeout FROM prefix_config",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, default_country, dual_billing_prefix, enabled, dialog_timeout FROM prefix_config", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      PrefixConfig resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("default_country");
        int index2=cursor.getColumnIndex("dual_billing_prefix");
        int index3=cursor.getColumnIndex("enabled");
        int index4=cursor.getColumnIndex("dialog_timeout");

        resultBean=new PrefixConfig();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
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
   * <pre>UPDATE prefix_config SET default_country=${bean.defaultCountry}, dual_billing_prefix=${bean.dualBillingPrefix}, enabled=${bean.enabled}, dialog_timeout=${bean.dialogTimeout} WHERE id = ${bean.id}</pre>
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

    String[] whereConditionsArray={String.valueOf(bean.id)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE prefix_config SET default_country='"+StringUtils.checkSize(contentValues.get("default_country"))+"', dual_billing_prefix='"+StringUtils.checkSize(contentValues.get("dual_billing_prefix"))+"', enabled='"+StringUtils.checkSize(contentValues.get("enabled"))+"', dialog_timeout='"+StringUtils.checkSize(contentValues.get("dialog_timeout"))+"' WHERE id = '%s'", (Object[]) whereConditionsArray));
    int result = database().update("prefix_config", contentValues, "id = ?", whereConditionsArray);
    return result;
  }
}
