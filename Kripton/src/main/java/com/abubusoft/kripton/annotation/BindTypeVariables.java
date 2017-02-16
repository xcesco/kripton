package com.abubusoft.kripton.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
	 *  public class TiledMapAnimation extends Parallel2Animation<TranslationFrame, TextureKeyFrame>
	 *  {
	 *  }
	 *  
	 *  public abstract class Parallel2Animation<K0 extends KeyFrame, K1 extends KeyFrame> extends Animation<K0> { ... }
	 * </pre>
	 * 
	 * <p>
	 * To bind <code>TiledMapAnimation</code> you have simply to use
	 * {@literal @}BindTypeVariables in this way:
	 * 
	 * <pre>
	 * {@literal @}BindType
	 * {@literal @}BindTypeVariables({"K0", "K1"})
	 * public class TiledMapAnimation extends Parallel2Animation<TranslationFrame, TextureKeyFrame> { ... }
	 * 
	 * public abstract class Parallel2Animation<K0 extends KeyFrame, K1 extends KeyFrame> extends Animation<K0> { ... }
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
	 * {@literal @}BindType
	 * {@literal @}BintTypeVariable(value = { "A", "B", "C" }, typeParameters = { Integer.class, Date.class, String.class })
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
