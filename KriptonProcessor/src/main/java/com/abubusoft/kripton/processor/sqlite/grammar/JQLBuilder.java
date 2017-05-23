package com.abubusoft.kripton.processor.sqlite.grammar;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.lang.model.element.VariableElement;

import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlPageSize;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;
import com.abubusoft.kripton.android.annotation.BindSqlWhere;
import com.abubusoft.kripton.android.annotation.BindSqlWhere.PrependType;
import com.abubusoft.kripton.android.annotation.BindSqlWhereArgs;
import com.abubusoft.kripton.android.sqlite.ConflictAlgorithmType;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.BindDataSourceSubProcessor;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.TypeName;

import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.DELETE_KEYWORD;
import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.DISTINCT_KEYWORD;
import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.FROM_KEYWORD;
import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.GROUP_BY_KEYWORD;
import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.HAVING_KEYWORD;
import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.INSERT_KEYWORD;
import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.INTO_KEYWORD;
import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.LIMIT_KEYWORD;
import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.OFFSET_KEYWORD;
import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.ORDER_BY_KEYWORD;
import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.SELECT_KEYWORD;
import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.SET_KEYWORD;
import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.UPDATE_KEYWORD;
import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.VALUES_KEYWORD;
import static  com.abubusoft.kripton.processor.sqlite.grammar.JQLKeywords.WHERE_KEYWORD;


public abstract class JQLBuilder {

	public interface OnFieldListener {

		String onField(String item);
		
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
	

	public static JQL buildJQL(SQLiteModelMethod method) {	
		final SQLDaoDefinition dao = method.getParent();
		final JQL result=new JQL();
		
		// for each method's parameter
		forEachParameter(method, new OnParameterListener() {
			
			@Override
			public void onParameter(VariableElement item) {
				if (dao.getEntity().getElement().asType().equals(item.asType())) {
					result.paramBean=item.getSimpleName().toString();
				}	
			}
		});
		
		
		if (method.hasAnnotation(BindSqlSelect.class)) {
			return buildJQLSelect(method, result);
		} else if (method.hasAnnotation(BindSqlInsert.class)) {
			return buildJQLInsert(method, result);
		} else if (method.hasAnnotation(BindSqlUpdate.class)) {
			return buildJQLUpdate(method, result);
		} else if (method.hasAnnotation(BindSqlDelete.class)) {
			return buildJQLDelete(method, result);
		}
		
		return null;
	}


	/**
	 * 
	 * <pre>DELETE person WHERE id = ${bean.id} AND #{where}</pre>
	 * 
	 * @param method
	 * @return
	 */
	private static JQL buildJQLDelete(SQLiteModelMethod method, JQL result) {
		final SQLDaoDefinition dao = method.getParent();		
		
		StringBuilder builder=new StringBuilder();
		builder.append(DELETE_KEYWORD);
		// entity name
		builder.append(" "+dao.getEntitySimplyClassName());
						
		builder.append(defineWhereStatement(method, result, BindSqlDelete.class));

		result.jql=builder.toString();		
		return result;		
	}
		

	/**
	 * <pre>INSERT INTO person (name, surname, birth_city, birth_day) VALUES (${name}, ${surname}, ${birthCity}, ${birthDay})</pre>
	 * 
	 * @param method
	 * @return
	 */
	private static JQL buildJQLInsert(SQLiteModelMethod method, JQL result) {
		final Class<? extends Annotation> annotation=BindSqlInsert.class;
		final SQLDaoDefinition dao = method.getParent();
		final boolean includePrimaryKey=AnnotationUtility.extractAsBoolean(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.INCLUDE_PRIMARY_KEY);
		
		// use annotation's attribute value and exclude and bean definition to define field list
		final Set<String> fields = defineFields(method, dao, annotation, includePrimaryKey);
						
		StringBuilder builder=new StringBuilder();
		builder.append(INSERT_KEYWORD);
		builder.append(" "+ConflictAlgorithmType.valueOf(AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.CONFLICT_ALGORITHM_TYPE)).getSql());
		
		
		builder.append(INTO_KEYWORD);
		builder.append(" "+dao.getEntitySimplyClassName());
			
		builder.append(" (");
		builder.append(forEachFields(fields, new OnFieldListener() {
			
			@Override
			public String onField(String item) {
				return item;
			}
		}));
		builder.append(") ");
		
		builder.append(VALUES_KEYWORD);
		
		final One<String> prefix=new One<>("");
		if (result.hasParamBean())
		{
			prefix.value0=result.paramBean+".";
		}
		
		builder.append(" (");
		builder.append(forEachFields(fields, new OnFieldListener() {
			
			@Override
			public String onField(String item) {
				return "${"+prefix.value0+item+"}";
			}
		}));
		builder.append(")");			
		
		result.jql=builder.toString();
		return result;
	}

	
	private static JQL buildJQLSelect(SQLiteModelMethod method, JQL result) {		
		final Class<? extends Annotation> annotation=BindSqlInsert.class;
		final SQLDaoDefinition dao = method.getParent();		
		
		// extract some informaction from method and bean
		// use annotation's attribute value and exclude and bean definition to define field list
		final Set<String> fields = defineFields(method, dao, BindSqlSelect.class, true);		
								
		boolean distinct=AnnotationUtility.extractAsBoolean(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.DISTINCT);
		String annotatedGroupBy=AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.GROUP_BY);
		String annotatedHaving=AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.HAVING);				
		
		StringBuilder builder=new StringBuilder();
		builder.append(SELECT_KEYWORD+" ");
		
		if (distinct) {
			builder.append(DISTINCT_KEYWORD+" ");
		}
		
		// recreate fields
		builder.append(forEachFields(fields, new OnFieldListener() {
			
			@Override
			public String onField(String item) {
				return item;
			}
		}));			
		
		// entity name
		builder.append(" "+FROM_KEYWORD+" "+dao.getEntitySimplyClassName());

		// where
		builder.append(defineWhereStatement(method, result, annotation));
						
		// group by
		if (StringUtils.hasText(annotatedGroupBy)) {
			result.annotatedGroupBy=true;
			builder.append(" "+GROUP_BY_KEYWORD+" "+annotatedGroupBy);
		}

		// having
		if (StringUtils.hasText(annotatedHaving))
		{
			result.annotatedGroupBy=true;
			builder.append(" "+HAVING_KEYWORD+" "+annotatedHaving);
		}
		
		// limit
		builder.append(defineLimitStatement(method,result, annotation));
		
		// order by
		builder.append(defineOrderByStatement(method, result, annotation));

		result.jql=builder.toString();
		return result;	
	}


	/**
	 * 
	 * <pre>UPDATE bean01 SET text=${text} WHERE id=${id}</pre>
	 * 
	 * @param method
	 * @return
	 */
	private static JQL buildJQLUpdate(final SQLiteModelMethod method, JQL result) {
		final Class<? extends Annotation> annotation=BindSqlInsert.class;
		final SQLDaoDefinition dao = method.getParent();		
		
		// extract some informaction from method and bean
		// use annotation's attribute value and exclude and bean definition to define field list
		final Set<String> fields = defineFields(method, dao, annotation, false);		
						
		StringBuilder builder=new StringBuilder();
		builder.append(UPDATE_KEYWORD);
		// entity name
		builder.append(" "+dao.getEntitySimplyClassName());
		
		// recreate fields
		final One<String> prefix=new One<>("");
		if (result.hasParamBean())
		{
			prefix.value0=result.paramBean+".";
		}
					
		builder.append(" "+SET_KEYWORD+ " ");		
				
		builder.append(forEachFields(fields, new OnFieldListener() {
			
			@Override
			public String onField(String item) {
				return item + "=${"+prefix.value0+item+"}";
			}
		}));					
		
		builder.append(defineWhereStatement(method, result, annotation));

		result.jql=builder.toString();
		return result;
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
		Pair<String, String> extractedFields=getDefinedFieldsInAnnotation(method, annotation);
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
	
	/**
	 * Define WHERE statement.
	 * 
	 * @param method
	 * @param classBuilder
	 * @param annotation
	 */
	private static <L extends Annotation> String defineLimitStatement(final SQLiteModelMethod method, final JQL result, Class<L> annotation) {
		StringBuilder builder=new StringBuilder();
		
		int pageSize=AnnotationUtility.extractAsInt(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.PAGE_SIZE);
		if (pageSize>0)
		{
			result.annotatedPageSize=true;
		}
		
		
		final One<String> pageDynamicName=new One<String>(null);
		forEachParameter(method, new OnParameterListener() {
			
			@Override
			public void onParameter(VariableElement methodParam) {
				if (methodParam.getAnnotation(BindSqlPageSize.class)!=null)
				{					
					pageDynamicName.value0=methodParam.getSimpleName().toString();
					result.paramPageSize=pageDynamicName.value0;
					
					//CONSTRAINT: @BindSqlWhereArgs can be used only on String[] parameter type					
					AssertKripton.assertTrueOrInvalidTypeForAnnotationMethodParameterException(TypeUtility.isEquals(TypeName.INT,TypeUtility.typeName(methodParam)) , method.getParent().getElement(), method.getElement(),  methodParam, BindSqlPageSize.class);
				}
				
			}
		});
		
		if (pageSize>0 || StringUtils.hasText(pageDynamicName.value0)) {
			builder.append(" "+LIMIT_KEYWORD+" ");
			
			if (pageSize>0) {				
				builder.append(pageSize);
			} else {
				builder.append("#{pageSize}");
			}
			
			builder.append(" "+OFFSET_KEYWORD+" ");			
			builder.append("#{pageOffset}");					
		}
		
		return builder.toString();
	}
	

	/**
	 * Define ORDER BY statement.
	 * 
	 * @param method
	 * @param classBuilder
	 * @param annotation
	 */
	private static <L extends Annotation> String defineOrderByStatement(final SQLiteModelMethod method, final JQL result, Class<L> annotation) {
		StringBuilder builder=new StringBuilder();
		
		String orderBy=AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.ORDER_BY);
		
		if (StringUtils.hasText(orderBy)) {
			result.annotatedOrderBy=true;
		}
		
		final One<String> orderDynamicName=new One<String>(null);
		forEachParameter(method, new OnParameterListener() {
			
			@Override
			public void onParameter(VariableElement methodParam) {
				if (methodParam.getAnnotation(BindSqlOrderBy.class)!=null)
				{					
					orderDynamicName.value0=methodParam.getSimpleName().toString();					
					
					result.paramOrderBy=orderDynamicName.value0;
					
					//CONSTRAINT: @BindSqlOrderBy can be used only on String parameter type					
					AssertKripton.assertTrueOrInvalidTypeForAnnotationMethodParameterException(TypeUtility.isEquals(TypeUtility.typeName(String.class),TypeUtility.typeName(methodParam)) , method.getParent().getElement(), method.getElement(),  methodParam, BindSqlOrderBy.class);
				}
				
			}
		});
		
		if (StringUtils.hasText(orderBy) || StringUtils.hasText(orderDynamicName.value0)) {
			builder.append(" "+ORDER_BY_KEYWORD);
			
			if (StringUtils.hasText(orderBy)) {				
				builder.append(StringUtils.startWithSpace(orderBy));
			}
			
			if (StringUtils.hasText(orderDynamicName.value0)) {
				if (StringUtils.hasText(orderBy)) {
					builder.append(", "+orderDynamicName.value0);
				}
				builder.append(" #{"+orderDynamicName.value0+"}");
			}
		}
		
		return builder.toString();
	}
	
	
	/**
	 * Define WHERE statement.
	 * 
	 * @param method
	 * @param classBuilder
	 * @param annotation
	 */
	private static <L extends Annotation> String defineWhereStatement(final SQLiteModelMethod method, final JQL jql, Class<L> annotation) {
		StringBuilder builder=new StringBuilder();
		
		String where=AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.WHERE);
		if (StringUtils.hasText(where)) jql.annotatedWhere=true;
		
		final Pair<String, String> whereDynamicName=new Pair<String,String>(null,null);
		forEachParameter(method, new OnParameterListener() {
			
			@Override
			public void onParameter(VariableElement methodParam) {
				if (methodParam.getAnnotation(BindSqlWhere.class)!=null)
				{
					jql.paramWhere=methodParam.getSimpleName().toString();
					PrependType prepend=PrependType.valueOf(AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, methodParam, BindSqlWhere.class, AnnotationAttributeType.PREPEND));
					whereDynamicName.value0=prepend.toString();
					whereDynamicName.value1=methodParam.getSimpleName().toString();
					
					//CONSTRAINT: @BindSqlWhere can be used only on String parameter type					
					AssertKripton.assertTrueOrInvalidTypeForAnnotationMethodParameterException(TypeUtility.isEquals(TypeUtility.typeName(String.class),TypeUtility.typeName(methodParam)), method.getParent().getElement(), method.getElement(),  methodParam, BindSqlWhere.class);
				} else if (methodParam.getAnnotation(BindSqlWhereArgs.class)!=null)
				{
					jql.paramWhereArgs=methodParam.getSimpleName().toString();
					
					//CONSTRAINT: @BindSqlWhereArgs can be used only on String[] parameter type					
					AssertKripton.assertTrueOrInvalidTypeForAnnotationMethodParameterException(TypeUtility.isEquals(ArrayTypeName.of(String.class),TypeUtility.typeName(methodParam)) , method.getParent().getElement(), method.getElement(),  methodParam, BindSqlWhereArgs.class);
				}
				
			}
		});
		
		if (StringUtils.hasText(where) || StringUtils.hasText(whereDynamicName.value1)) {
			builder.append(" "+WHERE_KEYWORD);
			
			if (StringUtils.hasText(where)) {				
				builder.append(StringUtils.startWithSpace(where));
			}
			
			if (StringUtils.hasText(whereDynamicName.value1)) {
				if (StringUtils.hasText(where)) {
					builder.append(" "+whereDynamicName.value0);
				}
				builder.append(" #{"+whereDynamicName.value1+"}");
			}
		}
		
		return builder.toString();
	}


	/**
	 * @param classBuilder
	 * @param fields
	 */
	private static String forEachFields(final Set<String> fields, OnFieldListener listener) {
		StringBuilder builder=new StringBuilder();
		{
			String comma="";			
			for (String item:fields) {
				builder.append(comma+listener.onField(item));
				comma=", ";
			}			
		}
		
		return builder.toString();
	}


	private static void forEachFields(SQLDaoDefinition dao, OnPropertyListener listener) {		
		for (SQLProperty item :dao.getEntity().getCollection())
		{
			listener.onProperty(item);							
		}
	}
	
	private static void forEachParameter(SQLiteModelMethod method, OnParameterListener listener) {		
		for (VariableElement p :method.getElement().getParameters())
		{
			listener.onParameter(p);		
		}
	}
	
	private static <A extends Annotation> Pair<String, String> getDefinedFieldsInAnnotation(SQLiteModelMethod method, Class<A> annotation) {
		String values=AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.VALUE);
		String excluedValues=AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.EXCLUDED_FIELDS);
		
		return new Pair<String, String>(values, excluedValues);
	}
	
	
}
