/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.core;

// TODO: Auto-generated Javadoc
/**
 * <p>Needed to mark generation code methods.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public enum GenerationPartMarks{

	/** The code 001. */
	CODE_001
	;

	/**
	 * Begin.
	 *
	 * @param methodBuilder the method builder
	 * @param code the code
	 */
	public static void begin(com.squareup.javapoet.MethodSpec.Builder methodBuilder, GenerationPartMarks code) {
		methodBuilder.addCode("// generation $L -- BEGIN\n", code);		
	}

	/**
	 * End.
	 *
	 * @param methodBuilder the method builder
	 * @param code the code
	 */
	public static void end(com.squareup.javapoet.MethodSpec.Builder methodBuilder, GenerationPartMarks code) {
		methodBuilder.addCode("// generation $L -- END\n", code);
		
	}

}
