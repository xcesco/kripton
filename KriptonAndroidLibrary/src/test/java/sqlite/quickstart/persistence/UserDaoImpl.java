package sqlite.quickstart.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;
import sqlite.quickstart.model.User;
import sqlite.quickstart.model.UserTable;

/**
 * <p>
 * DAO implementation for entity <code>User</code>, based on interface <code>UserDao</code>
 * </p>
 *
 *  @see User
 *  @see UserDao
 *  @see UserTable
 */
public class UserDaoImpl extends AbstractDao implements UserDao {
  public UserDaoImpl(BindQuickStartDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO user (id, name, username, email, address, phone, website, company) VALUES (${bean.id}, ${bean.name}, ${bean.username}, ${bean.email}, ${bean.address}, ${bean.phone}, ${bean.website}, ${bean.company})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>username</dt><dd>is mapped to <strong>${bean.username}</strong></dd>
   * 	<dt>email</dt><dd>is mapped to <strong>${bean.email}</strong></dd>
   * 	<dt>address</dt><dd>is mapped to <strong>${bean.address}</strong></dd>
   * 	<dt>phone</dt><dd>is mapped to <strong>${bean.phone}</strong></dd>
   * 	<dt>website</dt><dd>is mapped to <strong>${bean.website}</strong></dd>
   * 	<dt>company</dt><dd>is mapped to <strong>${bean.company}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   *
   */
  @Override
  public void insert(User bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", bean.id);

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }

    if (bean.username!=null) {
      contentValues.put("username", bean.username);
    } else {
      contentValues.putNull("username");
    }

    if (bean.email!=null) {
      contentValues.put("email", bean.email);
    } else {
      contentValues.putNull("email");
    }

    if (bean.address!=null) {
      contentValues.put("address", UserTable.serializeAddress(bean.address));
    } else {
      contentValues.putNull("address");
    }

    if (bean.phone!=null) {
      contentValues.put("phone", bean.phone);
    } else {
      contentValues.putNull("phone");
    }

    if (bean.website!=null) {
      contentValues.put("website", bean.website);
    } else {
      contentValues.putNull("website");
    }

    if (bean.company!=null) {
      contentValues.put("company", UserTable.serializeCompany(bean.company));
    } else {
      contentValues.putNull("company");
    }

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO user (id, name, username, email, address, phone, website, company) VALUES ('"+StringUtils.checkSize(contentValues.get("id"))+"', '"+StringUtils.checkSize(contentValues.get("name"))+"', '"+StringUtils.checkSize(contentValues.get("username"))+"', '"+StringUtils.checkSize(contentValues.get("email"))+"', '"+StringUtils.checkSize(contentValues.get("address"))+"', '"+StringUtils.checkSize(contentValues.get("phone"))+"', '"+StringUtils.checkSize(contentValues.get("website"))+"', '"+StringUtils.checkSize(contentValues.get("company"))+"')"));
    long result = database().insert("user", null, contentValues);
    bean.id=result;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, name, username, email, address, phone, website, company FROM user WHERE 1=1 ORDER BY username asc</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>username</dt><dd>is associated to bean's property <strong>username</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>phone</dt><dd>is associated to bean's property <strong>phone</strong></dd>
   * 	<dt>website</dt><dd>is associated to bean's property <strong>website</strong></dd>
   * 	<dt>company</dt><dd>is associated to bean's property <strong>company</strong></dd>
   * </dl>
   *
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<User> selectAll() {
    // build where condition
    String[] args={};

    Logger.info(StringUtils.formatSQL("SELECT id, name, username, email, address, phone, website, company FROM user WHERE 1=1 ORDER BY username asc"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, name, username, email, address, phone, website, company FROM user WHERE 1=1 ORDER BY username asc", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<User> resultList=new LinkedList<User>();
    User resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("name");
      int index2=cursor.getColumnIndex("username");
      int index3=cursor.getColumnIndex("email");
      int index4=cursor.getColumnIndex("address");
      int index5=cursor.getColumnIndex("phone");
      int index6=cursor.getColumnIndex("website");
      int index7=cursor.getColumnIndex("company");

      do
       {
        resultBean=new User();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.username=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.email=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.address=UserTable.parseAddress(cursor.getBlob(index4)); }
        if (!cursor.isNull(index5)) { resultBean.phone=cursor.getString(index5); }
        if (!cursor.isNull(index6)) { resultBean.website=cursor.getString(index6); }
        if (!cursor.isNull(index7)) { resultBean.company=UserTable.parseCompany(cursor.getBlob(index7)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, name, username, email, address, phone, website, company FROM user WHERE id = ${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>username</dt><dd>is associated to bean's property <strong>username</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>phone</dt><dd>is associated to bean's property <strong>phone</strong></dd>
   * 	<dt>website</dt><dd>is associated to bean's property <strong>website</strong></dd>
   * 	<dt>company</dt><dd>is associated to bean's property <strong>company</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to ${value}
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public User selectById(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtils.formatSQL("SELECT id, name, username, email, address, phone, website, company FROM user WHERE id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, name, username, email, address, phone, website, company FROM user WHERE id = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    User resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("name");
      int index2=cursor.getColumnIndex("username");
      int index3=cursor.getColumnIndex("email");
      int index4=cursor.getColumnIndex("address");
      int index5=cursor.getColumnIndex("phone");
      int index6=cursor.getColumnIndex("website");
      int index7=cursor.getColumnIndex("company");

      resultBean=new User();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
      if (!cursor.isNull(index2)) { resultBean.username=cursor.getString(index2); }
      if (!cursor.isNull(index3)) { resultBean.email=cursor.getString(index3); }
      if (!cursor.isNull(index4)) { resultBean.address=UserTable.parseAddress(cursor.getBlob(index4)); }
      if (!cursor.isNull(index5)) { resultBean.phone=cursor.getString(index5); }
      if (!cursor.isNull(index6)) { resultBean.website=cursor.getString(index6); }
      if (!cursor.isNull(index7)) { resultBean.company=UserTable.parseCompany(cursor.getBlob(index7)); }

    }
    cursor.close();

    return resultBean;
  }
}
