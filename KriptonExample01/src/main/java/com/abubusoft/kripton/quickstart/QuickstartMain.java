package com.abubusoft.kripton.quickstart;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.abubusoft.benchmark.Main;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.quickstart.model.Todo;

public class QuickstartMain {

	public static void main(String[] args) throws IOException {
		/*
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(KriptonBinderConverterFactory.create()).build();

		QuickStartService service = retrofit.create(QuickStartService.class);
		List<User> list = service.listUsers().execute().body();

		System.out.println(list.size());*/
		
		URL base = Main.class.getClassLoader().getResource("quickstart/todos.json");
		String inputTodo = IOUtils.toString(base, Charset.forName("UTF-8"));
		
		List<Todo> result = KriptonBinder.jsonBind().parseList(Todo.class, inputTodo);
		System.out.println(result);

	}
	
	
}
