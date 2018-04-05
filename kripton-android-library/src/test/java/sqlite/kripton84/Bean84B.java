/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
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
package sqlite.kripton84;

import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class Bean84B.
 */
@BindType
public class Bean84B {
	
	/** The id. */
	public long id;
	
	/** The column bean. */
	public Bean84B2 columnBean;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnBean == null) ? 0 : columnBean.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bean84B other = (Bean84B) obj;
		if (columnBean == null) {
			if (other.columnBean != null)
				return false;
		} else if (!columnBean.equals(other.columnBean))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
}
