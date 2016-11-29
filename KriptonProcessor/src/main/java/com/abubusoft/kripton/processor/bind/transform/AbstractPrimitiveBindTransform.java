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

import com.abubusoft.kripton.binder.xml.XmlType;
import com.abubusoft.kripton.common.PrimitiveUtil;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a Java Byte object
 * 
 * @author bulldog
 *
 */
abstract class AbstractPrimitiveBindTransform extends AbstractBindTransform {

	public AbstractPrimitiveBindTransform(boolean nullable) {
		this.nullable = nullable;
	}

	protected boolean nullable;

	protected String XML_TYPE;
	protected String PRIMITIVE_UTILITY_TYPE;
	protected String XML_CAST_TYPE = "";
	protected String XML_ATTRIBUTE_METHOD_PRE;
	protected String XML_ATTRIBUTE_METHOD_POST;

	protected String JSON_TYPE;
	protected String JSON_PARSER_METHOD;

	@Override
	public void generateParseOnXml(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;

		switch (xmlType) {
		case ATTRIBUTE:
			
			//methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.getAttributeAs$L(attributeIndex, $L).$L()"),XML_CAST_TYPE, PrimitiveUtil.class, PRIMITIVE_UTILITY_TYPE, DEFAULT_VALUE);
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.getAttributeAs$L(attributeIndex).$LValue()"),parserName, XML_ATTRIBUTE_METHOD_PRE, XML_ATTRIBUTE_METHOD_POST);
			/*String nullValue = nullable ? "null" : "\'\\0\'";
			if (CharacterTransform.CHAR_CAST_CONST.equals(XML_CAST_TYPE)) {
				methodBuilder.addStatement(setter(beanClass, beanName, property, "attributeValue.length()>0 ? attributeValue.charAt(0) : $L"), nullValue);
			} else {
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$L$L.valueOf(attributeValue)"), XML_CAST_TYPE, XML_UTILITY_TYPE);
			}*/
			break;
		case TAG:
			//methodBuilder.addStatement(setter(beanClass, beanName, property, "$L$L.getElementAs$L()"), XML_CAST_TYPE, parserName, XML_TYPE);
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$L$T.read$L($L.getElementAs$L(), $L)"),XML_CAST_TYPE, PrimitiveUtil.class, PRIMITIVE_UTILITY_TYPE, parserName, XML_TYPE, DEFAULT_VALUE);
			break;
		case VALUE:
		case VALUE_CDATA:
			//methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.valueOf($L.getText())"), XML_UTILITY_TYPE, parserName);
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$L$T.read$L($L.getText(), $L)"),XML_CAST_TYPE, PrimitiveUtil.class, PRIMITIVE_UTILITY_TYPE, parserName, DEFAULT_VALUE);
			break;
		default:
			break;
		}
	}

	@Override
	public void generateSerializeOnXml(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;
		if (nullable && property.isNullable() && !property.isInCollection()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		switch (xmlType) {
		case ATTRIBUTE:
			methodBuilder.addStatement("$L.writeAttribute($S, $T.write$L($L))", serializerName, property.xmlInfo.tag, PrimitiveUtil.class, PRIMITIVE_UTILITY_TYPE, getter(beanName, beanClass, property));
			break;
		case TAG:
			// value don't need to be converted into string
			methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.xmlInfo.tag);
			methodBuilder.addStatement("$L.write$L($L)", serializerName, XML_TYPE, getter(beanName, beanClass, property));
			methodBuilder.addStatement("$L.writeEndElement()", serializerName);			
			break;
		case VALUE:
			// value don't need to be converted into string
			methodBuilder.addStatement("$L.write$L($L)", serializerName, property.xmlInfo.tag, XML_TYPE, getter(beanName, beanClass, property));
			break;
		case VALUE_CDATA:
			methodBuilder.addStatement("$L.writeCData($T.write$L($L))", serializerName, PrimitiveUtil.class, PRIMITIVE_UTILITY_TYPE, getter(beanName, beanClass, property));
			break;
		}

		if (nullable && property.isNullable() && !property.isInCollection()) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateSerializeOnJackson(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (nullable && property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		// in a collection we need to insert only value, not field name
		if (property.isInCollection()) {
			methodBuilder.addStatement("$L.write$L($L)", serializerName, JSON_TYPE, getter(beanName, beanClass, property));
		} else {
			methodBuilder.addStatement("$L.write$LField($S, $L)", serializerName, JSON_TYPE, property.jacksonName, getter(beanName, beanClass, property));
		}

		if (nullable && property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateSerializeOnJacksonAsString(MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (nullable && property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		// we need to write only value
		if (property.isInCollection()) {
			methodBuilder.addStatement("$L.writeString($T.write$L($L))", serializerName, PrimitiveUtil.class, PRIMITIVE_UTILITY_TYPE, getter(beanName, beanClass, property));
		} else {
			methodBuilder.addStatement("$L.writeStringField($S, $T.write$L($L))", serializerName, property.jacksonName, PrimitiveUtil.class, PRIMITIVE_UTILITY_TYPE, getter(beanName, beanClass, property));
		}

		if (nullable && property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateParseOnJackson(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (nullable && property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}

		if (CharacterTransform.CHAR_CAST_CONST.equals(XML_CAST_TYPE)) {
			methodBuilder.addStatement(setter(beanClass, beanName, property, "Character.valueOf((char)$L.$L())"), parserName, JSON_PARSER_METHOD);
		} else {
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.$L()"), parserName, JSON_PARSER_METHOD);
		}

		if (nullable && property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateParseOnJacksonAsString(MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (nullable && property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}

		//if (CharacterTransform.CHAR_CAST_CONST.equals(XML_CAST_TYPE)) {
			//methodBuilder.addStatement(setter(beanClass, beanName, property, "Character.valueOf((char)(int)Integer.valueOf($L.getText()))"), parserName);
		//} else {
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read$L($L.getText(), $L)"), PrimitiveUtil.class, PRIMITIVE_UTILITY_TYPE, parserName, DEFAULT_VALUE);
		//}

		if (nullable && property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}
}
