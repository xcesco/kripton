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
 * This is one the key annotation for Kripton. It is used to generate for
 * annotated Java classes a BinderMapper. It's used on class definitions.
 * 
 * <h3>Attributes</h3> Defined attributes are:
 * 
 * <ul>
 * <li>value: name of the element. For XML it's the tag name. For JSON it has no
 * use. For Property format it's the name of property. If no value is specified,
 * the name of the element (tag or attribute for xml, name for properties just
 * for example) will be the field name.</li>
 * <li>allFields: All fields are binded, for each kind of binding. Default value
 * is true. If this attribute is false it is necessary to specify which field is
 * needed to be persisted by @Bind annotation.</li>
 * </ul>
 * <h3>How it is used</h3> When a Java class is annotated by {@code @Bind}
 * annotation, a class mapper will be created by Kripton Annotation Processor.
 * For the class Friend:
 * 
 * <pre>
 * &#64;BindType
 * public class Image {
 * 	public String id;
 * 	public String format;
 * 	public String url;
 * 	public String description;
 * }
 * </pre>
 * 
 * The annotation processor will generate <code>FriendBindMap</code>:
 * 
 * <pre>
&#64;BindMap(Image.class)
public class ImageBindMap extends AbstractMapper&lt;Image&gt; {
  &#64;Override
  public int serializeOnJackson(Image object, JsonGenerator jacksonSerializer) throws Exception {
    ...
  }

  &#64;Override
  public int serializeOnJacksonAsString(Image object, JsonGenerator jacksonSerializer) throws Exception {
    ...
  }

  &#64;Override
  public void serializeOnXml(Image object, XMLSerializer xmlSerializer, int currentEventType) throws Exception {
    ...
  }

  &#64;Override
  public Image parseOnJackson(JsonParser jacksonParser) throws Exception {
    ...
  }

  &#64;Override
  public Image parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    ...
  }

  &#64;Override
  public Image parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    ...
  }
}
 * </pre>
 * 
 * This kind of class will be used by binder contexts to do serialize o
 * deserialize Java bean. This class is showed just to understand how Kripton
 * Annotation Processor works.
 * 
 * To persist Friend class in JSON:
 * 
 * <pre>
Friend input=new Friend();
...
String result=KriptonBinder.jsonBind().serialize(input);
 * </pre>
 * 
 * To deserialize a Friend class from its JSON representation:
 * 
 * <pre>
String buffer=...
...
Friend result=KriptonBinder.jsonBind().parse(buffer, Friend.class);
 * </pre>
 * 
 * Persistence on XML format is similar to persist on JSON or other format,
 * registry XML binder context before persist bean.
 * 
 * <pre>
Friend input=new Friend();
...
String result=KriptonBinder.xmlBind().serialize(input);
...
 * </pre>
 * 
 * To deserialize a Friend class from its JSON rapresentation:
 * 
 * <pre>
String buffer="{\"id\":23,\"name\":\"dummy name\"}";
...
Friend result=KriptonBinder.xmlBind().parse(buffer, Friend.class);
 * </pre>
 * 
 * For other format like YAML or CBOR or (Java) Properties, before use parsing
 * or serialize an object, you have to registry specific binding context. To
 * enable the optional persistence context:
 * 
 * <pre>
 * // registry CBOR context
 * KriptonBinder.registryBinder(new KriptonCborContext());
 * 
 * // registry YAML context
 * KriptonBinder.registryBinder(new KriptonYamlContext());
 * 
 * // registry (Java) Properties context
 * KriptonBinder.registryBinder(new KriptonPropertiesContext());
 * </pre>
 * 
 * Every context can be used only if you include relative dependencies:
 * 
 * <ul>
 * <li>kripton-dataformat-yaml for YAML format</li>
 * <li>kripton-dataformat-properties for (Java) properties</li>
 * <li>kripton-dataformat-cbor for CBOR format</li>
 * <li>kripton-dataformat-smile for SMILE format</li>
 * </ul>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
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
}
