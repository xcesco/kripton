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
package sqlite.kripton84;

import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean84 {
	
	public long id;
	
	public List<String> columnListString;
	
	public Map<Integer, String> columnMapIntegerString;
	
	public Character[] columnArrayChar;
	
	public char[] columnArrayCharType;
	
	//public Bean84 columnBean;
	
	public byte[] columnArrayByteType;
}
