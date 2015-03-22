/**
 * 
 */
package com.abubusoft.kripton.binder.schema;

import java.util.ArrayList;

import com.abubusoft.kripton.common.Pair;

/**
 * Schema for array types.
 * 
 * @author xcesco
 *
 */
@SuppressWarnings("rawtypes")
public class SchemaArray extends Pair<ElementSchema, ArrayList> {

	public SchemaArray(ElementSchema value0, ArrayList value1) {
		super(value0, value1);
	}

}
