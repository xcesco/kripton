/**
 * 
 */
package kripton22;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.database.QueryListener;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;

import android.database.sqlite.SQLiteDatabase;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/test/resources/AndroidManifest.xml", emulateSdk = 21, reportSdk = 21)
public class SQLTest extends RoboBaseTest {

	@Test
	public void testSelect() 
	{
		ArrayList<ChatMessage> list = helper.selectAll(database, ChatMessage.class);
		
		for (ChatMessage item: list)
		{
			logger.info(item.toString());
		}
	}
	
	@Test
	public void testSelectOrder01() 
	{
		helper.selectOrder01(database, ChatMessage.class, new QueryListener<ChatMessage>() {

			@Override
			public void onRow(int count, ChatMessage bean) {
				logger.info(bean.toString());
			}
		});
		
	}
	
	@Test
	public void testSelectWhere01() 
	{
		Param02 p=new Param02();
		p.latitude=40f;
		
		helper.selectWhere01(database, ChatMessage.class,p, new QueryListener<ChatMessage>() {

			@Override
			public void onRow(int count, ChatMessage bean) {
				logger.info(bean.toString());
			}
		});
	}
	
	
	
	@Test
	public void testInsert() throws MappingException, WriterException {
		ChatMessage bean=new ChatMessage();
		bean.latitude=33;
		bean.rawValue="hello!".getBytes();
		
		helper.insert(database, bean);
		
		logger.info("New id ["+bean.id+"]");
		
		BinderWriter writer = BinderFactory.getXMLWriter(BinderOptions.build().indent(true));
		logger.info(writer.write(bean));
	}
	
	@Test
	public void testUpdate() throws MappingException, WriterException {
		ChatMessage bean=new ChatMessage();
		bean.latitude=33;
		bean.rawValue="hello!".getBytes();
		
		helper.insert(database, bean);
		
		bean.latitude=77;
		helper.update(database, bean);
		
		logger.info("New id ["+bean.id+"]");
		
		BinderWriter writer = BinderFactory.getXMLWriter(BinderOptions.build().indent(true));
		logger.info(writer.write(bean));
	}

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

	/*
	 * @Test public void testStart() throws URISyntaxException { ChatMessage
	 * msg=new ChatMessage(); msg.type=ChatMessageType.SAY; msg.groupUid="---";
	 * msg.uid=UIDHelper.generateUID(System.currentTimeMillis(), false);
	 * msg.senderUid="---";
	 * 
	 * ChatMessageTable table=new ChatMessageTable();
	 * 
	 * ArgonSQLiteHelper helper = new ArgonSQLiteHelper(context, "test.db",1);
	 * helper.insert(database, table, msg);
	 * 
	 * msg.value="ciao bello";
	 * 
	 * helper.updateById(database, table, msg);
	 * 
	 * logger.info("id %s", msg.getId()); }
	 */

	/*
	 * @Test public void testSelect() throws URISyntaxException {
	 * 
	 * ChatMessageTable table=new ChatMessageTable();
	 * 
	 * ArgonSQLiteHelper helper = new ArgonSQLiteHelper(context,"whisper.db",
	 * 1); Cursor cursor=helper.select(database,table,
	 * SQLSelectOptions.build().orderBy(table.UID));
	 * 
	 * // looping through all rows and adding to list if (cursor.moveToFirst())
	 * { do { ElioLogger.info("_id [%3s] [%s]", cursor.getString(0),
	 * cursor.getString(2)); } while (cursor.moveToNext()); } else {
	 * ElioLogger.info("Non trovato"); }
	 * 
	 * }
	 * 
	 * @Test public void testSelectListener() throws URISyntaxException {
	 * 
	 * ChatMessageTable table=new ChatMessageTable();
	 * 
	 * ArgonSQLiteHelper helper = new ArgonSQLiteHelper(context, "whisper.db",
	 * 1); helper.select(database,table,
	 * SQLSelectOptions.build().orderBy(ChatMessageTable.UID), new
	 * ArgonSQLReadEntityListener<ChatMessage>() {
	 * 
	 * @Override public void onRead(ChatMessage item) {
	 * ElioLogger.info("%3s %s", item.getId(), item.uid); }
	 * 
	 * }); }
	 * 
	 * @Test public void testSelectRawData() throws URISyntaxException,
	 * WriterException, MappingException, ReaderException {
	 * 
	 * ChatMessage messageOriginal=new ChatMessage();
	 * messageOriginal.rawValue="funziona!!! ma che bello non avrei mai creduto"
	 * .getBytes(); messageOriginal.uid=UIDHelper.generateLowPriorityUID();
	 * messageOriginal.groupUid="---";
	 * messageOriginal.senderUid=UIDHelper.generateLowPriorityUID();
	 * messageOriginal.type=ChatMessageType.SAY;
	 * 
	 * BinderWriter writer=BinderFactory.getJSONWriter(); BinderReader
	 * reader=BinderFactory.getJSONReader(); String
	 * buffer=writer.write(messageOriginal);
	 * ElioLogger.info("Write: %s",buffer);
	 * 
	 * ChatMessage message=reader.read(ChatMessage.class, buffer);
	 * ChatMessageTable table=new ChatMessageTable();
	 * 
	 * ArgonSQLiteHelper helper = new ArgonSQLiteHelper(context, "whisper.db",
	 * 1);
	 * 
	 * helper.insert(database, table, message); helper.select(database,table,
	 * SQLSelectOptions.build().orderBy(ChatMessageTable.UID), new
	 * ArgonSQLReadEntityListener<ChatMessage>() {
	 * 
	 * @Override public void onRead(ChatMessage item) {
	 * ElioLogger.info("%3s %s %s", item.getId(), item.uid, item.rawValue); }
	 * 
	 * }); }
	 * 
	 * @Test public void testInsertUpdateDelete() throws URISyntaxException {
	 * ChatMessage msg=new ChatMessage(); msg.type=ChatMessageType.SAY;
	 * msg.groupUid="---";
	 * msg.uid=UIDHelper.generateUID(System.currentTimeMillis());
	 * msg.senderUid="---";
	 * 
	 * ChatMessageTable table=new ChatMessageTable();
	 * 
	 * ArgonSQLiteHelper helper = new ArgonSQLiteHelper(context,
	 * "whisper.db",1);
	 * 
	 * org.junit.Assert.assertTrue("inserimento fallito",
	 * helper.insert(database, table, msg));
	 * Assert.assertTrue("creazione id fallito", msg.getId()!=-1);
	 * 
	 * msg.value="ciao bello";
	 * 
	 * Assert.assertTrue("update fallito", helper.updateById(database, table,
	 * msg)); Assert.assertTrue("delete fallito", helper.deleteById(database,
	 * table, msg));
	 * 
	 * ElioLogger.info("Fatto %s", msg.getId()); }
	 */
}
