package sqlite.kripton111.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;
import sqlite.kripton111.model.ActionType;
import sqlite.kripton111.model.PhoneNumber;

/**
 * <p>
 * DAO implementation for entity <code>PhoneNumber</code>, based on interface <code>PhoneDao</code>
 * </p>
 *
 *  @see PhoneNumber
 *  @see PhoneDao
 *  @see sqlite.kripton111.model.PhoneNumberTable
 */
public class PhoneDaoImpl extends AbstractDao implements PhoneDao {
  public PhoneDaoImpl(BindXenoDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO phone_number (action, number, country_code, contact_name, contact_id) VALUES (${bean.action}, ${bean.number}, ${bean.countryCode}, ${bean.contactName}, ${bean.contactId})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>action</dt><dd>is mapped to <strong>${bean.action}</strong></dd>
   * 	<dt>number</dt><dd>is mapped to <strong>${bean.number}</strong></dd>
   * 	<dt>country_code</dt><dd>is mapped to <strong>${bean.countryCode}</strong></dd>
   * 	<dt>contact_name</dt><dd>is mapped to <strong>${bean.contactName}</strong></dd>
   * 	<dt>contact_id</dt><dd>is mapped to <strong>${bean.contactId}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(PhoneNumber bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.action!=null) {
      contentValues.put("action", bean.action.toString());
    } else {
      contentValues.putNull("action");
    }

    if (bean.number!=null) {
      contentValues.put("number", bean.number);
    } else {
      contentValues.putNull("number");
    }

    if (bean.countryCode!=null) {
      contentValues.put("country_code", bean.countryCode);
    } else {
      contentValues.putNull("country_code");
    }

    if (bean.contactName!=null) {
      contentValues.put("contact_name", bean.contactName);
    } else {
      contentValues.putNull("contact_name");
    }

    if (bean.contactId!=null) {
      contentValues.put("contact_id", bean.contactId);
    } else {
      contentValues.putNull("contact_id");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT OR REPLACE INTO phone_number (action, number, country_code, contact_name, contact_id) VALUES ('"+StringUtils.checkSize(contentValues.get("action"))+"', '"+StringUtils.checkSize(contentValues.get("number"))+"', '"+StringUtils.checkSize(contentValues.get("country_code"))+"', '"+StringUtils.checkSize(contentValues.get("contact_name"))+"', '"+StringUtils.checkSize(contentValues.get("contact_id"))+"')"));
    // use SQLiteDatabase conflicts algorithm
    long result = database().insertWithOnConflict("phone_number", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    bean.id=result;

    return (int)result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>action</dt><dd>is associated to bean's property <strong>action</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>country_code</dt><dd>is associated to bean's property <strong>countryCode</strong></dd>
   * 	<dt>contact_name</dt><dd>is associated to bean's property <strong>contactName</strong></dd>
   * 	<dt>contact_id</dt><dd>is associated to bean's property <strong>contactId</strong></dd>
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
  public PhoneNumber selectById(long id) {
    // build where condition
    String[] _args={String.valueOf(id)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number WHERE id = '%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number WHERE id = ?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      PhoneNumber resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("action");
        int index2=cursor.getColumnIndex("number");
        int index3=cursor.getColumnIndex("country_code");
        int index4=cursor.getColumnIndex("contact_name");
        int index5=cursor.getColumnIndex("contact_id");

        resultBean=new PhoneNumber();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.action=ActionType.valueOf(cursor.getString(index1)); }
        if (!cursor.isNull(index2)) { resultBean.number=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.countryCode=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.contactName=cursor.getString(index4); }
        if (!cursor.isNull(index5)) { resultBean.contactId=cursor.getString(index5); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE phone_number WHERE id = ${id}</pre></pre>
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
    Logger.info(SqlUtils.formatSQL("DELETE phone_number WHERE id = %s", (Object[])whereConditionsArray));
    int result = database().delete("phone_number", "id = ?", whereConditionsArray);
    return result!=0;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number WHERE number = ${number}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>action</dt><dd>is associated to bean's property <strong>action</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>country_code</dt><dd>is associated to bean's property <strong>countryCode</strong></dd>
   * 	<dt>contact_name</dt><dd>is associated to bean's property <strong>contactName</strong></dd>
   * 	<dt>contact_id</dt><dd>is associated to bean's property <strong>contactId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${number}</dt><dd>is binded to method's parameter <strong>number</strong></dd>
   * </dl>
   *
   * @param number
   * 	is binded to <code>${number}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public PhoneNumber selectByNumber(String number) {
    // build where condition
    String[] _args={(number==null?"":number)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number WHERE number = '%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number WHERE number = ?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      PhoneNumber resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("action");
        int index2=cursor.getColumnIndex("number");
        int index3=cursor.getColumnIndex("country_code");
        int index4=cursor.getColumnIndex("contact_name");
        int index5=cursor.getColumnIndex("contact_id");

        resultBean=new PhoneNumber();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.action=ActionType.valueOf(cursor.getString(index1)); }
        if (!cursor.isNull(index2)) { resultBean.number=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.countryCode=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.contactName=cursor.getString(index4); }
        if (!cursor.isNull(index5)) { resultBean.contactId=cursor.getString(index5); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number ORDER BY contactName, number</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>action</dt><dd>is associated to bean's property <strong>action</strong></dd>
   * 	<dt>number</dt><dd>is associated to bean's property <strong>number</strong></dd>
   * 	<dt>country_code</dt><dd>is associated to bean's property <strong>countryCode</strong></dd>
   * 	<dt>contact_name</dt><dd>is associated to bean's property <strong>contactName</strong></dd>
   * 	<dt>contact_id</dt><dd>is associated to bean's property <strong>contactId</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<PhoneNumber> selectAll() {
    // build where condition
    String[] _args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number ORDER BY contact_name, number",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, action, number, country_code, contact_name, contact_id FROM phone_number ORDER BY contact_name, number", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<PhoneNumber> resultList=new LinkedList<PhoneNumber>();
      PhoneNumber resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("action");
        int index2=cursor.getColumnIndex("number");
        int index3=cursor.getColumnIndex("country_code");
        int index4=cursor.getColumnIndex("contact_name");
        int index5=cursor.getColumnIndex("contact_id");

        do
         {
          resultBean=new PhoneNumber();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.action=ActionType.valueOf(cursor.getString(index1)); }
          if (!cursor.isNull(index2)) { resultBean.number=cursor.getString(index2); }
          if (!cursor.isNull(index3)) { resultBean.countryCode=cursor.getString(index3); }
          if (!cursor.isNull(index4)) { resultBean.contactName=cursor.getString(index4); }
          if (!cursor.isNull(index5)) { resultBean.contactId=cursor.getString(index5); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }
}
