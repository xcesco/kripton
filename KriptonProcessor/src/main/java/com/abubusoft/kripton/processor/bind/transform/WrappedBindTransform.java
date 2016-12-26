/**
 * 
 */
package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;
import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.common.TypeAdapterUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.processor.bind.BindTypeContext;
import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.xml.XmlType;
import com.fasterxml.jackson.core.JsonToken;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * @author xcesco
 *
 */
public class WrappedBindTransform extends AbstractBindTransform {

	protected Class<?> utilClazz;

	public WrappedBindTransform(Class<?> utilClazz) {
		this.utilClazz = utilClazz;
	}
	
	public boolean isTypeAdapterSupported() {
		return true;
	}

	@Override
	public void generateParseOnJackson(BindTypeContext context, Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}

		if (property.hasTypeAdapter()) {
			methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$T.read($L.getText())" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
					TypeUtility.typeName(property.typeAdapter.adapterClazz), utilClazz, parserName);
		} else {
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read($L.getText())"), utilClazz, parserName);
		}

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}

	}

	@Override
	public void generateParseOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L.currentToken()!=$T.VALUE_NULL)", parserName, JsonToken.class);
		}

		if (property.hasTypeAdapter()) {
			methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$T.read($L.getText())" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
					TypeUtility.typeName(property.typeAdapter.adapterClazz), utilClazz, parserName);
		} else {
			methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read($L.getText())"), utilClazz, parserName);
		}

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateParseOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;

		if (property.hasTypeAdapter()) {
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$T.read($L.getAttributeValue(attributeIndex))" + POST_TYPE_ADAPTER),
						TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), utilClazz, parserName);
				break;
			case TAG:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$T.read($T.unescapeXml($L.getElementText()))" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), utilClazz, StringEscapeUtils.class, parserName);
				break;
			case VALUE:
			case VALUE_CDATA:
				methodBuilder.addStatement(setter(beanClass, beanName, property, PRE_TYPE_ADAPTER_TO_JAVA + "$T.read($T.unescapeXml($L.getText()))" + POST_TYPE_ADAPTER), TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), utilClazz, StringEscapeUtils.class, parserName);
				break;
			default:
				break;
			}
		} else {
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read($L.getAttributeValue(attributeIndex))"), utilClazz, parserName);
				break;
			case TAG:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read($T.unescapeXml($L.getElementText()))"), utilClazz, StringEscapeUtils.class, parserName);
				break;
			case VALUE:
			case VALUE_CDATA:
				methodBuilder.addStatement(setter(beanClass, beanName, property, "$T.read($T.unescapeXml($L.getText()))"), utilClazz, StringEscapeUtils.class, parserName);
				break;
			default:
				break;
			}
		}

	}

	@Override
	public void generateSerializeOnJackson(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		if (property.isNullable()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		if (property.isProperty()) {
			methodBuilder.addStatement("fieldCount++");
		}

		if (property.hasTypeAdapter()) {
			if (property.isInCollection()) {
				// we need to write only value
				methodBuilder.addStatement("$L.writeString($T.write(" + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + "))", serializerName, utilClazz, TypeAdapterUtils.class,
						TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
			} else {
				methodBuilder.addStatement("$L.writeStringField($S, $T.write(" + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + "))", serializerName, property.label, utilClazz,
						TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
			}
		} else {
			if (property.isInCollection()) {
				// we need to write only value
				methodBuilder.addStatement("$L.writeString($T.write($L))", serializerName, utilClazz, getter(beanName, beanClass, property));
			} else {
				methodBuilder.addStatement("$L.writeStringField($S, $T.write($L))", serializerName, property.label, utilClazz, getter(beanName, beanClass, property));
			}
		}

		if (property.isNullable()) {
			methodBuilder.endControlFlow();
		}
	}

	@Override
	public void generateSerializeOnJacksonAsString(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		generateSerializeOnJackson(context, methodBuilder, serializerName, beanClass, beanName, property);
	}

	@Override
	public void generateSerializeOnXml(BindTypeContext context, MethodSpec.Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		XmlType xmlType = property.xmlInfo.xmlType;

		if (property.isNullable() && !property.isInCollection()) {
			methodBuilder.beginControlFlow("if ($L!=null) ", getter(beanName, beanClass, property));
		}

		if (property.hasTypeAdapter()) {
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement("$L.writeAttribute($S, $T.escapeXml10($T.write(" + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + ")))", serializerName, property.label,
						StringEscapeUtils.class, utilClazz, TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				break;
			case TAG:
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.label);
				methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($T.write(" + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + ")))", serializerName, StringEscapeUtils.class,
						utilClazz, TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				methodBuilder.addStatement("$L.writeEndElement()", serializerName);
				break;
			case VALUE:
				methodBuilder.addStatement("$L.writeCharacters($T.escapeXml10($T.write(" + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + ")))", serializerName, StringEscapeUtils.class,
						utilClazz, TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				break;
			case VALUE_CDATA:
				methodBuilder.addStatement("$L.writeCData($T.escapeXml10($T.write(" + PRE_TYPE_ADAPTER_TO_DATA + "$L" + POST_TYPE_ADAPTER + ")))", serializerName, StringEscapeUtils.class, utilClazz,
						TypeAdapterUtils.class, TypeUtility.typeName(property.typeAdapter.adapterClazz), getter(beanName, beanClass, property));
				break;
			}
		} else {
			switch (xmlType) {
			case ATTRIBUTE:
				methodBuilder.addStatement("$L.writeAttribute($S, $T.write($L))", serializerName, property.label, utilClazz, getter(beanName, beanClass, property));
				break;
			case TAG:
				methodBuilder.addStatement("$L.writeStartElement($S)", serializerName, property.label);
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
		}

		if (property.isNullable() && !property.isInCollection()) {
			methodBuilder.endControlFlow();
		}

	}

}