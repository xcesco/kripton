package sqlite.feature.performance.simple;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import java.util.ArrayList;

/**
 * <p>
 * DAO implementation for entity <code>SimpleAddressItem</code>, based on interface <code>SimpleAddressDao</code>
 * </p>
 *
 *  @see SimpleAddressItem
 *  @see SimpleAddressDao
 *  @see SimpleAddressItemTable
 */
public class SimpleAddressDaoImpl extends Dao implements SimpleAddressDao {
  /**
   * SQL definition for method selectById
   */
  private static final String SELECT_BY_ID_SQL1 = "SELECT id, address, city, name, phone, state FROM simple_address_item WHERE id=?";

  private static SQLiteStatement deleteAllPreparedStatement0;

  /**
   * SQL definition for method selectAll
   */
  private static final String SELECT_ALL_SQL2 = "SELECT id, address, city, name, phone, state FROM simple_address_item";

  private static SQLiteStatement insertPreparedStatement1;

  public SimpleAddressDaoImpl(BindSimpleDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, address, city, name, phone, state FROM simple_address_item WHERE id=${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link SimpleAddressItem}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>city</dt><dd>is associated to bean's property <strong>city</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>phone</dt><dd>is associated to bean's property <strong>phone</strong></dd>
   * 	<dt>state</dt><dd>is associated to bean's property <strong>state</strong></dd>
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
  public SimpleAddressItem selectById(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL1;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      SimpleAddressItem resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("address");
        int index2=_cursor.getColumnIndex("city");
        int index3=_cursor.getColumnIndex("name");
        int index4=_cursor.getColumnIndex("phone");
        int index5=_cursor.getColumnIndex("state");

        resultBean=new SimpleAddressItem();

        resultBean.setId(_cursor.getLong(index0));
        if (!_cursor.isNull(index1)) { resultBean.setAddress(_cursor.getString(index1)); }
        if (!_cursor.isNull(index2)) { resultBean.setCity(_cursor.getString(index2)); }
        if (!_cursor.isNull(index3)) { resultBean.setName(_cursor.getString(index3)); }
        if (!_cursor.isNull(index4)) { resultBean.setPhone(_cursor.getLong(index4)); }
        if (!_cursor.isNull(index5)) { resultBean.setState(_cursor.getString(index5)); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM simple_address_item</pre>
   *
   * <p>No where parameters were found.</p>
   *
   */
  @Override
  public void deleteAll() {
    if (deleteAllPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM simple_address_item";
      deleteAllPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteAllPreparedStatement0);

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    int result = KriptonDatabaseWrapper.updateDelete(deleteAllPreparedStatement0, _contentValues);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, address, city, name, phone, state FROM simple_address_item</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link SimpleAddressItem}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>city</dt><dd>is associated to bean's property <strong>city</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>phone</dt><dd>is associated to bean's property <strong>phone</strong></dd>
   * 	<dt>state</dt><dd>is associated to bean's property <strong>state</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public ArrayList<SimpleAddressItem> selectAll() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL2;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<SimpleAddressItem> resultList=new ArrayList<SimpleAddressItem>(_cursor.getCount());
      SimpleAddressItem resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("address");
        int index2=_cursor.getColumnIndex("city");
        int index3=_cursor.getColumnIndex("name");
        int index4=_cursor.getColumnIndex("phone");
        int index5=_cursor.getColumnIndex("state");

        do
         {
          resultBean=new SimpleAddressItem();

          resultBean.setId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setAddress(_cursor.getString(index1)); }
          if (!_cursor.isNull(index2)) { resultBean.setCity(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.setName(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { resultBean.setPhone(_cursor.getLong(index4)); }
          if (!_cursor.isNull(index5)) { resultBean.setState(_cursor.getString(index5)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO simple_address_item (address, city, name, phone, state) VALUES (:bean.address, :bean.city, :bean.name, :bean.phone, :bean.state)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>address</dt><dd>is mapped to <strong>:bean.address</strong></dd>
   * 	<dt>city</dt><dd>is mapped to <strong>:bean.city</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>phone</dt><dd>is mapped to <strong>:bean.phone</strong></dd>
   * 	<dt>state</dt><dd>is mapped to <strong>:bean.state</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insert(SimpleAddressItem bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO simple_address_item (address, city, name, phone, state) VALUES (?, ?, ?, ?, ?)";
      insertPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement1);
    _contentValues.put(bean.getAddress());
    _contentValues.put(bean.getCity());
    _contentValues.put(bean.getName());
    _contentValues.put(bean.getPhone());
    _contentValues.put(bean.getState());

    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement1, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.setId(result);
    // Specialized Insert - InsertType - END
  }

  public static void clearCompiledStatements() {
    if (deleteAllPreparedStatement0!=null) {
      deleteAllPreparedStatement0.close();
      deleteAllPreparedStatement0=null;
    }
    if (insertPreparedStatement1!=null) {
      insertPreparedStatement1.close();
      insertPreparedStatement1=null;
    }
  }
}
