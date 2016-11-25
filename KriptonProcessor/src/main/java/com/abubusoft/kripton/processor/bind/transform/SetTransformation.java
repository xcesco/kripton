package com.abubusoft.kripton.processor.bind.transform;

import java.util.HashSet;
import java.util.Set;

import com.squareup.javapoet.ParameterizedTypeName;

public class SetTransformation extends AbstractCollectionTransform {

	public SetTransformation(ParameterizedTypeName clazz) {
		super(clazz, CollectionType.SET);
	}
	
	protected Class<?> defineCollectionClass(ParameterizedTypeName collectionTypeName) {
		if (collectionTypeName.toString().startsWith(Set.class.getCanonicalName())) {
			// it's a list
			return HashSet.class;
		}
		try {
			return Class.forName(collectionTypeName.rawType.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
