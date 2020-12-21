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

import java.math.BigDecimal;
import java.math.BigInteger;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

import bind.kripton81morecoveragetests.Bean81Enum;


/**
 * The Class Bean81A.
 */
@BindType
public class Bean81A {
	
	/** The id. */
	public long id;
	
	/** The value enum. */
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Bean81Enum valueEnum;
	
	/** The value bid decimal. */
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BigDecimal valueBidDecimal;
	
	/** The value bid integer. */
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public BigInteger valueBidInteger;
	
}
