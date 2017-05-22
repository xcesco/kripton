package com.abubusoft.kripton.processor.sqlite.grammar;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.lang.model.element.VariableElement;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.BindDataSourceSubProcessor;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
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

	private static String buildJQLUpdate(SQLiteModelMethod method) {
		// TODO Auto-generated method stub
		return null;		
	}

	/**
	 * <pre>INSERT INTO person (name, surname, birth_city, birth_day) VALUES (${name}, ${surname}, ${birthCity}, ${birthDay})</pre>
	 * 
	 * @param method
	 * @return
	 */
	private static String buildJQLInsert(SQLiteModelMethod method) {
		SQLDaoDefinition dao = method.getParent();
		
		StringBuilder builder=new StringBuilder();
		builder.append("INSERT ");
		builder.append(ConflictAlgorithmType.valueOf(AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), BindSqlInsert.class, AnnotationAttributeType.CONFLICT_ALGORITHM_TYPE)).getSql());
		
		boolean includePrimaryKey=AnnotationUtility.extractAsBoolean(BindDataSourceSubProcessor.elementUtils, method.getElement(), BindSqlInsert.class, AnnotationAttributeType.INCLUDE_PRIMARY_KEY);
		
		// manage values
		String values=AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), BindSqlInsert.class, AnnotationAttributeType.VALUE);
		String excluedValues=AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), BindSqlInsert.class, AnnotationAttributeType.EXCLUDED_FIELDS);
		
		Set<String> fields=new LinkedHashSet<>();
		for (SQLProperty item :dao.getEntity().getCollection())
		{
			if (!item.isPrimaryKey() || (item.isPrimaryKey() && includePrimaryKey)) {
				fields.add(item.getName());	
			}			
		}
		
		// it is the parameter function with same type of managed bean
		String parameterBeanName=null;
		for (VariableElement p :method.getElement().getParameters())
		{
			if (dao.getEntity().getElement().asType().equals(p.asType())) {
				parameterBeanName=p.getSimpleName().toString();
			}			
		}
		
		builder.append("INTO ");
		builder.append(dao.getEntitySimplyClassName());
		
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
		
		{
			String comma="";
			builder.append(" (");
			for (String item:fields) {
				builder.append(comma+item);
				comma=", ";
			}
			builder.append(")");
		}
		
		builder.append(" VALUES");
		
		// recreate fields
		String prefix="";
		if (StringUtils.hasText(parameterBeanName))
		{
			prefix=parameterBeanName+".";
		}
		
		{
			String comma="";
			builder.append(" (");
			for (String item:fields) {
				builder.append(comma+"${"+prefix+item+"}");
				comma=", ";
			}
			builder.append(")");
		}
		
		// detect selected fields
		

		return builder.toString();	
	}
}
