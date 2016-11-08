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
package com.abubusoft.kripton.processor.sqlite.model;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.utils.StringUtility;

public class SQLiteModelMethod extends ModelMethod implements SQLiteModelElement {
	
	private WeakReference<SQLDaoDefinition> parent;

	/**
	 * @return the parent
	 */
	public SQLDaoDefinition getParent() {
		return parent.get();
	}
	
	public SQLiteModelMethod(SQLDaoDefinition parent, ExecutableElement element) {
		super(element);
		this.parent = new WeakReference<SQLDaoDefinition>(parent);
		this.parameterAlias=new HashMap<>();
		
		for (VariableElement p : element.getParameters()) {
			BindSqlParam paramAlias=p.getAnnotation(BindSqlParam.class);
			if (paramAlias!=null && StringUtility.hasText(paramAlias.value()))
			{
				String alias=paramAlias.value();
				parameterAlias.put(alias, p.getSimpleName().toString());
			}					
		}
		
	}
	
	/**
	 * Check if method contains a parameter with value as name
	 * 
	 * @param name
	 *            parameter name to find
	 * @return TypeMirror associated
	 */
	public TypeMirror findParameterTypeByAliasOrName(String name) {
		if (parameterAlias.containsKey(name))
		{
			return findParameterType(parameterAlias.get(name));
		}
		
		return findParameterType(name);
	}
	
	/**
	 * Check if method contains a parameter with value as name
	 * 
	 * @param nameOrAlias
	 *            parameter name to find
	 * @return TypeMirror associated
	 */
	public String findParameterNameByAlias(String nameOrAlias) {
		if (parameterAlias.containsKey(nameOrAlias))
		{
			return parameterAlias.get(nameOrAlias);
		}
		
		return nameOrAlias;
	}

	@Override
	public void accept(SQLiteModelElementVisitor visitor) throws Exception {
		visitor.visit(this);
	}
	
	protected Map<String, String> parameterAlias;
		
}
