package com.abubusoft.kripton.processor.bind.model.many2many;

import java.util.ArrayList;
import java.util.List;

import com.squareup.javapoet.TypeName;

public class M2MEntity extends M2MBase {

	private String packageName;
	
	public String entityName1;
		
	public String entityName2;
	
	public String idPrefix;
	
	public TypeName daoTypeName;
	
	private List<M2MProperty> collection=new ArrayList<M2MProperty>();
	
	public String getPackageName() {
		return packageName;
	}


	public String name;

	public M2MEntity(String packageName, TypeName daoTypeName, String entityName1, String entityName2) {
		this.packageName=packageName;
		this.entityName1=entityName1;
		this.entityName2=entityName2;
		this.daoTypeName=daoTypeName;
		this.name=extractClassName(entityName1)+"2"+extractClassName(entityName2);
	}

	public List<M2MProperty> getCollection() {
		return collection;
	}
	
	private static String extractClassName(String fullName) {
		int l=fullName.lastIndexOf(".");
		
		return fullName.substring(l+1);
	}
	
}
