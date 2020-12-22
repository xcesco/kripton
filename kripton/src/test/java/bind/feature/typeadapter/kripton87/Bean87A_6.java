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
package bind.feature.typeadapter.kripton87;

import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

import bind.feature.typeadapter.kripton87.StringUrlTypeAdapter;


/**
 * The Class Bean87A_6.
 */
@BindType
public class Bean87A_6 {

	/** The attribute string. */
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	@BindAdapter(adapter = StringUrlTypeAdapter.class)
	public String attributeString;

	/** The element string. */
	@BindXml(xmlType = XmlType.TAG)
	@BindAdapter(adapter = StringUrlTypeAdapter.class)
	public String elementString;

	/** The data string. */
	@BindXml(xmlType = XmlType.VALUE)
	@BindAdapter(adapter = StringUrlTypeAdapter.class)
	public String dataString;

}
