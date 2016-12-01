package com.abubusoft.kripton.processor.sharedprefs.transform;

import java.util.HashSet;
import java.util.Set;

import com.abubusoft.kripton.processor.exceptions.KriptonClassNotFoundException;
import com.squareup.javapoet.ParameterizedTypeName;

public class SetTransformation extends AbstractCollectionTransform {

	public SetTransformation(ParameterizedTypeName clazz) {
		super(clazz);
	}
	
	protected Class<?> defineCollectionClass(ParameterizedTypeName collectionTypeName) {
		if (collectionTypeName.toString().startsWith(Set.class.getCanonicalName())) {
			// it's a set
			return HashSet.class;
		}
		try {
			return Class.forName(collectionTypeName.rawType.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new KriptonClassNotFoundException(e);			
		}
	}

}
