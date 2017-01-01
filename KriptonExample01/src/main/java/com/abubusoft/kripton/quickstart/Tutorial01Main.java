package com.abubusoft.kripton.quickstart;

import java.io.IOException;
import java.util.List;

import com.abubusoft.kripton.retrofit2.KriptonBinderConverterFactory;

import retrofit2.Retrofit;

public class Tutorial01Main {

	public static void main(String[] args) throws IOException {
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(KriptonBinderConverterFactory.create()).build();

		QuickStartService service = retrofit.create(QuickStartService.class);
		List<User> list = service.list().execute().body();

		System.out.println(list.size());
	}
}
