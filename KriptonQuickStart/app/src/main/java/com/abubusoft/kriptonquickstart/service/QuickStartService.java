package com.abubusoft.kriptonquickstart.service;

import com.abubusoft.kriptonquickstart.model.Post;
import com.abubusoft.kriptonquickstart.model.Todo;
import com.abubusoft.kriptonquickstart.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuickStartService {

	// https://jsonplaceholder.typicode.com/posts
	@GET("posts")
	Call<List<Post>> listPosts(@Query("userId") long userId);

	@GET("posts")
	Call<List<Todo>> listTodos(@Query("userId") long userId);

	@GET("users")
	Call<List<User>> listUsers();
}
