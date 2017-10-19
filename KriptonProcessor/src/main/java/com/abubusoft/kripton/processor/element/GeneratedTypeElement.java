package com.abubusoft.kripton.processor.element;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public class GeneratedTypeElement {

	public String packageName;
	public TypeSpec typeSpec;
	protected String tableName;
	public Set<SQLEntity> referedEntities=new HashSet<>();
	public List<String> index=new ArrayList<>();
	
	public List<SQLProperty> properties=new ArrayList<SQLProperty>();

	public String getTableName() {
		return tableName;
	}

	public GeneratedTypeElement(String packageName, TypeSpec typeSpec, String tableName, String index) {
		this.packageName = packageName;
		this.typeSpec = typeSpec;
		this.tableName=tableName;
		
		if (StringUtils.hasText(index)) {
			this.index.add(index);
		}
	}	
	
	public String getQualifiedName() {
		if (StringUtils.hasText(packageName)) {
			return packageName+"."+typeSpec.name;
		}
						
		return typeSpec.name;
	}
	
	public String getName() {
		return getQualifiedName();
	}

	public String getSimpleName() {
		return typeSpec.name;
	}

	public List<SQLProperty> getCollection() {
		return properties;
	}
	
	public TypeName getClassName() {
		return TypeUtility.className(getQualifiedName());
	}

}
