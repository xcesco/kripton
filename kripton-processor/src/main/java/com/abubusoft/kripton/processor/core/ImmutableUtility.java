/**
 * 
 */
package com.abubusoft.kripton.processor.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.bind.model.BindEntity;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;

/**
 * @author xcesco
 *
 */
public abstract class ImmutableUtility {

	public static String IMMUTABLE_PREFIX = "__";

	/**
	 * Analyse constructors
	 * 
	 * @param elementUtils
	 * @param entity
	 * @return
	 */
	public static void buildConstructors(Elements elementUtils, ModelClass<?> entity) {
		List<? extends Element> list = elementUtils.getAllMembers(entity.getElement());
		ExecutableElement constructor;
		List<List<Pair<String, TypeName>>> constructors = new ArrayList<>();

		for (Element item : list) {
			if (item.getKind() == ElementKind.CONSTRUCTOR && item.getModifiers().contains(Modifier.PUBLIC)) {
				constructor = (ExecutableElement) item;

				ArrayList<Pair<String, TypeName>> params = new ArrayList<>();
				for (VariableElement p : constructor.getParameters()) {
					params.add(new Pair<String, TypeName>(p.getSimpleName().toString(), TypeName.get(p.asType())));
				}

				constructors.add(params);
			}
		}

		// check if it has an empty constructor;
		for (List<Pair<String, TypeName>> currentConstructor : constructors) {
			if (currentConstructor.size() == 0) {
				entity.emptyContructor = true;
			} else if (currentConstructor.size() == entity.collection.size()) {
				// check if it is a immutable constructor
				Map<String, ModelProperty> items = new HashMap<>();

				for (ModelProperty property : entity.collection) {
					items.put(property.name, property);
				}

				// size is the same
				boolean immutableConstructor = true;
				for (Pair<String, TypeName> arg : currentConstructor) {
					ModelProperty temp = items.get(arg.value0);

					if (temp == null || !temp.isType(arg.value1)) {
						// property is not present
						immutableConstructor = false;
						break;
					}
				}
				if (immutableConstructor) {
					entity.immutableConstructors = currentConstructor;
				}

			}
		}
	}

	public static void generateImmutableVariableInit(ModelClass<?> entity, Builder methodBuilder) {
		methodBuilder.addComment("immutable object: inizialize temporary variables for properties");
		for (Pair<String, TypeName> property : entity.getImmutableConstructors()) {
			methodBuilder.addStatement("$T $L$L=$L", property.value1, IMMUTABLE_PREFIX, property.value0,
					TypeUtility.getDefaultValue(property.value1));
		}
		methodBuilder.addCode("\n");
	}

	public static void generateImmutableEntityCreation(BindEntity entity, Builder methodBuilder, String instanceName) {
		String separator = "";				
		methodBuilder.addComment("immutable object: inizialize object");
		methodBuilder.addCode("$T $L=new $T(", entity.getElement(), instanceName, entity.getElement());
		for (Pair<String, TypeName> property : entity.getImmutableConstructors()) {
			if (TypeUtility.isList(property.value1)					
					&& ((ParameterizedTypeName) property.value1).rawType.equals(ClassName.get(List.class))) {
				methodBuilder.addCode(separator + "($L==null ? null : $T.unmodifiableList($L))",
						IMMUTABLE_PREFIX+property.value0,
						Collections.class,
						IMMUTABLE_PREFIX + property.value0);
			} else if (TypeUtility.isSet(property.value1) && ((ParameterizedTypeName) property.value1).rawType.equals(ClassName.get(SortedSet.class))) {
				methodBuilder.addCode(separator + "($L==null ? null : $T.unmodifiableSortedSet($L))",
						IMMUTABLE_PREFIX+property.value0,
						Collections.class,
						IMMUTABLE_PREFIX + property.value0);			
			} else if (TypeUtility.isSet(property.value1) && ((ParameterizedTypeName) property.value1).rawType.equals(ClassName.get(Set.class))) {
				methodBuilder.addCode(separator + "($L==null ? null : $T.unmodifiableSet($L))",
						IMMUTABLE_PREFIX+property.value0,
						Collections.class,
						IMMUTABLE_PREFIX + property.value0);
			} else if (TypeUtility.isMap(property.value1) && ((ParameterizedTypeName) property.value1).rawType.equals(ClassName.get(SortedMap.class))) {
				methodBuilder.addCode(separator + "($L==null ? null : $T.unmodifiableSortedMap($L))",
						IMMUTABLE_PREFIX+property.value0,
						Collections.class,
						IMMUTABLE_PREFIX + property.value0);			
			} else if (TypeUtility.isMap(property.value1) && ((ParameterizedTypeName) property.value1).rawType.equals(ClassName.get(Map.class))) {
				methodBuilder.addCode(separator + "($L==null ? null : $T.unmodifiableMap($L))",
						IMMUTABLE_PREFIX+property.value0,
						Collections.class,
						IMMUTABLE_PREFIX + property.value0);
			} else {
				methodBuilder.addCode(separator + IMMUTABLE_PREFIX + property.value0);
			}
			separator = ",";
		}
		methodBuilder.addCode(");\n");

	}

}
