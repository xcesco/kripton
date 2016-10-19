package com.abubusoft.kripton.binder.transform;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Transformer between a string and a java.util.Locale object
 * 
 * @author bulldog
 *
 */
public class LocaleTransform implements Transform<Locale> {
	
    private final Pattern pattern;
   
    public LocaleTransform() {
       this.pattern = Pattern.compile("_");
    }

    public Locale read(String locale) throws Exception {
        String[] list = pattern.split(locale);
        
        if(list.length < 1) {
           throw new IllegalArgumentException("Invalid locale " + locale);
        }
        return read(list);
     }
     
     private Locale read(String[] locale)  throws Exception {
        String[] list = new String[] {"", "", ""};
        
        for(int i = 0; i < list.length; i++) {
           if(i < locale.length) {         
              list[i] = locale[i];
           }
        }
        return new Locale(list[0], list[1], list[2]);
     }
     
     public String write(Locale locale) {
        return locale.toString();
     }

}
