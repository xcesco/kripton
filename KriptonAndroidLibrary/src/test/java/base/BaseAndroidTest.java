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
public class BaseAndroidTest {
	
	protected boolean display;

	@Before
	public void setup()
	{
		display=true;
		ShadowLog.stream = System.out;
		KriptonLibrary.init(RuntimeEnvironment.application);
	}

}
