package sqlite.feature.schema;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteSchemaVerifierHelper;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTestDatabase;

import android.database.sqlite.SQLiteDatabase;
import base.BaseAndroidTest;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestSchemaUpdater extends BaseAndroidTest {

	@Test
	public void testUpdateWithHelper() throws FileNotFoundException {
		final FileInputStream stream = new FileInputStream("schemas/school_schema_2.sql");

		SQLiteUpdateTestDatabase database = SQLiteUpdateTestDatabase.builder(1, new FileInputStream("schemas/school_schema_1.sql"))

				.addVersionUpdateTask(new SQLiteUpdateTask(2) {

					@Override
					public void execute(SQLiteDatabase database) {
						SQLiteSchemaVerifierHelper.renameAllTablesWithPrefix(database, "tmp_");
						SQLiteSchemaVerifierHelper.executeSQL(database, stream);
						SQLiteSchemaVerifierHelper.dropTablesWithPrefix(database, "tmp_");

					}
				}).build();

		database.updateAndVerify(2, new FileInputStream("schemas/school_schema_2.sql"));

		log("finish");
	}

	@Test
	public void testUpdateWithFile() throws FileNotFoundException {
		SQLiteUpdateTestDatabase database = SQLiteUpdateTestDatabase.builder(1, new FileInputStream("schemas/school_schema_1.sql"))
				.addVersionUpdateTask(2, new FileInputStream("schemas/school_update_1_2.sql")).build();

		database.updateAndVerify(2, new FileInputStream("schemas/school_schema_2.sql"));

		log("finish");

	}
}
