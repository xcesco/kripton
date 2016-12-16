package com.abubusoft.kripton.example01;

import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonXmlContext;

public class Main {

	public static void main(String[] args) {
		Bean bean = new Bean();
		bean.description = "hello";

		// registry xml
		KriptonBinder.registryBinder(new KriptonXmlContext());

		{
			String result = KriptonBinder.jsonBind().serialize(bean);
			System.out.println(result);
		}

		{
			String result = KriptonBinder.bind(BinderType.XML).serialize(bean);
			System.out.println(result);
		}
	}

}
