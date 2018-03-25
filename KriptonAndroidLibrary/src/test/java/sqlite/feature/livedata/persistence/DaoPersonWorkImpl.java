package sqlite.feature.livedata.persistence;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.android.sqlite.SQLiteEvent;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.database.Cursor;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import sqlite.feature.livedata.data.Person;
import sqlite.feature.livedata.persistence.BindAppDataSource.Batch;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface
 * <code>DaoPerson</code>
 * </p>
 *
 * @see Person
 * @see DaoPerson
 * @see sqlite.feature.livedata.data.PersonTable
 */
public class DaoPersonWorkImpl extends Dao implements DaoPerson {

	private static final PublishSubject<SQLiteEvent> subject = PublishSubject.create();

	private static final String SELECT_SQL1 = "SELECT id, name, surname FROM person WHERE name=?";

	public DaoPersonWorkImpl(SQLContext context) {
		super(context);
	}

	/**
	 * Version of {@link DaoPersonWorkImpl#select(String)} that works with live
	 * data. This method include a connection. So there no need to include it in
	 * a transaction.
	 * 
	 * @param name
	 * @return
	 */
	@Override
	public LiveData<List<Person>> select(final String name) {

		final ComputableLiveData<List<Person>> builder = new ComputableLiveData<List<Person>>() {

			@Override
			protected List<Person> compute() {

				return BindAppDataSource.instance().executeBatch(new Batch<List<Person>>() {

					@Override
					public List<Person> onExecute(BindAppDaoFactory daoFactory) {
						return daoFactory.getDaoPerson().selectForLiveData(name);
					}
				});

			}
		};
		
		registryLiveData(builder);
		
		return builder.getLiveData();
	}
	
	static Collection<WeakReference<ComputableLiveData<?>>> cld=Collections.synchronizedCollection(new HashSet<WeakReference<ComputableLiveData<?>>>());
		
	static void registryLiveData(ComputableLiveData<?> value) {		
		cld.add(new WeakReference<ComputableLiveData<?>>(value));
	}
	
	static void invalidateLiveData() {
		for (WeakReference<ComputableLiveData<?>> item: cld) {
			if (item.get()!=null) {
				item.get().invalidate();
			}
		}
	}
	
	void sendEvent() {
		_context.registrySQLEvent("sqlite.feature.livedata.persistence.DaoPersonWorkImpl");
	}
	
	  /**
	   * <p>SQL insert:</p>
	   * <pre>INSERT INTO person (name, surname) VALUES (${bean.name}, ${bean.surname})</pre>
	   *
	   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
	   *
	   * <p><strong>Inserted columns:</strong></p>
	   * <dl>
	   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
	   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
	   * </dl>
	   *
	   * @param bean
	   * 	is mapped to parameter <strong>bean</strong>
	   *
	   */
	  @Override
	  public void insert(Person bean) {
	    if (insertPreparedStatement0==null) {
	      // generate static SQL for statement
	      String _sql="INSERT INTO person (name, surname) VALUES (?, ?)";
	      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
	    }
	    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
	    _contentValues.put("name", bean.name);
	    _contentValues.put("surname", bean.surname);

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
	      Logger.info("INSERT INTO person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
	    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement0, _contentValues);
	    
	    this._context.sendSQLEvent("DaoPerson");
	    
	    bean.id=result;
	  }

	  /**
	   * <h2>SQL update:</h2>
	   * <pre>UPDATE person SET name=:name, surname=:surname WHERE id=${bean.id}</pre>
	   *
	   * <h2>Updated columns:</h2>
	   * <dl>
	   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
	   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
	   * </dl>
	   *
	   * <h2>Parameters used in where conditions:</h2>
	   * <dl>
	   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
	   * </dl>
	   *
	   * @param bean
	   * 	is used as ${bean}
	   */
	  @Override
	  public void update(Person bean) {
	    if (updatePreparedStatement1==null) {
	      // generate static SQL for statement
	      String _sql="UPDATE person SET name=?, surname=? WHERE id=?";
	      updatePreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
	    }
	    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement1);
	    _contentValues.put("name", bean.name);
	    _contentValues.put("surname", bean.surname);

	    _contentValues.addWhereArgs(String.valueOf(bean.id));

	    // generation CODE_001 -- BEGIN
	    // generation CODE_001 -- END
	    // log section BEGIN
	    if (_context.isLogEnabled()) {

	      // display log
	      Logger.info("UPDATE person SET name=:name, surname=:surname WHERE id=?");

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
	    int result = KriptonDatabaseWrapper.updateDelete(updatePreparedStatement1, _contentValues);
	    
	    _context.onSQLEvent(this, SQLiteEvent.createUpdate(result));
	  }

	/**
	 * <h2>Select SQL:</h2>
	 *
	 * <pre>
	 * SELECT id, name, surname FROM person WHERE name=${name}
	 * </pre>
	 *
	 * <h2>Projected columns:</h2>
	 * <dl>
	 * <dt>id</dt>
	 * <dd>is associated to bean's property <strong>id</strong></dd>
	 * <dt>name</dt>
	 * <dd>is associated to bean's property <strong>name</strong></dd>
	 * <dt>surname</dt>
	 * <dd>is associated to bean's property <strong>surname</strong></dd>
	 * </dl>
	 *
	 * <h2>Query's parameters:</h2>
	 * <dl>
	 * <dt>${name}</dt>
	 * <dd>is binded to method's parameter <strong>name</strong></dd>
	 * </dl>
	 *
	 * @param name
	 *            is binded to <code>${name}</code>
	 * @return collection of bean or empty collection.
	 */
	private List<Person> selectForLiveData(String name) {
		KriptonContentValues _contentValues = contentValues();
		// query SQL is statically defined
		String _sql = SELECT_SQL1;
		// add where arguments
		_contentValues.addWhereArgs((name == null ? "" : name));
		String[] _sqlArgs = _contentValues.whereArgsAsArray();
		// log section BEGIN
		if (_context.isLogEnabled()) {
			// manage log
			Logger.info(_sql);

			// log for where parameters -- BEGIN
			int _whereParamCounter = 0;
			for (String _whereParamItem : _contentValues.whereArgs()) {
				Logger.info("==> param%s: '%s'", (_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
			}
			// log for where parameters -- END
		}
		// log section END
		try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
			// log section BEGIN
			if (_context.isLogEnabled()) {
				Logger.info("Rows found: %s", _cursor.getCount());
			}
			// log section END

			ArrayList<Person> resultList = new ArrayList<Person>(_cursor.getCount());
			Person resultBean = null;

			if (_cursor.moveToFirst()) {

				int index0 = _cursor.getColumnIndex("id");
				int index1 = _cursor.getColumnIndex("name");
				int index2 = _cursor.getColumnIndex("surname");

				do {
					resultBean = new Person();

					resultBean.id = _cursor.getLong(index0);
					if (!_cursor.isNull(index1)) {
						resultBean.name = _cursor.getString(index1);
					}
					if (!_cursor.isNull(index2)) {
						resultBean.surname = _cursor.getString(index2);
					}

					resultList.add(resultBean);
				} while (_cursor.moveToNext());

			}
			return resultList;
		}

	}

	public static void clearCompiledStatements() {
	}
}
