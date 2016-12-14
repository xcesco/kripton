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
/**
 * 
 */
package bind.kripton78;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;



/**
 * @author xcesco
 *
 */
@BindType(allFields=true)
public class BeanElement78 implements Serializable {

	public BeanElement78()
	{	
	}

	private static final long serialVersionUID = 3113613163524431347L;

	
	@Bind(mapKeyName="k", mapValueName="v")
	public Map<Integer, byte[]> valueMapIntByteArray;
	
	@Bind
	@BindXml(elementTag="item")
	public List<byte[]> valueListByteArray;
	

}
