package com.abubusoft.kripton.processor.bundle;

import java.io.IOException;
import java.util.Date;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import android.content.ContentValues;

import com.abubusoft.kripton.processor.convert.UsedClass;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;


public abstract class BundleGenerator {

	public static final String CONVERT_SUFFIX = "BundleMapper";

	public static void build(ProcessingEnvironment processingEnv, UsedClass definition, Elements elementUtils, Filer filer)
			throws IOException {
		TypeElement interfaceTypeElement = definition.getTypeElement();
		String adapterClassName = interfaceTypeElement.getSimpleName() + CONVERT_SUFFIX;

		PackageElement pkg = elementUtils.getPackageOf(interfaceTypeElement);
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		TypeSpec.Builder builder=TypeSpec.classBuilder(adapterClassName).addModifiers(Modifier.PUBLIC);		
		// generiamo il javadoc contenente la data di generazione della classe
		builder.addJavadoc("Generato automaticamente.\n\n @since $L\n\n", (new Date()).toString());		
		builder.addMethod(buildMap2ContentValueMethod(processingEnv, definition, elementUtils));
		builder.addMethod(buildContentValue2ObjectMethod(processingEnv, definition, elementUtils));
		TypeSpec typeSpec = builder.build();

		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
	}

	private static MethodSpec buildMap2ContentValueMethod(ProcessingEnvironment processingEnv, UsedClass definition, Elements elementUtils) {
		TypeElement typeElement = definition.getTypeElement();
		PackageElement pkg = elementUtils.getPackageOf(typeElement);
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();
		
		ClassName methodParam = ClassName.get(packageName, definition.getName());
		
		MethodSpec.Builder method = MethodSpec.methodBuilder("mapContentValues").addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.addParameter(methodParam, "value").returns(ContentValues.class);
		
		method.addJavadoc("Questo metodo funziona molto bene!");
		
		method.addStatement("$T result=new $T()", ContentValues.class, ContentValues.class);
		for (Element item: definition.getTypeElement().getEnclosedElements())
		{
			if (item.getKind()==ElementKind.FIELD && item.getModifiers().contains(Modifier.PUBLIC))
			{
				method.addStatement("result.put(\"$L\",value.$L)",item.getSimpleName(),item.getSimpleName());
			}
		}
		
		method.addStatement("return result");

		return method.build();
	}
	
	private static MethodSpec buildContentValue2ObjectMethod(ProcessingEnvironment processingEnv, UsedClass definition, Elements elementUtils) {				
		Types typesUtil = processingEnv.getTypeUtils();
		TypeElement typeElement = definition.getTypeElement();
		PackageElement pkg = elementUtils.getPackageOf(typeElement);
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();
		
		TypeMirror STRING = processingEnv.getElementUtils().getTypeElement(String.class.getCanonicalName()).asType();
		TypeMirror INTEGER = processingEnv.getElementUtils().getTypeElement(Integer.class.getCanonicalName()).asType();
		
		ClassName methodParam = ClassName.get(packageName, definition.getName());
		
		MethodSpec.Builder method = MethodSpec.methodBuilder("mapObject").addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.addParameter(ContentValues.class, "value").returns(methodParam);
		
		method.addJavadoc("\nAnche questo metodo funziona molto bene!\n");
		
		method.addStatement("$T result=new $T()", methodParam, methodParam);
		for (Element item: definition.getTypeElement().getEnclosedElements())
		{
			if (item.getKind()==ElementKind.FIELD && item.getModifiers().contains(Modifier.PUBLIC))
			{
				if (typesUtil.isAssignable(item.asType(), STRING))
				{							    			  
				method.addStatement("result.$L=value.getAsString(\"$L\")",item.getSimpleName(),item.getSimpleName());
				} else if (typesUtil.isAssignable(item.asType(), INTEGER))
				{
					method.addStatement("result.$L=value.getAsInteger(\"$L\")",item.getSimpleName(),item.getSimpleName());
				}
			}
		}
		
		method.addStatement("return result");

		return method.build();
	}
}
