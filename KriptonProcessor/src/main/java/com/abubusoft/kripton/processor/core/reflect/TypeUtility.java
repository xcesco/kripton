package com.abubusoft.kripton.processor.core.reflect;

import com.squareup.javapoet.TypeName;

public class TypeUtility {
	public static boolean isIn(TypeName value, Class<?>... classes) {
		for (Class<?> item : classes) {
			if (value.toString().equals(TypeName.get(item).toString())) {
				return true;
			}
		}

		return false;
	}

}
