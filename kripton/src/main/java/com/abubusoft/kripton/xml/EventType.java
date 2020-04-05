package com.abubusoft.kripton.xml;

public enum EventType {
	/**
	 * Signalize that parser is at the very beginning of the document and
	 * nothing was read yet. This event type can only be observed by calling
	 * getEvent() before the first call to next(), nextToken, or nextTag()).
	 *
	 */
	START_DOCUMENT,

	/**
	 * Logical end of the xml document. Returned from getEventType, next() and
	 * nextToken() when the end of the input document has been reached.
	 * <p>
	 * <strong>NOTE:</strong> subsequent calls to <a href="#next()">next()</a>
	 * or <a href="#nextToken()">nextToken()</a> may result in exception being
	 * thrown.
	 *
	 */
	END_DOCUMENT,

	/**
	 * Returned from getEventType(), <a href="#next()">next()</a>,
	 * <a href="#nextToken()">nextToken()</a> when a start tag was read. The
	 * name of start tag is available from getName(), its namespace and prefix
	 * are available from getNamespace() and getPrefix() if
	 * <a href='#FEATURE_PROCESS_NAMESPACES'>namespaces are enabled</a>. See
	 * getAttribute* methods to retrieve element attributes. See getNamespace*
	 * methods to retrieve newly declared namespaces.
	 *
	 */
	START_TAG,

	/**
	 * Returned from getEventType(), <a href="#next()">next()</a>, or
	 * <a href="#nextToken()">nextToken()</a> when an end tag was read. The name
	 * of start tag is available from getName(), its namespace and prefix are
	 * available from getNamespace() and getPrefix().
	 *
	 */
	END_TAG,

	/**
	 * Character data was read and will is available by calling getText().
	 * <p>
	 * <strong>Please note:</strong> <a href="#next()">next()</a> will
	 * accumulate multiple events into one TEXT event, skipping
	 * IGNORABLE_WHITESPACE, PROCESSING_INSTRUCTION and COMMENT events, In
	 * contrast, <a href="#nextToken()">nextToken()</a> will stop reading text
	 * when any other event is observed. Also, when the state was reached by
	 * calling next(), the text value will be normalized, whereas getText() will
	 * return unnormalized content in the case of nextToken(). This allows an
	 * exact roundtrip without changing line ends when examining low level
	 * events, whereas for high level applications the text is normalized
	 * appropriately.
	 */
	TEXT,

	

	/**
	 * A CDATA sections was just read; this token is available only from calls
	 * to <a href="#nextToken()">nextToken()</a>. A call to next() will
	 * accumulate various text events into a single event of type TEXT. The text
	 * contained in the CDATA section is available by calling getText().
	 */
	CDSECT,

	/**
	 * An entity reference was just read; this token is available from
	 * <a href="#nextToken()">nextToken()</a> only. The entity name is available
	 * by calling getName(). If available, the replacement text can be obtained
	 * by calling getText(); otherwise, the user is responsible for resolving
	 * the entity reference. This event type is never returned from next();
	 * next() will accumulate the replacement text and other text events to a
	 * single TEXT event.
	 */
	ENTITY_REF,

	/**
	 * Ignorable whitespace was just read. This token is available only from
	 * <a href="#nextToken()">nextToken()</a>). For non-validating parsers, this
	 * event is only reported by nextToken() when outside the root element.
	 * Validating parsers may be able to detect ignorable whitespace at other
	 * locations. The ignorable whitespace string is available by calling
	 * getText()
	 *
	 * <p>
	 * <strong>NOTE:</strong> this is different from calling the isWhitespace()
	 * method, since text content may be whitespace but not ignorable.
	 *
	 * Ignorable whitespace is skipped by next() automatically; this event type
	 * is never returned from next().
	 */
	IGNORABLE_WHITESPACE,

	/**
	 * An XML processing instruction declaration was just read. This event type
	 * is available only via <a href="#nextToken()">nextToken()</a>. getText()
	 * will return text that is inside the processing instruction. Calls to
	 * next() will skip processing instructions automatically.
	 */
	PROCESSING_INSTRUCTION,

	/**
	 * An XML comment was just read. This event type is this token is available
	 * via <a href="#nextToken()">nextToken()</a> only; calls to next() will
	 * skip comments automatically. The content of the comment can be accessed
	 * using the getText() method.
	 */
	COMMENT,

	/**
	 * An XML document type declaration was just read. This token is available
	 * from <a href="#nextToken()">nextToken()</a> only. The unparsed text
	 * inside the doctype is available via the getText() method.
	 */
	DOCDECL,
	
	/** The Constant ELEMENTDECL. 11 */
	ELEMENTDECL,
	
	/** The Constant ENTITYDECL. 12 */
	ENTITYDECL,
	
	/** The Constant ATTLISTDECL. 13*/
	ATTLISTDECL,
	
	/** The Constant NOTATIONDECL. 14*/
	NOTATIONDECL,
	
	/** The Constant PARAMETER_ENTITY_REF. 15 */
	PARAMETER_ENTITY_REF,
	
	/** The Constant XML_DECLARATION. The latest 998 */
	XML_DECLARATION;
}
