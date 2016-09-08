package com.abubusoft.kripton.processor.kripton42;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public class RestaurantMapper implements BindMapper<Restaurant> {

	static RestaurantMapper instance;

	public static RestaurantMapper instance() {
		if (instance == null)
			instance = new RestaurantMapper();

		return instance;
	}

	@Override
	public void write(JsonGenerator generator, Restaurant source) throws IOException {
		generator.writeStartObject();

		generator.writeFieldName("id");
		generator.writeNumber(source.id);

		generator.writeFieldName("name");		
		generator.writeString(source.name);

		generator.writeFieldName("address");
		generator.writeString(source.address);

		generator.writeFieldName("longitude");
		generator.writeNumber(source.longitude);

		generator.writeFieldName("latitude");
		generator.writeNumber(source.latitude);

		generator.writeEndObject();
	}
	
	@Override
	public void writeXml(ToXmlGenerator generator, Restaurant source) throws IOException {
		/*
		generator.writeStartObject();

		generator.setNextIsAttribute(true);
		generator.writeFieldName("id");
		generator.writeNumber(source.id);
		generator.setNextIsAttribute(false);		
	
		
		generator.writeFieldName("name");	
		generator.writeStartObject();
		generator.writeString(source.name);		
		
		generator.writeFieldName("address");
		generator.writeString(source.address);
		generator.writeEndObject();

		generator.writeFieldName("longitude");
		generator.writeNumber(source.longitude);

		generator.writeFieldName("latitude");
		generator.writeNumber(source.latitude);
		

		generator.writeEndObject();
		
		*/
		generator.writeStartObject();		
			generator.setNextIsAttribute(true);
			 
		        // and also need to force attribute
		        generator.writeFieldName("attr");
		        generator.writeNumber(-3);
	        
		        // and also need to force attribute
		        generator.writeFieldName("attr2");
		        generator.writeNumber(-3);
	        generator.setNextIsAttribute(false);
        
        
	        generator.writeFieldName("elem");        
	        	generator.writeStartObject();
	        		generator.setNextIsAttribute(true);
	        			generator.writeFieldName("attr2");
	        			generator.writeNumber(-3);
	        		generator.setNextIsAttribute(false);
	        		
	        		generator.writeFieldName("elem");         		
	            	generator.writeStartObject();
	            		generator.setNextIsAttribute(true);
	            			generator.writeFieldName("attr2");
	            			generator.writeNumber(-3);            			            		
	            		generator.setNextIsAttribute(false);            		
	            	generator.writeEndObject();
	        		
	            	generator.writeRawValue("a2");
	            	
	        	generator.writeEndObject();
        
	        generator.writeFieldName("location");
	        generator.writeString("<ciao>");
        
        generator.writeEndObject();
	}

	@Override
	public Restaurant read() {
		// TODO Auto-generated method stub
		return null;
	}

}
