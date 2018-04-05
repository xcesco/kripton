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
package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * When a class has a parent hierarchy with generics, This annotation allows to
 * specify used type variable.
 * </p>
 * <p>
 * For example, suppose you have the following situation:
 * </p>
 * 
 * <pre>
 *  {@literal @}BindType
 *  public class TiledMapAnimation extends Parallel2Animation&lt;TranslationFrame, TextureKeyFrame&gt;
 *  {
 *  }
 *  
 *  public abstract class Parallel2Animation&lt;K0 extends KeyFrame, K1 extends KeyFrame&gt; extends Animation&lt;K0&gt; { ... }
 * </pre>
 * 
 * <p>
 * To bind <code>TiledMapAnimation</code> you have simply to use
 * {@literal @}BindTypeVariables in this way:
 * 
 * <pre>
 * {@literal @}BindType
 * {@literal @}BindTypeVariables({"K0", "K1"})
 * public class TiledMapAnimation extends Parallel2Animation&lt;TranslationFrame, TextureKeyFrame&gt; { ... }
 * 
 * public abstract class Parallel2Animation&lt;K0 extends KeyFrame, K1 extends KeyFrame&gt; extends Animation&lt;K0&gt; { ... }
 * </pre>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindTypeVariables {

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
	 *  public class TiledMapAnimation extends Parallel2Animation&lt;TranslationFrame, TextureKeyFrame&gt;
	 *  {
	 *  }
	 *  
	 *  public abstract class Parallel2Animation&lt;K0 extends KeyFrame, K1 extends KeyFrame&gt; extends Animation&lt;K0&gt; { ... }
	 * </pre>
	 * 
	 * <p>
	 * To bind <code>TiledMapAnimation</code> you have simply to use
	 * {@literal @}BindTypeVariables in this way:
	 * 
	 * <pre>
	 * {@literal @}BindType
	 * {@literal @}BindTypeVariables({"K0", "K1"})
	 * public class TiledMapAnimation extends Parallel2Animation&lt;TranslationFrame, TextureKeyFrame&gt; { ... }
	 * 
	 * public abstract class Parallel2Animation&lt;K0 extends KeyFrame, K1 extends KeyFrame&gt; extends Animation&lt;K0&gt; { ... }
	 * </pre>
	 * 
	 * 
	 * @return type variables
	 */
	String[] value() default {};

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
	 * public class Class1&lt;A, B, C&gt; {
	 * 
	 * 	public A valueA;
	 * 
	 * 	public B valueB;
	 * 
	 * 	public C valueC;
	 * }
	 * 
	 * public class Class2&lt;A, B&gt; extends Class1&lt;A, B, String&gt; {
	 * }
	 * 
	 * {@literal @}BindType
	 * {@literal @}BintTypeVariable(value = { "A", "B", "C" }, typeParameters = { Integer.class, Date.class, String.class })
	 * public class Class3 extends Class2&lt;Integer, Date&gt; {
	 * 
	 * }
	 * </pre>
	 * 
	 * <p>
	 * Class <code>Class2</code> hide the third parameter <code>C</code>, so in
	 * class <code>Class3</code> is not possible define entire collection of
	 * type variables. <code>typeParameters</code> allows to specify directly
	 * type parameters used in class hierarchy.
	 * </p>
	 * 
	 * 
	 * @return type variables
	 */
	Class<?>[] typeParameters() default {};
}
