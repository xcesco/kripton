/**
 * 
 */
package kripton22;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowContentResolver;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;

/**
 * @author Francesco Benincasa
 * 
 */
public class RoboBaseTest {
	
	public RoboBaseTest()
	{
		
	}
	
	protected Logger logger = Logger.getAnonymousLogger();

	protected ContentResolver mContentResolver;
	protected ShadowContentResolver mShadowContentResolver;
	protected AssetManager assetManager;

	protected Context context;

	@Before
	public void before() {
		logger.info("Inizio esecuzione");

		context = RuntimeEnvironment.application;
		assetManager =  RuntimeEnvironment.application.getAssets();
		
		mContentResolver = RuntimeEnvironment.application.getContentResolver();		
	}

	@After
	public void after() {
		logger.info("Fine esecuzione");
	}

}
