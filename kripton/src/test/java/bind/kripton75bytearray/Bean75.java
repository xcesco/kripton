/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
package bind.kripton75bytearray;

import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class Bean75.
 */
@BindType
public class Bean75 {
	
	/**
	 * Instantiates a new bean 75.
	 */
	public Bean75()
	{
		
	}
	
	/**
	 * Instantiates a new bean 75.
	 *
	 * @param name the name
	 */
	public Bean75(String name)
	{
		this.name=name;
	}
	
	/** The name. */
	public String name;
		
	/** The value byte type array. */
	public byte[] valueByteTypeArray;
	
	/** The value byte array. */
	public Byte[] valueByteArray;	
	
}
