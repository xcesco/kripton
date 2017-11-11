package sqlite.kripton49.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;
import sqlite.kripton49.entities.Bean01Entity;

/**
 * <p>
 * DAO implementation for entity <code>Bean01Entity</code>, based on interface <code>DaoBean01</code>
 * </p>
 *
 *  @see Bean01Entity
 *  @see DaoBean01
 *  @see sqlite.kripton49.entities.Bean01EntityTable
 */
public class DaoBean01Impl extends AbstractDao implements DaoBean01 {
  protected String SELECT_ONE_SQL1 = "SELECT id, text FROM bean01 WHERE id=?";

  protected String SELECT_BY_ID_SQL2 = "SELECT id, text FROM bean01 WHERE id=?";

  private SQLiteStatement updateOnePreparedStatement0;

  private SQLiteStatement deleteOnePreparedStatement1;

  private SQLiteStatement insertOnePreparedStatement2;

  private SQLiteStatement insertOnePreparedStatement3;

  public DaoBean01Impl(BindDummy01DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, text FROM bean01 WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
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
  public Bean01Entity selectOne(Long id) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL1;
    // add where arguments
    _contentValues.addWhereArgs((id==null?"":String.valueOf(id)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
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
      if (this.dataSource.logEnabled) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      Bean01Entity resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("text");

        resultBean=new Bean01Entity();

        resultBean.setId(cursor.getLong(index0));
        if (!cursor.isNull(index1)) { resultBean.setText(cursor.getString(index1)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, text FROM bean01 WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean01Entity> selectById(Long id) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL2;
    // add where arguments
    _contentValues.addWhereArgs((id==null?"":String.valueOf(id)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (this.dataSource.logEnabled) {
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
      if (this.dataSource.logEnabled) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      ArrayList<Bean01Entity> resultList=new ArrayList<Bean01Entity>(cursor.getCount());
      Bean01Entity resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("text");

        do
         {
          resultBean=new Bean01Entity();

          resultBean.setId(cursor.getLong(index0));
          if (!cursor.isNull(index1)) { resultBean.setText(cursor.getString(index1)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE bean01 SET text=:text WHERE id=${id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>text</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param text
   * 	is used as updated field <strong>text</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(String text, Long id) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (text!=null) {
      _contentValues.put("text", text);
    } else {
      _contentValues.putNull("text");
    }

    _contentValues.addWhereArgs((id==null?"":String.valueOf(id)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (updateOnePreparedStatement0==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" id=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="UPDATE bean01 SET text=? WHERE id=?";
      updateOnePreparedStatement0 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("UPDATE bean01 SET text=:text WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, updateOnePreparedStatement0, _contentValues);
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM bean01 WHERE id=${id}</pre>
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
   * @return number of deleted records
   */
  @Override
  public long deleteOne(Long id) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    _contentValues.addWhereArgs((id==null?"":String.valueOf(id)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    if (deleteOnePreparedStatement1==null) {
      StringBuilder _sqlBuilder=getSQLStringBuilder();

      // manage WHERE arguments -- BEGIN

      // manage WHERE statement
      String _sqlWhereStatement=" id=?";
      _sqlBuilder.append(_sqlWhereStatement);

      // manage WHERE arguments -- END

      // generate sql
      String _sql="DELETE FROM bean01 WHERE id=?";
      deleteOnePreparedStatement1 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    // log section BEGIN
    if (this.dataSource.logEnabled) {

      // display log
      Logger.info("DELETE FROM bean01 WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(dataSource, deleteOnePreparedStatement1, _contentValues);
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean01 (id) VALUES (${id})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to column value <strong>id</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertOne(Long id) {
    KriptonContentValues _contentValues=contentValuesForUpdate();

    if (id!=null) {
      _contentValues.put("id", id);
    } else {
      _contentValues.putNull("id");
    }

    // log section BEGIN
    if (this.dataSource.logEnabled) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO bean01 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    }
    // log section END
    // insert operation
    if (insertOnePreparedStatement2==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO bean01 (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertOnePreparedStatement2 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertOnePreparedStatement2, _contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean01 (text) VALUES (${bean.text})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>text</dt><dd>is mapped to <strong>${bean.text}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertOne(Bean01Entity bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate();
    if (bean.getText()!=null) {
      _contentValues.put("text", bean.getText());
    } else {
      _contentValues.putNull("text");
    }

    // log section BEGIN
    if (this.dataSource.logEnabled) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO bean01 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    }
    // log section END
    // insert operation
    if (insertOnePreparedStatement3==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO bean01 (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertOnePreparedStatement3 = KriptonDatabaseWrapper.compile(dataSource, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(dataSource, insertOnePreparedStatement3, _contentValues);
    bean.setId(result);

    return result;
  }

  public void clearCompiledStatements() {
    if (updateOnePreparedStatement0!=null) {
      updateOnePreparedStatement0.close();
      updateOnePreparedStatement0=null;
    }
    if (deleteOnePreparedStatement1!=null) {
      deleteOnePreparedStatement1.close();
      deleteOnePreparedStatement1=null;
    }
    if (insertOnePreparedStatement2!=null) {
      insertOnePreparedStatement2.close();
      insertOnePreparedStatement2=null;
    }
    if (insertOnePreparedStatement3!=null) {
      insertOnePreparedStatement3.close();
      insertOnePreparedStatement3=null;
    }
  }
}
