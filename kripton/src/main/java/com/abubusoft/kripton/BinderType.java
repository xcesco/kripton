/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton;

// TODO: Auto-generated Javadoc
/**
 * Represents supported data format by Kripton. Note that
 * 
 * @author xcesco
 *
 */
public enum BinderType {
	
	/** xml format. */
	XML(false, false),
	
	/** json format. */
	JSON(false, false),
	
	/** cbor format. */
	CBOR(false, true),
	
	/** yaml format. */
	YAML(false, false),
	
	/** (java) properties format. */
	PROPERTIES(true, false),
	
	/** smile format. */
	SMILE(false, true);

	/**
	 * constructor.
	 *
	 * @param onlyTextValue            true if data format supports only text streams
	 * @param onlyBinaryValue            true if data format supports only binary streams
	 */
	private BinderType(boolean onlyTextValue, boolean onlyBinaryValue) {
		onlyText = onlyTextValue;
		onlyBinary = onlyBinaryValue;
	}

	/** data format can be used only on text streams. */
	public final boolean onlyText;

	/** data format can be used only on binary streams. */
	public final boolean onlyBinary;
}
