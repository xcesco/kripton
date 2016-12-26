package commons.benchmark.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import commons.benchmark.model.User;
import commons.benchmark.model.UserTable;
import java.util.ArrayList;

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
  public UserDaoImpl(BindBenchmarkDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO user (uid, index, guid, is_active, balance, picture_url, age, name, company, email, address, about, registered, latitude, longitude, tags, range, friends, images, greeting, favorite_fruit, eye_color, phone) VALUES (${bean.uid}, ${bean.index}, ${bean.guid}, ${bean.isActive}, ${bean.balance}, ${bean.pictureUrl}, ${bean.age}, ${bean.name}, ${bean.company}, ${bean.email}, ${bean.address}, ${bean.about}, ${bean.registered}, ${bean.latitude}, ${bean.longitude}, ${bean.tags}, ${bean.range}, ${bean.friends}, ${bean.images}, ${bean.greeting}, ${bean.favoriteFruit}, ${bean.eyeColor}, ${bean.phone})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>uid</dt><dd>is mapped to <strong>${bean.uid}</strong></dd>
   * 	<dt>index</dt><dd>is mapped to <strong>${bean.index}</strong></dd>
   * 	<dt>guid</dt><dd>is mapped to <strong>${bean.guid}</strong></dd>
   * 	<dt>is_active</dt><dd>is mapped to <strong>${bean.isActive}</strong></dd>
   * 	<dt>balance</dt><dd>is mapped to <strong>${bean.balance}</strong></dd>
   * 	<dt>picture_url</dt><dd>is mapped to <strong>${bean.pictureUrl}</strong></dd>
   * 	<dt>age</dt><dd>is mapped to <strong>${bean.age}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>company</dt><dd>is mapped to <strong>${bean.company}</strong></dd>
   * 	<dt>email</dt><dd>is mapped to <strong>${bean.email}</strong></dd>
   * 	<dt>address</dt><dd>is mapped to <strong>${bean.address}</strong></dd>
   * 	<dt>about</dt><dd>is mapped to <strong>${bean.about}</strong></dd>
   * 	<dt>registered</dt><dd>is mapped to <strong>${bean.registered}</strong></dd>
   * 	<dt>latitude</dt><dd>is mapped to <strong>${bean.latitude}</strong></dd>
   * 	<dt>longitude</dt><dd>is mapped to <strong>${bean.longitude}</strong></dd>
   * 	<dt>tags</dt><dd>is mapped to <strong>${bean.tags}</strong></dd>
   * 	<dt>range</dt><dd>is mapped to <strong>${bean.range}</strong></dd>
   * 	<dt>friends</dt><dd>is mapped to <strong>${bean.friends}</strong></dd>
   * 	<dt>images</dt><dd>is mapped to <strong>${bean.images}</strong></dd>
   * 	<dt>greeting</dt><dd>is mapped to <strong>${bean.greeting}</strong></dd>
   * 	<dt>favorite_fruit</dt><dd>is mapped to <strong>${bean.favoriteFruit}</strong></dd>
   * 	<dt>eye_color</dt><dd>is mapped to <strong>${bean.eyeColor}</strong></dd>
   * 	<dt>phone</dt><dd>is mapped to <strong>${bean.phone}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(User bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.uid!=null) {
      contentValues.put("uid", bean.uid);
    } else {
      contentValues.putNull("uid");
    }

    contentValues.put("index", bean.index);

    if (bean.guid!=null) {
      contentValues.put("guid", bean.guid);
    } else {
      contentValues.putNull("guid");
    }

    contentValues.put("is_active", bean.isActive);

    if (bean.balance!=null) {
      contentValues.put("balance", bean.balance);
    } else {
      contentValues.putNull("balance");
    }

    if (bean.pictureUrl!=null) {
      contentValues.put("picture_url", bean.pictureUrl);
    } else {
      contentValues.putNull("picture_url");
    }

    contentValues.put("age", bean.age);

    if (bean.name!=null) {
      contentValues.put("name", UserTable.serializeName(bean.name));
    } else {
      contentValues.putNull("name");
    }

    if (bean.company!=null) {
      contentValues.put("company", bean.company);
    } else {
      contentValues.putNull("company");
    }

    if (bean.email!=null) {
      contentValues.put("email", bean.email);
    } else {
      contentValues.putNull("email");
    }

    if (bean.address!=null) {
      contentValues.put("address", bean.address);
    } else {
      contentValues.putNull("address");
    }

    if (bean.about!=null) {
      contentValues.put("about", bean.about);
    } else {
      contentValues.putNull("about");
    }

    if (bean.registered!=null) {
      contentValues.put("registered", bean.registered);
    } else {
      contentValues.putNull("registered");
    }

    contentValues.put("latitude", bean.latitude);

    contentValues.put("longitude", bean.longitude);

    if (bean.tags!=null) {
      contentValues.put("tags", UserTable.serializeTags(bean.tags));
    } else {
      contentValues.putNull("tags");
    }

    if (bean.range!=null) {
      contentValues.put("range", UserTable.serializeRange(bean.range));
    } else {
      contentValues.putNull("range");
    }

    if (bean.friends!=null) {
      contentValues.put("friends", UserTable.serializeFriends(bean.friends));
    } else {
      contentValues.putNull("friends");
    }

    if (bean.images!=null) {
      contentValues.put("images", UserTable.serializeImages(bean.images));
    } else {
      contentValues.putNull("images");
    }

    if (bean.greeting!=null) {
      contentValues.put("greeting", bean.greeting);
    } else {
      contentValues.putNull("greeting");
    }

    if (bean.favoriteFruit!=null) {
      contentValues.put("favorite_fruit", bean.favoriteFruit);
    } else {
      contentValues.putNull("favorite_fruit");
    }

    if (bean.eyeColor!=null) {
      contentValues.put("eye_color", bean.eyeColor);
    } else {
      contentValues.putNull("eye_color");
    }

    if (bean.phone!=null) {
      contentValues.put("phone", bean.phone);
    } else {
      contentValues.putNull("phone");
    }

    // log
    Logger.info(StringUtils.formatSQL("SQL: INSERT INTO user (uid, index, guid, is_active, balance, picture_url, age, name, company, email, address, about, registered, latitude, longitude, tags, range, friends, images, greeting, favorite_fruit, eye_color, phone) VALUES ('"+StringUtils.checkSize(contentValues.get("uid"))+"', '"+StringUtils.checkSize(contentValues.get("index"))+"', '"+StringUtils.checkSize(contentValues.get("guid"))+"', '"+StringUtils.checkSize(contentValues.get("is_active"))+"', '"+StringUtils.checkSize(contentValues.get("balance"))+"', '"+StringUtils.checkSize(contentValues.get("picture_url"))+"', '"+StringUtils.checkSize(contentValues.get("age"))+"', '"+StringUtils.checkSize(contentValues.get("name"))+"', '"+StringUtils.checkSize(contentValues.get("company"))+"', '"+StringUtils.checkSize(contentValues.get("email"))+"', '"+StringUtils.checkSize(contentValues.get("address"))+"', '"+StringUtils.checkSize(contentValues.get("about"))+"', '"+StringUtils.checkSize(contentValues.get("registered"))+"', '"+StringUtils.checkSize(contentValues.get("latitude"))+"', '"+StringUtils.checkSize(contentValues.get("longitude"))+"', '"+StringUtils.checkSize(contentValues.get("tags"))+"', '"+StringUtils.checkSize(contentValues.get("range"))+"', '"+StringUtils.checkSize(contentValues.get("friends"))+"', '"+StringUtils.checkSize(contentValues.get("images"))+"', '"+StringUtils.checkSize(contentValues.get("greeting"))+"', '"+StringUtils.checkSize(contentValues.get("favorite_fruit"))+"', '"+StringUtils.checkSize(contentValues.get("eye_color"))+"', '"+StringUtils.checkSize(contentValues.get("phone"))+"')"));
    long result = database().insert("user", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, uid, index, guid, is_active, balance, picture_url, age, name, company, email, address, about, registered, latitude, longitude, tags, range, friends, images, greeting, favorite_fruit, eye_color, phone FROM user WHERE 1=1</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>uid</dt><dd>is associated to bean's property <strong>uid</strong></dd>
   * 	<dt>index</dt><dd>is associated to bean's property <strong>index</strong></dd>
   * 	<dt>guid</dt><dd>is associated to bean's property <strong>guid</strong></dd>
   * 	<dt>is_active</dt><dd>is associated to bean's property <strong>isActive</strong></dd>
   * 	<dt>balance</dt><dd>is associated to bean's property <strong>balance</strong></dd>
   * 	<dt>picture_url</dt><dd>is associated to bean's property <strong>pictureUrl</strong></dd>
   * 	<dt>age</dt><dd>is associated to bean's property <strong>age</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>company</dt><dd>is associated to bean's property <strong>company</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>about</dt><dd>is associated to bean's property <strong>about</strong></dd>
   * 	<dt>registered</dt><dd>is associated to bean's property <strong>registered</strong></dd>
   * 	<dt>latitude</dt><dd>is associated to bean's property <strong>latitude</strong></dd>
   * 	<dt>longitude</dt><dd>is associated to bean's property <strong>longitude</strong></dd>
   * 	<dt>tags</dt><dd>is associated to bean's property <strong>tags</strong></dd>
   * 	<dt>range</dt><dd>is associated to bean's property <strong>range</strong></dd>
   * 	<dt>friends</dt><dd>is associated to bean's property <strong>friends</strong></dd>
   * 	<dt>images</dt><dd>is associated to bean's property <strong>images</strong></dd>
   * 	<dt>greeting</dt><dd>is associated to bean's property <strong>greeting</strong></dd>
   * 	<dt>favorite_fruit</dt><dd>is associated to bean's property <strong>favoriteFruit</strong></dd>
   * 	<dt>eye_color</dt><dd>is associated to bean's property <strong>eyeColor</strong></dd>
   * 	<dt>phone</dt><dd>is associated to bean's property <strong>phone</strong></dd>
   * </dl>
   *
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public ArrayList<User> selectAll() {
    // build where condition
    String[] args={};

    Logger.info(StringUtils.formatSQL("SELECT id, uid, index, guid, is_active, balance, picture_url, age, name, company, email, address, about, registered, latitude, longitude, tags, range, friends, images, greeting, favorite_fruit, eye_color, phone FROM user WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, uid, index, guid, is_active, balance, picture_url, age, name, company, email, address, about, registered, latitude, longitude, tags, range, friends, images, greeting, favorite_fruit, eye_color, phone FROM user WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    ArrayList<User> resultList=new ArrayList<User>();
    User resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("uid");
      int index2=cursor.getColumnIndex("index");
      int index3=cursor.getColumnIndex("guid");
      int index4=cursor.getColumnIndex("is_active");
      int index5=cursor.getColumnIndex("balance");
      int index6=cursor.getColumnIndex("picture_url");
      int index7=cursor.getColumnIndex("age");
      int index8=cursor.getColumnIndex("name");
      int index9=cursor.getColumnIndex("company");
      int index10=cursor.getColumnIndex("email");
      int index11=cursor.getColumnIndex("address");
      int index12=cursor.getColumnIndex("about");
      int index13=cursor.getColumnIndex("registered");
      int index14=cursor.getColumnIndex("latitude");
      int index15=cursor.getColumnIndex("longitude");
      int index16=cursor.getColumnIndex("tags");
      int index17=cursor.getColumnIndex("range");
      int index18=cursor.getColumnIndex("friends");
      int index19=cursor.getColumnIndex("images");
      int index20=cursor.getColumnIndex("greeting");
      int index21=cursor.getColumnIndex("favorite_fruit");
      int index22=cursor.getColumnIndex("eye_color");
      int index23=cursor.getColumnIndex("phone");

      do
       {
        resultBean=new User();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.uid=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.index=cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.guid=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.isActive=cursor.getInt(index4)==0?false:true; }
        if (!cursor.isNull(index5)) { resultBean.balance=cursor.getString(index5); }
        if (!cursor.isNull(index6)) { resultBean.pictureUrl=cursor.getString(index6); }
        if (!cursor.isNull(index7)) { resultBean.age=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.name=UserTable.parseName(cursor.getBlob(index8)); }
        if (!cursor.isNull(index9)) { resultBean.company=cursor.getString(index9); }
        if (!cursor.isNull(index10)) { resultBean.email=cursor.getString(index10); }
        if (!cursor.isNull(index11)) { resultBean.address=cursor.getString(index11); }
        if (!cursor.isNull(index12)) { resultBean.about=cursor.getString(index12); }
        if (!cursor.isNull(index13)) { resultBean.registered=cursor.getString(index13); }
        if (!cursor.isNull(index14)) { resultBean.latitude=cursor.getDouble(index14); }
        if (!cursor.isNull(index15)) { resultBean.longitude=cursor.getDouble(index15); }
        if (!cursor.isNull(index16)) { resultBean.tags=UserTable.parseTags(cursor.getBlob(index16)); }
        if (!cursor.isNull(index17)) { resultBean.range=UserTable.parseRange(cursor.getBlob(index17)); }
        if (!cursor.isNull(index18)) { resultBean.friends=UserTable.parseFriends(cursor.getBlob(index18)); }
        if (!cursor.isNull(index19)) { resultBean.images=UserTable.parseImages(cursor.getBlob(index19)); }
        if (!cursor.isNull(index20)) { resultBean.greeting=cursor.getString(index20); }
        if (!cursor.isNull(index21)) { resultBean.favoriteFruit=cursor.getString(index21); }
        if (!cursor.isNull(index22)) { resultBean.eyeColor=cursor.getString(index22); }
        if (!cursor.isNull(index23)) { resultBean.phone=cursor.getString(index23); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }
}
