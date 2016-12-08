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
package com.abubusoft.kripton.binder.schema;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindTransform;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindTypeXml;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.transform.CustomTransform;
import com.abubusoft.kripton.binder.transform.DefaultCustomTransform;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.binder.xml.XmlType;
import com.abubusoft.kripton.binder.xml.internal.MapEntry;
import com.abubusoft.kripton.binder.xml.internal.MapEntryType;
import com.abubusoft.kripton.common.GenericClass;
import com.abubusoft.kripton.common.LRUCache;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.TypeReflector;
import com.abubusoft.kripton.exception.MappingException;

/**
 * Mapping schema for a class
 * 
 * @author bulldog
 * @author xcesco
 * 
 */
public class MappingSchema {

	/**
	 * Info about xml binding
	 * 
	 * @author xcesco
	 *
	 */
	public static class XmlInfo {
		/**
		 * if true, mapping schema is connected to a map entry.
		 */
		private boolean mapEntryStub;

		/**
		 * if true, mapping schema is connected to a map entry.
		 * 
		 */
		public boolean isMapEntryStub() {
			return mapEntryStub;
		}
	}

	/**
	 * info for xml rapresentation
	 */
	public XmlInfo xmlInfo = new XmlInfo();

	/**
	 * Counters
	 * 
	 * @author xcesco
	 *
	 */
	static class Counters {
		int valueSchemaCount;
		int anyElementSchemaCount;
		int elementSchemaCount;

		int primaryKeyCount;

		public void reset() {
			valueSchemaCount = 0;
			anyElementSchemaCount = 0;
			elementSchemaCount = 0;

			primaryKeyCount = 0;
		}
	}

	/**
	 * schema associated to class.
	 */
	private TypeElementSchema rootElementSchema;
	private Map<String, ElementSchema> field2SchemaMapping;
	private Map<String, ElementSchema> xml2SchemaMapping;

	private Map<String, ElementSchema> field2AttributeSchemaMapping;
	private ElementSchema valueSchema;
	private Map<String, ElementSchema> xml2AttributeSchemaMapping;

	/**
	 * type connected to the mapping schema
	 */
	private Class<?> type;

	/**
	 * if true each field is mapped.
	 */
	private boolean bindAllFields;

	private GenericClass genericsResolver;
	/**
	 * elenco dei wrapper di lista di questo schema
	 */
	private Map<String, ElementSchema> xmlWrapper2SchemaMapping;

	private BindType bindTypeAnnotation;

	public Map<String, ElementSchema> getXmlWrapper2SchemaMapping() {
		return xmlWrapper2SchemaMapping;
	}

	private static final int CACHE_SIZE = 100;

	// use LRU cache to limit memory consumption.
	private static Map<Class<?>, MappingSchema> schemaCache = Collections.synchronizedMap(new LRUCache<Class<?>, MappingSchema>(CACHE_SIZE));

	private MappingSchema(Class<?> type) throws MappingException {
		this.type = type;
		this.genericsResolver = GenericClass.forClass(type);

		// if present @BindAllFields, each field is mapped, except transient,
		// static and final fields
		bindTypeAnnotation = type.getAnnotation(BindType.class);
		
		// if no annotation @Bind is specified, every field will be mapped
		if (bindTypeAnnotation!=null)
			this.bindAllFields = bindTypeAnnotation.allFields();
		else
			this.bindAllFields=true;

		if (MapEntry.class.isAssignableFrom(type)) {
			xmlInfo.mapEntryStub = true;
		}

		// step 1
		buildTypeElementSchema();
		// step 2
		buildField2SchemaMapping();
		// step 3
		buildXml2SchemaMapping();
		// step 4
		buildField2AttributeSchemaMapping();
	}

	/**
	 * Build element schema for type
	 * 
	 * @throws MappingException
	 */
	private void buildTypeElementSchema() throws MappingException {
		rootElementSchema = new TypeElementSchema();

		// BindType
		// BindTypeJson
		BindType bindType = type.getAnnotation(BindType.class);
		BindTypeXml bindTypeXml = type.getAnnotation(BindTypeXml.class);

		if (bindType == null && bindTypeXml != null) {
			throw new MappingException("Class " + type.getName() + " need @BindType annotation, because it uses @BindTypeXml or @BindTable");
		}
		
		if (bindType!=null && !StringUtils.isEmpty(bindType.value())) {
			rootElementSchema.xmlInfo.setName(bindType.value());
		} else {
			rootElementSchema.xmlInfo.setName(StringUtils.lowercaseFirstLetter(type.getSimpleName()));
		}
		
		// BindTypeXml
		if (bindTypeXml != null) {
			if (!type.isAnnotationPresent(BindType.class))
				throw (new MappingException("The annotation @BindTypeXml annotation can not be used without @BinType in class definition " + type.getName()));
			
			BindTypeXml xre = type.getAnnotation(BindTypeXml.class);
			String namespace = StringUtils.isEmpty(xre.namespace()) ? null : xre.namespace();
			rootElementSchema.xmlInfo.setNamespace(namespace);
		} else {
			// if no BindTypeXml, use class name instead
			rootElementSchema.xmlInfo.setName(StringUtils.lowercaseFirstLetter(type.getSimpleName()));
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

		// set of used names
		HashSet<String> usedNames = new HashSet<String>();
		ArrayList<ElementSchema> listSchema = new ArrayList<ElementSchema>();

		listSchema = scanFieldSchema(type, usedNames, currentCounters);

		Class<?> superType = type.getSuperclass();
		// scan super class fields
		while (superType != Object.class && superType != null) {
			ArrayList<ElementSchema> parentField2SchemaMapping = scanFieldSchema(superType, usedNames, parentCounters);

			currentCounters.anyElementSchemaCount += parentCounters.anyElementSchemaCount;
			currentCounters.elementSchemaCount += parentCounters.elementSchemaCount;
			currentCounters.valueSchemaCount += parentCounters.valueSchemaCount;

			// xml validation
			if (currentCounters.valueSchemaCount > 1) {
				throw new MappingException("BinderValue annotation can't annotate more than one fields in the classes of " + " type = " + type.getName() + " and parentType = " + superType.getName());
			}

			if (currentCounters.anyElementSchemaCount > 1) {
				throw new MappingException("BinderAnyElement annotation can't annotate more than one fields in the classes of " + " type = " + type.getName() + " and parentType = " + superType.getName());
			}

			if (currentCounters.valueSchemaCount == 1 && currentCounters.elementSchemaCount >= 1) {
				throw new MappingException("BinderValue and BinderElement annotations can't coexist in the classes of " + " type = " + type.getName() + " and parentType = " + superType.getName());
			}

			// redefined fields in sub-class will overwrite corresponding fields
			// in super-class.
			parentField2SchemaMapping.addAll(listSchema);
			listSchema = parentField2SchemaMapping;
			superType = superType.getSuperclass();
		}

		// database validation
		if (currentCounters.primaryKeyCount > 1) {
			throw new MappingException("There are more than one primary key in the classes of " + " type = " + type.getName() + " and parentType = " + superType.getName());
		}

		// sort fields by order annotations, or name order
		Collections.sort(listSchema, new Comparator<ElementSchema>() {
			@Override
			public int compare(ElementSchema schema1, ElementSchema schema2) {
				if (schema1.order != schema2.order) {
					return schema1.order - schema2.order;
				}

				// both order are null
				return schema1.getName().compareTo(schema2.getName());
			}
		});

		field2SchemaMapping = new LinkedHashMap<String, ElementSchema>();
		ElementSchema item;
		for (int i = 0; i < listSchema.size(); i++) {
			item = listSchema.get(i);
			field2SchemaMapping.put(item.getName(), item);
		}

	}

	private void buildXml2SchemaMapping() {
		xml2SchemaMapping = new LinkedHashMap<String, ElementSchema>();
		xmlWrapper2SchemaMapping = new LinkedHashMap<String, ElementSchema>();
		xml2AttributeSchemaMapping = new LinkedHashMap<String, ElementSchema>();

		for (String fieldName : field2SchemaMapping.keySet()) {
			ElementSchema schema = field2SchemaMapping.get(fieldName);
			switch (schema.xmlInfo.type) {
			case ATTRIBUTE:
				xml2SchemaMapping.put(schema.getName(), schema);
				// build xml2AttributeSchemaMapping at the same time.
				xml2AttributeSchemaMapping.put(schema.getName(), schema);
				break;
			case TAG:
				xml2SchemaMapping.put(schema.getName(), schema);

				// if exist a wrapper, put it in map
				if (schema.hasWrapperName()) {
					xmlWrapper2SchemaMapping.put(schema.getWrapperName(), schema);
				}
				break;
			case VALUE:
			case VALUE_CDATA:
				xml2SchemaMapping.put(schema.getName(), schema);
				// build xml2AttributeSchemaMapping at the same time.
				valueSchema = schema;
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Create attribute schema mapping starting from field2SchemaMapping. Elements present in attribute schema are also contained in field2Schema.
	 */
	private void buildField2AttributeSchemaMapping() {
		field2AttributeSchemaMapping = new LinkedHashMap<String, ElementSchema>();

		for (String fieldName : field2SchemaMapping.keySet()) {
			ElementSchema schemaObj = field2SchemaMapping.get(fieldName);
			if (schemaObj.xmlInfo.type == XmlType.ATTRIBUTE) {
				field2AttributeSchemaMapping.put(fieldName, schemaObj);
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
		if (!StringUtils.hasText(key))
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
	@SuppressWarnings("unchecked")
	private ArrayList<ElementSchema> scanFieldSchema(Class<?> type, HashSet<String> usedNames, Counters counters) throws MappingException {
		ArrayList<ElementSchema> fieldsMap = new ArrayList<ElementSchema>();

		// get declared fields
		Field[] fields = type.getDeclaredFields();

		// used for validation
		counters.reset();

		int modifier;
		String nameFromAnnotation;
		String elementNameFromAnnotation;
		Class<?> fieldType = null;
		int order;
		Class<CustomTransform<?>> customTransformer = null;

		for (Field field : fields) {

			// unlock field if needed
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}

			// exclude transient fields and final fields
			modifier = field.getModifiers();
			if (Modifier.isTransient(modifier) || Modifier.isFinal(modifier) || Modifier.isStatic(modifier)) {
				continue;
			}

			
			BindTransform bindTransform =field.getAnnotation(BindTransform.class);
			
			Bind bindAnnotation = field.getAnnotation(Bind.class);
			BindXml bindXmlAnnotation = field.getAnnotation(BindXml.class);
			//BindJson bindJsonAnnotation = field.getAnnotation(BindJson.class);

			if (!bindAllFields && bindAnnotation == null && (bindXmlAnnotation != null)) {
				throw new MappingException("Can not use @BindXml,@BindJson without @Bind for field " + field.getName() + " in class " + type.getCanonicalName());
			}

			order = 0;

			if (bindAllFields || bindAnnotation != null) {
				fieldType = null;

				nameFromAnnotation = null;
				elementNameFromAnnotation = null;
				order = Bind.DEFAULT_ORDER;

				if (bindAnnotation != null) {
					nameFromAnnotation = bindAnnotation.value();					
					order = bindAnnotation.order();
				}
				
				if (bindXmlAnnotation!=null)
				{
					elementNameFromAnnotation = bindXmlAnnotation.elementTag();	
				}
				
				counters.elementSchemaCount++;

				ElementSchema elementSchema = new ElementSchema();
				elementSchema.order = order;
				customTransformer = (Class<CustomTransform<?>>) (bindTransform == null ? DefaultCustomTransform.class : bindTransform.value());

				if (StringUtils.isEmpty(nameFromAnnotation)) {
					elementSchema.setName(field.getName());
				} else {
					elementSchema.setName(nameFromAnnotation);
				}

				// if elementName is defined, use it on wrapperName and
				// elementName like name
				if (StringUtils.hasText(elementNameFromAnnotation)) {
					// invertiamo i nomi
					elementSchema.setWrapperName(elementNameFromAnnotation);

					String temp = elementSchema.getName();
					elementSchema.setName(elementSchema.getWrapperName());
					elementSchema.setWrapperName(temp);
				}

				elementSchema.buildXmlInfo(bindXmlAnnotation);
			//	elementSchema.buildJsonInfo(bindJsonAnnotation);
				
				elementSchema.setField(field);
				// put in set of used names
				if (StringUtils.hasText(elementSchema.getWrapperName()))
				{
					checkAlreadyUsed(elementSchema.getWrapperName(), usedNames, "Bind");
				} else {
					checkAlreadyUsed(elementSchema.getName(), usedNames, "Bind");
				}
				//
				

				// manage collections
				boolean collection = false;
				collection = collection || handleList(field, elementSchema, customTransformer);
				collection = collection || handleArray(field, elementSchema, customTransformer);
				collection = collection || handleSet(field, elementSchema, customTransformer);
				collection = collection || handleMap(field, elementSchema, bindAnnotation, bindXmlAnnotation != null ? bindXmlAnnotation.mapEntryType() : MapEntryType.TAG, bindTransform);

				if (!collection) {
					if (DefaultCustomTransform.class.equals(customTransformer)) {
						fieldType = TypeReflector.getParameterizedType(field, genericsResolver);
						fieldType = fieldType == null ? field.getType() : fieldType;
					} else {
						fieldType = customTransformer;
					}
					elementSchema.setFieldType(fieldType);

					if (StringUtils.hasText(elementNameFromAnnotation)) {
						// this is not a collection
						throw new MappingException("Attribute elementName is useless in @Bind for field '" + field.getName() + "' of class '" + type.getName() + "' is not a collection");
					}
				}

				// xml section
				switch (elementSchema.xmlInfo.type) {
				case ATTRIBUTE:
					// validation for attribute
					if (collection) {
						throw new MappingException("@BindXml annotation can't annotate like ATTRIBUTE a collection, " + "field = " + field.getName() + ", type = " + type.getName());
					}

					if (!Transformer.isTransformable(elementSchema.getFieldType())) {
						throw new MappingException("@BindXml annotation can't annotate like ATTRIBUTE a complex type field, " + "only primivte type or frequently used java type or enum type field is allowed, " + "field = "
								+ field.getName() + ", type = " + type.getName());
					}
					// don't increment counters.elementSchemaCount, it's an
					// attribute
					counters.elementSchemaCount--;
					break;
				case TAG:
					counters.elementSchemaCount++;
					break;
				case VALUE:
				case VALUE_CDATA:
					// validation
					if (!Transformer.isTransformable(elementSchema.getFieldType())) {
						throw new MappingException("BinderValue annotation can't annotate like VALUE and VALUE_CDATA a complex type field, " + "only primivte type or frequently used java type or enum type field is allowed, " + "field = "
								+ field.getName() + ", type = " + type.getName());
					}
					// if considered like value, we have to remove it from
					// elementSchemaCount
					counters.valueSchemaCount++;
					counters.elementSchemaCount--;
					valueSchema = elementSchema;
					break;
				}

				fieldsMap.add(elementSchema);
			}
		}

		// xml validation
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
	private boolean handleArray(Field field, ElementSchema elementSchema, Class<CustomTransform<?>> customTransformer) throws MappingException {
		Class<?> type = field.getType();
		if (type.isArray() && type.getComponentType() != byte.class) {			
			if (DefaultCustomTransform.class.equals(customTransformer)) {
				type = field.getType().getComponentType();
			} else {
				// use of custom transformer
				type = customTransformer;
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
	private boolean handleSet(Field field, ElementSchema elementSchema, Class<CustomTransform<?>> customTransformer) throws MappingException {
		Class<?> type = field.getType();
		if (TypeReflector.isSet(type)) {
			elementSchema.setAsSet();
			
			if (DefaultCustomTransform.class.equals(customTransformer)) {
				Class<?> paramizedType = TypeReflector.getParameterizedType(field, genericsResolver);
				if (paramizedType == null) {
					throw new MappingException("Can't get parameterized type of a Set field, " + "Framework only supports collection field of Set<T> type, and T must be a bindable type, " + "field = " + field.getName() + ", type = "
							+ type.getName());
				} else {
					elementSchema.setFieldType(paramizedType);
				}
			} else {
				// use of custom transformer
				type = customTransformer;
				elementSchema.setFieldType(type);
			}
			
			return true;
		}

		return false;
	}

	/**
	 * @param field
	 * @param elementSchema
	 * @param bindMapAnnotation
	 * @param mapEntryPolicy
	 * @return
	 * @throws MappingException
	 */
	private boolean handleMap(Field field, ElementSchema elementSchema, Bind bindAnnotation, MapEntryType mapEntryPolicy, BindTransform customTransform) throws MappingException {
		Class<?> type = field.getType();
		if (TypeReflector.isMap(type)) {
			Class<?>[] paramizedType = TypeReflector.getParameterizedTypeArray(field, genericsResolver);

			if (paramizedType == null || paramizedType.length != 2) {
				throw new MappingException("Can't get parameterized type of a Map field, " + "Framework only supports collection field of Map<K,V> type, and K,V must be bindable types, " + "field = " + field.getName() + ", type = "
						+ type.getName());
			}
			elementSchema.buildMapInfo(field.getName(), type, paramizedType[0], paramizedType[1], bindAnnotation, mapEntryPolicy, customTransform);
			elementSchema.setFieldType(Map.class);
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
	private boolean handleList(Field field, ElementSchema elementSchema, Class<CustomTransform<?>> customTransformer) throws MappingException {
		Class<?> type = field.getType();
		if (TypeReflector.isList(type)) {
			elementSchema.setAsList();
			
			if (DefaultCustomTransform.class.equals(customTransformer)) {
				Class<?> paramizedType = TypeReflector.getParameterizedType(field, genericsResolver);
				if (paramizedType == null) {
					throw new MappingException("Can't get parameterized type of a List field, " + "Framework only supports collection field of List<T> type, and T must be a bindable type, " + "field = " + field.getName() + ", type = "
							+ type.getName());
				} else {
					elementSchema.setFieldType(paramizedType);
				}
			} else {
				// use of custom transformer
				type = customTransformer;
				elementSchema.setFieldType(type);
			}

			return true;
		}

		return false;
	}

	/**
	 * getter of type covered in this mapping schema.
	 * 
	 * @return managed type
	 */
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
	 * True if class is contained in maps.
	 * 
	 * @param type
	 * @return
	 *		true if class is contained in maps.
	 */
	public static boolean contains(Class<?> type) {
		return schemaCache.containsKey(type);
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

	public Map<String, ElementSchema> getField2SchemaMapping() {
		return field2SchemaMapping;
	}

	public Map<String, ElementSchema> getXml2SchemaMapping() {
		return xml2SchemaMapping;
	}

	public TypeElementSchema getRootElementSchema() {
		return rootElementSchema;
	}

	public Map<String, ElementSchema> getField2AttributeSchemaMapping() {
		return field2AttributeSchemaMapping;
	}

	public ElementSchema getValueSchema() {
		return valueSchema;
	}

	public Map<String, ElementSchema> getXml2AttributeSchemaMapping() {
		return xml2AttributeSchemaMapping;
	}

}
