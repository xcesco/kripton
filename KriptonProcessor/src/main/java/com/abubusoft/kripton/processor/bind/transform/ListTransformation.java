package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.XMLEvent;

import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class ListTransformation extends AbstractBindTransform {

	static Converter<String, String> nc = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);

	protected Class<?> utilClazz;
	private ParameterizedTypeName listTypeName;
	private TypeName rawTypeName;

	public ListTransformation(ParameterizedTypeName clazz) {
		this.utilClazz = ProcessorHelper.class;

		this.listTypeName = clazz;
		this.rawTypeName = listTypeName.typeArguments.get(0);
	}

	private Class<?> defineListClass(ParameterizedTypeName listTypeName) {
		if (listTypeName.toString().startsWith(List.class.getCanonicalName())) {
			// it's a list
			return ArrayList.class;
		}
		try {
			return Class.forName(listTypeName.rawType.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		methodBuilder.addStatement("$T<$T> list=new $T<>()", ArrayList.class, TypeUtility.className(property.getPropertyType().getComposedValue()), ArrayList.class);
		methodBuilder.addStatement("$T item", TypeUtility.className(property.getPropertyType().getComposedValue()));
			
		BindTransform transform=BindTransformer.lookup(rawTypeName);
		BindProperty elementProperty=BindProperty.builder(rawTypeName, property).build();
		
		if (!property.xmlInfo.isWrappedCollection())
		{
			methodBuilder.addCode("// add first element\n");
			transform.generateParseOnXml(methodBuilder, parserName, null, "item", elementProperty);
			methodBuilder.addStatement("list.add(item)");
		}
		
		methodBuilder.beginControlFlow("while ($L.nextTag() != XMLEvent.END_ELEMENT && !$L.getName().toString().equals($S))", parserName, parserName, property.xmlInfo.tag);
			
		
			methodBuilder.beginControlFlow("if (xmlParser.getName().toString().equals($S))", property.xmlInfo.tagElement);
			methodBuilder.beginControlFlow("if (xmlParser.isEmptyElement())
            {
          	  item=xmlParser.getElementAsDouble();
            } else {
          	  item=null;
            }
			
				transform.generateParseOnXml(methodBuilder, parserName, null, "item", elementProperty);
				methodBuilder.addStatement("list.add(item)");
			methodBuilder.endControlFlow();
		methodBuilder.endControlFlow();
    	                 	
		methodBuilder.addStatement(setter(beanClass, beanName, property, "list"));			
	}

	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
			methodBuilder.addStatement("int n=$L.size()", getter(beanName, beanClass, property));
			methodBuilder.addStatement("$T item", rawTypeName);
			
			if (property.xmlInfo.isWrappedCollection())
			{
				methodBuilder.addCode("// write wrapper tag\n");
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
			}
			
			BindTransform transform=BindTransformer.lookup(rawTypeName);
			BindProperty elementProperty=BindProperty.builder(rawTypeName, property).build();
			
			methodBuilder.beginControlFlow("for (int i=0; i<n; i++)");
				methodBuilder.addStatement("item=$L.get(i)", getter(beanName, beanClass, property));
				methodBuilder.beginControlFlow("if (item==null)");
					methodBuilder.addStatement("$L.writeEmptyElement($S)", serializerName, property.xmlInfo.tagElement);
				methodBuilder.nextControlFlow("else");
					transform.generateSerializeOnXml(methodBuilder, serializerName, null, "item", elementProperty);
				methodBuilder.endControlFlow();
			methodBuilder.endControlFlow();
			
			if (property.xmlInfo.isWrappedCollection())
			{
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);
			}
			
			
//			methodBuilder.addCode("$L.putString($S,$T.asString($L." + getter(beanClass, property) + "))", editorName, property.getName(), utilClazz, beanName);
//			methodBuilder.addCode(";");
//			methodBuilder.addCode(" else ");
//			methodBuilder.addCode("$L.putString($S, null)", editorName, property.getName());
			methodBuilder.endControlFlow();
/*
			if (object.valueStringList!=null)
			{
				String item;				
								
				//xmlSerializer.writeStartElement("valueStringList");				
				for (int i=0; i<object.valueStringList.size();i++)
				{
					item=object.valueStringList.get(i);
					
					if (item==null)
					{
						xmlSerializer.writeEmptyElement("item");
					} else {
						xmlSerializer.writeStartElement("valueStringList");						
							xmlSerializer.writeCharacters(item);
						xmlSerializer.writeEndElement();
					}
				}
				//xmlSerializer.writeEndElement();
			}*/
	}

	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateParseOnJackson(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void generateParseOnJacksonAsString(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		
	}

}
