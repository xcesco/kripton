package com.abubusoft.kripton.binder.schema;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.database.ColumnType;
import com.abubusoft.kripton.binder.xml.XmlType;
import com.abubusoft.kripton.binder.xml.internal.MapEntryType;

/**
 * This bean stores mapping between an XML/JSON/DB element and a POJO field
 * 
 * @author bulldog
 * @author xcesco
 * 
 */
public class ElementSchema extends AbstractSchema {

	/**
	 * column info
	 *
	 */
	public static class ColumnInfo {
		public String name;
		public ColumnType feature;
	}

	/**
	 * Json info of element schema
	 */
	public static class JsonInfo {

	}

	/**
	 * Map info. Used for schema linked to field who implements Map interfaces.
	 */
	public static class MapInfo {
		/**
		 * strategy for define entries of map
		 */
		public MapEntryType entryStrategy;
		/**
		 * class of key part
		 */
		public Class<?> keyClazz;
		/**
		 * name of key part
		 */
		public String keyName;
		/**
		 * class of map
		 */
		public Class<?> mapClazz;
		/**
		 * class of value part
		 */
		public Class<?> valueClazz;
		/**
		 * name of value part
		 */
		public String valueName;
	}

	/**
	 * Xml info of element schema
	 */
	public static class XmlInfo {

		public XmlType type;

	}

	private ColumnInfo columnInfo;

	private MapInfo mapInfo;

	/**
	 * order of element schema.
	 */
	int order;

	private ElementSchemaType type = ElementSchemaType.ELEMENT;

	protected String wrapperName;

	/**
	 * info about xml rapresentation
	 */
	protected XmlInfo xmlInfo;

	/**
	 * Build column info
	 * 
	 * @param bindColumnAnnotation
	 */
	void buildColumnInfo(BindColumn bindColumnAnnotation) {
		columnInfo = new ColumnInfo();

		columnInfo.name = getName();
		columnInfo.feature=ColumnType.STANDARD;		
		if (bindColumnAnnotation != null) {
			columnInfo.feature = bindColumnAnnotation.value();
			columnInfo.name = this.getName();
			if (!"".equals(bindColumnAnnotation.name())) {
				columnInfo.name = bindColumnAnnotation.name();
			}
		}

	}

	/**
	 * Build map info.
	 * 
	 * @param bindMapAnnotation
	 * @param paramizedType
	 * 
	 * @param value
	 */
	void buildMapInfo(Class<?> mapType, Class<?> keyType, Class<?> valueType, Bind bindAnnotation, MapEntryType policy) {
		type = ElementSchemaType.MAP;
		mapInfo = new MapInfo();
		mapInfo.mapClazz = mapType;
		mapInfo.entryStrategy = policy;

		mapInfo.keyClazz = keyType;
		mapInfo.keyName = bindAnnotation != null ? bindAnnotation.mapKeyName() : Bind.MAP_KEY_DEFAULT;

		mapInfo.valueClazz = valueType;
		mapInfo.valueName = bindAnnotation != null ? bindAnnotation.mapValueName() : Bind.MAP_VALUE_DEFAULT;
	}

	void buildXmlInfo(BindXml bindXmlAnnotation) {
		xmlInfo = new XmlInfo();

		if (bindXmlAnnotation != null) {
			xmlInfo.type = bindXmlAnnotation.value();
		} else {
			xmlInfo.type = XmlType.TAG;
		}
	}

	public ColumnInfo getColumnInfo() {
		return columnInfo;
	}

	public MapInfo getMapInfo() {
		return mapInfo;
	}

	public ElementSchemaType getType() {
		return type;
	}

	public String getWrapperName() {
		return wrapperName;
	}

	public XmlInfo getXmlInfo() {
		return xmlInfo;
	}

	/**
	 * indica se ha un nome da usare come wrapper per incapsulare la lista.
	 * 
	 * @return
	 */
	public boolean hasWrapperName() {
		return wrapperName != null;
	}

	public boolean isArray() {
		return type == ElementSchemaType.ARRAY;
	}

	/**
	 * Check if this is a java.util.List filed, such as List<T>
	 * 
	 * @return true or false
	 */
	public boolean isList() {
		return type == ElementSchemaType.LIST;
	}

	/**
	 * True if field is a map
	 * 
	 * @return
	 */
	public boolean isMap() {
		return type == ElementSchemaType.MAP;
	}

	public boolean isSet() {
		return type == ElementSchemaType.SET;
	}

	public void setArray() {
		type = ElementSchemaType.ARRAY;
	}

	/**
	 * Set if this is a java.util.List field or not.
	 * 
	 * @param collection
	 */
	public void setList() {
		type = ElementSchemaType.LIST;
	}

	/**
	 * Set if field is a set.
	 * 
	 * @param value
	 */
	public void setSet() {
		type = ElementSchemaType.SET;
	}

	public void setWrapperName(String xmlWrapperName) {
		this.wrapperName = xmlWrapperName;
	}
}
