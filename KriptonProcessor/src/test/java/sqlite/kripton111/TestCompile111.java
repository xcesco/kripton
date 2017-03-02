package sqlite.kripton111;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;
import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.kripton111.model.ActionType;
import sqlite.kripton111.model.Country;
import sqlite.kripton111.model.PhoneNumber;
import sqlite.kripton111.model.PrefixConfig;
import sqlite.kripton111.persistence.AbstractDao;
import sqlite.kripton111.persistence.CountryDao;
import sqlite.kripton111.persistence.PhoneDao;
import sqlite.kripton111.persistence.PrefixConfigDao;
import sqlite.kripton111.persistence.XenoDataSource;

public class TestCompile111 extends AbstractBindSQLiteProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(ActionType.class, Country.class, PhoneNumber.class, PrefixConfig.class, AbstractDao.class, CountryDao.class, PhoneDao.class, PrefixConfigDao.class,
				XenoDataSource.class);
	}

}
