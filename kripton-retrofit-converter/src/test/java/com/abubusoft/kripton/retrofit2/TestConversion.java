package com.abubusoft.kripton.retrofit2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;

import retrofit2.Response;
import retrofit2.Retrofit;


public class TestConversion extends AbstractBaseTest {
	
	public void log(String msg)
	{
		System.out.println(msg);
	}
	
	
	@Test
	public void test2() {
		String input="\t[\n\t{\"userId\": 1,\"id\": 1, \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\"}]";
		
		KriptonJsonContext context = KriptonBinder.jsonBind();
		List a=context.parseCollection(input, new ArrayList<Post>(), Post.class);
		log(""+a.size());
	}
	
	@Test
	public void test() throws IOException
	{
	
		 Retrofit retrofit = new Retrofit.Builder()
	                .baseUrl("https://jsonplaceholder.typicode.com/")
	                .addConverterFactory(KriptonBinderConverterFactory.create())
	                .build();
	
		 	JsonPlaceHolderService service = retrofit.create(JsonPlaceHolderService.class);
	
	        //Response<List<Post>> response = service.getAllPost().execute();
	        
	        //log(response.toString());
	        
	        //log(""+list);
	}
	

	
}
