package com.abubusoft.kripton.example02;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PrenotazioniService {
	@GET("prestazioni")
	Call<List<Prestazione>> list(@Query("chiaveRicerca") String chiaveRicerca);
}