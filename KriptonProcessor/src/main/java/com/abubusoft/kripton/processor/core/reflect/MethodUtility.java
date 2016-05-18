package com.abubusoft.kripton.processor.core.reflect;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.sqlite.ReadCursorListener;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.core.ModelMethod;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.MethodFoundListener;
import com.squareup.javapoet.TypeName;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

public abstract class MethodUtility {
	
	private static final Pattern pattern = Pattern.compile("\\((.*)\\)(.*)");

    public static Pair<String, String> extractResultAndArguments(String value) {
        Matcher matcher = pattern.matcher(value);
        
        Pair<String, String> result=new Pair<String, String>();
        if (matcher.matches())
        {
        	result.value0=matcher.group(1);
        	result.value1=matcher.group(2);
        }
        return result;
    }
    
	/**
	 * Iterate over methods.
	 * 
	 * @param elementUtils
	 * @param typeElement
	 * @param listener
	 */
	public static void forEachMethods(Elements elementUtils, TypeElement typeElement, MethodFoundListener listener) {
		List<? extends Element> list = elementUtils.getAllMembers(typeElement);

		for (Element item : list) {
			if (item.getKind() == ElementKind.METHOD) {
				listener.onMethod((ExecutableElement) item);
			}
		}
	}
	
	public static boolean hasParameterOfType(ModelMethod method, TypeName kindOfParameter)
	{
		for(Pair<String, TypeMirror> item:method.getParameters())
		{
			if (TypeUtility.isEquals(typeName(item.value1), kindOfParameter))
			{
				return true;
			}			
		}
			
		return false;
	}
	
	public static int countParameterOfType(ModelMethod method, TypeName kindOfParameter)
	{
		int counter=0;
		for (Pair<String, TypeMirror> item:method.getParameters())
		{
			if (TypeUtility.isEquals(typeName(item.value1), typeName(ReadCursorListener.class)))
			{
				counter++;
			}
		}
		
		return counter;
	}
	
	public static String getNameParameterOfType(ModelMethod method, TypeName kindOfParameter)
	{
		for (Pair<String, TypeMirror> item:method.getParameters())
		{
			if (TypeUtility.isEquals(typeName(item.value1), typeName(ReadCursorListener.class)))
			{
				return item.value0;
			}
		}
		
		return null;
	}



	
}
