package sqlite.feature.many2many.case4.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;
import sqlite.feature.many2many.case4.model.Country;
import sqlite.feature.many2many.case4.model.CountryTable;

/**
 * <p>
 * DAO implementation for entity <code>Country</code>, based on interface <code>CountryDao</code>
 * </p>
 *
 *  @see Country
 *  @see CountryDao
 *  @see CountryTable
 */
public class CountryDaoImpl extends AbstractDao implements CountryDao {
  private static SQLiteStatement insertPreparedStatement0;

  private static final String SELECT_BY_ID_SQL6 = "SELECT id, area, code, calling_code, region, name, translated_name FROM country WHERE id = ?";

  private static SQLiteStatement deleteByIdPreparedStatement1;

  private static SQLiteStatement updateByIdPreparedStatement2;

  private static final String SELECT_ALL_SQL7 = "SELECT id, area, code, calling_code, region, name, translated_name FROM country ORDER BY name asc";

  private static final String SELECT_BY_CALLING_CODE_SQL8 = "SELECT id, area, code, calling_code, region, name, translated_name FROM country WHERE calling_code = ?";

  private static final String SELECT_BY_COUNTRY_SQL9 = "SELECT id, area, code, calling_code, region, name, translated_name FROM country WHERE code = ?";

  public CountryDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT OR REPLACE INTO country (area, code, calling_code, region, name, translated_name) VALUES (${area}, ${code}, ${callingCode}, ${region}, ${name}, ${translatedName})</pre>
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
   * 	<dt>translated_name</dt><dd>is mapped to <strong>${bean.translatedName}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(Country bean) {
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT OR REPLACE INTO country (area, code, calling_code, region, name, translated_name) VALUES (?, ?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("area", bean.area);
    if (bean.code!=null) {
      _contentValues.put("code", bean.code);
    } else {
      _contentValues.putNull("code");
    }
    if (bean.callingCode!=null) {
      _contentValues.put("calling_code", bean.callingCode);
    } else {
      _contentValues.putNull("calling_code");
    }
    if (bean.region!=null) {
      _contentValues.put("region", bean.region);
    } else {
      _contentValues.putNull("region");
    }
    if (bean.name!=null) {
      _contentValues.put("name", bean.name);
    } else {
      _contentValues.putNull("name");
    }
    if (bean.translatedName!=null) {
      _contentValues.put("translated_name", CountryTable.serializeTranslatedName(bean.translatedName));
    } else {
      _contentValues.putNull("translated_name");
    }

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
      Logger.info("INSERT OR REPLACE INTO country (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(_context, insertPreparedStatement0, _contentValues);
    bean.id=result;

    return (int)result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, area, code, calling_code, region, name, translated_name FROM country WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>translated_name</dt><dd>is associated to bean's property <strong>translatedName</strong></dd>
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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL6;
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      Country resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("area");
        int index2=cursor.getColumnIndex("code");
        int index3=cursor.getColumnIndex("calling_code");
        int index4=cursor.getColumnIndex("region");
        int index5=cursor.getColumnIndex("name");
        int index6=cursor.getColumnIndex("translated_name");

        resultBean=new Country();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.area=cursor.getLong(index1); }
        resultBean.code=cursor.getString(index2);
        resultBean.callingCode=cursor.getString(index3);
        if (!cursor.isNull(index4)) { resultBean.region=cursor.getString(index4); }
        resultBean.name=cursor.getString(index5);
        if (!cursor.isNull(index6)) { resultBean.translatedName=CountryTable.parseTranslatedName(cursor.getBlob(index6)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM country WHERE id = ${id}</pre>
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
      String _sql="DELETE FROM country WHERE id = ?";
      deleteByIdPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByIdPreparedStatement1);
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM country WHERE id = ?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(_context, deleteByIdPreparedStatement1, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM country WHERE id = ${bean.id}</pre>
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
  public boolean updateById(Country bean) {
    if (updateByIdPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM country WHERE id = ?";
      updateByIdPreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateByIdPreparedStatement2);
    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM country WHERE id = ?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(_context, updateByIdPreparedStatement2, _contentValues);
    return result!=0;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, area, code, calling_code, region, name, translated_name FROM country ORDER BY name asc</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>translated_name</dt><dd>is associated to bean's property <strong>translatedName</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Country> selectAll() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL7;
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<Country> resultList=new ArrayList<Country>(cursor.getCount());
      Country resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("area");
        int index2=cursor.getColumnIndex("code");
        int index3=cursor.getColumnIndex("calling_code");
        int index4=cursor.getColumnIndex("region");
        int index5=cursor.getColumnIndex("name");
        int index6=cursor.getColumnIndex("translated_name");

        do
         {
          resultBean=new Country();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.area=cursor.getLong(index1); }
          resultBean.code=cursor.getString(index2);
          resultBean.callingCode=cursor.getString(index3);
          if (!cursor.isNull(index4)) { resultBean.region=cursor.getString(index4); }
          resultBean.name=cursor.getString(index5);
          if (!cursor.isNull(index6)) { resultBean.translatedName=CountryTable.parseTranslatedName(cursor.getBlob(index6)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, area, code, calling_code, region, name, translated_name FROM country WHERE calling_code = ${callingCode}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>translated_name</dt><dd>is associated to bean's property <strong>translatedName</strong></dd>
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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_CALLING_CODE_SQL8;
    // add where arguments
    _contentValues.addWhereArgs((callingCode==null?"":callingCode));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      Country resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("area");
        int index2=cursor.getColumnIndex("code");
        int index3=cursor.getColumnIndex("calling_code");
        int index4=cursor.getColumnIndex("region");
        int index5=cursor.getColumnIndex("name");
        int index6=cursor.getColumnIndex("translated_name");

        resultBean=new Country();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.area=cursor.getLong(index1); }
        resultBean.code=cursor.getString(index2);
        resultBean.callingCode=cursor.getString(index3);
        if (!cursor.isNull(index4)) { resultBean.region=cursor.getString(index4); }
        resultBean.name=cursor.getString(index5);
        if (!cursor.isNull(index6)) { resultBean.translatedName=CountryTable.parseTranslatedName(cursor.getBlob(index6)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, area, code, calling_code, region, name, translated_name FROM country WHERE code = ${code}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>translated_name</dt><dd>is associated to bean's property <strong>translatedName</strong></dd>
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
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_COUNTRY_SQL9;
    // add where arguments
    _contentValues.addWhereArgs((code==null?"":code));
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
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      Country resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("area");
        int index2=cursor.getColumnIndex("code");
        int index3=cursor.getColumnIndex("calling_code");
        int index4=cursor.getColumnIndex("region");
        int index5=cursor.getColumnIndex("name");
        int index6=cursor.getColumnIndex("translated_name");

        resultBean=new Country();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.area=cursor.getLong(index1); }
        resultBean.code=cursor.getString(index2);
        resultBean.callingCode=cursor.getString(index3);
        if (!cursor.isNull(index4)) { resultBean.region=cursor.getString(index4); }
        resultBean.name=cursor.getString(index5);
        if (!cursor.isNull(index6)) { resultBean.translatedName=CountryTable.parseTranslatedName(cursor.getBlob(index6)); }

      }
      return resultBean;
    }
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
  }
}
