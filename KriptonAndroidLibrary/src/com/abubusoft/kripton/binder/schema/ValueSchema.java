package com.abubusoft.kripton.binder.schema;

/**
 * This bean stores mapping between an XML value and a POJO field.
 * 
 * @author bulldog
 *
 */
public class ValueSchema extends AbstractSchema {

	private boolean data;
	
	/**
	 * Indicates if the string content of the field should be put 
	 * in a CDATA container on serialization
	 * 
	 * @return true or false
	 */
	public boolean isData() {
		return data;
	}

	/**
	 * Set if the string content of the field should be put 
	 * in a CDATA container on serialization
	 * 
	 * @param data
	 */
	public void setData(boolean data) {
		this.data = data;
	}
	
}
