package com.abubusoft.kripton.example01;

import com.abubusoft.kripton.binder.BinderType;
import com.abubusoft.kripton.binder.KriptonBinder;
import com.abubusoft.kripton.binder.context.KriptonXmlContext;

public class Main {

	public static void main(String[] args) {
		Bean bean = new Bean();
		bean.description = "hello";

		// registry xml
		KriptonBinder.registryBinder(new KriptonXmlContext());

		{
			String result = KriptonBinder.getJsonBinderContext().serialize(bean);
			System.out.println(result);
		}

		{
			String result = KriptonBinder.getBinder(BinderType.XML).serialize(bean);
			System.out.println(result);
		}
	}

}
