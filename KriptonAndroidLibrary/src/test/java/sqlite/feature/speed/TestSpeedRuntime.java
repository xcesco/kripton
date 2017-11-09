package sqlite.feature.speed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.feature.speed.model.Person;
import sqlite.feature.speed.persistence.BindPersonDaoFactory;
import sqlite.feature.speed.persistence.BindPersonDataSource;
import sqlite.feature.speed.persistence.PersonDaoImpl;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestSpeedRuntime extends BaseAndroidTest {

	@Test
	public void testProfile() {
		long start=System.currentTimeMillis();
		
		final int COUNTER = 10000;

		final BindPersonDataSource ds = BindPersonDataSource.build();

		ds.execute(new BindPersonDataSource.SimpleTransaction() {

			@Override
			public TransactionResult onExecute(BindPersonDaoFactory daoFactory) {
				PersonDaoImpl dao = daoFactory.getPersonDao();
				for (int i = 0; i < COUNTER; i++) {
					Person bean = new Person();
					bean.name = "name" + i;
					bean.surname = "surname" + i;
					// Object bean = new
					dao.insert(bean);
				}

				return TransactionResult.COMMIT;
			}
		});
		
		long end=System.currentTimeMillis();
		
		//3100
		System.out.println("Esecuzione terminata in "+(end-start)+" ms");

	}
}
