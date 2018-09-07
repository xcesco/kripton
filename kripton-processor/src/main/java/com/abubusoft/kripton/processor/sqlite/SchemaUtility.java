/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;

import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

/**
 * 
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class SchemaUtility {

	public static void generateTransactionForDaoFactory(TypeSpec.Builder classBuilder, SQLiteDatabaseSchema schema) {
		for (ExecutableElement item : schema.transactions) {
			Set<String> daoNames = new HashSet<String>();
			daoNames.addAll(schema.getDaoNameSet());

			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(item.getSimpleName().toString())
					.returns(Boolean.TYPE).addModifiers(Modifier.PUBLIC);

			methodBuilder.addJavadoc("Executes {@link $L}\n", item.getSimpleName().toString());

			{
				for (VariableElement p : item.getParameters()) {
					// schema.contains(name)
					if (!daoNames.contains(TypeUtility.typeName(p.asType()).toString())) {
						methodBuilder.addParameter(TypeUtility.typeName(p.asType()), p.getSimpleName().toString());
					}
				}
			}
		}
	}

	public static void generateTransaction(TypeSpec.Builder classBuilder, SQLiteDatabaseSchema schema,
			boolean onlyInterface) {
		for (ExecutableElement item : schema.transactions) {
			Set<String> daoNames = new HashSet<String>();
			daoNames.addAll(schema.getDaoNameSet());

			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(item.getSimpleName().toString())
					.returns(Boolean.TYPE);

			if (!onlyInterface) {
				methodBuilder.addAnnotation(Override.class).addModifiers(Modifier.PUBLIC);
			}

			methodBuilder.addJavadoc("Executes {@link $L}\n", item.getSimpleName().toString());

			{
				for (VariableElement p : item.getParameters()) {
					// schema.contains(name)
					if (!daoNames.contains(TypeUtility.typeName(p.asType()).toString())) {
						methodBuilder.addParameter(TypeUtility.typeName(p.asType()), p.getSimpleName().toString());
					}
				}
			}

			if (!onlyInterface) {
				boolean managedTransationStatus = false;
				if (TypeUtility.isEquals(ClassName.get(TransactionResult.class),
						TypeUtility.typeName(item.getReturnType()))) {
					managedTransationStatus = true;
				}

				methodBuilder.addCode("return execute(daoFactory -> {");
				methodBuilder.addCode("\n$>");

				if (managedTransationStatus) {
					methodBuilder.addCode("return ");
				}

				methodBuilder
						.addCode(schema.getElement().getSimpleName() + "." + item.getSimpleName().toString() + "(");

				{
					String separator = "";
					for (VariableElement p : item.getParameters()) {
						methodBuilder.addCode(separator);
						// schema.contains(name)
						if (daoNames.contains(TypeUtility.typeName(p.asType()).toString())) {
							methodBuilder.addCode(
									"daoFactory.get" + TypeUtility.simpleName(TypeUtility.typeName(p.asType())) + "()");
						} else {
							methodBuilder.addCode("" + p.getSimpleName());
						}
						separator = ", ";

					}
				}
				methodBuilder.addCode(");");

				if (!managedTransationStatus) {
					methodBuilder.addCode("\nreturn TransactionResult.COMMIT;");
				}

				methodBuilder.addCode("$<\n");
				methodBuilder.addCode("});\n");
			}

			classBuilder.addMethod(methodBuilder.build());
		}

	}

}
