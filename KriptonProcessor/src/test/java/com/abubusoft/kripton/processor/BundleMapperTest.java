package com.abubusoft.kripton.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import static com.google.common.truth.Truth.assertAbout;
import static com.google.common.truth.Truth.assertThat;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

import javax.tools.JavaFileObject;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.primitives.Bytes;
import com.google.common.truth.FailureStrategy;
import com.google.common.truth.TestVerb;
import com.google.testing.compile.JavaFileObjects;

@RunWith(JUnit4.class)
public class BundleMapperTest {
	  
	Log logger=LogFactory.getLog(getClass());

	/*
	private static final JavaFileObject ENTITY_BEAN =
		      JavaFileObjects.forSourceLines(
		          EntityBean.class.getCanonicalName(),
		          Files.readAllBytes(Path.get("/src/main/java/"+, ))));
	*/
	@Test
	public void test01() throws IOException
	{
	/*	assert_().about(javaSource())
		.that(JavaFileObjects.forSourceString("HelloWorld", "final class HelloWorld {}"))
		.compilesWithoutError();*/
		
		Path path=Paths.get("src/test/java/",EntityBean.class.getCanonicalName().replace(".", Character.toString(File.separatorChar))+".java" );
		
		byte[] buffer=Files.readAllBytes(path.toAbsolutePath());
	
		/*assert_().about(javaSource())
		 *       .that(JavaFileObjects.forResource("HelloWorld.java"))
		 *       .processedWith(new MyAnnotationProcessor())
		 *       .compilesWithoutError()
		 *       .and().generatesSources(JavaFileObjects.forResource("GeneratedHelloWorld.java"));*/
		
		JavaFileObject source = JavaFileObjects.forSourceLines(EntityBean.class.getCanonicalName(),new String(buffer));
		//assertAbout(javaSource).that()
		assertAbout(javaSource()).that(source).processedWith(new ConvertProcessor()).compilesWithoutError();
        //.that(JavaFileObjects.forResource(Resources.getResource("HelloWorld.java")))compilesWithoutError();
		
		
		logger.info(new String(buffer));
	}
}
