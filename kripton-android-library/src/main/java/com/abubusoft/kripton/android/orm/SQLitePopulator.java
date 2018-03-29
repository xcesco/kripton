package com.abubusoft.kripton.android.orm;

/**
 * This simple interface is needed to implements populators executed after
 * database creation.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public interface SQLitePopulator {

	void execute();
}
