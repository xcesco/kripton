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
package shared.feature.typeadapter.case4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.android.sharedprefs.PreferenceTypeAdapter;


/**
 * The Class Set2ListTypeAdapter.
 */
public class Set2ListTypeAdapter implements PreferenceTypeAdapter<List<String>, Set<String>> {

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.TypeAdapter#toJava(java.lang.Object)
	 */
	@Override
	public List<String> toJava(Set<String> dataValue) {
		ArrayList<String> result=new ArrayList<String>();
		result.add("1");
		result.add("2");
		return result;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.TypeAdapter#toData(java.lang.Object)
	 */
	@Override
	public Set<String> toData(List<String> javaValue) {
		HashSet<String> result=new HashSet<>();
		
		result.add("1");
		result.add("2");
		
		return result;
	}



}
