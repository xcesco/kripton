package kripton22;

import java.io.File;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.SQLiteInsert;
import com.abubusoft.kripton.android.SQLiteQuery;
import com.abubusoft.kripton.android.SQLiteSchema;
import com.abubusoft.kripton.android.SQLiteUpdate;
import com.abubusoft.kripton.database.DatabaseSchemaFactory;
import com.abubusoft.kripton.database.DatabaseSchemaOptions;
import com.abubusoft.kripton.database.NameConverterType;
import com.abubusoft.kripton.database.Query;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/test/resources/AndroidManifest.xml", emulateSdk = 21, reportSdk = 21)
public class Issue22Test1 {
	
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
	//	options.add(Bean01.class);
		options.add(ChatMessage.class);

		String databaseName="kripton22";
		databaseSchema = DatabaseSchemaFactory.create(databaseName, SQLiteSchema.class, options);
		
		checkDb();
	}

	@Test
	public void test01()
	{
		ChatMessage msg=new ChatMessage();
		msg.latitude=2;
		msg.groupUid="xxx";
		msg.mediaDuration=224;
		
		SQLiteInsert insert=databaseSchema.getInsert(ChatMessage.class);
		insert.execute(database, msg);
		logger.info(insert.getSQL());		
		
		SQLiteQuery query = databaseSchema.getQuery(ChatMessage.class, Query.DEFAULT_BY_ID);
		logger.info(query.getSQL());
		msg=query.executeOne(database, ChatMessage.class, msg.id);		
		
		SQLiteUpdate update = databaseSchema.getUpdate(ChatMessage.class);
		msg.mediaUrl="ciao bello!";
		int n=update.execute(database, msg, msg.id);
		logger.info("Aggiornati "+n);
		
		logger.info(msg.toString());
		msg=query.executeOne(database, ChatMessage.class, msg.id);
		logger.info(msg.toString());
		
	}
}
