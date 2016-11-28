/**
 * 
 */
package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.binder.xml.XmlType;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * @author xcesco
 *
 */
public class WrappedCompileTimeTransform extends AbstractBindTransform {
	
	protected Class<?> utilClazz;

	public WrappedCompileTimeTransform(Class<?> utilClazz)
	{
		this.utilClazz=utilClazz;
	}
	
	@Override
	public void generateParseOnJackson(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable())
		{
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}
		methodBuilder.addStatement(setter(beanClass, beanName, property," $T.read($L.getText())"), utilClazz, parserName);
		
		if (property.isNullable())
		{
			methodBuilder.endControlFlow();
		}
		
	}
	
	@Override
	public void generateParseOnJacksonAsString(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable())
		{
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}
		methodBuilder.addStatement(setter(beanClass, beanName, property,"$T.read($L.getText())"), utilClazz, parserName);
		if (property.isNullable())
		{
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;
		
		switch (xmlType) {
		case ATTRIBUTE:
			methodBuilder.addStatement(setter(beanClass, beanName, property,"$T.read(attributeValue)"), utilClazz);
			break;
		case TAG:
			methodBuilder.addStatement(setter(beanClass, beanName, property,"$T.read($T.unescapeXml($L.getElementText()))"), utilClazz, StringEscapeUtils.class, parserName);
			break;
		case VALUE:
		case VALUE_CDATA:
			methodBuilder.addStatement(setter(beanClass, beanName, property,"$T.read($T.unescapeXml($L.getText()))"), utilClazz, StringEscapeUtils.class, parserName);			
			break;
		default:
			break;
		}

	}

	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable())
		{
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		
//		if (property.isElementInCollection())
//		{
//			// we need to write only value			
//			methodBuilder.addStatement("$L.writeString($T.write($L))", serializerName, utilClazz, getter(beanName, beanClass, property));
//		} else {		
			methodBuilder.addStatement("$L.writeStringField($S, $T.write($L))", serializerName, property.jacksonName, utilClazz, getter(beanName, beanClass, property));
		//}
		
		if (property.isNullable())
		{
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		generateSerializeOnJackson(methodBuilder, serializerName, beanClass, beanName, property);
	}
	
	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;
		
		if (property.isNullable() && !property.isElementInCollection())
		{
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
		switch (xmlType) {
		case ATTRIBUTE:
			methodBuilder.addStatement("$L.writeAttribute($S, $T.write($L))", serializerName, property.xmlInfo.tag, utilClazz, getter(beanName, beanClass, property));
			break;
		case TAG:
			methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
			methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($T.write($L)))", serializerName, StringEscapeUtils.class, utilClazz, getter(beanName, beanClass, property));
			methodBuilder.addStatement("$L.writeEndElement()", serializerName);
			break;
		case VALUE:
			methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($T.write($L)))", serializerName, StringEscapeUtils.class, utilClazz, getter(beanName, beanClass, property));
			break;
		case VALUE_CDATA:
			methodBuilder.addStatement("$L.writeCData($T.escapeXml10($T.write($L)))", serializerName, StringEscapeUtils.class, utilClazz, getter(beanName, beanClass, property));
			break;
		}

		if (property.isNullable() && !property.isElementInCollection())
		{
			methodBuilder.endControlFlow();
		}

	}
	
}