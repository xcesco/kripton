/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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

import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.TypeAdapterUtils;
import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.xml.XmlType;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;


/**
 * Transformer between a string and a Java Byte object.
 */
abstract class AbstractPrimitiveBindTransform extends AbstractBindTransform {

	/** The json parser method. */
	protected String JSON_PARSER_METHOD;

	/** The json type. */
	protected String JSON_TYPE;

	/** The nullable. */
	protected boolean nullable;
	
	/** The primitive utility type. */
	protected String PRIMITIVE_UTILITY_TYPE;
	
	/** The xml attribute method post. */
	protected String XML_ATTRIBUTE_METHOD_POST;
	
	/** The xml attribute method pre. */
	protected String XML_ATTRIBUTE_METHOD_PRE;
	
	/** The xml cast type. */
	protected String XML_CAST_TYPE = "";

	/** The xml type. */
	protected String XML_TYPE;

	/**
	 * Instantiates a new abstract primitive bind transform.
	 *
	 * @param nullable the nullable
	 */
	public AbstractPrimitiveBindTransform(boolean nullable) {
		this.nullable = nullable;
	}
	
	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#isTypeAdapterSupported()
	 */
	@Override
	public boolean isTypeAdapterSupported() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateParseOnJackson(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateParseOnJackson(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (nullable && property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}

		if (property.hasTypeAdapter()) {
			// there's an type adapter
			methodBuilder.addCode("// using type adapter $L\n", property.typeAdapter.adapterClazz);

			// no adapter is present
			if (CharacterBindTransform.CHAR_CAST_CONST.equals(XML_CAST_TYPE)) {
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "Character.valueOf((char)$L.$L())+POST_TYPE_ADAPTER"), TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), parserName, JSON_PARSER_METHOD);
			} else {
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$L.$L()" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), parserName, JSON_PARSER_METHOD);
			}

		} else {
			// no adapter is present
			if (CharacterBindTransform.CHAR_CAST_CONST.equals(XML_CAST_TYPE)) {
				methodBuilder.addStatement(setter(beanClass, beanName, property, "Character.valueOf((char)$L.$L())"), parserName, JSON_PARSER_METHOD);
			} else {
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.$L()"), parserName, JSON_PARSER_METHOD);
			}
		}

		if (nullable && property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateParseOnJacksonAsString(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateParseOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (nullable && property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}

		if (property.hasTypeAdapter()) {
			// there's an type adapter
			methodBuilder.addCode("// using type adapter $L\n", property.typeAdapter.adapterClazz);

			methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$T.read$L($L.getText(), $L)" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
					TypeUtility.typeName(property.typeAdapter.adapterClazz), PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE, parserName, DEFAULT_VALUE);
		} else {
			// without type adapter
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read$L($L.getText(), $L)"), PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE, parserName, DEFAULT_VALUE);
		}

		if (nullable && property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateParseOnXml(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateParseOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;

		if (property.hasTypeAdapter()) {
			// there's an type adapter
			methodBuilder.addCode("// using type adapter $L\n", property.typeAdapter.adapterClazz);

			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$L$T.read$L($L.getAttributeValue(attributeIndex), $L)" + POST_TYPE_ADAPTER),
						TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), XML_CAST_TYPE, PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE, parserName, DEFAULT_VALUE);
				break;
			case TAG:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$L$T.read$L($L.getElementAs$L(), $L)" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), XML_CAST_TYPE, PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE, parserName, XML_TYPE, DEFAULT_VALUE);
				break;
			case VALUE:
			case VALUE_CDATA:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$L$T.read$L($L.getText(), $L)" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), XML_CAST_TYPE, PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE, parserName, DEFAULT_VALUE);
				break;
			}
		} else {
			// without type adapter
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$L$T.read$L($L.getAttributeValue(attributeIndex), $L)"), XML_CAST_TYPE, PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE,
						parserName, DEFAULT_VALUE);
				break;
			case TAG:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$L$T.read$L($L.getElementAs$L(), $L)"), XML_CAST_TYPE, PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE, parserName,
						XML_TYPE, DEFAULT_VALUE);
				break;
			case VALUE:
			case VALUE_CDATA:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$L$T.read$L($L.getText(), $L)"), XML_CAST_TYPE, PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE, parserName,
						DEFAULT_VALUE);
				break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateSerializeOnJackson(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateSerializeOnJackson(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (nullable && property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		if (property.isProperty()) {
			methodBuilder.addStatement("fieldCount++");
		}

		if (property.hasTypeAdapter()) {
			// there's an type adapter
			methodBuilder.addCode("// using type adapter $L\n", property.typeAdapter.adapterClazz);

			// in a collection we need to insert only value, not field typeName
			if (property.isInCollection()) {
				methodBuilder.addStatement("$L.write$L("+PRE_TYPE_ADAPTER_TO_DATA+"$L"+POST_TYPE_ADAPTER+")", serializerName, JSON_TYPE, TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
			} else {
				methodBuilder.addStatement("$L.write$LField($S, "+PRE_TYPE_ADAPTER_TO_DATA+"$L"+POST_TYPE_ADAPTER+")", serializerName, JSON_TYPE, property.label, TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
			}
		} else {
			// there's no type adapter
			
			// in a collection we need to insert only value, not field typeName
			if (property.isInCollection()) {
				methodBuilder.addStatement("$L.write$L($L)", serializerName, JSON_TYPE, getter(beanName, beanClass, property));
			} else {
				methodBuilder.addStatement("$L.write$LField($S, $L)", serializerName, JSON_TYPE, property.label, getter(beanName, beanClass, property));
			}

		}

		if (nullable && property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateSerializeOnJacksonAsString(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateSerializeOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (nullable && property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		if (property.hasTypeAdapter()) {
			// there's an type adapter
			methodBuilder.addCode("// using type adapter $L\n", property.typeAdapter.adapterClazz);

			if (property.isInCollection()) {
				// in a collection we need to insert only value, not field typeName
				methodBuilder.addStatement("$L.writeString($T.write$L("+PRE_TYPE_ADAPTER_TO_DATA+"$L"+POST_TYPE_ADAPTER+"))", serializerName,PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE,  TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
			} else {
				// we need to write only value
				methodBuilder.addStatement("$L.writeStringField($S, $T.write$L("+PRE_TYPE_ADAPTER_TO_DATA+"$L"+POST_TYPE_ADAPTER+"))", serializerName, property.label, PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE,  TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
			}
		
		} else {
			// there's no type adapter
			
			if (property.isInCollection()) {
				// in a collection we need to insert only value, not field typeName
				methodBuilder.addStatement("$L.writeString($T.write$L($L))", serializerName, PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE, getter(beanName, beanClass, property));
			} else {
				// we need to write only value
				methodBuilder.addStatement("$L.writeStringField($S, $T.write$L($L))", serializerName, property.label, PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE, getter(beanName, beanClass, property));
			}
		}

		if (nullable && property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateSerializeOnXml(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateSerializeOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;
		if (nullable && property.isNullable() && !property.isInCollection()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		if (property.hasTypeAdapter()) {
			// there's an type adapter
			methodBuilder.addCode("// using type adapter $L\n", property.typeAdapter.adapterClazz);
			
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement("$L.writeAttribute($S, $T.write$L("+PRE_TYPE_ADAPTER_TO_DATA+"$L"+POST_TYPE_ADAPTER+"))", serializerName, BindProperty.xmlName(property), PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE, TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				break;
			case TAG:
				// value don't need to be converted into string
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, BindProperty.xmlName(property));
				methodBuilder.addStatement("$L.write$L("+PRE_TYPE_ADAPTER_TO_DATA+"$L"+POST_TYPE_ADAPTER+")", serializerName, XML_TYPE, TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);
				break;
			case VALUE:
				// value don't need to be converted into string
				methodBuilder.addStatement("$L.write$L("+PRE_TYPE_ADAPTER_TO_DATA+"$L"+POST_TYPE_ADAPTER+")", serializerName, XML_TYPE, TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				break;
			case VALUE_CDATA:
				methodBuilder.addStatement("$L.writeCData("+PRE_TYPE_ADAPTER_TO_DATA+"$T.write$L($L)"+POST_TYPE_ADAPTER+")", serializerName, PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE, TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				break;
			}
		
		} else {
			// there's no type adapter
		
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement("$L.writeAttribute($S, $T.write$L($L))", serializerName, BindProperty.xmlName(property), PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE, getter(beanName, beanClass, property));
				break;
			case TAG:
				// value don't need to be converted into string
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, BindProperty.xmlName(property));
				methodBuilder.addStatement("$L.write$L($L)", serializerName, XML_TYPE, getter(beanName, beanClass, property));
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);
				break;
			case VALUE:
				// value don't need to be converted into string
				methodBuilder.addStatement("$L.write$L($L)", serializerName, XML_TYPE, getter(beanName, beanClass, property));
				break;
			case VALUE_CDATA:
				methodBuilder.addStatement("$L.writeCData($T.write$L($L))", serializerName, PrimitiveUtils.class, PRIMITIVE_UTILITY_TYPE, getter(beanName, beanClass, property));
				break;
			}
		
		}

		if (nullable && property.isNullable() && !property.isInCollection()) {
			methodBuilder.endControlFlow();
		}
	}
}
