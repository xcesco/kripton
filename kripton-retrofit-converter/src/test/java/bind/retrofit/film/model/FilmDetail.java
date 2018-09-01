package bind.retrofit.film.model;

import java.util.List;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class FilmDetail {
	private String title;

	private String year;

	private String rated;

	private String released;

	private String runtime;

	private String genre;

	private String director;

	private String writer;

	private String actors;

	private String plot;

	private String language;

	private String country;

	private String awards;

	private String poster;

	private List<Rating> ratings;

	public String getTitle() {
		return title;
	}

	public String getYear() {
		return year;
	}

	/**
	 * @param title
	 * @param year
	 * @param rated
	 * @param released
	 * @param runtime
	 * @param genre
	 * @param director
	 * @param writer
	 * @param actors
	 * @param plot
	 * @param language
	 * @param country
	 * @param awards
	 * @param poster
	 * @param ratings
	 * @param metascore
	 * @param imdbRating
	 * @param imdbVotes
	 * @param imdbID
	 * @param type
	 * @param dVD
	 * @param boxOffice
	 * @param production
	 * @param website
	 * @param response
	 */
	public FilmDetail(String title, String year, String rated, String released, String runtime, String genre,
			String director, String writer, String actors, String plot, String language, String country, String awards,
			String poster, List<Rating> ratings, String metascore, String imdbRating, String imdbVotes, String imdbID,
			String type, String dVD, String boxOffice, String production, String website, String response) {
		super();
		this.title = title;
		this.year = year;
		this.rated = rated;
		this.released = released;
		this.runtime = runtime;
		this.genre = genre;
		this.director = director;
		this.writer = writer;
		this.actors = actors;
		this.plot = plot;
		this.language = language;
		this.country = country;
		this.awards = awards;
		this.poster = poster;
		this.ratings = ratings;
		this.metascore = metascore;
		this.imdbRating = imdbRating;
		this.imdbVotes = imdbVotes;
		this.imdbID = imdbID;
		this.type = type;
		this.dVD = dVD;
		this.boxOffice = boxOffice;
		this.production = production;
		this.website = website;
		this.response = response;
	}

	public String getRated() {
		return rated;
	}

	public String getReleased() {
		return released;
	}

	public String getRuntime() {
		return runtime;
	}

	public String getGenre() {
		return genre;
	}

	public String getDirector() {
		return director;
	}

	public String getWriter() {
		return writer;
	}

	public String getActors() {
		return actors;
	}

	public String getPlot() {
		return plot;
	}

	public String getLanguage() {
		return language;
	}

	public String getCountry() {
		return country;
	}

	public String getAwards() {
		return awards;
	}

	public String getPoster() {
		return poster;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public String getMetascore() {
		return metascore;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public String getImdbVotes() {
		return imdbVotes;
	}

	public String getImdbID() {
		return imdbID;
	}

	public String getType() {
		return type;
	}

	public String getDVD() {
		return dVD;
	}

	public String getBoxOffice() {
		return boxOffice;
	}

	public String getProduction() {
		return production;
	}

	public String getWebsite() {
		return website;
	}

	public String getResponse() {
		return response;
	}

	private String metascore;

	private String imdbRating;

	private String imdbVotes;

	private String imdbID;

	private String type;

	private String dVD;

	private String boxOffice;

	private String production;

	private String website;

	private String response;
}
