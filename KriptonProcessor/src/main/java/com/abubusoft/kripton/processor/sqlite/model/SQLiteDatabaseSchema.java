package com.abubusoft.kripton.processor.sqlite.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.core.ModelBucket;

public class SQLiteDatabaseSchema extends ModelBucket<SQLDaoDefinition, TypeElement> {
	
	public Converter<String, String> classNameConverter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);
	
	public Converter<String, String> columnNameConverter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);

	protected Map<String, SQLEntity> entities=new HashMap<String, SQLEntity>();

	public String fileName;

	public int version;
	
	public SQLiteDatabaseSchema(TypeElement item, String schemaFileName, int schemaVersion) {	
		super(item.getSimpleName().toString(), item);
		
		this.fileName=schemaFileName;
		this.version=schemaVersion;
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
	
	public SQLEntity getEntity(String entityClassName) {
		return entities.get(entityClassName);
	}

}
