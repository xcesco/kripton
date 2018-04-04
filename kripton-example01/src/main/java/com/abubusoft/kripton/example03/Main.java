package com.abubusoft.kripton.example03;

import java.util.HashMap;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;

public class Main {

	public static void main(String[] args) {
		KriptonJsonContext context = KriptonBinder.jsonBind();

		User user1 = new User();
		user1.name = "name1";
		user1.surname = "surname1";

		User user2 = new User();
		user2.map = new HashMap<>();
		user2.map.put(RelationShipType.CHILD, user1);

		System.out.println(context.serialize(user2));

	}

}
