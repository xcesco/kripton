/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package kripton42faster;

import java.io.IOException;

//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public class RestaurantMapper /* implements BindMapper<Restaurant> */ {

	static RestaurantMapper instance;

	public static RestaurantMapper instance() {
		if (instance == null)
			instance = new RestaurantMapper();

		return instance;
	}

//	@Override
//	public void write(JsonGenerator generator, Restaurant source) throws IOException {
//		generator.writeStartObject();
//
//		generator.writeFieldName("id");
//		generator.writeNumber(source.id);
//
//		generator.writeFieldName("name");		
//		generator.writeString(source.name);
//
//		generator.writeFieldName("address");
//		generator.writeString(source.address);
//
//		generator.writeFieldName("longitude");
//		generator.writeNumber(source.longitude);
//
//		generator.writeFieldName("latitude");
//		generator.writeNumber(source.latitude);
//
//		generator.writeEndObject();
//	}
	
//	@Override
//	public void writeXml(ToXmlGenerator generator, Restaurant source) throws IOException {
//		generator.writeStartObject();
//
//		generator.setNextIsAttribute(true);
//		generator.writeFieldName("id");
//		generator.writeNumber(source.id);
//
//		generator.setNextIsAttribute(false);
//		generator.writeFieldName("name");		
//		generator.writeString(source.name);
//
//		generator.writeFieldName("address");
//		generator.writeString(source.address);
//
//		generator.writeFieldName("longitude");
//		generator.writeNumber(source.longitude);
//
//		generator.writeFieldName("latitude");
//		generator.writeNumber(source.latitude);
//
//		generator.writeEndObject();
		
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
		
		/*
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
	            	
	        	generator.writeEndObject();
        
	        generator.writeFieldName("location");
	        generator.writeString("<ciao>");
        
        generator.writeEndObject();*/
//	}
//
//	@Override
//	public Restaurant read() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
