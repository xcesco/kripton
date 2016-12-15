package com.abubusoft.kripton.example02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.retrofit2.KriptonBinderConverterFactory;

import retrofit2.Retrofit;

public class Main {

	public static void main(String[] args) throws IOException {
		Prestazione bean=new Prestazione();
		bean.prenotationType=new Prenotabilita();
		bean.description="test";
		
		List<Prestazione> list=new ArrayList<>();
		//list.add(bean);
		//list.add(bean);
		
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://servizionline.sanita.fvg.it/prenotazioni/services/")
			    .addConverterFactory(KriptonBinderConverterFactory.create())
			    .build();

		PrenotazioniService service = retrofit.create(PrenotazioniService.class);
		list=service.list("vis").execute().body();
				
		System.out.print(list.size());
	}

}
