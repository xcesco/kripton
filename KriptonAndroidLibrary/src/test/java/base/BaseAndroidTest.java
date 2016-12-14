package base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import com.abubusoft.kripton.android.KriptonLibrary;

@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public abstract class BaseAndroidTest {
	
	private static final String KRIPTON_TEST_DEBUG = "KRIPTON_TEST_DEBUG";
	
	protected boolean display;

	@Before
	public void setup()
	{
		final String value = System.getenv(KRIPTON_TEST_DEBUG);		
		if ("true".equals(value))
		{
			display = true;
		}
				
		ShadowLog.stream = System.out;
		KriptonLibrary.init(RuntimeEnvironment.application);
	}

}
