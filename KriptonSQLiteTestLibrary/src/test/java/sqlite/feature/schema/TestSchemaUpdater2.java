package sqlite.feature.schema;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTestHelper;

import android.database.sqlite.SQLiteDatabase;
import base.BaseAndroidTest;
import sqlite.feature.schema.version2.BindSchoolDataSource;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestSchemaUpdater2 extends BaseAndroidTest {

	/**
	 * 
	 * Destroy and recreate everything
	 * 
	 */
	@Test
	public void testStandardUpdate() {
		
		BindSchoolDataSource dataSource=BindSchoolDataSource.open();
		
		//SQLiteUpdateTaskHelper.clearDatabase(BindSchoolDataSource.instance());
		
		SQLiteUpdateTaskHelper.forceSchemaUpdate(dataSource, 3);		
		dataSource=BindSchoolDataSource.open();
		
		Logger.info("finish");
	}
	
	/**
	 * 
	 * Destroy and recreate everything
	 * 
	 */
	@Test
	public void testCustomUpdateSingleStep() {					
		BindSchoolDataSource.build(DataSourceOptions.builder().addUpdateTask(3, "schemas/school_update_2_3.sql").build());
		//SQLiteUpdateTaskHelper.clearDatabase(BindSchoolDataSource.instance());
		
		BindSchoolDataSource dataSource=BindSchoolDataSource.open();				
		SQLiteUpdateTaskHelper.forceSchemaUpdate(dataSource, 3);
		
		dataSource=BindSchoolDataSource.open();					
		SQLiteUpdateTaskHelper.verifySchema(dataSource.database(), "schemas/school_schema_2.sql");
		
		Logger.info("finish");
	}
	
	/**
	 * 
	 * Destroy and recreate everything
	 * 
	 */
	@Test
	public void testCustomUpdateTwiceStep() {	
		
		BindSchoolDataSource.build(DataSourceOptions.builder().addUpdateTask(3, "schemas/school_update_2_3.sql")
				.addUpdateTask(new SQLiteUpdateTask(4) {
					
					@Override
					public void execute(SQLiteDatabase database) {
						Logger.info("just in case, 3 and 4 are the same!");
						
					}
				})
				.build());
		
		//SQLiteUpdateTaskHelper.clearDatabase(BindSchoolDataSource.instance());
		BindSchoolDataSource dataSource=BindSchoolDataSource.open();
							
		SQLiteUpdateTaskHelper.forceSchemaUpdate(dataSource, 4);		
		dataSource=BindSchoolDataSource.open();		
		
		Logger.info("finish");
	}


}
