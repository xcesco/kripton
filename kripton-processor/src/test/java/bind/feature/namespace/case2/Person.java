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
package bind.feature.namespace.case2;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.annotation.BindXmlNamespace;
import com.abubusoft.kripton.annotation.BindXmlType;
import com.abubusoft.kripton.xml.XmlType;

/**
 * The Class Person.
 */
@BindType
@BindXmlType(namespaces={
		@BindXmlNamespace(name="tool", uri="http://www.dummy.com")		
		})
public class Person {

	/** The name. */
	@BindXml(xmlType=XmlType.ATTRIBUTE, namespace="tool")
	public String name;
	
	/** The surname. */
	public String surname;
	
	/** The birthday. */
	public Date birthday;
	
	/** The tags. */
	public List<String> tags;
}
