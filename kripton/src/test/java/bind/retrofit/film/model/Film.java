package bind.retrofit.film.model;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Film {
	private String title;

	private String year;

	private String imdbID;

	/**
	 * @param title
	 * @param year
	 * @param imdbID
	 * @param type
	 * @param poster
	 */
	public Film(String title, String year, String imdbID, String type, String poster) {
		super();
		this.title = title;
		this.year = year;
		this.imdbID = imdbID;
		this.type = type;
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public String getYear() {
		return year;
	}

	public String getImdbID() {
		return imdbID;
	}

	public String getType() {
		return type;
	}

	public String getPoster() {
		return poster;
	}

	private String type;

	private String poster;

}
