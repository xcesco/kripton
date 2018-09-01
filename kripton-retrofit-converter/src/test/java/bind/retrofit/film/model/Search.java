package bind.retrofit.film.model;

import java.util.List;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Search {
	private String response;

	private List<Film> search;

	/**
	 * @param response
	 * @param search
	 * @param totalResults
	 */
	public Search(String response, List<Film> search, String totalResults) {
		super();
		this.response = response;
		this.search = search;
		this.totalResults = totalResults;
	}

	private String totalResults;

	public String getResponse() {
		return response;
	}

	public List<Film> getSearch() {
		return search;
	}

	public String getTotalResults() {
		return totalResults;
	}

	
}
