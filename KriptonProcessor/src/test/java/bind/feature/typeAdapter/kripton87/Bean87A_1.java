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
package bind.feature.typeAdapter.kripton87;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;

import bind.feature.typeAdapter.kripton87.DateLongTypeAdapter;
import bind.feature.typeAdapter.kripton87.StringInverterTypeAdapter;

@BindType
public class Bean87A_1 {
	
	@BindAdapter(adapter=DateLongTypeAdapter.class, dataType=Long.class)	
	public Date valueDate;
	
	@BindAdapter(adapter=StringInverterTypeAdapter.class, dataType=String.class)
	public String valueDescription;	
}
