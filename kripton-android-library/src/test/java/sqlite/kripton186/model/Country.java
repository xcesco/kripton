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
package sqlite.kripton186.model;

import android.graphics.Bitmap;

import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;


/**
 * The Class Country.
 */
@BindType
public class Country {

    /** The id. */
    @BindSqlColumn(columnType = ColumnType.PRIMARY_KEY)
	public long id;

	/** The area. */
	public long area;

    /** The code. */
    @BindSqlColumn(nullable = false, columnType = ColumnType.UNIQUE)
	public String code;

    /** The calling code. */
    @BindSqlColumn(nullable = false)
	public String callingCode;

	/** The region. */
	public String region;

    /** The name. */
    @BindSqlColumn(nullable = false)
	public String name;

	/** The translated name. */
	public Map<Translation, String> translatedName = new LinkedHashMap<Translation, String>();

	/**
	 * Checks for translation for.
	 *
	 * @param type the type
	 * @return true, if successful
	 */
	public boolean hasTranslationFor(Translation type) {
		return translatedName.containsKey(type);
	}

	/**
	 * Gets the translated name.
	 *
	 * @param language the language
	 * @return the translated name
	 */
	public String getTranslatedName(String language) {
		Translation t=null;
		language=language.toUpperCase();
		for (Translation item: Translation.values())
		{
			if (language.equals(item.toString()))
			{
				t = item;
				break;
			}
		}

		if (t != null && translatedName.get(t)!=null) {
			return translatedName.get(t);
		}



		return name;
	}

    /** The bitmap. */
    @BindDisabled
    public Bitmap bitmap;
}
