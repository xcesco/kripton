package com.abubusoft.kripton.processor.exceptions;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class KriptonClassNotFoundException extends KriptonProcessorException {

	public KriptonClassNotFoundException(ClassNotFoundException e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5217387587464012546L;

}
