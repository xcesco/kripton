package com.abubusoft.kripton.retrofit2;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.Test;

import com.abubusoft.kripton.BinderFactory;
import com.abubusoft.kripton.BinderJsonWriter;
import com.abubusoft.kripton.binder.json.JsonWriter;

import retrofit2.Retrofit;


public class TestConversion {
	
	public void log(String msg)
	{
		System.out.println(msg);
	}
	
	@Test
	public void test() throws IOException
	{
		/*
		 Retrofit retrofit = new Retrofit.Builder()
	                .baseUrl("http://servizionline.sanita.fvg.it/prenotazioni/services/")
	                .addConverterFactory(KriptonJsonConverterFactory.create())
	                .build();
	
	        EsamiService service = retrofit.create(EsamiService.class);
	
	        Set<Prenotazione> list = service.listPrenotazioni("ru").execute().body();
	        
	        log(""+list.size());*/
	}
	

	
}
