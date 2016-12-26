package commons.benchmark.model;

import android.database.Cursor;
import java.util.LinkedList;

/**
 * <p>
 * Cursor implementation for entity <code>User</code>
 * </p>
 *  @see User
 */
public class BindUserCursor {
  /**
   * Cursor used to read database
   */
  protected Cursor cursor;

  /**
   * Index for column "id"
   */
  protected int index0;

  /**
   * Index for column "uid"
   */
  protected int index1;

  /**
   * Index for column "index"
   */
  protected int index2;

  /**
   * Index for column "guid"
   */
  protected int index3;

  /**
   * Index for column "isActive"
   */
  protected int index4;

  /**
   * Index for column "balance"
   */
  protected int index5;

  /**
   * Index for column "pictureUrl"
   */
  protected int index6;

  /**
   * Index for column "age"
   */
  protected int index7;

  /**
   * Index for column "name"
   */
  protected int index8;

  /**
   * Index for column "company"
   */
  protected int index9;

  /**
   * Index for column "email"
   */
  protected int index10;

  /**
   * Index for column "address"
   */
  protected int index11;

  /**
   * Index for column "about"
   */
  protected int index12;

  /**
   * Index for column "registered"
   */
  protected int index13;

  /**
   * Index for column "latitude"
   */
  protected int index14;

  /**
   * Index for column "longitude"
   */
  protected int index15;

  /**
   * Index for column "tags"
   */
  protected int index16;

  /**
   * Index for column "range"
   */
  protected int index17;

  /**
   * Index for column "friends"
   */
  protected int index18;

  /**
   * Index for column "images"
   */
  protected int index19;

  /**
   * Index for column "greeting"
   */
  protected int index20;

  /**
   * Index for column "favoriteFruit"
   */
  protected int index21;

  /**
   * Index for column "eyeColor"
   */
  protected int index22;

  /**
   * Index for column "phone"
   */
  protected int index23;

  /**
   * <p>Constructor</p>
   *
   * @param cursor cursor used to read from database
   */
  BindUserCursor(Cursor cursor) {
    wrap(cursor);
  }

  /**
   * <p>Wrap cursor with this class</p>
   *
   * @param cursor cursor to include
   */
  public BindUserCursor wrap(Cursor cursor) {
    this.cursor=cursor;

    index0=cursor.getColumnIndex("id");
    index1=cursor.getColumnIndex("uid");
    index2=cursor.getColumnIndex("index");
    index3=cursor.getColumnIndex("guid");
    index4=cursor.getColumnIndex("is_active");
    index5=cursor.getColumnIndex("balance");
    index6=cursor.getColumnIndex("picture_url");
    index7=cursor.getColumnIndex("age");
    index8=cursor.getColumnIndex("name");
    index9=cursor.getColumnIndex("company");
    index10=cursor.getColumnIndex("email");
    index11=cursor.getColumnIndex("address");
    index12=cursor.getColumnIndex("about");
    index13=cursor.getColumnIndex("registered");
    index14=cursor.getColumnIndex("latitude");
    index15=cursor.getColumnIndex("longitude");
    index16=cursor.getColumnIndex("tags");
    index17=cursor.getColumnIndex("range");
    index18=cursor.getColumnIndex("friends");
    index19=cursor.getColumnIndex("images");
    index20=cursor.getColumnIndex("greeting");
    index21=cursor.getColumnIndex("favorite_fruit");
    index22=cursor.getColumnIndex("eye_color");
    index23=cursor.getColumnIndex("phone");

    return this;
  }

  /**
   * <p>Execute the cursor and read all rows from database.</p>
   *
   * @return list of beans
   */
  public LinkedList<User> execute() {

    LinkedList<User> resultList=new LinkedList<User>();
    User resultBean=new User();

    if (cursor.moveToFirst()) {
      do
       {
        resultBean=new User();

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.uid=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.index=cursor.getInt(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.guid=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.isActive=cursor.getInt(index4)==0?false:true;}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.balance=cursor.getString(index5);}
        if (index6>=0 && !cursor.isNull(index6)) { resultBean.pictureUrl=cursor.getString(index6);}
        if (index7>=0 && !cursor.isNull(index7)) { resultBean.age=cursor.getInt(index7);}
        if (index8>=0 && !cursor.isNull(index8)) { resultBean.name=UserTable.parseName(cursor.getBlob(index8));}
        if (index9>=0 && !cursor.isNull(index9)) { resultBean.company=cursor.getString(index9);}
        if (index10>=0 && !cursor.isNull(index10)) { resultBean.email=cursor.getString(index10);}
        if (index11>=0 && !cursor.isNull(index11)) { resultBean.address=cursor.getString(index11);}
        if (index12>=0 && !cursor.isNull(index12)) { resultBean.about=cursor.getString(index12);}
        if (index13>=0 && !cursor.isNull(index13)) { resultBean.registered=cursor.getString(index13);}
        if (index14>=0 && !cursor.isNull(index14)) { resultBean.latitude=cursor.getDouble(index14);}
        if (index15>=0 && !cursor.isNull(index15)) { resultBean.longitude=cursor.getDouble(index15);}
        if (index16>=0 && !cursor.isNull(index16)) { resultBean.tags=UserTable.parseTags(cursor.getBlob(index16));}
        if (index17>=0 && !cursor.isNull(index17)) { resultBean.range=UserTable.parseRange(cursor.getBlob(index17));}
        if (index18>=0 && !cursor.isNull(index18)) { resultBean.friends=UserTable.parseFriends(cursor.getBlob(index18));}
        if (index19>=0 && !cursor.isNull(index19)) { resultBean.images=UserTable.parseImages(cursor.getBlob(index19));}
        if (index20>=0 && !cursor.isNull(index20)) { resultBean.greeting=cursor.getString(index20);}
        if (index21>=0 && !cursor.isNull(index21)) { resultBean.favoriteFruit=cursor.getString(index21);}
        if (index22>=0 && !cursor.isNull(index22)) { resultBean.eyeColor=cursor.getString(index22);}
        if (index23>=0 && !cursor.isNull(index23)) { resultBean.phone=cursor.getString(index23);}

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * Method executed for each row extracted from database. For each row specified listener will be invoked.
   *
   * @param listener listener to invoke for each row
   */
  public void executeListener(OnUserListener listener) {
    User resultBean=new User();

    if (cursor.moveToFirst()) {
      do
       {
        if (index0>=0) { resultBean.id=0L;}
        if (index1>=0) { resultBean.uid=null;}
        if (index2>=0) { resultBean.index=0;}
        if (index3>=0) { resultBean.guid=null;}
        if (index4>=0) { resultBean.isActive=false;}
        if (index5>=0) { resultBean.balance=null;}
        if (index6>=0) { resultBean.pictureUrl=null;}
        if (index7>=0) { resultBean.age=0;}
        if (index8>=0) { resultBean.name=null;}
        if (index9>=0) { resultBean.company=null;}
        if (index10>=0) { resultBean.email=null;}
        if (index11>=0) { resultBean.address=null;}
        if (index12>=0) { resultBean.about=null;}
        if (index13>=0) { resultBean.registered=null;}
        if (index14>=0) { resultBean.latitude=0;}
        if (index15>=0) { resultBean.longitude=0;}
        if (index16>=0) { resultBean.tags=null;}
        if (index17>=0) { resultBean.range=null;}
        if (index18>=0) { resultBean.friends=null;}
        if (index19>=0) { resultBean.images=null;}
        if (index20>=0) { resultBean.greeting=null;}
        if (index21>=0) { resultBean.favoriteFruit=null;}
        if (index22>=0) { resultBean.eyeColor=null;}
        if (index23>=0) { resultBean.phone=null;}

        if (index0>=0 && !cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0);}
        if (index1>=0 && !cursor.isNull(index1)) { resultBean.uid=cursor.getString(index1);}
        if (index2>=0 && !cursor.isNull(index2)) { resultBean.index=cursor.getInt(index2);}
        if (index3>=0 && !cursor.isNull(index3)) { resultBean.guid=cursor.getString(index3);}
        if (index4>=0 && !cursor.isNull(index4)) { resultBean.isActive=cursor.getInt(index4)==0?false:true;}
        if (index5>=0 && !cursor.isNull(index5)) { resultBean.balance=cursor.getString(index5);}
        if (index6>=0 && !cursor.isNull(index6)) { resultBean.pictureUrl=cursor.getString(index6);}
        if (index7>=0 && !cursor.isNull(index7)) { resultBean.age=cursor.getInt(index7);}
        if (index8>=0 && !cursor.isNull(index8)) { resultBean.name=UserTable.parseName(cursor.getBlob(index8));}
        if (index9>=0 && !cursor.isNull(index9)) { resultBean.company=cursor.getString(index9);}
        if (index10>=0 && !cursor.isNull(index10)) { resultBean.email=cursor.getString(index10);}
        if (index11>=0 && !cursor.isNull(index11)) { resultBean.address=cursor.getString(index11);}
        if (index12>=0 && !cursor.isNull(index12)) { resultBean.about=cursor.getString(index12);}
        if (index13>=0 && !cursor.isNull(index13)) { resultBean.registered=cursor.getString(index13);}
        if (index14>=0 && !cursor.isNull(index14)) { resultBean.latitude=cursor.getDouble(index14);}
        if (index15>=0 && !cursor.isNull(index15)) { resultBean.longitude=cursor.getDouble(index15);}
        if (index16>=0 && !cursor.isNull(index16)) { resultBean.tags=UserTable.parseTags(cursor.getBlob(index16));}
        if (index17>=0 && !cursor.isNull(index17)) { resultBean.range=UserTable.parseRange(cursor.getBlob(index17));}
        if (index18>=0 && !cursor.isNull(index18)) { resultBean.friends=UserTable.parseFriends(cursor.getBlob(index18));}
        if (index19>=0 && !cursor.isNull(index19)) { resultBean.images=UserTable.parseImages(cursor.getBlob(index19));}
        if (index20>=0 && !cursor.isNull(index20)) { resultBean.greeting=cursor.getString(index20);}
        if (index21>=0 && !cursor.isNull(index21)) { resultBean.favoriteFruit=cursor.getString(index21);}
        if (index22>=0 && !cursor.isNull(index22)) { resultBean.eyeColor=cursor.getString(index22);}
        if (index23>=0 && !cursor.isNull(index23)) { resultBean.phone=cursor.getString(index23);}

        listener.onRow(resultBean, cursor.getPosition(),cursor.getCount());
      } while (cursor.moveToNext());
    }
    cursor.close();
  }

  /**
   * <p>Create a binded cursor starting from a cursor</p>
   *
   * @param cursor to wrap
   */
  public static BindUserCursor create(Cursor cursor) {
    return new BindUserCursor(cursor);
  }

  /**
   * <p>Listener for row read from database.</p>
   */
  public interface OnUserListener {
    /**
     * Method executed for each row extracted from database
     *
     * @param bean loaded from database. Only selected columns/fields are valorized
     * @param rowPosition position of row
     * @param rowCount total number of rows
     */
    void onRow(User bean, int rowPosition, int rowCount);
  }
}
