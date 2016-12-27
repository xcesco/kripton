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
package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer for generic object. For XML binding no Attribute o Value is
 * allowed.
 * 
 * @author bulldog
 *
 */
public class ObjectBindTransform extends AbstractBindTransform {

	public ObjectBindTransform() {
	}

	@Override
	public void generateParseOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {		
		String bindName=context.getBindMapperName(context, property);		
		methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.parseOnXml(xmlParser, eventType)"), bindName);
	}
	
	public boolean isTypeAdapterSupported() {
		return false;
	}

	@Override
	public void generateSerializeOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		String bindName=context.getBindMapperName(context, property);
		
		// @formatter:off
		if (property.isNullable() && !property.isInCollection()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}
				
		methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.label);
		methodBuilder.addStatement("$L.serializeOnXml($L, xmlSerializer, $L)", bindName, getter(beanName, beanClass, property), XmlPullParser.START_TAG);
		methodBuilder.addStatement("$L.writeEndElement()", serializerName);

		if (property.isNullable() && !property.isInCollection()) {
			methodBuilder.endControlFlow();
		}
		// @formatter:on
	}

	void generateSerializeInternal(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property, boolean onString) {
		String bindName=context.getBindMapperName(context, property);
		
		// @formatter:off
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		if (property.isProperty()) {
			methodBuilder.addStatement("fieldCount++");
		}

		if (!property.isInCollection()) {
			methodBuilder.addStatement("$L.writeFieldName($S)", serializerName, property.label);
		}

		if (onString) {
			methodBuilder.beginControlFlow("if ($L.serializeOnJacksonAsString($L, jacksonSerializer)==0)", bindName,
					getter(beanName, beanClass, property));
			// KRITPON-38: in a collection, for null object we write
			methodBuilder.addStatement("$L.writeNullField($S)", serializerName, property.label);
			methodBuilder.endControlFlow();
		} else {
			methodBuilder.addStatement("$L.serializeOnJackson($L, jacksonSerializer)", bindName, getter(beanName, beanClass, property));
		}

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}
		// @formatter:on
	}

	@Override
	public void generateSerializeOnJackson(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		generateSerializeInternal(context, methodBuilder, serializerName, beanClass, beanName, property, false);
	}

	@Override
	public void generateSerializeOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		generateSerializeInternal(context, methodBuilder, serializerName, beanClass, beanName, property, true);
	}

	@Override
	public void generateParseOnJackson(BindTypeContext context, Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		String bindName=context.getBindMapperName(context, property);
		
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()==$T.START_OBJECT)", parserName, JsonToken.class);
		}

		methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.parseOnJackson(jacksonParser)"), bindName);
		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateParseOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		String bindName=context.getBindMapperName(context, property);
		
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()==$T.START_OBJECT || $L.currentToken()==$T.VALUE_STRING)", parserName, JsonToken.class, parserName, JsonToken.class);
		}
		methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.parseOnJacksonAsString(jacksonParser)"), bindName);
		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}
}
