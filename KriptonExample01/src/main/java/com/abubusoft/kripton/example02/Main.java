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
		Person user = new Person();
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

			Person user2 = binderContext.parse(buffer, Person.class);

			System.out.println(buffer);
		}

		{
			BinderContext binderContext = KriptonBinder.bind(BinderType.XML);
			String buffer = binderContext.serialize(user);

			Person user2 = binderContext.parse(buffer, Person.class);

			System.out.println(buffer);
		}

		
		{
			
			
			Person person=new Person();
			
			person.email="tonj.manero@mail.com";
			person.name="Tonj";
			person.surname="Manero";
			person.username="tonj.manero";
									
			String result=KriptonBinder.jsonBind().serialize(person);
			System.out.println(result);
		}
		
		{

			BinderContext binderContext = KriptonBinder.bind(BinderType.CBOR);

			// We use this buffer as output for serialization
			KriptonByteArrayOutputStream buffer = new KriptonByteArrayOutputStream();
			binderContext.serialize(user, buffer);

			Person user2 = binderContext.parse(buffer.getByteBuffer(), Person.class);
			System.out.println(toString(buffer.getByteBufferCopy()));
		}

		{
			BinderContext binderContext = KriptonBinder.bind(BinderType.PROPERTIES);
			String buffer = binderContext.serialize(user);

			Person user2 = binderContext.parse(buffer, Person.class);
			System.out.println(buffer);
		}

		{
			BinderContext binderContext = KriptonBinder.bind(BinderType.YAML);
			String buffer = binderContext.serialize(user);

			Person user2 = binderContext.parse(buffer, Person.class);
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
