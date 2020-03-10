package sqlite.feature.pkstring.case1;

import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.common.SQLDateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.io.IOException;
import java.sql.Date;

/**
 * <p>
 * DAO implementation for entity <code>ZArtist</code>, based on interface <code>DaoZArtist</code>
 * </p>
 *
 *  @see ZArtist
 *  @see DaoZArtist
 *  @see ZArtistTable
 */
public class DaoZArtistImpl extends Dao implements DaoZArtist {
  private static SupportSQLiteStatement insertPreparedStatement0;

  private static SupportSQLiteStatement insert1PreparedStatement1;

  private static SupportSQLiteStatement insert2PreparedStatement2;

  private static SupportSQLiteStatement insert3PreparedStatement3;

  private static SupportSQLiteStatement insert4PreparedStatement4;

  private static SupportSQLiteStatement insert5PreparedStatement5;

  private static SupportSQLiteStatement updatePreparedStatement6;

  private static SupportSQLiteStatement update2PreparedStatement7;

  private static SupportSQLiteStatement update3PreparedStatement8;

  public DaoZArtistImpl(BindAppDaoFactory daoFactory) {
    super(daoFactory.getContext());
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO z_artist (name, year) VALUES (:name, :year)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>year</dt><dd>is mapped to <strong>:bean.year</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insert(ZArtist bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO z_artist (name, year) VALUES (?, ?)";
      insertPreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("name", bean.name);
    _contentValues.put("year", SQLDateUtils.write(bean.year));

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
      Logger.info("INSERT INTO z_artist (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertPreparedStatement0, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO z_artist (name, year) VALUES (:name, :year)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>year</dt><dd>is mapped to <strong>:bean.year</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public String insert1(ZArtist bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insert1PreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO z_artist (name, year) VALUES (?, ?)";
      insert1PreparedStatement1 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insert1PreparedStatement1);
    _contentValues.put("name", bean.name);
    _contentValues.put("year", SQLDateUtils.write(bean.year));

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
      Logger.info("INSERT INTO z_artist (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insert1PreparedStatement1, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;

    return String.valueOf(result);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO z_artist (name, year) VALUES (:name, :year)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>year</dt><dd>is mapped to <strong>:bean.year</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert2(ZArtist bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insert2PreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO z_artist (name, year) VALUES (?, ?)";
      insert2PreparedStatement2 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insert2PreparedStatement2);
    _contentValues.put("name", bean.name);
    _contentValues.put("year", SQLDateUtils.write(bean.year));

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
      Logger.info("INSERT INTO z_artist (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insert2PreparedStatement2, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;

    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO z_artist (year) VALUES (:year)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>year</dt><dd>is binded to query's parameter <strong>:year</strong> and method's parameter <strong>year</strong></dd>
   * </dl>
   *
   * @param year
   * 	is binded to column value <strong>year</strong>
   *
   */
  @Override
  public void insert3(Date year) {
    // Specialized Insert - InsertType - BEGIN
    if (insert3PreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO z_artist (year) VALUES (?)";
      insert3PreparedStatement3 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insert3PreparedStatement3);

    _contentValues.put("year", SQLDateUtils.write(year));

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
      Logger.info("INSERT INTO z_artist (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insert3PreparedStatement3, _contentValues);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO z_artist (year) VALUES (:year)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>year</dt><dd>is binded to query's parameter <strong>:year</strong> and method's parameter <strong>year</strong></dd>
   * </dl>
   *
   * @param year
   * 	is binded to column value <strong>year</strong>
   *
   */
  @Override
  public String insert4(Date year) {
    // Specialized Insert - InsertType - BEGIN
    if (insert4PreparedStatement4==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO z_artist (year) VALUES (?)";
      insert4PreparedStatement4 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insert4PreparedStatement4);

    _contentValues.put("year", SQLDateUtils.write(year));

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
      Logger.info("INSERT INTO z_artist (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insert4PreparedStatement4, _contentValues);
    return String.valueOf(result);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO z_artist (year) VALUES (:year)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>year</dt><dd>is binded to query's parameter <strong>:year</strong> and method's parameter <strong>year</strong></dd>
   * </dl>
   *
   * @param year
   * 	is binded to column value <strong>year</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert5(Date year) {
    // Specialized Insert - InsertType - BEGIN
    if (insert5PreparedStatement5==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO z_artist (year) VALUES (?)";
      insert5PreparedStatement5 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insert5PreparedStatement5);

    _contentValues.put("year", SQLDateUtils.write(year));

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
      Logger.info("INSERT INTO z_artist (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insert5PreparedStatement5, _contentValues);
    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE z_artist SET name=:name, year=:year WHERE name=:bean.name</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>year</dt><dd>is mapped to <strong>:bean.year</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.name</dt><dd>is mapped to method's parameter <strong>bean.name</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   */
  @Override
  public void update(ZArtist bean) {
    if (updatePreparedStatement6==null) {
      // generate static SQL for statement
      String _sql="UPDATE z_artist SET name=?, year=? WHERE name=?";
      updatePreparedStatement6 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement6);
    _contentValues.put("name", bean.name);
    _contentValues.put("year", SQLDateUtils.write(bean.year));

    _contentValues.addWhereArgs((bean.name==null?"":bean.name));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE z_artist SET name=:name, year=:year WHERE name=?");

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
    int result = KriptonDatabaseHelper.updateDelete(updatePreparedStatement6, _contentValues);
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE z_artist SET name=:name, year=:year WHERE name=:bean.name</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>year</dt><dd>is mapped to <strong>:bean.year</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.name</dt><dd>is mapped to method's parameter <strong>bean.name</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return number of updated records
   */
  @Override
  public int update2(ZArtist bean) {
    if (update2PreparedStatement7==null) {
      // generate static SQL for statement
      String _sql="UPDATE z_artist SET name=?, year=? WHERE name=?";
      update2PreparedStatement7 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(update2PreparedStatement7);
    _contentValues.put("name", bean.name);
    _contentValues.put("year", SQLDateUtils.write(bean.year));

    _contentValues.addWhereArgs((bean.name==null?"":bean.name));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE z_artist SET name=:name, year=:year WHERE name=?");

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
    int result = KriptonDatabaseHelper.updateDelete(update2PreparedStatement7, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE z_artist SET name=:name, year=:year WHERE name=:bean.name</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>year</dt><dd>is mapped to <strong>:bean.year</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.name</dt><dd>is mapped to method's parameter <strong>bean.name</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return number of updated records
   */
  @Override
  public long update3(ZArtist bean) {
    if (update3PreparedStatement8==null) {
      // generate static SQL for statement
      String _sql="UPDATE z_artist SET name=?, year=? WHERE name=?";
      update3PreparedStatement8 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(update3PreparedStatement8);
    _contentValues.put("name", bean.name);
    _contentValues.put("year", SQLDateUtils.write(bean.year));

    _contentValues.addWhereArgs((bean.name==null?"":bean.name));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE z_artist SET name=:name, year=:year WHERE name=?");

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
    int result = KriptonDatabaseHelper.updateDelete(update3PreparedStatement8, _contentValues);
    return result;
  }

  public static void clearCompiledStatements() {
    try {
      if (insertPreparedStatement0!=null) {
        insertPreparedStatement0.close();
        insertPreparedStatement0=null;
      }
      if (insert1PreparedStatement1!=null) {
        insert1PreparedStatement1.close();
        insert1PreparedStatement1=null;
      }
      if (insert2PreparedStatement2!=null) {
        insert2PreparedStatement2.close();
        insert2PreparedStatement2=null;
      }
      if (insert3PreparedStatement3!=null) {
        insert3PreparedStatement3.close();
        insert3PreparedStatement3=null;
      }
      if (insert4PreparedStatement4!=null) {
        insert4PreparedStatement4.close();
        insert4PreparedStatement4=null;
      }
      if (insert5PreparedStatement5!=null) {
        insert5PreparedStatement5.close();
        insert5PreparedStatement5=null;
      }
      if (updatePreparedStatement6!=null) {
        updatePreparedStatement6.close();
        updatePreparedStatement6=null;
      }
      if (update2PreparedStatement7!=null) {
        update2PreparedStatement7.close();
        update2PreparedStatement7=null;
      }
      if (update3PreparedStatement8!=null) {
        update3PreparedStatement8.close();
        update3PreparedStatement8=null;
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
