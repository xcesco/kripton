package com.abubusoft.kripton.example01;

import com.abubusoft.kripton.binder.KriptonBinder;

public class Main {

	public static void main(String[] args) {
		Bean bean=new Bean();
		bean.description="hello";
		
		String result=KriptonBinder.getJsonBinderContext().serialize(bean);
		System.out.print(result);
	}

}
