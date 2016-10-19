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
public abstract class SQLiteSelectBuilder {

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
