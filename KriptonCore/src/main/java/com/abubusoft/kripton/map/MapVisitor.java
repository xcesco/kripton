package com.abubusoft.kripton.map;

import java.util.List;
import java.util.Map;

public interface MapVisitor {
	/**
	 * @param name
	 * @param map
	 */
	public void visit(String name, Map<String, Object> map);

	/**
	 * @param name
	 * @param list
	 */
	public void visit(String name, List<Object> list);

	/**
	 * @param name
	 * @param value
	 */
	public void visit(String name, String value);
	
}