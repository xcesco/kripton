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
import com.abubusoft.kripton.processor.core.reflect.PropertyUtility;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

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
					ModelProperty associatedProperty = entity.findPropertyByName(p.getSimpleName().toString());
					TypeName associatedType= TypeName.get(p.asType());
					if (associatedProperty!=null && TypeUtility.isCollection(associatedType)) {
						associatedType=associatedProperty.getPropertyType().typeName;
					}
					
					params.add(new Pair<String, TypeName>(p.getSimpleName().toString(),associatedType));
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
		
		AssertKripton.assertTrueOfInvalidConstructor(entity.emptyContructor==true || entity.immutableConstructors!=null, entity);
	}

	public static void generateImmutableVariableReset(ModelClass<?> entity, Builder methodBuilder) {
		generateImmutableVariableInternal(entity, methodBuilder, false);
	}

	public static void generateImmutableVariableInit(ModelClass<?> entity, Builder methodBuilder) {
		generateImmutableVariableInternal(entity, methodBuilder, true);
	}

	/**
	 * 
	 * @param entity
	 * @param methodBuilder
	 * @param entityName
	 */
	public static void generateImmutableVariableCopyFromEntity(ModelClass<?> entity, Builder methodBuilder,
			String entityName) {
		methodBuilder.addComment(
				"immutable object: initialize temporary variables for properties with entity propertiy values");
		for (Pair<String, TypeName> property : entity.getImmutableConstructors()) {
			methodBuilder.addCode("$L$L=$L.$L;\n", IMMUTABLE_PREFIX, property.value0,
					entityName, PropertyUtility.getter(entity.get(property.value0)));
		}
	}

	private static void generateImmutableVariableInternal(ModelClass<?> entity, Builder methodBuilder,
			boolean declare) {
		methodBuilder.addComment("immutable object: initialize temporary variables for properties");
		for (Pair<String, TypeName> property : entity.getImmutableConstructors()) {
			if (declare) {
				methodBuilder.addCode("$T ", property.value1);
			}
			methodBuilder.addCode("$L$L=$L;\n", IMMUTABLE_PREFIX, property.value0,
					TypeUtility.getDefaultValue(property.value1));
		}
	}

	/**
	 * used for example for dao select result
	 * 
	 * @param entity
	 * @param methodBuilder
	 * @param name
	 * @param typeName
	 */
	public static void generateImmutableCollectionIfPossible(ModelClass<?> entity, Builder methodBuilder, String name,
			TypeName typeName) {
		if (TypeUtility.isList(typeName)
				&& ((ParameterizedTypeName) typeName).rawType.equals(ClassName.get(List.class))) {
			methodBuilder.addCode("($L==null ? null : $T.unmodifiableList($L))", name, Collections.class, name);
		} else if (TypeUtility.isSet(typeName)
				&& ((ParameterizedTypeName) typeName).rawType.equals(ClassName.get(SortedSet.class))) {
			methodBuilder.addCode("($L==null ? null : $T.unmodifiableSortedSet($L))", name, Collections.class, name);
		} else if (TypeUtility.isSet(typeName)
				&& ((ParameterizedTypeName) typeName).rawType.equals(ClassName.get(Set.class))) {
			methodBuilder.addCode("($L==null ? null : $T.unmodifiableSet($L))", name, Collections.class, name);
		} else if (TypeUtility.isMap(typeName)
				&& ((ParameterizedTypeName) typeName).rawType.equals(ClassName.get(SortedMap.class))) {
			methodBuilder.addCode("($L==null ? null : $T.unmodifiableSortedMap($L))", name, Collections.class, name);
		} else if (TypeUtility.isMap(typeName)
				&& ((ParameterizedTypeName) typeName).rawType.equals(ClassName.get(Map.class))) {
			methodBuilder.addCode("($L==null ? null : $T.unmodifiableMap($L))", name, Collections.class, name);
		} else {
			methodBuilder.addCode(name);
		}
	}

	public static void generateImmutableEntityCreation(ModelClass<?> entity, Builder methodBuilder, String instanceName,
			boolean createInstance) {
		String separator = "";
		methodBuilder.addComment("immutable object: inizialize object");
		if (createInstance) {
			methodBuilder.addCode("$T ", entity.getElement());
		}

		methodBuilder.addCode("$L=new $T(", instanceName, entity.getElement());
		for (Pair<String, TypeName> property : entity.getImmutableConstructors()) {
			if (TypeUtility.isList(property.value1)
					&& ((ParameterizedTypeName) property.value1).rawType.equals(ClassName.get(List.class))) {
				methodBuilder.addCode(separator + "($L==null ? null : $T.unmodifiableList($L))",
						IMMUTABLE_PREFIX + property.value0, Collections.class, IMMUTABLE_PREFIX + property.value0);
			} else if (TypeUtility.isSet(property.value1)
					&& ((ParameterizedTypeName) property.value1).rawType.equals(ClassName.get(SortedSet.class))) {
				methodBuilder.addCode(separator + "($L==null ? null : $T.unmodifiableSortedSet($L))",
						IMMUTABLE_PREFIX + property.value0, Collections.class, IMMUTABLE_PREFIX + property.value0);
			} else if (TypeUtility.isSet(property.value1)
					&& ((ParameterizedTypeName) property.value1).rawType.equals(ClassName.get(Set.class))) {
				methodBuilder.addCode(separator + "($L==null ? null : $T.unmodifiableSet($L))",
						IMMUTABLE_PREFIX + property.value0, Collections.class, IMMUTABLE_PREFIX + property.value0);
			} else if (TypeUtility.isMap(property.value1)
					&& ((ParameterizedTypeName) property.value1).rawType.equals(ClassName.get(SortedMap.class))) {
				methodBuilder.addCode(separator + "($L==null ? null : $T.unmodifiableSortedMap($L))",
						IMMUTABLE_PREFIX + property.value0, Collections.class, IMMUTABLE_PREFIX + property.value0);
			} else if (TypeUtility.isMap(property.value1)
					&& ((ParameterizedTypeName) property.value1).rawType.equals(ClassName.get(Map.class))) {
				methodBuilder.addCode(separator + "($L==null ? null : $T.unmodifiableMap($L))",
						IMMUTABLE_PREFIX + property.value0, Collections.class, IMMUTABLE_PREFIX + property.value0);
			} else {
				methodBuilder.addCode(separator + IMMUTABLE_PREFIX + property.value0);
			}
			separator = ",";
		}
		methodBuilder.addCode(");\n");

	}

}
