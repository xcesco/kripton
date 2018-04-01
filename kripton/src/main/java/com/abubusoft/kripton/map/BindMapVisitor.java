package com.abubusoft.kripton.map;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

public abstract class BindMapVisitor {

	public static enum VisitorStatusType {
		RUN, STOP
	}

	@SuppressWarnings("unchecked")
	static VisitorStatusType visitMap(String name, Map<String, Object> map, BindMapListener listener, VisitorStatusType status) {
		for (Entry<String, Object> item : map.entrySet()) {
			if (item.getValue() == null || item.getValue() instanceof String) {
				visit(item.getKey(), (String) item.getValue(), listener, status);
			} else if (item.getValue() instanceof List) {
				visitList(item.getKey(), (List<Object>) item.getValue(), listener, status);
			} else if (item.getValue() instanceof Map) {
				visitMap(item.getKey(), (Map<String, Object>) item.getValue(), listener, status);
			}

			if (status == VisitorStatusType.STOP)
				return status;
		}

		return status;
	}

	@SuppressWarnings("unchecked")
	static VisitorStatusType visitList(String name, List<Object> list, BindMapListener listener, VisitorStatusType status) {
		int i = 0;
		for (Object item : list) {
			if (item == null || item instanceof String) {
				visit(name + "." + i, (String) item, listener, status);
			} else if (item instanceof List) {
				visitList(name + "." + i, (List<Object>) item, listener, status);
			} else if (item instanceof Map) {
				visitMap(name + "." + i, (Map<String, Object>) item, listener, status);
			}
			i++;

			if (status == VisitorStatusType.STOP)
				return status;
		}

		return status;
	}

	static void visit(String name, String value, BindMapListener listener, VisitorStatusType status) {
		listener.onField(name, value, status);
	}

	@SuppressWarnings("unchecked")
	public static void execute(Map<String, Object> map, BindMapListener listener) {
		if (listener == null)
			throw (new KriptonRuntimeException("listener can not be null"));
		if (map == null)
			throw (new KriptonRuntimeException("map can not be null"));

		VisitorStatusType status = VisitorStatusType.RUN;

		for (Entry<String, Object> item : map.entrySet()) {
			if (item.getValue() == null || item.getValue() instanceof String) {
				visit(item.getKey(), (String) item.getValue(), listener, status);
			} else if (item.getValue() instanceof List) {
				visitList(item.getKey(), (List<Object>) item.getValue(), listener, status);
			} else if (item.getValue() instanceof Map) {
				visitMap(item.getKey(), (Map<String, Object>) item.getValue(), listener, status);
			}

			if (status == VisitorStatusType.STOP)
				return;

		}

	}

}
