package com.abubusoft.kripton.binder.schema;

/**
 * 
 * This bean stores XML/JSON root element information
 * 
 * @author bulldog
 * @author xcesco
 *
 */
public class RootElementSchema {
	
	/**
	 * name of element
	 */
	private String name;

	/**
	 * namespace
	 */
	private String namespace;
	
	/**
	 * consider only children
	 */
	private boolean onlyChildren;

	public boolean isOnlyChildren() {
		return onlyChildren;
	}

	public void setOnlyChildren(boolean onlyChildren) {
		this.onlyChildren = onlyChildren;
	}

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