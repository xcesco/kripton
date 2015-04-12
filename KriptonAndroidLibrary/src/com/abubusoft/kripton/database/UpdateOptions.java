package com.abubusoft.kripton.database;

public class UpdateOptions {
	public String fields = "*";

	public String where;

	public Class<?> paramsClass;

	public String name;

	public UpdateOptions where(String value) {
		where = value;
		return this;
	}

	public UpdateOptions select(String value) {
		fields = value;
		return this;
	}

	public UpdateOptions paramsClass(Class<?> value) {
		paramsClass = value;
		return this;
	}

	public static UpdateOptions build() {
		return new UpdateOptions();
	}

	public UpdateOptions name(String value) {
		name = value;
		return this;
	}
}
