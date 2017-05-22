package com.abubusoft.kripton.processor.sqlite.grammar;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.lang.model.element.VariableElement;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.BindDataSourceSubProcessor;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;

public abstract class JQLBuilder {

	public static String buildJQL(SQLiteModelMethod method) {		
		if (method.hasAnnotation(BindSqlSelect.class)) {
			return buildJQLSelect(method);
		} else if (method.hasAnnotation(BindSqlInsert.class)) {
			return buildJQLInsert(method);
		} else if (method.hasAnnotation(BindSqlUpdate.class)) {
			return buildJQLUpdate(method);
		} else if (method.hasAnnotation(BindSqlDelete.class)) {
			return buildJQLDelete(method);
		}
		
		return null;
	}

	
	private static String buildJQLSelect(SQLiteModelMethod method) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String buildJQLDelete(SQLiteModelMethod method) {
		// TODO Auto-generated method stub
		return null;		
	}

	/**
	 * 
	 * <pre>UPDATE bean01 SET text=${text} WHERE id=${id}</pre>
	 * 
	 * @param method
	 * @return
	 */
	private static String buildJQLUpdate(SQLiteModelMethod method) {
		final SQLDaoDefinition dao = method.getParent();		
		
		// extract some informaction from method and bean
		// use annotation's attribute value and exclude and bean definition to define field list
		final Set<String> fields = defineFields(method, dao, BindSqlUpdate.class, false);		
		
		// for each method's parameter
		final One<String> parameterBeanName=new One<String>();		
		forEachParameter(method, new OnParameterListener() {
			
			@Override
			public void onParameter(VariableElement item) {
				if (dao.getEntity().getElement().asType().equals(item.asType())) {
					parameterBeanName.value0=item.getSimpleName().toString();
				}	
			}
		});
		
		StringBuilder builder=new StringBuilder();
		builder.append("UPDATE ");
		// entity name
		builder.append(dao.getEntitySimplyClassName());
		
		// recreate fields
		final One<String> prefix=new One<>("");
		if (StringUtils.hasText(parameterBeanName.value0))
		{
			prefix.value0=parameterBeanName.value0+".";
		}
					
		builder.append("SET ");		
				
		forEachFields(builder, fields, new OnFieldListener() {
			
			@Override
			public String onField(String item) {
				return item + "= ${"+prefix.value0+"."+item+"}";
			}
		});					
		
		builder.append("WHERE ");
		
		String where=AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), BindSqlUpdate.class, AnnotationAttributeType.WHERE);

		return builder.toString();		
	}

	/**
	 * <pre>INSERT INTO person (name, surname, birth_city, birth_day) VALUES (${name}, ${surname}, ${birthCity}, ${birthDay})</pre>
	 * 
	 * @param method
	 * @return
	 */
	private static String buildJQLInsert(SQLiteModelMethod method) {		
		final SQLDaoDefinition dao = method.getParent();
		final boolean includePrimaryKey=AnnotationUtility.extractAsBoolean(BindDataSourceSubProcessor.elementUtils, method.getElement(), BindSqlInsert.class, AnnotationAttributeType.INCLUDE_PRIMARY_KEY);
		
		// use annotation's attribute value and exclude and bean definition to define field list
		final Set<String> fields = defineFields(method, dao, BindSqlInsert.class, includePrimaryKey);
		
		// for each method's parameter
		final One<String> parameterBeanName=new One<String>();		
		forEachParameter(method, new OnParameterListener() {
			
			@Override
			public void onParameter(VariableElement item) {
				if (dao.getEntity().getElement().asType().equals(item.asType())) {
					parameterBeanName.value0=item.getSimpleName().toString();
				}	
			}
		});
		
		StringBuilder builder=new StringBuilder();
		builder.append("INSERT ");
		builder.append(ConflictAlgorithmType.valueOf(AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), BindSqlInsert.class, AnnotationAttributeType.CONFLICT_ALGORITHM_TYPE)).getSql());
		
		
		builder.append("INTO ");
		builder.append(dao.getEntitySimplyClassName());
			
		builder.append(" (");
		forEachFields(builder, fields, new OnFieldListener() {
			
			@Override
			public String onField(String item) {
				return item;
			}
		});
		builder.append(")");
		
		builder.append(" VALUES");
		
		final One<String> prefix=new One<>("");
		if (StringUtils.hasText(parameterBeanName.value0))
		{
			prefix.value0=parameterBeanName.value0+".";
		}
		
		builder.append(" (");
		forEachFields(builder, fields, new OnFieldListener() {
			
			@Override
			public String onField(String item) {
				return "${"+prefix.value0+item+"}";
			}
		});
		builder.append(")");			
		
		return builder.toString();	
	}


	/**
	 * @param builder
	 * @param fields
	 */
	private static void forEachFields(StringBuilder builder, final Set<String> fields, OnFieldListener listener) {
		{
			String comma="";			
			for (String item:fields) {
				builder.append(comma+listener.onField(item));
				comma=", ";
			}			
		}
	}
	
	public interface OnFieldListener {

		String onField(String item);
		
	}
	

	/**
	 * @param <A>
	 * @param method
	 * @param dao
	 * @param includePrimaryKey
	 * @return
	 */
	private static <A extends Annotation> Set<String> defineFields(SQLiteModelMethod method, final SQLDaoDefinition dao,
			Class<A> annotation, final boolean includePrimaryKey) {
		// extract some informaction from method and bean
		Pair<String, String> extractedFields=getDefinedFieldsInAnnotation(method, BindSqlInsert.class);
		String values=extractedFields.value0;
		String excluedValues=extractedFields.value1;
		
		// for each field of managed bean
		final Set<String> fields = new LinkedHashSet<>();
		forEachFields(dao, new OnPropertyListener() {
			
			@Override
			public void onProperty(SQLProperty item) {
				if (!item.isPrimaryKey() || (item.isPrimaryKey() && includePrimaryKey)) {
					fields.add(item.getName());
				}				
			}
		});
		if (StringUtils.hasText(values))
		{
			if (values.equals("*")) {
				// include all fields (already puts in fields)
			} else {
				// field are specified
				String[] includedFieldNames=values.split(",");
				
				for (String i: includedFieldNames) {
					fields.add(i.trim());
				}
			}
		} else if (StringUtils.hasText(excluedValues)) {
			String[] excludedFieldNames=excluedValues.split(",");
			
			for (String i: excludedFieldNames) {
				fields.remove(i.trim());
			}						
		}
		return fields;
	}
	
	
	private static <A extends Annotation> Pair<String, String> getDefinedFieldsInAnnotation(SQLiteModelMethod method, Class<A> annotation) {
		String values=AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.VALUE);
		String excluedValues=AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.EXCLUDED_FIELDS);
		
		return new Pair<String, String>(values, excluedValues);
	}


	/**
	 * Listener on property iteraction
	 */
	interface OnPropertyListener {
		/**
		 * 
		 * @param item
		 * @return
		 */
		void onProperty(SQLProperty item);
	}


	private static void forEachFields(SQLDaoDefinition dao, OnPropertyListener listener) {		
		for (SQLProperty item :dao.getEntity().getCollection())
		{
			listener.onProperty(item);							
		}
	}
	
	/**
	 * Listener on property iteraction
	 */
	interface OnParameterListener {
		/**
		 * 
		 * @param item
		 * @return
		 */
		void onParameter(VariableElement item);
	}
	
	private static void forEachParameter(SQLiteModelMethod method, OnParameterListener listener) {		
		for (VariableElement p :method.getElement().getParameters())
		{
			listener.onParameter(p);		
		}
	}
	
	
}
