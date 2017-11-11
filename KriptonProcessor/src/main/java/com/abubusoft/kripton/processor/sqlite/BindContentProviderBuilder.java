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
package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderEntry.MultiplicityResultType;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.bind.JavaWriterHelper;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLType;
import com.abubusoft.kripton.processor.sqlite.model.SQLDaoDefinition;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.CodeBlock.Builder;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Generates content provider class
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class BindContentProviderBuilder extends AbstractBuilder {

	public static class ContentEntry {
		public ContentEntry(String path) {
			this.path = path;
		}

		public String path;

		public String uri_index;

		public SQLiteModelMethod insert;

		public SQLiteModelMethod update;

		public SQLiteModelMethod delete;

		public SQLiteModelMethod select;

		public String pathCostant;

		public String pathIndex;

		public String uriTemplate;

		public int pathValue;

		public String getContentType() {
			String type = "item";

			String typeName = null;
			SQLiteModelMethod[] methods = { insert, update, delete, select };

			for (SQLiteModelMethod item : methods) {
				if (item == null)
					continue;
				if (typeName == null) {
					typeName = item.getParent().contentProviderTypeName;
				}

				String value = AnnotationUtility.extractAsEnumerationValue(item.getElement(), BindContentProviderEntry.class, AnnotationAttributeType.MULTIPLICITY_RESULT);
				MultiplicityResultType multiplicity = MultiplicityResultType.valueOf(value);

				if (multiplicity == MultiplicityResultType.MANY || (multiplicity == MultiplicityResultType.DEFAULT && item.jql.operationType == JQLType.SELECT)) {
					type = "dir";
				}
			}

			return "vnd.android.cursor." + type + "/vnd." + typeName;

		}
	}

	protected Map<String, ContentEntry> uriSet = new TreeMap<>();

	public static final String PREFIX = "Bind";

	public static final String SUFFIX = "ContentProvider";

	public BindContentProviderBuilder(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		super(elementUtils, filer, model);
	}

	/**
	 * Generate content provider
	 * 
	 * @param elementUtils
	 * @param filer
	 * @param schema
	 * @throws Exception
	 */
	public static void generate(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		BindContentProviderBuilder visitorDao = new BindContentProviderBuilder(elementUtils, filer, schema);
		visitorDao.build(elementUtils, filer, schema);
	}

	public void build(Elements elementUtils, Filer filer, SQLiteDatabaseSchema schema) throws Exception {
		uriSet.clear();

		String dataSourceName = schema.getName();
		String dataSourceNameClazz = BindDataSourceBuilder.PREFIX + dataSourceName;
		String contentProviderName = PREFIX + dataSourceName.replace(BindDataSourceBuilder.SUFFIX, "") + SUFFIX;

		PackageElement pkg = elementUtils.getPackageOf(schema.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindContentProvider.class, packageName, contentProviderName);
		classBuilder = TypeSpec.classBuilder(contentProviderName).addModifiers(Modifier.PUBLIC).superclass(ContentProvider.class);
		
		classBuilder.addJavadoc("<p>This is the content provider generated for {@link $L}</p>\n\n", dataSourceName);
		
		classBuilder.addJavadoc("<h2>Content provider authority:</h2>\n");
		classBuilder.addJavadoc("<pre>$L</pre>\n", schema.contentProvider.authority);
		
		generateOnCreate(dataSourceNameClazz);

		generateOnShutdown(dataSourceNameClazz);

		// define static fields
		classBuilder.addField(FieldSpec.builder(String.class, "URI", Modifier.STATIC, Modifier.PUBLIC, Modifier.FINAL).initializer("$S", schema.contentProviderUri())
				.addJavadoc("<p>content provider's URI. Example:</p>\n<pre>content://sqlite.contentprovider.kripton35</pre>\n").build());

		// instance
		classBuilder.addField(FieldSpec.builder(className(dataSourceNameClazz), "dataSource", Modifier.PRIVATE, Modifier.STATIC).addJavadoc("<p>datasource singleton</p>\n").build());
		classBuilder.addField(FieldSpec.builder(String.class, "AUTHORITY", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).addJavadoc("<p>Content provider authority</p>\n")
				.initializer("$S", schema.contentProvider.authority).build());
		classBuilder.addField(FieldSpec.builder(UriMatcher.class, "sURIMatcher", Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL).addJavadoc("<p>URI matcher</p>\n")
				.initializer("new $T($T.NO_MATCH)", UriMatcher.class, UriMatcher.class).build());

		Builder staticBuilder = CodeBlock.builder();
		Converter<String, String> daoConstantConverter = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);

		List<FieldSpec> list1 = new ArrayList<>();
		List<FieldSpec> list2 = new ArrayList<>();

		for (SQLDaoDefinition daoDefinition : schema.getCollection()) {
			String pathConstantName = "PATH_" + daoConstantConverter.convert(daoDefinition.getEntitySimplyClassName());

			if (!daoDefinition.contentProviderEnabled)
				continue;

			// define content provider paths
			for (SQLiteModelMethod daoMethod : daoDefinition.getCollection()) {
				if (!daoMethod.contentProviderEntryPathEnabled)
					continue;

				ContentEntry entry = uriSet.get(daoMethod.contentProviderEntryPathTemplate);
				if (entry == null) {
					entry = new ContentEntry(daoMethod.contentProviderEntryPath);
					uriSet.put(daoMethod.contentProviderEntryPathTemplate, entry);

					entry.path = daoMethod.contentProviderEntryPath;
					entry.uriTemplate = daoMethod.contentProviderEntryPathTemplate;

					// we finish later
					entry.pathCostant = pathConstantName; // ;+ "_" + i;
					entry.pathIndex = pathConstantName; // ;+ "_" + i +
														// "_INDEX";
					// entry.pathValue = i;

				}

				switch (daoMethod.jql.operationType) {
				case INSERT:
					AssertKripton.fail(entry.insert != null, String.format("In DAO %s, there are more than one %s statement associated to content provider path '%s'",
							daoDefinition.getName().toString(), daoMethod.jql.operationType, entry.path));
					entry.insert = daoMethod;
					break;
				case UPDATE:
					AssertKripton.fail(entry.update != null, String.format("In DAO %s, there are more than one %s statement associated to content provider path '%s'",
							daoDefinition.getName().toString(), daoMethod.jql.operationType, entry.path));
					entry.update = daoMethod;
					break;
				case SELECT:
					AssertKripton.fail(entry.select != null, String.format("In DAO %s, there are more than one %s statement associated to content provider path '%s'",
							daoDefinition.getName().toString(), daoMethod.jql.operationType, entry.path));
					entry.select = daoMethod;
					break;
				case DELETE:
					AssertKripton.fail(entry.delete != null, String.format("In DAO %s, there are more than one %s statement associated to content provider path '%s'",
							daoDefinition.getName().toString(), daoMethod.jql.operationType, entry.path));
					entry.delete = daoMethod;
					break;

				}
			}
		}

		// sort uri set by
		List<Pair<String, ContentEntry>> listUriSet = new ArrayList<>();
		Pair<String, ContentEntry> item;
		for (Entry<String, ContentEntry> entry : uriSet.entrySet()) {
			item = new Pair<String, ContentEntry>(entry.getKey(), entry.getValue());
			listUriSet.add(item);
		}

		Comparator<Pair<String, ContentEntry>> c = new Comparator<Pair<String, ContentEntry>>() {

			@Override
			public int compare(Pair<String, ContentEntry> lhs, Pair<String, ContentEntry> rhs) {
				return lhs.value0.compareTo(rhs.value0);
			}

		};
		Collections.sort(listUriSet, c);

		int i = 1;
		for (Pair<String, ContentEntry> entry : listUriSet) {
			// define ordered part of attributes (after sort)
			entry.value1.pathCostant += "_" + i;
			entry.value1.pathIndex += "_" + i + "_INDEX";
			entry.value1.pathValue = i;

			// build class attributes
			list1.add(FieldSpec.builder(String.class, entry.value1.pathCostant, Modifier.STATIC, Modifier.FINAL, Modifier.PUBLIC).initializer(CodeBlock.of("$S", entry.value1.uriTemplate)).build());
			list2.add(FieldSpec.builder(Integer.TYPE, entry.value1.pathIndex, Modifier.STATIC, Modifier.FINAL).initializer(CodeBlock.of("$L", entry.value1.pathValue)).build());
			staticBuilder.addStatement("sURIMatcher.addURI(AUTHORITY, $L, $L)", entry.value1.pathCostant, entry.value1.pathIndex);

			i++;
		}

		for (FieldSpec f : list1) {
			classBuilder.addField(f);
		}

		for (FieldSpec f : list2) {
			classBuilder.addField(f);
		}

		classBuilder.addStaticBlock(staticBuilder.build());

		generateInsert(schema);

		generateUpdate(schema);

		generateQuery(schema);

		generateDelete(schema);

		generateGetType(schema);

		TypeSpec typeSpec = classBuilder.build();
		JavaWriterHelper.writeJava2File(filer, packageName, typeSpec);
	}

	/**
	 * <p>
	 * Generate INSERT code for content provider
	 * </p>
	 * .
	 * 
	 * @param schema
	 */
	private void generateInsert(SQLiteDatabaseSchema schema) {
		// method sign
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("insert").addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).returns(Uri.class);
		methodBuilder.addParameter(Uri.class, "uri");
		methodBuilder.addParameter(ContentValues.class, "contentValues");

		boolean hasOperation = hasOperationOfType(schema, methodBuilder, JQLType.INSERT);

		defineJavadocHeaderForContentOperation("insert");

		// method code
		methodBuilder.addStatement("long _id=-1");
		methodBuilder.addStatement("$T _returnURL=null", Uri.class);
		methodBuilder.beginControlFlow("switch (sURIMatcher.match(uri))");
		for (Entry<String, ContentEntry> item : uriSet.entrySet()) {
			if (item.getValue().insert == null)
				continue;

			defineJavadocForContentUri(item.getValue().insert);

			methodBuilder.beginControlFlow("case $L:", item.getValue().pathIndex);
			methodBuilder.addStatement("_id=dataSource.get$L().$L(uri, contentValues)", item.getValue().insert.getParent().getName(), item.getValue().insert.contentProviderMethodName);
			methodBuilder.addStatement("_returnURL=Uri.withAppendedPath(uri, String.valueOf(_id))");

			methodBuilder.addStatement("break");
			methodBuilder.endControlFlow();
		}

		classBuilder.addJavadoc("</table>\n");

		methodBuilder.beginControlFlow("default:");
		methodBuilder.addStatement("throw new $T(\"Unknown URI for $L operation: \" + uri)", IllegalArgumentException.class, JQLType.INSERT);
		methodBuilder.endControlFlow();

		methodBuilder.endControlFlow();

		if (hasOperation) {

			if (schema.generateLog) {
				// generate log section - BEGIN
				methodBuilder.addComment("log section BEGIN");
				methodBuilder.beginControlFlow("if (this.dataSource.logEnabled)");
								
				methodBuilder.addStatement("$T.info(\"Element is created with URI '%s'\", _returnURL)", Logger.class);
				methodBuilder.addStatement("$T.info(\"Changes are notified for URI '%s'\", uri)", Logger.class);
				
				// generate log section - END
				methodBuilder.endControlFlow();
				methodBuilder.addComment("log section END");
			}
			methodBuilder.addStatement("getContext().getContentResolver().notifyChange(uri, null)");
			methodBuilder.addStatement("return _returnURL");
		}

		classBuilder.addMethod(methodBuilder.build());

	}

	private void defineJavadocForContentUri(SQLiteModelMethod method) {
		classBuilder.addJavadoc("<tr><td><pre>$L</pre></td><td>{@link $LImpl#$L}</td></tr>\n", method.contentProviderUri(), method.getParent().getName(), method.contentProviderMethodName);

	}

	private void generateDelete(SQLiteDatabaseSchema schema) {
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("delete").addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).returns(Integer.TYPE);
		methodBuilder.addParameter(Uri.class, "uri");
		methodBuilder.addParameter(String.class, "selection");
		methodBuilder.addParameter(ParameterSpec.builder(TypeUtility.arrayTypeName(String.class), "selectionArgs").build());

		boolean hasOperation = hasOperationOfType(schema, methodBuilder, JQLType.DELETE);

		if (!hasOperation) {
			methodBuilder.addStatement("throw new $T(\"Unknown URI for $L operation: \" + uri)", IllegalArgumentException.class, JQLType.DELETE);
			classBuilder.addMethod(methodBuilder.build());
			return;
		}

		methodBuilder.addStatement("int returnRowDeleted=-1");
		methodBuilder.beginControlFlow("switch (sURIMatcher.match(uri))");

		defineJavadocHeaderForContentOperation("delete");

		for (Entry<String, ContentEntry> item : uriSet.entrySet()) {
			if (item.getValue().delete == null)
				continue;

			defineJavadocForContentUri(item.getValue().delete);

			// methodBuilder.addJavadoc("uri $L\n", item.getKey());

			methodBuilder.beginControlFlow("case $L:", item.getValue().pathIndex);
			methodBuilder.addCode("// URI: $L\n", item.getValue().delete.contentProviderUri());
			methodBuilder.addStatement("returnRowDeleted=dataSource.get$L().$L(uri, selection, selectionArgs)", item.getValue().delete.getParent().getName(),
					item.getValue().delete.contentProviderMethodName);
			methodBuilder.addStatement("break");
			methodBuilder.endControlFlow();
		}

		classBuilder.addJavadoc("</table>\n");

		methodBuilder.beginControlFlow("default:");
		methodBuilder.addStatement("throw new $T(\"Unknown URI for $L operation: \" + uri)", IllegalArgumentException.class, JQLType.DELETE);
		methodBuilder.endControlFlow();

		methodBuilder.endControlFlow();

		if (hasOperation) {
			if (schema.generateLog) {
				// generate log section - BEGIN
				methodBuilder.addComment("log section BEGIN");
				methodBuilder.beginControlFlow("if (this.dataSource.logEnabled)");
				
				methodBuilder.addStatement("$T.info(\"Changes are notified for URI %s\", uri)", Logger.class);
				
				// generate log section - END
				methodBuilder.endControlFlow();
				methodBuilder.addComment("log section END");
			}
			methodBuilder.addStatement("getContext().getContentResolver().notifyChange(uri, null)");
		}
		methodBuilder.addCode("return returnRowDeleted;\n");

		classBuilder.addMethod(methodBuilder.build());
	}

	private void generateUpdate(SQLiteDatabaseSchema schema) {
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("update").addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).returns(Integer.TYPE);
		methodBuilder.addParameter(Uri.class, "uri");
		methodBuilder.addParameter(ContentValues.class, "contentValues");
		methodBuilder.addParameter(String.class, "selection");
		methodBuilder.addParameter(ParameterSpec.builder(TypeUtility.arrayTypeName(String.class), "selectionArgs").build());

		boolean hasOperation = hasOperationOfType(schema, methodBuilder, JQLType.UPDATE);

		if (!hasOperation) {
			methodBuilder.addStatement("throw new $T(\"Unknown URI for $L operation: \" + uri)", IllegalArgumentException.class, JQLType.UPDATE);
			classBuilder.addMethod(methodBuilder.build());
			return;
		}

		defineJavadocHeaderForContentOperation("update");

		methodBuilder.addStatement("int returnRowUpdated=1");

		methodBuilder.beginControlFlow("switch (sURIMatcher.match(uri))");
		for (Entry<String, ContentEntry> item : uriSet.entrySet()) {
			if (item.getValue().update == null)
				continue;

			// methodBuilder.addJavadoc("uri $L\n", item.getKey());
			defineJavadocForContentUri(item.getValue().update);

			methodBuilder.beginControlFlow("case $L:", item.getValue().pathIndex);
			methodBuilder.addCode("// URI: $L\n", item.getValue().update.contentProviderUri());
			methodBuilder.addStatement("returnRowUpdated=dataSource.get$L().$L(uri, contentValues, selection, selectionArgs)", item.getValue().update.getParent().getName(),
					item.getValue().update.contentProviderMethodName);
			methodBuilder.addStatement("break");
			methodBuilder.endControlFlow();
		}

		classBuilder.addJavadoc("</table>\n");

		methodBuilder.beginControlFlow("default:");
		methodBuilder.addStatement("throw new $T(\"Unknown URI for $L operation: \" + uri)", IllegalArgumentException.class, JQLType.UPDATE);
		methodBuilder.endControlFlow();

		methodBuilder.endControlFlow();

		if (hasOperation) {
			if (schema.generateLog) {
				// generate log section - BEGIN
				methodBuilder.addComment("log section BEGIN");
				methodBuilder.beginControlFlow("if (this.dataSource.logEnabled)");
				
				methodBuilder.addStatement("$T.info(\"Changes are notified for URI %s\", uri)", Logger.class);
				
				// generate log section - END
				methodBuilder.endControlFlow();
				methodBuilder.addComment("log section END");
			}

			methodBuilder.addStatement("getContext().getContentResolver().notifyChange(uri, null)");
		}
		methodBuilder.addStatement("return returnRowUpdated");

		classBuilder.addMethod(methodBuilder.build());

	}

	private void defineJavadocHeaderForContentOperation(String value) {
		classBuilder.addJavadoc("\n<h2>Supported $L operations</h2>\n", value);
		classBuilder.addJavadoc("<table>\n");
		classBuilder.addJavadoc("<tr><th>URI</th><th>DAO.METHOD</th></tr>\n");

	}

	/**
	 * iterate methods, selecting only jqlType and
	 * contains @BindContentProviderEntry annotation.
	 * 
	 * @param schema
	 * @param methodBuilder
	 * @return
	 */
	private boolean hasOperationOfType(SQLiteDatabaseSchema schema, MethodSpec.Builder methodBuilder, JQLType jqlType) {
		boolean hasOperation = false;
		for (SQLDaoDefinition daoDefinition : schema.getCollection()) {
			
			if (daoDefinition.getElement().getAnnotation(BindContentProviderPath.class)==null) continue;
			
			for (SQLiteModelMethod daoMethod : daoDefinition.getCollection()) {
				if (daoMethod.jql.operationType != jqlType) {
					continue;
				}
				if (!daoMethod.hasAnnotation(BindContentProviderEntry.class)) {
					continue;
				}

				hasOperation = true;
				methodBuilder.addJavadoc("method $L.$L\n", daoDefinition.getName(), daoMethod.getName());
			}
		}
		return hasOperation;
	}

	private void generateGetType(SQLiteDatabaseSchema schema) {
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("getType").addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).returns(String.class);
		methodBuilder.addParameter(Uri.class, "uri");

		methodBuilder.beginControlFlow("switch (sURIMatcher.match(uri))");
		for (Entry<String, ContentEntry> item : uriSet.entrySet()) {
			// methodBuilder.addJavadoc("uri $L\n", item.getKey());

			methodBuilder.beginControlFlow("case $L:", item.getValue().pathIndex);
			methodBuilder.addStatement("return $S", item.getValue().getContentType());
			methodBuilder.endControlFlow();
		}
		methodBuilder.endControlFlow();

		methodBuilder.addStatement("throw new $T(\"Unknown URI for $L operation: \" + uri)", IllegalArgumentException.class, "getType");

		classBuilder.addMethod(methodBuilder.build());

	}

	private void generateOnCreate(String dataSourceNameClazz) {
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("onCreate").addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).returns(Boolean.TYPE);

		methodBuilder.addJavadoc("<p>Create datasource and open database in read mode.</p>\n");
		methodBuilder.addJavadoc("\n");
		methodBuilder.addJavadoc("@see android.content.ContentProvider#onCreate()\n");

		methodBuilder.addStatement("dataSource = $L.instance()", dataSourceNameClazz);
		methodBuilder.addStatement("dataSource.openWritableDatabase()");

		methodBuilder.addCode("return true;\n");

		classBuilder.addMethod(methodBuilder.build());
	}

	private void generateOnShutdown(String dataSourceNameClazz) {
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("shutdown").addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).returns(Void.TYPE);

		methodBuilder.addJavadoc("<p>Close database.</p>\n");
		methodBuilder.addJavadoc("\n");
		methodBuilder.addJavadoc("@see android.content.ContentProvider#shutdown()\n");

		methodBuilder.addStatement("super.shutdown()");
		methodBuilder.addStatement("dataSource.close()");

		classBuilder.addMethod(methodBuilder.build());
	}

	private void generateQuery(SQLiteDatabaseSchema schema) {
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("query").addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).returns(Cursor.class);
		methodBuilder.addParameter(Uri.class, "uri");
		methodBuilder.addParameter(ParameterSpec.builder(TypeUtility.arrayTypeName(String.class), "projection").build());
		methodBuilder.addParameter(String.class, "selection");
		methodBuilder.addParameter(ParameterSpec.builder(TypeUtility.arrayTypeName(String.class), "selectionArgs").build());
		methodBuilder.addParameter(String.class, "sortOrder");

		boolean hasOperation = hasOperationOfType(schema, methodBuilder, JQLType.SELECT);

		if (!hasOperation) {
			methodBuilder.addStatement("throw new $T(\"Unknown URI for $L operation: \" + uri)", IllegalArgumentException.class, JQLType.SELECT);
			classBuilder.addMethod(methodBuilder.build());
			return;
		}

		methodBuilder.addStatement("$T returnCursor=null", Cursor.class);

		for (SQLDaoDefinition daoDefinition : schema.getCollection()) {
			for (SQLiteModelMethod daoMethod : daoDefinition.getCollection()) {
				if (daoMethod.jql.operationType != JQLType.SELECT)
					continue;
				methodBuilder.addJavadoc("method $L.$L\n", daoDefinition.getName(), daoMethod.getName());
			}
		}

		defineJavadocHeaderForContentOperation("query");

		methodBuilder.beginControlFlow("switch (sURIMatcher.match(uri))");
		for (Entry<String, ContentEntry> item : uriSet.entrySet()) {
			if (item.getValue().select == null)
				continue;

			defineJavadocForContentUri(item.getValue().select);

			// methodBuilder.addJavadoc("uri $L\n", item.getKey());

			methodBuilder.beginControlFlow("case $L:", item.getValue().pathIndex);
			methodBuilder.addCode("// URI: $L\n", item.getValue().select.contentProviderUri());
			methodBuilder.addStatement("returnCursor=dataSource.get$L().$L(uri, projection, selection, selectionArgs, sortOrder)", item.getValue().select.getParent().getName(),
					item.getValue().select.contentProviderMethodName);
			methodBuilder.addStatement("break");
			methodBuilder.endControlFlow();
		}
		methodBuilder.beginControlFlow("default:");
		methodBuilder.addStatement("throw new $T(\"Unknown URI for $L operation: \" + uri)", IllegalArgumentException.class, JQLType.SELECT);
		methodBuilder.endControlFlow();

		methodBuilder.endControlFlow();

		classBuilder.addJavadoc("</table>\n");

		if (hasOperation) {
			methodBuilder.addStatement("return returnCursor");
		}

		classBuilder.addMethod(methodBuilder.build());
	}

}
