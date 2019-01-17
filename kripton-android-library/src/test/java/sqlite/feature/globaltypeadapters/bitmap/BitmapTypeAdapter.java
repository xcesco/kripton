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
package sqlite.feature.globaltypeadapters.bitmap;

import java.io.ByteArrayOutputStream;

import com.abubusoft.kripton.android.SqlTypeAdapter;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class BitmapTypeAdapter.
 */
public class BitmapTypeAdapter implements SqlTypeAdapter<Bitmap, byte[]> {

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.TypeAdapter#toJava(java.lang.Object)
	 */
	@Override
	public Bitmap toJava(byte[] dataValue) {
		if (dataValue == null)
			return null;
		return BitmapFactory.decodeByteArray(dataValue, 0, dataValue.length);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.TypeAdapter#toData(java.lang.Object)
	 */
	@Override
	public byte[] toData(Bitmap bitmap) {
		if (bitmap == null)
			return null;

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		return stream.toByteArray();
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.SqlTypeAdapter#toString(java.lang.Object)
	 */
	@Override
	public String toString(Bitmap javaValue) {
		throw (new KriptonRuntimeException("Unsupported operation!"));
	}

}
