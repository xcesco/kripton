/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;

import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * 
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class SchemaUtility {
/*
	public static void generateTransactionForDaoFactory(TypeSpec.Builder classBuilder, SQLiteDatabaseSchema schema) {
		generateMethodDeclaration(schema, classBuilder, true);
		generateMethodDeclaration(schema, classBuilder, false);
	}*/

	/**
	 * @param schema
	 * @param classBuilder 
	 * @return 
	 */
	/*
	private static void generateMethodDeclaration(SQLiteDatabaseSchema schema, Builder classBuilder, boolean async) {
		for (ExecutableElement item : schema.transactions) {
			Set<String> daoNames = new HashSet<String>();
			daoNames.addAll(schema.getDaoNameSet());

			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(item.getSimpleName().toString())
					.addModifiers(Modifier.PUBLIC);
			
			if (async) {
				methodBuilder.returns(ParameterizedTypeName.get(Future.class, Boolean.TYPE));
			} else {
				methodBuilder.returns(Boolean.TYPE);
			}

			methodBuilder.addJavadoc("Executes $L {@link $L}\n", (async? "in async mode":""), item.getSimpleName().toString());

			{
				for (VariableElement p : item.getParameters()) {
					// schema.contains(name)
					if (!daoNames.contains(TypeUtility.typeName(p.asType()).toString())) {
						methodBuilder.addParameter(TypeUtility.typeName(p.asType()), p.getSimpleName().toString());
					}
				}
			}
			
			classBuilder.addMethod(methodBuilder.build());
		}
	}*/

	public static void generateTransaction(TypeSpec.Builder classBuilder, SQLiteDatabaseSchema schema,
			boolean onlyInterface) {
		for (ExecutableElement item : schema.transactions) {
			Set<String> daoNames = new HashSet<String>();
			daoNames.addAll(schema.getDaoNameSet());

			MethodSpec.Builder methodBuilderSync = MethodSpec.methodBuilder(item.getSimpleName().toString())
					.addModifiers(Modifier.PUBLIC).returns(Boolean.TYPE);
			MethodSpec.Builder methodBuilderAsync = MethodSpec.methodBuilder(item.getSimpleName().toString()+"Async")
					.addModifiers(Modifier.PUBLIC).returns(ParameterizedTypeName.get(Future.class, Boolean.class));

			if (!onlyInterface) {
				methodBuilderSync.addAnnotation(Override.class);
				methodBuilderAsync.addAnnotation(Override.class);
			} else {
				methodBuilderSync.addModifiers(Modifier.ABSTRACT);
				methodBuilderAsync.addModifiers(Modifier.ABSTRACT);
			}

			methodBuilderSync.addJavadoc("Executes method {@link $L}\n\n@return <code>true</code> if transaction was done succefull.\n", item.getSimpleName().toString());
			methodBuilderAsync.addJavadoc("Executes method {@link $L} in async mode\n\n@return a <code>Future</code> with true if transaction was done succefull.\n", item.getSimpleName().toString());

			{
				for (VariableElement p : item.getParameters()) {
					// schema.contains(name)
					if (!daoNames.contains(TypeUtility.typeName(p.asType()).toString())) {
						methodBuilderSync.addParameter(TypeUtility.typeName(p.asType()), p.getSimpleName().toString());
						methodBuilderAsync.addParameter(TypeUtility.typeName(p.asType()), p.getSimpleName().toString());
					}
				}
			}

			if (!onlyInterface) {
				boolean managedTransationStatus = false;
				if (TypeUtility.isEquals(ClassName.get(TransactionResult.class),
						TypeUtility.typeName(item.getReturnType()))) {
					managedTransationStatus = true;
				}

				methodBuilderSync.addCode("return $L.this.execute(daoFactory -> {", schema.getGeneratedClassName());
				methodBuilderSync.addCode("\n$>");
												
				if (managedTransationStatus) {
					methodBuilderSync.addCode("return ");					
				}
				
				methodBuilderAsync.addCode("return $L.this.executeAsync(daoFactory -> { ", schema.getGeneratedClassName());
				methodBuilderAsync.addCode("\n$>if (daoFactory.$L(", item.getSimpleName().toString());	
				String s="";
				for (VariableElement p : item.getParameters()) {
					// schema.contains(name)
					if (!daoNames.contains(TypeUtility.typeName(p.asType()).toString())) {
						methodBuilderAsync.addCode(s+p.getSimpleName().toString());
						s=", ";
					}
				}
				methodBuilderAsync.addCode(")==true) { return $T.COMMIT; } else { return $T.ROLLBACK; }", TransactionResult.class, TransactionResult.class);
				methodBuilderAsync.addCode("$<\n});\n");

				methodBuilderSync
						.addCode(schema.getElement().getSimpleName() + "." + item.getSimpleName().toString() + "(");

				{
					String separator = "";
					for (VariableElement p : item.getParameters()) {
						methodBuilderSync.addCode(separator);
						// schema.contains(name)
						if (daoNames.contains(TypeUtility.typeName(p.asType()).toString())) {
							methodBuilderSync.addCode(
									"daoFactory.get" + TypeUtility.simpleName(TypeUtility.typeName(p.asType())) + "()");
						} else {
							methodBuilderSync.addCode("" + p.getSimpleName());
						}
						separator = ", ";

					}
				}
				methodBuilderSync.addCode(");");

				if (!managedTransationStatus) {
					methodBuilderSync.addCode("\nreturn TransactionResult.COMMIT;");
				}

				methodBuilderSync.addCode("$<\n");
				methodBuilderSync.addCode("});\n");
			}

			classBuilder.addMethod(methodBuilderSync.build());
			classBuilder.addMethod(methodBuilderAsync.build());
		}

	}

}
