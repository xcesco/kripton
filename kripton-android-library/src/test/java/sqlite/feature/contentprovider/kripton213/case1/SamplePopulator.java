package sqlite.feature.contentprovider.kripton213.case1;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.SQLitePopulator;

import android.database.sqlite.SQLiteDatabase;

public class SamplePopulator implements SQLitePopulator {

	@Override
	public void execute(SQLiteDatabase database) {
		Logger.info("execute populator");
		
	}

}

