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
/**
 * 
 */
package bind.kripton78;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;



// TODO: Auto-generated Javadoc
/**
 * The Class BeanElement78.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@BindType(allFields=true)
public class BeanElement78 implements Serializable {

	/**
	 * Instantiates a new bean element 78.
	 */
	public BeanElement78()
	{	
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3113613163524431347L;

	
	/** The value map int byte array. */
	@Bind(mapKeyName="k", mapValueName="v")
	public Map<Integer, byte[]> valueMapIntByteArray;
	
	/** The value list byte array. */
	@Bind
	public List<byte[]> valueListByteArray;
	

}
