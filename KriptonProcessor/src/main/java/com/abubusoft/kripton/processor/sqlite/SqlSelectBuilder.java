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
package com.abubusoft.kripton.processor.sqlite;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.MethodSpec;

/**
 * @author xcesco
 *
 *
 * @since 05/mag/2016
 */
public abstract class SqlSelectBuilder {

	public enum SelectResultType {
			LISTENER_BEAN(SelectBeanListenerHelper.class, true),
			LISTENER_CURSOR(SelectRawListenerHelper.class, false),
			LIST_BEAN(SelectBeanListHelper.class, true),
			LIST_SCALAR(SelectScalarListHelper.class, false),
			CURSOR(SelectRawHelper.class, false),
			BEAN(SelectBeanHelper.class, true),
			SCALAR(SelectScalarHelper.class, false);

		private SelectCodeGenerator codeGenerator;

		private boolean mapFields;

		/**
		 * if true, map cursor fields to bean attributes.
		 * 
		 * @return the mapFields
		 */
		public boolean isMapFields() {
			return mapFields;
		}

		/**
		 * @return the codeGenerator
		 */
		public SelectCodeGenerator getCodeGenerator() {
			return codeGenerator;
		}

		private SelectResultType(Class<? extends SelectCodeGenerator> codeGenerator, boolean mapFields) {
			try {
				this.mapFields = mapFields;
				this.codeGenerator = codeGenerator.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		public void generate(Elements elementUtils, PropertyList fieldList, MethodSpec.Builder methodBuilder, SQLiteModelMethod method, TypeMirror returnType) {
			codeGenerator.generate(elementUtils, fieldList, methodBuilder, this.isMapFields(), method, returnType);

		}
	}

	public interface SelectCodeGenerator {
		void generate(Elements elementUtils, PropertyList fieldList, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeMirror returnType);
	}

}
