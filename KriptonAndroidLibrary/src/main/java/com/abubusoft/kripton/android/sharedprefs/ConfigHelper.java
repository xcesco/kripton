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
package com.abubusoft.kripton.android.sharedprefs;

import java.util.Date;

import com.abubusoft.kripton.android.commons.SimpleCodec;

public class ConfigHelper {

	private static final String SPLIT_SEPARATOR = "\\|\\|\\|";
	public static final String SEPARATOR = "|||";

	/**
	 * Codifica una stringa, aggiungendo informazioni di contorno, in modo da rendere più difficile la comprensione.
	 * 
	 * @param stringa
	 *            da codificare
	 * @return
	 */
	public static String encodedString(String secret) {

		long t1 = ((new Date()).getTime());

		String code = "" + t1 + SEPARATOR + secret + SEPARATOR + (t1 + 1001);

		String code1 = SimpleCodec.encode(code);

		return code1;
	}

	/**
	 * Data una stringa, decodifica il token in essa contenuto.
	 * 
	 * @param input
	 * @return
	 */
	public static String decodeString(String input) {
		try {
			String temp = SimpleCodec.decode(input);

			String[] temp1;
			temp1 = temp.split(SPLIT_SEPARATOR);

			String secret = temp1[1];

			return secret;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
