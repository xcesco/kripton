/** Copyright (c) 2002,2003, Stefan Haustein, Oberhausen, Rhld., Germany
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The  above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE. */

// Contributors: Paul Hackenberger (unterminated entity handling in relaxed mode)

package com.abubusoft.kripton.xml;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.xml.internal.StringPool;

import static com.abubusoft.kripton.xml.EventType.START_DOCUMENT;
import static com.abubusoft.kripton.xml.EventType.END_DOCUMENT;
import static com.abubusoft.kripton.xml.EventType.START_TAG;
import static com.abubusoft.kripton.xml.EventType.END_TAG;
import static com.abubusoft.kripton.xml.EventType.TEXT;
import static com.abubusoft.kripton.xml.EventType.CDSECT;
import static com.abubusoft.kripton.xml.EventType.ENTITY_REF;
import static com.abubusoft.kripton.xml.EventType.IGNORABLE_WHITESPACE;
import static com.abubusoft.kripton.xml.EventType.PROCESSING_INSTRUCTION;
import static com.abubusoft.kripton.xml.EventType.COMMENT;
import static com.abubusoft.kripton.xml.EventType.DOCDECL;

import static com.abubusoft.kripton.xml.EventType.ELEMENTDECL;
import static com.abubusoft.kripton.xml.EventType.ENTITYDECL;
import static com.abubusoft.kripton.xml.EventType.ATTLISTDECL;
import static com.abubusoft.kripton.xml.EventType.NOTATIONDECL;
import static com.abubusoft.kripton.xml.EventType.PARAMETER_ENTITY_REF;

import static com.abubusoft.kripton.xml.EventType.XML_DECLARATION;


/**
 * An XML pull parser with limited support for parsing internal DTDs.
 */
public class XMLParser implements XmlPullParser, Closeable {

	/** The Constant PROPERTY_XMLDECL_VERSION. */
	private static final String PROPERTY_XMLDECL_VERSION = "http://xmlpull.org/v1/doc/properties.html#xmldecl-version";
	
	/** The Constant PROPERTY_XMLDECL_STANDALONE. */
	private static final String PROPERTY_XMLDECL_STANDALONE = "http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone";
	
	/** The Constant PROPERTY_LOCATION. */
	private static final String PROPERTY_LOCATION = "http://xmlpull.org/v1/doc/properties.html#location";
	
	/** The Constant FEATURE_RELAXED. */
	private static final String FEATURE_RELAXED = "http://xmlpull.org/v1/doc/features.html#relaxed";

	/** The Constant DEFAULT_ENTITIES. */
	private static final Map<String, String> DEFAULT_ENTITIES = new HashMap<String, String>();
	static {
		DEFAULT_ENTITIES.put("lt", "<");
		DEFAULT_ENTITIES.put("gt", ">");
		DEFAULT_ENTITIES.put("amp", "&");
		DEFAULT_ENTITIES.put("apos", "'");
		DEFAULT_ENTITIES.put("quot", "\"");
	}


	
	/** The Constant START_COMMENT. */
	private static final char[] START_COMMENT = { '<', '!', '-', '-' };
	
	/** The Constant END_COMMENT. */
	private static final char[] END_COMMENT = { '-', '-', '>' };
	
	/** The Constant COMMENT_DOUBLE_DASH. */
	private static final char[] COMMENT_DOUBLE_DASH = { '-', '-' };
	
	/** The Constant START_CDATA. */
	private static final char[] START_CDATA = { '<', '!', '[', 'C', 'D', 'A', 'T', 'A', '[' };
	
	/** The Constant END_CDATA. */
	private static final char[] END_CDATA = { ']', ']', '>' };
	
	/** The Constant START_PROCESSING_INSTRUCTION. */
	private static final char[] START_PROCESSING_INSTRUCTION = { '<', '?' };
	
	/** The Constant END_PROCESSING_INSTRUCTION. */
	private static final char[] END_PROCESSING_INSTRUCTION = { '?', '>' };
	
	/** The Constant START_DOCTYPE. */
	private static final char[] START_DOCTYPE = { '<', '!', 'D', 'O', 'C', 'T', 'Y', 'P', 'E' };
	
	/** The Constant SYSTEM. */
	private static final char[] SYSTEM = { 'S', 'Y', 'S', 'T', 'E', 'M' };
	
	/** The Constant PUBLIC. */
	private static final char[] PUBLIC = { 'P', 'U', 'B', 'L', 'I', 'C' };
	
	/** The Constant START_ELEMENT. */
	private static final char[] START_ELEMENT = { '<', '!', 'E', 'L', 'E', 'M', 'E', 'N', 'T' };
	
	/** The Constant START_ATTLIST. */
	private static final char[] START_ATTLIST = { '<', '!', 'A', 'T', 'T', 'L', 'I', 'S', 'T' };
	
	/** The Constant START_ENTITY. */
	private static final char[] START_ENTITY = { '<', '!', 'E', 'N', 'T', 'I', 'T', 'Y' };
	
	/** The Constant START_NOTATION. */
	private static final char[] START_NOTATION = { '<', '!', 'N', 'O', 'T', 'A', 'T', 'I', 'O', 'N' };
	
	/** The Constant EMPTY. */
	private static final char[] EMPTY = new char[] { 'E', 'M', 'P', 'T', 'Y' };
	
	/** The Constant ANY. */
	private static final char[] ANY = new char[] { 'A', 'N', 'Y' };
	
	/** The Constant NDATA. */
	private static final char[] NDATA = new char[] { 'N', 'D', 'A', 'T', 'A' };
	
	/** The Constant NOTATION. */
	private static final char[] NOTATION = new char[] { 'N', 'O', 'T', 'A', 'T', 'I', 'O', 'N' };
	
	/** The Constant REQUIRED. */
	private static final char[] REQUIRED = new char[] { 'R', 'E', 'Q', 'U', 'I', 'R', 'E', 'D' };
	
	/** The Constant IMPLIED. */
	private static final char[] IMPLIED = new char[] { 'I', 'M', 'P', 'L', 'I', 'E', 'D' };
	
	/** The Constant FIXED. */
	private static final char[] FIXED = new char[] { 'F', 'I', 'X', 'E', 'D' };

	/** The Constant UNEXPECTED_EOF. */
	static final private String UNEXPECTED_EOF = "Unexpected EOF";
	
	/** The Constant ILLEGAL_TYPE. */
	static final private String ILLEGAL_TYPE = "Wrong event type";

	/** The location. */
	// general
	private String location;

	/** The version. */
	private String version;
	
	/** The standalone. */
	private Boolean standalone;
	
	/** The root element name. */
	private String rootElementName;
	
	/** The system id. */
	private String systemId;
	
	/** The public id. */
	private String publicId;

	/**
	 * True if the {@code <!DOCTYPE>} contents are handled. The DTD defines
	 * entity values and default attribute values. These values are parsed at
	 * inclusion time and may contain both tags and entity references.
	 *
	 * <p>
	 * If this is false, the user must {@link #defineEntityReplacementText
	 * define entity values manually}. Such entity values are literal strings
	 * and will not be parsed. There is no API to define default attributes
	 * manually.
	 */
	private boolean processDocDecl;
	
	/** The process nsp. */
	private boolean processNsp;
	
	/** The relaxed. */
	private boolean relaxed;
	
	/** The keep namespace attributes. */
	private boolean keepNamespaceAttributes;

	/**
	 * If non-null, the contents of the read buffer must be copied into this
	 * string builder before the read buffer is overwritten. This is used to
	 * capture the raw DTD text while parsing the DTD.
	 */
	private StringBuilder bufferCapture;

	/**
	 * Entities defined in or for this document. This map is created lazily.
	 */
	private Map<String, char[]> documentEntities;

	/**
	 * Default attributes in this document. The outer map's key is the element
	 * name; the inner map's key is the attribute name. Both keys should be
	 * without namespace adjustments. This map is created lazily.
	 */
	private Map<String, Map<String, String>> defaultAttributes;

	/** The depth. */
	private int depth;
	
	/** The element stack. */
	private String[] elementStack = new String[16];
	
	/** The nsp stack. */
	private String[] nspStack = new String[8];
	
	/** The nsp counts. */
	private int[] nspCounts = new int[4];

	// source

	/** The reader. */
	private Reader reader;
	
	/** The encoding. */
	private String encoding;
	
	/** The next content source. */
	private ContentSource nextContentSource;
	
	/** The buffer. */
	private char[] buffer = new char[8192];
	
	/** The position. */
	private int position = 0;
	
	/** The limit. */
	private int limit = 0;

	/** The buffer start line. */
	/*
	 * Track the number of newlines and columns preceding the current buffer. To
	 * compute the line and column of a position in the buffer, compute the line
	 * and column in the buffer and add the preceding values.
	 */
	private int bufferStartLine;
	
	/** The buffer start column. */
	private int bufferStartColumn;

	// the current token

	/** The type. */
	private EventType type;
	
	/** The is whitespace. */
	private boolean isWhitespace;
	
	/** The namespace. */
	private String namespace;
	
	/** The prefix. */
	private String prefix;
	
	/** The name. */
	private String name;
	
	/** The text. */
	private String text;

	/** The degenerated. */
	private boolean degenerated;
	
	/** The attribute count. */
	private int attributeCount;

	/** The attributes. */
	/*
	 * The current element's attributes arranged in groups of 4: i + 0 =
	 * attribute namespace URI i + 1 = attribute namespace prefix i + 2 =
	 * attribute qualified name (may contain ":", as in "html:h1") i + 3 =
	 * attribute value
	 */
	private String[] attributes = new String[16];

	/** The error. */
	private String error;

	/** The unresolved. */
	private boolean unresolved;

	/** The string pool. */
	public final StringPool stringPool = new StringPool();

	/**
	 * Retains namespace attributes like {@code xmlns="http://foo"} or
	 * {@code xmlns:foo="http:foo"} in pulled elements. Most applications will
	 * only be interested in the effective namespaces of their elements, so
	 * these attributes aren't useful. But for structure preserving wrappers
	 * like DOM, it is necessary to keep the namespace data around.
	 */
	public void keepNamespaceAttributes() {
		this.keepNamespaceAttributes = true;
	}

	/**
	 * Adjust nsp.
	 *
	 * @return true, if successful
	 */
	private boolean adjustNsp() {
		boolean any = false;

		for (int i = 0; i < attributeCount << 2; i += 4) {
			String attrName = attributes[i + 2];
			int cut = attrName.indexOf(':');
			String prefix;

			if (cut != -1) {
				prefix = attrName.substring(0, cut);
				attrName = attrName.substring(cut + 1);
			} else if (attrName.equals("xmlns")) {
				prefix = attrName;
				attrName = null;
			} else {
				continue;
			}

			if (!prefix.equals("xmlns")) {
				any = true;
			} else {
				int j = (nspCounts[depth]++) << 1;

				nspStack = ensureCapacity(nspStack, j + 2);
				nspStack[j] = attrName;
				nspStack[j + 1] = attributes[i + 3];

				if (attrName != null && attributes[i + 3].isEmpty()) {
					checkRelaxed("illegal empty namespace");
				}

				if (keepNamespaceAttributes) {
					// explicitly set the namespace for unprefixed attributes
					// such as xmlns="http://foo"
					attributes[i] = "http://www.w3.org/2000/xmlns/";
					any = true;
				} else {
					System.arraycopy(attributes, i + 4, attributes, i, ((--attributeCount) << 2) - i);

					i -= 4;
				}
			}
		}

		if (any) {
			for (int i = (attributeCount << 2) - 4; i >= 0; i -= 4) {

				String attrName = attributes[i + 2];
				int cut = attrName.indexOf(':');

				if (cut == 0 && !relaxed) {
					throw new KriptonRuntimeException("illegal attribute name: " + attrName + " at " + this);
				} else if (cut != -1) {
					String attrPrefix = attrName.substring(0, cut);

					attrName = attrName.substring(cut + 1);

					String attrNs = getNamespace(attrPrefix);

					if (attrNs == null && !relaxed) {
						throw new KriptonRuntimeException("Undefined Prefix: " + attrPrefix + " in " + this);
					}

					attributes[i] = attrNs;
					attributes[i + 1] = attrPrefix;
					attributes[i + 2] = attrName;
				}
			}
		}

		int cut = name.indexOf(':');

		if (cut == 0) {
			checkRelaxed("illegal tag name: " + name);
		}

		if (cut != -1) {
			prefix = name.substring(0, cut);
			name = name.substring(cut + 1);
		}

		this.namespace = getNamespace(prefix);

		if (this.namespace == null) {
			if (prefix != null) {
				checkRelaxed("undefined prefix: " + prefix);
			}
			this.namespace = NO_NAMESPACE;
		}

		return any;
	}

	/**
	 * Ensure capacity.
	 *
	 * @param arr the arr
	 * @param required the required
	 * @return the string[]
	 */
	private String[] ensureCapacity(String[] arr, int required) {
		if (arr.length >= required) {
			return arr;
		}
		String[] bigger = new String[required + 16];
		System.arraycopy(arr, 0, bigger, 0, arr.length);
		return bigger;
	}

	/**
	 * Check relaxed.
	 *
	 * @param errorMessage the error message
	 */
	private void checkRelaxed(String errorMessage) {
		if (!relaxed) {
			throw new KriptonRuntimeException(errorMessage, true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}
		if (error == null) {
			error = "Error: " + errorMessage;
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#next()
	 */
	@Override
	public EventType next() throws KriptonRuntimeException, IOException {
		return next(false);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#nextToken()
	 */
	@Override
	public EventType nextToken() throws KriptonRuntimeException, IOException {
		return next(true);
	}

	/**
	 * Next.
	 *
	 * @param justOneToken the just one token
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private EventType next(boolean justOneToken) throws IOException, KriptonRuntimeException {
		if (reader == null) {
			throw new KriptonRuntimeException("setInput() must be called first.", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}

		if (type == END_TAG) {
			depth--;
		}

		// degenerated needs to be handled before error because of possible
		// processor expectations(!)

		if (degenerated) {
			degenerated = false;
			type = END_TAG;
			return type;
		}

		if (error != null) {
			if (justOneToken) {
				text = error;
				type = COMMENT;
				error = null;
				return type;
			} else {
				error = null;
			}
		}

		type = peekType(false);

		if (type == XML_DECLARATION) {
			readXmlDeclaration();
			type = peekType(false);
		}

		text = null;
		isWhitespace = true;
		prefix = null;
		name = null;
		namespace = null;
		attributeCount = -1;
		boolean throwOnResolveFailure = !justOneToken;

		while (true) {
			switch (type) {

			/*
			 * Return immediately after encountering a start tag, end tag, or
			 * the end of the document.
			 */
			case START_TAG:
				parseStartTag(false, throwOnResolveFailure);
				return type;
			case END_TAG:
				readEndTag();
				return type;
			case END_DOCUMENT:
				return type;

			/*
			 * Return after any text token when we're looking for a single
			 * token. Otherwise concatenate all text between tags.
			 */
			case ENTITY_REF:
				if (justOneToken) {
					StringBuilder entityTextBuilder = new StringBuilder();
					readEntity(entityTextBuilder, true, throwOnResolveFailure, ValueContext.TEXT);
					text = entityTextBuilder.toString();
					break;
				}
				// fall-through
			case TEXT:
				text = readValue('<', !justOneToken, throwOnResolveFailure, ValueContext.TEXT);
				if (depth == 0 && isWhitespace) {
					type = IGNORABLE_WHITESPACE;
				}
				break;
			case CDSECT:
				read(START_CDATA);
				text = readUntil(END_CDATA, true);
				break;

			/*
			 * Comments, processing instructions and declarations are returned
			 * when we're looking for a single token. Otherwise they're skipped.
			 */
			case COMMENT:
				String commentText = readComment(justOneToken);
				if (justOneToken) {
					text = commentText;
				}
				break;
			case PROCESSING_INSTRUCTION:
				read(START_PROCESSING_INSTRUCTION);
				String processingInstruction = readUntil(END_PROCESSING_INSTRUCTION, justOneToken);
				if (justOneToken) {
					text = processingInstruction;
				}
				break;
			case DOCDECL:
				readDoctype(justOneToken);
				break;

			default:
				throw new KriptonRuntimeException("Unexpected token", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
			}

			if (depth == 0 && (type == ENTITY_REF || type == TEXT || type == CDSECT)) {
				throw new KriptonRuntimeException("Unexpected token", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
			}

			if (justOneToken) {
				return type;
			}

			if (type == IGNORABLE_WHITESPACE) {
				text = null;
			}

			/*
			 * We've read all that we can of a non-empty text block. Always
			 * report this as text, even if it was a CDATA block or entity
			 * reference.
			 */
			EventType peek = peekType(false);
			if (text != null && !text.isEmpty() && peek.ordinal() < TEXT.ordinal()) {
				type = TEXT;
				return type;
			}

			type = peek;
		}
	}

	/**
	 * Reads text until the specified delimiter is encountered. Consumes the
	 * text and the delimiter.
	 *
	 * @param delimiter the delimiter
	 * @param returnText            true to return the read text excluding the delimiter; false to
	 *            return null.
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private String readUntil(char[] delimiter, boolean returnText) throws IOException, KriptonRuntimeException {
		int start = position;
		StringBuilder result = null;

		if (returnText && text != null) {
			result = new StringBuilder();
			result.append(text);
		}

		search: while (true) {
			if (position + delimiter.length >= limit) {
				if (start < position && returnText) {
					if (result == null) {
						result = new StringBuilder();
					}
					result.append(buffer, start, position - start);
				}
				if (!fillBuffer(delimiter.length)) {
					checkRelaxed(UNEXPECTED_EOF);
					type = COMMENT;
					return null;
				}
				start = position;
			}

			// TODO: replace with Arrays.equals(buffer, position, delimiter, 0,
			// delimiter.length)
			// when the VM has better method inlining
			for (int i = 0; i < delimiter.length; i++) {
				if (buffer[position + i] != delimiter[i]) {
					position++;
					continue search;
				}
			}

			break;
		}

		int end = position;
		position += delimiter.length;

		if (!returnText) {
			return null;
		} else if (result == null) {
			return stringPool.get(buffer, start, end - start);
		} else {
			result.append(buffer, start, end - start);
			return result.toString();
		}
	}

	/**
	 * Returns true if an XML declaration was read.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private void readXmlDeclaration() throws IOException, KriptonRuntimeException {
		if (bufferStartLine != 0 || bufferStartColumn != 0 || position != 0) {
			checkRelaxed("processing instructions must not start with xml");
		}

		read(START_PROCESSING_INSTRUCTION);
		parseStartTag(true, true);

		if (attributeCount < 1 || !"version".equals(attributes[2])) {
			checkRelaxed("version expected");
		}

		version = attributes[3];

		int pos = 1;

		if (pos < attributeCount && "encoding".equals(attributes[2 + 4])) {
			encoding = attributes[3 + 4];
			pos++;
		}

		if (pos < attributeCount && "standalone".equals(attributes[4 * pos + 2])) {
			String st = attributes[3 + 4 * pos];
			if ("yes".equals(st)) {
				standalone = Boolean.TRUE;
			} else if ("no".equals(st)) {
				standalone = Boolean.FALSE;
			} else {
				checkRelaxed("illegal standalone value: " + st);
			}
			pos++;
		}

		if (pos != attributeCount) {
			checkRelaxed("unexpected attributes in XML declaration");
		}

		isWhitespace = true;
		text = null;
	}

	/**
	 * Read comment.
	 *
	 * @param returnText the return text
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private String readComment(boolean returnText) throws IOException, KriptonRuntimeException {
		read(START_COMMENT);

		if (relaxed) {
			return readUntil(END_COMMENT, returnText);
		}

		String commentText = readUntil(COMMENT_DOUBLE_DASH, returnText);
		if (peekCharacter() != '>') {
			throw new KriptonRuntimeException("Comments may not contain --", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}
		position++;
		return commentText;
	}

	/**
	 * Read the document's DTD. Although this parser is non-validating, the DTD
	 * must be parsed to capture entity values and default attribute values.
	 *
	 * @param saveDtdText the save dtd text
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private void readDoctype(boolean saveDtdText) throws IOException, KriptonRuntimeException {
		read(START_DOCTYPE);

		int startPosition = -1;
		if (saveDtdText) {
			bufferCapture = new StringBuilder();
			startPosition = position;
		}
		try {
			skip();
			rootElementName = readName();
			readExternalId(true, true);
			skip();
			if (peekCharacter() == '[') {
				readInternalSubset();
			}
			skip();
		} finally {
			if (saveDtdText) {
				bufferCapture.append(buffer, 0, position);
				bufferCapture.delete(0, startPosition);
				text = bufferCapture.toString();
				bufferCapture = null;
			}
		}

		read('>');
	}

	/**
	 * Reads an external ID of one of these two forms: SYSTEM "quoted system
	 * name" PUBLIC "quoted public id" "quoted system name"
	 * 
	 * If the system name is not required, this also supports lone public IDs of
	 * this form: PUBLIC "quoted public id"
	 * 
	 * Returns true if any ID was read.
	 *
	 * @param requireSystemName the require system name
	 * @param assignFields the assign fields
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private boolean readExternalId(boolean requireSystemName, boolean assignFields) throws IOException, KriptonRuntimeException {
		skip();
		int c = peekCharacter();

		if (c == 'S') {
			read(SYSTEM);
		} else if (c == 'P') {
			read(PUBLIC);
			skip();
			if (assignFields) {
				publicId = readQuotedId(true);
			} else {
				readQuotedId(false);
			}
		} else {
			return false;
		}

		skip();

		if (!requireSystemName) {
			int delimiter = peekCharacter();
			if (delimiter != '"' && delimiter != '\'') {
				return true; // no system name!
			}
		}

		if (assignFields) {
			systemId = readQuotedId(true);
		} else {
			readQuotedId(false);
		}
		return true;
	}

	/** The Constant SINGLE_QUOTE. */
	private static final char[] SINGLE_QUOTE = new char[] { '\'' };
	
	/** The Constant DOUBLE_QUOTE. */
	private static final char[] DOUBLE_QUOTE = new char[] { '"' };

	/**
	 * Reads a quoted string, performing no entity escaping of the contents.
	 *
	 * @param returnText the return text
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private String readQuotedId(boolean returnText) throws IOException, KriptonRuntimeException {
		int quote = peekCharacter();
		char[] delimiter;
		if (quote == '"') {
			delimiter = DOUBLE_QUOTE;
		} else if (quote == '\'') {
			delimiter = SINGLE_QUOTE;
		} else {
			throw new KriptonRuntimeException("Expected a quoted string", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}
		position++;
		return readUntil(delimiter, returnText);
	}

	/**
	 * Read internal subset.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private void readInternalSubset() throws IOException, KriptonRuntimeException {
		read('[');

		while (true) {
			skip();
			if (peekCharacter() == ']') {
				position++;
				return;
			}

			EventType declarationType = peekType(true);
			switch (declarationType) {
			case ELEMENTDECL:
				readElementDeclaration();
				break;

			case ATTLISTDECL:
				readAttributeListDeclaration();
				break;

			case ENTITYDECL:
				readEntityDeclaration();
				break;

			case NOTATIONDECL:
				readNotationDeclaration();
				break;

			case PROCESSING_INSTRUCTION:
				read(START_PROCESSING_INSTRUCTION);
				readUntil(END_PROCESSING_INSTRUCTION, false);
				break;

			case COMMENT:
				readComment(false);
				break;

			case PARAMETER_ENTITY_REF:
				throw new KriptonRuntimeException("Parameter entity references are not supported", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);

			default:
				throw new KriptonRuntimeException("Unexpected token", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
			}
		}
	}

	/**
	 * Read an element declaration. This contains a name and a content spec.
	 * <!ELEMENT foo EMPTY > <!ELEMENT foo (bar?,(baz|quux)) > <!ELEMENT foo
	 * (#PCDATA|bar)* >
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private void readElementDeclaration() throws IOException, KriptonRuntimeException {
		read(START_ELEMENT);
		skip();
		readName();
		readContentSpec();
		skip();
		read('>');
	}

	/**
	 * Read an element content spec. This is a regular expression-like pattern
	 * of names or other content specs. The following operators are supported:
	 * sequence: (a,b,c) choice: (a|b|c) optional: a? one or more: a+ any
	 * number: a*
	 * 
	 * The special name '#PCDATA' is permitted but only if it is the first
	 * element of the first group: (#PCDATA|a|b)
	 * 
	 * The top-level element must be either a choice, a sequence, or one of the
	 * special names EMPTY and ANY.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private void readContentSpec() throws IOException, KriptonRuntimeException {
		// this implementation is very lenient; it scans for balanced parens
		// only
		skip();
		int c = peekCharacter();
		if (c == '(') {
			int depth = 0;
			do {
				if (c == '(') {
					depth++;
				} else if (c == ')') {
					depth--;
				}
				position++;
				c = peekCharacter();
			} while (depth > 0);

			if (c == '*' || c == '?' || c == '+') {
				position++;
			}
		} else if (c == EMPTY[0]) {
			read(EMPTY);
		} else if (c == ANY[0]) {
			read(ANY);
		} else {
			throw new KriptonRuntimeException("Expected element content spec", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}
	}

	/**
	 * Reads an attribute list declaration such as the following: <!ATTLIST foo
	 * bar CDATA #IMPLIED quux (a|b|c) "c" baz NOTATION (a|b|c) #FIXED "c">
	 * 
	 * Each attribute has a name, type and default.
	 * 
	 * Types are one of the built-in types (CDATA, ID, IDREF, IDREFS, ENTITY,
	 * ENTITIES, NMTOKEN, or NMTOKENS), an enumerated type "(list|of|options)"
	 * or NOTATION followed by an enumerated type.
	 * 
	 * The default is either #REQUIRED, #IMPLIED, #FIXED, a quoted value, or
	 * #FIXED with a quoted value.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private void readAttributeListDeclaration() throws IOException, KriptonRuntimeException {
		read(START_ATTLIST);
		skip();
		String elementName = readName();

		while (true) {
			skip();
			int c = peekCharacter();
			if (c == '>') {
				position++;
				return;
			}

			// attribute name
			String attributeName = readName();

			// attribute type
			skip();
			if (position + 1 >= limit && !fillBuffer(2)) {
				throw new KriptonRuntimeException("Malformed attribute list", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
			}
			if (buffer[position] == NOTATION[0] && buffer[position + 1] == NOTATION[1]) {
				read(NOTATION);
				skip();
			}
			c = peekCharacter();
			if (c == '(') {
				position++;
				while (true) {
					skip();
					readName();
					skip();
					c = peekCharacter();
					if (c == ')') {
						position++;
						break;
					} else if (c == '|') {
						position++;
					} else {
						throw new KriptonRuntimeException("Malformed attribute type", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
					}
				}
			} else {
				readName();
			}

			// default value
			skip();
			c = peekCharacter();
			if (c == '#') {
				position++;
				c = peekCharacter();
				if (c == 'R') {
					read(REQUIRED);
				} else if (c == 'I') {
					read(IMPLIED);
				} else if (c == 'F') {
					read(FIXED);
				} else {
					throw new KriptonRuntimeException("Malformed attribute type", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
				}
				skip();
				c = peekCharacter();
			}
			if (c == '"' || c == '\'') {
				position++;
				// TODO: does this do escaping correctly?
				String value = readValue((char) c, true, true, ValueContext.ATTRIBUTE);
				position++;
				defineAttributeDefault(elementName, attributeName, value);
			}
		}
	}

	/**
	 * Define attribute default.
	 *
	 * @param elementName the element name
	 * @param attributeName the attribute name
	 * @param value the value
	 */
	private void defineAttributeDefault(String elementName, String attributeName, String value) {
		if (defaultAttributes == null) {
			defaultAttributes = new HashMap<String, Map<String, String>>();
		}
		Map<String, String> elementAttributes = defaultAttributes.get(elementName);
		if (elementAttributes == null) {
			elementAttributes = new HashMap<String, String>();
			defaultAttributes.put(elementName, elementAttributes);
		}
		elementAttributes.put(attributeName, value);
	}

	/**
	 * Read an entity declaration. The value of internal entities are inline:
	 * <!ENTITY foo "bar">
	 * 
	 * The values of external entities must be retrieved by URL or path:
	 * <!ENTITY foo SYSTEM "http://host/file"> <!ENTITY foo PUBLIC
	 * "-//Android//Foo//EN" "http://host/file"> <!ENTITY foo SYSTEM
	 * "../file.png" NDATA png>
	 * 
	 * Entities may be general or parameterized. Parameterized entities are
	 * marked by a percent sign. Such entities may only be used in the DTD:
	 * <!ENTITY % foo "bar">
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private void readEntityDeclaration() throws IOException, KriptonRuntimeException {
		read(START_ENTITY);
		boolean generalEntity = true;

		skip();
		if (peekCharacter() == '%') {
			generalEntity = false;
			position++;
			skip();
		}

		String name = readName();

		skip();
		int quote = peekCharacter();
		String entityValue;
		if (quote == '"' || quote == '\'') {
			position++;
			entityValue = readValue((char) quote, true, false, ValueContext.ENTITY_DECLARATION);
			position++;
		} else if (readExternalId(true, false)) {
			/*
			 * Map external entities to the empty string. This is dishonest, but
			 * it's consistent with Android's Expat pull parser.
			 */
			entityValue = "";
			skip();
			if (peekCharacter() == NDATA[0]) {
				read(NDATA);
				skip();
				readName();
			}
		} else {
			throw new KriptonRuntimeException("Expected entity value or external ID", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}

		if (generalEntity && processDocDecl) {
			if (documentEntities == null) {
				documentEntities = new HashMap<String, char[]>();
			}
			documentEntities.put(name, entityValue.toCharArray());
		}

		skip();
		read('>');
	}

	/**
	 * Read notation declaration.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private void readNotationDeclaration() throws IOException, KriptonRuntimeException {
		read(START_NOTATION);
		skip();
		readName();
		if (!readExternalId(false, false)) {
			throw new KriptonRuntimeException("Expected external ID or public ID for notation", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}
		skip();
		read('>');
	}

	/**
	 * Read end tag.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private void readEndTag() throws IOException, KriptonRuntimeException {
		read('<');
		read('/');
		name = readName(); // TODO: pass the expected name in as a hint?
		skip();
		read('>');

		int sp = (depth - 1) * 4;

		if (depth == 0) {
			checkRelaxed("read end tag " + name + " with no tags open");
			type = COMMENT;
			return;
		}

		if (name.equals(elementStack[sp + 3])) {
			namespace = elementStack[sp];
			prefix = elementStack[sp + 1];
			name = elementStack[sp + 2];
		} else if (!relaxed) {
			throw new KriptonRuntimeException("expected: /" + elementStack[sp + 3] + " read: " + name, true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return getEventType() != END_DOCUMENT;
	}

	/**
	 * Returns the type of the next token.
	 *
	 * @param inDeclaration the in declaration
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private EventType peekType(boolean inDeclaration) throws IOException, KriptonRuntimeException {
		if (position >= limit && !fillBuffer(1)) {
			return END_DOCUMENT;
		}

		switch (buffer[position]) {
		case '&':
			return ENTITY_REF; // &
		case '<':
			if (position + 3 >= limit && !fillBuffer(4)) {
				throw new KriptonRuntimeException("Dangling <", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
			}

			switch (buffer[position + 1]) {
			case '/':
				return END_TAG; // </
			case '?':
				// we're looking for "<?xml " with case insensitivity
				if ((position + 5 < limit || fillBuffer(6)) && (buffer[position + 2] == 'x' || buffer[position + 2] == 'X') && (buffer[position + 3] == 'm' || buffer[position + 3] == 'M')
						&& (buffer[position + 4] == 'l' || buffer[position + 4] == 'L') && (buffer[position + 5] == ' ')) {
					return XML_DECLARATION; // <?xml
				} else {
					return PROCESSING_INSTRUCTION; // <?
				}
			case '!':
				switch (buffer[position + 2]) {
				case 'D':
					return DOCDECL; // <!D
				case '[':
					return CDSECT; // <![
				case '-':
					return COMMENT; // <!-
				case 'E':
					switch (buffer[position + 3]) {
					case 'L':
						return ELEMENTDECL; // <!EL
					case 'N':
						return ENTITYDECL; // <!EN
					}
					break;
				case 'A':
					return ATTLISTDECL; // <!A
				case 'N':
					return NOTATIONDECL; // <!N
				}
				throw new KriptonRuntimeException("Unexpected <!", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
			default:
				return START_TAG; // <
			}
		case '%':
			return inDeclaration ? PARAMETER_ENTITY_REF : TEXT;
		default:
			return TEXT;
		}
	}

	/**
	 * Sets name and attributes.
	 *
	 * @param xmldecl the xmldecl
	 * @param throwOnResolveFailure the throw on resolve failure
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private void parseStartTag(boolean xmldecl, boolean throwOnResolveFailure) throws IOException, KriptonRuntimeException {
		if (!xmldecl) {
			read('<');
		}
		name = readName();
		attributeCount = 0;

		while (true) {
			skip();

			if (position >= limit && !fillBuffer(1)) {
				checkRelaxed(UNEXPECTED_EOF);
				return;
			}

			int c = buffer[position];

			if (xmldecl) {
				if (c == '?') {
					position++;
					read('>');
					return;
				}
			} else {
				if (c == '/') {
					degenerated = true;
					position++;
					skip();
					read('>');
					break;
				} else if (c == '>') {
					position++;
					break;
				}
			}

			String attrName = readName();

			int i = (attributeCount++) * 4;
			attributes = ensureCapacity(attributes, i + 4);
			attributes[i] = "";
			attributes[i + 1] = null;
			attributes[i + 2] = attrName;

			skip();
			if (position >= limit && !fillBuffer(1)) {
				checkRelaxed(UNEXPECTED_EOF);
				return;
			}

			if (buffer[position] == '=') {
				position++;

				skip();
				if (position >= limit && !fillBuffer(1)) {
					checkRelaxed(UNEXPECTED_EOF);
					return;
				}
				char delimiter = buffer[position];

				if (delimiter == '\'' || delimiter == '"') {
					position++;
				} else if (relaxed) {
					delimiter = ' ';
				} else {
					throw new KriptonRuntimeException("attr value delimiter missing!", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
				}

				attributes[i + 3] = readValue(delimiter, true, throwOnResolveFailure, ValueContext.ATTRIBUTE);

				if (delimiter != ' ') {
					position++; // end quote
				}
			} else if (relaxed) {
				attributes[i + 3] = attrName;
			} else {
				checkRelaxed("Attr.value missing f. " + attrName);
				attributes[i + 3] = attrName;
			}
		}

		int sp = depth++ * 4;
		elementStack = ensureCapacity(elementStack, sp + 4);
		elementStack[sp + 3] = name;

		if (depth >= nspCounts.length) {
			int[] bigger = new int[depth + 4];
			System.arraycopy(nspCounts, 0, bigger, 0, nspCounts.length);
			nspCounts = bigger;
		}

		nspCounts[depth] = nspCounts[depth - 1];

		if (processNsp) {
			adjustNsp();
		} else {
			namespace = "";
		}

		// For consistency with Expat, add default attributes after fixing
		// namespaces.
		if (defaultAttributes != null) {
			Map<String, String> elementDefaultAttributes = defaultAttributes.get(name);
			if (elementDefaultAttributes != null) {
				for (Map.Entry<String, String> entry : elementDefaultAttributes.entrySet()) {
					if (getAttributeValue(null, entry.getKey()) != null) {
						continue; // an explicit value overrides the default
					}

					int i = (attributeCount++) * 4;
					attributes = ensureCapacity(attributes, i + 4);
					attributes[i] = "";
					attributes[i + 1] = null;
					attributes[i + 2] = entry.getKey();
					attributes[i + 3] = entry.getValue();
				}
			}
		}

		elementStack[sp] = namespace;
		elementStack[sp + 1] = prefix;
		elementStack[sp + 2] = name;
	}

	/**
	 * Reads an entity reference from the buffer, resolves it, and writes the
	 * resolved entity to {@code out}. If the entity cannot be read or resolved,
	 * {@code out} will contain the partial entity reference.
	 *
	 * @param out the out
	 * @param isEntityToken the is entity token
	 * @param throwOnResolveFailure the throw on resolve failure
	 * @param valueContext the value context
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private void readEntity(StringBuilder out, boolean isEntityToken, boolean throwOnResolveFailure, ValueContext valueContext) throws IOException, KriptonRuntimeException {
		int start = out.length();

		if (buffer[position++] != '&') {
			throw new AssertionError();
		}

		out.append('&');

		while (true) {
			int c = peekCharacter();

			if (c == ';') {
				out.append(';');
				position++;
				break;

			} else if (c >= 128 || (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_' || c == '-' || c == '#') {
				position++;
				out.append((char) c);

			} else if (relaxed) {
				// intentionally leave the partial reference in 'out'
				return;

			} else {
				throw new KriptonRuntimeException("unterminated entity ref", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
			}
		}

		String code = out.substring(start + 1, out.length() - 1);

		if (isEntityToken) {
			name = code;
		}

		if (code.startsWith("#")) {
			try {
				int c = code.startsWith("#x") ? Integer.parseInt(code.substring(2), 16) : Integer.parseInt(code.substring(1));
				out.delete(start, out.length());
				out.appendCodePoint(c);
				unresolved = false;
				return;
			} catch (NumberFormatException notANumber) {
				throw new KriptonRuntimeException("Invalid character reference: &" + code);
			} catch (IllegalArgumentException invalidCodePoint) {
				throw new KriptonRuntimeException("Invalid character reference: &" + code);
			}
		}

		if (valueContext == ValueContext.ENTITY_DECLARATION) {
			// keep the unresolved &code; in the text to resolve later
			return;
		}

		String defaultEntity = DEFAULT_ENTITIES.get(code);
		if (defaultEntity != null) {
			out.delete(start, out.length());
			unresolved = false;
			out.append(defaultEntity);
			return;
		}

		char[] resolved;
		if (documentEntities != null && (resolved = documentEntities.get(code)) != null) {
			out.delete(start, out.length());
			unresolved = false;
			if (processDocDecl) {
				pushContentSource(resolved); // parse the entity as XML
			} else {
				out.append(resolved); // include the entity value as text
			}
			return;
		}

		/*
		 * The parser skipped an external DTD, and now we've encountered an
		 * unknown entity that could have been declared there. Map it to the
		 * empty string. This is dishonest, but it's consistent with Android's
		 * old ExpatPullParser.
		 */
		if (systemId != null) {
			out.delete(start, out.length());
			return;
		}

		// keep the unresolved entity "&code;" in the text for relaxed clients
		unresolved = true;
		if (throwOnResolveFailure) {
			checkRelaxed("unresolved: &" + code + ";");
		}
	}

	/**
	 * Where a value is found impacts how that value is interpreted. For
	 * example, in attributes, "\n" must be replaced with a space character. In
	 * text, "]]>" is forbidden. In entity declarations, named references are
	 * not resolved.
	 */
	enum ValueContext {
		
		/** The attribute. */
		ATTRIBUTE, 
 /** The text. */
 TEXT, 
 /** The entity declaration. */
 ENTITY_DECLARATION
	}

	/**
	 * Returns the current text or attribute value. This also has the side
	 * effect of setting isWhitespace to false if a non-whitespace character is
	 * encountered.
	 *
	 * @param delimiter            {@code <} for text, {@code "} and {@code '} for quoted
	 *            attributes, or a space for unquoted attributes.
	 * @param resolveEntities the resolve entities
	 * @param throwOnResolveFailure the throw on resolve failure
	 * @param valueContext the value context
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private String readValue(char delimiter, boolean resolveEntities, boolean throwOnResolveFailure, ValueContext valueContext) throws IOException, KriptonRuntimeException {

		/*
		 * This method returns all of the characters from the current position
		 * through to an appropriate delimiter.
		 *
		 * If we're lucky (which we usually are), we'll return a single slice of
		 * the buffer. This fast path avoids allocating a string builder.
		 *
		 * There are 6 unlucky characters we could encounter: - "&": entities
		 * must be resolved. - "%": parameter entities are unsupported in entity
		 * values. - "<": this isn't permitted in attributes unless relaxed. -
		 * "]": this requires a lookahead to defend against the forbidden CDATA
		 * section delimiter "]]>". - "\r": If a "\r" is followed by a "\n", we
		 * discard the "\r". If it isn't followed by "\n", we replace "\r" with
		 * either a "\n" in text nodes or a space in attribute values. - "\n":
		 * In attribute values, "\n" must be replaced with a space.
		 *
		 * We could also get unlucky by needing to refill the buffer midway
		 * through the text.
		 */

		int start = position;
		StringBuilder result = null;

		// if a text section was already started, prefix the start
		if (valueContext == ValueContext.TEXT && text != null) {
			result = new StringBuilder();
			result.append(text);
		}

		while (true) {

			/*
			 * Make sure we have at least a single character to read from the
			 * buffer. This mutates the buffer, so save the partial result to
			 * the slow path string builder first.
			 */
			if (position >= limit) {
				if (start < position) {
					if (result == null) {
						result = new StringBuilder();
					}
					result.append(buffer, start, position - start);
				}
				if (!fillBuffer(1)) {
					return result != null ? result.toString() : "";
				}
				start = position;
			}

			char c = buffer[position];

			if (c == delimiter || (delimiter == ' ' && (c <= ' ' || c == '>')) || c == '&' && !resolveEntities) {
				break;
			}

			if (c != '\r' && (c != '\n' || valueContext != ValueContext.ATTRIBUTE) && c != '&' && c != '<' && (c != ']' || valueContext != ValueContext.TEXT)
					&& (c != '%' || valueContext != ValueContext.ENTITY_DECLARATION)) {
				isWhitespace &= (c <= ' ');
				position++;
				continue;
			}

			/*
			 * We've encountered an unlucky character! Convert from fast path to
			 * slow path if we haven't done so already.
			 */
			if (result == null) {
				result = new StringBuilder();
			}
			result.append(buffer, start, position - start);

			if (c == '\r') {
				if ((position + 1 < limit || fillBuffer(2)) && buffer[position + 1] == '\n') {
					position++;
				}
				c = (valueContext == ValueContext.ATTRIBUTE) ? ' ' : '\n';

			} else if (c == '\n') {
				c = ' ';

			} else if (c == '&') {
				isWhitespace = false; // TODO: what if the entity resolves to
										// whitespace?
				readEntity(result, false, throwOnResolveFailure, valueContext);
				start = position;
				continue;

			} else if (c == '<') {
				if (valueContext == ValueContext.ATTRIBUTE) {
					checkRelaxed("Illegal: \"<\" inside attribute value");
				}
				isWhitespace = false;

			} else if (c == ']') {
				if ((position + 2 < limit || fillBuffer(3)) && buffer[position + 1] == ']' && buffer[position + 2] == '>') {
					checkRelaxed("Illegal: \"]]>\" outside CDATA section");
				}
				isWhitespace = false;

			} else {
				// if (c == '%')
				throw new KriptonRuntimeException("This parser doesn't support parameter entities", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);

			}

			position++;
			result.append(c);
			start = position;
		}

		if (result == null) {
			return stringPool.get(buffer, start, position - start);
		} else {
			result.append(buffer, start, position - start);
			return result.toString();
		}
	}

	/**
	 * Read.
	 *
	 * @param expected the expected
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private void read(char expected) throws IOException, KriptonRuntimeException {
		int c = peekCharacter();
		if (c != expected) {
			checkRelaxed("expected: '" + expected + "' actual: '" + ((char) c) + "'");
		}
		position++;
	}

	/**
	 * Read.
	 *
	 * @param chars the chars
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private void read(char[] chars) throws IOException, KriptonRuntimeException {
		if (position + chars.length >= limit && !fillBuffer(chars.length)) {
			checkRelaxed("expected: '" + new String(chars) + "' but was EOF");
			return;
		}

		// TODO: replace with Arrays.equals(buffer, position, delimiter, 0,
		// delimiter.length)
		// when the VM has better method inlining
		for (int i = 0; i < chars.length; i++) {
			if (buffer[position + i] != chars[i]) {
				checkRelaxed("expected: \"" + new String(chars) + "\" but was \"" + new String(buffer, position, chars.length) + "...\"");
			}
		}

		position += chars.length;
	}

	/**
	 * Peek character.
	 *
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private int peekCharacter() throws IOException, KriptonRuntimeException {
		if (position < limit || fillBuffer(1)) {
			return buffer[position];
		}
		return -1;
	}

	/**
	 * Returns true once {@code limit - position >= minimum}. If the data is
	 * exhausted before that many characters are available, this returns false.
	 *
	 * @param minimum the minimum
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private boolean fillBuffer(int minimum) throws IOException, KriptonRuntimeException {
		// If we've exhausted the current content source, remove it
		while (nextContentSource != null) {
			if (position < limit) {
				throw new KriptonRuntimeException("Unbalanced entity!", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
			}
			popContentSource();
			if (limit - position >= minimum) {
				return true;
			}
		}

		// Before clobbering the old characters, update where buffer starts
		for (int i = 0; i < position; i++) {
			if (buffer[i] == '\n') {
				bufferStartLine++;
				bufferStartColumn = 0;
			} else {
				bufferStartColumn++;
			}
		}

		if (bufferCapture != null) {
			bufferCapture.append(buffer, 0, position);
		}

		if (limit != position) {
			limit -= position;
			System.arraycopy(buffer, position, buffer, 0, limit);
		} else {
			limit = 0;
		}

		position = 0;
		int total;
		while ((total = reader.read(buffer, limit, buffer.length - limit)) != -1) {
			limit += total;
			if (limit >= minimum) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns an element or attribute name. This is always non-empty for
	 * non-relaxed parsers.
	 *
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private String readName() throws IOException, KriptonRuntimeException {
		if (position >= limit && !fillBuffer(1)) {
			checkRelaxed("name expected");
			return "";
		}

		int start = position;
		StringBuilder result = null;

		// read the first character
		char c = buffer[position];
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_' || c == ':' || c >= '\u00c0' // TODO:
																										// check
																										// the
																										// XML
																										// spec
				|| relaxed) {
			position++;
		} else {
			checkRelaxed("name expected");
			return "";
		}

		while (true) {
			/*
			 * Make sure we have at least a single character to read from the
			 * buffer. This mutates the buffer, so save the partial result to
			 * the slow path string builder first.
			 */
			if (position >= limit) {
				if (result == null) {
					result = new StringBuilder();
				}
				result.append(buffer, start, position - start);
				if (!fillBuffer(1)) {
					return result.toString();
				}
				start = position;
			}

			// read another character
			c = buffer[position];
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '_' || c == '-' || c == ':' || c == '.' || c >= '\u00b7') { // TODO:
																																								// check
																																								// the
																																								// XML
																																								// spec
				position++;
				continue;
			}

			// we encountered a non-name character. done!
			if (result == null) {
				return stringPool.get(buffer, start, position - start);
			} else {
				result.append(buffer, start, position - start);
				return result.toString();
			}
		}
	}

	/**
	 * Skip.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws KriptonRuntimeException the kripton runtime exception
	 */
	private void skip() throws IOException, KriptonRuntimeException {
		while (position < limit || fillBuffer(1)) {
			int c = buffer[position];
			if (c > ' ') {
				break;
			}
			position++;
		}
	}

	// public part starts here...

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#setInput(java.io.Reader)
	 */
	@Override
	public void setInput(Reader reader) {
		this.reader = reader;

		type = START_DOCUMENT;
		name = null;
		namespace = null;
		degenerated = false;
		attributeCount = -1;
		encoding = null;
		version = null;
		standalone = null;

		if (reader == null) {
			return;
		}

		position = 0;
		limit = 0;
		bufferStartLine = 0;
		bufferStartColumn = 0;
		depth = 0;
		documentEntities = null;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#setInput(java.io.InputStream, java.lang.String)
	 */
	@Override
	public void setInput(InputStream is, String charset) {
		position = 0;
		limit = 0;
		boolean detectCharset = (charset == null);

		if (is == null) {
			throw new IllegalArgumentException();
		}

		try {
			if (detectCharset) {
				// read the four bytes looking for an indication of the encoding
				// in use
				int firstFourBytes = 0;
				while (limit < 4) {
					int i = is.read();
					if (i == -1) {
						break;
					}
					firstFourBytes = (firstFourBytes << 8) | i;
					buffer[limit++] = (char) i;
				}

				if (limit == 4) {
					switch (firstFourBytes) {
					case 0x00000FEFF: // UTF-32BE BOM
						charset = "UTF-32BE";
						limit = 0;
						break;

					case 0x0FFFE0000: // UTF-32LE BOM
						charset = "UTF-32LE";
						limit = 0;
						break;

					case 0x0000003c: // '<' in UTF-32BE
						charset = "UTF-32BE";
						buffer[0] = '<';
						limit = 1;
						break;

					case 0x03c000000: // '<' in UTF-32LE
						charset = "UTF-32LE";
						buffer[0] = '<';
						limit = 1;
						break;

					case 0x0003c003f: // "<?" in UTF-16BE
						charset = "UTF-16BE";
						buffer[0] = '<';
						buffer[1] = '?';
						limit = 2;
						break;

					case 0x03c003f00: // "<?" in UTF-16LE
						charset = "UTF-16LE";
						buffer[0] = '<';
						buffer[1] = '?';
						limit = 2;
						break;

					case 0x03c3f786d: // "<?xm" in ASCII etc.
						while (true) {
							int i = is.read();
							if (i == -1) {
								break;
							}
							buffer[limit++] = (char) i;
							if (i == '>') {
								String s = new String(buffer, 0, limit);
								int i0 = s.indexOf("encoding");
								if (i0 != -1) {
									while (s.charAt(i0) != '"' && s.charAt(i0) != '\'') {
										i0++;
									}
									char deli = s.charAt(i0++);
									int i1 = s.indexOf(deli, i0);
									charset = s.substring(i0, i1);
								}
								break;
							}
						}
						break;

					default:
						// handle a byte order mark followed by something other
						// than <?
						if ((firstFourBytes & 0x0ffff0000) == 0x0feff0000) {
							charset = "UTF-16BE";
							buffer[0] = (char) ((buffer[2] << 8) | buffer[3]);
							limit = 1;
						} else if ((firstFourBytes & 0x0ffff0000) == 0x0fffe0000) {
							charset = "UTF-16LE";
							buffer[0] = (char) ((buffer[3] << 8) | buffer[2]);
							limit = 1;
						} else if ((firstFourBytes & 0x0ffffff00) == 0x0efbbbf00) {
							charset = "UTF-8";
							buffer[0] = buffer[3];
							limit = 1;
						}
					}
				}
			}

			if (charset == null) {
				charset = "UTF-8";
			}

			int savedLimit = limit;
			setInput(new InputStreamReader(is, charset));
			encoding = charset;
			limit = savedLimit;

			/*
			 * Skip the optional BOM if we didn't above. This decrements limit
			 * rather than incrementing position so that <?xml version='1.0'?>
			 * is still at character 0.
			 */
			if (!detectCharset && peekCharacter() == 0xfeff) {
				limit--;
				System.arraycopy(buffer, 1, buffer, 0, limit);
			}
		} catch (Exception e) {
			throw new KriptonRuntimeException("Invalid stream or encoding: " + e, true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), e);
		}
	}

	/* (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		if (reader != null) {
			reader.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getFeature(java.lang.String)
	 */
	@Override
	public boolean getFeature(String feature) {
		if (XmlPullParser.FEATURE_PROCESS_NAMESPACES.equals(feature)) {
			return processNsp;
		} else if (FEATURE_RELAXED.equals(feature)) {
			return relaxed;
		} else if (FEATURE_PROCESS_DOCDECL.equals(feature)) {
			return processDocDecl;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getInputEncoding()
	 */
	@Override
	public String getInputEncoding() {
		return encoding;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#defineEntityReplacementText(java.lang.String, java.lang.String)
	 */
	@Override
	public void defineEntityReplacementText(String entity, String value) {
		if (processDocDecl) {
			throw new IllegalStateException("Entity replacement text may not be defined with DOCTYPE processing enabled.");
		}
		if (reader == null) {
			throw new IllegalStateException("Entity replacement text must be defined after setInput()");
		}
		if (documentEntities == null) {
			documentEntities = new HashMap<String, char[]>();
		}
		documentEntities.put(entity, value.toCharArray());
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getProperty(java.lang.String)
	 */
	@Override
	public Object getProperty(String property) {
		if (property.equals(PROPERTY_XMLDECL_VERSION)) {
			return version;
		} else if (property.equals(PROPERTY_XMLDECL_STANDALONE)) {
			return standalone;
		} else if (property.equals(PROPERTY_LOCATION)) {
			return location != null ? location : reader.toString();
		} else {
			return null;
		}
	}

	/**
	 * Returns the root element's name if it was declared in the DTD. This
	 * equals the first tag's name for valid documents.
	 * 
	 * @return string
	 */
	public String getRootElementName() {
		return rootElementName;
	}

	/**
	 * Returns the document's system ID if it was declared. This is typically a
	 * string like {@code http://www.w3.org/TR/html4/strict.dtd}.
	 * 
	 * @return string
	 */
	public String getSystemId() {
		return systemId;
	}

	/**
	 * Returns the document's public ID if it was declared. This is typically a
	 * string like {@code -//W3C//DTD HTML 4.01//EN}.
	 * 
	 * @return string
	 */
	public String getPublicId() {
		return publicId;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getNamespaceCount(int)
	 */
	@Override
	public int getNamespaceCount(int depth) {
		if (depth > this.depth) {
			throw new IndexOutOfBoundsException();
		}
		return nspCounts[depth];
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getNamespacePrefix(int)
	 */
	@Override
	public String getNamespacePrefix(int pos) {
		return nspStack[pos * 2];
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getNamespaceUri(int)
	 */
	@Override
	public String getNamespaceUri(int pos) {
		return nspStack[(pos * 2) + 1];
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getNamespace(java.lang.String)
	 */
	@Override
	public String getNamespace(String prefix) {
		if ("xml".equals(prefix)) {
			return "http://www.w3.org/XML/1998/namespace";
		}
		if ("xmlns".equals(prefix)) {
			return "http://www.w3.org/2000/xmlns/";
		}

		for (int i = (getNamespaceCount(depth) << 1) - 2; i >= 0; i -= 2) {
			if (prefix == null) {
				if (nspStack[i] == null) {
					return nspStack[i + 1];
				}
			} else if (prefix.equals(nspStack[i])) {
				return nspStack[i + 1];
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getDepth()
	 */
	@Override
	public int getDepth() {
		return depth;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getPositionDescription()
	 */
	@Override
	public String getPositionDescription() {
		StringBuilder buf = new StringBuilder(type.toString());
		buf.append(' ');

		if (type == START_TAG || type == END_TAG) {
			if (degenerated) {
				buf.append("(empty) ");
			}
			buf.append('<');
			if (type == END_TAG) {
				buf.append('/');
			}

			if (prefix != null) {
				buf.append("{" + namespace + "}" + prefix + ":");
			}
			buf.append(name);

			int cnt = attributeCount * 4;
			for (int i = 0; i < cnt; i += 4) {
				buf.append(' ');
				if (attributes[i + 1] != null) {
					buf.append("{" + attributes[i] + "}" + attributes[i + 1] + ":");
				}
				buf.append(attributes[i + 2] + "='" + attributes[i + 3] + "'");
			}

			buf.append('>');
		} else if (type == IGNORABLE_WHITESPACE) {
			;
		} else if (type != TEXT) {
			buf.append(getText());
		} else if (isWhitespace) {
			buf.append("(whitespace)");
		} else {
			String text = getText();
			if (text.length() > 16) {
				text = text.substring(0, 16) + "...";
			}
			buf.append(text);
		}

		buf.append("@" + getLineNumber() + ":" + getColumnNumber());
		if (location != null) {
			buf.append(" in ");
			buf.append(location);
		} else if (reader != null) {
			buf.append(" in ");
			buf.append(reader.toString());
		}
		return buf.toString();
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getLineNumber()
	 */
	@Override
	public int getLineNumber() {
		int result = bufferStartLine;
		for (int i = 0; i < position; i++) {
			if (buffer[i] == '\n') {
				result++;
			}
		}
		return result + 1; // the first line is '1'
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getColumnNumber()
	 */
	@Override
	public int getColumnNumber() {
		int result = bufferStartColumn;
		for (int i = 0; i < position; i++) {
			if (buffer[i] == '\n') {
				result = 0;
			} else {
				result++;
			}
		}
		return result + 1; // the first column is '1'
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#isWhitespace()
	 */
	@Override
	public boolean isWhitespace() {
		if (type != TEXT && type != IGNORABLE_WHITESPACE && type != CDSECT) {
			throw new KriptonRuntimeException(ILLEGAL_TYPE, true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}
		return isWhitespace;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getText()
	 */
	@Override
	public String getText() {
		if (type.ordinal() < TEXT.ordinal() || (type == ENTITY_REF && unresolved)) {
			return null;
		} else if (text == null) {
			return "";
		} else {
			return text;
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getTextCharacters(int[])
	 */
	@Override
	public char[] getTextCharacters(int[] poslen) {
		String text = getText();
		if (text == null) {
			poslen[0] = -1;
			poslen[1] = -1;
			return null;
		}
		char[] result = text.toCharArray();
		poslen[0] = 0;
		poslen[1] = result.length;
		return result;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getNamespace()
	 */
	@Override
	public String getNamespace() {
		return namespace;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getPrefix()
	 */
	@Override
	public String getPrefix() {
		return prefix;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#isEmptyElementTag()
	 */
	@Override
	public boolean isEmptyElementTag() {
		if (type != START_TAG) {
			throw new KriptonRuntimeException(ILLEGAL_TYPE, true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}
		return degenerated;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getAttributeCount()
	 */
	@Override
	public int getAttributeCount() {
		return attributeCount;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getAttributeType(int)
	 */
	@Override
	public String getAttributeType(int index) {
		return "CDATA";
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#isAttributeDefault(int)
	 */
	@Override
	public boolean isAttributeDefault(int index) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getAttributeNamespace(int)
	 */
	@Override
	public String getAttributeNamespace(int index) {
		if (index >= attributeCount) {
			throw new IndexOutOfBoundsException();
		}
		return attributes[index * 4];
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getAttributeName(int)
	 */
	@Override
	public String getAttributeName(int index) {
		if (index >= attributeCount) {
			throw new IndexOutOfBoundsException();
		}
		return attributes[(index * 4) + 2];
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getAttributePrefix(int)
	 */
	@Override
	public String getAttributePrefix(int index) {
		if (index >= attributeCount) {
			throw new IndexOutOfBoundsException();
		}
		return attributes[(index * 4) + 1];
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getAttributeValue(int)
	 */
	@Override
	public String getAttributeValue(int index) {
		if (index >= attributeCount) {
			throw new IndexOutOfBoundsException();
		}
		return attributes[(index * 4) + 3];
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getAttributeValue(java.lang.String, java.lang.String)
	 */
	@Override
	public String getAttributeValue(String namespace, String name) {
		for (int i = (attributeCount * 4) - 4; i >= 0; i -= 4) {
			if (attributes[i + 2].equals(name) && (namespace == null || attributes[i].equals(namespace))) {
				return attributes[i + 3];
			}
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getAttributeIndex(java.lang.String, java.lang.String)
	 */
	@Override
	public int getAttributeIndex(String namespace, String name) {
		for (int i = (attributeCount * 4) - 4; i >= 0; i -= 4) {
			if (attributes[i + 2].equals(name) && (namespace == null || attributes[i].equals(namespace))) {
				return i / 4;
			}
		}

		return -1;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getEventType()
	 */
	@Override
	public EventType getEventType() {
		return type;
	}

	// utility methods to make XML parsing easier ...

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#nextTag()
	 */
	@Override
	public EventType nextTag() throws KriptonRuntimeException, IOException {
		next();
		if (type == TEXT && isWhitespace) {
			next();
		}

		if (type != END_TAG && type != START_TAG) {
			throw new KriptonRuntimeException("unexpected type", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}

		return type;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#require(int, java.lang.String, java.lang.String)
	 */
	@Override
	public void require(EventType type, String namespace, String name) throws KriptonRuntimeException, IOException {
		if (type != this.type || (namespace != null && !namespace.equals(getNamespace())) || (name != null && !name.equals(getName()))) {
			throw new KriptonRuntimeException("expected: " + type + " {" + namespace + "}" + name, true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#nextText()
	 */
	@Override
	public String nextText() throws IOException {
		if (type != START_TAG) {
			throw new KriptonRuntimeException("precondition: START_TAG", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}

		next();

		String result;
		if (type == TEXT) {
			result = getText();
			next();
		} else {
			result = "";
		}

		if (type != END_TAG) {
			throw new KriptonRuntimeException("END_TAG expected", true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#setFeature(java.lang.String, boolean)
	 */
	@Override
	public void setFeature(String feature, boolean value) {
		if (XmlPullParser.FEATURE_PROCESS_NAMESPACES.equals(feature)) {
			processNsp = value;
		} else if (XmlPullParser.FEATURE_PROCESS_DOCDECL.equals(feature)) {
			processDocDecl = value;
		} else if (FEATURE_RELAXED.equals(feature)) {
			relaxed = value;
		} else {
			throw new KriptonRuntimeException("unsupported feature: " + feature, true, this.getLineNumber(), this.getColumnNumber(), getPositionDescription(), null);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#setProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setProperty(String property, Object value) {
		if (property.equals(PROPERTY_LOCATION)) {
			location = String.valueOf(value);
		} else {
			throw new KriptonRuntimeException("unsupported property: " + property);
		}
	}

	/**
	 * A chain of buffers containing XML content. Each content source contains
	 * the parser's primary read buffer or the characters of entities actively
	 * being parsed.
	 *
	 * <p>
	 * For example, note the buffers needed to parse this document:
	 * 
	 * <pre>
	 *    {@code
	 *   <!DOCTYPE foo [
	 *       <!ENTITY baz "ghi">
	 *       <!ENTITY bar "def &baz; jkl">
	 *   ]>
	 *   <foo>abc &bar; mno</foo>
	 * }
	 * </pre>
	 *
	 * <p>
	 * Things get interesting when the bar entity is encountered. At that point
	 * two buffers are active:
	 * <ol>
	 * <li>The value for the bar entity, containing {@code "def &baz; jkl"}
	 * <li>The parser's primary read buffer, containing {@code " mno</foo>"}
	 * </ol>
	 * <p>
	 * The parser will return the characters {@code "def "} from the bar
	 * entity's buffer, and then it will encounter the baz entity. To handle
	 * that, three buffers will be active:
	 * <ol>
	 * <li>The value for the baz entity, containing {@code "ghi"}
	 * <li>The remaining value for the bar entity, containing {@code " jkl"}
	 * <li>The parser's primary read buffer, containing {@code " mno</foo>"}
	 * </ol>
	 * <p>
	 * The parser will then return the characters {@code ghi jkl mno} in that
	 * sequence by reading each buffer in sequence.
	 */
	static class ContentSource {
		
		/** The next. */
		private final ContentSource next;
		
		/** The buffer. */
		private final char[] buffer;
		
		/** The position. */
		private final int position;
		
		/** The limit. */
		private final int limit;

		/**
		 * Instantiates a new content source.
		 *
		 * @param next the next
		 * @param buffer the buffer
		 * @param position the position
		 * @param limit the limit
		 */
		ContentSource(ContentSource next, char[] buffer, int position, int limit) {
			this.next = next;
			this.buffer = buffer;
			this.position = position;
			this.limit = limit;
		}
	}

	/**
	 * Prepends the characters of {@code newBuffer} to be read before the
	 * current buffer.
	 *
	 * @param newBuffer the new buffer
	 */
	private void pushContentSource(char[] newBuffer) {
		nextContentSource = new ContentSource(nextContentSource, buffer, position, limit);
		buffer = newBuffer;
		position = 0;
		limit = newBuffer.length;
	}

	/**
	 * Replaces the current exhausted buffer with the next buffer in the chain.
	 */
	private void popContentSource() {
		buffer = nextContentSource.buffer;
		position = nextContentSource.position;
		limit = nextContentSource.limit;
		nextContentSource = nextContentSource.next;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#hasText()
	 */
	@Override
	public boolean hasText() {
		return StringUtils.hasText(getText());
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getElementText()
	 */
	@Override
	public String getElementText() throws IOException {
		return nextText();
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getElementAsBoolean()
	 */
	@Override
	public boolean getElementAsBoolean() throws IOException {
		return Boolean.parseBoolean(nextText());
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getElementAsInt()
	 */
	@Override
	public int getElementAsInt() throws Exception {
		return Integer.parseInt(nextText());
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getElementAsLong()
	 */
	@Override
	public long getElementAsLong() throws Exception {
		return Long.parseLong(nextText());
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getElementAsFloat()
	 */
	@Override
	public float getElementAsFloat() throws Exception {
		return Float.parseFloat(nextText());
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getElementAsDouble()
	 */
	@Override
	public double getElementAsDouble() throws Exception {
		return Double.parseDouble(nextText());
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getElementAsInteger()
	 */
	@Override
	public BigInteger getElementAsInteger() throws Exception {
		return new BigInteger(nextText());
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getElementAsDecimal()
	 */
	@Override
	public BigDecimal getElementAsDecimal() throws Exception {
		return new BigDecimal(nextText());
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#isEmptyElement()
	 */
	@Override
	public boolean isEmptyElement() {
		return isEmptyElementTag();
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getElementAsBinary()
	 */
	@Override
	public byte[] getElementAsBinary() throws IOException {
		return Base64Utils.decode(nextText());
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getAttributeAsDecimal(int)
	 */
	@Override
	public BigDecimal getAttributeAsDecimal(int index) {
		return new BigDecimal(getAttributeValue(index).trim());
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.xml.XmlPullParser#getAttributeAsInteger(int)
	 */
	@Override
	public BigInteger getAttributeAsInteger(int index) {
		return new BigInteger(getAttributeValue(index).trim());
	}

	/**
	 * Skip the entire tag and position the parser to the its end.
	 *  
	 * @throws KriptonRuntimeException
	 * @throws IOException
	 */
	public void skipChildren() throws KriptonRuntimeException, IOException {
		int startDepth=getDepth();
		String startName=getName();
		int currentDepth;
		do {
			nextTag();
			currentDepth=getDepth();
		} while (this.hasNext() && currentDepth>startDepth && !startName.equals(getName()));
	}
}
