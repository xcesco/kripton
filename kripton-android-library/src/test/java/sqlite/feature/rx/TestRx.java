package sqlite.feature.rx;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.orm.OnReadBeanListener;
import com.abubusoft.kripton.android.orm.SQLiteEvent;
import com.abubusoft.kripton.android.orm.TransactionResult;
import com.abubusoft.kripton.common.One;

import base.BaseAndroidTest;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import sqlite.feature.rx.model.Country;
import sqlite.feature.rx.persistence.BindXenoDaoFactory;
import sqlite.feature.rx.persistence.BindXenoDataSource;
import sqlite.feature.rx.persistence.CountryDaoImpl;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestRx extends BaseAndroidTest {

	private static final int COUNTER = 10;

	// .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
	@Test
	public void test() {
		final One<String> result = new One<String>("");
		Observable<String> observer = Observable.just("Hello"); // provides
																// datea
		observer.subscribe(new Consumer<String>() {
			@Override
			public void accept(String s) throws Exception {
				result.value0 = s;
			} 
		});
		// Callable as subscriber
		assertTrue(result.value0.equals("Hello"));
	}

	@Test
	public void testDatabase() {
		final BindXenoDataSource ds = prepareDataSource();

		Disposable s1 = ds.getCountryDao().subject().subscribe(new Consumer<SQLiteEvent>() {

			@Override
			public void accept(SQLiteEvent t) throws Exception {
				log("---->  MAP " + Thread.currentThread().getName());
				log("S1 ---------------------- receive country %s %s", t.operationType, t.value);

			}
		});
		/*
		 * ds.execute(new ObservableTransaction<Country>() {
		 * 
		 * @Override public TransactionResult onExecute(BindXenoDaoFactory
		 * daoFactory, ObservableEmitter<Country> emitter) { log("onExecute " +
		 * Thread.currentThread().getName()); CountryDaoImpl dao =
		 * daoFactory.getCountryDao();
		 * 
		 * List<Country> list = dao.selectAll();
		 * 
		 * for (Country item : list) { emitter.onNext(item); }
		 * 
		 * return TransactionResult.COMMIT; }
		 * }).subscribeOn(Schedulers.computation()).observeOn(Schedulers.io()).
		 * subscribe(new Consumer<Country>() {
		 * 
		 * @Override public void accept(Country t) throws Exception {
		 * log("accept " + Thread.currentThread().getName()); log(" country " +
		 * t.name);
		 * 
		 * } });
		 */

		ds.executeBatch(new BindXenoDataSource.Batch<Void>() {

			@Override
			public Void onExecute(BindXenoDaoFactory daoFactory) {

				for (int i = 100; i < 102; i++) {
					Country bean = new Country();
					bean.code = "code" + i;
					bean.callingCode = "" + i;
					bean.name = "name" + i;
					daoFactory.getCountryDao().insert(bean);
				}

				return null;
			}
		});

		Disposable s2 = ds.countrySubject().observeOn(Schedulers.io()).map(new Function<SQLiteEvent, List<Country>>() {

			@Override
			public List<Country> apply(SQLiteEvent t) throws Exception {
				log("---->  MAP " + Thread.currentThread().getName());

				return ds.executeBatch(new BindXenoDataSource.Batch<List<Country>>() {

					@Override
					public List<Country> onExecute(BindXenoDaoFactory daoFactory) {
						return daoFactory.getCountryDao().selectAll();

					}
				});

			}
		}).subscribe(new Consumer<List<Country>>() {

			@Override
			public void accept(List<Country> t) throws Exception {
				log("---->  S2 " + Thread.currentThread().getName());
				// log("S2 ---------------------- receive country %s
				// %s",t.operationType , t.value);
				// ds.getCountryDao().selectAll();

			}
		});

		ds.executeBatch(new BindXenoDataSource.Batch<Void>() {

			@Override
			public Void onExecute(BindXenoDaoFactory daoFactory) {

				for (int i = 200; i < 202; i++) {
					Country bean = new Country();
					bean.code = "code" + i;
					bean.callingCode = "" + i;
					bean.name = "name" + i;
					daoFactory.getCountryDao().insert(bean);
				}

				return null;
			}

		});

		try {
			Thread.currentThread().sleep(5000);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		s1.dispose();
		s2.dispose();
	}

	public BindXenoDataSource prepareDataSource() {
		BindXenoDataSource dataSource = BindXenoDataSource.instance();

		dataSource.executeBatch(new BindXenoDataSource.Batch<Void>() {

			@Override
			public Void onExecute(BindXenoDaoFactory daoFactory) {
				CountryDaoImpl dao = daoFactory.getCountryDao();

				for (int i = 0; i < COUNTER; i++) {
					Country bean = new Country();
					bean.code = "code" + i;
					bean.callingCode = "" + i;
					bean.name = "name" + i;
					// Object bean = new
					dao.insert(bean);
				}

				return null;

				// dao.selectAll();

			}
		});

		return dataSource;
	}

	@Test
	public void testRunAsync() {
		BindXenoDataSource dataSource = prepareDataSource();

		dataSource.execute(new BindXenoDataSource.ObservableTransaction<Country>() {

			@Override
			public TransactionResult onExecute(BindXenoDaoFactory daoFactory, ObservableEmitter<Country> emitter) {
				CountryDaoImpl dao = daoFactory.getCountryDao();
				List<Country> list = dao.selectAll();

				for (Country item : list) {
					emitter.onNext(item);
				}

				return TransactionResult.COMMIT;
			}
		}).subscribeOn(Schedulers.newThread()).subscribe(

				new Observer<Country>() {

					@Override
					public void onSubscribe(Disposable d) {
						System.out.println(Thread.currentThread().getName() + " onSubscribe");

					}

					@Override
					public void onNext(Country t) {
						System.out.println(Thread.currentThread().getName() + " onNext " + t.name);

					}

					@Override
					public void onError(Throwable e) {
						System.out.println(Thread.currentThread().getName() + " onNext");

					}

					@Override
					public void onComplete() {
						System.out.println(Thread.currentThread().getName() + " onComplete");

					}
				});
		System.out.println(Thread.currentThread().getName() + " Finished");

		try {
			Thread.currentThread().sleep(5000);
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Test
	public void testRunSyncWithListener() {
		BindXenoDataSource dataSource = BindXenoDataSource.instance();

		dataSource.execute(new BindXenoDataSource.Transaction() {
			@Override
			public TransactionResult onExecute(BindXenoDaoFactory daoFactory) {
				CountryDaoImpl dao = daoFactory.getCountryDao();

				for (int i = 0; i < COUNTER; i++) {
					Country bean = new Country();
					bean.code = "code" + i;
					bean.callingCode = "" + i;
					bean.name = "name" + i;
					// Object bean = new
					dao.insert(bean);
				}

				dao.selectAll();
				return TransactionResult.COMMIT;
			}
		});

		dataSource.execute(new BindXenoDataSource.Transaction() {

			@Override
			public TransactionResult onExecute(BindXenoDaoFactory daoFactory) {
				System.out.println("onSubscribe");
				CountryDaoImpl dao = daoFactory.getCountryDao();
				dao.selectAll(new OnReadBeanListener<Country>() {

					@Override
					public void onRead(Country bean, int row, int rowCount) {
						System.out.println("onNext" + bean);

					}
				});

				System.out.println("onComplete");

				return TransactionResult.COMMIT;
			}
		});
	}

	@Test
	public void testRunSync() {
		BindXenoDataSource dataSource = BindXenoDataSource.instance();

		dataSource.execute(new BindXenoDataSource.Transaction() {
			@Override
			public TransactionResult onExecute(BindXenoDaoFactory daoFactory) {
				CountryDaoImpl dao = daoFactory.getCountryDao();

				for (int i = 0; i < COUNTER; i++) {
					Country bean = new Country();
					bean.code = "code" + i;
					bean.callingCode = "" + i;
					bean.name = "name" + i;
					// Object bean = new
					dao.insert(bean);
				}

				dao.selectAll();
				return TransactionResult.COMMIT;
			}
		});

		dataSource.execute(new BindXenoDataSource.Transaction() {

			@Override
			public TransactionResult onExecute(BindXenoDaoFactory daoFactory) {
				System.out.println("onSubscribe");
				CountryDaoImpl dao = daoFactory.getCountryDao();
				List<Country> list = dao.selectAll();

				for (Country item : list) {
					System.out.println("onNext" + item);
				}

				System.out.println("onComplete");

				return TransactionResult.COMMIT;
			}
		});
	}
}
