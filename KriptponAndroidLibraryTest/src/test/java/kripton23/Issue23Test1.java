package kripton23;


import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.DatabaseSchemaFactory;
import com.abubusoft.kripton.DatabaseSchemaOptions;
import com.abubusoft.kripton.android.SQLiteSchema;
import com.abubusoft.kripton.binder.database.NameConverterType;
import com.example01.datamodel.TempiAttesa;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/test/resources/AndroidManifest.xml", emulateSdk = 21, reportSdk = 21)
public class Issue23Test1 {
	
	protected Logger logger = Logger.getAnonymousLogger();
	
	private static final String DB_PATH = "src/test/resources/database/" + "test.db";

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
			ArrayList<String> sql=databaseSchema.createTablesSQL();
			
			for (int i=0;i <sql.size();i++)
			{
				String temp=sql.get(i);
				database.execSQL(temp);	
			}
		}
	}
	
	@Before
	public void before()
	{
		DatabaseSchemaOptions options = DatabaseSchemaOptions.build();
		options.nameConverter(NameConverterType.UPPER_UNDERSCORE);
		options.add(TempiAttesa.class);

		String databaseName="kripton23";
		
		databaseSchema = DatabaseSchemaFactory.create(databaseName, SQLiteSchema.class, options);
		
		ArrayList<String> tables = databaseSchema.createTablesSQL();
		
		for (String items: tables)
		{
			logger.info("Create table  " + items);	
		}
		
		checkDb();
	}

	@Test
	public void test01() throws MalformedURLException
	{
		
		
	}
}
