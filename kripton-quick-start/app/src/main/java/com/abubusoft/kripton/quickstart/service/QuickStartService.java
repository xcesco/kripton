package com.abubusoft.kripton.quickstart.service;

import com.abubusoft.kripton.quickstart.model.Album;
import com.abubusoft.kripton.quickstart.model.Comment;
import com.abubusoft.kripton.quickstart.model.Photo;
import com.abubusoft.kripton.quickstart.model.Post;
import com.abubusoft.kripton.quickstart.model.Todo;
import com.abubusoft.kripton.quickstart.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuickStartService {

	@GET("posts")
	Call<List<Post>> listPosts(@Query("userId") long userId);

	@GET("todos")
	Call<List<Todo>> listTodos(@Query("userId") long userId);

	@GET("comments")
	Call<List<Comment>> listComments(@Query("postId") long postId);

	@GET("albums")
	Call<List<Album>> listAlbums(@Query("userId") long userId);

	@GET("photos")
	Call<List<Photo>> listPhotos(@Query("albumId") long albumId);

	@GET("users")
	Call<List<User>> listUsers();
}
