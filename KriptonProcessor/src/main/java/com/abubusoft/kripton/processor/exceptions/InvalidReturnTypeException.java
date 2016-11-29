/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.exceptions;

import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.squareup.javapoet.TypeName;

public class InvalidReturnTypeException extends KriptonProcessorException {

	public InvalidReturnTypeException()
	{
		
	}
	
	private static final long serialVersionUID = -864898010888025423L;
	
	public InvalidReturnTypeException(SQLDaoDefinition daoDefinition, ModelMethod method, TypeName foundReturnType, TypeName aspectedReturnType)
	{
		super("In class '"+daoDefinition.getName()+"' method '"+method.getName()+"'  has an invalid return type. Found '"+foundReturnType.toString()+"' aspected '"+aspectedReturnType.toString()+"'");
	}

}
