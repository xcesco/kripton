package com.abubusoft.kripton.processor.bind.transform;

import java.util.HashSet;
import java.util.Set;

import com.squareup.javapoet.ParameterizedTypeName;

public class SetBindTransformation extends AbstractCollectionBindTransform {

	public SetBindTransformation(ParameterizedTypeName clazz) {
		super(clazz, CollectionType.SET);
		collectionClazz=Set.class;
		defaultClazz=HashSet.class;
	}
	
	
}
