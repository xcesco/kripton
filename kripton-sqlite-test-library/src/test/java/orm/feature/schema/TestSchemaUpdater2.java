package orm.feature.schema;

import java.io.FileInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.orm.DataSourceOptions;
import com.abubusoft.kripton.android.orm.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteSchemaVerifierHelper;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTestHelper;

import android.database.sqlite.SQLiteDatabase;
import base.BaseAndroidTest;
import orm.feature.schema.version2.BindSchoolDataSource;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestSchemaUpdater2 extends BaseAndroidTest {

	/**
	 * 
	 * Destroy and recreate everything
	 * 
	 */
	@Test
	public void testStandardUpdate()  throws Exception {
		SQLiteUpdateTestHelper.resetInstance(BindSchoolDataSource.class);
		
		BindSchoolDataSource.build(DataSourceOptions.builder().addUpdateTask(3, new FileInputStream("schemas/school_update_2_3.sql")).build());
		BindSchoolDataSource dataSource=BindSchoolDataSource.open();
		
		SQLiteSchemaVerifierHelper.forceSchemaUpdate(dataSource, 3);		
		dataSource=BindSchoolDataSource.open();
		
		Logger.info("finish");
	}

	
	/**
	 * 
	 * Destroy and recreate everything
	 * 
	 */
	@Test
	public void testCustomUpdateTwiceStep() throws Exception {	
		SQLiteUpdateTestHelper.resetInstance(BindSchoolDataSource.class);
		BindSchoolDataSource.build(DataSourceOptions.builder().addUpdateTask(3, new FileInputStream("schemas/school_update_2_3.sql"))
				.addUpdateTask(4, new SQLiteUpdateTask() {
					
					@Override
					public void execute(SQLiteDatabase database) {
						Logger.info("just in case, 3 and 4 are the same!");			
						
					}
				})
				.build());
		
		SQLiteSchemaVerifierHelper.forceSchemaUpdate(BindSchoolDataSource.instance(), 4);		
		
		Logger.info("finish");
	}


}
