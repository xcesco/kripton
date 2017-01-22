package com.abubusoft.kripton.example02;

import com.abubusoft.kripton.BinderContext;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonCborContext;
import com.abubusoft.kripton.KriptonPropertiesContext;
import com.abubusoft.kripton.KriptonXmlContext;
import com.abubusoft.kripton.KriptonYamlContext;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;

public class Main {

	public static void main(String[] args) throws Exception {
		User user = new User();
		user.email = "dummy@test.org";
		user.name = "Tonj";
		user.surname = "Manero";
		user.username = "1234";

		// registry xml
		KriptonBinder.registryBinder(new KriptonXmlContext());
		KriptonBinder.registryBinder(new KriptonCborContext());
		KriptonBinder.registryBinder(new KriptonPropertiesContext());
		KriptonBinder.registryBinder(new KriptonYamlContext());

		{
			BinderContext binderContext = KriptonBinder.jsonBind();
			String buffer = binderContext.serialize(user);

			User user2 = binderContext.parse(buffer, User.class);

			System.out.println(buffer);
		}

		{
			BinderContext binderContext = KriptonBinder.bind(BinderType.XML);
			String buffer = binderContext.serialize(user);

			User user2 = binderContext.parse(buffer, User.class);

			System.out.println(buffer);
		}

		{

			BinderContext binderContext = KriptonBinder.bind(BinderType.CBOR);

			// We use this buffer as output for serialization
			KriptonByteArrayOutputStream buffer = new KriptonByteArrayOutputStream();
			binderContext.serialize(user, buffer);

			User user2 = binderContext.parse(buffer.getByteBuffer(), User.class);
			System.out.println(toString(buffer.getByteBufferCopy()));
		}

		{
			BinderContext binderContext = KriptonBinder.bind(BinderType.PROPERTIES);
			String buffer = binderContext.serialize(user);

			User user2 = binderContext.parse(buffer, User.class);
			System.out.println(buffer);
		}

		{
			BinderContext binderContext = KriptonBinder.bind(BinderType.YAML);
			String buffer = binderContext.serialize(user);

			User user2 = binderContext.parse(buffer, User.class);
			System.out.println(buffer);
		}

	}

	static String toString(byte[] input) {
		StringBuilder buffer = new StringBuilder();
		for (int j = 0; j < input.length; j++) {
			buffer.append(String.format("%02X", input[j]));
		}
		return buffer.toString();
	}

}
