package com.abubusoft.kripton.processor.bind.model.many2many;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.ClassName;

public class M2MEntity extends M2MBase {

	private String packageName;

	public ClassName entity1Name;

	public ClassName entity2Name;

	public String idName;

	public String getPackageName() {
		return packageName;
	}

	public String name;

	public String tableName;

	public ClassName daoName;

	public boolean needToCreate;
	
	public TypeElement daoElement;

	public M2MEntity(TypeElement daoElement, String packageName, String entityName, ClassName daoClazzName, ClassName entity1ClazzName, ClassName entity2ClazzName, String idName, String tableName, boolean needToCreate) {
		this.packageName = packageName;
		this.entity1Name = entity1ClazzName;
		this.entity2Name = entity2ClazzName;
		this.daoName = daoClazzName;
		this.idName = idName;
		this.name = entityName;
		this.tableName = StringUtils.hasText(tableName) ? tableName : (CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name));
		this.needToCreate = needToCreate;
		this.daoElement=daoElement;
	}

	public static String extractClassName(String fullName) {
		int l = fullName.lastIndexOf(".");

		return fullName.substring(l + 1);
	}

	public ClassName getClassName() {
		return TypeUtility.className(this.packageName, this.name);
	}

	/**
	 * Works with @BindDaoMany2Many and @BindDao to extract entity name.
	 * 
	 * @param item
	 * @return
	 */
	public static M2MEntity extractEntityManagedByDAO(TypeElement daoElement) {
		ClassName entity1 = null;
		ClassName entity2 = null;
		String prefixId = null;
		String tableName = null;
		String entityName = null;
		PackageElement pkg = null;
		String packageName = null;
		boolean needToCreate = true;

		if (daoElement.getAnnotation(BindDaoMany2Many.class) != null) {
			entity1 = TypeUtility.className(AnnotationUtility.extractAsClassName(daoElement, BindDaoMany2Many.class, AnnotationAttributeType.ENTITY_1));
			entity2 = TypeUtility.className(AnnotationUtility.extractAsClassName(daoElement, BindDaoMany2Many.class, AnnotationAttributeType.ENTITY_2));
			prefixId = AnnotationUtility.extractAsString(daoElement, BindDaoMany2Many.class, AnnotationAttributeType.ID_NAME);
			tableName = AnnotationUtility.extractAsString(daoElement, BindDaoMany2Many.class, AnnotationAttributeType.TABLE_NAME);
			entityName = entity1.simpleName() + entity2.simpleName();
			pkg = BaseProcessor.elementUtils.getPackageOf(daoElement);
			packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();
		}

		if (daoElement.getAnnotation(BindDao.class) != null) {
			// we have @BindDao
			String derived = AnnotationUtility.extractAsClassName(daoElement, BindDao.class, AnnotationAttributeType.VALUE);
			ClassName clazz = TypeUtility.className(derived);

			packageName = clazz.packageName();
			entityName = clazz.simpleName();

			String tableTemp = AnnotationUtility.extractAsClassName(daoElement, BindDao.class, AnnotationAttributeType.TABLE_NAME);
			if (StringUtils.hasText(tableTemp)) {
				tableName = tableTemp;
			}

			needToCreate = false;
		}

		M2MEntity entity = new M2MEntity(daoElement, packageName, entityName, TypeUtility.className(daoElement.asType().toString()), entity1, entity2, prefixId, tableName, needToCreate);

		return entity;
	}

	public String getQualifiedName() {
		if (StringUtils.hasText(packageName)) {
			return packageName+"."+name;
		}
		return name;
	}

	public String getSimpleName() {
		return name;
	}

}
