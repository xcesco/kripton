package com.abubusoft.kripton.processor.convert;

import java.io.IOException;
import java.util.Date;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import android.content.ContentValues;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

public abstract class ConvertGenerator {

	public static final String CONVERT_SUFFIX = "Convert";

	public static void build(ConvertDefinition definition, Elements elementUtils, Filer filer)
			throws IOException {
		TypeElement interfaceTypeElement = definition.getTypeElement();
		String adapterClassName = interfaceTypeElement.getSimpleName() + CONVERT_SUFFIX;

		PackageElement pkg = elementUtils.getPackageOf(interfaceTypeElement);
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		TypeSpec.Builder builder=TypeSpec.classBuilder(adapterClassName).addModifiers(Modifier.PUBLIC);		
		// generiamo il javadoc contenente la data di generazione della classe
		builder.addJavadoc("Generato automaticamente.\n\n @since $L\n\n", (new Date()).toString());		
		builder.addMethod(buildCreateMethod(definition, elementUtils));
		TypeSpec typeSpec = builder.build();

		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
	}

	private static MethodSpec buildCreateMethod(ConvertDefinition definition, Elements elementUtils) {
		TypeElement typeElement = definition.getTypeElement();
		PackageElement pkg = elementUtils.getPackageOf(typeElement);
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();
		
		ClassName methodParam = ClassName.get(packageName, definition.getName());
		
		MethodSpec.Builder method = MethodSpec.methodBuilder("convertToContentValues").addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.addParameter(methodParam, "value").returns(ContentValues.class);
		
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
}
