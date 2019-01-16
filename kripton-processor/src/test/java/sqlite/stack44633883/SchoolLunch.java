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
package sqlite.stack44633883;

import java.util.HashSet;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;

// TODO: Auto-generated Javadoc
/**
 * The Class SchoolLunch.
 */
@BindSqlType(name = "SchoolLunches")
public class SchoolLunch {
    
    /** The lunch id. */
    @BindSqlColumn(columnType=ColumnType.PRIMARY_KEY)
    private long lunchId;

    /**
     * Gets the lunch id.
     *
     * @return the lunch id
     */
    public long getLunchId() {
		return lunchId;
	}

	/**
	 * Sets the lunch id.
	 *
	 * @param lunchId the new lunch id
	 */
	public void setLunchId(long lunchId) {
		this.lunchId = lunchId;
	}

	/** The fresh. */
	private boolean fresh;

    /**
     * Checks if is fresh.
     *
     * @return true, if is fresh
     */
    public boolean isFresh() {
		return fresh;
	}

	/**
	 * Sets the fresh.
	 *
	 * @param fresh the new fresh
	 */
	public void setFresh(boolean fresh) {
		this.fresh = fresh;
	}

	/** The contains meat. */
	private boolean containsMeat;

    /** The fruits. */
    private HashSet<String> fruits;

    /**
     * Checks if is contains meat.
     *
     * @return true, if is contains meat
     */
    public boolean isContainsMeat() {
        return containsMeat;
    }

    /**
     * Sets the contains meat.
     *
     * @param containsMeat the new contains meat
     */
    public void setContainsMeat(boolean containsMeat) {
        this.containsMeat = containsMeat;
    }

    /**
     * Gets the fruits.
     *
     * @return the fruits
     */
    public HashSet<String> getFruits() {
        return fruits;
    }

    /**
     * Sets the fruits.
     *
     * @param fruits the new fruits
     */
    public void setFruits(HashSet<String> fruits) {
        this.fruits = fruits;
    }
}
