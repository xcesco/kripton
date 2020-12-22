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
package bind.kripton42faster;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;


/**
 * The Class Restaurant.
 */
@BindType
public class Restaurant {

	/** The id. */
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public long id;
			
	/** The name. */
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public String name;
	
	/** The address. */
	@BindXml(xmlType = XmlType.VALUE_CDATA)
	public String address;
		
	/** The longitude. */
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public Double longitude;
	
	/** The latitude. */
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public Double latitude;
}
