package com.abubusoft.kripton.processor.element;

import com.abubusoft.kripton.common.StringUtils;
import com.squareup.javapoet.TypeSpec;

public class GeneratedTypeElement {

	public String packageName;
	public TypeSpec typeSpec;

	public GeneratedTypeElement(String packageName, TypeSpec typeSpec) {
		this.packageName = packageName;
		this.typeSpec = typeSpec;
	}	
	
	public String getQualifiedName() {
		if (StringUtils.hasText(packageName)) {
			return packageName+"."+typeSpec.name;
		}
		
		return typeSpec.name;
	}

}
