package com.abubusoft.kripton.binder.schema;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindTypeXml;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.annotation.XmlType;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.binder.xml.internal.MapEntry;
import com.abubusoft.kripton.binder.xml.internal.MapEntryType;
import com.abubusoft.kripton.common.GenericClass;
import com.abubusoft.kripton.common.LRUCache;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.common.TypeReflector;

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
		 * @return
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

	public Map<String, ElementSchema> getXmlWrapper2SchemaMapping() {
		return xmlWrapper2SchemaMapping;
	}

	private static final int CACHE_SIZE = 100;
	// use LRU cache to limit memory consumption.
	private static Map<Class<?>, MappingSchema> schemaCache = Collections.synchronizedMap(new LRUCache<Class<?>, MappingSchema>(CACHE_SIZE));

	private MappingSchema(Class<?> type) throws MappingException {
		this.type = type;

		genericsResolver = GenericClass.forClass(type);

		// if present @BindAllFields, each field is mapped, except transient,
		// static and final fields
		bindAllFields = type.isAnnotationPresent(BindAllFields.class);

		if (MapEntry.class.isAssignableFrom(type)) {
			xmlInfo.mapEntryStub = true;
		}

		// step 1
		this.buildTypeElementSchema();
		// step 2
		this.buildField2SchemaMapping();
		// step 3
		this.buildXml2SchemaMapping();
		// step 4
		this.buildField2AttributeSchemaMapping();
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
		BindType bindType=type.getAnnotation(BindType.class);
		BindTypeXml bindTypeXml=type.getAnnotation(BindTypeXml.class);

		if (bindType==null && bindTypeXml!=null)
		{
			throw new MappingException("Class "+type.getName()+" need @BindType annotation, because it uses @BindTypeXml");
		}
		// BindTypeXml
		if (bindTypeXml!=null) {
			// ASSERT: BindTypeXml need BindType
			if (!type.isAnnotationPresent(BindType.class))
				throw (new MappingException("The annotation @BindTypeXml annotation can not be used without @BinType in class definition " + type.getName()));

			BindTypeXml xre = type.getAnnotation(BindTypeXml.class);
			if (StringUtil.isEmpty(xre.value())) {
				rootElementSchema.xmlInfo.setName(StringUtil.lowercaseFirstLetter(type.getSimpleName()));
			} else {
				rootElementSchema.xmlInfo.setName(xre.value());
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

		// set of used names
		HashSet<String> usedNames = new HashSet<>();
		ArrayList<ElementSchema> listSchema = new ArrayList<>();

		listSchema = scanFieldSchema(type, usedNames, currentCounters);

		Class<?> superType = type.getSuperclass();
		// scan super class fields
		while (superType != Object.class && superType != null) {
			ArrayList<ElementSchema> parentField2SchemaMapping = scanFieldSchema(superType, usedNames, parentCounters);

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
			parentField2SchemaMapping.addAll(listSchema);
			listSchema = parentField2SchemaMapping;
			superType = superType.getSuperclass();
		}

		// sort fields by order annotations, or name order
		Collections.sort(listSchema, new Comparator<ElementSchema>() {
			@Override
			public int compare(ElementSchema schema1, ElementSchema schema2) {
				Integer order1 = schema1.order;
				Integer order2 = schema2.order;

				if (order1 != null || order2 != null) {
					if (order1 != null && order2 != null) {
						if (order1 == order2) {
							return schema1.getName().compareTo(schema2.getName());
						}
						return order1 - order2;
					}
					if (order1 != null && order2 == null) {
						return -1;
					}
					if (order1 == null && order2 != null) {
						return 1;
					}
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
	 * Create attribute schema mapping starting from field2SchemaMapping.
	 * Elements present in attribute schema are also contained in field2Schema.
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
	private ArrayList<ElementSchema> scanFieldSchema(Class<?> type, HashSet<String> usedNames, Counters counters) throws MappingException {
		ArrayList<ElementSchema> fieldsMap = new ArrayList<ElementSchema>();

		// get declared fields
		Field[] fields = type.getDeclaredFields();

		// used for validation
		counters.valueSchemaCount = 0;
		counters.anyElementSchemaCount = 0;
		counters.elementSchemaCount = 0;

		int modifier;
		String nameFromAnnotation;
		String elementNameFromAnnotation;
		Class<?> fieldType = null;
		@SuppressWarnings("unused")
		int order;
		
		for (Field field : fields) {

			if (!field.isAccessible()) { 
				// unlock field
				field.setAccessible(true);
			}

			// exclude transient fields and final fields
			modifier = field.getModifiers();
			if (Modifier.isTransient(modifier) || Modifier.isFinal(modifier) || Modifier.isStatic(modifier)) {
				continue;
			}

			Bind bindAnnotation = field.getAnnotation(Bind.class);
			BindXml bindXmlAnnotation = field.getAnnotation(BindXml.class);			

			if (!bindAllFields && bindAnnotation == null && (bindXmlAnnotation != null)) {
				throw new MappingException("Can not use @BindXml without @Bind for field " + field.getName() + " in class " + type.getCanonicalName());
			}
			
			order=0;

			if (bindAllFields || bindAnnotation != null) {
				fieldType = null;

				nameFromAnnotation = null;
				elementNameFromAnnotation = null;
				if (bindAnnotation != null) {
					nameFromAnnotation = bindAnnotation.value();
					elementNameFromAnnotation = bindAnnotation.elementName();
					order=bindAnnotation.order();
				} 
				counters.elementSchemaCount++;

				ElementSchema elementSchema = new ElementSchema();
				
				if (StringUtil.isEmpty(nameFromAnnotation)) {
					elementSchema.setName(field.getName());
				} else {
					elementSchema.setName(nameFromAnnotation);
				}

				// if elementName is defined, use it on wrapperName and
				// elementName like name
				if (!StringUtil.isEmpty(elementNameFromAnnotation)) {
					// invertiamo i nomi
					elementSchema.setWrapperName(elementNameFromAnnotation);

					String temp = elementSchema.getName();
					elementSchema.setName(elementSchema.getWrapperName());
					elementSchema.setWrapperName(temp);
				}

				elementSchema.buildXmlInfo(bindXmlAnnotation);
				elementSchema.setField(field);
				// put in set of used names
				checkAlreadyUsed(elementSchema.getName(), usedNames, "Bind");
				checkAlreadyUsed(elementSchema.getWrapperName(), usedNames, "Bind");

				// manage collections
				boolean collection = false;
				collection = collection || handleList(field, elementSchema);
				collection = collection || handleArray(field, elementSchema);
				collection = collection || handleSet(field, elementSchema);
				collection = collection
						|| handleMap(field, elementSchema, bindAnnotation, bindXmlAnnotation != null ? bindXmlAnnotation.mapEntryStrategy()
								: MapEntryType.ELEMENTS);

				if (!collection) {
					fieldType = TypeReflector.getParameterizedType(field, genericsResolver);
					fieldType = fieldType == null ? field.getType() : fieldType;
					elementSchema.setFieldType(fieldType);

					if (StringUtil.hasText(elementNameFromAnnotation)) {
						// this is not a collection
						throw new MappingException("Attribute elementName is useless in @Bind for field '" + field.getName() + "' of class '" + type.getName()
								+ "' is not a collection");
					}
				}

				switch (elementSchema.xmlInfo.type) {
				case ATTRIBUTE:
					// validation for attribute
					if (collection) {
						throw new MappingException("@BindXml annotation can't annotate like ATTRIBUTE a collection, " + "field = " + field.getName()
								+ ", type = " + type.getName());
					}

					if (!Transformer.isTransformable(elementSchema.getFieldType())) {
						throw new MappingException("@BindXml annotation can't annotate like ATTRIBUTE a complex type field, "
								+ "only primivte type or frequently used java type or enum type field is allowed, " + "field = " + field.getName()
								+ ", type = " + type.getName());
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
						throw new MappingException("BinderValue annotation can't annotate like VALUE and VALUE_CDATA a complex type field, "
								+ "only primivte type or frequently used java type or enum type field is allowed, " + "field = " + field.getName()
								+ ", type = " + type.getName()); 
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

	/**
	 * @param field
	 * @param elementSchema
	 * @param bindMapAnnotation
	 * @param mapEntryPolicy
	 * @return
	 * @throws MappingException
	 */
	@SuppressWarnings("unused")
	private boolean handleMap(Field field, ElementSchema elementSchema, Bind bindAnnotation, MapEntryType mapEntryPolicy) throws MappingException {
		Class<?> type = field.getType();
		if (TypeReflector.isMap(type)) {
			Class<?>[] paramizedType = TypeReflector.getParameterizedTypeArray(field, genericsResolver);

			elementSchema.buildMapInfo(type, paramizedType[0], paramizedType[1], bindAnnotation, mapEntryPolicy);

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
