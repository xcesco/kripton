/**
 * 
 */
package com.abubusoft.kripton.processor.bind;

import java.io.IOException;

import javax.annotation.processing.Filer;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class JavaWriterHelper {

	/**
	 * @param filer
	 * @param packageName
	 * @param typeSpec
	 * @throws IOException
	 */
	public static void writeJava2File(Filer filer, String packageName, TypeSpec typeSpec) throws IOException {
		JavaFile target = JavaFile.builder(packageName, typeSpec).skipJavaLangImports(true).build();
		target.writeTo(filer);
	}

}
