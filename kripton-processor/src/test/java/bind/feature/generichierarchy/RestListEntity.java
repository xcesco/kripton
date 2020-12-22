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
package bind.feature.generichierarchy;

import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.annotation.BindXml;



/**
 * The Class RestListEntity.
 *
 * @param <E> the element type
 */
public abstract class RestListEntity<E extends UIDObject> extends RestResponse {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7911782943679996559L;
	
	/** The bean. */
	public E bean;
	
	/** The list. */
	@BindXml(elementTag="item")
	protected List<E> list;
	
	/** The map. */
	public Map<String, E> map;

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<E> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list the new list
	 */
	public void setList(List<E> list) {
		this.list = list;
	}

	

}
