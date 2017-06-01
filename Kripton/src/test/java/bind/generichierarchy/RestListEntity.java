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
package bind.generichierarchy;

import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.annotation.BindXml;

public abstract class RestListEntity<E extends UIDObject> extends RestResponse {

	private static final long serialVersionUID = -7911782943679996559L;
	
	public E bean;
	
	@BindXml(elementTag="item")
	protected List<E> list;
	
	public Map<String, E> map;

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	

}
