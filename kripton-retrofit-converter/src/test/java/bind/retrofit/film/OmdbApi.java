package bind.retrofit.film;

import bind.retrofit.film.model.FilmDetail;
import bind.retrofit.film.model.Search;
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