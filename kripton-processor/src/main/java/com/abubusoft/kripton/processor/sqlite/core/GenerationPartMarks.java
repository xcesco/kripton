/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.core;

/**
 * <p>Needed to mark generation code methods
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public enum GenerationPartMarks{

	CODE_001
	;

	public static void begin(com.squareup.javapoet.MethodSpec.Builder methodBuilder, GenerationPartMarks code) {
		methodBuilder.addCode("// generation $L -- BEGIN\n", code);		
	}

	public static void end(com.squareup.javapoet.MethodSpec.Builder methodBuilder, GenerationPartMarks code) {
		methodBuilder.addCode("// generation $L -- END\n", code);
		
	}

}
