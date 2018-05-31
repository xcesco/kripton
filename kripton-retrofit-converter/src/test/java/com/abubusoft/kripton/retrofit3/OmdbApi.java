package com.abubusoft.kripton.retrofit3;

import com.abubusoft.kripton.retrofit3.model.FilmDetail;
import com.abubusoft.kripton.retrofit3.model.Search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface OmdbApi {
	@GET("./")
	@Headers({ "Cache-control: no-cache" })
	Call<Search> search(@Query("s") String search, @Query("apikey") String apiKey);

	@GET("./")
	@Headers({ "Cache-control: no-cache" })
	Call<FilmDetail> getFilm(@Query("i") String uid, @Query("apikey") String apiKey);
}