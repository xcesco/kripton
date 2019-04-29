/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
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
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.content.Context;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseAndroidTest.
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public abstract class BaseAndroidTest {

	/** The Constant KRIPTON_DEBUG_MODE. */
	private static final String KRIPTON_DEBUG_MODE = "kripton.debug";

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		//final String value = System.getProperty(KRIPTON_DEBUG_MODE);
		final String value = System.getProperty(KRIPTON_DEBUG_MODE);
		if ("false".equals(value)) {
			ShadowLog.stream = new PrintStream(new NullOutputStream());
			// we are in test, but we don't see log on System.out
			System.setOut(new PrintStream(new NullOutputStream()));
			System.setErr(new PrintStream(new NullOutputStream()));
		} else {
			ShadowLog.stream = System.out;
		}
				
		

		KriptonLibrary.init(RuntimeEnvironment.application);
	}
	
	/**
	 * Gets the application context.
	 *
	 * @return the application context
	 */
	protected Context getApplicationContext() {
		return RuntimeEnvironment.application;
	}

	/** The expected ex. */
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	/**
	 * Expected kripton runtime exception with cause.
	 *
	 * @param <E> the element type
	 * @param clazzException the clazz exception
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	public <E> void expectedKriptonRuntimeExceptionWithCause(Class<E> clazzException) throws InstantiationException, IllegalAccessException {
		expectedEx.expect(KriptonRuntimeException.class);
		expectedEx.expectMessage(clazzException.getSimpleName());
	}

	/**
	 * Expected exception.
	 *
	 * @param <E> the element type
	 * @param clazzException the clazz exception
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	public <E> void expectedException(Class<? extends Throwable> clazzException) throws InstantiationException, IllegalAccessException {
		expectedEx.expect(clazzException);
	}
	
	/**
	 * Log.
	 *
	 * @param format the format
	 * @param objects the objects
	 */
	public void log(String format, Object ...objects)
	{
		Logger.info(String.format(format, objects));		
	}

}
