/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * This annotation is used to mark Java bean which need to be persisted.
 * </p>
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindType {

	/**
	 * Name of the element. For XML it's the tag name. For JSON it has no use.
	 * For Property format it's the name of property
	 * 
	 * @return name
	 */
	public String value() default "";

	/**
	 * All fields are binded, for each kind of binding.
	 * 
	 * @return true if all fields must be binded
	 */
	boolean allFields() default true;

	/**
	 * <p>
	 * When a class has a parent hierarchy with generics, This attribute allows
	 * to specify used type variable.
	 * </p>
	 * <p>
	 * For example, suppose you have the following situation:
	 * </p>
	 * 
	 * <pre>
	 *  {@literal @}BindType
	 *  public class TiledMapAnimation extends Parallel2Animation<TranslationFrame, TextureKeyFrame>
	 *  {
	 *  }
	 *  
	 *  public abstract class Parallel2Animation<K0 extends KeyFrame, K1 extends KeyFrame> extends Animation<K0> { ... }
	 * </pre>
	 * 
	 * <p>
	 * To bind <code>TiledMapAnimation</code> you have simply to use
	 * {@literal @}BindType in this way:
	 * 
	 * <pre>
	 * {@literal @}BindType(typeVariables={"K0", "K1"})
	 * public class TiledMapAnimation extends Parallel2Animation<TranslationFrame, TextureKeyFrame> { ... }
	 * 
	 * public abstract class Parallel2Animation<K0 extends KeyFrame, K1 extends KeyFrame> extends Animation<K0> { ... }
	 * </pre>
	 * 
	 * 
	 * @return type variables
	 */
	String[] typeVariables() default {};

	/**
	 * <p>
	 * When a class has a parent hierarchy with generics, This attribute allows
	 * to specify used type parameters. It's usefull when
	 * </p>
	 * <p>
	 * For example, suppose you have the following situation:
	 * </p>
	 * 
	 * <pre>
	 * public class Class1<A, B, C> {
	 * 
	 * 	public A valueA;
	 * 
	 * 	public B valueB;
	 * 
	 * 	public C valueC;
	 * }
	 * 
	 * public class Class2<A, B> extends Class1<A, B, String> {
	 * }
	 * 
	 * {@literal @}BindType(typeVariables = { "A", "B", "C" }, typeParameters = { Integer.class, Date.class, String.class })
	 * public class Class3 extends Class2<Integer, Date> {
	 * 
	 * }
	 * </pre>
	 * 
	 * <p>
	 * Class <code>Class2</code> hide the third parameter <code>C</code>, so in
	 * class
	 * <code>Class3</cod> is not possible define entire collection of type variables. <code>typeParameters</code>
	 * allows to specify directly type parameters used in class hierarchy.
	 * </p>
	 * 
	 * 
	 * @return type variables
	 */
	Class<?>[] typeParameters() default {};

}
