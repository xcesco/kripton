/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.sqlite.model;

import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlPageSize;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.TypeName;

public class SQLiteModelMethod extends ModelMethod implements SQLiteModelElement {

	interface OnFoundDynamicParameter {
		void onFoundParameter(String parameterName);
	}

	/**
	 * <p>
	 * It is the typeName of parameter used to dynamic order by condition (defined at
	 * runtime).
	 * </p>
	 */
	public String dynamicOrderByParameterName;

	/**
	 * <p>
	 * It is the typeName of parameter used to dynamic page size (defined at
	 * runtime).
	 * </p>
	 */
	public String dynamicPageSizeName;
	
	/**
	 * typeName of the paginated result parameter typeName.
	 */
	public String paginatedResultName;

	protected Map<String, String> parameterAlias2NameField;

	protected Map<String, String> parameterNameField2Alias;

	private WeakReference<SQLDaoDefinition> parent;

	public MethodInfo info;

	public SQLiteModelMethod(SQLDaoDefinition parent, ExecutableElement element) {
		super(element);
		this.parent = new WeakReference<SQLDaoDefinition>(parent);
		this.parameterAlias2NameField = new HashMap<>();
		this.parameterNameField2Alias = new HashMap<>();
		this.info=new MethodInfo();

		// analyze method looking for BindSqlParam
		for (VariableElement p : element.getParameters()) {
			BindSqlParam paramAlias = p.getAnnotation(BindSqlParam.class);
			if (paramAlias != null && StringUtils.hasText(paramAlias.value())) {
				String alias = paramAlias.value();
				parameterAlias2NameField.put(alias, p.getSimpleName().toString());
				parameterNameField2Alias.put(p.getSimpleName().toString(), alias);
			}
		}
		
		// looks for dynamic where conditions
		findStringDynamicStatement(parent, BindSqlDynamicWhere.class, unsupportedSQLForDynamicWhere, new OnFoundDynamicParameter() {

			@Override
			public void onFoundParameter(String parameterName) {
				info.dynamicWhereParameterName = parameterName;
			}

		});

		// looks for dynamic orderBy conditions
		findStringDynamicStatement(parent, BindSqlOrderBy.class, unsupportedSQLForDynamicOrderBy, new OnFoundDynamicParameter() {

			@Override
			public void onFoundParameter(String parameterName) {
				dynamicOrderByParameterName = parameterName;
			}

		});
		
		// looks for dynamic pageSize
		findIntDynamicStatement(parent, BindSqlPageSize.class, unsupportedSQLForDynamicOrderBy, new OnFoundDynamicParameter() {

			@Override
			public void onFoundParameter(String parameterName) {
				dynamicPageSizeName = parameterName;
			}

		});
	}

	@Override
	public void accept(SQLiteModelElementVisitor visitor) throws Exception {
		visitor.visit(this);
	}

	static List<Class<? extends Annotation>> unsupportedSQLForDynamicWhere = new ArrayList<>();

	static List<Class<? extends Annotation>> unsupportedSQLForDynamicOrderBy = new ArrayList<>();

	static {
		unsupportedSQLForDynamicWhere.add(BindSqlInsert.class);

		unsupportedSQLForDynamicOrderBy.add(BindSqlInsert.class);
		unsupportedSQLForDynamicOrderBy.add(BindSqlUpdate.class);
		unsupportedSQLForDynamicOrderBy.add(BindSqlDelete.class);
	}

	/**
	 * Look for a method parameter which is annotated with an annotationClass
	 * annotation. When it is found, a client action is required through
	 * listener.
	 * 
	 * @param parent
	 * @param element
	 */
	private <A extends Annotation> void findStringDynamicStatement(SQLDaoDefinition parent, Class<A> annotationClazz, List<Class<? extends Annotation>> unsupportedQueryType,
			OnFoundDynamicParameter listener) {
							
		int counter = 0;
		for (VariableElement p : element.getParameters()) {
			A annotation = p.getAnnotation(annotationClazz);
			if (annotation != null) {
				// Dynamic queries can not be used in Inser SQL.
				for (Class<? extends Annotation> item : unsupportedQueryType) {
					AssertKripton.assertTrueOrInvalidMethodSignException(element.getAnnotation(item) == null, this, "in this method is not allowed to mark parameters with @%s annotation.", annotationClazz.getSimpleName());
				}
				
				AssertKripton.assertTrueOrInvalidMethodSignException(TypeUtility.isString(TypeUtility.typeName(p)), this, "only String parameters can be marked with @%s annotation.", annotationClazz.getSimpleName());
				
				listener.onFoundParameter(p.getSimpleName().toString());
				counter++;
			}
		}
		AssertKripton.assertTrueOrInvalidMethodSignException(counter < 2, this, "there are %s parameters marked with @%s. Only one is allowed.", counter, annotationClazz.getSimpleName());
	}
	
	/**
	 * Look for a method parameter which is annotated with an annotationClass
	 * annotation. When it is found, a client action is required through
	 * listener.
	 * 
	 * @param parent
	 * @param element
	 */
	private <A extends Annotation> void findIntDynamicStatement(SQLDaoDefinition parent, Class<A> annotationClazz, List<Class<? extends Annotation>> unsupportedQueryType,
			OnFoundDynamicParameter listener) {
							
		int counter = 0;
		for (VariableElement p : element.getParameters()) {
			A annotation = p.getAnnotation(annotationClazz);
			if (annotation != null) {
				// Dynamic queries can not be used in Inser SQL.
				for (Class<? extends Annotation> item : unsupportedQueryType) {
					AssertKripton.assertTrueOrInvalidMethodSignException(element.getAnnotation(item) == null, this, "in this method is not allowed to mark parameters with @%s annotation.", annotationClazz.getSimpleName());
				}
				
				AssertKripton.assertTrueOrInvalidMethodSignException(TypeUtility.isTypeIncludedIn(TypeUtility.typeName(p), Integer.TYPE), this, "only a int parameter can be marked with @%s annotation.", annotationClazz.getSimpleName());
				
				listener.onFoundParameter(p.getSimpleName().toString());
				counter++;
			}
		}
		AssertKripton.assertTrueOrInvalidMethodSignException(counter < 2, this, "there are %s parameters marked with @%s. Only one is allowed.", counter, annotationClazz.getSimpleName());
	}

	/**
	 * Retrieve for a method's parameter its alias, used to work with queries.
	 * If no alias is present, typeName will be used.
	 * 
	 * @param typeName
	 * @return
	 */
	public String findParameterAliasByName(String name) {
		if (parameterNameField2Alias.containsKey(name)) {
			return parameterNameField2Alias.get(name);
		}

		return name;
	}

	/**
	 * Check if method contains a parameter with value as typeName
	 * 
	 * @param nameOrAlias
	 *            parameter typeName to find
	 * @return TypeMirror associated
	 */
	public String findParameterNameByAlias(String nameOrAlias) {
		String[] arrays = nameOrAlias.split("\\.");
		String prefix = "";

		if (arrays.length == 2) {
			nameOrAlias = arrays[0];
			prefix = "." + arrays[1];

		}

		if (parameterAlias2NameField.containsKey(nameOrAlias)) {
			return parameterAlias2NameField.get(nameOrAlias) + prefix;
		}

		return nameOrAlias + prefix;
	}

	/**
	 * Check if method contains a parameter with value as typeName
	 * 
	 * @param typeName
	 *            parameter typeName to find
	 * @return TypeMirror associated
	 */
	public TypeName findParameterTypeByAliasOrName(String name) {
		if (parameterAlias2NameField.containsKey(name)) {
			return findParameterType(parameterAlias2NameField.get(name));
		}

		return findParameterType(name);
	}

	/**
	 * @return the parent
	 */
	public SQLDaoDefinition getParent() {
		return parent.get();
	}

	public boolean hasDynamicOrderByConditions() {
		return StringUtils.hasText(dynamicOrderByParameterName);
	}
	
	public boolean hasDynamicPageSizeConditions() {
		return StringUtils.hasText(dynamicPageSizeName);
	}
	
	public boolean isThisDynamicWhereConditionsName(String parameterName) {
		return StringUtils.hasText(info.dynamicWhereParameterName) && parameterName.equals(info.dynamicWhereParameterName);
	}
	
	public boolean isThisDynamicPageSizeName(String parameterName) {
		return StringUtils.hasText(dynamicPageSizeName) && parameterName.equals(dynamicPageSizeName);
	}

	public boolean hasPaginatedResultParameter() {
		return StringUtils.hasText(paginatedResultName);
	}

}
