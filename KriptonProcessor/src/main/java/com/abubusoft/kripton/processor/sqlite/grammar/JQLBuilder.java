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
	private static final String DELETE_SQL = "DELETE";
	private static final String DISTINCT_SQL ="DISTINCT";
	private static final String FROM_SQL ="FROM";
	private static final String GROUP_BY_SQL ="GROUP BY";
	private static final String HAVING_SQL ="HAVING";
	private static final String INSERT_SQL = "INSERT";
	private static final String INTO_SQL = "INTO";
	private static final String LIMIT_SQL ="LIMIT";
	private static final String OFFSET_SQL ="OFFSET";
	private static final String ORDER_BY_SQL ="ORDER BY";	
	private static final String SELECT_SQL = "SELECT";
	private static final String SET_SQL = "SET";
	private static final String UPDATE_SQL = "UPDATE";
	private static final String VALUES_SQL = "VALUES";
	private static final String WHERE_SQL = "WHERE";

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


	/**
	 * 
	 * <pre>DELETE person WHERE id = ${bean.id} AND #{where}</pre>
	 * 
	 * @param method
	 * @return
	 */
	private static String buildJQLDelete(SQLiteModelMethod method) {
		final SQLDaoDefinition dao = method.getParent();		
		
		StringBuilder builder=new StringBuilder();
		builder.append(DELETE_SQL);
		// entity name
		builder.append(" "+dao.getEntitySimplyClassName());
						
		builder.append(defineWhereStatement(method, BindSqlDelete.class));

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
		builder.append(INSERT_SQL);
		builder.append(" "+ConflictAlgorithmType.valueOf(AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, method.getElement(), BindSqlInsert.class, AnnotationAttributeType.CONFLICT_ALGORITHM_TYPE)).getSql());
		
		
		builder.append(INTO_SQL);
		builder.append(" "+dao.getEntitySimplyClassName());
			
		builder.append(" (");
		builder.append(forEachFields(fields, new OnFieldListener() {
			
			@Override
			public String onField(String item) {
				return item;
			}
		}));
		builder.append(") ");
		
		builder.append(VALUES_SQL);
		
		final One<String> prefix=new One<>("");
		if (StringUtils.hasText(parameterBeanName.value0))
		{
			prefix.value0=parameterBeanName.value0+".";
		}
		
		builder.append(" (");
		builder.append(forEachFields(fields, new OnFieldListener() {
			
			@Override
			public String onField(String item) {
				return "${"+prefix.value0+item+"}";
			}
		}));
		builder.append(")");			
		
		return builder.toString();	
	}

	
	private static String buildJQLSelect(SQLiteModelMethod method) {
		final SQLDaoDefinition dao = method.getParent();		
		
		// extract some informaction from method and bean
		// use annotation's attribute value and exclude and bean definition to define field list
		final Set<String> fields = defineFields(method, dao, BindSqlSelect.class, true);		
		
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
				
		boolean distinct=AnnotationUtility.extractAsBoolean(BindDataSourceSubProcessor.elementUtils, method.getElement(), BindSqlSelect.class, AnnotationAttributeType.DISTINCT);
		String groupBy=AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), BindSqlSelect.class, AnnotationAttributeType.GROUP_BY);
		String having=AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), BindSqlSelect.class, AnnotationAttributeType.HAVING);				
		
		StringBuilder builder=new StringBuilder();
		builder.append(SELECT_SQL+" ");
		
		if (distinct) {
			builder.append(DISTINCT_SQL+" ");
		}
		
		// recreate fields
		builder.append(forEachFields(fields, new OnFieldListener() {
			
			@Override
			public String onField(String item) {
				return item;
			}
		}));			
		
		// entity name
		builder.append(" "+FROM_SQL+" "+dao.getEntitySimplyClassName());

		// where
		builder.append(defineWhereStatement(method, BindSqlSelect.class));
						
		// group by
		if (StringUtils.hasText(groupBy))
		{
			builder.append(" "+GROUP_BY_SQL+" "+groupBy);
		}
		
		// having
		if (StringUtils.hasText(having))
		{
			builder.append(" "+HAVING_SQL+" "+having);
		}
		
		// limit
		builder.append(defineLimitStatement(method,BindSqlSelect.class));
		
		// order by
		builder.append(defineOrderByStatement(method, BindSqlSelect.class));

		return builder.toString();	
	}


	/**
	 * 
	 * <pre>UPDATE bean01 SET text=${text} WHERE id=${id}</pre>
	 * 
	 * @param method
	 * @return
	 */
	private static String buildJQLUpdate(final SQLiteModelMethod method) {
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
		builder.append(UPDATE_SQL);
		// entity name
		builder.append(" "+dao.getEntitySimplyClassName());
		
		// recreate fields
		final One<String> prefix=new One<>("");
		if (StringUtils.hasText(parameterBeanName.value0))
		{
			prefix.value0=parameterBeanName.value0+".";
		}
					
		builder.append(" "+SET_SQL+ " ");		
				
		builder.append(forEachFields(fields, new OnFieldListener() {
			
			@Override
			public String onField(String item) {
				return item + "=${"+prefix.value0+item+"}";
			}
		}));					
		
		builder.append(defineWhereStatement(method, BindSqlUpdate.class));

		return builder.toString();		
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
	 * @param builder
	 * @param annotation
	 */
	private static <L extends Annotation> String defineLimitStatement(final SQLiteModelMethod method, Class<L> annotation) {
		StringBuilder builder=new StringBuilder();
		
		int pageSize=AnnotationUtility.extractAsInt(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.PAGE_SIZE);
		
		final One<String> pageDynamicName=new One<String>(null);
		forEachParameter(method, new OnParameterListener() {
			
			@Override
			public void onParameter(VariableElement item) {
				if (item.getAnnotation(BindSqlPageSize.class)!=null)
				{										
					pageDynamicName.value0=item.getSimpleName().toString();
				}
				
			}
		});
		
		if (pageSize>0 || StringUtils.hasText(pageDynamicName.value0)) {
			builder.append(" "+LIMIT_SQL+" ");
			
			if (pageSize>0) {				
				builder.append(pageSize);
			} else {
				builder.append("#{pageSize}");
			}
			
			builder.append(" "+OFFSET_SQL+" ");			
			builder.append("#{pageOffset}");					
		}
		
		return builder.toString();
	}
	

	/**
	 * Define ORDER BY statement.
	 * 
	 * @param method
	 * @param builder
	 * @param annotation
	 */
	private static <L extends Annotation> String defineOrderByStatement(final SQLiteModelMethod method, Class<L> annotation) {
		StringBuilder builder=new StringBuilder();
		
		String orderBy=AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.ORDER_BY);
		
		final One<String> orderDynamicName=new One<String>(null);
		forEachParameter(method, new OnParameterListener() {
			
			@Override
			public void onParameter(VariableElement item) {
				if (item.getAnnotation(BindSqlOrderBy.class)!=null)
				{					
					orderDynamicName.value0=item.getSimpleName().toString();					
				}
				
			}
		});
		
		if (StringUtils.hasText(orderBy) || StringUtils.hasText(orderDynamicName.value0)) {
			builder.append(" "+ORDER_BY_SQL);
			
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
	 * @param builder
	 * @param annotation
	 */
	private static <L extends Annotation> String defineWhereStatement(final SQLiteModelMethod method, Class<L> annotation) {
		StringBuilder builder=new StringBuilder();
		
		String where=AnnotationUtility.extractAsString(BindDataSourceSubProcessor.elementUtils, method.getElement(), annotation, AnnotationAttributeType.WHERE);
		
		final Pair<String, String> whereDynamicName=new Pair<String,String>(null,null);
		forEachParameter(method, new OnParameterListener() {
			
			@Override
			public void onParameter(VariableElement item) {
				if (item.getAnnotation(BindSqlWhere.class)!=null)
				{
					PrependType prepend=PrependType.valueOf(AnnotationUtility.extractAsEnumerationValue(BindDataSourceSubProcessor.elementUtils, item, BindSqlWhere.class, AnnotationAttributeType.PREPEND));
					whereDynamicName.value0=prepend.toString();
					whereDynamicName.value1=item.getSimpleName().toString();
				}
				
			}
		});
		
		if (StringUtils.hasText(where) || StringUtils.hasText(whereDynamicName.value1)) {
			builder.append(" "+WHERE_SQL);
			
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
	 * @param builder
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
