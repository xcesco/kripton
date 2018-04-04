package com.abubusoft.kripton.example04;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;

public class Main {

	public static void main(String[] args) {
		KriptonJsonContext context = KriptonBinder.jsonBind();

		User user1 = new User();
		user1.name = "name1";
		user1.email = "dummy@gmail.com";
		user1.id=1;
		user1.phone="032123456";
		user1.website="http://www.html.it";
	

		System.out.println(context.serialize(user1));

	}

}
