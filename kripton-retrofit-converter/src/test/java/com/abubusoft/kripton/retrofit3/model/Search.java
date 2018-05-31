package com.abubusoft.kripton.retrofit3.model;

import java.util.List;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Search {
	private List<Film> Search;

	public List<Film> getSearch() {
		return this.Search;
	}

	public void setSearch(List<Film> Search) {
		this.Search = Search;
	}

	private String totalResults;

	public String getTotalResults() {
		return this.totalResults;
	}

	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}

	private String Response;

	public String getResponse() {
		return this.Response;
	}

	public void setResponse(String Response) {
		this.Response = Response;
	}
}
