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
/**
 * 
 */
package com.abubusoft.kripton.android.commons;

import com.abubusoft.kripton.common.Base64Util;




/**
 * Classe per decodificare/codificare delle stringhe. Si basa su base64, ma
 * utilizza qualche piccolo accorgimento, tanto per rendere la cosa un pò più
 * complicata per chi vuole hackarare la faccenda.
 * 
 * @author Francesco Benincasa
 * 
 */
public abstract class SimpleCodec {


	/**
	 * Decodes data from Base64 notation.
	 * 
	 * @param s
	 *            the string to decode (decoded in default encoding)
	 * @return the decoded data
	 */
	public static String encode(String s) {
		byte[] source = s.getBytes();

		invertElement(source);

		String result = Base64Util.encode(source);

		return result;
	}

	/**
	 * Encodes a byte array into Base64 notation. Equivalent to calling
	 * {@code encodeBytes(source, 0, source.length)}
	 * 
	 * @param source
	 *            The data to convert
	 * @since 1.4
	 */
	public static String decode(String source) {
		byte[] temp = Base64Util.decode(source);

		invertElement(temp);
		return new String(temp);
	}

	private static void invertElement(byte[] source) {
		byte temp;
		for (int i = 0; i < source.length / 2; i++) {
			temp = source[i];
			source[i] = source[source.length - 1 - i];
			source[source.length - 1 - i] = temp;
		}
	}
}
