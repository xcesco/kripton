package bind.retrofit.film.model;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Rating {
	/**
	 * @param source
	 * @param value
	 */
	public Rating(String source, String value) {
		super();
		this.source = source;
		this.value = value;
	}


	private String source;

	
	private String value;


	public String getSource() {
		return source;
	}


	public String getValue() {
		return value;
	}

	
}
