/**
 * 
 */
package com.abubusoft.kripton.processor.bind;

import java.io.IOException;

import javax.annotation.processing.Filer;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaWriterHelper.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class JavaWriterHelper {

	/**
	 * Write java 2 file.
	 *
	 * @param filer the filer
	 * @param packageName the package name
	 * @param typeSpec the type spec
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void writeJava2File(Filer filer, String packageName, TypeSpec typeSpec) throws IOException {
		JavaFile target = JavaFile.builder(packageName, typeSpec).skipJavaLangImports(true).build();
		target.writeTo(filer);
	}

}
