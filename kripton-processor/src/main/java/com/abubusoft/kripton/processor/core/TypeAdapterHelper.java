package com.abubusoft.kripton.processor.core;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;

public class TypeAdapterHelper {
	public static String detectSourceType(String adapterClazz) {
		return detectSourceType(null, adapterClazz);
	}
	
	public static String detectSourceType(Element element, String adapterClazz) {
		TypeElement a = BaseProcessor.elementUtils.getTypeElement(adapterClazz);
		for (Element i : BaseProcessor.elementUtils.getAllMembers(a)) {
			if (i.getKind() == ElementKind.METHOD && "toJava".equals(i.getSimpleName().toString())) {
				ExecutableElement method = (ExecutableElement) i;
				return TypeUtility.typeName(method.getReturnType()).toString();
			}
		}

		AssertKripton.fail("In '%s', class '%s' can not be used as type adapter", element, adapterClazz);
		return null;
	}

	public static String detectDestinationType(Element element, String adapterClazz) {
		TypeElement a = BaseProcessor.elementUtils.getTypeElement(adapterClazz);
		for (Element i : BaseProcessor.elementUtils.getAllMembers(a)) {
			if (i.getKind() == ElementKind.METHOD && "toData".equals(i.getSimpleName().toString())) {
				ExecutableElement method = (ExecutableElement) i;
				return TypeUtility.typeName(method.getReturnType()).toString();
			}
		}

		AssertKripton.fail("In '%s', class '%s' can not be used as type adapter", element, adapterClazz);
		return null;
	}
}
