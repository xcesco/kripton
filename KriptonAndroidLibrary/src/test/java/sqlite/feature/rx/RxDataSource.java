package sqlite.feature.rx;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
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

	public <T> Observable<T> execute(final ObservableBatch<T> batch) {
		return execute(batch, false);
	}

	public <T> Observable<T> execute(final ObservableBatch<T> batch, final boolean writeMode) {
		ObservableOnSubscribe<T> emitter = new ObservableOnSubscribe<T>() {

			@Override
			public void subscribe(ObservableEmitter<T> emitter) throws Exception {
				SQLiteDatabase connection = openWritableDatabase();
				try {					
					connection.beginTransaction();
					if (batch != null) {
						batch.onExecute(RxDataSource.this, emitter);		
					}
					emitter.onComplete();
				} catch (Throwable e) {					
					Logger.error(e.getMessage());
					e.printStackTrace();					
					
					emitter.onError(e);
				} finally {					
					close();
				}
			}
		};

		return Observable.create(emitter);

	}
	
	public <T> Observable<T> execute(final ObservableTransaction<T> transaction) {		
		ObservableOnSubscribe<T> emitter = new ObservableOnSubscribe<T>() {

			@Override
			public void subscribe(ObservableEmitter<T> emitter) throws Exception {
				SQLiteDatabase connection = openWritableDatabase();
				try {					
					connection.beginTransaction();
					if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(RxDataSource.this, emitter)) {
						connection.setTransactionSuccessful();						
					}
					emitter.onComplete();
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
	
	public <T> Maybe<T> execute(final MaybeExecutable<T> transaction) {
		MaybeOnSubscribe<T> emitter = new MaybeOnSubscribe<T>() {

			@Override
			public void subscribe(MaybeEmitter<T> emitter) throws Exception {
				SQLiteDatabase connection = openWritableDatabase();				
				try {
					System.out.println(Thread.currentThread().getName()+" TRANSACTION");
					connection.beginTransaction();
					if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(RxDataSource.this, emitter)) {
						connection.setTransactionSuccessful();						
					}
					emitter.onComplete();
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

		return Maybe.create(emitter);

	}
	
	public <T> Flowable<T> execute(final FlowableBatch<T> commands) {
		return execute(commands, false);
	}
	
	public <T> Flowable<T> execute(final FlowableBatch<T> commands, final boolean writeMode) {
		FlowableOnSubscribe<T> emitter = new FlowableOnSubscribe<T>() {

			@Override
			public void subscribe(FlowableEmitter<T> emitter) throws Exception {
				if (writeMode) open(); else openReadOnly();
				try {
					System.out.println(Thread.currentThread().getName()+" TRANSACTION");					
					if (commands != null) {
						commands.onExecute(RxDataSource.this, emitter);								
					}				
					emitter.onComplete();
				} catch (Throwable e) {					
					Logger.error(e.getMessage());
					e.printStackTrace();					
					
					emitter.onError(e);
				} finally {
					try {						
					} catch (Throwable e) {
						Logger.warn("error closing transaction %s", e.getMessage());
					}
					close();
				}
				
			}

		};

		return Flowable.create(emitter, BackpressureStrategy.BUFFER);
 
	}
	
	public <T> Flowable<T> execute(final FlowableTransaction<T> transaction) {
		FlowableOnSubscribe<T> emitter = new FlowableOnSubscribe<T>() {

			@Override
			public void subscribe(FlowableEmitter<T> emitter) throws Exception {
				SQLiteDatabase connection = openWritableDatabase();
				try {
					System.out.println(Thread.currentThread().getName()+" TRANSACTION");
					connection.beginTransaction();
					if (transaction != null && TransactionResult.COMMIT==transaction.onExecute(RxDataSource.this, emitter)) {
						connection.setTransactionSuccessful();						
					}				
					emitter.onComplete();
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

		return Flowable.create(emitter, BackpressureStrategy.BUFFER);
 
	}
	
	public <T> Single<T> execute(final SingleExecutable<T> executable) {
		SingleOnSubscribe<T> emitter = new SingleOnSubscribe<T>() {
						
			@Override
			public void subscribe(SingleEmitter<T> emitter) throws Exception {
				SQLiteDatabase connection = openWritableDatabase();
				try {
					System.out.println(Thread.currentThread().getName()+" TRANSACTION");
					connection.beginTransaction();
					if (executable != null && TransactionResult.COMMIT==executable.onExecute(RxDataSource.this, emitter)) {
						connection.setTransactionSuccessful();						
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

		return Single.create(emitter);

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

	
	public interface ObservableBatch<T> {
		void onExecute(BindXenoDaoFactory daoFactory, ObservableEmitter<T> emitter);
	}
	
	public interface ObservableTransaction<T> {
		TransactionResult onExecute(BindXenoDaoFactory daoFactory, ObservableEmitter<T> emitter);
	}
	
	/**
	 * interface to define transactions
	 * @param <T>
	 */
	public interface FlowableTransaction<T> {
		TransactionResult onExecute(BindXenoDaoFactory daoFactory, FlowableEmitter<T> emitter);
	}
	
	public interface FlowableBatch<T> {
		void onExecute(BindXenoDaoFactory daoFactory, FlowableEmitter<T> emitter);
	}
	
	/**
	 * interface to define transactions
	 * @param <T>
	 */
	public interface MaybeExecutable<T> {
		TransactionResult onExecute(BindXenoDaoFactory daoFactory, MaybeEmitter<T> emitter);
	}
	
	/**
	 * interface to define transactions
	 * @param <T>
	 */
	public interface SingleExecutable<T> {
		TransactionResult onExecute(BindXenoDaoFactory daoFactory, SingleEmitter<T> emitter);
	}
}
