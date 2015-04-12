/**
 * 
 */
package com.abubusoft.kripton.database;

/**
 * @author xcesco
 *
 */
public class DeleteOptions {

	public String fields = "*";

	public String where;

	public Class<?> paramsClass;

	public String name;

	public DeleteOptions where(String value) {
		where = value;
		return this;
	}

	public DeleteOptions select(String value) {
		fields = value;
		return this;
	}

	public DeleteOptions paramsClass(Class<?> value) {
		paramsClass = value;
		return this;
	}

	public static DeleteOptions build() {
		return new DeleteOptions();
	}

	public DeleteOptions name(String value) {
		name = value;
		return this;
	}

}
