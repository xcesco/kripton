package sqlite.feature.many2many.case4.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
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
public class CountryDaoImpl extends Dao implements CountryDao {
  private static SQLiteStatement insertPreparedStatement0;

  private static final String SELECT_BY_ID_SQL6 = "SELECT id, area, calling_code, code, name, region, translated_name FROM country WHERE id = ?";

  private static SQLiteStatement deleteByIdPreparedStatement1;

  private static SQLiteStatement updateByIdPreparedStatement2;

  private static final String SELECT_ALL_SQL7 = "SELECT id, area, calling_code, code, name, region, translated_name FROM country ORDER BY name asc";

  private static final String SELECT_BY_CALLING_CODE_SQL8 = "SELECT id, area, calling_code, code, name, region, translated_name FROM country WHERE calling_code = ?";

  private static final String SELECT_BY_COUNTRY_SQL9 = "SELECT id, area, calling_code, code, name, region, translated_name FROM country WHERE code = ?";

  public CountryDaoImpl(BindXenoDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR REPLACE INTO country (area, calling_code, code, name, region, translated_name) VALUES (:area, :callingCode, :code, :name, :region, :translatedName)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>area</dt><dd>is mapped to <strong>:bean.area</strong></dd>
   * 	<dt>calling_code</dt><dd>is mapped to <strong>:bean.callingCode</strong></dd>
   * 	<dt>code</dt><dd>is mapped to <strong>:bean.code</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>region</dt><dd>is mapped to <strong>:bean.region</strong></dd>
   * 	<dt>translated_name</dt><dd>is mapped to <strong>:bean.translatedName</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(Country bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT OR REPLACE INTO country (area, calling_code, code, name, region, translated_name) VALUES (?, ?, ?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("area", bean.area);
    _contentValues.put("calling_code", bean.callingCode);
    _contentValues.put("code", bean.code);
    _contentValues.put("name", bean.name);
    _contentValues.put("region", bean.region);
    _contentValues.put("translated_name", CountryTable.serializeTranslatedName(bean.translatedName));

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
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement0, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;

    return (int)result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, area, calling_code, code, name, region, translated_name FROM country WHERE id = ${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Country}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>translated_name</dt><dd>is associated to bean's property <strong>translatedName</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Country selectById(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL6;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Country resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("area");
        int index2=_cursor.getColumnIndex("calling_code");
        int index3=_cursor.getColumnIndex("code");
        int index4=_cursor.getColumnIndex("name");
        int index5=_cursor.getColumnIndex("region");
        int index6=_cursor.getColumnIndex("translated_name");

        resultBean=new Country();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.area=_cursor.getLong(index1); }
        resultBean.callingCode=_cursor.getString(index2);
        resultBean.code=_cursor.getString(index3);
        resultBean.name=_cursor.getString(index4);
        if (!_cursor.isNull(index5)) { resultBean.region=_cursor.getString(index5); }
        if (!_cursor.isNull(index6)) { resultBean.translatedName=CountryTable.parseTranslatedName(_cursor.getBlob(index6)); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM country WHERE id = :id</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>:id</strong>
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
    int result = KriptonDatabaseWrapper.updateDelete(deleteByIdPreparedStatement1, _contentValues);
    return result!=0;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM country WHERE id = ${bean.id}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
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
    int result = KriptonDatabaseWrapper.updateDelete(updateByIdPreparedStatement2, _contentValues);
    return result!=0;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, area, calling_code, code, name, region, translated_name FROM country ORDER BY name asc</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Country}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>translated_name</dt><dd>is associated to bean's property <strong>translatedName</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Country> selectAll() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL7;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Country> resultList=new ArrayList<Country>(_cursor.getCount());
      Country resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("area");
        int index2=_cursor.getColumnIndex("calling_code");
        int index3=_cursor.getColumnIndex("code");
        int index4=_cursor.getColumnIndex("name");
        int index5=_cursor.getColumnIndex("region");
        int index6=_cursor.getColumnIndex("translated_name");

        do
         {
          resultBean=new Country();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.area=_cursor.getLong(index1); }
          resultBean.callingCode=_cursor.getString(index2);
          resultBean.code=_cursor.getString(index3);
          resultBean.name=_cursor.getString(index4);
          if (!_cursor.isNull(index5)) { resultBean.region=_cursor.getString(index5); }
          if (!_cursor.isNull(index6)) { resultBean.translatedName=CountryTable.parseTranslatedName(_cursor.getBlob(index6)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, area, calling_code, code, name, region, translated_name FROM country WHERE calling_code = ${callingCode}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Country}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>translated_name</dt><dd>is associated to bean's property <strong>translatedName</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:callingCode</dt><dd>is binded to method's parameter <strong>callingCode</strong></dd>
   * </dl>
   *
   * @param callingCode
   * 	is binded to <code>:callingCode</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Country selectByCallingCode(String callingCode) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_CALLING_CODE_SQL8;
    // add where arguments
    _contentValues.addWhereArgs((callingCode==null?"":callingCode));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Country resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("area");
        int index2=_cursor.getColumnIndex("calling_code");
        int index3=_cursor.getColumnIndex("code");
        int index4=_cursor.getColumnIndex("name");
        int index5=_cursor.getColumnIndex("region");
        int index6=_cursor.getColumnIndex("translated_name");

        resultBean=new Country();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.area=_cursor.getLong(index1); }
        resultBean.callingCode=_cursor.getString(index2);
        resultBean.code=_cursor.getString(index3);
        resultBean.name=_cursor.getString(index4);
        if (!_cursor.isNull(index5)) { resultBean.region=_cursor.getString(index5); }
        if (!_cursor.isNull(index6)) { resultBean.translatedName=CountryTable.parseTranslatedName(_cursor.getBlob(index6)); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, area, calling_code, code, name, region, translated_name FROM country WHERE code = ${code}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Country}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>area</dt><dd>is associated to bean's property <strong>area</strong></dd>
   * 	<dt>calling_code</dt><dd>is associated to bean's property <strong>callingCode</strong></dd>
   * 	<dt>code</dt><dd>is associated to bean's property <strong>code</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>region</dt><dd>is associated to bean's property <strong>region</strong></dd>
   * 	<dt>translated_name</dt><dd>is associated to bean's property <strong>translatedName</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:code</dt><dd>is binded to method's parameter <strong>code</strong></dd>
   * </dl>
   *
   * @param code
   * 	is binded to <code>:code</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Country selectByCountry(String code) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_COUNTRY_SQL9;
    // add where arguments
    _contentValues.addWhereArgs((code==null?"":code));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
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
    // log section for select END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Country resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("area");
        int index2=_cursor.getColumnIndex("calling_code");
        int index3=_cursor.getColumnIndex("code");
        int index4=_cursor.getColumnIndex("name");
        int index5=_cursor.getColumnIndex("region");
        int index6=_cursor.getColumnIndex("translated_name");

        resultBean=new Country();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.area=_cursor.getLong(index1); }
        resultBean.callingCode=_cursor.getString(index2);
        resultBean.code=_cursor.getString(index3);
        resultBean.name=_cursor.getString(index4);
        if (!_cursor.isNull(index5)) { resultBean.region=_cursor.getString(index5); }
        if (!_cursor.isNull(index6)) { resultBean.translatedName=CountryTable.parseTranslatedName(_cursor.getBlob(index6)); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
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
