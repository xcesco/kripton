package com.abubusoft.kripton.example02;

import com.abubusoft.kripton.annotation.BindType;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
@BindType
public class Prenotabilita {

	public boolean onLine;
	
	public boolean callCenter;
	
	public boolean sportelliCup;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Prenotabilita [onLine=");
		builder.append(onLine);
		builder.append(", callCenter=");
		builder.append(callCenter);
		builder.append(", sportelliCup=");
		builder.append(sportelliCup);
		builder.append("]");
		return builder.toString();
	}
}
