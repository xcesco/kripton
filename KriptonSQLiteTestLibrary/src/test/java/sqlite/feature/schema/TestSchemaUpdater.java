package sqlite.feature.schema;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTestDatabase;

import android.database.sqlite.SQLiteDatabase;
import base.BaseAndroidTest;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestSchemaUpdater extends BaseAndroidTest {

	@Test
	public void testUpdateWithHelper() {
		SQLiteUpdateTestDatabase database = SQLiteUpdateTestDatabase.builder(1, "schemas/school_schema_1.sql")

				.addVersionUpdateTask(new SQLiteUpdateTask(2) {

					@Override
					public void execute(SQLiteDatabase database) {
						SQLiteUpdateTaskHelper.addPrefixToTables(database, "tmp_");
						SQLiteUpdateTaskHelper.executeSQLFromFile(database, "schemas/school_schema_2.sql");
						SQLiteUpdateTaskHelper.dropTablesWithPrefix(database, "tmp_");

					}
				}).build();
		database.create();
		database.updateAndVerify(2, "schemas/school_schema_2.sql");

		log("finish");

	}

	@Test
	public void testUpdateWithFile() {
		SQLiteUpdateTestDatabase database = SQLiteUpdateTestDatabase.builder(1, "schemas/school_schema_1.sql")
				.addVersionUpdateTask(2, "schemas/school_update_1_2.sql").build();

		database.create();
		database.updateAndVerify(2, "schemas/school_schema_2.sql");

		log("finish");

	}
}
