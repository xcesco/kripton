package com.abubusoft.kripton.retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface JsonPlaceHolderService {

	@POST("/posts/")
	Call<List<Post>> getAllPost();
}
