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
package com.abubusoft.kripton.processor.sqlite;

import java.util.Collection;

import com.abubusoft.kripton.android.annotation.BindSqlPageSize;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.PaginatedResult;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.KriptonClassNotFoundException;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import android.database.Cursor;

// TODO: Auto-generated Javadoc
/**
 * The Class SelectBuilderUtility.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @since 05/mag/2016
 */
public abstract class SelectBuilderUtility {

	/**
	 * The Interface SelectCodeGenerator.
	 */
	public interface SelectCodeGenerator {
		
		/**
		 * Generate.
		 *
		 * @param builder the builder
		 * @param mapFields the map fields
		 * @param method the method
		 */
		void generate(TypeSpec.Builder builder, boolean mapFields, SQLiteModelMethod method);
		
		/**
		 * Generate live data.
		 *
		 * @param classBuilder the class builder
		 * @param method the method
		 */
		void generateLiveData(TypeSpec.Builder classBuilder, SQLiteModelMethod method);

		/**
		 * Sets the select result tye.
		 *
		 * @param selectResultType the new select result tye
		 */
		void setSelectResultTye(SelectType selectResultType);
	}

	/**
	 * The Enum SelectType.
	 */
	public enum SelectType {
		
		/** The bean. */
		BEAN(SelectBeanHelper.class, true), 
		
		/** The cursor. */
		CURSOR(SelectRawHelper.class, false),
		
		/** The list bean. */
		LIST_BEAN(SelectBeanListHelper.class, true), 
		
		/** The list scalar. */
		LIST_SCALAR(SelectScalarListHelper.class, false), 
		
		/** The listener bean. */
		LISTENER_BEAN(SelectBeanListenerHelper.class, true), 
		
		/** The listener cursor. */
		LISTENER_CURSOR(SelectRawListenerHelper.class, false), 
		
		/** The paged result. */
		PAGED_RESULT(SelectPaginatedResultHelper.class, true), 
		
		/** The scalar. */
		SCALAR(SelectScalarHelper.class, false);		

		/** The code generator. */
		private SelectCodeGenerator codeGenerator;

		/** The map fields. */
		private boolean mapFields;

		/**
		 * Instantiates a new select type.
		 *
		 * @param codeGenerator the code generator
		 * @param mapFields the map fields
		 */
		private SelectType(Class<? extends AbstractSelectCodeGenerator> codeGenerator, boolean mapFields) {
			try {
				this.mapFields = mapFields;
				this.codeGenerator = codeGenerator.newInstance();
				this.codeGenerator.setSelectResultTye(this);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Generate.
		 *
		 * @param builder the builder
		 * @param method the method
		 */
		public void generate(TypeSpec.Builder builder, SQLiteModelMethod method) {
			codeGenerator.generate(builder, this.isMapFields(), method);
		}

		/**
		 * Checks if is map fields.
		 *
		 * @return the codeGenerator
		 */
		// public SelectCodeGenerator getCodeGenerator() {
		// return codeGenerator;
		// }

		/**
		 * if true, map cursor fields to bean attributes.
		 * 
		 * @return the mapFields
		 */
		public boolean isMapFields() {
			return mapFields;
		}

		/**
		 * Generate live data.
		 *
		 * @param builder the builder
		 * @param method the method
		 */
		public void generateLiveData(Builder builder, SQLiteModelMethod method) {
			codeGenerator.generateLiveData(builder, method);			
		}
	}
	
	/**
	 * Detect select type
	 * @param method
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static SelectType detectSelectType(SQLiteModelMethod method) {
		SQLiteDaoDefinition daoDefinition = method.getParent();
		SQLiteEntity entity = daoDefinition.getEntity();

		SelectBuilderUtility.SelectType selectResultType = null;

		// if true, field must be associate to ben attributes
		TypeName returnTypeName = method.getReturnClass();

		ParameterizedTypeName readBeanListener = ParameterizedTypeName.get(ClassName.get(OnReadBeanListener.class), ClassName.get(entity.getElement()));
		ClassName readCursorListener = ClassName.get(OnReadCursorListener.class);

		ModelAnnotation annotation = method.getAnnotation(BindSqlSelect.class);
		int pageSize = annotation.getAttributeAsInt(AnnotationAttributeType.PAGE_SIZE);

		AssertKripton.failWithInvalidMethodSignException(pageSize < 0, method, "@%s#%s must be set with positive number", BindSqlSelect.class.getSimpleName(), AnnotationAttributeType.PAGE_SIZE.getValue());
		AssertKripton.failWithInvalidMethodSignException(pageSize > 0 && method.hasDynamicPageSizeConditions(), method, "can not define @%s#%s and mark a method parameter with @%s ",
				BindSqlSelect.class.getSimpleName(), BindSqlPageSize.class.getSimpleName(), AnnotationAttributeType.PAGE_SIZE.getValue());							

		if (TypeUtility.isTypeIncludedIn(returnTypeName, Void.class, Void.TYPE)) {
			// return VOID (in the parameters must be a listener)
			if (SqlBuilderHelper.hasParameterOfType(method, readCursorListener)) {
				selectResultType = SelectBuilderUtility.SelectType.LISTENER_CURSOR;
			} else if (SqlBuilderHelper.hasParameterOfType(method, readBeanListener)) {
				selectResultType = SelectBuilderUtility.SelectType.LISTENER_BEAN;
			}
		} else if (TypeUtility.isTypeIncludedIn(returnTypeName, Cursor.class)) {
			// return Cursor (no listener)
			selectResultType = SelectBuilderUtility.SelectType.CURSOR;
		} else if (returnTypeName instanceof ParameterizedTypeName) {
			ParameterizedTypeName returnParameterizedTypeName = (ParameterizedTypeName) returnTypeName;
			ClassName returnParameterizedClassName = returnParameterizedTypeName.rawType;

			// return List (no listener)
			AssertKripton.assertTrueOrInvalidMethodSignException(returnParameterizedTypeName.typeArguments.size() == 1, method, "return type %s is not supported", returnTypeName);
			TypeName elementName = returnParameterizedTypeName.typeArguments.get(0);

			Class<?> wrapperClazz=null;
			try {
				wrapperClazz = Class.forName(returnParameterizedClassName.toString());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw(new KriptonClassNotFoundException(e));
			}
			if (PaginatedResult.class.isAssignableFrom(wrapperClazz)) {
				// method must have pageSize, statically or dynamically
				// defined
				AssertKripton.assertTrueOrInvalidMethodSignException(method.hasDynamicPageSizeConditions() || pageSize > 0, method,
						"use of PaginatedResult requires 'pageSize' attribute or a @%s annotated parameter", returnTypeName, BindSqlPageSize.class.getSimpleName());

				// paged result
				AssertKripton.assertTrueOrInvalidMethodSignException(TypeUtility.isEquals(elementName, entity.getName().toString()), method, "return type %s is not supported", returnTypeName);
				selectResultType = SelectBuilderUtility.SelectType.PAGED_RESULT;
				// set typeName of paginatedResult
				method.paginatedResultName = "paginatedResult";
			} else if (Collection.class.isAssignableFrom(wrapperClazz)) {
				if (TypeUtility.isEquals(elementName, entity.getName().toString())) {
					// entity list
					selectResultType = SelectBuilderUtility.SelectType.LIST_BEAN;
				} else if (SQLTransformer.isSupportedJDKType(elementName) || TypeUtility.isByteArray(elementName)) {
					// scalar list
					selectResultType = SelectBuilderUtility.SelectType.LIST_SCALAR;
				} else {
					AssertKripton.failWithInvalidMethodSignException(true, method, "%s is invalid return type", method.getReturnClass());
				}

			}
		} else if (TypeUtility.isEquals(returnTypeName, entity)) {
			// return one element (no listener)
			selectResultType = SelectBuilderUtility.SelectType.BEAN;
		} else if (SQLTransformer.isSupportedJDKType(returnTypeName) || TypeUtility.isByteArray(returnTypeName)) {
			// return single value string, int, long, short, double, float,
			// String (no listener)
			selectResultType = SelectBuilderUtility.SelectType.SCALAR;
		}

		AssertKripton.assertTrueOrInvalidMethodSignException(selectResultType != null, method, "'%s' as return type is not supported", returnTypeName);

		return selectResultType;
	}

}
