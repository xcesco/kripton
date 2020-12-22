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
package bind.kripton81morecoveragetests;

import java.util.Currency;
import java.util.Locale;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;


/**
 * The Class Bean81O.
 */
@BindType
public class Bean81O {
	
	/** The id. */
	public long id;
	
	/** The value currency. */
	@BindXml(xmlType=XmlType.VALUE_CDATA)
	public Currency valueCurrency;
	
	/** The value locale. */
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Locale valueLocale;
	
}
