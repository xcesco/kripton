package com.abubusoft.kripton.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.tools.JavaFileObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.abubusoft.kritpon.example01.ChannelMessage;
import com.abubusoft.kritpon.example01.DummyDatabase;
import com.google.testing.compile.JavaFileObjects;

public class BaseProcessorTest {
	protected Log logger = LogFactory.getLog(getClass());

	public enum PathSourceType {
		SRC_TEST_JAVA("src/test/java/");

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
}
