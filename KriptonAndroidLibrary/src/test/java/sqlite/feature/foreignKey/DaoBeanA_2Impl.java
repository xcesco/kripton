package sqlite.feature.foreignKey;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>BeanA_2</code>, based on interface <code>DaoBeanA_2</code>
 * </p>
 *
 *  @see BeanA_2
 *  @see DaoBeanA_2
 *  @see BeanA_2Table
 */
public class DaoBeanA_2Impl extends AbstractDao implements DaoBeanA_2 {
  public DaoBeanA_2Impl(BindDummyDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, value_string2 FROM bean_a_2</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value_string2</dt><dd>is associated to bean's property <strong>valueString2</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_2> selectAll() {
    StringBuilder _sqlBuilder=new StringBuilder();
    _sqlBuilder.append("SELECT pk, value_string2 FROM bean_a_2");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();
    String _sqlWhereStatement="";

    // build where condition
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<BeanA_2> resultList=new LinkedList<BeanA_2>();
      BeanA_2 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("pk");
        int index1=cursor.getColumnIndex("value_string2");

        do
         {
          resultBean=new BeanA_2();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.valueString2=cursor.getString(index1); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, value_string2 FROM bean_a_2 WHERE pk=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value_string2</dt><dd>is associated to bean's property <strong>valueString2</strong></dd>
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
  public List<BeanA_2> selectById(long id) {
    StringBuilder _sqlBuilder=new StringBuilder();
    _sqlBuilder.append("SELECT pk, value_string2 FROM bean_a_2");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE pk=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(id));
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<BeanA_2> resultList=new LinkedList<BeanA_2>();
      BeanA_2 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("pk");
        int index1=cursor.getColumnIndex("value_string2");

        do
         {
          resultBean=new BeanA_2();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.valueString2=cursor.getString(index1); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk FROM bean_a_2 WHERE value_string2=${dummy}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${dummy}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${dummy}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_2> selectByString(String value) {
    StringBuilder _sqlBuilder=new StringBuilder();
    _sqlBuilder.append("SELECT pk FROM bean_a_2");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE value_string2=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add((value==null?"":value));
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<BeanA_2> resultList=new LinkedList<BeanA_2>();
      BeanA_2 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("pk");

        do
         {
          resultBean=new BeanA_2();

          resultBean.id=cursor.getLong(index0);

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean_a_2 (value_string2) VALUES (${bean.valueString2})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_string2</dt><dd>is mapped to <strong>${bean.valueString2}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(BeanA_2 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.valueString2!=null) {
      contentValues.put("value_string2", bean.valueString2);
    } else {
      contentValues.putNull("value_string2");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO bean_a_2 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    long result = database().insert("bean_a_2", null, contentValues);
    bean.id=result;

    return (int)result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean_a_2 SET value_string2=${bean.valueString2} WHERE WHERE valueString2=${bean.valueString2}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>value_string2</dt><dd>is mapped to <strong>${bean.valueString2}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.valueString2}</dt><dd>is mapped to method's parameter <strong>bean.valueString2</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public int update(BeanA_2 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.valueString2!=null) {
      contentValues.put("value_string2", bean.valueString2);
    } else {
      contentValues.putNull("value_string2");
    }

    ArrayList<String> _sqlWhereParams=new ArrayList<String>();
    _sqlWhereParams.add((bean.valueString2==null?"":bean.valueString2));

    StringBuilder _sqlBuilder=new StringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" value_string2=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    //StringUtils and SqlUtils will be used to format SQL

    // display log
    Logger.info("UPDATE bean_a_2 SET valueString2=:bean.valueString2 WHERE valueString2=?");

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
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    int result = database().update("bean_a_2", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
    return result;
  }
}
