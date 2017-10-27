package sqlite.feature.rx;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.SQLOperationType;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;

import base.BaseAndroidTest;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import sqlite.feature.rx.model.Country;
import sqlite.feature.rx.persistence.BindXenoDaoFactory;
import sqlite.feature.rx.persistence.BindXenoDataSource;
import sqlite.feature.rx.persistence.BindXenoDataSource.ObservableTransaction;
import sqlite.feature.rx.persistence.CountryDaoImpl;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestRx extends BaseAndroidTest {

	private static final int COUNTER = 10;

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
		BindXenoDataSource ds = prepareDataSource();
						
		ds.getCountryDao().subject().subscribe(new Consumer<Pair<SQLOperationType, Long>>() {
 
			@Override
			public void accept(Pair<SQLOperationType, Long> t) throws Exception {
				log("S1 ---------------------- receive country %s %s",t.value0 , t.value1);	
				
			}
		});
/*
		ds.execute(new ObservableTransaction<Country>() {

			@Override
			public TransactionResult onExecute(BindXenoDaoFactory daoFactory, ObservableEmitter<Country> emitter) {
				log("onExecute " + Thread.currentThread().getName());
				CountryDaoImpl dao = daoFactory.getCountryDao();

				List<Country> list = dao.selectAll();

				for (Country item : list) {
					emitter.onNext(item);
				}

				return TransactionResult.COMMIT;
			}
		}).subscribeOn(Schedulers.computation()).observeOn(Schedulers.io()).subscribe(new Consumer<Country>() {

			@Override
			public void accept(Country t) throws Exception {
				log("accept " + Thread.currentThread().getName());
				log(" country " + t.name);

			}
		});
		*/
		ds.getCountryDao().subject().subscribe(new Consumer<Pair<SQLOperationType, Long>>() {
			 
			@Override
			public void accept(Pair<SQLOperationType, Long> t) throws Exception {
				log("S2 ---------------------- receive country %s %s",t.value0 , t.value1);	
				
			}
		});
		
		ds.execute(new BindXenoDataSource.SimpleBatch() {
							
			@Override
			public void onExecute(BindXenoDaoFactory daoFactory) {
				Country bean=new Country();
				int i=110;
				bean.code = "code" + i;
				bean.callingCode = "" + i;
				bean.name = "name" + i;
				
				daoFactory.getCountryDao().insert(bean);
				
			}
		}); 
		

		

		try {
			Thread.currentThread().sleep(5000);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BindXenoDataSource prepareDataSource() {
		BindXenoDataSource dataSource = BindXenoDataSource.instance();

		dataSource.execute(new BindXenoDataSource.SimpleBatch() {

			@Override
			public void onExecute(BindXenoDaoFactory daoFactory) {
				CountryDaoImpl dao = daoFactory.getCountryDao();

				for (int i = 0; i < COUNTER; i++) {
					Country bean = new Country();
					bean.code = "code" + i;
					bean.callingCode = "" + i;
					bean.name = "name" + i;
					// Object bean = new
					dao.insert(bean);
				}

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

		dataSource.execute(new BindXenoDataSource.SimpleTransaction() {
			@Override
			public TransactionResult onExecute(BindXenoDaoFactory daoFactory){
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

		dataSource.execute(new BindXenoDataSource.SimpleTransaction() {

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

		dataSource.execute(new BindXenoDataSource.SimpleTransaction() {
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

		dataSource.execute(new BindXenoDataSource.SimpleTransaction() {

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
