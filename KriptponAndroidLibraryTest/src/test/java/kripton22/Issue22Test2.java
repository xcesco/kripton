package kripton22;


import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
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

import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.DatabaseSchemaFactory;
import com.abubusoft.kripton.DatabaseSchemaOptions;
import com.abubusoft.kripton.android.SQLiteDelete;
import com.abubusoft.kripton.android.SQLiteInsert;
import com.abubusoft.kripton.android.SQLiteQuery;
import com.abubusoft.kripton.android.SQLiteSchema;
import com.abubusoft.kripton.android.SQLiteUpdate;
import com.abubusoft.kripton.binder.database.NameConverterType;
import com.abubusoft.kripton.binder.database.Query;

/**
 * Test for foreign key
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
			String[] sql=databaseSchema.createTablesSQL();
			
			for (int i=0;i <sql.length;i++)
			{
				String temp=sql[i];
				database.execSQL(temp);	
			}
		}
	}
	
	@Before
	public void before()
	{
		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.nameConverter(NameConverterType.UPPER_UNDERSCORE);
		options.tablePrefix("TD_");
		options.add(BeanTest2_Master.class);
		options.add(BeanTest2_Detail.class);

		String databaseName="kripton22_test02";
		databaseSchema = DatabaseSchemaFactory.create(databaseName, SQLiteSchema.class, options);
		
		checkDb();
	}

	@Test
	public void test01() throws MalformedURLException
	{
		BeanTest1_0 bean0=new BeanTest1_0();
		bean0.doubleNumber=1.1;
		bean0.doubleNumber2=2.1;
		bean0.intNumber=10;
		bean0.intNumber2=11;
		bean0.enumValue=EnumValue.TYPE0;
		bean0.longNumber=33;
		bean0.longNumber2=12L;
		bean0.shortNumber=1;
		bean0.shortNumber2=2;
		bean0.charNumber='a';
		bean0.charNumber2='b';		
		bean0.dateValue=new Date();
		bean0.timeValue=new Time(bean0.dateValue.getTime());
		bean0.localeValue=Locale.CHINA;
		bean0.byteValue=4;
		bean0.byteValue2=8;
		bean0.floatValue=24.f;
		bean0.floatValue2=234f;
		bean0.description="this is a description";
		bean0.boolValue=true;
		bean0.boolValue2=true;
		
		bean0.content="this is a content".getBytes();
		bean0.url=new URL("http://www.google.it");
		
		bean0.bigIntegerValue=BigInteger.valueOf(12);
		bean0.bigDecimalValue=BigDecimal.valueOf(123);
		bean0.calendarValue=Calendar.getInstance();
		bean0.currencyValue=Currency.getInstance(Locale.CHINA);
		bean0.timeZoneValue=TimeZone.getDefault();
		
		/*
		SQLiteInsert insert=databaseSchema.getInsert(BeanTest_0.class);
		insert.execute(database, bean0);
		logger.info(insert.getSQL());		
		
		SQLiteQuery query = databaseSchema.getQuery(BeanTest1_0.class, Query.DEFAULT_BY_ID);
		logger.info(query.getSQL());
		
		BeanTest1_0 bean1=query.executeOne(database, BeanTest1_0.class, bean0.id);
		int count=query.executeCount(database, BeanTest1_0.class, bean0.id);
		Assert.assertEquals(count, 1);
		
		assertReflectionEquals(bean0, bean1);
				
		SQLiteUpdate update=databaseSchema.getUpdate(BeanTest1_0.class);
		
		bean0.longNumber=34;
		int updateCount=update.execute(database, bean0);
		Assert.assertEquals(updateCount, 1);
		
		SQLiteDelete delete=databaseSchema.getDelete(BeanTest1_0.class);
		
		Assert.assertEquals(insert.execute(database, bean1), true);		
		Assert.assertEquals(delete.execute(database, bean1),1);
		
		logger.info(bean0.toString());*/
		
	}
}
