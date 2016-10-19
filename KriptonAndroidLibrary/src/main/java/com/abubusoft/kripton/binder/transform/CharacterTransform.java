package com.abubusoft.kripton.binder.transform;

/**
 * Transformer between a string and a Java Character object
 * 
 * @author bulldog
 *
 */
class CharacterTransform implements Transform<Character> {

	public Character read(String value) throws Exception {
	      if(value.length() != 1) {
	          throw new IllegalArgumentException("Cannot transfrom " + value + " to a character");
	       }
	       return value.charAt(0);   
	}

	public String write(Character value) throws Exception {
	      return value.toString();
	}

}
