package sqlite.feature.livedata.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.android.sqlite.SQLiteModification;
import com.abubusoft.kripton.common.StringUtils;

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
public class DaoPersonImpl extends AbstractDao implements DaoPerson {

	private static final PublishSubject<SQLiteModification> subject = PublishSubject.create();

	private static final String SELECT_SQL1 = "SELECT id, name, surname FROM person WHERE name=?";

	public DaoPersonImpl(SQLContext context) {
		super(context);
	}

	/**
	 * Version of {@link DaoPersonImpl#select(String)} that works with live
	 * data. This method include a connection. So there no need to include it in
	 * a transaction.
	 * 
	 * @param name
	 * @return
	 */
	public LiveData<List<Person>> selectAsLiveData(final String name) {

		final ComputableLiveData<List<Person>> builder = new ComputableLiveData<List<Person>>() {

			@Override
			protected List<Person> compute() {

				return BindAppDataSource.instance().executeBatch(new Batch<List<Person>>() {

					@Override
					public List<Person> onExecute(BindAppDaoFactory daoFactory) {
						return daoFactory.getDaoPerson().select(name);
					}
				});

			}
		};
		
		registryLiveData(builder);
		
		return builder.getLiveData();
	}
	
	Set<ComputableLiveData<?>> cld=new HashSet<>();
	
	void registryLiveData(ComputableLiveData<?> value) {		
		cld.add(value);
	}
	
	void invalidateLiveData() {
		for (ComputableLiveData<?> item: cld) {
			item.invalidate();
		}
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
	@Override
	public List<Person> select(String name) {
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
