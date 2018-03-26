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

import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 *
 * @since 05/mag/2016
 */
public abstract class SelectBuilderUtility {

	public interface SelectCodeGenerator {
		void generate(TypeSpec.Builder builder, boolean mapFields, SQLiteModelMethod method, TypeName returnType);

		void setSelectResultTye(SelectType selectResultType);
	}

	public enum SelectType {
		BEAN(SelectBeanHelper.class, true), 
		CURSOR(SelectRawHelper.class, false),
		LIST_BEAN(SelectBeanListHelper.class, true), 
		LIST_SCALAR(SelectScalarListHelper.class, false), 
		LISTENER_BEAN(SelectBeanListenerHelper.class, true), 
		LISTENER_CURSOR(SelectRawListenerHelper.class, false), 
		PAGED_RESULT(SelectPaginatedResultHelper.class, true), 
		SCALAR(SelectScalarHelper.class, false);		

		private SelectCodeGenerator codeGenerator;

		private boolean mapFields;

		private SelectType(Class<? extends AbstractSelectCodeGenerator> codeGenerator, boolean mapFields) {
			try {
				this.mapFields = mapFields;
				this.codeGenerator = codeGenerator.newInstance();
				this.codeGenerator.setSelectResultTye(this);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		public void generate(TypeSpec.Builder builder, SQLiteModelMethod method, TypeName returnType) {
			codeGenerator.generate(builder, this.isMapFields(), method, returnType);
		}

		/**
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
	}

}
