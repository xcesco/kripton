/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa.
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
/**
 * 
 */
package com.abubusoft.kripton.processor.bind.transform;


/**
 * @author xcesco
 *
 */
public abstract class AbstractBindTransform implements BindTransform {
	
	protected String DEFAULT_VALUE="null";
	
	protected static final String PRE_TYPE_ADAPTER_TO_JAVA="$T.toJava($T.class, ";
	protected static final String PRE_TYPE_ADAPTER_TO_DATA="$T.toData($T.class, ";
	
	protected static final String POST_TYPE_ADAPTER=")";
	
	

}
