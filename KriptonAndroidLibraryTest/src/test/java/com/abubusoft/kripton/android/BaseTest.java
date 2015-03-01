/**
 * 
 */
package com.abubusoft.kripton.android;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowContentResolver;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;

/**
 * @author Francesco Benincasa
 * 
 */
public class BaseTest {

	protected ContentResolver mContentResolver;
	protected ShadowContentResolver mShadowContentResolver;
	protected AssetManager assetManager;
	//protected WhisperDataSource dataBaseProvider;
	
	protected Logger logger=Logger.getAnonymousLogger();

	protected Context context;

	@Before
	public void before() {
		logger.info("Inizio esecuzione");

		context = Robolectric.application;
		assetManager = Robolectric.application.getAssets();
		
		mContentResolver = Robolectric.application.getContentResolver();
		/*mShadowContentResolver = Robolectric.shadowOf(mContentResolver);
		
		dataBaseProvider = new WhisperDataSource(context);
		dataBaseProvider.o
		
		ShadowContentResolver.registerProvider(DataBaseProvider.AUTHORITY, dataBaseProvider);*/
	}

	@After
	public void after() {
		logger.info("Fine esecuzione");
	}

	public enum Indent {
		TABLE("<table border='1'>", "</table>", "<tr>", "</tr>", "<td>", "</td>", "<b>", "</b>"), NONE("", "", "", "", " ", " ", "", "");

		public String tableB;
		public String tableE;
		public String boldE;
		public String boldB;
		public String colE;
		public String rowE;
		public String rowB;
		public String colB;

		Indent(String tableB, String tableE, String rowB, String rowE, String colB, String colE, String boldB, String boldE) {
			this.tableB = tableB;
			this.tableE = tableE;
			this.rowB = rowB;
			this.rowE = rowE;
			this.colB = colB;
			this.colE = colE;
			this.boldB = boldB;
			this.boldE = boldE;
		}

	}

}
