package orm.feature.schema;

import java.io.FileInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.orm.DataSourceOptions;
import com.abubusoft.kripton.android.orm.SQLiteSchemaVerifierHelper;
import com.abubusoft.kripton.android.orm.SQLiteUpdateTestHelper;

import base.BaseAndroidTest;
import orm.feature.schema.version2.BindSchoolDataSource;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestSchemaUpdater3 extends BaseAndroidTest {

	/**
	 * 
	 * Destroy and recreate everything
	 * 
	 */
	@Test
	public void testCustomUpdateSingleStep() throws Exception {
		SQLiteUpdateTestHelper.resetInstance(BindSchoolDataSource.class);
		BindSchoolDataSource.build(DataSourceOptions.builder().addUpdateTask(3, new FileInputStream("schemas/school_update_2_3.sql")).build());

		SQLiteSchemaVerifierHelper.forceSchemaUpdate(BindSchoolDataSource.instance(), 3);
		SQLiteSchemaVerifierHelper.verifySchema(BindSchoolDataSource.instance(), new FileInputStream("schemas/school_schema_2.sql"));

		Logger.info("finish");
	}

}
