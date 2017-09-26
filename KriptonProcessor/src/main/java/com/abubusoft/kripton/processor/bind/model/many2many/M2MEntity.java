package com.abubusoft.kripton.processor.bind.model.many2many;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.ClassName;

public class M2MEntity extends M2MBase {

	private String packageName;

	public ClassName entity1Name;

	public ClassName entity2Name;

	public String idName;

	private List<M2MProperty> collection = new ArrayList<M2MProperty>();

	public String getPackageName() {
		return packageName;
	}

	public String name;

	public String tableName;

	public ClassName daoName;

	public M2MEntity(String packageName, ClassName daoClazzName, String entity1ClazzName, String entity2ClazzName, String idName, String tableName) {
		this.packageName = packageName;
		this.entity1Name = TypeUtility.className(entity1ClazzName);
		this.entity2Name = TypeUtility.className(entity2ClazzName);
		this.daoName = daoClazzName;
		this.idName = idName;
		this.name = entity1Name.simpleName() + entity2Name.simpleName();
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
