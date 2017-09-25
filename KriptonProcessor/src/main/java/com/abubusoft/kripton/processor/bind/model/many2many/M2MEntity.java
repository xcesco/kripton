package com.abubusoft.kripton.processor.bind.model.many2many;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.StringUtils;
import com.squareup.javapoet.TypeName;

public class M2MEntity extends M2MBase {

	private String packageName;

	public String entityName1;

	public String entityName2;

	public String idName;

	public TypeName daoTypeName;

	private List<M2MProperty> collection = new ArrayList<M2MProperty>();

	public String getPackageName() {
		return packageName;
	}

	public String name;

	public String tableName;

	public String daoName;

	public M2MEntity(String packageName, String daoName, TypeName daoTypeName, String entityName1, String entityName2, String idName, String tableName) {
		this.packageName = packageName;
		this.entityName1 = entityName1;
		this.entityName2 = entityName2;
		this.daoName = daoName;
		this.daoTypeName = daoTypeName;
		this.idName = idName;
		this.name = extractClassName(entityName1) + extractClassName(entityName2);
		this.tableName = StringUtils.hasText(tableName) ? tableName : (CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name));
	}

	public List<M2MProperty> getCollection() {
		return collection;
	}

	public static String extractClassName(String fullName) {
		int l = fullName.lastIndexOf(".");

		return fullName.substring(l + 1);
	}

}
