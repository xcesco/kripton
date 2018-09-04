/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package base;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.tools.JavaFileObject;

import org.apache.commons.io.output.NullOutputStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.KriptonProcessor;
import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;
import com.google.common.collect.ImmutableList;
import com.google.common.io.ByteStreams;
import com.google.testing.compile.JavaFileObjects;

/**
 * The Class BaseProcessorTest.
 */
public class BaseProcessorTest {

	/** The Constant KRIPTON_DEBUG_MODE. */
	private static final String KRIPTON_DEBUG_MODE = "kripton.debug";

	/** The test type. */
	protected TestType testType = TestType.NONE;

	/**
	 * Log.
	 *
	 * @param message
	 *            the message
	 * @param objects
	 *            the objects
	 */
	public void log(String message, Object... objects) {
		if (objects.length == 0) {
			System.out.println(message);
		} else {
			System.out.println(String.format(message, objects));
		}
	}

	/**
	 * Test each elements of two collections. It does not matter the collections' kind. Elements are compared by reflection.
	 *
	 * @param collection1
	 *            the collection 1
	 * @param collection2
	 *            the collection 2
	 */
	public void checkCollectionExactly(Collection<?> collection1, Collection<?> collection2) {
		assertEquals("collections does not have same size", collection1.size(), collection2.size());

		Iterator<?> i1 = collection1.iterator();
		Iterator<?> i2 = collection2.iterator();

		while (i1.hasNext()) {
			ReflectionAssert.assertReflectionEquals(i2.next(), i1.next(), ReflectionComparatorMode.LENIENT_ORDER);
		}
	}

	/**
	 * Before.
	 */
	@Before
	public void before() {
		String value = System.getProperty(KRIPTON_DEBUG_MODE);
		// value = "true";
		if ("false".equals(value)) {
			// we are in test, but we don't see log on System.out
			System.setOut(new PrintStream(new NullOutputStream()));
			System.setErr(new PrintStream(new NullOutputStream()));
		} else {
			BaseProcessor.DEBUG_MODE = true;
		}

		// when we run junit test, AnnotationProcessor is always in TEST_MODE
		BaseProcessor.JUNIT_TEST_MODE = true;
		System.setProperty("java.util.logging.SimpleFormatter.format",
				"%1$tH:%1$tM:%1$tS.%1$tL %4$-7s [%3$s] (%2$s) %5$s %6$s%n");
	}

	/** The destination path. */
	protected PathSourceType destinationPath;

	/** The expected ex. */
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	/**
	 * Expected exception.
	 *
	 * @param <E>
	 *            the element type
	 * @param clazzException
	 *            the clazz exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	public <E extends KriptonProcessorException> void expectedException(Class<E> clazzException)
			throws InstantiationException, IllegalAccessException {
		expectedEx.expect(AssertionError.class);
		expectedEx.expectMessage(clazzException.getSimpleName());
	}

	/**
	 * Expected exception.
	 *
	 * @param <E>
	 *            the element type
	 * @param clazzException
	 *            the clazz exception
	 * @param containedMessage
	 *            contained message
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	public <E extends KriptonProcessorException> void expectedException(Class<E> clazzException,
			String containedMessage) throws InstantiationException, IllegalAccessException {
		expectedEx.expect(AssertionError.class);
		expectedEx.expectMessage(clazzException.getSimpleName());
		expectedEx.expectMessage(containedMessage);
	}

	/**
	 * The Enum TestType.
	 */
	public enum TestType {

		/** The compare. */
		COMPARE,
		/** The prepare test android library. */
		PREPARE_TEST_ANDROID_LIBRARY,
		/** The prepare test java library. */
		PREPARE_TEST_JAVA_LIBRARY,
		/** The none. */
		NONE;
	}

	/**
	 * The Enum PathSourceType.
	 */
	public enum PathSourceType {

		/** The src test java. */
		SRC_TEST_JAVA("src/test/java/"),
		/** The src test expected. */
		SRC_TEST_EXPECTED("src/test/expected/"),
		/** The target test result. */
		TARGET_TEST_RESULT("target/test/generated/"),
		/** The dest test android library. */
		DEST_TEST_ANDROID_LIBRARY("../kripton-android-library/src/test/java/"),
		/** The dest test java library. */
		DEST_TEST_JAVA_LIBRARY("../kripton/src/test/java/");

		/**
		 * Instantiates a new path source type.
		 *
		 * @param path
		 *            the path
		 */
		private PathSourceType(String path) {
			this.path = path;
		}

		/** The path. */
		private String path;

		/**
		 * Gets the path.
		 *
		 * @return the path
		 */
		public String getPath() {
			return path;
		}

		/**
		 * <p>
		 * Create a file starting with specified folder. Path have to start without "/"
		 *
		 * @param fileNameWithRelativePath
		 *            the file name with relative path
		 * @return the file
		 */
		public File createFile(String fileNameWithRelativePath) {
			if (fileNameWithRelativePath.startsWith("/") || fileNameWithRelativePath.startsWith("\\")) {
				fileNameWithRelativePath = fileNameWithRelativePath.substring(1);
			}
			return new File(this.path + fileNameWithRelativePath);
		}
	};

	/**
	 * Source.
	 *
	 * @param clazz
	 *            the clazz
	 * @return the java file object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected static JavaFileObject source(Class<?> clazz) throws IOException {
		return getSourceFile(PathSourceType.SRC_TEST_JAVA, clazz);
	}

	/**
	 * Sources.
	 *
	 * @param clazzes
	 *            the clazzes
	 * @return the list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected List<JavaFileObject> sources(Class<?>... clazzes) throws IOException {
		List<JavaFileObject> list = new ArrayList<JavaFileObject>();

		for (Class<?> item : clazzes) {
			list.add(source(item));
		}

		return list;
	}

	/**
	 * Write generated file.
	 *
	 * @param basePath
	 *            the base path
	 * @param javaFileObject
	 *            the java file object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected static void writeGeneratedFile(PathSourceType basePath, JavaFileObject javaFileObject)
			throws IOException {
		PathSourceType pathSourceType = basePath;
		Path path = Paths.get(pathSourceType.getPath(), javaFileObject.getName().replace("SOURCE_OUTPUT", ""));

		if (path == null)
			throw new IOException("can not work with " + javaFileObject.getName());

		Path checkPath = Files.createDirectories(path.getParent());
		if (checkPath == null)
			throw new IOException("can not work with " + javaFileObject.getName());

		byte[] bytes = ByteStreams.toByteArray(javaFileObject.openInputStream());

		String pathString = javaFileObject.getName().toString().replace("/SOURCE_OUTPUT", "");
		// if (pathString.startsWith("/")) pathString=pathString.substring(1);
		pathString = basePath.path + pathString;

		pathString = pathString.replaceFirst("//", "/");

		System.out.println("Generate file " + pathString);
		Files.write(path, bytes);
	}

	/**
	 * Compare generated file.
	 *
	 * @param javaFileObject
	 *            the java file object
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected static boolean compareGeneratedFile(JavaFileObject javaFileObject) throws IOException {
		PathSourceType pathSourceType = PathSourceType.SRC_TEST_EXPECTED;
		Path path = Paths.get(pathSourceType.getPath(), javaFileObject.getName().replace("SOURCE_OUTPUT", ""));

		byte[] bytes = ByteStreams.toByteArray(javaFileObject.openInputStream());
		byte[] aspectedBytes = Files.readAllBytes(path);

		return Arrays.equals(bytes, aspectedBytes);
	}

	/**
	 * Gets the string from input stream.
	 *
	 * @param is
	 *            the is
	 * @return the string from input stream
	 */
	protected static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

	/**
	 * Generate an java file object.
	 *
	 * @param pathSourceType
	 *            the path source type
	 * @param clazz
	 *            the clazz
	 * @return the source file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected static JavaFileObject getSourceFile(PathSourceType pathSourceType, Class<?> clazz) throws IOException {
		Path path = Paths.get(pathSourceType.getPath(),
				clazz.getCanonicalName().replace(".", Character.toString(File.separatorChar)) + ".java");
		byte[] buffer = Files.readAllBytes(path.toAbsolutePath());

		JavaFileObject source = JavaFileObjects.forSourceLines(clazz.getCanonicalName(), new String(buffer));
		return source;
	}

	/**
	 * With generated source counter.
	 *
	 * @param realValue
	 *            the real value
	 * @param aspected
	 *            the aspected
	 */
	protected void withGeneratedSourceCounter(long realValue, long aspected) {
		Assert.assertEquals(realValue, aspected);
	}

	/**
	 * Builds the shared preferences processor test.
	 *
	 * @param classesToTest
	 *            the classes to test
	 * @return the long
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected long buildSharedPreferencesProcessorTest(Class<?>... classesToTest)
			throws InstantiationException, IllegalAccessException, IOException {
		return buildTest(KriptonProcessor.class, classesToTest);
	}

	/**
	 * Builds the bind processor test.
	 *
	 * @param classesToTest
	 *            the classes to test
	 * @return the long
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected long buildBindProcessorTest(Class<?>... classesToTest)
			throws InstantiationException, IllegalAccessException, IOException {
		return buildTest(KriptonProcessor.class, classesToTest);
	}

	/**
	 * Builds the data source processor test.
	 *
	 * @param classesToTest
	 *            the classes to test
	 * @return the long
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected long buildDataSourceProcessorTest(Class<?>... classesToTest)
			throws InstantiationException, IllegalAccessException, IOException {
		return buildTest(KriptonProcessor.class, classesToTest);
	}

	/**
	 * Build standard test.
	 *
	 * @param processorClazz
	 *            the processor clazz
	 * @param classesToTest
	 *            classes to compile and test
	 * @return count of generated sources
	 */
	protected long buildTest(Class<? extends BaseProcessor> processorClazz, Class<?>... classesToTest) {
		final AtomicLong counter = new AtomicLong(0);
		try {
			//final Map<String, String> mapSet = new HashMap<>();
			final List<JavaFileObject> sourcesPhase1 = sources(classesToTest);
						
			System.out.println(com.google.testing.compile.Compiler.javac().toString());
			ImmutableList<JavaFileObject> generated = com.google.testing.compile.Compiler.javac()
					.withProcessors(processorClazz.newInstance())					
					.compile(sourcesPhase1).generatedSourceFiles();
			for (JavaFileObject item : generated) {
				counter.addAndGet(1);
				try {
					//String contentFile = getStringFromInputStream(item.openInputStream());

					//mapSet.put(item.getName(), contentFile);

					switch (testType) {
					case COMPARE:
						writeGeneratedFile(PathSourceType.TARGET_TEST_RESULT, item);
						boolean result = compareGeneratedFile(item);
						Assert.assertTrue(String.format("%s not generated as aspected", item.getName()), result);
						break;
					case NONE:
						break;
					case PREPARE_TEST_ANDROID_LIBRARY:
						writeGeneratedFile(PathSourceType.DEST_TEST_ANDROID_LIBRARY, item);
						break;
					case PREPARE_TEST_JAVA_LIBRARY:
						writeGeneratedFile(PathSourceType.DEST_TEST_JAVA_LIBRARY, item);
						break;
					}

				} catch (Throwable e) {
					// e.printStackTrace();
					Assert.fail(e.getMessage());
				}
			}

			/**
			 * copy beans too
			 */
			switch (testType) {
			case PREPARE_TEST_ANDROID_LIBRARY:
				for (JavaFileObject item : sourcesPhase1) {
					writeGeneratedFile(PathSourceType.DEST_TEST_ANDROID_LIBRARY, item);
				}
				break;
			case PREPARE_TEST_JAVA_LIBRARY:
				for (JavaFileObject item : sourcesPhase1) {
					writeGeneratedFile(PathSourceType.DEST_TEST_JAVA_LIBRARY, item);
				}
				break;
			default:
				break;
			}

		} catch (Throwable e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		return counter.longValue();
	}
}
