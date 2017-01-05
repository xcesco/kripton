package com.abubusoft.kriptonquickstart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuickStartService {

	// https://jsonplaceholder.typicode.com/posts
	@GET("posts")
	Call<List<User>> list();
}
