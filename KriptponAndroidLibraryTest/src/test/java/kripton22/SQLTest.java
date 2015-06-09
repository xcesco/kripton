/**
 * 
 */
package kripton22;

import java.io.File;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/test/resources/AndroidManifest.xml", emulateSdk = 22, reportSdk = 22)
public class SQLTest extends RoboBaseTest {
/*
	@Test
	public void testSelect() {
		ArrayList<ChatMessage> list = helper.selectAll(database, ChatMessage.class);

		for (ChatMessage item : list) {
			logger.info(item.toString());
		}
	}

	@Test
	public void testSelectOrder01() {
		helper.selectOrder01(database, ChatMessage.class, new QueryListener<ChatMessage>() {

			@Override
			public void onRow(int count, ChatMessage bean) {
				logger.info(bean.toString());
			}
		});

	}

	@Test
	public void testSelectWhere01() {
		Param02 p = new Param02();
		p.latitude = 40f;

		helper.selectWhere01(database, ChatMessage.class, p, new QueryListener<ChatMessage>() {

			@Override
			public void onRow(int count, ChatMessage bean) {
				logger.info(bean.toString());
			}
		});
	}

	@Test
	public void testInsert() throws MappingException, WriterException {
		ChatMessage bean = new ChatMessage();
		bean.latitude = 33;
		bean.rawValue = "hello!".getBytes();

		helper.insert(database, bean);

		logger.info("New id [" + bean.id + "]");

		BinderWriter writer = BinderFactory.getXMLWriter(BinderOptions.build().indent(true));
		logger.info(writer.write(bean));
	}
*/
	/*
	@Test
	public void testUpdate() throws MappingException, WriterException {
		Bean0 bean = new Bean0();
		bean.latitude = 33;
		bean.rawValue = "hello!".getBytes();

		helper.insert(database, bean);

		bean.latitude = 77;
		helper.update(database, bean);

		logger.info("New id [" + bean.id + "]");

		bean = helper.select(database, Bean0.class, bean.id);

		BinderWriter writer = BinderFactory.getXMLWriter(BinderOptions.build().indent(true));
		logger.info(writer.write(bean));
				
	}*/

	protected Logger logger = Logger.getAnonymousLogger();

	@Before
	public void before() {
		logger.info("Begin test");
		checkDb();
	}

	@After
	public void after() {
		logger.info("End test");
	}

	private static final String DB_PATH = "src/test/resources/database/" + "test.db";

	private SQLiteDatabase database;

	private ArgonSQLiteHelper helper;

	// This will contain the absolute file path to the database
	// private String dbPath;

	public void checkDb() {
		File file = new File(DB_PATH);
		boolean mustCreate = false;
		if (!file.exists()) {
			mustCreate = true;
		}

		database = SQLiteDatabase.openDatabase((new File(DB_PATH)).getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
		helper = new ArgonSQLiteHelper(context, "test", 1);

		if (mustCreate) {
			logger.info("Create db in " + (new File(DB_PATH)).getAbsolutePath());
			helper.onCreate(database);
		}
	}
	
}
