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
package sqlite.kripton111.model;

import android.graphics.Bitmap;

import java.util.LinkedHashMap;
import java.util.Map;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class Country.
 */
@BindType
public class Country {

    /** The id. */
    @BindColumn(columnType = ColumnType.PRIMARY_KEY)
	public long id;

	/** The area. */
	public long area;

    /** The code. */
    @BindColumn(nullable = false, columnType = ColumnType.UNIQUE)
	public String code;

    /** The calling code. */
    @BindColumn(nullable = false)
	public String callingCode;

	/** The region. */
	public String region;

    /** The name. */
    @BindColumn(nullable = false)
	public String name;


    /** The bitmap. */
    @BindDisabled
    public Bitmap bitmap;
}
