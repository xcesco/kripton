package com.abubusoft.kripton.binder.schema;

/**
 * 
 * Mapping schema for type (class)
 * 
 * @author bulldog
 * @author xcesco
 *
 */
public class TypeElementSchema {

	/**
	 * metadtaa about xml format
	 * 
	 * @author xcesco
	 *
	 */
	public class XmlInfo {

		/**
		 * name of element
		 */
		private String name;
		/**
		 * namespace
		 */
		private String namespace;

		/**
		 * Get xml root element information
		 * 
		 * @return xml root element name
		 */
		public String getName() {
			return name;
		}

		/**
		 * Set xml root element name
		 * 
		 * @param xmlName
		 */
		public void setName(String xmlName) {
			this.name = xmlName;
		}

		/**
		 * Get xml root element namespace
		 * 
		 * @return xml root element namespace
		 */
		public String getNamespace() {
			return namespace;
		}

		/**
		 * Set xml root element namespace
		 * 
		 * @param namespace
		 */
		public void setNamespace(String namespace) {
			this.namespace = namespace;
		}

	}

	public class JsonInfo {

	}

	/**
	 * metadata about xml format
	 */
	public XmlInfo xmlInfo = new XmlInfo();

	/**
	 * metadata about json format
	 */
	public JsonInfo jsonInfo = new JsonInfo();

}