package com.abubusoft.kripton.retrofit3.model;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Film {
	private String Title;

	public String getTitle() {
		return this.Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	private String Year;

	public String getYear() {
		return this.Year;
	}

	public void setYear(String Year) {
		this.Year = Year;
	}

	private String imdbID;

	public String getImdbID() {
		return this.imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	private String Type;

	public String getType() {
		return this.Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}

	private String Poster;

	public String getPoster() {
		return this.Poster;
	}

	public void setPoster(String Poster) {
		this.Poster = Poster;
	}
}
