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

import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.TypeAdapterUtils;
import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.xml.XmlType;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;


/**
 * Transformer between a string and a Java String object.
 *
 * @author xcesco
 */
public class ByteArrayBindTransform extends AbstractBindTransform {

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateParseOnJackson(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateParseOnJackson(BindTypeContext context, Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJacksonInternal(context, methodBuilder, parserName, beanClass, beanName, property, false);

	}
	
	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#isTypeAdapterSupported()
	 */
	public boolean isTypeAdapterSupported() {
		return true;
	}

	/**
	 * Generate parse on jackson internal.
	 *
	 * @param context the context
	 * @param methodBuilder the method builder
	 * @param parserName the parser name
	 * @param beanClass the bean class
	 * @param beanName the bean name
	 * @param property the property
	 * @param onString the on string
	 */
	public void generateParseOnJacksonInternal(BindTypeContext context, Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property, boolean onString) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}

		if (property.hasTypeAdapter()) {

			if (onString) {
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$T.decode($L.getValueAsString())" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), Base64Utils.class, parserName);
			} else {
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$L.getBinaryValue()" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), parserName);
			}

		} else {

			if (onString) {
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.decode($L.getValueAsString())"), Base64Utils.class, parserName);
			} else {
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.getBinaryValue()"), parserName);
			}

		}

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}

	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateParseOnJacksonAsString(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateParseOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		generateParseOnJacksonInternal(context, methodBuilder, parserName, beanClass, beanName, property, true);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateParseOnXml(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateParseOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;

		if (property.hasTypeAdapter()) {

			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$T.decode($L.getAttributeValue(attributeIndex))" + POST_TYPE_ADAPTER),
						TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), Base64Utils.class, parserName);
				break;
			case TAG:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$L.getElementAsBinary()" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), parserName);
				break;
			case VALUE:
			case VALUE_CDATA:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$T.decode($L.getText())" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), Base64Utils.class, parserName);
				break;
			default:
				break;
			}

		} else {

			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.decode($L.getAttributeValue(attributeIndex))"), Base64Utils.class, parserName);
				break;
			case TAG:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$L.getElementAsBinary()"), parserName);
				break;
			case VALUE:
			case VALUE_CDATA:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.decode($L.getText())"), Base64Utils.class, parserName);
				break;
			default:
				break;
			}

		}

	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateSerializeOnJackson(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateSerializeOnJackson(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		if (property.isProperty()) {
			methodBuilder.addStatement("fieldCount++");
		}

		if (property.hasTypeAdapter()) {
			// in a collection we need to insert only value, not field typeName
			if (property.isInCollection()) {
				methodBuilder.addStatement("$L.writeBinary(" + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + ")", serializerName, TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
			} else {
				methodBuilder.addStatement("$L.writeBinaryField($S, " + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + ")", serializerName, property.label, TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
			}
		} else {
			// in a collection we need to insert only value, not field typeName
			if (property.isInCollection()) {
				methodBuilder.addStatement("$L.writeBinary($L)", serializerName, getter(beanName, beanClass, property));
			} else {
				methodBuilder.addStatement("$L.writeBinaryField($S, $L)", serializerName, property.label, getter(beanName, beanClass, property));
			}

		}

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateSerializeOnJacksonAsString(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateSerializeOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		generateSerializeOnJackson(context, methodBuilder, serializerName, beanClass, beanName, property);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.processor.bind.transform.BindTransform#generateSerializeOnXml(com.abubusoft.kripton.processor.bind.BindTypeContext, com.squareup.javapoet.MethodSpec.Builder, java.lang.String, com.squareup.javapoet.TypeName, java.lang.String, com.abubusoft.kripton.processor.bind.model.BindProperty)
	 */
	@Override
	public void generateSerializeOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;

		if (property.isNullable() && !property.isInCollection()) {
			methodBuilder.beginControlFlow("if ($L!=null)", getter(beanName, beanClass, property));
		}

		if (property.hasTypeAdapter()) {
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement("$L.writeAttribute($S, $T.encode(" + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + "))", serializerName, BindProperty.xmlName(property), Base64Utils.class,
						TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz),getter(beanName, beanClass, property));
				break;
			case TAG:
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, BindProperty.xmlName(property));
				methodBuilder.addStatement("$L.writeBinary(" + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + ")", serializerName, TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz),getter(beanName, beanClass, property));
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);
				break;
			case VALUE:
			case VALUE_CDATA:
				methodBuilder.addStatement("$L.writeBinary("+PRE_TYPE_ADAPTER_TO_DATA+"$L"+POST_TYPE_ADAPTER+")", serializerName, TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz),getter(beanName, beanClass, property));
				break;
			}
		} else {
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement("$L.writeAttribute($S, $T.encode($L))", serializerName, BindProperty.xmlName(property), Base64Utils.class, getter(beanName, beanClass, property));
				break;
			case TAG:
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, BindProperty.xmlName(property));
				methodBuilder.addStatement("$L.writeBinary($L)", serializerName, getter(beanName, beanClass, property));
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);
				break;
			case VALUE:
			case VALUE_CDATA:
				methodBuilder.addStatement("$L.writeBinary($L)", serializerName, getter(beanName, beanClass, property));
				break;
			}
		}

		if (property.isNullable() && !property.isInCollection()) {
			methodBuilder.endControlFlow();
		}
	}
}
