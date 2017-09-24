package sqlite.feature.performance.simple;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
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
public class SimpleAddressDaoImpl extends AbstractDao implements SimpleAddressDao {
  public SimpleAddressDaoImpl(BindSimpleDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, address, city, state, phone FROM simple_address_item WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>city</dt><dd>is associated to bean's property <strong>city</strong></dd>
   * 	<dt>state</dt><dd>is associated to bean's property <strong>state</strong></dd>
   * 	<dt>phone</dt><dd>is associated to bean's property <strong>phone</strong></dd>
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
  public SimpleAddressItem selectById(long id) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, name, address, city, state, phone FROM simple_address_item");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {

      SimpleAddressItem resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("address");
        int index3=cursor.getColumnIndex("city");
        int index4=cursor.getColumnIndex("state");
        int index5=cursor.getColumnIndex("phone");

        resultBean=new SimpleAddressItem();

        resultBean.setId(cursor.getLong(index0));
        if (!cursor.isNull(index1)) { resultBean.setName(cursor.getString(index1)); }
        if (!cursor.isNull(index2)) { resultBean.setAddress(cursor.getString(index2)); }
        if (!cursor.isNull(index3)) { resultBean.setCity(cursor.getString(index3)); }
        if (!cursor.isNull(index4)) { resultBean.setState(cursor.getString(index4)); }
        if (!cursor.isNull(index5)) { resultBean.setPhone(cursor.getLong(index5)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM simple_address_item</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * </dl>
   *
   */
  @Override
  public void deleteAll() {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";
    int result = database().delete("simple_address_item", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, address, city, state, phone FROM simple_address_item</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>address</dt><dd>is associated to bean's property <strong>address</strong></dd>
   * 	<dt>city</dt><dd>is associated to bean's property <strong>city</strong></dd>
   * 	<dt>state</dt><dd>is associated to bean's property <strong>state</strong></dd>
   * 	<dt>phone</dt><dd>is associated to bean's property <strong>phone</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public ArrayList<SimpleAddressItem> selectAll() {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, name, address, city, state, phone FROM simple_address_item");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    String _sqlWhereStatement="";

    // build where condition
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {

      ArrayList<SimpleAddressItem> resultList=new ArrayList<SimpleAddressItem>();
      SimpleAddressItem resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("address");
        int index3=cursor.getColumnIndex("city");
        int index4=cursor.getColumnIndex("state");
        int index5=cursor.getColumnIndex("phone");

        do
         {
          resultBean=new SimpleAddressItem();

          resultBean.setId(cursor.getLong(index0));
          if (!cursor.isNull(index1)) { resultBean.setName(cursor.getString(index1)); }
          if (!cursor.isNull(index2)) { resultBean.setAddress(cursor.getString(index2)); }
          if (!cursor.isNull(index3)) { resultBean.setCity(cursor.getString(index3)); }
          if (!cursor.isNull(index4)) { resultBean.setState(cursor.getString(index4)); }
          if (!cursor.isNull(index5)) { resultBean.setPhone(cursor.getLong(index5)); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO simple_address_item (name, address, city, state, phone) VALUES (${bean.name}, ${bean.address}, ${bean.city}, ${bean.state}, ${bean.phone})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>address</dt><dd>is mapped to <strong>${bean.address}</strong></dd>
   * 	<dt>city</dt><dd>is mapped to <strong>${bean.city}</strong></dd>
   * 	<dt>state</dt><dd>is mapped to <strong>${bean.state}</strong></dd>
   * 	<dt>phone</dt><dd>is mapped to <strong>${bean.phone}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insert(SimpleAddressItem bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.getName()!=null) {
      contentValues.put("name", bean.getName());
    } else {
      contentValues.putNull("name");
    }
    if (bean.getAddress()!=null) {
      contentValues.put("address", bean.getAddress());
    } else {
      contentValues.putNull("address");
    }
    if (bean.getCity()!=null) {
      contentValues.put("city", bean.getCity());
    } else {
      contentValues.putNull("city");
    }
    if (bean.getState()!=null) {
      contentValues.put("state", bean.getState());
    } else {
      contentValues.putNull("state");
    }
    contentValues.put("phone", bean.getPhone());

    long result = database().insert("simple_address_item", null, contentValues);
    bean.setId(result);
  }
}
