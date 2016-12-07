package com.abubusoft.kripton.processor.bind.transform;

import java.util.ArrayList;
import java.util.List;

import com.squareup.javapoet.ParameterizedTypeName;

public class ListBindTransformation extends AbstractCollectionBindTransform {

	public ListBindTransformation(ParameterizedTypeName clazz) {
		super(clazz, CollectionType.LIST);
		collectionClazz=List.class;
		defaultClazz=ArrayList.class;
	}

}
