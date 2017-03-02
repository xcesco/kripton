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
import sqlite.kripton111.model.Country;

/**
 * <p>
 * DAO implementation for entity <code>Country</code>, based on interface <code>CountryDao</code>
 * </p>
 *
 *  @see Country
 *  @see CountryDao
 *  @see sqlite.kripton111.model.CountryTable
 */
public class CountryDaoImpl extends AbstractDao implements CountryDao {
  public CountryDaoImpl(BindXenoDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO country (area, code, calling_code, region, name) VALUES (${bean.area}, ${bean.code}, ${bean.callingCode}, ${bean.region}, ${bean.name})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>area</dt><dd>is mapped to <strong>${bean.area}</strong></dd>
   * 	<dt>code</dt><dd>is mapped to <strong>${bean.code}</strong></dd>
   * 	<dt>calling_code</dt><dd>is mapped to <strong>${bean.callingCode}</strong></dd>
   * 	<dt>region</dt><dd>is mapped to <strong>${bean.region}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(Country bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("area", bean.area);

    if (bean.code!=null) {
      contentValues.put("code", bean.code);
    } else {
      contentValues.putNull("code");
    }

    if (bean.callingCode!=null) {
      contentValues.put("calling_code", bean.callingCode);
    } else {
      contentValues.putNull("calling_code");
    }

    if (bean.region!=null) {
      contentValues.put("region", bean.region);
    } else {
      contentValues.putNull("region");
    }

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT OR REPLACE INTO country (area, code, calling_code, region, name) VALUES ('"+StringUtils.checkSize(contentValues.get("area"))+"', '"+StringUtils.checkSize(contentValues.get("code"))+"', '"+StringUtils.checkSize(contentValues.get("calling_code"))+"', '"+StringUtils.checkSize(contentValues.get("region"))+"', '"+StringUtils.checkSize(contentValues.get("name"))+"')"));
    // use SQLiteDatabase conflicts algorithm
    long result = database().insertWithOnConflict("country", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    bean.id=result;

    return (int)result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, area, code, calling_code, region, name FROM country WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
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
  public Country selectById(long id) {
    // build where condition
    String[] _args={String.valueOf(id)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, area, code, calling_code, region, name FROM country WHERE id = '%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, area, code, calling_code, region, name FROM country WHERE id = ?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Country resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("area");
        int index2=cursor.getColumnIndex("code");
        int index3=cursor.getColumnIndex("calling_code");
        int index4=cursor.getColumnIndex("region");
        int index5=cursor.getColumnIndex("name");

        resultBean=new Country();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.area=cursor.getLong(index1); }
        resultBean.code=cursor.getString(index2);
        resultBean.callingCode=cursor.getString(index3);
        if (!cursor.isNull(index4)) { resultBean.region=cursor.getString(index4); }
        resultBean.name=cursor.getString(index5);

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE country WHERE id = ${id}</pre></pre>
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
    Logger.info(SqlUtils.formatSQL("DELETE country WHERE id = %s", (Object[])whereConditionsArray));
    int result = database().delete("country", "id = ?", whereConditionsArray);
    return result!=0;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, area, code, calling_code, region, name FROM country ORDER BY name asc</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Country> selectAll() {
    // build where condition
    String[] _args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, area, code, calling_code, region, name FROM country ORDER BY name asc",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, area, code, calling_code, region, name FROM country ORDER BY name asc", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Country> resultList=new LinkedList<Country>();
      Country resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("area");
        int index2=cursor.getColumnIndex("code");
        int index3=cursor.getColumnIndex("calling_code");
        int index4=cursor.getColumnIndex("region");
        int index5=cursor.getColumnIndex("name");

        do
         {
          resultBean=new Country();

          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.area=cursor.getLong(index1); }
          resultBean.code=cursor.getString(index2);
          resultBean.callingCode=cursor.getString(index3);
          if (!cursor.isNull(index4)) { resultBean.region=cursor.getString(index4); }
          resultBean.name=cursor.getString(index5);

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, area, code, calling_code, region, name FROM country WHERE callingCode = ${callingCode}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${callingCode}</dt><dd>is binded to method's parameter <strong>callingCode</strong></dd>
   * </dl>
   *
   * @param callingCode
   * 	is binded to <code>${callingCode}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Country selectByCallingCode(String callingCode) {
    // build where condition
    String[] _args={(callingCode==null?"":callingCode)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, area, code, calling_code, region, name FROM country WHERE calling_code = '%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, area, code, calling_code, region, name FROM country WHERE calling_code = ?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Country resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("area");
        int index2=cursor.getColumnIndex("code");
        int index3=cursor.getColumnIndex("calling_code");
        int index4=cursor.getColumnIndex("region");
        int index5=cursor.getColumnIndex("name");

        resultBean=new Country();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.area=cursor.getLong(index1); }
        resultBean.code=cursor.getString(index2);
        resultBean.callingCode=cursor.getString(index3);
        if (!cursor.isNull(index4)) { resultBean.region=cursor.getString(index4); }
        resultBean.name=cursor.getString(index5);

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, area, code, calling_code, region, name FROM country WHERE code = ${code}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${code}</dt><dd>is binded to method's parameter <strong>code</strong></dd>
   * </dl>
   *
   * @param code
   * 	is binded to <code>${code}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Country selectByCountry(String code) {
    // build where condition
    String[] _args={(code==null?"":code)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, area, code, calling_code, region, name FROM country WHERE code = '%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, area, code, calling_code, region, name FROM country WHERE code = ?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Country resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("area");
        int index2=cursor.getColumnIndex("code");
        int index3=cursor.getColumnIndex("calling_code");
        int index4=cursor.getColumnIndex("region");
        int index5=cursor.getColumnIndex("name");

        resultBean=new Country();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.area=cursor.getLong(index1); }
        resultBean.code=cursor.getString(index2);
        resultBean.callingCode=cursor.getString(index3);
        if (!cursor.isNull(index4)) { resultBean.region=cursor.getString(index4); }
        resultBean.name=cursor.getString(index5);

      }
      return resultBean;
    }
  }
}
