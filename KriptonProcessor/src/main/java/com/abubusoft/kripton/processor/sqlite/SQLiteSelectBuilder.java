package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import android.database.Cursor;

import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.JavaDocUtility;
import com.abubusoft.kripton.processor.core.reflect.MethodUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.sqlite.model.AnnotationAttributeType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.utils.LiteralType;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec.Builder;

/**
 * @author xcesco
 *
 *
 * @since 05/mag/2016
 */
public abstract class SQLiteSelectBuilder {

	public enum SelectResultType {
			LISTENER_BEAN(SelectBeanListenerHelper.class, true),
			LISTENER_CURSOR(SelectCursorListenerHelper.class, false),
			LIST_BEAN(SelectBeanListHelper.class, true),
			LIST_SCALAR(SelectScalarListHelper.class, false),
			CURSOR(SelectCursorHelper.class, false),
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

		public void generate(Elements elementUtils, SQLDaoDefinition daoDefinition, SQLEntity entity, Pair<String, List<SQLProperty>> fieldList, MethodSpec.Builder methodBuilder, SQLiteModelMethod method, TypeName returnType) {
			codeGenerator.generate(elementUtils, daoDefinition, entity, fieldList, methodBuilder, this.isMapFields(), method, returnType);

		}
	}

	public interface SelectCodeGenerator {
		void generate(Elements elementUtils, SQLDaoDefinition daoDefinition, SQLEntity entity, Pair<String, List<SQLProperty>> fieldList, MethodSpec.Builder methodBuilder, boolean mapFields, SQLiteModelMethod method, TypeName returnType);
	}

	/**
	 * 
	 * @param elementUtils
	 * @param builder
	 * @param model
	 * @param daoDefinition
	 * @param method
	 */
	public static void generate(Elements elementUtils, Builder builder, SQLiteDatabaseSchema model, SQLDaoDefinition daoDefinition, SQLiteModelMethod method) {
		SQLEntity entity = model.getEntity(daoDefinition.getEntityClassName());
		SelectResultType selectResultType = null;

		// if true, field must be associate to ben attributes
		TypeName returnType = typeName(method.getReturnClass());
		LiteralType readBeanListener=LiteralType.of(ReadBeanListener.class.getCanonicalName(), entity.getName());
		LiteralType readCursorListener=LiteralType.of(ReadCursorListener.class);

		if (TypeUtility.isTypeIncludedIn(returnType, Void.class, Void.TYPE)) {
			// return VOID (in the parameters must be a listener)
			if (MethodUtility.hasParameterOfType(method, readCursorListener)) {
				selectResultType = SelectResultType.LISTENER_CURSOR;
			} else if (MethodUtility.hasParameterOfType(method, readBeanListener)) {
				selectResultType = SelectResultType.LISTENER_BEAN;
			}

			if (selectResultType == null) {
				// error
			}

		} else if (TypeUtility.isTypeIncludedIn(returnType, Cursor.class)) {
			// return Cursor (no listener)
			selectResultType = SelectResultType.CURSOR;
		} else if (returnType instanceof ParameterizedTypeName) {
			ClassName listClazzName = ((ParameterizedTypeName) returnType).rawType;						

			if (TypeUtility.isTypeIncludedIn(listClazzName, List.class, Collection.class)) {
				// return List (no listener)
				List<TypeName> list = ((ParameterizedTypeName) returnType).typeArguments;

				if (list.size() == 1) {
					TypeName elementName = ((ParameterizedTypeName) returnType).typeArguments.get(0);
					if (TypeUtility.isSameType(list.get(0), entity.getName().toString())) {
						// entity list
						selectResultType = SelectResultType.LIST_BEAN;
					} else if (TypeUtility.isByteArray(elementName) || TypeUtility.isTypePrimitive(elementName) || TypeUtility.isTypeWrappedPrimitive(elementName) || TypeUtility.isTypeIncludedIn(elementName, String.class)) {
						// scalar list
						selectResultType = SelectResultType.LIST_SCALAR;
					}
				} else {
					// error
				}
			} else {
				// error
			}
		} else if (TypeUtility.isEquals(returnType, entity)) {
			// return one element (no listener)
			selectResultType = SelectResultType.BEAN;
		} else if (TypeUtility.isTypePrimitive(returnType) || TypeUtility.isTypeWrappedPrimitive(returnType) || TypeUtility.isTypeIncludedIn(returnType, String.class) || TypeUtility.isByteArray(returnType)) {
			// return single value string, int, long, short, double, float, String (no listener)
			selectResultType = SelectResultType.SCALAR;
		}
		
		if (selectResultType==null)
		{
			throw(new InvalidMethodSignException(daoDefinition, method));
		}

		// take field list
		Pair<String, List<SQLProperty>> fieldList = CodeBuilderUtility.generatePropertyList(elementUtils, model, daoDefinition, method, BindSelect.class, selectResultType.isMapFields(),  null);
		String fieldStatement = fieldList.value0;
		List<SQLProperty> fields = fieldList.value1;
		String tableStatement = model.classNameConverter.convert(daoDefinition.getEntitySimplyClassName());

		// separate params used for update bean and params used in whereCondition
		// analyze whereCondition
		ModelAnnotation annotation = method.getAnnotation(BindSelect.class);
		boolean distinctClause = Boolean.valueOf(annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_DISTINCT));

		SQLAnalyzer analyzer = new SQLAnalyzer();

		String whereSQL = annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_WHERE);
		analyzer.execute(elementUtils, daoDefinition, entity, method, whereSQL);
		String whereStatement = analyzer.getSQLStatement();
		List<String> whereParamGetters = analyzer.getParamGetters();
		List<String> whereParamNames = analyzer.getParamNames();

		String havingSQL = annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_HAVING);
		analyzer.execute(elementUtils, daoDefinition, entity, method, havingSQL);
		String havingStatement = analyzer.getSQLStatement();
		List<String> havingParamGetters = analyzer.getParamGetters();
		List<String> havingParamNames = analyzer.getParamNames();

		String groupBySQL = annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_GROUP_BY);
		analyzer.execute(elementUtils, daoDefinition, entity, method, groupBySQL);
		String groupByStatement = analyzer.getSQLStatement();
		List<String> groupByParamGetters = analyzer.getParamGetters();
		List<String> groupByParamNames = analyzer.getParamNames();

		String orderBySQL = annotation.getAttribute(AnnotationAttributeType.ATTRIBUTE_ORDER_BY);
		analyzer.execute(elementUtils, daoDefinition, entity, method, orderBySQL);
		String orderByStatement = analyzer.getSQLStatement();
		List<String> orderByParamGetters = analyzer.getParamGetters();
		List<String> orderByParamNames = analyzer.getParamNames();

		// select statement
		String sqlWithParameters = SelectStatementBuilder.create().distinct(distinctClause).fields(fieldStatement).table(tableStatement).where(whereSQL).having(havingSQL).groupBy(groupBySQL).orderBy(orderBySQL).build();
		String sql = SelectStatementBuilder.create().distinct(distinctClause).fields(fieldStatement).table(tableStatement).where(whereStatement).having(havingStatement).groupBy(groupByStatement).orderBy(orderByStatement).build();

		// select parameters
		List<String> paramNames = new ArrayList<String>();
		paramNames.addAll(whereParamNames);
		paramNames.addAll(havingParamNames);
		paramNames.addAll(groupByParamNames);
		paramNames.addAll(orderByParamNames);

		// select getter
		List<String> paramGetters = new ArrayList<String>();
		paramGetters.addAll(whereParamGetters);
		paramGetters.addAll(havingParamGetters);
		paramGetters.addAll(groupByParamGetters);
		paramGetters.addAll(orderByParamGetters);

		// generate method code
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName()).addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);

		JavaDocUtility.generateJavaDocForSelect(methodBuilder, sqlWithParameters, paramNames, method, annotation, fieldStatement);

		ParameterSpec parameterSpec;
		for (Pair<String, TypeMirror> item : method.getParameters()) {
			parameterSpec = ParameterSpec.builder(TypeName.get(item.value1), item.value0).build();
			methodBuilder.addParameter(parameterSpec);
		}
		methodBuilder.returns(returnType);

		// build where condition (common for every type of select)
		methodBuilder.addCode("// build where condition\n");
		methodBuilder.addCode("String[] args={");
		{
			String separator = "";

			for (String item : paramGetters) {
				methodBuilder.addCode(separator);
				methodBuilder.addCode("String.valueOf($L)", item);

				separator = ", ";
			}
			methodBuilder.addCode("};\n");
		}

		methodBuilder.addCode("\n");
		methodBuilder.addCode("$T cursor = database.rawQuery(\"$L\", args);\n", Cursor.class, sql);

		selectResultType.generate(elementUtils, daoDefinition, entity, fieldList, methodBuilder, method, returnType);

		/*} else {
			throw (new InvalidReturnTypeException(daoDefinition, method, typeName(method.getReturnClass()), typeName(Cursor.class)));
		}*/

		MethodSpec methodSpec = methodBuilder.build();

		builder.addMethod(methodSpec);
	}

	static boolean isIn(TypeName value, Class<?>... classes) {
		for (Class<?> item : classes) {
			if (value.toString().equals(TypeName.get(item).toString())) {
				return true;
			}
		}

		return false;
	}
}
