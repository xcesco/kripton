/**
 * 
 */
package com.abubusoft.kripton.processor.bind;

import java.io.IOException;
import java.util.HashSet;

import javax.annotation.processing.Filer;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class JavaWriterHelper {

	//private static HashSet<String> values=new HashSet<>();
	
	public static void reset() {
		//values.clear();
	}
	
	/**
	 * @param filer
	 * @param packageName
	 * @param typeSpec
	 * @throws IOException
	 */
	public static void writeJava2File(Filer filer, String packageName, TypeSpec typeSpec) throws IOException {
		String fileName = packageName.isEmpty()   ? typeSpec.name     : packageName + "." + typeSpec.name;
		JavaFile target = JavaFile.builder(packageName, typeSpec).skipJavaLangImports(true).build();
								
		//if (!values.contains(fileName)) {			
			target.writeTo(filer);
			//values.add(fileName);
		//}
	}
	
}
