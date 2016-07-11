package com.abubusoft.kripton.binder.transform;

import java.net.URL;

/**
 * Transformer between a string and a java.net.URL object
 * 
 * @author bulldog
 *
 */
public class UrlTransform implements Transform<URL> {

	public URL read(String value) throws Exception {
	    return new URL(value);
	}

	public String write(URL value) throws Exception {
	    return value.toString();
	}

}
