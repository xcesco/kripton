package com.abubusoft.kripton.processor.bind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.squareup.javapoet.TypeName;

/**
 * Binding Map Registry 
 * @author xcesco
 *
 */
public class BinderMapRegistry
{
	/* ordered set of binder needed for model */
	private Map<TypeName, Set<TypeName>> registry=new HashMap<TypeName, Set<TypeName>>();
	
	private final static BinderMapRegistry instance=new BinderMapRegistry();
	
	public static BinderMapRegistry getInstance() {
		return instance;
	}

	public void registry(TypeName binderTypeName, TypeName typeName) {
		Set<TypeName> entry = registry.get(binderTypeName);
		
		if (entry==null) {
			entry=new HashSet<>();
			registry.put(binderTypeName, entry);
		}
		
		entry.add(typeName);
		
		
	}

	public Pair<String, TypeName> getMapperNames(TypeName typeName) {
		Converter<String, String> format = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		TypeName bindMapperName=TypeUtility.mergeTypeNameWithSuffix(typeName,BindTypeBuilder.SUFFIX);
		String simpleName=format.convert(TypeUtility.simpleName(bindMapperName));
		
		Pair<String, TypeName>result=Pair.of(simpleName, bindMapperName);
		
		return result;
	}

	public Set<TypeName> getEntityEntries(TypeName entityType) {
		return registry.get(entityType);
	}
	
	
}
