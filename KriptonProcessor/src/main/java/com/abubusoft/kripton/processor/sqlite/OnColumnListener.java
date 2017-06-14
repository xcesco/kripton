package com.abubusoft.kripton.processor.sqlite;

import com.squareup.javapoet.MethodSpec.Builder;

public interface OnColumnListener {

	void onColumnCheck(Builder methodBuilder, String columnName);

}
