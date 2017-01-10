package com.abubusoft.kriptonquickstart;

import com.abubusoft.kriptonquickstart.model.Post;
import com.abubusoft.kriptonquickstart.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuickStartService {

	// https://jsonplaceholder.typicode.com/posts
	@GET("posts")
	Call<List<Post>> listPosts();

	@GET("users")
	Call<List<User>> listUsers();
}
