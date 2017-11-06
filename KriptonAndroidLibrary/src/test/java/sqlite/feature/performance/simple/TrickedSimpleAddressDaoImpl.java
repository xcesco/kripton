package sqlite.feature.performance.simple;

import java.util.ArrayList;
import java.util.HashMap;

import com.abubusoft.kripton.android.sqlite.database.KriptonContentValues;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

/**
 * <p>
 * DAO implementation for entity <code>SimpleAddressItem</code>, based on
 * interface <code>SimpleAddressDao</code>
 * </p>
 *
 * @see SimpleAddressItem
 * @see SimpleAddressDao
 * @see SimpleAddressItemTable
 */
public class TrickedSimpleAddressDaoImpl extends SimpleAddressDaoImpl {
	public TrickedSimpleAddressDaoImpl(BindSimpleDataSource dataSet) {
		super(dataSet);
	}

	/**
	 * <h2>Select SQL:</h2>
	 *
	 * <pre>
	 * SELECT id, name, address, city, state, phone FROM simple_address_item WHERE id=${id}
	 * </pre>
	 *
	 * <h2>Projected columns:</h2>
	 * <dl>
	 * <dt>id</dt>
	 * <dd>is associated to bean's property <strong>id</strong></dd>
	 * <dt>name</dt>
	 * <dd>is associated to bean's property <strong>name</strong></dd>
	 * <dt>address</dt>
	 * <dd>is associated to bean's property <strong>address</strong></dd>
	 * <dt>city</dt>
	 * <dd>is associated to bean's property <strong>city</strong></dd>
	 * <dt>state</dt>
	 * <dd>is associated to bean's property <strong>state</strong></dd>
	 * <dt>phone</dt>
	 * <dd>is associated to bean's property <strong>phone</strong></dd>
	 * </dl>
	 *
	 * <h2>Query's parameters:</h2>
	 * <dl>
	 * <dt>${id}</dt>
	 * <dd>is binded to method's parameter <strong>id</strong></dd>
	 * </dl>
	 *
	 * @param id
	 *            is binded to <code>${id}</code>
	 * @return selected bean or <code>null</code>.
	 */
	@Override
	public SimpleAddressItem selectById(long id) {
		StringBuilder _sqlBuilder = getSQLStringBuilder();
		_sqlBuilder.append("SELECT id, name, address, city, state, phone FROM simple_address_item");
		// generation CODE_001 -- BEGIN
		// generation CODE_001 -- END
		//ArrayList<String> _sqlWhereParams = getWhereParamsArray();
		KriptonContentValues _contentValues=contentValues();

		// manage WHERE arguments -- BEGIN

		// manage WHERE statement
		String _sqlWhereStatement = " WHERE id=?";
		_sqlBuilder.append(_sqlWhereStatement);

		// manage WHERE arguments -- END

		// build where condition
		_contentValues.addWhereArgs(String.valueOf(id));
		String _sql = _sqlBuilder.toString();
		//String[] _sqlArgs = _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
		try (Cursor cursor = database().rawQuery(_sql, _contentValues.whereArgsAsArray())) {

			SimpleAddressItem resultBean = null;

			if (cursor.moveToFirst()) {

				int index0 = cursor.getColumnIndex("id");
				int index1 = cursor.getColumnIndex("name");
				int index2 = cursor.getColumnIndex("address");
				int index3 = cursor.getColumnIndex("city");
				int index4 = cursor.getColumnIndex("state");
				int index5 = cursor.getColumnIndex("phone");

				resultBean = new SimpleAddressItem();

				resultBean.setId(cursor.getLong(index0));
				if (!cursor.isNull(index1)) {
					resultBean.setName(cursor.getString(index1));
				}
				if (!cursor.isNull(index2)) {
					resultBean.setAddress(cursor.getString(index2));
				}
				if (!cursor.isNull(index3)) {
					resultBean.setCity(cursor.getString(index3));
				}
				if (!cursor.isNull(index4)) {
					resultBean.setState(cursor.getString(index4));
				}
				if (!cursor.isNull(index5)) {
					resultBean.setPhone(cursor.getLong(index5));
				}

			}
			return resultBean;
		}
	}

	/**
	 * <p>
	 * SQL insert:
	 * </p>
	 * 
	 * <pre>
	 * INSERT INTO simple_address_item (name, address, city, state, phone) VALUES (${bean.name}, ${bean.address}, ${bean.city}, ${bean.state}, ${bean.phone})
	 * </pre>
	 *
	 * <p>
	 * <code>bean.id</code> is automatically updated because it is the primary
	 * key
	 * </p>
	 *
	 * <p>
	 * <strong>Inserted columns:</strong>
	 * </p>
	 * <dl>
	 * <dt>name</dt>
	 * <dd>is mapped to <strong>${bean.name}</strong></dd>
	 * <dt>address</dt>
	 * <dd>is mapped to <strong>${bean.address}</strong></dd>
	 * <dt>city</dt>
	 * <dd>is mapped to <strong>${bean.city}</strong></dd>
	 * <dt>state</dt>
	 * <dd>is mapped to <strong>${bean.state}</strong></dd>
	 * <dt>phone</dt>
	 * <dd>is mapped to <strong>${bean.phone}</strong></dd>
	 * </dl>
	 *
	 * @param bean
	 *            is mapped to parameter <strong>bean</strong>
	 *
	 */	
	String insertOptimizedSql = "INSERT INTO simple_address_item (name, address, city, state, phone) VALUES (?, ?, ?, ?, ?)";
	
	HashMap<String, SQLiteStatement> map=new HashMap<>();

	public void insertOptimized(SimpleAddressItem bean) {
		SQLiteStatement insertOptimizedStatement=map.get(insertOptimizedSql);
		if (insertOptimizedStatement == null) {
			insertOptimizedStatement = this.dataSource.database().compileStatement(insertOptimizedSql);
			map.put(insertOptimizedSql, insertOptimizedStatement);
		} 

		if (bean.getName() != null) {
			insertOptimizedStatement.bindString(1, bean.getName());
		} else {
			insertOptimizedStatement.bindNull(1);
		}

		if (bean.getAddress() != null) {
			insertOptimizedStatement.bindString(2, bean.getAddress());
		} else {
			insertOptimizedStatement.bindNull(2);
		}


		if (bean.getCity() != null) {
			insertOptimizedStatement.bindString(3, bean.getCity());
		} else {
			insertOptimizedStatement.bindNull(3);
		}
		
		if (bean.getState() != null) {
			insertOptimizedStatement.bindString(4, bean.getState());
		} else {
			insertOptimizedStatement.bindNull(4);
		}
		
		insertOptimizedStatement.bindLong(5, bean.getPhone());
		

		long result = insertOptimizedStatement.executeInsert();
		bean.setId(result);

	}

	boolean d = false;
	int index0;
	int index1;
	int index2;
	int index3;
	int index4;
	int index5;

	public ArrayList<SimpleAddressItem> selectAllOptimzed() {
		StringBuilder _sqlBuilder = getSQLStringBuilder();
		_sqlBuilder.append("SELECT id, name, address, city, state, phone FROM simple_address_item");
		// generation CODE_001 -- BEGIN
		// generation CODE_001 -- END
		KriptonContentValues _contentValues=contentValues();
		String _sqlWhereStatement = "";

		// build where condition
		// StringUtils, SqlUtils will be used in case of dynamic parts of SQL
		String _sql = _sqlBuilder.toString();
		//String[] _sqlArgs = _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
		try (Cursor cursor = database().rawQuery(_sql, _contentValues.whereArgsAsArray())) {

			ArrayList<SimpleAddressItem> resultList = new ArrayList<SimpleAddressItem>();
			SimpleAddressItem resultBean = null;

			if (cursor.moveToFirst()) {

				if (!d) {
					index0 = cursor.getColumnIndex("id");
					index1 = cursor.getColumnIndex("name");
					index2 = cursor.getColumnIndex("address");
					index3 = cursor.getColumnIndex("city");
					index4 = cursor.getColumnIndex("state");
					index5 = cursor.getColumnIndex("phone");
					d = true;
				}

				do {
					resultBean = new SimpleAddressItem();

					resultBean.setId(cursor.getLong(index0));
					if (!cursor.isNull(index1)) {
						resultBean.setName(cursor.getString(index1));
					}
					if (!cursor.isNull(index2)) {
						resultBean.setAddress(cursor.getString(index2));
					}
					if (!cursor.isNull(index3)) {
						resultBean.setCity(cursor.getString(index3));
					}
					if (!cursor.isNull(index4)) {
						resultBean.setState(cursor.getString(index4));
					}
					if (!cursor.isNull(index5)) {
						resultBean.setPhone(cursor.getLong(index5));
					}

					resultList.add(resultBean);
				} while (cursor.moveToNext());
			}

			return resultList;
		}
	}

}
