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
package com.abubusoft.kripton.processor.sqlite.model;

import com.abubusoft.kripton.android.annotation.*;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere.PrependType;
import com.abubusoft.kripton.android.sqlite.NoAdapter;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.processor.BindDataSourceSubProcessor;
import com.abubusoft.kripton.processor.KriptonDynamicClassManager;
import com.abubusoft.kripton.processor.core.*;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.exceptions.IncompatibleAnnotationException;
import com.abubusoft.kripton.processor.sqlite.FindSqlChildSelectVisitor;
import com.abubusoft.kripton.processor.sqlite.SelectBuilderUtility;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLBuilder;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker.JQLParameterName;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLContext;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.ContentUriChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.ContentUriChecker.UriPlaceHolderReplacerListener;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.ContentUriPlaceHolder;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.*;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.util.*;

/**
 * The Class SQLiteModelMethod.
 */
public class SQLiteModelMethod extends ModelMethod implements SQLiteModelElement, JQLContext {

  /**
   * Entity associated to method. For insert/modify/update must be the same of
   * the dao definition For select statement it can be another (it must be
   * annotated with @BindSqlType)
   */
  private SQLiteEntity entity;

  public SQLiteEntity getEntity() {
    return entity;
  }

  /**
   * The Interface OnFoundDynamicParameter.
   */
  interface OnFoundDynamicParameter {

    /**
     * On found parameter.
     *
     * @param parameterName the parameter name
     */
    void onFoundParameter(String parameterName);
  }

  /**
   * <p>
   * It is the typeName of parameter used to dynamic order by condition
   * (defined at runtime).
   * </p>
   */
  public String dynamicOrderByParameterName;

  /**
   * <p>
   * It is the typeName of parameter used to dynamic where condition (defined
   * at runtime).
   * </p>
   */
  public String dynamicWhereParameterName;

  /**
   * <p>
   * It's the name of the parameter used to define arguments for dynamic where
   * statement. It can be used only on String[] parameter type.
   * </p>
   */
  public String dynamicWhereArgsParameterName;

  /**
   * <p>
   * It is the typeName of parameter used to dynamic page size (defined at
   * runtime).
   * </p>
   */
  public String dynamicPageSizeName;

  /**
   * typeName of the paginated result parameter typeName.
   */
  public String paginatedResultName;

  /**
   * The parameter alias 2 name field.
   */
  protected Map<String, String> parameterAlias2NameField;

  /**
   * The parameter name 2 alias.
   */
  protected Map<String, String> parameterName2Alias;

  /**
   * The parameter name 2 adapter.
   */
  protected Map<String, String> parameterName2Adapter;

  /**
   * The parent.
   */
  private WeakReference<SQLiteDaoDefinition> parent;

  /**
   * Next counter.
   *
   * @return the long
   */
  public long nextCounter() {
    return getParent().nextCounter();
  }

  public long currentCounter() {
    return getParent().currentCounter();
  }

  /**
   * The jql.
   */
  public final JQL jql;

  /**
   * The content provider entry path enabled.
   */
  public boolean contentProviderEntryPathEnabled;

  /**
   * it's the path defined in @ContentProviderEntry.path
   */
  public String contentProviderEntryPath;

  /**
   * name of method generated for content provider.
   */
  public String contentProviderMethodName;

  /**
   * The parameter bean name.
   */
  public String parameterBeanName;

  /**
   * The content provider uri variables.
   */
  public List<ContentUriPlaceHolder> contentProviderUriVariables;

  /**
   * <p>
   * It's the uri with place holder replaced with <code>#</code> or
   * <code>*</code>. An example:
   * </p>
   *
   * <pre>
   * content://sqlite.contentprovider.kripton35/persons/#/children
   * </pre>
   */
  public String contentProviderUriTemplate;

  /**
   * The content provider entry path template.
   */
  public String contentProviderEntryPathTemplate;

  /**
   * The dynamic where prepend.
   */
  public PrependType dynamicWherePrepend;

  /**
   * if true, means that this method returns live data.
   */
  private final boolean liveDataEnabled;

  /**
   * Checks for live data.
   *
   * @return true, if successful
   */
  public boolean hasLiveData() {
    return liveDataEnabled;
  }

  /**
   * return type of the original method. Defined only for LiveData methods.
   */
  public ParameterizedTypeName liveDataReturnClass;

  /**
   * list of children selects. Valid only for select methods
   */
  public List<Triple<String, String, SQLiteModelMethod>> childrenSelects = new ArrayList<>();

  private boolean pagedLiveData;

  private boolean staticMethod;

  public boolean isStaticMethod() {
    return staticMethod;
  }

  /**
   * return true if is use a bean (that this dao manage) as parameter.
   *
   * @return return true if is use a bean (that this dao manage) as parameter.
   */
  public boolean hasBeanAsParameter() {
    TypeName entityTypeName = TypeUtility.typeName(this.getEntity().getElement());

    for (Pair<String, TypeName> item : this.parameters) {
      if (item.value1.equals(entityTypeName)) {
        return true;
      }
    }

    return false;
  }

  /**
   * has children selects
   *
   * @return
   */
  public boolean hasChildrenSelects() {
    return childrenSelects.size() > 0;
  }

  /**
   * Instantiates a new SQ lite model method.
   *
   * @param parent         the parent
   * @param element        the element
   * @param annotationList the annotation list
   */
  public SQLiteModelMethod(SQLiteDaoDefinition parent, ExecutableElement element, List<ModelAnnotation> annotationList) {
    super(element);

    staticMethod = element.getModifiers().contains(Modifier.STATIC);

    // before proceed convert typevariable in right typename
    parent.resolveTypeVariable(this);

    this.parent = new WeakReference<SQLiteDaoDefinition>(parent);

    // detect type of operation

    if (annotationList != null) {
      this.annotations.addAll(annotationList);
    }

    this.parameterAlias2NameField = new HashMap<>();
    this.parameterName2Alias = new HashMap<>();
    this.parameterName2Adapter = new HashMap<>();

    // element.isV

    // analyze method looking for BindSqlParam
    for (VariableElement p : element.getParameters()) {

      BindSqlParam paramAlias = p.getAnnotation(BindSqlParam.class);
      if (paramAlias != null) {
        // check for name
        if (StringUtils.hasText(paramAlias.value())) {
          String alias = paramAlias.value();
          parameterAlias2NameField.put(alias, p.getSimpleName().toString());
          parameterName2Alias.put(p.getSimpleName().toString(), alias);
        }

        // check for adapter
        String paramAdapter = AnnotationUtility.extractAsClassName(p, BindSqlParam.class, AnnotationAttributeType.ADAPTER);
        if (!NoAdapter.class.getCanonicalName().equals(paramAdapter)) {
          this.parameterName2Adapter.put(p.getSimpleName().toString(), paramAdapter);
        }

      }

      if (TypeUtility.isEquals(TypeUtility.typeName(p.asType()), parent.getEntityClassName())) {
        this.parameterBeanName = p.getSimpleName().toString();
      }

      BindSqlDynamicWhere paramDynamicWhereName = p.getAnnotation(BindSqlDynamicWhere.class);
      if (paramDynamicWhereName != null) {
        this.dynamicWhereParameterName = p.getSimpleName().toString();
        PrependType prepend = PrependType.valueOf(AnnotationUtility.extractAsEnumerationValue(p, BindSqlDynamicWhere.class, AnnotationAttributeType.PREPEND));
        this.dynamicWherePrepend = prepend;

        // CONSTRAINT: @BindSqlWhere can be used only on String
        // parameter type
        AssertKripton.assertTrueOrInvalidTypeForAnnotationMethodParameterException(TypeUtility.isEquals(TypeUtility.typeName(String.class), TypeUtility.typeName(p.asType())),
                getParent().getElement(), getElement(), p, BindSqlDynamicWhere.class);
      }

      BindSqlDynamicWhereParams paramDynamicWhereArgsName = p.getAnnotation(BindSqlDynamicWhereParams.class);
      if (paramDynamicWhereArgsName != null) {

        this.dynamicWhereArgsParameterName = p.getSimpleName().toString();

        // only String[] parameter can be marked as dynamicWhereArgs
        // CONSTRAINT: @BindSqlWhereArgs can be used only on
        // String[] parameter type
        AssertKripton.assertTrueOrInvalidTypeForAnnotationMethodParameterException(TypeUtility.isEquals(ArrayTypeName.of(String.class), TypeUtility.typeName(p.asType())),
                getParent().getElement(), getElement(), p, BindSqlDynamicWhereParams.class);
      }
    }

    // looks for dynamic where conditions
    findStringDynamicStatement(parent, BindSqlDynamicWhere.class, unsupportedSQLForDynamicWhere, new OnFoundDynamicParameter() {

      @Override
      public void onFoundParameter(String parameterName) {
        dynamicWhereParameterName = parameterName;
      }

    });

    // looks for dynamic orderBy conditions
    findStringDynamicStatement(parent, BindSqlDynamicOrderBy.class, unsupportedSQLForDynamicOrderBy, new OnFoundDynamicParameter() {

      @Override
      public void onFoundParameter(String parameterName) {
        dynamicOrderByParameterName = parameterName;
      }

    });

    // looks for dynamic pageSize
    findIntDynamicStatement(parent, BindSqlPageSize.class, unsupportedSQLForDynamicOrderBy, new OnFoundDynamicParameter() {

      @Override
      public void onFoundParameter(String parameterName) {
        dynamicPageSizeName = parameterName;
      }

    });

    // live data support BEFORE RETURN TYPE
    this.liveDataEnabled = SQLiteModelMethod.isLiveData(this);
    if (liveDataEnabled) {
      ParameterizedTypeName returnParameterizedTypeName = (ParameterizedTypeName) getReturnClass();
      this.liveDataReturnClass = returnParameterizedTypeName;
      setReturnClass(returnParameterizedTypeName.typeArguments.get(0));

      // if true, the live data is paged
      this.pagedLiveData = KriptonDynamicClassManager.getInstance().isPagedLiveData(this.liveDataReturnClass);
    }

    // detect entity, before other things like jql
    if (element.getAnnotation(BindSqlSelect.class) != null) {
      // try to detect if
      TypeName returnType = SelectBuilderUtility.extractReturnType(this);

      if (returnType == null) {
        this.entity = getParent().getEntity();
      } else {
        this.entity = BindDataSourceSubProcessor.getInstance().createSQLEntity(parent.getParent(), parent.getElement(), returnType.toString(), false);
      }
    } else {
      // this is not a select
      this.entity = getParent().getEntity();
    }

    // check if we have jql annotation attribute
    String preparedJql = getJQLDeclared();

    this.jql = JQLBuilder.buildJQL(this, preparedJql);

    // content provider generation
    BindContentProviderEntry annotation = element.getAnnotation(BindContentProviderEntry.class);
    BindContentProviderPath annotationPath = parent.getElement().getAnnotation(BindContentProviderPath.class);
    if (annotationPath != null && annotation != null) {
      // manage content provider generation
      String methodPath = "";
      if (StringUtils.hasText(annotation.path())) {
        methodPath = annotation.path();
      }

      AssertKripton.assertTrue(!this.hasDynamicPageSizeConditions(), "Method %s.%s can not be marked with @%s annotation and contains parameter with @%s annotation", getParent().getName(),
              getName(), BindContentProviderEntry.class.getSimpleName(), BindSqlPageSize.class.getSimpleName(), IncompatibleAnnotationException.class);

      this.contentProviderEntryPathEnabled = true;
      this.contentProviderEntryPath = methodPath;
      this.contentProviderMethodName = getElement().getSimpleName().toString() + parent.contentProviderCounter + "ForContentProvider";

      parent.contentProviderCounter++;

      final SQLiteEntity entity = this.getEntity();

      String contentProviderUri = contentProviderUri();

      AssertKripton.assertTrueOrInvalidMethodSignException(!contentProviderUri.endsWith("/"), this, " content provider URI '%s' can not finish with '/'", contentProviderUri);
      AssertKripton.assertTrueOrInvalidMethodSignException(!this.contentProviderPath().contains("//"), this, " content provider URI '%s' can not contain with '//'", contentProviderUri);

      List<ContentUriPlaceHolder> uriParams = ContentUriChecker.getInstance().extract(contentProviderUri);
      String uriTemplate = ContentUriChecker.getInstance().replace(contentProviderUri(), new UriPlaceHolderReplacerListener() {

        @Override
        public String onParameterName(int pathSegmentIndex, String name) {
          JQLParameterName pName = JQLParameterName.parse(name);

          String propertyName = pName.getValue();

          SQLProperty entityProperty = entity.get(propertyName);
          TypeName methodParamTypeName = SQLiteModelMethod.this.findParameterTypeByAliasOrName(name);

          if (entityProperty != null) {
            TypeName entityPropertyType = entityProperty.getPropertyType().getTypeName();
            if (TypeUtility.isString(entityPropertyType)) {
              return "*";
            } else if (TypeUtility.isTypeIncludedIn(entityPropertyType, Long.class, Long.TYPE)) {
              return "#";
            } else {
              AssertKripton.fail("In '%s.%s', parameter '%s' of type '%s', can not be used in path content provider path '%s'", getParent().getName(), getName(), name,
                      entityPropertyType, contentProviderUri());
            }
          } else if (methodParamTypeName != null) {
            if (methodParamTypeName != null && TypeUtility.isTypeIncludedIn(methodParamTypeName, String.class)) {
              return "*";
            } else if (methodParamTypeName != null && TypeUtility.isTypeIncludedIn(methodParamTypeName, Long.class, Long.TYPE)) {
              return "#";
            } else {
              AssertKripton.fail("In '%s.%s', parameter '%s' has an invalid type '%s' to be used in path content provider path '%s'", getParent().getName(), getName(), name,
                      methodParamTypeName, contentProviderUri());
            }

          } else {
            AssertKripton.fail("Invalid parameter '%s' is used in content provider path '%s' associated to method '%s.%s'", name, contentProviderUri(), getParent().getName(), getName());
          }
          return null;

        }
      });

      this.contentProviderUriVariables = uriParams;
      this.contentProviderUriTemplate = uriTemplate;

      // if we have a path, we have to remove the initial /
      this.contentProviderEntryPathTemplate = uriTemplate.substring(getParent().getParent().contentProviderUri().length());
      if (this.contentProviderEntryPathTemplate.startsWith("/"))
        contentProviderEntryPathTemplate = this.contentProviderEntryPathTemplate.substring(1);

      // INSERT from SELECT type SQL can not be used with content provider
      AssertKripton.assertTrueOrInvalidMethodSignException(!(this.jql != null && this.jql.operationType == JQLType.INSERT && this.jql.containsSelectOperation), this,
              " INSERT-FROM-SELECT sql can not be used for content provider");

      // UPDATE from SELECT type SQL can not be used with content provider
      AssertKripton.assertTrueOrInvalidMethodSignException(!(this.jql != null && this.jql.operationType == JQLType.UPDATE && this.jql.containsSelectOperation), this,
              " UPDATE-FROM-SELECT sql can not be used for content provider");
    }

    if (element.getAnnotation(BindSqlSelect.class) != null) {
      FindSqlChildSelectVisitor visitor = new FindSqlChildSelectVisitor();

      for (AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
        Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues = annotationMirror.getElementValues();

        if (BindSqlSelect.class.getName().equals(annotationMirror.getAnnotationType().toString())) {
          for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues.entrySet()) {
            String key = entry.getKey().getSimpleName().toString();

            entry.getValue().accept(visitor, key);
          }
          List<Triple<String, String, SQLiteModelMethod>> childrenSelects = visitor.getChildrenSelects();
          this.childrenSelects = childrenSelects;

          break;
        }
      }
    }

  }

  public boolean isPagedLiveData() {
    return pagedLiveData;
  }

  /**
   * Checks if is live data.
   *
   * @param methodDefinition the method definition
   * @return true, if is live data
   */
  public static boolean isLiveData(SQLiteModelMethod methodDefinition) {
    boolean result = false;

    TypeName returnTypeName = methodDefinition.getReturnClass();
    if (returnTypeName instanceof ParameterizedTypeName) {
      ParameterizedTypeName returnParameterizedTypeName = (ParameterizedTypeName) returnTypeName;
      ClassName returnParameterizedClassName = returnParameterizedTypeName.rawType;
//			Class<?> wrapperClazz;
//			try {
//				wrapperClazz = Class.forName(returnParameterizedClassName.toString());
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//				throw (new KriptonRuntimeException(e));
//			}
//
//			String wrapperName = wrapperClazz.getName();
      if (KriptonDynamicClassManager.getInstance().isLiveData(returnParameterizedClassName.toString())) {
        result = true;
      }
    }

    return result;
  }

  /**
   * Checks for adapter for param.
   *
   * @param paramName the param name
   * @return true, if successful
   */
  public boolean hasAdapterForParam(String paramName) {
    return this.parameterName2Adapter.containsKey(paramName);
  }

  /**
   * Gets the JQL declared.
   *
   * @return the JQL declared
   */
  private String getJQLDeclared() {
    ModelAnnotation inserAnnotation = this.getAnnotation(BindSqlInsert.class);
    ModelAnnotation updateAnnotation = this.getAnnotation(BindSqlUpdate.class);
    ModelAnnotation selectAnnotation = this.getAnnotation(BindSqlSelect.class);
    ModelAnnotation deleteAnnotation = this.getAnnotation(BindSqlDelete.class);

    String jql = null;
    int counter = 0;

    if (selectAnnotation != null) {
      jql = selectAnnotation.getAttribute(AnnotationAttributeType.JQL);

      if (StringUtils.hasText(jql)) {
        counter++;

        AssertKripton.assertTrue(selectAnnotation.getAttributeCount() > 1, "Annotation %s in method %s.%s have more than one annotation with JQL attribute", selectAnnotation.getSimpleName(),
                this.getParent().getName(), this.getName());
      }
    }

    if (inserAnnotation != null) {
      jql = inserAnnotation.getAttribute(AnnotationAttributeType.JQL);
      if (StringUtils.hasText(jql)) {
        counter++;

        AssertKripton.assertTrue(inserAnnotation.getAttributeCount() > 1, "Annotation %s in method %s.%s have more than one annotation with JQL attribute", inserAnnotation.getSimpleName(),
                this.getParent().getName(), this.getName());
      }
    }

    if (updateAnnotation != null) {
      jql = updateAnnotation.getAttribute(AnnotationAttributeType.JQL);
      if (StringUtils.hasText(jql)) {
        counter++;

        AssertKripton.assertTrue(updateAnnotation.getAttributeCount() > 1, "Annotation %s in method %s.%s have more than one annotation with JQL attribute", updateAnnotation.getSimpleName(),
                this.getParent().getName(), this.getName());
      }
    }

    if (deleteAnnotation != null) {
      jql = deleteAnnotation.getAttribute(AnnotationAttributeType.JQL);
      if (StringUtils.hasText(jql)) {
        counter++;

        AssertKripton.assertTrue(deleteAnnotation.getAttributeCount() > 1, "Annotation %s in method %s.%s have more than one annotation with JQL attribute", deleteAnnotation.getSimpleName(),
                this.getParent().getName(), this.getName());
      }
    }

    AssertKripton.assertTrue(counter <= 1, "Method %s.%s have more than one annotation with JQL attribute", this.getParent().getName(), this.getName());

    // remove unscape charater (example \'%\' -> '%')
    jql = StringEscapeUtils.unescapeEcmaScript(jql);
    return jql;

  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.abubusoft.kripton.processor.sqlite.model.SQLiteModelElement#accept(
   * com.abubusoft.kripton.processor.sqlite.model.SQLiteModelElementVisitor)
   */
  @Override
  public void accept(SQLiteModelElementVisitor visitor) throws Exception {
    visitor.visit(this);
  }

  /**
   * The unsupported SQL for dynamic where.
   */
  static List<Class<? extends Annotation>> unsupportedSQLForDynamicWhere = new ArrayList<>();

  /**
   * The unsupported SQL for dynamic order by.
   */
  static List<Class<? extends Annotation>> unsupportedSQLForDynamicOrderBy = new ArrayList<>();

  static {
    unsupportedSQLForDynamicWhere.add(BindSqlInsert.class);

    unsupportedSQLForDynamicOrderBy.add(BindSqlInsert.class);
    unsupportedSQLForDynamicOrderBy.add(BindSqlUpdate.class);
    unsupportedSQLForDynamicOrderBy.add(BindSqlDelete.class);
  }

  /**
   * Look for a method parameter which is annotated with an annotationClass
   * annotation. When it is found, a client action is required through
   * listener.
   *
   * @param <A>                  the generic type
   * @param parent               the parent
   * @param annotationClazz      the annotation clazz
   * @param unsupportedQueryType the unsupported query type
   * @param listener             the listener
   */
  private <A extends Annotation> void findStringDynamicStatement(SQLiteDaoDefinition parent, Class<A> annotationClazz, List<Class<? extends Annotation>> unsupportedQueryType,
                                                                 OnFoundDynamicParameter listener) {

    int counter = 0;
    for (VariableElement p : element.getParameters()) {
      A annotation = p.getAnnotation(annotationClazz);
      if (annotation != null) {
        // Dynamic queries can not be used in Inser SQL.
        for (Class<? extends Annotation> item : unsupportedQueryType) {
          AssertKripton.assertTrueOrInvalidMethodSignException(element.getAnnotation(item) == null, this, "in this method is not allowed to mark parameters with @%s annotation.",
                  annotationClazz.getSimpleName());
        }

        AssertKripton.assertTrueOrInvalidMethodSignException(TypeUtility.isString(TypeUtility.typeName(p)), this, "only String parameters can be marked with @%s annotation.",
                annotationClazz.getSimpleName());

        listener.onFoundParameter(p.getSimpleName().toString());
        counter++;
      }
    }
    AssertKripton.assertTrueOrInvalidMethodSignException(counter < 2, this, "there are %s parameters marked with @%s. Only one is allowed.", counter, annotationClazz.getSimpleName());
  }

  /**
   * Look for a method parameter which is annotated with an annotationClass
   * annotation. When it is found, a client action is required through
   * listener.
   *
   * @param <A>                  the generic type
   * @param parent               the parent
   * @param annotationClazz      the annotation clazz
   * @param unsupportedQueryType the unsupported query type
   * @param listener             the listener
   */
  private <A extends Annotation> void findIntDynamicStatement(SQLiteDaoDefinition parent, Class<A> annotationClazz, List<Class<? extends Annotation>> unsupportedQueryType,
                                                              OnFoundDynamicParameter listener) {

    int counter = 0;
    for (VariableElement p : element.getParameters()) {
      A annotation = p.getAnnotation(annotationClazz);
      if (annotation != null) {
        // Dynamic queries can not be used in Inser SQL.
        for (Class<? extends Annotation> item : unsupportedQueryType) {
          AssertKripton.assertTrueOrInvalidMethodSignException(element.getAnnotation(item) == null, this, "in this method is not allowed to mark parameters with @%s annotation.",
                  annotationClazz.getSimpleName());
        }

        AssertKripton.assertTrueOrInvalidMethodSignException(TypeUtility.isTypeIncludedIn(TypeUtility.typeName(p), Integer.TYPE), this,
                "only a int parameter can be marked with @%s annotation.", annotationClazz.getSimpleName());

        listener.onFoundParameter(p.getSimpleName().toString());
        counter++;
      }
    }
    AssertKripton.assertTrueOrInvalidMethodSignException(counter < 2, this, "there are %s parameters marked with @%s. Only one is allowed.", counter, annotationClazz.getSimpleName());
  }

  /**
   * Retrieve for a method's parameter its alias, used to work with queries.
   * If no alias is present, typeName will be used.
   *
   * @param name the name
   * @return the string
   */
  public String findParameterAliasByName(String name) {
    if (parameterName2Alias.containsKey(name)) {
      return parameterName2Alias.get(name);
    }

    return name;
  }

  /**
   * Check if method contains a parameter with value as typeName.
   *
   * @param nameOrAlias parameter typeName to find
   * @return TypeMirror associated
   */
  public String findParameterNameByAlias(String nameOrAlias) {
    String[] arrays = nameOrAlias.split("\\.");
    String prefix = "";

    if (arrays.length == 2) {
      nameOrAlias = arrays[0];
      prefix = "." + arrays[1];

    }

    if (parameterAlias2NameField.containsKey(nameOrAlias)) {
      return parameterAlias2NameField.get(nameOrAlias) + prefix;
    }

    return nameOrAlias + prefix;
  }

  /**
   * Check if method contains a parameter with value as typeName.
   *
   * @param name the name
   * @return TypeMirror associated
   */
  public TypeName findParameterTypeByAliasOrName(String name) {
    if (parameterAlias2NameField.containsKey(name)) {
      return findParameterType(parameterAlias2NameField.get(name));
    }

    return findParameterType(name);
  }

  /**
   * Gets the parent.
   *
   * @return the parent
   */
  public SQLiteDaoDefinition getParent() {
    return parent.get();
  }

  /**
   * Checks for dynamic order by conditions.
   *
   * @return true, if successful
   */
  public boolean hasDynamicOrderByConditions() {
    return StringUtils.hasText(dynamicOrderByParameterName);
  }

  /**
   * Checks for dynamic where conditions.
   *
   * @return true, if successful
   */
  public boolean hasDynamicWhereConditions() {
    return StringUtils.hasText(dynamicWhereParameterName);
  }

  /**
   * Checks for dynamic where args.
   *
   * @return true, if successful
   */
  public boolean hasDynamicWhereArgs() {
    return StringUtils.hasText(dynamicWhereArgsParameterName);
  }

  /**
   * Checks for dynamic page size conditions.
   *
   * @return true, if successful
   */
  public boolean hasDynamicPageSizeConditions() {
    return StringUtils.hasText(dynamicPageSizeName);
  }

  /**
   * Checks if is this dynamic where conditions name.
   *
   * @param parameterName the parameter name
   * @return true, if is this dynamic where conditions name
   */
  public boolean isThisDynamicWhereConditionsName(String parameterName) {
    return StringUtils.hasText(dynamicWhereParameterName) && parameterName.equals(dynamicWhereParameterName);
  }

  /**
   * Checks if is log enabled.
   *
   * @return true, if is log enabled
   */
  public boolean isLogEnabled() {
    return getParent().isLogEnabled();
  }

  /**
   * Checks if is this dynamic page size name.
   *
   * @param parameterName the parameter name
   * @return true, if is this dynamic page size name
   */
  public boolean isThisDynamicPageSizeName(String parameterName) {
    return StringUtils.hasText(dynamicPageSizeName) && parameterName.equals(dynamicPageSizeName);
  }

  /**
   * Checks if is this dynamic where args name.
   *
   * @param parameterName the parameter name
   * @return true, if is this dynamic where args name
   */
  public boolean isThisDynamicWhereArgsName(String parameterName) {
    return StringUtils.hasText(dynamicWhereArgsParameterName) && parameterName.equals(dynamicWhereArgsParameterName);
  }

  /**
   * Checks for paginated result parameter.
   *
   * @return true, if successful
   */
  public boolean hasPaginatedResultParameter() {
    return StringUtils.hasText(paginatedResultName);
  }

  /**
   * Content provider uri.
   *
   * @return the string
   */
  public String contentProviderUri() {
    if (!contentProviderEntryPathEnabled)
      return "";

    return this.getParent().contentProviderUri() + (StringUtils.hasText(contentProviderEntryPath) ? ("/" + contentProviderEntryPath) : "");
  }

  /**
   * Content provider path.
   *
   * @return the string
   */
  public String contentProviderPath() {
    if (!contentProviderEntryPathEnabled)
      return "";

    return (StringUtils.hasText(contentProviderEntryPath) ? ("/" + contentProviderEntryPath) : "");
  }

  /*
   * (non-Javadoc)
   *
   * @see com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLContext#
   * getContextDescription()
   */
  @Override
  public String getContextDescription() {
    String msg = String.format("In method '%s.%s'", getParent().getElement().getSimpleName().toString(), getElement().getSimpleName().toString());

    return msg;
  }

  /**
   * Gets the adapter for param.
   *
   * @param paramName the param name
   * @return the adapter for param
   */
  public TypeName getAdapterForParam(String paramName) {
    if (this.hasAdapterForParam(paramName)) {
      return TypeUtility.typeName(this.parameterName2Adapter.get(paramName));
    } else {
      return null;
    }
  }

  /**
   * Find entity property.
   *
   * @return the string
   */
  public String findEntityProperty() {
    SQLiteEntity entity = getEntity();

    for (Pair<String, TypeName> item : this.parameters) {
      if (item.value1.equals(TypeUtility.typeName(entity.getElement()))) {
        return item.value0;
      }
    }
    return null;
  }

  /**
   * The prepared statement name.
   */
  private String preparedStatementName;

  /**
   * Builds the prepared statement name.
   *
   * @return the string
   */
  public String buildPreparedStatementName() {
    if (!StringUtils.hasText(preparedStatementName)) {
      preparedStatementName = getParent().buildPreparedStatementName(getName());
    }
    return preparedStatementName;
  }

  /**
   * Checks for dynamic parts.
   *
   * @return true, if successful
   */
  public boolean hasDynamicParts() {
    return hasDynamicOrderByConditions() || hasDynamicPageSizeConditions() || hasDynamicWhereConditions() || this.jql.hasDynamicParts() || isPagedLiveData();

  }

  /**
   * Builds the SQL name.
   *
   * @return the string
   */
  public String buildSQLName() {
    return getName() + "Sql" + nextCounter();
  }

  /**
   * Builds the SQL name with current counter
   *
   * @return the string
   */
  public String buildSQLNameWithCurrentCounter() {
    return getName() + "Sql" + (currentCounter() - 1);
  }

  public boolean isSpreadParameter(String methodParamName) {
    return this.jql.spreadParams.contains(methodParamName);
  }

  @Override
  public String getParentName() {
    return getParent().getName();
  }

  @Override
  public Finder<SQLProperty> findEntityByName(String entityName) {
    return getParent().getParent().getEntityBySimpleName(entityName);
  }

  /**
   * return true if method is select type and it projects on a different bean
   * than the one managed by dao
   *
   * @return
   */
  public boolean hasCustomProjection() {
    return JQLType.SELECT == jql.operationType && !this.getEntity().equals(this.getParent().getEntity());
  }

  public boolean hasOptionalResult() {
    // if true, field must be associate to ben attributes
    TypeName returnTypeName = getReturnClass();

    if (returnTypeName instanceof ParameterizedTypeName &&
            TypeUtility.isTypeIncludedIn((((ParameterizedTypeName) returnTypeName).rawType), Optional.class)) {
      return true;
    } else {
      return false;
    }

  }

  /**
   * Gets the typeName of optional result type.
   *
   * @return
   *    typeName of optional result type.
   */
  public TypeName getOptionalReturnClass () {
    TypeName returnTypeName = getReturnClass();
    if (hasOptionalResult()) {
      returnTypeName = ((ParameterizedTypeName) returnTypeName).typeArguments.get(0);
    }

    return returnTypeName;
  }

}
