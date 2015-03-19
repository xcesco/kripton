package com.abubusoft.kripton.binder.schema;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.annotation.BindAttribute;
import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindOrder;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindTypeXml;
import com.abubusoft.kripton.annotation.BindValue;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.common.GenericClass;
import com.abubusoft.kripton.common.LRUCache;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.common.TypeReflector;

/**
 * Factory class for OX mapping schema
 * 
 * 
 * @author bulldog
 * @author xcesco
 * 
 */
public class MappingSchema {

	/**
	 * @author xcesco
	 *
	 */
	static class Counters {
		int valueSchemaCount;
		int anyElementSchemaCount;
		int elementSchemaCount;
	}

	private RootElementSchema rootElementSchema;
	private Map<String, Object> field2SchemaMapping;
	private Map<String, Object> xml2SchemaMapping;

	private Map<String, AttributeSchema> field2AttributeSchemaMapping;
	private ValueSchema valueSchema;
	private Map<String, AttributeSchema> xml2AttributeSchemaMapping;
	private AnyElementSchema anyElementSchema;

	private Class<?> type;
	private boolean isDefault;
	private GenericClass genericsResolver;
	/**
	 * elenco dei wrapper di lista di questo schema
	 */
	private Map<String, Object> xmlWrapper2SchemaMapping;
	private boolean mapStrategy;

	public Map<String, Object> getXmlWrapper2SchemaMapping() {
		return xmlWrapper2SchemaMapping;
	}

	private static final int CACHE_SIZE = 100;
	// use LRU cache to limit memory consumption.
	private static Map<Class<?>, MappingSchema> schemaCache = Collections.synchronizedMap(new LRUCache<Class<?>, MappingSchema>(CACHE_SIZE));

	private MappingSchema(Class<?> type) throws MappingException {
		this.type = type;

		genericsResolver = GenericClass.forClass(type);

		isDefault = type.isAnnotationPresent(BindAllFields.class);
		
		if (MapStrategy.class.isAssignableFrom(type))
		{
			mapStrategy=true;
		}

		// step 1
		this.buildRootElementSchema();
		// step 2
		this.buildField2SchemaMapping();
		// step 3
		this.buildXml2SchemaMapping();
		// step 4
		this.buildField2AttributeSchemaMapping();
	}

	public boolean isMapStrategy() {
		return mapStrategy;
	}

	private void buildRootElementSchema() throws MappingException {
		rootElementSchema = new RootElementSchema();
		
		// BindType
		
		// BindTypeJson
		
		// BindTypeXml
		if (type.isAnnotationPresent(BindTypeXml.class)) {
			// ASSERT: BindTypeXml need BindType
			if (!type.isAnnotationPresent(BindType.class)) throw(new MappingException("The annotation @BindTypeXml annotation can not be used without @BinType in class definition "+type.getName()));
			
			BindTypeXml xre = type.getAnnotation(BindTypeXml.class);
			if (StringUtil.isEmpty(xre.name())) {
				rootElementSchema.xmlInfo.setName(StringUtil.lowercaseFirstLetter(type.getSimpleName()));
			} else {
				rootElementSchema.xmlInfo.setName(xre.name());
			}
			String namespace = StringUtil.isEmpty(xre.namespace()) ? null : xre.namespace();
			rootElementSchema.xmlInfo.setNamespace(namespace);
		} else {
			// if no BindTypeXml, use class name instead
			rootElementSchema.xmlInfo.setName(StringUtil.lowercaseFirstLetter(type.getSimpleName()));
			rootElementSchema.xmlInfo.setNamespace(null);
		}

	}

	/**
	 * Map fields to field2SchemaMapping
	 * 
	 * @throws MappingException
	 */
	private void buildField2SchemaMapping() throws MappingException {
		Counters currentCounters = new Counters();
		Counters parentCounters = new Counters();
		HashSet<String> usedNames = new HashSet<>();

		field2SchemaMapping = scanFieldSchema(type, usedNames, currentCounters);

		Class<?> superType = type.getSuperclass();
		// scan super class fields
		while (superType != Object.class && superType != null) {
			Map<String, Object> parentField2SchemaMapping = scanFieldSchema(superType, usedNames, parentCounters);

			currentCounters.anyElementSchemaCount += parentCounters.anyElementSchemaCount;
			currentCounters.elementSchemaCount += parentCounters.elementSchemaCount;
			currentCounters.valueSchemaCount += parentCounters.valueSchemaCount;

			// more validation
			if (currentCounters.valueSchemaCount > 1) {
				throw new MappingException("BinderValue annotation can't annotate more than one fields in the classes of " + " type = " + type.getName()
						+ " and parentType = " + superType.getName());
			}

			if (currentCounters.anyElementSchemaCount > 1) {
				throw new MappingException("BinderAnyElement annotation can't annotate more than one fields in the classes of " + " type = " + type.getName()
						+ " and parentType = " + superType.getName());
			}

			if (currentCounters.valueSchemaCount == 1 && currentCounters.elementSchemaCount >= 1) {
				throw new MappingException("BinderValue and BinderElement annotations can't coexist in the classes of " + " type = " + type.getName()
						+ " and parentType = " + superType.getName());
			}

			// redefined fields in sub-class will overwrite corresponding fields
			// in super-class.
			parentField2SchemaMapping.putAll(field2SchemaMapping);
			field2SchemaMapping = parentField2SchemaMapping;
			superType = superType.getSuperclass();
		}
	}

	private void buildXml2SchemaMapping() {
		xml2SchemaMapping = new LinkedHashMap<String, Object>();
		xmlWrapper2SchemaMapping = new LinkedHashMap<String, Object>();
		xml2AttributeSchemaMapping = new LinkedHashMap<String, AttributeSchema>();

		for (String fieldName : field2SchemaMapping.keySet()) {
			Object schemaObj = field2SchemaMapping.get(fieldName);
			if (schemaObj instanceof AttributeSchema) {
				AttributeSchema as = (AttributeSchema) schemaObj;
				xml2SchemaMapping.put(as.getName(), as);
				// build xml2AttributeSchemaMapping at the same time.
				xml2AttributeSchemaMapping.put(as.getName(), as);
			} else if (schemaObj instanceof ElementSchema) {
				ElementSchema es = (ElementSchema) schemaObj;
				xml2SchemaMapping.put(es.getName(), es);

				// if exist a wrapper, put it in map
				if (es.hasWrapperName()) {
					xmlWrapper2SchemaMapping.put(es.getWrapperName(), es);
				}
			}
		}
	}

	private void buildField2AttributeSchemaMapping() {
		field2AttributeSchemaMapping = new LinkedHashMap<String, AttributeSchema>();

		for (String fieldName : field2SchemaMapping.keySet()) {
			Object schemaObj = field2SchemaMapping.get(fieldName);
			if (schemaObj instanceof AttributeSchema) {
				field2AttributeSchemaMapping.put(fieldName, (AttributeSchema) schemaObj);
			}
		}
	}

	/**
	 * Check if string if present
	 * 
	 * @param key
	 * @param usedStrings
	 * @param message
	 * @throws MappingException
	 */
	private void checkAlreadyUsed(String key, HashSet<String> usedStrings, String message) throws MappingException {
		if (!StringUtil.hasText(key))
			return;
		// put in set of used names
		if (usedStrings.contains(key)) {
			throw new MappingException(message + " annotation can't annotate more than one fields in same class with name " + key);
		} else {
			usedStrings.add(key);
		}
	}

	/**
	 * Scans declared field of class passed like parameter
	 * 
	 * @param type
	 *            class to scan
	 * @param usedName
	 *            set of used name
	 * @return
	 * @throws MappingException
	 */
	private Map<String, Object> scanFieldSchema(Class<?> type, HashSet<String> usedNames, Counters counters) throws MappingException {
		// usiamo la linkedhashmap per tenere traccia dell'ordine
		Map<String, Object> fieldsMap = new LinkedHashMap<String, Object>();
		Field[] fields = type.getDeclaredFields();

		// sort fields according to order annotations, or to name order
		Arrays.sort(fields, new Comparator<Field>() {
			@Override
			public int compare(Field field1, Field field2) {
				BindOrder order1 = field1.getAnnotation(BindOrder.class);
				BindOrder order2 = field2.getAnnotation(BindOrder.class);
				if (order1 != null && order2 != null) {
					return order1.value() - order2.value();
				}
				if (order1 != null && order2 == null) {
					return -1;
				}
				if (order1 == null && order2 != null) {
					return 1;
				}
				return field1.getName().compareTo(field2.getName());
			}

		});

		// used for validation
		counters.valueSchemaCount = 0;
		counters.anyElementSchemaCount = 0;
		counters.elementSchemaCount = 0; 

		int modifier;

		for (Field field : fields) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}

			// exclude transient fields and final fields
			modifier = field.getModifiers();
			if (Modifier.isTransient(modifier) || Modifier.isFinal(modifier)) {
				continue;
			}

			if (field.isAnnotationPresent(Bind.class)) {
				counters.elementSchemaCount++;

				Bind xmlElement = field.getAnnotation(Bind.class);
				ElementSchema elementSchema = new ElementSchema();

				if (StringUtil.isEmpty(xmlElement.name())) {
					elementSchema.setName(field.getName());
				} else {
					elementSchema.setName(xmlElement.name());
				}

				// se esiste un nome per gli elementi di una lista/array, il
				// nome dell'elemento
				// diventa il nome del wrapper
				if (!StringUtil.isEmpty(xmlElement.elementName())) {
					// invertiamo i nomi
					elementSchema.setWrapperName(xmlElement.elementName());

					String temp = elementSchema.getName();
					elementSchema.setName(elementSchema.getWrapperName());
					elementSchema.setWrapperName(temp);
				}

				elementSchema.setData(xmlElement.data());
				elementSchema.setField(field);
				// put in set of used names
				checkAlreadyUsed(elementSchema.getName(), usedNames, "BinderElement");
				checkAlreadyUsed(elementSchema.getWrapperName(), usedNames, "BinderElement");

				fieldsMap.put(field.getName(), elementSchema);

				// List validation
				if (handleList(field, elementSchema))
					continue;
				if (handleArray(field, elementSchema))
					continue;
				if (handleSet(field, elementSchema))
					continue;
				if (handleMap(field, elementSchema, xmlElement.mapEntryPolicy()))
					continue;

				// put after list and array evaluation
				Class<?> fieldType = TypeReflector.getParameterizedType(field, genericsResolver);
				fieldType = fieldType == null ? field.getType() : fieldType;
				elementSchema.setFieldType(fieldType);

				/*
				 * TODO to implements in next releases // database section if
				 * (field.isAnnotationPresent(BindDatabase.class)) {
				 * BindDatabase databaseAnnotation =
				 * field.getAnnotation(BindDatabase.class);
				 * elementSchema.setPrimaryKey(databaseAnnotation.primaryKey());
				 * elementSchema.setNullable(databaseAnnotation.nullable());
				 * elementSchema.setUnique(databaseAnnotation.unique()); }
				 */
			} else if (field.isAnnotationPresent(BindAttribute.class)) {
				Class<?> fieldType = TypeReflector.getParameterizedType(field, genericsResolver);
				fieldType = fieldType == null ? field.getType() : fieldType;

				// validation
				if (!Transformer.isTransformable(fieldType)) {
					throw new MappingException("BinderAttribute annotation can't annotate complex type field, "
							+ "only primivte type or frequently used java type or enum type field is allowed, " + "field = " + field.getName() + ", type = "
							+ type.getName());
				}

				BindAttribute xmlAttribute = field.getAnnotation(BindAttribute.class);
				AttributeSchema attributeSchema = new AttributeSchema();
				// if attribute name was not provided, use field name instead
				if (StringUtil.isEmpty(xmlAttribute.name())) {
					attributeSchema.setName(field.getName());
				} else {
					attributeSchema.setName(xmlAttribute.name());
				}
				attributeSchema.setField(field);
				attributeSchema.setFieldType(fieldType);

				// put in set of used names
				checkAlreadyUsed(attributeSchema.getName(), usedNames, "BinderAttribute");
				fieldsMap.put(field.getName(), attributeSchema);

				/*
				 * TODO to implements in next releases // database section if
				 * (field.isAnnotationPresent(BindDatabase.class)) {
				 * BindDatabase databaseAnnotation =
				 * field.getAnnotation(BindDatabase.class);
				 * attributeSchema.setPrimaryKey
				 * (databaseAnnotation.primaryKey());
				 * attributeSchema.setNullable(databaseAnnotation.nullable());
				 * attributeSchema.setUnique(databaseAnnotation.unique()); }
				 */
			} else if (field.isAnnotationPresent(BindValue.class)) {
				counters.valueSchemaCount++;

				Class<?> fieldType = TypeReflector.getParameterizedType(field, genericsResolver);
				fieldType = fieldType == null ? field.getType() : fieldType;

				// validation
				if (!Transformer.isTransformable(fieldType)) {
					throw new MappingException("BinderValue annotation can't annotate complex type field, "
							+ "only primivte type or frequently used java type or enum type field is allowed, " + "field = " + field.getName() + ", type = "
							+ type.getName());
				}

				BindValue xmlValue = field.getAnnotation(BindValue.class);

				valueSchema = new ValueSchema();
				valueSchema.setName(field.getName());
				valueSchema.setData(xmlValue.data());
				valueSchema.setField(field);
				valueSchema.setFieldType(fieldType);

				// put in set of used names
				checkAlreadyUsed(valueSchema.getName(), usedNames, "BinderValue");

				/*
				 * TODO to implements in next releases // database section if
				 * (field.isAnnotationPresent(BindDatabase.class)) {
				 * BindDatabase databaseAnnotation =
				 * field.getAnnotation(BindDatabase.class);
				 * valueSchema.setPrimaryKey(databaseAnnotation.primaryKey());
				 * valueSchema.setNullable(databaseAnnotation.nullable());
				 * valueSchema.setUnique(databaseAnnotation.unique()); }
				 */

				/*
				 * } else if (field.isAnnotationPresent(BindAnyElement.class)) {
				 * counters.anyElementSchemaCount++;
				 * 
				 * if (!TypeReflector.collectionAssignable(field.getType())) {
				 * throw new MappingException(
				 * "Current nano framework only supports java.util.List<T> as container of any type, "
				 * + "field = " + field.getName() + ", type = " +
				 * type.getName()); }
				 * 
				 * Class<?> fieldType = field.getType(); if
				 * (!TypeReflector.isList(fieldType)) { throw new
				 * MappingException(
				 * "Current nano framework only supports java.util.List<T> as collection type, "
				 * + "field = " + field.getName() + ", type = " +
				 * type.getName()); }
				 * 
				 * anyElementSchema = new AnyElementSchema();
				 * anyElementSchema.setField(field);
				 * 
				 * // set the name anyElementSchema.setName(field.getName());
				 * 
				 * // database section if
				 * (field.isAnnotationPresent(BindDatabase.class)) {
				 * BindDatabase databaseAnnotation =
				 * field.getAnnotation(BindDatabase.class);
				 * anyElementSchema.setPrimaryKey
				 * (databaseAnnotation.primaryKey());
				 * anyElementSchema.setNullable(databaseAnnotation.nullable());
				 * anyElementSchema.setUnique(databaseAnnotation.unique()); }
				 */
			} else if (isDefault) { 
				// default to Node
				counters.elementSchemaCount++;

				Class<?> fieldType = TypeReflector.getParameterizedType(field, genericsResolver);
				fieldType = fieldType == null ? field.getType() : fieldType;

				ElementSchema elementSchema = new ElementSchema();

				elementSchema.setName(field.getName());
				elementSchema.setFieldType(fieldType);
				elementSchema.setField(field);

				// put in set of used names
				checkAlreadyUsed(elementSchema.getName(), usedNames, "BinderElement");
				// perhaps this is never used
				// checkAlreadyUsed(elementSchema.getWrapperName(), usedNames,
				// "BinderElement");

				fieldsMap.put(field.getName(), elementSchema);

				// List validation
				if (handleList(field, elementSchema))
					continue;
				if (handleArray(field, elementSchema))
					continue;
				if (handleSet(field, elementSchema))
					continue;
				if (handleMap(field, elementSchema, MapEntryStrategyType.ELEMENTS))
					continue;

				/*
				 * TODO to implements in next releases // database section if
				 * (field.isAnnotationPresent(BindDatabase.class)) {
				 * BindDatabase databaseAnnotation =
				 * field.getAnnotation(BindDatabase.class);
				 * elementSchema.setPrimaryKey(databaseAnnotation.primaryKey());
				 * elementSchema.setNullable(databaseAnnotation.nullable());
				 * elementSchema.setUnique(databaseAnnotation.unique()); }
				 */
			}
		}

		// more validation
		if (counters.valueSchemaCount > 1) {
			throw new MappingException("BinderValue annotation can't annotate more than one fields in same class," + " type = " + type.getName());
		}

		if (counters.anyElementSchemaCount > 1) {
			throw new MappingException("BinderAnyElement annotation can't annotate more than one fields in same class," + " type = " + type.getName());
		}

		if (counters.valueSchemaCount == 1 && counters.elementSchemaCount >= 1) {
			throw new MappingException("BinderValue and BinderElement annotations can't coexist in same class," + " type = " + type.getName());
		}

		return fieldsMap;

	}

	/**
	 * Set field type if field is an array.
	 * 
	 * @param field
	 * @param elementSchema
	 * @return
	 * @throws MappingException
	 */
	private boolean handleArray(Field field, ElementSchema elementSchema) throws MappingException {
		Class<?> type = field.getType();
		if (type.isArray() && type.getComponentType() != byte.class) {
			type = field.getType().getComponentType();

			if (!elementSchema.hasWrapperName()) {
				// elementSchema.setWrapperName(elementSchema.getName());
				// elementSchema.setName(elementSchema.getName()+"Element");
			}

			elementSchema.setArray();
			elementSchema.setFieldType(type);

			return true;
		}

		return false;
	}

	/**
	 * Set type of elementSchema if field is a collection
	 * 
	 * @param field
	 * @param elementSchema
	 * @return
	 * @throws MappingException
	 */
	private boolean handleSet(Field field, ElementSchema elementSchema) throws MappingException {
		Class<?> type = field.getType();
		if (TypeReflector.isSet(type)) {
			elementSchema.setSet();
			Class<?> paramizedType = TypeReflector.getParameterizedType(field, genericsResolver);
			if (paramizedType == null) {
				throw new MappingException("Can't get parameterized type of a Set field, "
						+ "Framework only supports collection field of Set<T> type, and T must be a bindable type, " + "field = " + field.getName()
						+ ", type = " + type.getName());
			} else {
				elementSchema.setFieldType(paramizedType);
			}
			return true;
		}

		return false;
	}

	@SuppressWarnings("unused")
	private boolean handleMap(Field field, ElementSchema elementSchema, MapEntryStrategyType mapEntryPolicy) throws MappingException {
		Class<?> type = field.getType();
		if (TypeReflector.isMap(type)) {
			Class<?>[] paramizedType = TypeReflector.getParameterizedTypeArray(field, genericsResolver);

			elementSchema.setMapInfo(type, paramizedType[0], paramizedType[1], mapEntryPolicy);

			if (paramizedType == null) {
				throw new MappingException("Can't get parameterized type of a Map field, "
						+ "Framework only supports collection field of Map<K,V> type, and K,V must be bindable types, " + "field = " + field.getName()
						+ ", type = " + type.getName());
			} else {
				elementSchema.setFieldType(Map.class);
			}
			return true;
		}

		return false;
	}

	/**
	 * Set type of elementSchema if field is a collection
	 * 
	 * @param field
	 * @param elementSchema
	 * @return
	 * @throws MappingException
	 */
	private boolean handleList(Field field, ElementSchema elementSchema) throws MappingException {
		Class<?> type = field.getType();
		if (TypeReflector.isList(type)) {

			elementSchema.setList();
			Class<?> paramizedType = TypeReflector.getParameterizedType(field, genericsResolver);
			if (paramizedType == null) {
				throw new MappingException("Can't get parameterized type of a List field, "
						+ "Framework only supports collection field of List<T> type, and T must be a bindable type, " + "field = " + field.getName()
						+ ", type = " + type.getName());
			} else {
				elementSchema.setFieldType(paramizedType);
			}

			return true;
		}

		return false;
	}

	public Class<?> getType() {
		return this.type;
	}

	/**
	 * Factory method.
	 * 
	 * @param object
	 *            an object to get mapping schema from.
	 * @return a MappingSchema instance.
	 */
	public static MappingSchema fromObject(Object object) throws MappingException {
		return fromClass(object.getClass());
	}

	/**
	 * Factory method.
	 * 
	 * @param type
	 *            a Class type to get mapping schema from.
	 * @return a MappingSchema instance.
	 */
	public static MappingSchema fromClass(Class<?> type) throws MappingException {
		if (schemaCache.containsKey(type)) {
			return schemaCache.get(type);
		} else {
			MappingSchema mappingSchema = new MappingSchema(type);
			schemaCache.put(type, mappingSchema);
			return mappingSchema;
		}
	}

	public Map<String, Object> getField2SchemaMapping() {
		return field2SchemaMapping;
	}

	public Map<String, Object> getXml2SchemaMapping() {
		return xml2SchemaMapping;
	}

	public RootElementSchema getRootElementSchema() {
		return rootElementSchema;
	}

	public Map<String, AttributeSchema> getField2AttributeSchemaMapping() {
		return field2AttributeSchemaMapping;
	}

	public ValueSchema getValueSchema() {
		return valueSchema;
	}

	public AnyElementSchema getAnyElementSchema() {
		return anyElementSchema;
	}

	public Map<String, AttributeSchema> getXml2AttributeSchemaMapping() {
		return xml2AttributeSchemaMapping;
	}

}
