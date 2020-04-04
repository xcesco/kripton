/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
/* -*-             c-basic-offset: 4; indent-tabs-mode: nil; -*-  //------100-columns-wide------>|*/
// for license please see accompanying LICENSE.txt file (available also at http://www.xmlpull.org/)

package com.abubusoft.kripton.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;

// TODO: Auto-generated Javadoc
/**
 * XML Pull Parser is an interface that defines parsing functionality provided
 * in <a href="http://www.xmlpull.org/">XMLPULL V1 API</a> (visit this website
 * to learn more about API and its implementations).
 * 
 * <p>
 * There are following different kinds of parser depending on which features are
 * set:
 * <ul>
 * <li><b>non-validating</b> parser as defined in XML 1.0 spec when
 * FEATURE_PROCESS_DOCDECL is set to true
 * <li><b>validating parser</b> as defined in XML 1.0 spec when
 * FEATURE_VALIDATION is true (and that implies that FEATURE_PROCESS_DOCDECL is
 * true)
 * <li>when FEATURE_PROCESS_DOCDECL is false (this is default and if different
 * value is required necessary must be changed before parsing is started) then
 * parser behaves like XML 1.0 compliant non-validating parser under condition
 * that <em>no DOCDECL is present</em> in XML documents (internal entites can
 * still be defined with defineEntityReplacementText()). This mode of operation
 * is intended <b>for operation in constrained environments</b> such as J2ME.
 * </ul>
 * 
 * 
 * <p>
 * There are two key methods: next() and nextToken(). While next() provides
 * access to high level parsing events, nextToken() allows access to lower level
 * tokens.
 * 
 * <p>
 * The current event state of the parser can be determined by calling the
 * <a href="#getEventType()">getEventType()</a> method. Initially, the parser is
 * in the <a href="#START_DOCUMENT">START_DOCUMENT</a> state.
 * 
 * <p>
 * The method <a href="#next()">next()</a> advances the parser to the next
 * event. The int value returned from next determines the current parser state
 * and is identical to the value returned from following calls to getEventType
 * ().
 * 
 * <p>
 * Th following event types are seen by next()
 * <dl>
 * <dt><a href="#START_TAG">START_TAG</a>
 * <dd>An XML start tag was read.
 * <dt><a href="#TEXT">TEXT</a>
 * <dd>Text content was read; the text content can be retrieved using the
 * getText() method. (when in validating mode next() will not report ignorable
 * whitespace, use nextToken() instead)
 * <dt><a href="#END_TAG">END_TAG</a>
 * <dd>An end tag was read
 * <dt><a href="#END_DOCUMENT">END_DOCUMENT</a>
 * <dd>No more events are available
 * </dl>
 * 
 * <p>
 * after first next() or nextToken() (or any other next*() method) is called
 * user application can obtain XML version, standalone and encoding from XML
 * declaration in following ways:
 * <ul>
 * <li><b>version</b>: getProperty(&quot;<a href=
 * "http://xmlpull.org/v1/doc/properties.html#xmldecl-version">http://xmlpull.org/v1/doc/properties.html#xmldecl-version</a>&quot;)
 * returns String ("1.0") or null if XMLDecl was not read or if property is not
 * supported
 * <li><b>standalone</b>: getProperty(&quot;<a href=
 * "http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone">http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone</a>&quot;)
 * returns Boolean: null if there was no standalone declaration or if property
 * is not supported otherwise returns Boolean(true) if standalone="yes" and
 * Boolean(false) when standalone="no"
 * <li><b>encoding</b>: obtained from getInputEncoding() null if stream had
 * unknown encoding (not set in setInputStream) and it was not declared in
 * XMLDecl
 * </ul>
 * 
 * A minimal example for using this API may look as follows:
 * 
 * <pre>
 * import java.io.IOException;
 * import java.io.StringReader;
 * 
 * import org.xmlpull.v1.XmlPullParser;
 * import org.xmlpull.v1.<a href=
 * "XmlPullParserException.html">XmlPullParserException</a>;
 * import org.xmlpull.v1.<a href=
 * "XmlPullParserFactory.html">XmlPullParserFactory</a>;
 * 
 * public class SimpleXmlPullApp
 * {
 * 
 *     public static void main (String args[])
 *        , IOException
 *     {
 *         XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
 *         factory.setNamespaceAware(true);
 *         XmlPullParser xpp = factory.newPullParser();
 * 
 *         xpp.<a href="#setInput">setInput</a>( new StringReader ( "&lt;foo&gt;Hello World!&lt;/foo&gt;" ) );
 *         int eventType = xpp.getEventType();
 *         while (eventType != XmlPullParser.END_DOCUMENT) {
 *          if(eventType == XmlPullParser.START_DOCUMENT) {
 *              System.out.println("Start document");
 *          } else if(eventType == XmlPullParser.START_TAG) {
 *              System.out.println("Start tag "+xpp.<a href=
 * "#getName()">getName()</a>);
 *          } else if(eventType == XmlPullParser.END_TAG) {
 *              System.out.println("End tag "+xpp.getName());
 *          } else if(eventType == XmlPullParser.TEXT) {
 *              System.out.println("Text "+xpp.<a href=
 * "#getText()">getText()</a>);
 *          }
 *          eventType = xpp.next();
 *         }
 *         System.out.println("End document");
 *     }
 * }
 * </pre>
 * 
 * <p>
 * The above example will generate the following output:
 * 
 * <pre>
 * Start document
 * Start tag foo
 * Text Hello World!
 * End tag foo
 * End document
 * </pre>
 * 
 * <p>
 * For more details on API usage, please refer to the quick Introduction
 * available at <a href="http://www.xmlpull.org">http://www.xmlpull.org</a>
 *
 * @author <a href=
 *         "http://www-ai.cs.uni-dortmund.de/PERSONAL/haustein.html">Stefan
 *         Haustein</a>
 * @author <a href="http://www.extreme.indiana.edu/~aslom/">Aleksander
 *         Slominski</a>
 * @see #defineEntityReplacementText
 * @see #getName
 * @see #getNamespace
 * @see #getText
 * @see #next
 * @see #nextToken
 * @see #setInput
 * @see #FEATURE_PROCESS_DOCDECL
 * @see #FEATURE_VALIDATION
 * @see #START_DOCUMENT
 * @see #START_TAG
 * @see #TEXT
 * @see #END_TAG
 * @see #END_DOCUMENT
 */

public interface XmlPullParser {

	/** This constant represents the default namespace (empty string ""). */
	String NO_NAMESPACE = "";

	// ----------------------------------------------------------------------------
	// EVENT TYPES as reported by next()

	

	/**
	 * This array can be used to convert the event type integer constants such
	 * as START_TAG or TEXT to to a string. For example, the value of
	 * TYPES[START_TAG] is the string "START_TAG".
	 *
	 * This array is intended for diagnostic output only. Relying on the
	 * contents of the array may be dangerous since malicious applications may
	 * alter the array, although it is final, due to limitations of the Java
	 * language.
	 */
	String[] TYPES = { "START_DOCUMENT", "END_DOCUMENT", "START_TAG", "END_TAG", "TEXT", "CDSECT", "ENTITY_REF", "IGNORABLE_WHITESPACE", "PROCESSING_INSTRUCTION", "COMMENT", "DOCDECL" };

	// ----------------------------------------------------------------------------
	// namespace related features

	/**
	 * This feature determines whether the parser processes namespaces. As for
	 * all features, the default value is false.
	 * <p>
	 * <strong>NOTE:</strong> The value can not be changed during parsing an
	 * must be set before parsing.
	 *
	 * @see #getFeature
	 * @see #setFeature
	 */
	String FEATURE_PROCESS_NAMESPACES = "http://xmlpull.org/v1/doc/features.html#process-namespaces";

	/**
	 * This feature determines whether namespace attributes are exposed via the
	 * attribute access methods. Like all features, the default value is false.
	 * This feature cannot be changed during parsing.
	 *
	 * @see #getFeature
	 * @see #setFeature
	 */
	String FEATURE_REPORT_NAMESPACE_ATTRIBUTES = "http://xmlpull.org/v1/doc/features.html#report-namespace-prefixes";

	/**
	 * This feature determines whether the document declaration is processed. If
	 * set to false, the DOCDECL event type is reported by nextToken() and
	 * ignored by next().
	 *
	 * If this feature is activated, then the document declaration must be
	 * processed by the parser.
	 *
	 * <p>
	 * <strong>Please note:</strong> If the document type declaration was
	 * ignored, entity references may cause exceptions later in the parsing
	 * process. The default value of this feature is false. It cannot be changed
	 * during parsing.
	 *
	 * @see #getFeature
	 * @see #setFeature
	 */
	String FEATURE_PROCESS_DOCDECL = "http://xmlpull.org/v1/doc/features.html#process-docdecl";

	/**
	 * If this feature is activated, all validation errors as defined in the XML
	 * 1.0 specification are reported. This implies that FEATURE_PROCESS_DOCDECL
	 * is true and both, the internal and external document type declaration
	 * will be processed.
	 * <p>
	 * <strong>Please Note:</strong> This feature can not be changed during
	 * parsing. The default value is false.
	 *
	 * @see #getFeature
	 * @see #setFeature
	 */
	String FEATURE_VALIDATION = "http://xmlpull.org/v1/doc/features.html#validation";

	/**
	 * Use this call to change the general behaviour of the parser, such as
	 * namespace processing or doctype declaration handling. This method must be
	 * called before the first call to next or nextToken. Otherwise, an
	 * exception is thrown.
	 * <p>
	 * Example: call setFeature(FEATURE_PROCESS_NAMESPACES, true) in order to
	 * switch on namespace processing. The initial settings correspond to the
	 * properties requested from the XML Pull Parser factory. If none were
	 * requested, all features are deactivated by default.
	 *
	 * @param name
	 *            the name
	 * @param state
	 *            the state
	 * @exception IllegalArgumentException
	 *                If string with the feature name is null
	 */
	void setFeature(String name, boolean state);

	/**
	 * Returns the current value of the given feature.
	 * <p>
	 * <strong>Please note:</strong> unknown features are
	 * <strong>always</strong> returned as false.
	 *
	 * @param name
	 *            The name of feature to be retrieved.
	 * @return The value of the feature.
	 * @exception IllegalArgumentException
	 *                if string the feature name is null
	 */

	boolean getFeature(String name);

	/**
	 * Set the value of a property.
	 * 
	 * The property name is any fully-qualified URI.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @exception IllegalArgumentException
	 *                If string with the property name is null
	 */
	void setProperty(String name, Object value);

	/**
	 * Look up the value of a property.
	 *
	 * The property name is any fully-qualified URI.
	 * <p>
	 * <strong>NOTE:</strong> unknown properties are <strong>always</strong>
	 * returned as null.
	 *
	 * @param name
	 *            The name of property to be retrieved.
	 * @return The value of named property.
	 */
	Object getProperty(String name);

	/**
	 * Set the input source for parser to the given reader and resets the
	 * parser. The event type is set to the initial value START_DOCUMENT.
	 * Setting the reader to null will just stop parsing and reset parser state,
	 * allowing the parser to free internal resources such as parsing buffers.
	 *
	 * @param in
	 *            the new input
	 */
	void setInput(Reader in);

	/**
	 * Sets the input stream the parser is going to process. This call resets
	 * the parser state and sets the event type to the initial value
	 * START_DOCUMENT.
	 *
	 * <p>
	 * <strong>NOTE:</strong> If an input encoding string is passed, it MUST be
	 * used. Otherwise, if inputEncoding is null, the parser SHOULD try to
	 * determine input encoding following XML 1.0 specification (see below). If
	 * encoding detection is supported then following feature <a href=
	 * "http://xmlpull.org/v1/doc/features.html#detect-encoding">http://xmlpull.org/v1/doc/features.html#detect-encoding</a>
	 * MUST be true amd otherwise it must be false
	 *
	 * @param inputStream
	 *            contains a raw byte input stream of possibly unknown encoding
	 *            (when inputEncoding is null).
	 *
	 * @param inputEncoding
	 *            if not null it MUST be used as encoding for inputStream
	 */
	void setInput(InputStream inputStream, String inputEncoding);

	/**
	 * Returns the input encoding if known, null otherwise. If
	 * setInput(InputStream, inputEncoding) was called with an inputEncoding
	 * value other than null, this value must be returned from this method.
	 * Otherwise, if inputEncoding is null and the parser supports the encoding
	 * detection feature
	 * (http://xmlpull.org/v1/doc/features.html#detect-encoding), it must return
	 * the detected encoding. If setInput(Reader) was called, null is returned.
	 * After first call to next if XML declaration was present this method will
	 * return encoding declared.
	 * 
	 * @return string
	 */
	String getInputEncoding();

	/**
	 * Set new value for entity replacement text as defined in
	 * <a href="http://www.w3.org/TR/REC-xml#intern-replacement">XML 1.0 Section
	 * 4.5 Construction of Internal Entity Replacement Text</a>. If
	 * FEATURE_PROCESS_DOCDECL or FEATURE_VALIDATION are set, calling this
	 * function will result in an exception -- when processing of DOCDECL is
	 * enabled, there is no need to the entity replacement text manually.
	 * 
	 * <p>
	 * The motivation for this function is to allow very small implementations
	 * of XMLPULL that will work in J2ME environments. Though these
	 * implementations may not be able to process the document type declaration,
	 * they still can work with known DTDs by using this function.
	 * 
	 * <p>
	 * <b>Please notes:</b> The given value is used literally as replacement
	 * text and it corresponds to declaring entity in DTD that has all special
	 * characters escaped: left angle bracket is replaced with &amp;lt;,
	 * ampersand with &amp;amp; and so on.
	 * 
	 * <p>
	 * <b>Note:</b> The given value is the literal replacement text and must not
	 * contain any other entity reference (if it contains any entity reference
	 * there will be no further replacement).
	 * 
	 * <p>
	 * <b>Note:</b> The list of pre-defined entity names will always contain
	 * standard XML entities such as amp (&amp;amp;), lt (&amp;lt;), gt
	 * (&amp;gt;), quot (&amp;quot;), and apos (&amp;apos;). Those cannot be
	 * redefined by this method!
	 *
	 * @param entityName
	 *            the entity name
	 * @param replacementText
	 *            the replacement text
	 * @see #setInput
	 * @see #FEATURE_PROCESS_DOCDECL
	 * @see #FEATURE_VALIDATION
	 */
	void defineEntityReplacementText(String entityName, String replacementText);

	/**
	 * Returns the numbers of elements in the namespace stack for the given
	 * depth. If namespaces are not enabled, 0 is returned.
	 * 
	 * <p>
	 * <b>NOTE:</b> when parser is on END_TAG then it is allowed to call this
	 * function with getDepth()+1 argument to retrieve position of namespace
	 * prefixes and URIs that were declared on corresponding START_TAG.
	 * <p>
	 * <b>NOTE:</b> to retrieve list of namespaces declared in current element:
	 * 
	 * <pre>
	 *       XmlPullParser pp = ...
	 *       int nsStart = pp.getNamespaceCount(pp.getDepth()-1);
	 *       int nsEnd = pp.getNamespaceCount(pp.getDepth());
	 *       for (int i = nsStart; i &lt; nsEnd; i++) {
	 *          String prefix = pp.getNamespacePrefix(i);
	 *          String ns = pp.getNamespaceUri(i);
	 *           // ...
	 *      }
	 * </pre>
	 *
	 * @param depth
	 *            the depth
	 * @return count
	 * @see #getNamespacePrefix
	 * @see #getNamespaceUri
	 * @see #getNamespace()
	 * @see #getNamespace(String)
	 */
	int getNamespaceCount(int depth);

	/**
	 * Returns the namespace prefix for the given position in the namespace
	 * stack. Default namespace declaration (xmlns='...') will have null as
	 * prefix. If the given index is out of range, an exception is thrown.
	 * <p>
	 * <b>Please note:</b> when the parser is on an END_TAG, namespace prefixes
	 * that were declared in the corresponding START_TAG are still accessible
	 * although they are no longer in scope.
	 *
	 * @param pos
	 *            the pos
	 * @return string
	 */
	String getNamespacePrefix(int pos);

	/**
	 * Returns the namespace URI for the given position in the namespace stack
	 * If the position is out of range, an exception is thrown.
	 * <p>
	 * <b>NOTE:</b> when parser is on END_TAG then namespace prefixes that were
	 * declared in corresponding START_TAG are still accessible even though they
	 * are not in scope
	 *
	 * @param pos
	 *            the pos
	 * @return string
	 */
	String getNamespaceUri(int pos);

	/**
	 * Returns the URI corresponding to the given prefix, depending on current
	 * state of the parser.
	 * 
	 * <p>
	 * If the prefix was not declared in the current scope, null is returned.
	 * The default namespace is included in the namespace table and is available
	 * via getNamespace (null).
	 * 
	 * <p>
	 * This method is a convenience method for
	 * 
	 * <pre>
	 * for (int i = getNamespaceCount(getDepth()) - 1; i &gt;= 0; i--) {
	 * 	if (getNamespacePrefix(i).equals(prefix)) {
	 * 		return getNamespaceUri(i);
	 * 	}
	 * }
	 * return null;
	 * </pre>
	 * 
	 * <p>
	 * <strong>Please note:</strong> parser implementations may provide more
	 * efficient lookup, e.g. using a Hashtable. The 'xml' prefix is bound to
	 * "http://www.w3.org/XML/1998/namespace", as defined in the
	 * <a href="http://www.w3.org/TR/REC-xml-names/#ns-using">Namespaces in
	 * XML</a> specification. Analogous, the 'xmlns' prefix is resolved to
	 * <a href="http://www.w3.org/2000/xmlns/">http://www.w3.org/2000/xmlns/</a>
	 *
	 * @param prefix
	 *            the prefix
	 * @return string
	 * @see #getNamespaceCount
	 * @see #getNamespacePrefix
	 * @see #getNamespaceUri
	 */
	String getNamespace(String prefix);

	// --------------------------------------------------------------------------
	// miscellaneous reporting methods

	/**
	 * Returns the current depth of the element. Outside the root element, the
	 * depth is 0. The depth is incremented by 1 when a start tag is reached.
	 * The depth is decremented AFTER the end tag event was observed.
	 *
	 * <pre>
	 * &lt;!-- outside --&gt;     0
	 * &lt;root&gt;               1
	 *   sometext                 1
	 *     &lt;foobar&gt;         2
	 *     &lt;/foobar&gt;        2
	 * &lt;/root&gt;              1
	 * &lt;!-- outside --&gt;     0
	 * </pre>
	 * 
	 * @return depth
	 */
	int getDepth();

	/**
	 * Returns a short text describing the current parser state, including the
	 * position, a description of the current event and the data source if
	 * known. This method is especially useful to provide meaningful error
	 * messages and for debugging purposes.
	 * 
	 * @return description
	 */
	String getPositionDescription();

	/**
	 * Returns the current line number, starting from 1. When the parser does
	 * not know the current line number or can not determine it, -1 is returned
	 * (e.g. for WBXML).
	 *
	 * @return current line number or -1 if unknown.
	 */
	int getLineNumber();

	/**
	 * Returns the current column number, starting from 0. When the parser does
	 * not know the current column number or can not determine it, -1 is
	 * returned (e.g. for WBXML).
	 *
	 * @return current column number or -1 if unknown.
	 */
	int getColumnNumber();

	// --------------------------------------------------------------------------
	// TEXT related methods

	/**
	 * Checks whether the current TEXT event contains only whitespace
	 * characters. For IGNORABLE_WHITESPACE, this is always true. For TEXT and
	 * CDSECT, false is returned when the current event text contains at least
	 * one non-white space character. For any other event type an exception is
	 * thrown.
	 *
	 * <p>
	 * <b>Please note:</b> non-validating parsers are not able to distinguish
	 * whitespace and ignorable whitespace, except from whitespace outside the
	 * root element. Ignorable whitespace is reported as separate event, which
	 * is exposed via nextToken only.
	 * 
	 * @return whitespace
	 *
	 */
	boolean isWhitespace();

	/**
	 * Returns the text content of the current event as String. The value
	 * returned depends on current event type, for example for TEXT event it is
	 * element content (this is typical case when next() is used).
	 *
	 * See description of nextToken() for detailed description of possible
	 * returned values for different types of events.
	 *
	 * <p>
	 * <strong>NOTE:</strong> in case of ENTITY_REF, this method returns the
	 * entity replacement text (or null if not available). This is the only case
	 * where getText() and getTextCharacters() return different values.
	 * 
	 * @return text
	 *
	 * @see #getEventType
	 * @see #next
	 * @see #nextToken
	 */
	String getText();

	/**
	 * check if current tag has text.
	 *
	 * @return has text
	 */
	boolean hasText();

	/**
	 * Returns the buffer that contains the text of the current event, as well
	 * as the start offset and length relevant for the current event. See
	 * getText(), next() and nextToken() for description of possible returned
	 * values.
	 * 
	 * <p>
	 * <strong>Please note:</strong> this buffer must not be modified and its
	 * content MAY change after a call to next() or nextToken(). This method
	 * will always return the same value as getText(), except for ENTITY_REF. In
	 * the case of ENTITY ref, getText() returns the replacement text and this
	 * method returns the actual input buffer containing the entity name. If
	 * getText() returns null, this method returns null as well and the values
	 * returned in the holder array MUST be -1 (both start and length).
	 *
	 * @param holderForStartAndLength
	 *            Must hold an 2-element int array into which the start offset
	 *            and length values will be written.
	 * @return char buffer that contains the text of the current event (null if
	 *         the current event has no text associated).
	 * @see #getText
	 * @see #next
	 * @see #nextToken
	 */
	char[] getTextCharacters(int[] holderForStartAndLength);

	// --------------------------------------------------------------------------
	// START_TAG / END_TAG shared methods

	/**
	 * Returns the namespace URI of the current element. The default namespace
	 * is represented as empty string. If namespaces are not enabled, an empty
	 * String ("") is always returned. The current event must be START_TAG or
	 * END_TAG; otherwise, null is returned.
	 * 
	 * @return namespace
	 */
	String getNamespace();

	/**
	 * For START_TAG or END_TAG events, the (local) name of the current element
	 * is returned when namespaces are enabled. When namespace processing is
	 * disabled, the raw name is returned. For ENTITY_REF events, the entity
	 * name is returned. If the current event is not START_TAG, END_TAG, or
	 * ENTITY_REF, null is returned.
	 * <p>
	 * <b>Please note:</b> To reconstruct the raw element name when namespaces
	 * are enabled and the prefix is not null, you will need to add the prefix
	 * and a colon to localName..
	 * 
	 * @return name
	 *
	 */
	String getName();

	/**
	 * Returns the prefix of the current element. If the element is in the
	 * default namespace (has no prefix), null is returned. If namespaces are
	 * not enabled, or the current event is not START_TAG or END_TAG, null is
	 * returned.
	 * 
	 * @return prefix
	 */
	String getPrefix();

	/**
	 * Returns true if the current event is START_TAG and the tag is degenerated
	 * (e.g. &lt;foobar/&gt;).
	 * <p>
	 * <b>NOTE:</b> if the parser is not on START_TAG, an exception will be
	 * thrown.
	 * 
	 * @return empty tag
	 */
	boolean isEmptyElementTag();

	// --------------------------------------------------------------------------
	// START_TAG Attributes retrieval methods

	/**
	 * Returns the number of attributes of the current start tag, or -1 if the
	 * current event type is not START_TAG.
	 *
	 * @return count
	 * @see #getAttributeNamespace
	 * @see #getAttributeName
	 * @see #getAttributePrefix
	 * @see #getAttributeValue
	 */
	int getAttributeCount();

	/**
	 * Returns the namespace URI of the attribute with the given index (starts
	 * from 0). Returns an empty string ("") if namespaces are not enabled or
	 * the attribute has no namespace. Throws an IndexOutOfBoundsException if
	 * the index is out of range or the current event type is not START_TAG.
	 *
	 * <p>
	 * <strong>NOTE:</strong> if FEATURE_REPORT_NAMESPACE_ATTRIBUTES is set then
	 * namespace attributes (xmlns:ns='...') must be reported with namespace
	 * <a href="http://www.w3.org/2000/xmlns/">http://www.w3.org/2000/xmlns/</a>
	 * (visit this URL for description!). The default namespace attribute
	 * (xmlns="...") will be reported with empty namespace.
	 * <p>
	 * <strong>NOTE:</strong>The xml prefix is bound as defined in
	 * <a href="http://www.w3.org/TR/REC-xml-names/#ns-using">Namespaces in
	 * XML</a> specification to "http://www.w3.org/XML/1998/namespace".
	 *
	 * @param index
	 *            zero-based index of attribute
	 * @return attribute namespace, empty string ("") is returned if namespaces
	 *         processing is not enabled or namespaces processing is enabled but
	 *         attribute has no namespace (it has no prefix).
	 */
	String getAttributeNamespace(int index);

	/**
	 * Returns the local name of the specified attribute if namespaces are
	 * enabled or just attribute name if namespaces are disabled. Throws an
	 * IndexOutOfBoundsException if the index is out of range or current event
	 * type is not START_TAG.
	 *
	 * @param index
	 *            zero-based index of attribute
	 * @return attribute name (null is never returned)
	 */
	String getAttributeName(int index);

	/**
	 * Returns the prefix of the specified attribute Returns null if the element
	 * has no prefix. If namespaces are disabled it will always return null.
	 * Throws an IndexOutOfBoundsException if the index is out of range or
	 * current event type is not START_TAG.
	 *
	 * @param index
	 *            zero-based index of attribute
	 * @return attribute prefix or null if namespaces processing is not enabled.
	 */
	String getAttributePrefix(int index);

	/**
	 * Returns the type of the specified attribute If parser is non-validating
	 * it MUST return CDATA.
	 *
	 * @param index
	 *            zero-based index of attribute
	 * @return attribute type (null is never returned)
	 */
	String getAttributeType(int index);

	/**
	 * Returns if the specified attribute was not in input was declared in XML.
	 * If parser is non-validating it MUST always return false. This information
	 * is part of XML infoset:
	 *
	 * @param index
	 *            zero-based index of attribute
	 * @return false if attribute was in input
	 */
	boolean isAttributeDefault(int index);

	/**
	 * Returns the given attributes value. Throws an IndexOutOfBoundsException
	 * if the index is out of range or current event type is not START_TAG.
	 * 
	 * <p>
	 * <strong>NOTE:</strong> attribute value must be normalized (including
	 * entity replacement text if PROCESS_DOCDECL is false) as described in
	 * <a href="http://www.w3.org/TR/REC-xml#AVNormalize">XML 1.0 section 3.3.3
	 * Attribute-Value Normalization</a>
	 *
	 * @param index
	 *            zero-based index of attribute
	 * @return value of attribute (null is never returned)
	 * @see #defineEntityReplacementText
	 */
	String getAttributeValue(int index);

	/**
	 * Returns the attributes value identified by namespace URI and namespace
	 * localName. If namespaces are disabled namespace must be null. If current
	 * event type is not START_TAG then IndexOutOfBoundsException will be
	 * thrown.
	 * 
	 * <p>
	 * <strong>NOTE:</strong> attribute value must be normalized (including
	 * entity replacement text if PROCESS_DOCDECL is false) as described in
	 * <a href="http://www.w3.org/TR/REC-xml#AVNormalize">XML 1.0 section 3.3.3
	 * Attribute-Value Normalization</a>
	 *
	 * @param namespace
	 *            Namespace of the attribute if namespaces are enabled otherwise
	 *            must be null
	 * @param name
	 *            If namespaces enabled local name of attribute otherwise just
	 *            attribute name
	 * @return value of attribute or null if attribute with given name does not
	 *         exist
	 * @see #defineEntityReplacementText
	 */
	String getAttributeValue(String namespace, String name);

	// --------------------------------------------------------------------------
	// actual parsing methods

	/**
	 * Returns the type of the current event (START_TAG, END_TAG, TEXT, etc.)
	 * 
	 * @return event type
	 *
	 * @see #next()
	 * @see #nextToken()
	 */
	EventType getEventType();

	/**
	 * Get next parsing event - element content wil be coalesced and only one
	 * TEXT event must be returned for whole element content (comments and
	 * processing instructions will be ignored and entity references must be
	 * expanded or exception mus be thrown if entity reference can not be
	 * expanded). If element content is empty (content is "") then no TEXT event
	 * will be reported.
	 * 
	 * <p>
	 * <b>NOTE:</b> empty element (such as &lt;tag/&gt;) will be reported with
	 * two separate events: START_TAG, END_TAG - it must be so to preserve
	 * parsing equivalency of empty element to &lt;tag&gt;&lt;/tag&gt;. (see
	 * isEmptyElementTag ())
	 *
	 * @return next
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see #isEmptyElementTag
	 * @see #START_TAG
	 * @see #TEXT
	 * @see #END_TAG
	 * @see #END_DOCUMENT
	 */
	EventType next() throws IOException;

	/**
	 * This method works similarly to next() but will expose additional event
	 * types (COMMENT, CDSECT, DOCDECL, ENTITY_REF, PROCESSING_INSTRUCTION, or
	 * IGNORABLE_WHITESPACE) if they are available in input.
	 * 
	 * <p>
	 * If special feature <a href=
	 * "http://xmlpull.org/v1/doc/features.html#xml-roundtrip">FEATURE_XML_ROUNDTRIP</a>
	 * (identified by URI:
	 * http://xmlpull.org/v1/doc/features.html#xml-roundtrip) is enabled it is
	 * possible to do XML document round trip ie. reproduce exectly on output
	 * the XML input using getText(): returned content is always unnormalized
	 * (exactly as in input). Otherwise returned content is end-of-line
	 * normalized as described
	 * <a href="http://www.w3.org/TR/REC-xml#sec-line-ends">XML 1.0 End-of-Line
	 * Handling</a> and. Also when this feature is enabled exact content of
	 * START_TAG, END_TAG, DOCDECL and PROCESSING_INSTRUCTION is available.
	 * 
	 * <p>
	 * Here is the list of tokens that can be returned from nextToken() and what
	 * getText() and getTextCharacters() returns:
	 * <dl>
	 * <dt>START_DOCUMENT
	 * <dd>null
	 * <dt>END_DOCUMENT
	 * <dd>null
	 * <dt>START_TAG
	 * <dd>null unless FEATURE_XML_ROUNDTRIP enabled and then returns XML tag,
	 * ex: &lt;tag attr='val'&gt;
	 * <dt>END_TAG
	 * <dd>null unless FEATURE_XML_ROUNDTRIP id enabled and then returns XML
	 * tag, ex: &lt;/tag&gt;
	 * <dt>TEXT
	 * <dd>return element content. <br>
	 * Note: that element content may be delivered in multiple consecutive TEXT
	 * events.
	 * <dt>IGNORABLE_WHITESPACE
	 * <dd>return characters that are determined to be ignorable white space. If
	 * the FEATURE_XML_ROUNDTRIP is enabled all whitespace content outside root
	 * element will always reported as IGNORABLE_WHITESPACE otherwise reporting
	 * is optional. <br>
	 * Note: that element content may be delivered in multiple consecutive
	 * IGNORABLE_WHITESPACE events.
	 * <dt>CDSECT
	 * <dd>return text <em>inside</em> CDATA (ex. 'fo&lt;o' from
	 * &lt;!CDATA[fo&lt;o]]&gt;)
	 * <dt>PROCESSING_INSTRUCTION
	 * <dd>if FEATURE_XML_ROUNDTRIP is true return exact PI content ex: 'pi foo'
	 * from &lt;?pi foo?&gt; otherwise it may be exact PI content or
	 * concatenation of PI target, space and data so for example for &lt;?target
	 * data?&gt; string &quot;target data&quot; may be returned if
	 * FEATURE_XML_ROUNDTRIP is false.
	 * <dt>COMMENT
	 * <dd>return comment content ex. 'foo bar' from &lt;!--foo bar--&gt;
	 * <dt>ENTITY_REF
	 * <dd>getText() MUST return entity replacement text if PROCESS_DOCDECL is
	 * false otherwise getText() MAY return null, additionally
	 * getTextCharacters() MUST return entity name (for example 'entity_name'
	 * for &amp;entity_name;). <br>
	 * <b>NOTE:</b> this is the only place where value returned from getText()
	 * and getTextCharacters() <b>are different</b> <br>
	 * <b>NOTE:</b> it is user responsibility to resolve entity reference if
	 * PROCESS_DOCDECL is false and there is no entity replacement text set in
	 * defineEntityReplacementText() method (getText() will be null) <br>
	 * <b>NOTE:</b> character entities (ex. &amp;#32;) and standard entities
	 * such as &amp;amp; &amp;lt; &amp;gt; &amp;quot; &amp;apos; are reported as
	 * well and are <b>not</b> reported as TEXT tokens but as ENTITY_REF tokens!
	 * This requirement is added to allow to do roundtrip of XML documents!
	 * <dt>DOCDECL
	 * <dd>if FEATURE_XML_ROUNDTRIP is true or PROCESS_DOCDECL is false then
	 * return what is inside of DOCDECL for example it returns:
	 * 
	 * <pre>
	 * &quot; titlepage SYSTEM "http://www.foo.bar/dtds/typo.dtd"
	 * [&lt;!ENTITY % active.links "INCLUDE"&gt;]&quot;
	 * </pre>
	 * <p>
	 * for input document that contained:
	 * 
	 * <pre>
	 * &lt;!DOCTYPE titlepage SYSTEM "http://www.foo.bar/dtds/typo.dtd"
	 * [&lt;!ENTITY % active.links "INCLUDE"&gt;]&gt;
	 * </pre>
	 * 
	 * otherwise if FEATURE_XML_ROUNDTRIP is false and PROCESS_DOCDECL is true
	 * then what is returned is undefined (it may be even null)</dd>
	 * </dl>
	 * 
	 * <p>
	 * <strong>NOTE:</strong> there is no guarantee that there will only one
	 * TEXT or IGNORABLE_WHITESPACE event from nextToken() as parser may chose
	 * to deliver element content in multiple tokens (dividing element content
	 * into chunks)
	 * </p>
	 * 
	 * <p>
	 * <strong>NOTE:</strong> whether returned text of token is end-of-line
	 * normalized is depending on FEATURE_XML_ROUNDTRIP.
	 * </p>
	 * 
	 * <p>
	 * <strong>NOTE:</strong> XMLDecl (&lt;?xml ...?&gt;) is not reported but
	 * its content is available through optional properties (see class
	 * description above).
	 * </p>
	 *
	 * @return token
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see #next
	 * @see #START_TAG
	 * @see #TEXT
	 * @see #END_TAG
	 * @see #END_DOCUMENT
	 * @see #COMMENT
	 * @see #DOCDECL
	 * @see #PROCESSING_INSTRUCTION
	 * @see #ENTITY_REF
	 * @see #IGNORABLE_WHITESPACE
	 */
	EventType nextToken() throws IOException;

	// -----------------------------------------------------------------------------
	// utility methods to mak XML parsing easier ...

	/**
	 * Test if the current event is of the given type and if the namespace and
	 * name do match. null will match any namespace and any name. If the test is
	 * not passed, an exception is thrown. The exception text indicates the
	 * parser position, the expected event and the current event that is not
	 * meeting the requirement.
	 * 
	 * <p>
	 * Essentially it does this
	 * 
	 * <pre>
	 * if (type != getEventType() || (namespace != null &amp;&amp; !namespace.equals(getNamespace())) || (name != null &amp;&amp; !name.equals(getName())))
	 * 	throw new XmlPullParserException("expected " + TYPES[type] + getPositionDescription());
	 * </pre>
	 *
	 * @param type
	 *            the type
	 * @param namespace
	 *            the namespace
	 * @param name
	 *            the name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	void require(EventType type, String namespace, String name) throws IOException;

	/**
	 * If current event is START_TAG then if next element is TEXT then element
	 * content is returned or if next event is END_TAG then empty string is
	 * returned, otherwise exception is thrown. After calling this function
	 * successfully parser will be positioned on END_TAG.
	 * 
	 * <p>
	 * The motivation for this function is to allow to parse consistently both
	 * empty elements and elements that has non empty content, for example for
	 * input:
	 * <ol>
	 * <li>&lt;tag&gt;foo&lt;/tag&gt;
	 * <li>&lt;tag&gt;&lt;/tag&gt; (which is equivalent to &lt;tag/&gt; both
	 * </ol>
	 * input can be parsed with the same code:
	 * 
	 * 
	 * <pre>
	 *   p.nextTag()
	 *   p.requireEvent(p.START_TAG, "", "tag");
	 *   String content = p.nextText();
	 *   p.requireEvent(p.END_TAG, "", "tag");
	 * </pre>
	 * 
	 * This function together with nextTag make it very easy to parse XML that
	 * has no mixed content.
	 * 
	 * 
	 * <p>
	 * Essentially it does this
	 * 
	 * <pre>
	 * if (getEventType() != START_TAG) {
	 * 	throw new XmlPullParserException("parser must be on START_TAG to read next text", this, null);
	 * }
	 * int eventType = next();
	 * if (eventType == TEXT) {
	 * 	String result = getText();
	 * 	eventType = next();
	 * 	if (eventType != END_TAG) {
	 * 		throw new XmlPullParserException("event TEXT it must be immediately followed by END_TAG", this, null);
	 * 	}
	 * 	return result;
	 * } else if (eventType == END_TAG) {
	 * 	return "";
	 * } else {
	 * 	throw new XmlPullParserException("parser must be on START_TAG or TEXT to read text", this, null);
	 * }
	 * </pre>
	 * 
	 * <p>
	 * <strong>Warning:</strong> Prior to API level 14, the pull parser returned
	 * by {@code
	 * android.util.Xml} did not always advance to the END_TAG event when this
	 * method was called. Work around by using manually advancing after calls to
	 * nextText():
	 * 
	 * <pre>
	 * String text = xpp.nextText();
	 * if (xpp.getEventType() != XmlPullParser.END_TAG) {
	 * 	xpp.next();
	 * }
	 * </pre>
	 *
	 * @return next text
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	String nextText() throws IOException;

	/**
	 * Call next() and return event if it is START_TAG or END_TAG otherwise
	 * throw an exception. It will skip whitespace TEXT before actual tag if
	 * any.
	 * 
	 * <p>
	 * essentially it does this
	 * 
	 * <pre>
	 * int eventType = next();
	 * if (eventType == TEXT &amp;&amp; isWhitespace()) { // skip whitespace
	 * 	eventType = next();
	 * }
	 * if (eventType != START_TAG &amp;&amp; eventType != END_TAG) {
	 * 	throw new XmlPullParserException("expected start or end tag", this, null);
	 * }
	 * return eventType;
	 * </pre>
	 *
	 * @return next tag
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	EventType nextTag() throws IOException;

	/**
	 * Checks for next.
	 *
	 * @return has next
	 */
	boolean hasNext();

	/**
	 * Reads the content of a text-only element, an exception is thrown if this
	 * is not a text-only element. Regardless of value of
	 * javax.xml.stream.isCoalescing this method always returns coalesced
	 * content. <br>
	 * Precondition: the current event is START_ELEMENT. <br>
	 * Postcondition: the current event is the corresponding END_ELEMENT.
	 * 
	 * <br>
	 * The method does the following (implementations are free to optimized but
	 * must do equivalent processing):
	 * 
	 * <pre>
	 * if (getEventType() != XMLStreamConstants.START_ELEMENT) {
	 * 	throw new XMLStreamException("parser must be on START_ELEMENT to read next text", getLocation());
	 * }
	 * int eventType = next();
	 * StringBuffer content = new StringBuffer();
	 * while (eventType != XMLStreamConstants.END_ELEMENT) {
	 * 	if (eventType == XMLStreamConstants.CHARACTERS || eventType == XMLStreamConstants.CDATA || eventType == XMLStreamConstants.SPACE || eventType == XMLStreamConstants.ENTITY_REFERENCE) {
	 * 		buf.append(getText());
	 * 	} else if (eventType == XMLStreamConstants.PROCESSING_INSTRUCTION || eventType == XMLStreamConstants.COMMENT) {
	 * 		// skipping
	 * 	} else if (eventType == XMLStreamConstants.END_DOCUMENT) {
	 * 		throw new XMLStreamException("unexpected end of document when reading element text content", this);
	 * 	} else if (eventType == XMLStreamConstants.START_ELEMENT) {
	 * 		throw new XMLStreamException("element text content may not contain START_ELEMENT", getLocation());
	 * 	} else {
	 * 		throw new XMLStreamException("Unexpected event type " + eventType, getLocation());
	 * 	}
	 * 	eventType = next();
	 * }
	 * return buf.toString();
	 * </pre>
	 *
	 * @return element text
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public String getElementText() throws IOException;

	/**
	 * <p>
	 * Read an element content as a boolean. The lexical representation of a
	 * boolean is defined by the
	 * <a href="http://www.w3.org/TR/xmlschema-2/#boolean">XML Schema
	 * boolean</a> data type. Whitespace MUST be <a href=
	 * "http://www.w3.org/TR/xmlschema-2/datatypes.html#rf-whiteSpace">collapsed</a>
	 * according to the whiteSpace facet for the XML Schema boolean data type.
	 * An exception is thrown if, after whitespace is collapsed, the resulting
	 * sequence of characters is not in the lexical space defined by the XML
	 * Schema boolean data type. (note: allowed lexical values are canonicals
	 * "true" and "false", as well as non-canonical "0" and "1")
	 * </p>
	 * <p>
	 * These are the pre- and post-conditions of calling this method:
	 * </p>
	 * <ul>
	 * <li>Precondition: the current event is START_ELEMENT.</li>
	 * <li>Postcondition: the current event is the corresponding
	 * END_ELEMENT.</li>
	 * </ul>
	 * 
	 *
	 * @return element as boolean
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public boolean getElementAsBoolean() throws IOException;

	/**
	 * Gets the element as int.
	 *
	 * @return int
	 * @throws Exception
	 *             the exception
	 */
	public int getElementAsInt() throws Exception;

	/**
	 * Gets the element as long.
	 *
	 * @return long
	 * @throws Exception
	 *             the exception
	 */
	public long getElementAsLong() throws Exception;

	/**
	 * Gets the element as float.
	 *
	 * @return float
	 * @throws Exception
	 *             the exception
	 */
	public float getElementAsFloat() throws Exception;

	/**
	 * Gets the element as double.
	 *
	 * @return double
	 * @throws Exception
	 *             the exception
	 */
	public double getElementAsDouble() throws Exception;

	/**
	 * Gets the element as integer.
	 *
	 * @return integer
	 * @throws Exception
	 *             the exception
	 */
	public BigInteger getElementAsInteger() throws Exception;

	/**
	 * Gets the element as decimal.
	 *
	 * @return decimal
	 * @throws Exception
	 *             the exception
	 */
	public BigDecimal getElementAsDecimal() throws Exception;

	/**
	 * Returns true if the current event is START_TAG and the tag is degenerated
	 * (e.g. &lt;foobar/&gt;).
	 * <p>
	 * <b>NOTE:</b> if the parser is not on START_TAG, an exception will be
	 * thrown.
	 * 
	 * @return element empty
	 */
	boolean isEmptyElement();

	/**
	 * Gets the element as binary.
	 *
	 * @return byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	byte[] getElementAsBinary() throws IOException;

	/**
	 * Gets the attribute as decimal.
	 *
	 * @param index
	 *            the index
	 * @return big decimal
	 */
	BigDecimal getAttributeAsDecimal(int index);

	/**
	 * Gets the attribute as integer.
	 *
	 * @param index
	 *            the index
	 * @return big integer
	 */
	BigInteger getAttributeAsInteger(int index);

	/**
	 * Gets the attribute index.
	 *
	 * @param namespace
	 *            the namespace
	 * @param name
	 *            the name
	 * @return attribute index
	 */
	int getAttributeIndex(String namespace, String name);

}
