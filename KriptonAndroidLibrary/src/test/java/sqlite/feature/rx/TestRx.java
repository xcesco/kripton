package sqlite.feature.rx;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.common.One;

import base.BaseAndroidTest;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import sqlite.feature.rx.RxDataSource.AsyncTransaction;
import sqlite.feature.rx.model.Country;
import sqlite.feature.rx.persistence.BindXenoDaoFactory;
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
	public void testRunAsync() {
		RxDataSource dataSource = RxDataSource.instance();

		dataSource.execute(new RxDataSource.SimpleTransaction() {
			@Override
			public boolean onExecute(BindXenoDaoFactory daoFactory) throws Throwable {
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
				return true;
			}
		});

		Disposable disposable=dataSource.executeAsync(new AsyncTransaction<Country>() {

			@Override
			public boolean onExecute(BindXenoDaoFactory daoFactory, ObservableEmitter<Country> emitter) {

				CountryDaoImpl dao = daoFactory.getCountryDao();
				List<Country> list = dao.selectAll();

				for (Country item : list) {
					emitter.onNext(item);
				}

				return true;
			}
		}).filter(new Predicate<Country>() {

			@Override
			public boolean test(Country t) throws Exception {				
				return t.id%2==0;
			}
		}).map(new Function<Country, Country>() {

			@Override
			public Country apply(Country t) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		}).subscribe(new Consumer<Country>() {

			@Override
			public void accept(Country t) throws Exception {
				System.out.println("onNext "+t.name);				
			}
		}); 
		
		disposable.dispose();
		
		/*new Observer<Country>() {

			@Override
			public void onSubscribe(Disposable d) {
				System.out.println("onSubscribe");

			}

			@Override
			public void onNext(Country t) {
				System.out.println("onNext "+t.name);

			}

			@Override
			public void onError(Throwable e) {
				System.out.println("onNext");

			}

			@Override
			public void onComplete() {
				System.out.println("onComplete");

			}
		});*/
	}

	@Test
	public void testRunSyncWithListener() {
		RxDataSource dataSource = RxDataSource.instance();

		dataSource.execute(new RxDataSource.SimpleTransaction() {
			@Override
			public boolean onExecute(BindXenoDaoFactory daoFactory) throws Throwable {
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
				return true;
			}
		});

		dataSource.execute(new RxDataSource.SimpleTransaction() {

			@Override
			public boolean onExecute(BindXenoDaoFactory daoFactory) throws Throwable {
				System.out.println("onSubscribe");
				CountryDaoImpl dao = daoFactory.getCountryDao();
				dao.selectAll(new OnReadBeanListener<Country>() {
					
					@Override
					public void onRead(Country bean, int row, int rowCount) {
						System.out.println("onNext" + bean);
						
					}
				});

				System.out.println("onComplete");

				return true;
			}
		});
	}
	
	@Test
	public void testRunSync() {
		RxDataSource dataSource = RxDataSource.instance();

		dataSource.execute(new RxDataSource.SimpleTransaction() {
			@Override
			public boolean onExecute(BindXenoDaoFactory daoFactory) throws Throwable {
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
				return true;
			}
		});

		dataSource.execute(new RxDataSource.SimpleTransaction() {

			@Override
			public boolean onExecute(BindXenoDaoFactory daoFactory) throws Throwable {
				System.out.println("onSubscribe");
				CountryDaoImpl dao = daoFactory.getCountryDao();
				List<Country> list = dao.selectAll();

				for (Country item : list) {
					System.out.println("onNext" + item);
				}

				System.out.println("onComplete");

				return true;
			}
		});
	}
}
