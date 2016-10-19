package com.abubusoft.kripton.processor.sqlite;

import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;

public class PropertyList extends Pair<String, List<SQLProperty>> {
	
	/**
	 * if true, field list is explicit defined.
	 */
	protected boolean explicitDefinition;

	public PropertyList()
	{		
		value1 = new ArrayList<SQLProperty>();
	}
}
