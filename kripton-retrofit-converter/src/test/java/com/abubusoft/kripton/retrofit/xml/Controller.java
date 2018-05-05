package com.abubusoft.kripton.retrofit.xml;

import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.retrofit2.KriptonBinderConverterFactory;

import retrofit2.Call;
import retrofit2.Retrofit;

public class Controller {

	VogellaAPI vogellaAPI;
	static final String BASE_URL = "http://vogella.com/";

	public Controller() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
				.addConverterFactory(KriptonBinderConverterFactory.create(BinderType.XML)).build();

		vogellaAPI = retrofit.create(VogellaAPI.class);
	}

	public Call<RSSFeed> execute() {
		Call<RSSFeed> call = vogellaAPI.loadRSSFeed();
		return call;
	}
}
