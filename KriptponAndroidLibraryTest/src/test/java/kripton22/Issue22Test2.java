package kripton22;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Logger;

import kripton22.BeanTest1_0.EnumValue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.DatabaseSchemaFactory;
import com.abubusoft.kripton.DatabaseSchemaOptions;
import com.abubusoft.kripton.android.OnRowListener;
import com.abubusoft.kripton.android.QueryForeignKey;
import com.abubusoft.kripton.android.SQLiteDelete;
import com.abubusoft.kripton.android.SQLiteInsert;
import com.abubusoft.kripton.android.SQLiteQuery;
import com.abubusoft.kripton.android.SQLiteSchema;
import com.abubusoft.kripton.android.SQLiteUpdate;
import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.binder.database.NameConverterType;
import com.abubusoft.kripton.binder.database.Query;

/**
 * Test for foreign key
 * 
 * @author xcesco
 *
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/test/resources/AndroidManifest.xml", emulateSdk = 21, reportSdk = 21)
public class Issue22Test2 {

	protected Logger logger = Logger.getAnonymousLogger();

	private static final String DB_PATH = "src/test/resources/database/" + "test02.db";

	private SQLiteDatabase database;

	private SQLiteSchema databaseSchema;

	// This will contain the absolute file path to the database
	// private String dbPath;

	public void checkDb() {
		File file = new File(DB_PATH);
		boolean mustCreate = false;
		if (!file.exists()) {
			mustCreate = true;
		}

		database = SQLiteDatabase.openDatabase((new File(DB_PATH)).getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);

		if (mustCreate) {
			logger.info("Create db in " + (new File(DB_PATH)).getAbsolutePath());
			ArrayList<String> sql = databaseSchema.createTablesSQL();

			for (int i = 0; i < sql.size(); i++) {
				String temp = sql.get(i);
				logger.info("DDL: " + temp);
				database.execSQL(temp);
			}
		}
	}

	@Before
	public void before() {
		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.nameConverter(NameConverterType.UPPER_UNDERSCORE);
		options.tablePrefix("TD_");
		options.add(BeanTest2_Detail.class);
		options.add(BeanTest2_Master.class);

		String databaseName = "kripton22_test02";
		databaseSchema = DatabaseSchemaFactory.create(databaseName, SQLiteSchema.class, options);

		checkDb();
	}

	@Test
	public void test01() throws MalformedURLException {
		BeanTest2_Master bean0 = new BeanTest2_Master();
		bean0.date = new Date();
		bean0.label = "Master label";

		BeanTest2_Detail bean1 = new BeanTest2_Detail();
		bean1.master = bean0;
		bean1.description = "labelbale";
		bean1.url = new URL("http://www.google.it");
		bean1.value = BigDecimal.valueOf(1201);

		SQLiteInsert insertMaster = databaseSchema.getInsert(BeanTest2_Master.class);
		insertMaster.execute(database, bean0);
		logger.info(insertMaster.getSQL());

		SQLiteInsert insertDetail = databaseSchema.getInsert(BeanTest2_Detail.class);
		insertDetail.execute(database, bean1);
		logger.info(insertDetail.getSQL());

		SQLiteQuery<BeanTest2_Detail> queryAll = databaseSchema.getQuery(BeanTest2_Detail.class, Query.QUERY_ALL);
		queryAll.setListener(new OnRowListener<BeanTest2_Detail>() {

			@Override
			public void onRow(Cursor cursor, int rowIndex, BeanTest2_Detail bean, ArrayList<QueryForeignKey> foreignKeys) {
				logger.info("CONTO -- " + rowIndex + " ---- " + bean.toString()+"    "+foreignKeys.get(0).v2.name +" "+cursor.getLong(foreignKeys.get(0).v1));

			}

		});
		queryAll.executeList(database);

		/*
		 * SQLiteQuery<BeanTest1_0> query =
		 * databaseSchema.getQuery(BeanTest1_0.class, Query.DEFAULT_BY_ID);
		 * logger.info(query.getSQL());
		 * 
		 * BeanTest1_0 bean1=query.executeOne(database, bean0.id); int
		 * count=query.executeCount(database, BeanTest1_0.class, bean0.id);
		 * Assert.assertEquals(count, 1);
		 * 
		 * assertReflectionEquals(bean0, bean1);
		 * 
		 * SQLiteUpdate update=databaseSchema.getUpdate(BeanTest1_0.class);
		 * 
		 * bean0.longNumber=34; int updateCount=update.execute(database, bean0);
		 * Assert.assertEquals(updateCount, 1);
		 * 
		 * SQLiteDelete delete=databaseSchema.getDelete(BeanTest1_0.class);
		 * 
		 * Assert.assertEquals(insertMaster.execute(database, bean1), true);
		 * Assert.assertEquals(delete.execute(database, bean1),1);
		 * 
		 * logger.info(bean0.toString());
		 */

	}
}
