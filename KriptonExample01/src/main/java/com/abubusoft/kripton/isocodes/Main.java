package com.abubusoft.kripton.isocodes;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.isocodes.input.InputCountry;
import com.abubusoft.kripton.isocodes.output.Country;
import com.abubusoft.kripton.isocodes.output.Translation;

public class Main {

	public static void main(String[] args) throws Exception {
		List<InputCountry> list=KriptonBinder.jsonBind().parseList(Main.class.getClassLoader().getResourceAsStream("isocodes/contries.json"), InputCountry.class);
		
		//System.out.println(list);
				
		List<Country> outputList=new ArrayList<>();		
		for (InputCountry item: list)
		{
			Country country=new Country();
			
			if (item.callingCode==null || item.callingCode.size()!=1) continue;			
			
			country.name=item.name.common;
			country.code=item.cca2;
			country.area=item.area;
			country.callingCode=item.callingCode.get(0);
			country.region=item.region;
			if (item.translations.fra!=null) country.translatedName.put(Translation.FR, item.translations.fra.common);
			if (item.translations.deu!=null) country.translatedName.put(Translation.GE, item.translations.deu.common);
			if (item.translations.ita!=null) country.translatedName.put(Translation.IT, item.translations.ita.common);
			if (item.translations.jpn!=null) country.translatedName.put(Translation.JP, item.translations.jpn.common);
			if (item.translations.rus!=null) country.translatedName.put(Translation.RU, item.translations.rus.common);
			if (item.translations.spa!=null) country.translatedName.put(Translation.ES, item.translations.spa.common);
			
			outputList.add(country);
		}		
		
		File file=new File("iso_countries.json");
		System.out.println(file.getAbsolutePath());
		KriptonBinder.jsonBind().serializeCollection(outputList, Country.class, new FileOutputStream(file));
	}

}
