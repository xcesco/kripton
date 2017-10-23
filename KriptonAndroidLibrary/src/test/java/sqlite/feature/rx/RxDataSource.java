package sqlite.feature.rx;

import android.database.sqlite.SQLiteDatabase;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.List;
import sqlite.feature.rx.model.CountryTable;
import sqlite.feature.rx.model.PersonTable;
import sqlite.feature.rx.model.PhoneNumberTable;
import sqlite.feature.rx.model.PrefixConfigTable;
import sqlite.feature.rx.persistence.BindXenoDaoFactory;
import sqlite.feature.rx.persistence.BindXenoDataSource;

/**
 * <p>
 * Represents implementation of datasource XenoDataSource. This class expose
 * database interface through Dao attribute.
 * </p>
 *
 * @see XenoDataSource
 * @see BindXenoDaoFactory
 * @see PhoneDao
 * @see PhoneDaoImpl
 * @see PhoneNumber
 * @see PrefixConfigDao
 * @see PrefixConfigDaoImpl
 * @see PrefixConfig
 * @see CountryDao
 * @see CountryDaoImpl
 * @see Country
 * @see Person2PhoneDao
 * @see Person2PhoneDaoImpl
 * @see PersonPhoneNumber
 * @see PersonDao
 * @see PersonDaoImpl
 * @see Person
 */
public class RxDataSource extends BindXenoDataSource {
	

	private static RxDataSource instance;


	protected RxDataSource(DataSourceOptions options) {
		super(options);
	}


	public <T> Observable<T> executeAsync(final AsyncTransaction<T> transaction) {
		ObservableOnSubscribe<T> emitter = new ObservableOnSubscribe<T>() {

			@Override
			public void subscribe(ObservableEmitter<T> emitter) throws Exception {
				SQLiteDatabase connection = openWritableDatabase();
				try {
					System.out.println(Thread.currentThread().getName()+" TRANSACTION");
					connection.beginTransaction();
					if (transaction != null && transaction.onExecute(RxDataSource.this, emitter)) {
						connection.setTransactionSuccessful();
						emitter.onComplete();
					}
				} catch (Throwable e) {					
					Logger.error(e.getMessage());
					e.printStackTrace();					
					
					emitter.onError(e);
				} finally {
					try {
						connection.endTransaction();
					} catch (Throwable e) {
						Logger.warn("error closing transaction %s", e.getMessage());
					}
					close();
				}
			}

		};

		return Observable.create(emitter);

	}
	

	/**
	 * instance
	 */
	public static synchronized RxDataSource instance() {
		if (instance == null) {
			instance = new RxDataSource(null);
		}
		return instance;
	}


	/**
	 * Build instance.
	 * 
	 * @return dataSource instance.
	 */
	public static synchronized RxDataSource build(DataSourceOptions options) {
		if (instance == null) {
			instance = new RxDataSource(options);
		}
		instance.openWritableDatabase();
		return instance;
	}

	
	/**
	 * interface to define transactions
	 * @param <T>
	 */
	public interface AsyncTransaction<T> {
		boolean onExecute(BindXenoDaoFactory daoFactory, ObservableEmitter<T> emitter);
	}
}
