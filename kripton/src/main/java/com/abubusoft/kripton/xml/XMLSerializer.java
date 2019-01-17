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
package com.abubusoft.kripton.xml;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;

import com.abubusoft.kripton.common.Base64Utils;
import com.fasterxml.jackson.core.JsonEncoding;

// TODO: Auto-generated Javadoc
/**
 * Implementation of MXSerializer interface from XmlPull V1 API. This
 * implementation is optimzied for performance and low memory footprint.
 *
 * <p>
 * Implemented features:
 * <ul>
 * <li>FEATURE_NAMES_INTERNED - when enabled all returned names (namespaces,
 * prefixes) will be interned and it is required that all names passed as
 * arguments MUST be interned
 * <li>FEATURE_SERIALIZER_ATTVALUE_USE_APOSTROPHE
 * </ul>
 * <p>
 * Implemented properties:
 * <ul>
 * <li>PROPERTY_SERIALIZER_INDENTATION
 * <li>PROPERTY_SERIALIZER_LINE_SEPARATOR
 * </ul>
 *
 */
/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class XMLSerializer {
	
	/** The Constant XML_URI. */
	protected final static String XML_URI = "http://www.w3.org/XML/1998/namespace";
	
	/** The Constant XMLNS_URI. */
	protected final static String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
	
	/** The Constant TRACE_SIZING. */
	private static final boolean TRACE_SIZING = false;
	
	/** The Constant TRACE_ESCAPING. */
	private static final boolean TRACE_ESCAPING = false;

	/** The Constant FEATURE_SERIALIZER_ATTVALUE_USE_APOSTROPHE. */
	public static final String FEATURE_SERIALIZER_ATTVALUE_USE_APOSTROPHE = "http://xmlpull.org/v1/doc/features.html#serializer-attvalue-use-apostrophe";
	
	/** The feature names interned. */
	protected final String FEATURE_NAMES_INTERNED = "http://xmlpull.org/v1/doc/features.html#names-interned";
	
	/** The Constant PROPERTY_SERIALIZER_INDENTATION. */
	public static final String PROPERTY_SERIALIZER_INDENTATION = "http://xmlpull.org/v1/doc/properties.html#serializer-indentation";
	
	/** The Constant PROPERTY_SERIALIZER_LINE_SEPARATOR. */
	public static final String PROPERTY_SERIALIZER_LINE_SEPARATOR = "http://xmlpull.org/v1/doc/properties.html#serializer-line-separator";
	
	/** The Constant PROPERTY_LOCATION. */
	protected final static String PROPERTY_LOCATION = "http://xmlpull.org/v1/doc/properties.html#location";

	/** The names interned. */
	// properties/features
	protected boolean namesInterned;
	
	/** The attribute use apostrophe. */
	protected boolean attributeUseApostrophe;
	
	/** The indentation string. */
	protected String indentationString = null; // " ";
	
	/** The line separator. */
	protected String lineSeparator = "\n";

	/** The location. */
	protected String location;
	
	/** The out. */
	protected Writer out;

	/** The auto declared prefixes. */
	protected int autoDeclaredPrefixes;

	/** The depth. */
	protected int depth = 0;

	/** The el namespace. */
	// element stack
	protected String elNamespace[] = new String[2];
	
	/** The el name. */
	protected String elName[] = new String[elNamespace.length];
	
	/** The el prefix. */
	protected String elPrefix[] = new String[elNamespace.length];
	
	/** The el namespace count. */
	protected int elNamespaceCount[] = new int[elNamespace.length];

	/** The namespace end. */
	// namespace stack
	protected int namespaceEnd = 0;
	
	/** The namespace prefix. */
	protected String namespacePrefix[] = new String[8];
	
	/** The namespace uri. */
	protected String namespaceUri[] = new String[namespacePrefix.length];

	/** The finished. */
	protected boolean finished;
	
	/** The past root. */
	protected boolean pastRoot;
	
	/** The set prefix called. */
	protected boolean setPrefixCalled;
	
	/** The start tag incomplete. */
	protected boolean startTagIncomplete;

	/** The do indent. */
	protected boolean doIndent;
	
	/** The seen tag. */
	protected boolean seenTag;

	/** The seen bracket. */
	protected boolean seenBracket;
	
	/** The seen bracket bracket. */
	protected boolean seenBracketBracket;

	/** The Constant BUF_LEN. */
	// buffer output if neede to write escaped String see text(String)
	private static final int BUF_LEN = Runtime.getRuntime().freeMemory() > 1000000L ? 8 * 1024 : 256;
	
	/** The buf. */
	protected char buf[] = new char[BUF_LEN];

	/** The Constant precomputedPrefixes. */
	protected static final String precomputedPrefixes[];

	static {
		precomputedPrefixes = new String[32]; // arbitrary number ...
		for (int i = 0; i < precomputedPrefixes.length; i++) {
			precomputedPrefixes[i] = ("n" + i).intern();
		}
	}

	/** The check names interned. */
	private boolean checkNamesInterned = false;

	/**
	 * Instantiates a new XML serializer.
	 *
	 * @param writer the writer
	 */
	public XMLSerializer(Writer writer) {
		setOutput(writer);
	}

	/**
	 * Check interning.
	 *
	 * @param name the name
	 */
	private void checkInterning(String name) {
		if (namesInterned && name != name.intern()) {
			throw new IllegalArgumentException("all names passed as arguments must be interned" + "when NAMES INTERNED feature is enabled");
		}
	}

	/**
	 * Reset.
	 */
	protected void reset() {
		location = null;
		out = null;
		autoDeclaredPrefixes = 0;
		depth = 0;

		// nullify references on all levels to allow it to be GCed
		for (int i = 0; i < elNamespaceCount.length; i++) {
			elName[i] = null;
			elPrefix[i] = null;
			elNamespace[i] = null;
			elNamespaceCount[i] = 2;
		}

		namespaceEnd = 0;

		// NOTE: no need to intern() as all literal strings and string-valued
		// constant expressions
		// are interned. String literals are defined in 3.10.5 of the Java
		// Language Specification
		// just checking ...
		// assert "xmlns" == "xmlns".intern();
		// assert XMLNS_URI == XMLNS_URI.intern();

		// TODO: how to prevent from reporting this namespace?
		// this is special namespace declared for consistensy with XML infoset
		namespacePrefix[namespaceEnd] = "xmlns";
		namespaceUri[namespaceEnd] = XMLNS_URI;
		++namespaceEnd;

		namespacePrefix[namespaceEnd] = "xml";
		namespaceUri[namespaceEnd] = XML_URI;
		++namespaceEnd;

		finished = false;
		pastRoot = false;
		setPrefixCalled = false;
		startTagIncomplete = false;
		// doIntent is not changed
		seenTag = false;

		seenBracket = false;
		seenBracketBracket = false;
	}

	/**
	 * Ensure elements capacity.
	 */
	protected void ensureElementsCapacity() {
		final int elStackSize = elName.length;
		// assert (depth + 1) >= elName.length;
		// we add at least one extra slot ...
		final int newSize = (depth >= 7 ? 2 * depth : 8) + 2; // = lucky 7 + 1
																// //25
		if (TRACE_SIZING) {
			System.err.println(getClass().getName() + " elStackSize " + elStackSize + " ==> " + newSize);
		}
		final boolean needsCopying = elStackSize > 0;
		String[] arr = null;
		// reuse arr local variable slot
		arr = new String[newSize];
		if (needsCopying)
			System.arraycopy(elName, 0, arr, 0, elStackSize);
		elName = arr;

		arr = new String[newSize];
		if (needsCopying)
			System.arraycopy(elPrefix, 0, arr, 0, elStackSize);
		elPrefix = arr;

		arr = new String[newSize];
		if (needsCopying)
			System.arraycopy(elNamespace, 0, arr, 0, elStackSize);
		elNamespace = arr;

		final int[] iarr = new int[newSize];
		if (needsCopying) {
			System.arraycopy(elNamespaceCount, 0, iarr, 0, elStackSize);
		} else {
			// special initialization
			iarr[0] = 0;
		}
		elNamespaceCount = iarr;
	}

	/**
	 * Ensure namespaces capacity.
	 */
	protected void ensureNamespacesCapacity() { // int size) {
		// int namespaceSize = namespacePrefix != null ? namespacePrefix.length
		// : 0;
		// assert (namespaceEnd >= namespacePrefix.length);

		// if(size >= namespaceSize) {
		// int newSize = size > 7 ? 2 * size : 8; // = lucky 7 + 1 //25
		final int newSize = namespaceEnd > 7 ? 2 * namespaceEnd : 8;
		if (TRACE_SIZING) {
			System.err.println(getClass().getName() + " namespaceSize " + namespacePrefix.length + " ==> " + newSize);
		}
		final String[] newNamespacePrefix = new String[newSize];
		final String[] newNamespaceUri = new String[newSize];
		if (namespacePrefix != null) {
			System.arraycopy(namespacePrefix, 0, newNamespacePrefix, 0, namespaceEnd);
			System.arraycopy(namespaceUri, 0, newNamespaceUri, 0, namespaceEnd);
		}
		namespacePrefix = newNamespacePrefix;
		namespaceUri = newNamespaceUri;

		// TODO use hashes for quick namespace->prefix lookups
		// if( ! allStringsInterned ) {
		// int[] newNamespacePrefixHash = new int[newSize];
		// if(namespacePrefixHash != null) {
		// System.arraycopy(
		// namespacePrefixHash, 0, newNamespacePrefixHash, 0, namespaceEnd);
		// }
		// namespacePrefixHash = newNamespacePrefixHash;
		// }
		// prefixesSize = newSize;
		// ////assert nsPrefixes.length > size && nsPrefixes.length == newSize
		// }
	}

	/**
	 * Sets the feature.
	 *
	 * @param name the name
	 * @param state the state
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalStateException the illegal state exception
	 */
	public void setFeature(String name, boolean state) throws IllegalArgumentException, IllegalStateException {
		if (name == null) {
			throw new IllegalArgumentException("feature name can not be null");
		}
		if (FEATURE_NAMES_INTERNED.equals(name)) {
			namesInterned = state;
		} else if (FEATURE_SERIALIZER_ATTVALUE_USE_APOSTROPHE.equals(name)) {
			attributeUseApostrophe = state;
		} else {
			throw new IllegalStateException("unsupported feature " + name);
		}
	}

	/**
	 * Gets the feature.
	 *
	 * @param name the name
	 * @return boolean
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public boolean getFeature(String name) throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("feature name can not be null");
		}
		if (FEATURE_NAMES_INTERNED.equals(name)) {
			return namesInterned;
		} else if (FEATURE_SERIALIZER_ATTVALUE_USE_APOSTROPHE.equals(name)) {
			return attributeUseApostrophe;
		} else {
			return false;
		}
	}

	/** The offset new line. */
	// precomputed variables to simplify writing indentation
	protected int offsetNewLine;
	
	/** The indentation jump. */
	protected int indentationJump;
	
	/** The indentation buf. */
	protected char[] indentationBuf;
	
	/** The max indent level. */
	protected int maxIndentLevel;
	
	/** The write line separtor. */
	protected boolean writeLineSepartor; // should end-of-line be written
	
	/** The write indentation. */
	protected boolean writeIndentation; // is indentation used?

	/**
	 * For maximum efficiency when writing indents the required output is
	 * pre-computed This is internal function that recomputes buffer after user
	 * requested chnages.
	 */
	protected void rebuildIndentationBuf() {
		if (doIndent == false)
			return;
		final int maxIndent = 65; // hardcoded maximum indentation size in
									// characters
		int bufSize = 0;
		offsetNewLine = 0;
		if (writeLineSepartor) {
			offsetNewLine = lineSeparator.length();
			bufSize += offsetNewLine;
		}
		maxIndentLevel = 0;
		if (writeIndentation) {
			indentationJump = indentationString.length();
			maxIndentLevel = maxIndent / indentationJump;
			bufSize += maxIndentLevel * indentationJump;
		}
		if (indentationBuf == null || indentationBuf.length < bufSize) {
			indentationBuf = new char[bufSize + 8];
		}
		int bufPos = 0;
		if (writeLineSepartor) {
			for (int i = 0; i < lineSeparator.length(); i++) {
				indentationBuf[bufPos++] = lineSeparator.charAt(i);
			}
		}
		if (writeIndentation) {
			for (int i = 0; i < maxIndentLevel; i++) {
				for (int j = 0; j < indentationString.length(); j++) {
					indentationBuf[bufPos++] = indentationString.charAt(j);
				}
			}
		}
	}

	/**
	 * Write indent.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// if(doIndent) writeIndent();
	protected void writeIndent() throws IOException {
		final int start = writeLineSepartor ? 0 : offsetNewLine;
		final int level = (depth > maxIndentLevel) ? maxIndentLevel : depth;
		out.write(indentationBuf, start, ((level - 1) * indentationJump) + offsetNewLine);
	}

	/**
	 * Sets the property.
	 *
	 * @param name the name
	 * @param value the value
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalStateException the illegal state exception
	 */
	public void setProperty(String name, Object value) throws IllegalArgumentException, IllegalStateException {
		if (name == null) {
			throw new IllegalArgumentException("property name can not be null");
		}
		if (PROPERTY_SERIALIZER_INDENTATION.equals(name)) {
			indentationString = (String) value;
		} else if (PROPERTY_SERIALIZER_LINE_SEPARATOR.equals(name)) {
			lineSeparator = (String) value;
		} else if (PROPERTY_LOCATION.equals(name)) {
			location = (String) value;
		} else {
			throw new IllegalStateException("unsupported property " + name);
		}
		writeLineSepartor = lineSeparator != null && lineSeparator.length() > 0;
		writeIndentation = indentationString != null && indentationString.length() > 0;
		// optimize - do not write when nothing to write ...
		doIndent = indentationString != null && (writeLineSepartor || writeIndentation);
		// NOTE: when indentationString == null there is no indentation
		// (even though writeLineSeparator may be true ...)
		rebuildIndentationBuf();
		seenTag = false; // for consistency
	}

	/**
	 * Gets the property.
	 *
	 * @param name the name
	 * @return obj
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public Object getProperty(String name) throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("property name can not be null");
		}
		if (PROPERTY_SERIALIZER_INDENTATION.equals(name)) {
			return indentationString;
		} else if (PROPERTY_SERIALIZER_LINE_SEPARATOR.equals(name)) {
			return lineSeparator;
		} else if (PROPERTY_LOCATION.equals(name)) {
			return location;
		} else {
			return null;
		}
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	private String getLocation() {
		return location != null ? " @" + location : "";
	}

	/**
	 * Gets the writer.
	 *
	 * @return this is special method that can be accessed directly to retrieve
	 *         Writer serializer is using
	 */
	public Writer getWriter() {
		return out;
	}

	/**
	 * Sets the output.
	 *
	 * @param writer the new output
	 */
	public void setOutput(Writer writer) {
		reset();
		out = writer;
	}

	/**
	 * Sets the output.
	 *
	 * @param os the os
	 * @param encoding the encoding
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void setOutput(OutputStream os, String encoding) throws IOException {
		if (os == null)
			throw new IllegalArgumentException("output stream can not be null");
		reset();
		if (encoding != null) {
			out = new OutputStreamWriter(os, encoding);
		} else {
			out = new OutputStreamWriter(os);
		}
	}

	/**
	 * Start document.
	 *
	 * @param encoding the encoding
	 * @param standalone the standalone
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void startDocument(String encoding, Boolean standalone) throws IOException {
		@SuppressWarnings("unused")
		char apos = attributeUseApostrophe ? '\'' : '"';
		if (attributeUseApostrophe) {
			out.write("<?xml version='1.0'");
		} else {
			out.write("<?xml version=\"1.0\"");
		}
		if (encoding != null) {
			out.write(" encoding=");
			out.write(attributeUseApostrophe ? '\'' : '"');
			out.write(encoding);
			out.write(attributeUseApostrophe ? '\'' : '"');
			// out.write('\'');
		}
		if (standalone != null) {
			out.write(" standalone=");
			out.write(attributeUseApostrophe ? '\'' : '"');
			if (standalone.booleanValue()) {
				out.write("yes");
			} else {
				out.write("no");
			}
			out.write(attributeUseApostrophe ? '\'' : '"');
			// if(standalone.booleanValue()) {
			// out.write(" standalone='yes'");
			// } else {
			// out.write(" standalone='no'");
			// }
		}
		out.write("?>");
	}

	/**
	 * End document.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void endDocument() throws IOException {
		// close all unclosed tag;
		while (depth > 0) {
			endTag(elNamespace[depth], elName[depth]);
		}
		// assert depth == 0;
		// assert startTagIncomplete == false;
		finished = pastRoot = startTagIncomplete = true;
		out.flush();
	}

	/**
	 * Sets the prefix.
	 *
	 * @param prefix the prefix
	 * @param namespace the namespace
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("unused")
	public void setPrefix(String prefix, String namespace) throws IOException {
		if (startTagIncomplete)
			closeStartTag();
		// assert prefix != null;
		// assert namespace != null;
		if (prefix == null) {
			prefix = "";
		}
		if (!namesInterned) {
			prefix = prefix.intern(); // will throw NPE if prefix==null
		} else if (checkNamesInterned) {
			checkInterning(prefix);
		} else if (prefix == null) {
			throw new IllegalArgumentException("prefix must be not null" + getLocation());
		}

		// check that prefix is not duplicated ...
		for (int i = elNamespaceCount[depth]; i < namespaceEnd; i++) {
			if (prefix == namespacePrefix[i]) {
				throw new IllegalStateException("duplicated prefix " + printable(prefix) + getLocation());
			}
		}

		if (!namesInterned) {
			namespace = namespace.intern();
		} else if (checkNamesInterned) {
			checkInterning(namespace);
		} else if (namespace == null) {
			throw new IllegalArgumentException("namespace must be not null" + getLocation());
		}

		if (namespaceEnd >= namespacePrefix.length) {
			ensureNamespacesCapacity();
		}
		namespacePrefix[namespaceEnd] = prefix;
		namespaceUri[namespaceEnd] = namespace;
		++namespaceEnd;
		setPrefixCalled = true;
	}

	/**
	 * Lookup or declare prefix.
	 *
	 * @param namespace the namespace
	 * @return the string
	 */
	protected String lookupOrDeclarePrefix(String namespace) {
		return getPrefix(namespace, true);
	}

	/**
	 * Gets the prefix.
	 *
	 * @param namespace the namespace
	 * @param generatePrefix the generate prefix
	 * @return prefix
	 */
	public String getPrefix(String namespace, boolean generatePrefix) {
		return getPrefix(namespace, generatePrefix, false);
	}

	/**
	 * Gets the prefix.
	 *
	 * @param namespace the namespace
	 * @param generatePrefix the generate prefix
	 * @param nonEmpty the non empty
	 * @return prefix
	 */
	protected String getPrefix(String namespace, boolean generatePrefix, boolean nonEmpty) {
		// assert namespace != null;
		if (!namesInterned) {
			// when String is interned we can do much faster namespace stack
			// lookups ...
			namespace = namespace.intern();
		} else if (checkNamesInterned) {
			checkInterning(namespace);
			// assert namespace != namespace.intern();
		}
		if (namespace == null) {
			throw new IllegalArgumentException("namespace must be not null" + getLocation());
		} else if (namespace.length() == 0) {
			throw new IllegalArgumentException("default namespace cannot have prefix" + getLocation());
		}

		// first check if namespace is already in scope
		for (int i = namespaceEnd - 1; i >= 0; --i) {
			if (namespace == namespaceUri[i]) {
				final String prefix = namespacePrefix[i];
				if (nonEmpty && prefix.length() == 0)
					continue;
				// now check that prefix is still in scope
				for (int p = namespaceEnd - 1; p > i; --p) {
					if (prefix == namespacePrefix[p])
						continue; // too bad - prefix is redeclared with
									// different namespace
				}
				return prefix;
			}
		}

		// so not found it ...
		if (!generatePrefix) {
			return null;
		}
		return generatePrefix(namespace);
	}

	/**
	 * Generate prefix.
	 *
	 * @param namespace the namespace
	 * @return the string
	 */
	private String generatePrefix(String namespace) {
		// assert namespace == namespace.intern();
		while (true) {
			++autoDeclaredPrefixes;
			// fast lookup uses table that was pre-initialized in static{} ....
			final String prefix = autoDeclaredPrefixes < precomputedPrefixes.length ? precomputedPrefixes[autoDeclaredPrefixes] : ("n" + autoDeclaredPrefixes).intern();
			// make sure this prefix is not declared in any scope (avoid hiding
			// in-scope prefixes)!
			for (int i = namespaceEnd - 1; i >= 0; --i) {
				if (prefix == namespacePrefix[i]) {
					continue; // prefix is already declared - generate new and
								// try again
				}
			}
			// declare prefix

			if (namespaceEnd >= namespacePrefix.length) {
				ensureNamespacesCapacity();
			}
			namespacePrefix[namespaceEnd] = prefix;
			namespaceUri[namespaceEnd] = namespace;
			++namespaceEnd;

			return prefix;
		}
	}

	/**
	 * Gets the depth.
	 *
	 * @return depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Gets the namespace.
	 *
	 * @return namespace
	 */
	public String getNamespace() {
		return elNamespace[depth];
	}

	/**
	 * Gets the name.
	 *
	 * @return name
	 */
	public String getName() {
		return elName[depth];
	}

	/**
	 * Start tag.
	 *
	 * @param namespace the namespace
	 * @param name the name
	 * @return serializer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public XMLSerializer startTag(String namespace, String name) throws IOException {
		if (startTagIncomplete) {
			closeStartTag();
		}
		seenBracket = seenBracketBracket = false;
		++depth;
		if (doIndent && depth > 0 && seenTag) {
			writeIndent();
		}
		seenTag = true;
		setPrefixCalled = false;
		startTagIncomplete = true;
		if ((depth + 1) >= elName.length) {
			ensureElementsCapacity();
		}
		//// assert namespace != null;

		if (checkNamesInterned && namesInterned)
			checkInterning(namespace);
		elNamespace[depth] = (namesInterned || namespace == null) ? namespace : namespace.intern();
		// assert name != null;
		// elName[ depth ] = name;
		if (checkNamesInterned && namesInterned)
			checkInterning(name);
		elName[depth] = (namesInterned || name == null) ? name : name.intern();
		if (out == null) {
			throw new IllegalStateException("setOutput() must called set before serialization can start");
		}
		out.write('<');
		if (namespace != null) {
			if (namespace.length() > 0) {
				// ALEK: in future make this algo a feature on serializer
				String prefix = null;
				if (depth > 0 && (namespaceEnd - elNamespaceCount[depth - 1]) == 1) {
					// if only one prefix was declared un-declare it if the
					// prefix is already declared on parent el with the same URI
					String uri = namespaceUri[namespaceEnd - 1];
					if (uri == namespace || uri.equals(namespace)) {
						String elPfx = namespacePrefix[namespaceEnd - 1];
						// 2 == to skip predefined namesapces (xml and xmlns
						// ...)
						for (int pos = elNamespaceCount[depth - 1] - 1; pos >= 2; --pos) {
							String pf = namespacePrefix[pos];
							if (pf == elPfx || pf.equals(elPfx)) {
								String n = namespaceUri[pos];
								if (n == uri || n.equals(uri)) {
									--namespaceEnd; // un-declare namespace:
													// this is kludge!
									prefix = elPfx;
								}
								break;
							}
						}
					}
				}
				if (prefix == null) {
					prefix = lookupOrDeclarePrefix(namespace);
				}
				// assert prefix != null;
				// make sure that default ("") namespace to not print ":"
				if (prefix.length() > 0) {
					elPrefix[depth] = prefix;
					out.write(prefix);
					out.write(':');
				} else {
					elPrefix[depth] = "";
				}
			} else {
				// make sure that default namespace can be declared
				for (int i = namespaceEnd - 1; i >= 0; --i) {
					if ("".equals(namespacePrefix[i])) {
						final String uri = namespaceUri[i];
						if (uri == null) {
							// declare default namespace
							setPrefix("", "");
						} else if (uri.length() > 0) {
							throw new IllegalStateException("start tag can not be written in empty default namespace " + "as default namespace is currently bound to '" + uri + "'" + getLocation());
						}
						break;
					}
				}
				elPrefix[depth] = "";
			}
		} else {
			elPrefix[depth] = "";
		}
		out.write(name);
		return this;
	}

	/**
	 * Attribute.
	 *
	 * @param namespace the namespace
	 * @param name the name
	 * @param value the value
	 * @return serializer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public XMLSerializer attribute(String namespace, String name, String value) throws IOException {
		if (!startTagIncomplete) {
			throw new IllegalArgumentException("startTag() must be called before attribute()" + getLocation());
		}
		// assert setPrefixCalled == false;
		out.write(' ');
		//// assert namespace != null;
		if (namespace != null && namespace.length() > 0) {
			// namespace = namespace.intern();
			if (!namesInterned) {
				namespace = namespace.intern();
			} else if (checkNamesInterned) {
				checkInterning(namespace);
			}
			String prefix = getPrefix(namespace, false, true);
			// assert( prefix != null);
			// if(prefix.length() == 0) {
			if (prefix == null) {
				// needs to declare prefix to hold default namespace
				// NOTE: attributes such as a='b' are in NO namespace
				prefix = generatePrefix(namespace);
			}
			out.write(prefix);
			out.write(':');
			// if(prefix.length() > 0) {
			// out.write(prefix);
			// out.write(':');
			// }
		}
		// assert name != null;
		out.write(name);
		out.write('=');
		// assert value != null;
		out.write(attributeUseApostrophe ? '\'' : '"');
		writeAttributeValue(value, out);
		out.write(attributeUseApostrophe ? '\'' : '"');
		return this;
	}

	/**
	 * Close start tag.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void closeStartTag() throws IOException {
		if (finished) {
			throw new IllegalArgumentException("trying to write past already finished output" + getLocation());
		}
		if (seenBracket) {
			seenBracket = seenBracketBracket = false;
		}
		if (startTagIncomplete || setPrefixCalled) {
			if (setPrefixCalled) {
				throw new IllegalArgumentException("startTag() must be called immediately after setPrefix()" + getLocation());
			}
			if (!startTagIncomplete) {
				throw new IllegalArgumentException("trying to close start tag that is not opened" + getLocation());
			}

			// write all namespace delcarations!
			writeNamespaceDeclarations();
			out.write('>');
			elNamespaceCount[depth] = namespaceEnd;
			startTagIncomplete = false;
		}
	}

	/**
	 * Write namespace declarations.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void writeNamespaceDeclarations() throws IOException {
		// int start = elNamespaceCount[ depth - 1 ];
		for (int i = elNamespaceCount[depth - 1]; i < namespaceEnd; i++) {
			if (doIndent && namespaceUri[i].length() > 40) {
				writeIndent();
				out.write(" ");
			}
			if ("".equals(namespacePrefix[i])) {
				out.write(" xmlns:");
				out.write(namespacePrefix[i]);
				out.write('=');
			} else {
				out.write(" xmlns=");
			}
			out.write(attributeUseApostrophe ? '\'' : '"');

			// NOTE: escaping of namespace value the same way as attributes!!!!
			writeAttributeValue(namespaceUri[i], out);

			out.write(attributeUseApostrophe ? '\'' : '"');
		}
	}

	/**
	 * End tag.
	 *
	 * @param namespace the namespace
	 * @param name the name
	 * @return serializer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public XMLSerializer endTag(String namespace, String name) throws IOException {
		// check that level is valid
		//// assert namespace != null;
		// if(namespace != null) {
		// namespace = namespace.intern();
		// }
		seenBracket = seenBracketBracket = false;
		if (namespace != null) {
			if (!namesInterned) {
				namespace = namespace.intern();
			} else if (checkNamesInterned) {
				checkInterning(namespace);
			}
		}

		if (namespace != elNamespace[depth]) {
			throw new IllegalArgumentException("expected namespace " + printable(elNamespace[depth]) + " and not " + printable(namespace) + getLocation());
		}
		if (name == null) {
			throw new IllegalArgumentException("end tag name can not be null" + getLocation());
		}
		if (checkNamesInterned && namesInterned) {
			checkInterning(name);
		}
		String startTagName = elName[depth];
		if ((!name.equals(startTagName))) {
			throw new IllegalArgumentException("expected element name " + printable(elName[depth]) + " and not " + printable(name) + getLocation());
		}
		if (startTagIncomplete) {
			writeNamespaceDeclarations();
			// out.write(" />"); //space is added to make it easier to work in
			// XHTML!!!
			out.write("/>");
			--depth;
		} else {
			// assert startTagIncomplete == false;
			if (doIndent && seenTag) {
				writeIndent();
			}
			out.write("</");
			String startTagPrefix = elPrefix[depth];
			if (startTagPrefix.length() > 0) {
				out.write(startTagPrefix);
				out.write(':');
			}

			// if(namespace != null && namespace.length() > 0) {
			// //TODO prefix should be alredy known from matching start tag ...
			// final String prefix = lookupOrDeclarePrefix( namespace );
			// //assert( prefix != null);
			// if(prefix.length() > 0) {
			// out.write(prefix);
			// out.write(':');
			// }
			// }
			out.write(name);
			out.write('>');
			--depth;
		}
		namespaceEnd = elNamespaceCount[depth];
		startTagIncomplete = false;
		seenTag = true;
		return this;
	}

	/**
	 * Text.
	 *
	 * @param text the text
	 * @return serializer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public XMLSerializer text(String text) throws IOException {
		// assert text != null;
		if (startTagIncomplete || setPrefixCalled)
			closeStartTag();
		if (doIndent && seenTag)
			seenTag = false;
		writeElementContent(text, out);
		return this;
	}

	/**
	 * Text.
	 *
	 * @param buf the buf
	 * @param start the start
	 * @param len the len
	 * @return serializer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public XMLSerializer text(char[] buf, int start, int len) throws IOException {
		if (startTagIncomplete || setPrefixCalled)
			closeStartTag();
		if (doIndent && seenTag)
			seenTag = false;
		writeElementContent(buf, start, len, out);
		return this;
	}

	/**
	 * Cdsect.
	 *
	 * @param text the text
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void cdsect(String text) throws IOException {
		if (startTagIncomplete || setPrefixCalled || seenBracket)
			closeStartTag();
		if (doIndent && seenTag)
			seenTag = false;
		out.write("<![CDATA[");
		out.write(text); // escape?
		out.write("]]>");
	}

	/**
	 * Entity ref.
	 *
	 * @param text the text
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void entityRef(String text) throws IOException {
		if (startTagIncomplete || setPrefixCalled || seenBracket)
			closeStartTag();
		if (doIndent && seenTag)
			seenTag = false;
		out.write('&');
		out.write(text); // escape?
		out.write(';');
	}

	/**
	 * Processing instruction.
	 *
	 * @param text the text
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void processingInstruction(String text) throws IOException {
		if (startTagIncomplete || setPrefixCalled || seenBracket)
			closeStartTag();
		if (doIndent && seenTag)
			seenTag = false;
		out.write("<?");
		out.write(text); // escape?
		out.write("?>");
	}

	/**
	 * Comment.
	 *
	 * @param text the text
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void comment(String text) throws IOException {
		if (startTagIncomplete || setPrefixCalled || seenBracket)
			closeStartTag();
		if (doIndent && seenTag)
			seenTag = false;
		out.write("<!--");
		out.write(text); // escape?
		out.write("-->");
	}

	/**
	 * Docdecl.
	 *
	 * @param text the text
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void docdecl(String text) throws IOException {
		if (startTagIncomplete || setPrefixCalled || seenBracket)
			closeStartTag();
		if (doIndent && seenTag)
			seenTag = false;
		out.write("<!DOCTYPE");
		out.write(text); // escape?
		out.write(">");
	}

	/**
	 * Ignorable whitespace.
	 *
	 * @param text the text
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void ignorableWhitespace(String text) throws IOException {
		if (startTagIncomplete || setPrefixCalled || seenBracket)
			closeStartTag();
		if (doIndent && seenTag)
			seenTag = false;
		if (text.length() == 0) {
			throw new IllegalArgumentException("empty string is not allowed for ignorable whitespace" + getLocation());
		}
		out.write(text); // no escape?
	}

	/**
	 * Flush.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void flush() throws IOException {
		if (!finished && startTagIncomplete)
			closeStartTag();
		out.flush();
	}

	// --- utility methods

	/**
	 * Write attribute value.
	 *
	 * @param value the value
	 * @param out the out
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void writeAttributeValue(String value, Writer out) throws IOException {
		// .[apostrophe and <, & escaped],
		final char quot = attributeUseApostrophe ? '\'' : '"';
		final String quotEntity = attributeUseApostrophe ? "&apos;" : "&quot;";

		int pos = 0;
		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);
			if (ch == '&') {
				if (i > pos)
					out.write(value.substring(pos, i));
				out.write("&amp;");
				pos = i + 1;
			}
			if (ch == '<') {
				if (i > pos)
					out.write(value.substring(pos, i));
				out.write("&lt;");
				pos = i + 1;
			} else if (ch == quot) {
				if (i > pos)
					out.write(value.substring(pos, i));
				out.write(quotEntity);
				pos = i + 1;
			} else if (ch < 32) {
				// in XML 1.0 only legal character are #x9 | #xA | #xD
				// and they must be escaped otherwise in attribute value they
				// are normalized to spaces
				if (ch == 13 || ch == 10 || ch == 9) {
					if (i > pos)
						out.write(value.substring(pos, i));
					out.write("&#");
					out.write(Integer.toString(ch));
					out.write(';');
					pos = i + 1;
				} else {
					if (TRACE_ESCAPING)
						System.err.println(getClass().getName() + " DEBUG ATTR value.len=" + value.length() + " " + printable(value));

					throw new IllegalStateException(
							// "character "+Integer.toString(ch)+" is not
							// allowed in output"+getLocation());
							"character " + printable(ch) + " (" + Integer.toString(ch) + ") is not allowed in output" + getLocation() + " (attr value=" + printable(value) + ")");
					// in XML 1.1 legal are [#x1-#xD7FF]
					// if(ch > 0) {
					// if(i > pos) out.write(text.substring(pos, i));
					// out.write("&#");
					// out.write(Integer.toString(ch));
					// out.write(';');
					// pos = i + 1;
					// } else {
					// throw new IllegalStateException(
					// "character zero is not allowed in XML 1.1
					// output"+getLocation());
					// }
				}
			}
		}
		if (pos > 0) {
			out.write(value.substring(pos));
		} else {
			out.write(value); // this is shortcut to the most common case
		}

	}

	/**
	 * Write element content.
	 *
	 * @param text the text
	 * @param out the out
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void writeElementContent(String text, Writer out) throws IOException {
		// esccape '<', '&', ']]>', <32 if necessary
		int pos = 0;
		for (int i = 0; i < text.length(); i++) {
			// TODO: check if doing char[] text.getChars() would be faster than
			// getCharAt(i) ...
			char ch = text.charAt(i);
			if (ch == ']') {
				if (seenBracket) {
					seenBracketBracket = true;
				} else {
					seenBracket = true;
				}
			} else {
				if (ch == '&') {
					if (i > pos)
						out.write(text.substring(pos, i));
					out.write("&amp;");
					pos = i + 1;
				} else if (ch == '<') {
					if (i > pos)
						out.write(text.substring(pos, i));
					out.write("&lt;");
					pos = i + 1;
				} else if (seenBracketBracket && ch == '>') {
					if (i > pos)
						out.write(text.substring(pos, i));
					out.write("&gt;");
					pos = i + 1;
				} else if (ch < 32) {
					// in XML 1.0 only legal character are #x9 | #xA | #xD
					if (ch == 9 || ch == 10 || ch == 13) {
						// pass through

						// } else if(ch == 13) { //escape
						// if(i > pos) out.write(text.substring(pos, i));
						// out.write("&#");
						// out.write(Integer.toString(ch));
						// out.write(';');
						// pos = i + 1;
					} else {
						if (TRACE_ESCAPING)
							System.err.println(getClass().getName() + " DEBUG TEXT value.len=" + text.length() + " " + printable(text));
						throw new IllegalStateException("character " + Integer.toString(ch) + " is not allowed in output" + getLocation() + " (text value=" + printable(text) + ")");
						// in XML 1.1 legal are [#x1-#xD7FF]
						// if(ch > 0) {
						// if(i > pos) out.write(text.substring(pos, i));
						// out.write("&#");
						// out.write(Integer.toString(ch));
						// out.write(';');
						// pos = i + 1;
						// } else {
						// throw new IllegalStateException(
						// "character zero is not allowed in XML 1.1
						// output"+getLocation());
						// }
					}
				}
				if (seenBracket) {
					seenBracketBracket = seenBracket = false;
				}

			}
		}
		if (pos > 0) {
			out.write(text.substring(pos));
		} else {
			out.write(text); // this is shortcut to the most common case
		}

	}

	/**
	 * Write element content.
	 *
	 * @param buf the buf
	 * @param off the off
	 * @param len the len
	 * @param out the out
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void writeElementContent(char[] buf, int off, int len, Writer out) throws IOException {
		// esccape '<', '&', ']]>'
		final int end = off + len;
		int pos = off;
		for (int i = off; i < end; i++) {
			final char ch = buf[i];
			if (ch == ']') {
				if (seenBracket) {
					seenBracketBracket = true;
				} else {
					seenBracket = true;
				}
			} else {
				if (ch == '&') {
					if (i > pos) {
						out.write(buf, pos, i - pos);
					}
					out.write("&amp;");
					pos = i + 1;
				} else if (ch == '<') {
					if (i > pos) {
						out.write(buf, pos, i - pos);
					}
					out.write("&lt;");
					pos = i + 1;

				} else if (seenBracketBracket && ch == '>') {
					if (i > pos) {
						out.write(buf, pos, i - pos);
					}
					out.write("&gt;");
					pos = i + 1;
				} else if (ch < 32) {
					// in XML 1.0 only legal character are #x9 | #xA | #xD
					if (ch == 9 || ch == 10 || ch == 13) {
						// pass through

						// } else if(ch == 13 ) { //if(ch == '\r') {
						// if(i > pos) {
						// out.write(buf, pos, i - pos);
						// }
						// out.write("&#");
						// out.write(Integer.toString(ch));
						// out.write(';');
						// pos = i + 1;
					} else {
						if (TRACE_ESCAPING)
							System.err.println(getClass().getName() + " DEBUG TEXT value.len=" + len + " " + printable(new String(buf, off, len)));
						throw new IllegalStateException("character " + printable(ch) + " (" + Integer.toString(ch) + ") is not allowed in output" + getLocation());
						// in XML 1.1 legal are [#x1-#xD7FF]
						// if(ch > 0) {
						// if(i > pos) out.write(text.substring(pos, i));
						// out.write("&#");
						// out.write(Integer.toString(ch));
						// out.write(';');
						// pos = i + 1;
						// } else {
						// throw new IllegalStateException(
						// "character zero is not allowed in XML 1.1
						// output"+getLocation());
						// }
					}
				}
				if (seenBracket) {
					seenBracketBracket = seenBracket = false;
				}
				// assert seenBracketBracket == seenBracket == false;
			}
		}
		if (end > pos) {
			out.write(buf, pos, end - pos);
		}
	}

	/**
	 *  simple utility method -- good for debugging.
	 *
	 * @param s the s
	 * @return the string
	 */
	protected static final String printable(String s) {
		if (s == null)
			return "null";
		StringBuffer retval = new StringBuffer(s.length() + 16);
		retval.append("'");
		@SuppressWarnings("unused")
		char ch;
		for (int i = 0; i < s.length(); i++) {
			addPrintable(retval, s.charAt(i));
		}
		retval.append("'");
		return retval.toString();
	}

	/**
	 * Printable.
	 *
	 * @param ch the ch
	 * @return the string
	 */
	protected static final String printable(char ch) {
		StringBuffer retval = new StringBuffer();
		addPrintable(retval, ch);
		return retval.toString();
	}

	/**
	 * Adds the printable.
	 *
	 * @param retval the retval
	 * @param ch the ch
	 */
	private static void addPrintable(StringBuffer retval, char ch) {
		switch (ch) {
		case '\b':
			retval.append("\\b");
			break;
		case '\t':
			retval.append("\\t");
			break;
		case '\n':
			retval.append("\\n");
			break;
		case '\f':
			retval.append("\\f");
			break;
		case '\r':
			retval.append("\\r");
			break;
		case '\"':
			retval.append("\\\"");
			break;
		case '\'':
			retval.append("\\\'");
			break;
		case '\\':
			retval.append("\\\\");
			break;
		default:
			if (ch < 0x20 || ch > 0x7e) {
				final String ss = "0000" + Integer.toString(ch, 16);
				retval.append("\\u" + ss.substring(ss.length() - 4, ss.length()));
			} else {
				retval.append(ch);
			}
		}
	}

	/** The name stack. */
	protected Stack<String> nameStack = new Stack<>();

	/**
	 * Write start element.
	 *
	 * @param tag the tag
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeStartElement(String tag) throws IOException {
		nameStack.push(tag);

		this.startTag(null, tag);
	}

	/**
	 * Write attribute.
	 *
	 * @param attributeName the attribute name
	 * @param value the value
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeAttribute(String attributeName, String value) throws IOException {
		this.attribute(null, attributeName, value);

	}

	/**
	 * Write C data.
	 *
	 * @param value the value
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeCData(String value) throws IOException {
		this.cdsect(value);

	}

	/**
	 * Write end element.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeEndElement() throws IOException {
		this.endTag(null, nameStack.pop());

	}

	/**
	 * Write characters.
	 *
	 * @param value the value
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeCharacters(String value) throws IOException {
		this.text(value);

	}

	/**
	 * Write boolean.
	 *
	 * @param valueBool the value bool
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeBoolean(boolean valueBool) throws IOException {
		this.text(Boolean.toString(valueBool));
	}

	/**
	 * Write int.
	 *
	 * @param valueInt the value int
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeInt(int valueInt) throws IOException {
		this.text(Integer.toString(valueInt));
	}

	/**
	 * Write float.
	 *
	 * @param valueFloatType the value float type
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeFloat(float valueFloatType) throws IOException {
		this.text(Float.toString(valueFloatType));
	}

	/**
	 * Write long.
	 *
	 * @param valueLong the value long
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeLong(long valueLong) throws IOException {
		this.text(Long.toString(valueLong));
	}

	/**
	 * Write double.
	 *
	 * @param valueDouble the value double
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeDouble(double valueDouble) throws IOException {
		this.text(Double.toString(valueDouble));
	}

	/**
	 * Write empty element.
	 *
	 * @param tag the tag
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeEmptyElement(String tag) throws IOException {
		startTag(null, tag);
		endTag(null, tag);
	}

	/**
	 * Write binary.
	 *
	 * @param value the value
	 * @param start the start
	 * @param length the length
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeBinary(byte[] value, int start, int length) throws IOException {
		text(Base64Utils.encode(value, start, length));
	}

	/**
	 * Write binary.
	 *
	 * @param value the value
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeBinary(byte[] value) throws IOException {
		text(Base64Utils.encode(value, 0, value.length));
	}

	/**
	 * Write decimal attribute.
	 *
	 * @param prefix the prefix
	 * @param namespaceURI the namespace URI
	 * @param localName the local name
	 * @param value the value
	 * @throws Exception the exception
	 */
	public void writeDecimalAttribute(String prefix, String namespaceURI, String localName, BigDecimal value) throws Exception {
		this.attribute(namespaceURI, localName, value.toPlainString());
	}

	/**
	 * Write integer attribute.
	 *
	 * @param prefix the prefix
	 * @param namespaceURI the namespace URI
	 * @param localName the local name
	 * @param value the value
	 * @throws Exception the exception
	 */
	public void writeIntegerAttribute(String prefix, String namespaceURI, String localName, BigInteger value) throws Exception {
		this.attribute(namespaceURI, localName, value.toString());

	}

	/**
	 * Close.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void close() throws IOException {
		out.close();

	}

	/**
	 * Write double attribute.
	 *
	 * @param prefix the prefix
	 * @param namespaceURI the namespace URI
	 * @param localName the local name
	 * @param value the value
	 * @throws Exception the exception
	 */
	public void writeDoubleAttribute(String prefix, String namespaceURI, String localName, double value) throws Exception {
		this.attribute(namespaceURI, localName, Double.toString(value));

	}

	/**
	 * Write attribute.
	 *
	 * @param namespaceURI the namespace URI
	 * @param localName the local name
	 * @param value the value
	 * @throws Exception the exception
	 */
	public void writeAttribute(String namespaceURI, String localName, String value) throws Exception {
		this.attribute(namespaceURI, localName, value.toString());
	}

	/**
	 * Write boolean attribute.
	 *
	 * @param prefix the prefix
	 * @param namespaceURI the namespace URI
	 * @param localName the local name
	 * @param value the value
	 * @throws Exception the exception
	 */
	public void writeBooleanAttribute(String prefix, String namespaceURI, String localName, boolean value) throws Exception {
		this.attribute(namespaceURI, localName, Boolean.toString(value));

	}

	/**
	 * Write empty element.
	 *
	 * @param namespaceURI the namespace URI
	 * @param localName the local name
	 * @throws Exception the exception
	 */
	public void writeEmptyElement(String namespaceURI, String localName) throws Exception {
		startTag(namespaceURI, localName);
		endTag(namespaceURI, localName);

	}

	/**
	 * Write comment.
	 *
	 * @param data the data
	 * @throws Exception the exception
	 */
	public void writeComment(String data) throws Exception {
		this.comment(data);

	}

	/**
	 * Write decimal.
	 *
	 * @param value the value
	 * @throws Exception the exception
	 */
	public void writeDecimal(BigDecimal value) throws Exception {
		text(value.toPlainString());
	}

	/**
	 * Write end document.
	 *
	 * @throws Exception the exception
	 */
	public void writeEndDocument() throws Exception {
		endDocument();

	}

	/**
	 * Write start document.
	 *
	 * @throws Exception the exception
	 */
	public void writeStartDocument() throws Exception {
		startDocument(JsonEncoding.UTF8.getJavaName(), true);

	}

}
