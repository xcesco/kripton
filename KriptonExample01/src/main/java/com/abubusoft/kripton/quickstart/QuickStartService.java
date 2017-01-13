package com.abubusoft.kripton.quickstart;

import java.util.List;

import com.abubusoft.kripton.quickstart.model.Post;
import com.abubusoft.kripton.quickstart.model.Todo;
import com.abubusoft.kripton.quickstart.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
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
