package com.abubusoft.kripton.example02;

import java.util.Arrays;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Prestazione {

	@Bind("codicePrestazione")
	public String code;
	
	public String info;
	
	@Bind("descrizione")
	public String description;
	
	public String[] tags;
	
	@Bind("prenotabilita")
	public Prenotabilita prenotationType;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Prestazione [");
		if (code != null) {
			builder.append("code=");
			builder.append(code);
			builder.append(", ");
		}
		if (info != null) {
			builder.append("info=");
			builder.append(info);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (tags != null) {
			builder.append("tags=");
			builder.append(Arrays.toString(tags));
			builder.append(", ");
		}
		if (prenotationType != null) {
			builder.append("prenotationType=");
			builder.append(prenotationType);
		}
		builder.append("]");
		return builder.toString();
	}
}
