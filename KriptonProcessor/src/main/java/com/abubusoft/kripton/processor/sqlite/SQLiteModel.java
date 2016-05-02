package com.abubusoft.kripton.processor.sqlite;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;

public class SQLiteModel {
	
	public Converter<String, String> classNameConverter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);
	
	public Converter<String, String> columnNameConverter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);

	protected Map<String, SQLEntity> entities=new HashMap<String, SQLEntity>();
	
	protected Map<String, DaoDefinition> daos=new HashMap<String, DaoDefinition>();
	
	/**
	 * @return the daos
	 */
	public Map<String, DaoDefinition> getDaos() {
		return daos;
	}

	public void clear() {
		entities.clear();
	}

	public void entityAdd(SQLEntity value) {
		entities.put(value.getName(), value);
		
	}

	public Collection<SQLEntity> getEntities() {
		return entities.values();
	}

	public void daoAdd(DaoDefinition value) {
		daos.put(value.getName(), value);
		
	}

	public SQLEntity getEntity(String entityClassName) {
		return entities.get(entityClassName);
	}

}
