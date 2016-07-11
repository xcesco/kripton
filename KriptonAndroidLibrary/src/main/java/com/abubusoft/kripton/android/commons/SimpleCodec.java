/**
 * 
 */
package com.abubusoft.kripton.android.commons;

import com.abubusoft.kripton.common.Base64;




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

		String result = Base64.encode(source);

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
		byte[] temp = Base64.decode(source);

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
