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
import com.squareup.javapoet.TypeSpec.Builder;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 *
 * @since 05/mag/2016
 */
public abstract class SqlSelectBuilder {

	public interface SelectCodeGenerator {
		void generate(Elements elementUtils, Builder builder, boolean mapFields, SQLiteModelMethod method, TypeMirror returnType);

		void setSelectResultTye(SelectResultType selectResultType);
	}

	public enum SelectResultType {
			BEAN(SelectBeanHelper.class, true),
			CURSOR(SelectRawHelper.class, false),
			LIST_BEAN(SelectBeanListHelper.class, true),
			LIST_SCALAR(SelectScalarListHelper.class, false),
			LISTENER_BEAN(SelectBeanListenerHelper.class, true),
			LISTENER_CURSOR(SelectRawListenerHelper.class, false),
			PAGED_RESULT(SelectPagedResultHelper.class, true),
			SCALAR(SelectScalarHelper.class, false);

		private SelectCodeGenerator codeGenerator;

		private boolean mapFields;

		private SelectResultType(Class<? extends AbstractSelectCodeGenerator> codeGenerator, boolean mapFields) {
			try {
				this.mapFields = mapFields;
				this.codeGenerator = codeGenerator.newInstance();
				this.codeGenerator.setSelectResultTye(this);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		public void generate(Elements elementUtils, Builder builder, SQLiteModelMethod method, TypeMirror returnType) {
			codeGenerator.generate(elementUtils, builder, this.isMapFields(), method, returnType);

		}

		/**
		 * @return the codeGenerator
		 */
		public SelectCodeGenerator getCodeGenerator() {
			return codeGenerator;
		}

		/**
		 * if true, map cursor fields to bean attributes.
		 * 
		 * @return the mapFields
		 */
		public boolean isMapFields() {
			return mapFields;
		}
	}

}
