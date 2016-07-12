package com.abubusoft.kripton.processor;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.tools.JavaFileObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.io.ByteStreams;
import com.google.testing.compile.JavaFileObjects;
import com.google.testing.compile.CompileTester.CompilationResultsConsumer;
import com.google.testing.compile.CompileTester.GenerationClause;
import com.google.testing.compile.CompileTester.SuccessfulCompilationClause;

public class BaseProcessorTest {
	protected Log logger = LogFactory.getLog(getClass());

	public enum PathSourceType {
		SRC_TEST_JAVA("src/test/java/"),
		SRC_TEST_RESULT("src/test/result/");

		private PathSourceType(String path) {
			this.path = path;
		}

		private String path;

		public String getPath() {
			return path;
		}
	};
	
	protected static JavaFileObject source(Class<?> clazz) throws IOException
	{
		return getSourceFile(PathSourceType.SRC_TEST_JAVA, clazz);
	}
	
	protected List<JavaFileObject> sources(Class<?> ... clazzes) throws IOException
	{
		List<JavaFileObject> list=new ArrayList<JavaFileObject>();
		
		for (Class<?> item: clazzes)
		{
			list.add(source(item));
		}		
		
		return list;
	}
	
	protected static void writeGeneratedFile(JavaFileObject javaFileObject) throws IOException
	{
		PathSourceType pathSourceType=PathSourceType.SRC_TEST_RESULT;
		Path path = Paths.get(pathSourceType.getPath(), javaFileObject.getName().replace("SOURCE_OUTPUT", ""));
				
		Files.createDirectories(path.getParent());
		byte[] bytes=ByteStreams.toByteArray(javaFileObject.openInputStream());		 
		
		Files.write(path, bytes );
		//javaFileObject.getName();
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
	
	protected void buildSharedPreferencesProcessorTest(Class<?> ... classesToTest) throws IOException, InstantiationException, IllegalAccessException
	{
		buildTest(BindSharedPreferencesProcessor.class, classesToTest);
	}
	
	protected void buildDataSourceProcessorTest(Class<?> ... classesToTest) throws IOException, InstantiationException, IllegalAccessException
	{
		buildTest(BindDataSourceProcessor.class, classesToTest);
	}
	
	/**
	 * Build standard test
	 * 
	 * @param classesToTest
	 * 		classes to compile and test
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	protected void buildTest(Class<? extends BaseProcessor> processorClazz, Class<?> ... classesToTest) throws IOException, InstantiationException, IllegalAccessException
	{
		final List<JavaFileObject> sourcesPhase2=new ArrayList<JavaFileObject>();
		
		final List<JavaFileObject> sourcesPhase1=sources(classesToTest);
		
		BindDataSourceProcessor.DEVELOP_MODE=true;
		
		//@formatter:off
		SuccessfulCompilationClause result1 = assertAbout(javaSources()).that(
				sourcesPhase1).processedWith(processorClazz.newInstance()).compilesWithoutError();
		//@formatter:on
		GenerationClause<SuccessfulCompilationClause> resultPhase1 = result1.and().generatesSources();
		
		resultPhase1.forAllOfWhich(new CompilationResultsConsumer() {

			@Override
			public void accept(Map<String, JavaFileObject> t) {				
				for (Entry<String, JavaFileObject> item : t.entrySet()) {
					logger.info("item " + item.getKey());
					try {
						sourcesPhase2.add(item.getValue());
						logger.info("-------\n" + getStringFromInputStream(item.getValue().openInputStream()));
						writeGeneratedFile(item.getValue());
						//assertAbout(javaSource()).that(item.getValue()).compilesWithoutError();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		});
	}
}
