package com.abubusoft.kripton.processor.bind.transform;

import java.util.ArrayList;
import java.util.List;

import com.squareup.javapoet.ParameterizedTypeName;

public class ListTransformation extends AbstractCollectionTransform {

	public ListTransformation(ParameterizedTypeName clazz) {
		super(clazz, false);
	}
	
	protected Class<?> defineCollectionClass(ParameterizedTypeName collectionTypeName) {
		if (collectionTypeName.toString().startsWith(List.class.getCanonicalName())) {
			// it's a list
			return ArrayList.class;
		}
		try {
			return Class.forName(collectionTypeName.rawType.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
