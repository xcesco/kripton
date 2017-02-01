package base;

import java.io.PrintStream;

import org.apache.commons.io.output.NullOutputStream;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public abstract class BaseAndroidTest {
			
	private static final String KRIPTON_DEBUG_MODE = "KRIPTON_DEBUG_MODE";
	
	@Before
	public void setup()
	{
		final String value = System.getenv(KRIPTON_DEBUG_MODE);
		if ("true".equals(value)) {
			ShadowLog.stream = System.out;
		} else {
			ShadowLog.stream=new PrintStream(new NullOutputStream());
			// we are in test, but we don't see log on System.out
			System.setOut(new PrintStream(new NullOutputStream()));
			System.setErr(new PrintStream(new NullOutputStream()));
		}
		
		KriptonLibrary.init(RuntimeEnvironment.application);
	}
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	public <E> void expectedException(Class<E> clazzException) throws InstantiationException, IllegalAccessException {
		expectedEx.expect(KriptonRuntimeException.class);
		expectedEx.expectMessage(clazzException.getSimpleName());
	}

}
