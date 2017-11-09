package sqlite.feature.speed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.common.One;

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
		final One<Long> start=new One<>();
		final One<Long> end=new One<>();
		
		final int COUNTER = 10000;

		final BindPersonDataSource ds = BindPersonDataSource.build();

		ds.execute(new BindPersonDataSource.SimpleTransaction() {

			@Override
			public TransactionResult onExecute(BindPersonDaoFactory daoFactory) {
				start.value0=System.currentTimeMillis();
				PersonDaoImpl dao = daoFactory.getPersonDao();
				for (int i = 0; i < COUNTER; i++) {
					Person bean = new Person();
					bean.name = "name" + i;
					bean.surname = "surname" + i;
					// Object bean = new
					dao.insert(bean);
				}

				end.value0=System.currentTimeMillis();
				return TransactionResult.COMMIT;
			}
		});
		
		
		
		//3100
		System.out.println("Esecuzione terminata in "+(end.value0-start.value0)+" ms");

	}
}
