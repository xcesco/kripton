/**
 * 
 */
package com.abubusoft.kripton.binder.schema;

import java.util.ArrayList;

import com.abubusoft.kripton.binder.schema.AbstractSchema;
import com.abubusoft.kripton.common.Pair;

/**
 * @author xcesco
 *
 */
@SuppressWarnings("rawtypes")
public class SchemaArray extends Pair<AbstractSchema, ArrayList> {

	public SchemaArray(AbstractSchema value0, ArrayList value1) {
		super(value0, value1);
	}

}
