package com.abubusoft.kripton.processor;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import javax.tools.JavaFileObject;

import org.junit.Assert;
import org.junit.Before;

import com.google.common.io.ByteStreams;
import com.google.testing.compile.CompileTester.CompilationResultsConsumer;
import com.google.testing.compile.CompileTester.GenerationClause;
import com.google.testing.compile.CompileTester.SuccessfulCompilationClause;
import com.google.testing.compile.JavaFileObjects;

public class BaseProcessorTest {
	
	final TestType testType=TestType.GENERATE;
	
	@Before
	public void before()
	{
		/*		
		System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL %4$-7s [%3$s] (%2$s) %5$s %6$s%n");
                */
		
		System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tH:%1$tM:%1$tS.%1$tL %4$-7s [%3$s] (%2$s) %5$s %6$s%n");
		 
		// logger = LogManager.getLogger(getClass());
	}
	
	public enum TestType
	{
		GENERATE,
		COMPARE
	}
	
	protected static Logger logger = Logger.getGlobal();

	public enum PathSourceType {
		SRC_TEST_JAVA("src/test/java/"),
		SRC_TEST_RESULT("src/test/results/");

		private PathSourceType(String path) {
			this.path = path;
		}

		private String path;

		public String getPath() {
			return path;
		}
		
		/**
		 * <p>
		 * Create a file starting with specified folder. Path have to start without "/" 
		 * 
		 * @param fileName
		 * @return
		 */
		public File createFile(String fileNameWithRelativePath)
		{
			if (fileNameWithRelativePath.startsWith("/") || fileNameWithRelativePath.startsWith("\\") )
			{
				fileNameWithRelativePath=fileNameWithRelativePath.substring(1);
			}
			return new File(this.path+fileNameWithRelativePath);
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
	}
	
	protected static boolean compareGeneratedFile(JavaFileObject javaFileObject) throws IOException
	{
		PathSourceType pathSourceType=PathSourceType.SRC_TEST_RESULT;
		Path path = Paths.get(pathSourceType.getPath(), javaFileObject.getName().replace("SOURCE_OUTPUT", ""));
				
		Files.createDirectories(path.getParent());
		byte[] bytes=ByteStreams.toByteArray(javaFileObject.openInputStream());		 		
		byte[] aspectedBytes=Files.readAllBytes(path);
		
		return Arrays.equals(bytes,aspectedBytes);
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
	
	protected void withGeneratedSourceCounter(long realValue, long aspected)
	{
		Assert.assertEquals(realValue, aspected);
	}
	
	protected long buildSharedPreferencesProcessorTest(Class<?> ... classesToTest) throws IOException, InstantiationException, IllegalAccessException
	{
		return buildTest(BindSharedPreferencesProcessor.class, classesToTest);
	}
	
	protected long buildDataSourceProcessorTest(Class<?> ... classesToTest) throws IOException, InstantiationException, IllegalAccessException
	{
		return buildTest(BindDataSourceProcessor.class, classesToTest);
	}
	
	/**
	 * Build standard test
	 * 
	 * @param classesToTest
	 * 		classes to compile and test
	 *  
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * 
	 *  @return count of generated sources
	 */
	protected long buildTest(Class<? extends BaseProcessor> processorClazz, Class<?> ... classesToTest) throws IOException, InstantiationException, IllegalAccessException
	{
		final Map<String, String> mapSet=new HashMap<>();
		final List<JavaFileObject> sourcesPhase1=sources(classesToTest);
		
		BindDataSourceProcessor.DEVELOP_MODE=true;		
		
		final AtomicLong counter=new AtomicLong(0);
		
		//@formatter:off
		SuccessfulCompilationClause result1 = assertAbout(javaSources()).that(
				sourcesPhase1).processedWith(processorClazz.newInstance()).compilesWithoutError();
		//@formatter:on
		GenerationClause<SuccessfulCompilationClause> resultPhase1 = result1.and().generatesSources();						
		resultPhase1.forAllOfWhich(new CompilationResultsConsumer() {

			@Override
			public void accept(Map<String, JavaFileObject> t) {				
				for (Entry<String, JavaFileObject> item : t.entrySet()) {
					counter.addAndGet(1);
					//logger.info("item " + item.getKey());
					try {
						String contentFile=getStringFromInputStream(item.getValue().openInputStream());
												
						mapSet.put(item.getKey(),contentFile);												
						
						if (testType==TestType.COMPARE)
						{
							boolean result=compareGeneratedFile(item.getValue());
							Assert.assertTrue(String.format("%s not generated as aspected", item.getKey()), result);
							//logger.info(String.format("%s: %s",item.getKey(), result));
						} else {
							writeGeneratedFile(item.getValue());
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		});
		
		return counter.longValue();
	}
}
