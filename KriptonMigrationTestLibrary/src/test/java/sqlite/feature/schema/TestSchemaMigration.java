package sqlite.feature.schema;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.migration.Migration;
import com.abubusoft.kripton.android.sqlite.migration.MigrationHelper;

import android.database.sqlite.SQLiteDatabase;
import base.BaseAndroidTest;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestSchemaMigration extends BaseAndroidTest {

	@Test
	public void testMigration() {
		SQLiteDatabase database = MigrationHelper.createDatabase(1, "schemas/school.schema.1.sql");						
		database.close();
		
//		database = MigrationHelper.updateDatabase(2, new Migration() {
//			
//		});						
		//database.close();
		
		//SchemaCrawlerUtility.
		
		
	}
}
