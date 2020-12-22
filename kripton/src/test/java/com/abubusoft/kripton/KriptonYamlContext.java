/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton;

import com.abubusoft.kripton.BinderType;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


/**
 * The Class KriptonYamlContext.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class KriptonYamlContext extends AbstractJacksonContext {

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#getSupportedFormat()
	 */
	@Override
	public BinderType getSupportedFormat()
	{
		return BinderType.YAML;
	}
	
	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.AbstractJacksonContext#createInnerFactory()
	 */
	@Override
	protected JsonFactory createInnerFactory()
	{
		return new YAMLFactory();
	}
	
}
