package com.abubusoft.kripton.quickstart.service;

import com.abubusoft.kripton.quickstart.model.Comment;
import com.abubusoft.kripton.quickstart.model.Post;
import com.abubusoft.kripton.quickstart.model.Todo;
import com.abubusoft.kripton.quickstart.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuickStartService {

	// https://jsonplaceholder.typicode.com/posts
	@GET("posts")
	Call<List<Post>> listPosts(@Query("userId") long userId);

	@GET("todos")
	Call<List<Todo>> listTodos(@Query("userId") long userId);

	@GET("comments")
	Call<List<Comment>> listComments(@Query("userId") long userId);

	@GET("users")
	Call<List<User>> listUsers();
}
