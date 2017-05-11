package com.abubusoft.kripton.processor.sqlite.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.common.Triple;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;

/**
 * <p>Representation of a content provider</p>  
 *
 */
public class SQLiteModelContentProvider {
	
	final static Converter<String, String> convert = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);
	
	public enum SupportedOperation {
		INSERT,
		UPDATE,
		DELETE,
		SELECT
	}
 
	public List<Triple<String, SQLiteModelMethod, Set<SupportedOperation>>> uris=new ArrayList<>();
	
	private Triple<String, SQLiteModelMethod, Set<SupportedOperation>> findOrCreateByUri(String uri) {
		for (Triple<String, SQLiteModelMethod, Set<SupportedOperation>> item: uris) {
			if (item.value0.equals(uri)) return item;
		}
		
		// we have to create it
		Triple<String, SQLiteModelMethod, Set<SupportedOperation>> result=new Triple<String, SQLiteModelMethod, Set<SupportedOperation>>();
		result.value0=uri;		
		result.value2=new HashSet<SupportedOperation>();
		
		uris.add(result);
		return result;
	}
	
	public void addOperation(String uri, SQLiteModelMethod daoMethod, SupportedOperation operation) {
		Triple<String, SQLiteModelMethod, Set<SupportedOperation>> entity = findOrCreateByUri(uri);
		entity.value1=daoMethod;
		entity.value2.add(operation);
	}
	
	public void addOperation(String uri, SupportedOperation operation) {
		addOperation(uri, null, operation);
	}
	
	public String getConstant(String uri)
	{
		return "URI_"+convert.convert(uri);
	}
}
