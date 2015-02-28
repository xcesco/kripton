/**
 * 
 */
package com.abubusoft.kripton.android.sql;

import java.io.Serializable;
import java.util.ArrayList;

import com.abubusoft.kripton.binder.annotation.BindDefault;
import com.abubusoft.kripton.binder.annotation.BindElement;

/**
 * @author xcesco
 *
 */
@BindDefault
public class SQLSelect implements Serializable {
	
	protected byte[] buffer;

	public byte[] getBuffer() {
		return buffer;
	}

	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}

	protected String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public ArrayList<SQLField> getFields() {
		return fields;
	}

	public void setFields(ArrayList<SQLField> fields) {
		this.fields = fields;
	}

	protected String beanClass;

	private static final long serialVersionUID = -7963753006422953671L;
	
	@BindElement(elementName="field")
	protected ArrayList<SQLField> fields=new ArrayList<SQLField>();
	
	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	
	@BindElement(elementName="col")
	protected String[] columns;

	public String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	void build()
	{
		int n=fields.size();
		columns=new String[n];
		for (int i=0; i<n;i++)
		{
			columns[i]=fields.get(i).getColumnName();
		}
	}
	
	void addField(String fieldName, String columnName)
	{
		SQLField field=new SQLField();
		field.setField(fieldName);
		field.setColumnName(columnName);
		
		fields.add(field);
	}

}
