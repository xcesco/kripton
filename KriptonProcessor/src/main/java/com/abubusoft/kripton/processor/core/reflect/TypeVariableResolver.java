package com.abubusoft.kripton.processor.core.reflect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import com.abubusoft.kripton.annotation.BindTypeVariables;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.processor.BindTypeProcessor;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.squareup.javapoet.TypeName;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * <p>Resolver for type variables used in class or interface hierarchy.</p> 
 * 
 * <p>Usually it is used in <code>BindType</code> bean and <code>Dao</code> definitions.</p>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class TypeVariableResolver {

	List<? extends TypeMirror> declaredTypeArgumentList;
	
	Map<String, TypeName> typeVariableMap;
	
	TypeElement element;
	
	boolean active;
	
	
	public TypeVariableResolver(TypeElement element, List<? extends TypeMirror> typeArgs, Map<String, TypeName> typeVariableMap) {
		this.element=element;
		this.declaredTypeArgumentList=typeArgs;
		this.typeVariableMap=typeVariableMap;
		this.active=(typeArgs != null && typeArgs.size() > 0) || (typeVariableMap.size()>0);
	}
	
	public boolean hasTypeVariables() {
		return active;
	}
	
	@SuppressWarnings({ "unchecked"})
	public static TypeVariableResolver build(TypeElement element)
	{
		List<? extends TypeMirror> typeArgs = TypeUtility.getTypeArguments(element);
		Map<String, TypeName> typeVariableMap=new HashMap<>();
		
		BindTypeVariables annotationBeanType = element.getAnnotation(BindTypeVariables.class);
		List<String> typeVariables = new ArrayList<String>();
		if (annotationBeanType != null) {
			typeVariables = Arrays.asList(annotationBeanType.value());
		}
		
		List<String> typeParameters = AnnotationUtility.extractAsClassNameArray(BindTypeProcessor.elementUtils, element, BindTypeVariables.class, AnnotationAttributeType.TYPE_PARAMETERS);

		AssertKripton.assertTrue(typeVariables.size() >= typeParameters.size(), "%s '%s' has incorrect definition of type variables/parameters on annotation @BindTypeVariables",
				element.getKind()==ElementKind.CLASS ? "Class" :"Interface", element.asType());

		if (typeVariables.size() > 0) {
			String[] temp;

			AssertKripton.assertTrue((typeParameters.size() == 0) || (typeVariables.size() == typeParameters.size()),
					"%s '%s' has an incorrect definition of type variables/parameters on annotation @BindTypeVariables(typeVariables, typeParameters)", element.getKind()==ElementKind.CLASS ? "Class" :"Interface", element.asType());

			if (StringUtils.hasText(typeVariables.get(0))) {
				int i = 0;
				for (String key : typeVariables) {
					if (StringUtils.hasText(key)) {
						temp = key.split(",");
						for (String alias : temp) {
							if (typeParameters.size() > 0) {
								typeVariableMap.put(alias.trim(), TypeUtility.typeName(typeParameters.get(i)));
							} else {
								typeVariableMap.put(alias.trim(), TypeUtility.typeName(typeArgs.get(i)));
							}
						}
					}
					i++;
				}

			} else {
				AssertKripton.assertTrue(typeVariableMap == null && typeArgs.size() < 2, "%s '%s' use more than one type variables in its class hierarchy. Try to use @BindTypeVariables",
						element.getKind()==ElementKind.CLASS ? "Class" :"Interface", element.asType());
			}
		}
		
		
		return new TypeVariableResolver(element, typeArgs, typeVariableMap);
	}
	
	public TypeName resolve(TypeName inputTypeName) {
		if (!hasTypeVariables())
			return inputTypeName;
		
		if (inputTypeName.toString().contains(".") || inputTypeName.isPrimitive() || inputTypeName.isBoxedPrimitive()) {
			return inputTypeName;
		}

		if (typeVariableMap != null && typeVariableMap.containsKey(inputTypeName.toString())) {
			TypeName type = typeVariableMap.get(inputTypeName.toString());
			return type;
		} else if (typeVariableMap == null) {
			TypeName resolved = TypeUtility.typeName(declaredTypeArgumentList.get(0));
			// if we found a type variable not yet bound, we bound it.
			typeVariableMap = new HashMap<>();
			typeVariableMap.put(inputTypeName.toString(), resolved);

			return resolved;
		}

		AssertKripton.assertTrue(false, "In type hierarchy of %s '%s' there is a unresolved type variable named '%s'. Define it with @BindType(typeVariables)", element.getKind()==ElementKind.CLASS ? "class" :"interface", element.getQualifiedName(),
				inputTypeName.toString());

		return inputTypeName;
	}
	
}
