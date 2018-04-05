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
package com.abubusoft.kripton.android.sqlite.internals;

import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving JQLBaseError events.
 * The class that is interested in processing a JQLBaseError
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addJQLBaseErrorListener</code> method. When
 * the JQLBaseError event occurs, that object's appropriate
 * method is invoked.
 *
 */
public class JQLBaseErrorListener implements ANTLRErrorListener {

	/* (non-Javadoc)
	 * @see org.antlr.v4.runtime.ANTLRErrorListener#syntaxError(org.antlr.v4.runtime.Recognizer, java.lang.Object, int, int, java.lang.String, org.antlr.v4.runtime.RecognitionException)
	 */
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		
	}

	/* (non-Javadoc)
	 * @see org.antlr.v4.runtime.ANTLRErrorListener#reportAmbiguity(org.antlr.v4.runtime.Parser, org.antlr.v4.runtime.dfa.DFA, int, int, boolean, java.util.BitSet, org.antlr.v4.runtime.atn.ATNConfigSet)
	 */
	@Override
	public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact,
			BitSet ambigAlts, ATNConfigSet configs) {
		
	}

	/* (non-Javadoc)
	 * @see org.antlr.v4.runtime.ANTLRErrorListener#reportAttemptingFullContext(org.antlr.v4.runtime.Parser, org.antlr.v4.runtime.dfa.DFA, int, int, java.util.BitSet, org.antlr.v4.runtime.atn.ATNConfigSet)
	 */
	@Override
	public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
			BitSet conflictingAlts, ATNConfigSet configs) {
		
	}

	/* (non-Javadoc)
	 * @see org.antlr.v4.runtime.ANTLRErrorListener#reportContextSensitivity(org.antlr.v4.runtime.Parser, org.antlr.v4.runtime.dfa.DFA, int, int, int, org.antlr.v4.runtime.atn.ATNConfigSet)
	 */
	@Override
	public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction,
			ATNConfigSet configs) {
		
	}

}
