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

import static com.abubusoft.testing.compile.JavaSourcesSubjectFactory.javaSources;
import static com.google.common.truth.Truth.assertAbout;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import com.abubusoft.testing.compile.CompileTester.CompilationResultsConsumer;
import com.abubusoft.testing.compile.CompileTester.GenerationClause;
import com.abubusoft.testing.compile.CompileTester.SuccessfulCompilationClause;
import com.abubusoft.testing.compile.JavaFileObjects;
import com.google.common.io.ByteStreams;

public class BaseProcessorTest {

	private static final String KRIPTON_DEBUG_MODE = "kripton.debug";

	protected TestType testType = TestType.NONE;

	public void log(String message, Object... objects) {
		if (objects.length==0) {
			System.out.println(message);
		} else {
			System.out.println(String.format(message, objects));
		}
	}

	/**
	 * Test each elements of two collections. It does not matter the
	 * collections' kind. Elements are compared by reflection.
	 * 
	 * @param collection1
	 * @param collection2
	 */
	public void checkCollectionExactly(Collection<?> collection1, Collection<?> collection2) {
		assertEquals("collections does not have same size", collection1.size(), collection2.size());

		Iterator<?> i1 = collection1.iterator();
		Iterator<?> i2 = collection2.iterator();

		while (i1.hasNext()) {
			ReflectionAssert.assertReflectionEquals(i1.next(), i2.next(), ReflectionComparatorMode.LENIENT_ORDER);
		}
	}

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
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tH:%1$tM:%1$tS.%1$tL %4$-7s [%3$s] (%2$s) %5$s %6$s%n");
	}

	protected PathSourceType destinationPath;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	public <E extends KriptonProcessorException> void expectedException(Class<E> clazzException) throws InstantiationException, IllegalAccessException {
		expectedEx.expect(AssertionError.class);
		expectedEx.expectMessage(clazzException.getSimpleName());
	}

	public enum TestType {
		COMPARE, PREPARE_TEST_ANDROID_LIBRARY, PREPARE_TEST_JAVA_LIBRARY, NONE;
	}

	public enum PathSourceType {
		SRC_TEST_JAVA("src/test/java/"), SRC_TEST_EXPECTED("src/test/expected/"), TARGET_TEST_RESULT("target/test/generated/"), DEST_TEST_ANDROID_LIBRARY(
				"../KriptonAndroidLibrary/src/test/java/"), DEST_TEST_JAVA_LIBRARY("../Kripton/src/test/java/");

		private PathSourceType(String path) {
			this.path = path;
		}

		private String path;

		public String getPath() {
			return path;
		}

		/**
		 * <p>
		 * Create a file starting with specified folder. Path have to start
		 * without "/"
		 * 
		 * @param fileName
		 * @return
		 */
		public File createFile(String fileNameWithRelativePath) {
			if (fileNameWithRelativePath.startsWith("/") || fileNameWithRelativePath.startsWith("\\")) {
				fileNameWithRelativePath = fileNameWithRelativePath.substring(1);
			}
			return new File(this.path + fileNameWithRelativePath);
		}
	};

	protected static JavaFileObject source(Class<?> clazz) throws IOException {
		return getSourceFile(PathSourceType.SRC_TEST_JAVA, clazz);
	}

	protected List<JavaFileObject> sources(Class<?>... clazzes) throws IOException {
		List<JavaFileObject> list = new ArrayList<JavaFileObject>();

		for (Class<?> item : clazzes) {
			list.add(source(item));
		}

		return list;
	}

	protected static void writeGeneratedFile(PathSourceType basePath, JavaFileObject javaFileObject) throws IOException {
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

	protected static boolean compareGeneratedFile(JavaFileObject javaFileObject) throws IOException {
		PathSourceType pathSourceType = PathSourceType.SRC_TEST_EXPECTED;
		Path path = Paths.get(pathSourceType.getPath(), javaFileObject.getName().replace("SOURCE_OUTPUT", ""));

		byte[] bytes = ByteStreams.toByteArray(javaFileObject.openInputStream());
		byte[] aspectedBytes = Files.readAllBytes(path);

		return Arrays.equals(bytes, aspectedBytes);
	}

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
	 * Generate an java file object
	 * 
	 * @param pathSourceType
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	protected static JavaFileObject getSourceFile(PathSourceType pathSourceType, Class<?> clazz) throws IOException {
		Path path = Paths.get(pathSourceType.getPath(), clazz.getCanonicalName().replace(".", Character.toString(File.separatorChar)) + ".java");
		byte[] buffer = Files.readAllBytes(path.toAbsolutePath());

		JavaFileObject source = JavaFileObjects.forSourceLines(clazz.getCanonicalName(), new String(buffer));
		return source;
	}

	protected void withGeneratedSourceCounter(long realValue, long aspected) {
		Assert.assertEquals(realValue, aspected);
	}

	protected long buildSharedPreferencesProcessorTest(Class<?>... classesToTest) throws InstantiationException, IllegalAccessException, IOException {
		return buildTest(KriptonProcessor.class, classesToTest);
	}

	protected long buildBindProcessorTest(Class<?>... classesToTest) throws InstantiationException, IllegalAccessException, IOException {
		return buildTest(KriptonProcessor.class, classesToTest);
	}

	protected long buildDataSourceProcessorTest(Class<?>... classesToTest) throws InstantiationException, IllegalAccessException, IOException {
		return buildTest(KriptonProcessor.class, classesToTest);
	}
	

	/**
	 * Build standard test
	 * 
	 * @param classesToTest
	 *            classes to compile and test
	 * 
	 * @return count of generated sources
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws Throwable
	 */
	protected long buildTest(Class<? extends BaseProcessor> processorClazz, Class<?>... classesToTest) {
		final AtomicLong counter = new AtomicLong(0);
		try {
			final Map<String, String> mapSet = new HashMap<>();
			final List<JavaFileObject> sourcesPhase1 = sources(classesToTest);

			SuccessfulCompilationClause result1 = assertAbout(javaSources()).that(sourcesPhase1).processedWith(processorClazz.newInstance()).compilesWithoutError();
			GenerationClause<SuccessfulCompilationClause> resultPhase1 = result1.and().generatesSources();
			resultPhase1.forAllOfWhich(new CompilationResultsConsumer() {

				@Override
				public void accept(Map<String, JavaFileObject> t) {
					for (Entry<String, JavaFileObject> item : t.entrySet()) {
						counter.addAndGet(1);
						try {
							String contentFile = getStringFromInputStream(item.getValue().openInputStream());

							mapSet.put(item.getKey(), contentFile);

							switch (testType) {
							case COMPARE:
								writeGeneratedFile(PathSourceType.TARGET_TEST_RESULT, item.getValue());
								boolean result = compareGeneratedFile(item.getValue());
								Assert.assertTrue(String.format("%s not generated as aspected", item.getKey()), result);
								break;
							case NONE:
								break;
							case PREPARE_TEST_ANDROID_LIBRARY:
								writeGeneratedFile(PathSourceType.DEST_TEST_ANDROID_LIBRARY, item.getValue());
								break;
							case PREPARE_TEST_JAVA_LIBRARY:
								writeGeneratedFile(PathSourceType.DEST_TEST_JAVA_LIBRARY, item.getValue());
								break;
							}

						} catch (Throwable e) {
							// e.printStackTrace();
							Assert.fail(e.getMessage());
						}
					}

				}
			});

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
			//e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		return counter.longValue();
	}
}
