package com.abubusoft.kripton.binder.schema;

/**
 * This bean stores mapping between an XML/JSON/DB element and a POJO field
 * 
 * @author bulldog
 * 
 */
public class ElementSchema extends AbstractSchema {

	protected String wrapperName;

	public String getWrapperName() {
		return wrapperName;
	}

	public void setWrapperName(String xmlWrapperName) {
		this.wrapperName = xmlWrapperName;
	}

	private ElementSchemaType type = ElementSchemaType.DEFAULT;

	public boolean isSet() {
		return type == ElementSchemaType.SET;
	}

	public boolean isArray() {
		return type == ElementSchemaType.ARRAY;
	}

	public void setArray() {
		type = ElementSchemaType.ARRAY;
	}

	public ElementSchemaType getType() {
		return type;
	}
	
	public static class MapInfo
	{
		public Class<?> keyClazz;
		public Class<?> valueClazz;
		public MapEntryPolicyType entryPolicy;
	}
	

	
	private MapInfo mapInfo;
	

	public MapInfo getMapInfo() {
		return mapInfo;
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
	 * Set if this is a java.util.List field or not.
	 * 
	 * @param collection
	 */
	public void setList() {
		type = ElementSchemaType.LIST;
	}

	/**
	 * indica se ha un nome da usare come wrapper per incapsulare la lista.
	 * 
	 * @return
	 */
	public boolean hasWrapperName() {
		return wrapperName != null;
	}

	/**
	 * Indicates if the string content of the field should be put in a CDATA
	 * container on serialization
	 * 
	 * @return true or false
	 */
	public boolean isData() {
		return type == ElementSchemaType.CDATA;
	}

	/**
	 * Set if the string content of the field should be put in a CDATA container
	 * on serialization
	 * 
	 * @param data
	 */
	public void setData(boolean value) {
		type = value ? ElementSchemaType.CDATA : ElementSchemaType.DEFAULT;
	}

	/**
	 * Set if field is a set.
	 * 
	 * @param value
	 */
	public void setSet() {
		type = ElementSchemaType.SET;
	}

	/**
	 * Set if field is a map
	 * 
	 * @param value
	 */
	public void setMap(Class<?> keyType, Class<?> valueType, MapEntryPolicyType policy) {
		type = ElementSchemaType.MAP;
		mapInfo=new MapInfo();
		mapInfo.keyClazz=keyType;
		mapInfo.valueClazz=valueType;
		mapInfo.entryPolicy=policy;
	}

	/**
	 * True if field is a map
	 * 
	 * @return
	 */
	public boolean isMap() {
		return type == ElementSchemaType.MAP;
	}
}
