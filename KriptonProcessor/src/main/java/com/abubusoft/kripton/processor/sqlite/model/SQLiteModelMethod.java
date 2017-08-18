/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere.PrependType;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereArgs;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlPageSize;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.BindDataSourceSubProcessor;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.IncompatibleAnnotationException;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLBuilder;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker.JQLParameterName;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.ContentUriChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.ContentUriChecker.UriPlaceHolderReplacerListener;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.ContentUriPlaceHolder;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.TypeName;

public class SQLiteModelMethod extends ModelMethod implements SQLiteModelElement {

	interface OnFoundDynamicParameter {
		void onFoundParameter(String parameterName);
	}

	/**
	 * <p>
	 * It is the typeName of parameter used to dynamic order by condition
	 * (defined at runtime).
	 * </p>
	 */
	public String dynamicOrderByParameterName;

	/**
	 * <p>
	 * It is the typeName of parameter used to dynamic where condition (defined
	 * at runtime).
	 * </p>
	 */
	public String dynamicWhereParameterName;

	/**
	 * <p>
	 * It's the name of the parameter used to define arguments for dynamic where
	 * statement. It can be used only on String[] parameter type.
	 * </p>
	 */
	public String dynamicWhereArgsParameterName;

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

	public final JQL jql;

	public boolean contentProviderEntryPathEnabled;

	/**
	 * it's the path defined in @ContentProviderEntry.path
	 */
	public String contentProviderEntryPath;

	/**
	 * name of method generated for content provider
	 */
	public String contentProviderMethodName;

	public String parameterBeanName;

	public List<ContentUriPlaceHolder> contentProviderUriVariables;

	/**
	 * <p>
	 * It's the uri with place holder replaced with <code>#</code> or
	 * <code>*</code>. An example:
	 * </p>
	 * 
	 * <pre>
	 * content://sqlite.contentprovider.kripton35/persons/#/children
	 * </pre>
	 */
	public String contentProviderUriTemplate;

	public String contentProviderEntryPathTemplate;

	public PrependType dynamicWherePrepend;

	public SQLiteModelMethod(SQLDaoDefinition parent, ExecutableElement element, List<ModelAnnotation> annotationList) {
		super(element);

		// before proceed convert typevariable in right typename
		parent.resolveTypeVariable(this);

		this.parent = new WeakReference<SQLDaoDefinition>(parent);

		// detect type of operation

		if (annotationList != null) {
			this.annotations.addAll(annotationList);
		}

		this.parameterAlias2NameField = new HashMap<>();
		this.parameterNameField2Alias = new HashMap<>();

		// analyze method looking for BindSqlParam
		for (VariableElement p : element.getParameters()) {
			BindSqlParam paramAlias = p.getAnnotation(BindSqlParam.class);
			if (paramAlias != null && StringUtils.hasText(paramAlias.value())) {
				String alias = paramAlias.value();
				parameterAlias2NameField.put(alias, p.getSimpleName().toString());
				parameterNameField2Alias.put(p.getSimpleName().toString(), alias);
			}

			if (TypeUtility.isEquals(TypeUtility.typeName(p.asType()), parent.getEntityClassName())) {
				this.parameterBeanName = p.getSimpleName().toString();
			}

			BindSqlDynamicWhere paramDynamicWhereName = p.getAnnotation(BindSqlDynamicWhere.class);
			if (paramDynamicWhereName != null) {
				this.dynamicWhereParameterName = p.getSimpleName().toString();
				PrependType prepend = PrependType
						.valueOf(AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, p, BindSqlDynamicWhere.class, AnnotationAttributeType.PREPEND));
				this.dynamicWherePrepend = prepend;

				// CONSTRAINT: @BindSqlWhere can be used only on String
				// parameter type
				AssertKripton.assertTrueOrInvalidTypeForAnnotationMethodParameterException(TypeUtility.isEquals(TypeUtility.typeName(String.class), TypeUtility.typeName(p.asType())),
						getParent().getElement(), getElement(), p, BindSqlDynamicWhere.class);
			}

			BindSqlDynamicWhereArgs paramDynamicWhereArgsName = p.getAnnotation(BindSqlDynamicWhereArgs.class);
			if (paramDynamicWhereArgsName != null) {

				this.dynamicWhereArgsParameterName = p.getSimpleName().toString();

				// only String[] parameter can be marked as dynamicWhereArgs
				// CONSTRAINT: @BindSqlWhereArgs can be used only on
				// String[] parameter type
				AssertKripton.assertTrueOrInvalidTypeForAnnotationMethodParameterException(TypeUtility.isEquals(ArrayTypeName.of(String.class), TypeUtility.typeName(p.asType())),
						getParent().getElement(), getElement(), p, BindSqlDynamicWhereArgs.class);
			}
		}

		// looks for dynamic where conditions
		findStringDynamicStatement(parent, BindSqlDynamicWhere.class, unsupportedSQLForDynamicWhere, new OnFoundDynamicParameter() {

			@Override
			public void onFoundParameter(String parameterName) {
				dynamicWhereParameterName = parameterName;
			}

		});

		// looks for dynamic orderBy conditions
		findStringDynamicStatement(parent, BindSqlDynamicOrderBy.class, unsupportedSQLForDynamicOrderBy, new OnFoundDynamicParameter() {

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

		// check if we have jql annotation attribute
		String preparedJql = getJQLDeclared();

		this.jql = JQLBuilder.buildJQL(this, preparedJql);
		//

		BindContentProviderEntry annotation = element.getAnnotation(BindContentProviderEntry.class);
		if (annotation != null) {
			// manage content provider generation
			String methodPath = "";
			if (StringUtils.hasText(annotation.path())) {
				methodPath = annotation.path();
			}

			AssertKripton.assertTrue(!this.hasDynamicPageSizeConditions(), "Method %s.%s can not be marked with @%s annotation and contains parameter with @%s annotation", getParent().getName(),
					getName(), BindContentProviderEntry.class.getSimpleName(), BindSqlPageSize.class.getSimpleName(), IncompatibleAnnotationException.class);

			this.contentProviderEntryPathEnabled = true;
			this.contentProviderEntryPath = methodPath;
			this.contentProviderMethodName = getElement().getSimpleName().toString() + parent.contentProviderCounter;

			parent.contentProviderCounter++;

			final SQLEntity entity = this.getParent().getEntity();

			String contentProviderUri = contentProviderUri();

			AssertKripton.assertTrueOrInvalidMethodSignException(!contentProviderUri.endsWith("/"), this, " content provider URI '%s' can not finish with '/'", contentProviderUri);
			AssertKripton.assertTrueOrInvalidMethodSignException(!this.contentProviderPath().contains("//"), this, " content provider URI '%s' can not contain with '//'", contentProviderUri);

			List<ContentUriPlaceHolder> uriParams = ContentUriChecker.getInstance().extract(contentProviderUri);
			String uriTemplate = ContentUriChecker.getInstance().replace(contentProviderUri(), new UriPlaceHolderReplacerListener() {

				@Override
				public String onParameterName(int pathSegmentIndex, String name) {
					JQLParameterName pName = JQLParameterName.parse(name);

					String propertyName = pName.getValue();

					SQLProperty entityProperty = entity.get(propertyName);
					TypeName methodParamTypeName = SQLiteModelMethod.this.findParameterTypeByAliasOrName(name);

					if (entityProperty != null) {
						TypeName entityPropertyType = entityProperty.getPropertyType().getTypeName();
						if (TypeUtility.isString(entityPropertyType)) {
							return "*";
						} else if (TypeUtility.isTypeIncludedIn(entityPropertyType, Long.class, Long.TYPE)) {
							return "#";
						} else {
							AssertKripton.fail("In '%s.%s', parameter '%s' has an invalid type '%s' to be used in path content provider path '%s'", getParent().getName(), getName(), name,
									entityPropertyType, contentProviderUri());
						}
					} else if (methodParamTypeName != null) {
						if (methodParamTypeName != null && TypeUtility.isTypeIncludedIn(methodParamTypeName, String.class)) {
							return "*";
						} else if (methodParamTypeName != null && TypeUtility.isTypeIncludedIn(methodParamTypeName, Long.class, Long.TYPE)) {
							return "#";
						} else {
							AssertKripton.fail("In '%s.%s', parameter '%s' has an invalid type '%s' to be used in path content provider path '%s'", getParent().getName(), getName(), name,
									methodParamTypeName, contentProviderUri());
						}

					} else {
						AssertKripton.fail("Invalid parameter '%s' is used in content provider path '%s' associated to method '%s.%s'", name, contentProviderUri(), getParent().getName(), getName());
					}
					return null;

				}
			});

			this.contentProviderUriVariables = uriParams;
			this.contentProviderUriTemplate = uriTemplate;

			// if we have a path, we have to remove the initial /
			this.contentProviderEntryPathTemplate = uriTemplate.substring(getParent().getParent().contentProviderUri().length());
			if (this.contentProviderEntryPathTemplate.startsWith("/"))
				contentProviderEntryPathTemplate = this.contentProviderEntryPathTemplate.substring(1);

			// INSERT from SELECT type SQL can not be used with content provider
			AssertKripton.assertTrueOrInvalidMethodSignException(!(this.jql.operationType == JQLType.INSERT && this.jql.containsSelectOperation), this,
					" INSERT-FROM-SELECT sql can not be used for content provider");

			// UPDATE from SELECT type SQL can not be used with content provider
			AssertKripton.assertTrueOrInvalidMethodSignException(!(this.jql.operationType == JQLType.UPDATE && this.jql.containsSelectOperation), this,
					" UPDATE-FROM-SELECT sql can not be used for content provider");

		}

	}

	private String getJQLDeclared() {
		ModelAnnotation inserAnnotation = this.getAnnotation(BindSqlInsert.class);
		ModelAnnotation updateAnnotation = this.getAnnotation(BindSqlUpdate.class);
		ModelAnnotation selectAnnotation = this.getAnnotation(BindSqlSelect.class);
		ModelAnnotation deleteAnnotation = this.getAnnotation(BindSqlDelete.class);

		String jql = null;
		int counter = 0;

		if (selectAnnotation != null) {
			jql = selectAnnotation.getAttribute(AnnotationAttributeType.JQL);
			if (StringUtils.hasText(jql)) {
				counter++;

				AssertKripton.assertTrue(selectAnnotation.getAttributeCount() > 1, "Annotation %s in method %s.%s have more than one annotation with JQL attribute", selectAnnotation.getSimpleName(),
						this.getParent().getName(), this.getName());
			}
		}

		if (inserAnnotation != null) {
			jql = inserAnnotation.getAttribute(AnnotationAttributeType.JQL);
			if (StringUtils.hasText(jql)) {
				counter++;

				AssertKripton.assertTrue(inserAnnotation.getAttributeCount() > 1, "Annotation %s in method %s.%s have more than one annotation with JQL attribute", inserAnnotation.getSimpleName(),
						this.getParent().getName(), this.getName());
			}
		}

		if (updateAnnotation != null) {
			jql = updateAnnotation.getAttribute(AnnotationAttributeType.JQL);
			if (StringUtils.hasText(jql)) {
				counter++;

				AssertKripton.assertTrue(updateAnnotation.getAttributeCount() > 1, "Annotation %s in method %s.%s have more than one annotation with JQL attribute", updateAnnotation.getSimpleName(),
						this.getParent().getName(), this.getName());
			}
		}

		if (deleteAnnotation != null) {
			jql = deleteAnnotation.getAttribute(AnnotationAttributeType.JQL);
			if (StringUtils.hasText(jql)) {
				counter++;

				AssertKripton.assertTrue(deleteAnnotation.getAttributeCount() > 1, "Annotation %s in method %s.%s have more than one annotation with JQL attribute", deleteAnnotation.getSimpleName(),
						this.getParent().getName(), this.getName());
			}
		}

		AssertKripton.assertTrue(counter <= 1, "Method %s.%s have more than one annotation with JQL attribute", this.getParent().getName(), this.getName());

		return jql;

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
					AssertKripton.assertTrueOrInvalidMethodSignException(element.getAnnotation(item) == null, this, "in this method is not allowed to mark parameters with @%s annotation.",
							annotationClazz.getSimpleName());
				}

				AssertKripton.assertTrueOrInvalidMethodSignException(TypeUtility.isString(TypeUtility.typeName(p)), this, "only String parameters can be marked with @%s annotation.",
						annotationClazz.getSimpleName());

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
					AssertKripton.assertTrueOrInvalidMethodSignException(element.getAnnotation(item) == null, this, "in this method is not allowed to mark parameters with @%s annotation.",
							annotationClazz.getSimpleName());
				}

				AssertKripton.assertTrueOrInvalidMethodSignException(TypeUtility.isTypeIncludedIn(TypeUtility.typeName(p), Integer.TYPE), this,
						"only a int parameter can be marked with @%s annotation.", annotationClazz.getSimpleName());

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

	public boolean hasDynamicWhereConditions() {
		return StringUtils.hasText(dynamicWhereParameterName);
	}

	public boolean hasDynamicWhereArgs() {
		return StringUtils.hasText(dynamicWhereArgsParameterName);
	}

	public boolean hasDynamicPageSizeConditions() {
		return StringUtils.hasText(dynamicPageSizeName);
	}

	public boolean isThisDynamicWhereConditionsName(String parameterName) {
		return StringUtils.hasText(dynamicWhereParameterName) && parameterName.equals(dynamicWhereParameterName);
	}

	public boolean isThisDynamicPageSizeName(String parameterName) {
		return StringUtils.hasText(dynamicPageSizeName) && parameterName.equals(dynamicPageSizeName);
	}

	public boolean isThisDynamicWhereArgsName(String parameterName) {
		return StringUtils.hasText(dynamicWhereArgsParameterName) && parameterName.equals(dynamicWhereArgsParameterName);
	}

	public boolean hasPaginatedResultParameter() {
		return StringUtils.hasText(paginatedResultName);
	}

	public String contentProviderUri() {
		if (!contentProviderEntryPathEnabled)
			return "";

		return this.getParent().contentProviderUri() + (StringUtils.hasText(contentProviderEntryPath) ? ("/" + contentProviderEntryPath) : "");
	}

	public String contentProviderPath() {
		if (!contentProviderEntryPathEnabled)
			return "";

		return (StringUtils.hasText(contentProviderEntryPath) ? ("/" + contentProviderEntryPath) : "");
	}

}
