package com.abubusoft.kripton.processor;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;

import javax.tools.JavaFileObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kritpon.example01.Channel;
import com.abubusoft.kritpon.example01.ChannelMessage;
import com.abubusoft.kritpon.example01.DaoChannel;
import com.abubusoft.kritpon.example01.DaoChannelMessage;
import com.abubusoft.kritpon.example01.DummyDatabase;
import com.google.common.truth.FailureStrategy;
import com.google.common.truth.TestVerb;
import com.google.testing.compile.CompileTester.CompilationResultsConsumer;
import com.google.testing.compile.CompileTester.GenerationClause;
import com.google.testing.compile.CompileTester.SuccessfulCompilationClause;
import com.google.testing.compile.JavaFileObjects;

@RunWith(JUnit4.class)
public class SQLiteProcessorTest extends BaseProcessorTest {

	@Test
	public void test01() throws IOException {
		/*
		 * assert_().about(javaSource()) .that(JavaFileObjects.forSourceString("HelloWorld", "final class HelloWorld {}")) .compilesWithoutError();
		 */

		JavaFileObject source = getSourceFile(PathSourceType.SRC_TEST_JAVA, DummyDatabase.class);
		SuccessfulCompilationClause result = assertAbout(javaSource()).that(source).processedWith(new SQLiteProcessor()).compilesWithoutError();
		GenerationClause<SuccessfulCompilationClause> sources = result.and().generatesSources();

		sources.forAllOfWhich(new CompilationResultsConsumer() {

			@Override
			public void accept(Map<String, JavaFileObject> t) {

				for (Entry<String, JavaFileObject> item : t.entrySet()) {
					logger.info("item " + item.getKey());
					try {
						logger.info("-------\n" + getStringFromInputStream(item.getValue().openInputStream()));
						assertAbout(javaSource()).that(item.getValue()).compilesWithoutError();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		});

		// .that(JavaFileObjects.forResource(Resources.getResource("HelloWorld.java")))compilesWithoutError();
		// result.and().generatesFileNamed(StandardLocation.SOURCE_OUTPUT,"com.abubusoft.kripton.processor","EntityBeanConvert.java").withContents(ByteSource.empty());
		// logger.info(new String(buffer));
	}

	@Test
	public void testMultiple() throws IOException {
		//@formatter:off
		SuccessfulCompilationClause result = assertAbout(javaSources()).that(
				sources(
						DummyDatabase.class,
						ChannelMessage.class, Channel.class,
						DaoChannelMessage.class, DaoChannel.class
				))
				.processedWith(new SQLiteProcessor()).compilesWithoutError();
		//@formatter:on
		GenerationClause<SuccessfulCompilationClause> sources = result.and().generatesSources();

		sources.forAllOfWhich(new CompilationResultsConsumer() {

			@Override
			public void accept(Map<String, JavaFileObject> t) {

				for (Entry<String, JavaFileObject> item : t.entrySet()) {
					logger.info("item " + item.getKey());
					try {
						logger.info("-------\n" + getStringFromInputStream(item.getValue().openInputStream()));
						assertAbout(javaSource()).that(item.getValue()).compilesWithoutError();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		});

		// .that(JavaFileObjects.forResource(Resources.getResource("HelloWorld.java")))compilesWithoutError();
		// result.and().generatesFileNamed(StandardLocation.SOURCE_OUTPUT,"com.abubusoft.kripton.processor","EntityBeanConvert.java").withContents(ByteSource.empty());
		// logger.info(new String(buffer));
	}

	/*
	 * private static final JavaFileObject ENTITY_BEAN = JavaFileObjects.forSourceLines( EntityBean.class.getCanonicalName(), Files.readAllBytes(Path.get("/src/main/java/"+, ))));
	 */
	@Test
	public void test02() throws IOException {
		/*
		 * assert_().about(javaSource()) .that(JavaFileObjects.forSourceString("HelloWorld", "final class HelloWorld {}")) .compilesWithoutError();
		 */

		Path path = Paths.get("src/test/java/", UserIdentity.class.getCanonicalName().replace(".", Character.toString(File.separatorChar)) + ".java");

		byte[] buffer = Files.readAllBytes(path.toAbsolutePath());

		JavaFileObject source = JavaFileObjects.forSourceLines(UserIdentity.class.getCanonicalName(), new String(buffer));
		// assertAbout(javaSource).that()
		SuccessfulCompilationClause result = assertAbout(javaSource()).that(source).processedWith(new SQLiteProcessor()).compilesWithoutError();

		GenerationClause<SuccessfulCompilationClause> sources = result.and().generatesSources();

		sources.forAllOfWhich(new CompilationResultsConsumer() {

			@Override
			public void accept(Map<String, JavaFileObject> t) {

				for (Entry<String, JavaFileObject> item : t.entrySet()) {
					logger.info("item " + item.getKey());
					try {
						logger.info("-------\n" + getStringFromInputStream(item.getValue().openInputStream()));
						assertAbout(javaSource()).that(item.getValue()).compilesWithoutError();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		});

		// .that(JavaFileObjects.forResource(Resources.getResource("HelloWorld.java")))compilesWithoutError();
		// result.and().generatesFileNamed(StandardLocation.SOURCE_OUTPUT,"com.abubusoft.kripton.processor","EntityBeanConvert.java").withContents(ByteSource.empty());
		// logger.info(new String(buffer));
	}

	// convert InputStream to String
	private static String getStringFromInputStream(InputStream is) {

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

	private static final TestVerb VERIFY = new TestVerb(new FailureStrategy() {
		@Override
		public void fail(String message) {
			throw new RuntimeException(message);
		}
	});

	@Test
	public void compilesWithoutWarnings_failsWithWarnings() throws IOException {
		/*
		 * Path path = Paths.get("src/test/java/", EntityBean.class.getCanonicalName().replace(".", Character.toString(File.separatorChar)) + ".java");
		 * 
		 * byte[] buffer = Files.readAllBytes(path.toAbsolutePath());
		 * 
		 * JavaFileObject source = JavaFileObjects.forSourceLines(EntityBean.class.getCanonicalName(), new String(buffer));
		 * 
		 * VERIFY.about(javaSource()).that(source).processedWith(new BundleTypeProcessor()).compilesWithoutError();
		 */

	}
}
