package sqlite.feature.schema;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.DatabaseVersionUpdateTask;
import com.abubusoft.kripton.android.sqlite.migration.MigrationTestDatabase;
import com.abubusoft.kripton.android.sqlite.migration.MigrationTestHelper;

import android.database.sqlite.SQLiteDatabase;
import base.BaseAndroidTest;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestSchemaMigration extends BaseAndroidTest {

	@Test
	public void testMigration() {
		MigrationTestDatabase database = MigrationTestDatabase.builder(1, "schemas/school.schema.1.sql")

				.addVersionUpdateTask(new DatabaseVersionUpdateTask(1, 2) {

					@Override
					public void execute(SQLiteDatabase database) {
						MigrationTestHelper.renameAllTables(database, "tmp_");
						MigrationTestHelper.dropAllIndex(database);
						MigrationTestHelper.executeSQLFromFile(database, "schemas/school.schema.2.sql");
						MigrationTestHelper.dropAllTables(database, "tmp_");

					}
				}).build();
		// database.addVersionUpdateTask(new
		// DatabaseVersionUpdateFromFileTask(1,
		// 2,"schemas/school.update.1.2.sql"));

		database.create();
		database.updateAndVerify(2, "schemas/school.schema.2.sql");

		log("finish");

	}
}
