package sqlite.feature.typeadapter;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.SQLTypeAdapterUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Contact</code>, based on interface <code>ContactDao</code>
 * </p>
 *
 *  @see Contact
 *  @see ContactDao
 *  @see ContactTable
 */
public class ContactDaoImpl extends AbstractDao implements ContactDao {
  public ContactDaoImpl(BindContactDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM contact WHERE id=${bean.id} and type=${bean.type}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.type}</dt><dd>is mapped to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void deleteCompactBean(Contact bean) {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.getId()));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=? and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM contact WHERE id=? and type=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("contact", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM contact WHERE password=${password} and type=${type}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${password}</dt><dd>is mapped to method's parameter <strong>password</strong></dd>
   * 	<dt>${type}</dt><dd>is mapped to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param password
   * 	is used as where parameter <strong>${password}</strong>
   * @param type
   * 	is used as where parameter <strong>${type}</strong>
   */
  @Override
  public void deleteCompactRaw(String password, ContactType type) {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" password=? and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM contact WHERE password=? and type=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("contact", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE FROM contact WHERE id=${bean.id} and type=${bean.type}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.type}</dt><dd>is mapped to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void deleteJQLBean(Contact bean) {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.getId()));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=? and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM contact WHERE id=? and type=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("contact", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM contact WHERE id=${id} and type=${type}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * 	<dt>${type}</dt><dd>is mapped to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param type
   * 	is used as where parameter <strong>${type}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteJQLRaw(long id, ContactType type) {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(id));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=? and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM contact WHERE id=? and type=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("contact", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth_day, password, type FROM contact WHERE id=${bean.id}  and type=${bean.type}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.type}</dt><dd>is binded to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selectCompactBean(Contact bean) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, birth_day, password, type FROM contact");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?  and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(bean.getId()));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Contact> resultList=new LinkedList<Contact>();
      Contact resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("birth_day");
        int index2=cursor.getColumnIndex("password");
        int index3=cursor.getColumnIndex("type");

        do
         {
          resultBean=new Contact();

          resultBean.setId(cursor.getLong(index0));
          if (!cursor.isNull(index1)) { resultBean.birthDay=SQLTypeAdapterUtils.toJava(DateAdapterType.class, cursor.getLong(index1)); }
          if (!cursor.isNull(index2)) { resultBean.setPassword(SQLTypeAdapterUtils.toJava(PasswordAdapterType.class, cursor.getBlob(index2))); }
          if (!cursor.isNull(index3)) { resultBean.type=SQLTypeAdapterUtils.toJava(EnumAdapterType.class, cursor.getInt(index3)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth_day, password, type FROM contact WHERE id=${bean.id} and password=${bean.password} and type=${bean.type}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.password}</dt><dd>is binded to method's parameter <strong>bean.password</strong></dd>
   * 	<dt>${bean.type}</dt><dd>is binded to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @param listener
   * 	is the Contact listener
   */
  @Override
  public void selectJQLBeanListener(Contact bean, OnReadBeanListener<Contact> listener) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, birth_day, password, type FROM contact");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=? and password=? and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(bean.getId()));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, bean.getPassword()));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Contact resultBean=new Contact();
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("birth_day");
        int index2=cursor.getColumnIndex("password");
        int index3=cursor.getColumnIndex("type");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset
          resultBean.birthDay=null;
          resultBean.setPassword(null);
          resultBean.type=null;

          // generate mapping
          resultBean.setId(cursor.getLong(index0));
          if (!cursor.isNull(index1)) { resultBean.birthDay=SQLTypeAdapterUtils.toJava(DateAdapterType.class, cursor.getLong(index1)); }
          if (!cursor.isNull(index2)) { resultBean.setPassword(SQLTypeAdapterUtils.toJava(PasswordAdapterType.class, cursor.getBlob(index2))); }
          if (!cursor.isNull(index3)) { resultBean.type=SQLTypeAdapterUtils.toJava(EnumAdapterType.class, cursor.getInt(index3)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT birth_day, password, type FROM contact WHERE id=${bean.id} and password=${bean.password} and type=${bean.type}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is binded to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.password}</dt><dd>is binded to method's parameter <strong>bean.password</strong></dd>
   * 	<dt>${bean.type}</dt><dd>is binded to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selecJQLBean(Contact bean) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT birth_day, password, type FROM contact");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=? and password=? and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(bean.getId()));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, bean.getPassword()));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Contact> resultList=new LinkedList<Contact>();
      Contact resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("birth_day");
        int index1=cursor.getColumnIndex("password");
        int index2=cursor.getColumnIndex("type");

        do
         {
          resultBean=new Contact();

          if (!cursor.isNull(index0)) { resultBean.birthDay=SQLTypeAdapterUtils.toJava(DateAdapterType.class, cursor.getLong(index0)); }
          if (!cursor.isNull(index1)) { resultBean.setPassword(SQLTypeAdapterUtils.toJava(PasswordAdapterType.class, cursor.getBlob(index1))); }
          if (!cursor.isNull(index2)) { resultBean.type=SQLTypeAdapterUtils.toJava(EnumAdapterType.class, cursor.getInt(index2)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT * FROM contact WHERE password=${password} and type=${type}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${password}</dt><dd>is binded to method's parameter <strong>password</strong></dd>
   * 	<dt>${type}</dt><dd>is binded to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param password
   * 	is binded to <code>${password}</code>
   * @param type
   * 	is binded to <code>${type}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selectJQLRaw(String password, ContactType type) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT * FROM contact");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE password=? and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Contact> resultList=new LinkedList<Contact>();
      Contact resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("birth_day");
        int index2=cursor.getColumnIndex("password");
        int index3=cursor.getColumnIndex("type");

        do
         {
          resultBean=new Contact();

          resultBean.setId(cursor.getLong(index0));
          if (!cursor.isNull(index1)) { resultBean.birthDay=SQLTypeAdapterUtils.toJava(DateAdapterType.class, cursor.getLong(index1)); }
          if (!cursor.isNull(index2)) { resultBean.setPassword(SQLTypeAdapterUtils.toJava(PasswordAdapterType.class, cursor.getBlob(index2))); }
          if (!cursor.isNull(index3)) { resultBean.type=SQLTypeAdapterUtils.toJava(EnumAdapterType.class, cursor.getInt(index3)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, birth_day, password, type FROM contact WHERE password=${password} and type=${type}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * 	<dt>password</dt><dd>is associated to bean's property <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is associated to bean's property <strong>type</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${password}</dt><dd>is binded to method's parameter <strong>password</strong></dd>
   * 	<dt>${type}</dt><dd>is binded to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param password
   * 	is binded to <code>${password}</code>
   * @param type
   * 	is binded to <code>${type}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Contact> selectCompactRaw(String password, ContactType type) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, birth_day, password, type FROM contact");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE password=? and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Contact> resultList=new LinkedList<Contact>();
      Contact resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("birth_day");
        int index2=cursor.getColumnIndex("password");
        int index3=cursor.getColumnIndex("type");

        do
         {
          resultBean=new Contact();

          resultBean.setId(cursor.getLong(index0));
          if (!cursor.isNull(index1)) { resultBean.birthDay=SQLTypeAdapterUtils.toJava(DateAdapterType.class, cursor.getLong(index1)); }
          if (!cursor.isNull(index2)) { resultBean.setPassword(SQLTypeAdapterUtils.toJava(PasswordAdapterType.class, cursor.getBlob(index2))); }
          if (!cursor.isNull(index3)) { resultBean.type=SQLTypeAdapterUtils.toJava(EnumAdapterType.class, cursor.getInt(index3)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE contact SET id=:id, type=:type WHERE id=${bean.id}  and password=${bean.password} and type=${bean.type}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>${bean.type}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.password}</dt><dd>is mapped to method's parameter <strong>bean.password</strong></dd>
   * 	<dt>${bean.type}</dt><dd>is mapped to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public long updateCompactBean(Contact bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", bean.getId());
    if (bean.type!=null) {
      contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, bean.type));
    } else {
      contentValues.putNull("type");
    }

    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.getId()));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, bean.getPassword()));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?  and password=? and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE contact SET id=:id, type=:type WHERE id=?  and password=? and type=?");

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
    int result = database().update("contact", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE contact SET password=:password, type=:type WHERE id=${id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>password</li>
   * 	<li>type</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param password
   * 	is used as updated field <strong>password</strong>
   * @param type
   * 	is used as updated field <strong>type</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateCompactRaw1(String password, ContactType type, long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (password!=null) {
      contentValues.put("password", SQLTypeAdapterUtils.toData(PasswordAdapterType.class, password));
    } else {
      contentValues.putNull("password");
    }
    if (type!=null) {
      contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, type));
    } else {
      contentValues.putNull("type");
    }

    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE contact SET password=:password, type=:type WHERE id=?");

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
    int result = database().update("contact", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE contact SET birth_day=:birthDay, id=:id WHERE password=${password} and type=${type}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>birth_day</li>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${password}</dt><dd>is mapped to method's parameter <strong>password</strong></dd>
   * 	<dt>${type}</dt><dd>is mapped to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param birthDay
   * 	is used as updated field <strong>birthDay</strong>
   * @param password
   * 	is used as where parameter <strong>${password}</strong>
   * @param type
   * 	is used as where parameter <strong>${type}</strong>
   * @param id
   * 	is used as updated field <strong>id</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateCompactRaw2(Date birthDay, String password, ContactType type, long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (birthDay!=null) {
      contentValues.put("birth_day", SQLTypeAdapterUtils.toData(DateAdapterType.class, birthDay));
    } else {
      contentValues.putNull("birth_day");
    }
    contentValues.put("id", id);

    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" password=? and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE contact SET birth_day=:birthDay, id=:id WHERE password=? and type=?");

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
    int result = database().update("contact", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE contact SET birth_day=:birthDay, password=:password, type=:type WHERE type=${bean.type}  and type=${bean.password}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * 	<dt>password</dt><dd>is mapped to <strong>${bean.password}</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>${bean.type}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.type}</dt><dd>is mapped to method's parameter <strong>bean.type</strong></dd>
   * 	<dt>${bean.password}</dt><dd>is mapped to method's parameter <strong>bean.password</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public long updateJQLBean(Contact bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.birthDay!=null) {
      contentValues.put("birth_day", SQLTypeAdapterUtils.toData(DateAdapterType.class, bean.birthDay));
    } else {
      contentValues.putNull("birth_day");
    }
    if (bean.getPassword()!=null) {
      contentValues.put("password", bean.getPassword());
    } else {
      contentValues.putNull("password");
    }
    if (bean.type!=null) {
      contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, bean.type));
    } else {
      contentValues.putNull("type");
    }

    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, bean.getPassword()));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" type=?  and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE contact SET birth_day=:birthDay, password=:password, type=:type WHERE type=?  and type=?");

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
    int result = database().update("contact", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE contact SET birth_day=:birthDay, id=:id WHERE password=${password} and type=${type}</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>birth_day</li>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${password}</dt><dd>is mapped to method's parameter <strong>password</strong></dd>
   * 	<dt>${type}</dt><dd>is mapped to method's parameter <strong>type</strong></dd>
   * </dl>
   *
   * @param password
   * 	is used as where parameter <strong>${password}</strong>
   * @param birthDay
   * 	is used as updated field <strong>birthDay</strong>
   * @param type
   * 	is used as where parameter <strong>${type}</strong>
   * @param id
   * 	is used as updated field <strong>id</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateJQLRaw(String password, Date birthDay, ContactType type, long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (birthDay!=null) {
      contentValues.put("birth_day", SQLTypeAdapterUtils.toData(DateAdapterType.class, birthDay));
    } else {
      contentValues.putNull("birth_day");
    }
    contentValues.put("id", id);

    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(PasswordAdapterType.class, password));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, type));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" password=? and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE contact SET birth_day=:birthDay, id=:id WHERE password=? and type=?");

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
    int result = database().update("contact", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO contact (password, type, id) VALUES (${password}, ${type}, ${id})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>password</dt><dd>is binded to query's parameter <strong>${password}</strong> and method's parameter <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is binded to query's parameter <strong>${type}</strong> and method's parameter <strong>type</strong></dd>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param password
   * 	is binded to column value <strong>password</strong>
   * @param type
   * 	is binded to column value <strong>type</strong>
   * @param id
   * 	is binded to column value <strong>id</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertCompactRaw(String password, ContactType type, long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (password!=null) {
      contentValues.put("password", SQLTypeAdapterUtils.toData(PasswordAdapterType.class, password));
    } else {
      contentValues.putNull("password");
    }
    if (type!=null) {
      contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, type));
    } else {
      contentValues.putNull("type");
    }
    contentValues.put("id", id);

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO contact (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    long result = database().insert("contact", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO contact (id, type) VALUES (${bean.id}, ${bean.type})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>${bean.type}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertCompactBean(Contact bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", bean.getId());
    if (bean.type!=null) {
      contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, bean.type));
    } else {
      contentValues.putNull("type");
    }

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO contact (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    long result = database().insert("contact", null, contentValues);
    bean.setId(result);

    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO contact (password, type, id) VALUES (${bean.password}, ${bean.type}, ${bean.id})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>password</dt><dd>is mapped to <strong>${bean.password}</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>${bean.type}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertJQLBean(Contact bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.getPassword()!=null) {
      contentValues.put("password", bean.getPassword());
    } else {
      contentValues.putNull("password");
    }
    if (bean.type!=null) {
      contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, bean.type));
    } else {
      contentValues.putNull("type");
    }
    contentValues.put("id", bean.getId());

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO contact (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    long result = database().insert("contact", null, contentValues);
    bean.setId(result);

    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO contact (password, type, id) VALUES (${password}, ${type}, ${id})</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>password</dt><dd>is binded to query's parameter <strong>${password}</strong> and method's parameter <strong>password</strong></dd>
   * 	<dt>type</dt><dd>is binded to query's parameter <strong>${type}</strong> and method's parameter <strong>type</strong></dd>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param password
   * 	is binded to column value <strong>password</strong>
   * @param type
   * 	is binded to column value <strong>type</strong>
   * @param id
   * 	is binded to column value <strong>id</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertJQLRaw(String password, Date birthDay, ContactType type, long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (password!=null) {
      contentValues.put("password", SQLTypeAdapterUtils.toData(PasswordAdapterType.class, password));
    } else {
      contentValues.putNull("password");
    }
    if (birthDay!=null) {
      contentValues.put("birth_day", SQLTypeAdapterUtils.toData(DateAdapterType.class, birthDay));
    } else {
      contentValues.putNull("birth_day");
    }
    if (type!=null) {
      contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, type));
    } else {
      contentValues.putNull("type");
    }
    contentValues.put("id", id);

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO contact (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    long result = database().insert("contact", null, contentValues);
    return result;
  }
}
