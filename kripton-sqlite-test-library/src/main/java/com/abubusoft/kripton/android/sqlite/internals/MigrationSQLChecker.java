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
/**
 * 
 */
package com.abubusoft.kripton.android.sqlite.internals;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.abubusoft.kripton.android.sqlite.KriptonAssert;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlLexer;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser;

/**
 * The Class MigrationSQLChecker.
 */
public class MigrationSQLChecker {

	/** The instance. */
	protected static MigrationSQLChecker instance;

	/**
	 * Gets the single instance of MigrationSQLChecker.
	 *
	 * @return single instance of MigrationSQLChecker
	 */
	public static final MigrationSQLChecker getInstance() {
		if (instance == null) {
			instance = new MigrationSQLChecker();
		}

		return instance;
	}

	/** The walker. */
	ParseTreeWalker walker = new ParseTreeWalker();

	/**
	 * Instantiates a new migration SQL checker.
	 */
	private MigrationSQLChecker() {

	}

	/**
	 * Analyze internal.
	 *
	 * @param <L> the generic type
	 * @param jql the jql
	 * @param listener the listener
	 */
	protected <L extends JqlBaseListener> void analyzeInternal(final String jql, L listener) {
		walker.walk(listener, prepareParser(jql).value0);
	}

	/**
	 * Analyze.
	 *
	 * @param <L> the generic type
	 * @param sql the sql
	 * @param listener the listener
	 */
	public <L extends JqlBaseListener> void analyze(final String sql, L listener) {
		analyzeInternal(sql, listener);
	}

	/**
	 * Prepare parser.
	 *
	 * @param jql the jql
	 * @return the pair
	 */
	protected Pair<ParserRuleContext, CommonTokenStream> prepareParser(final String jql) {
		JqlLexer lexer = new JqlLexer(CharStreams.fromString(jql));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JqlParser parser = new JqlParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new JQLBaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				KriptonAssert.assertTrue(false, "unespected char at pos %s of SQL '%s'", charPositionInLine, jql);
			}
		});

		ParserRuleContext context = parser.parse();
		return new Pair<>(context, tokens);
	}

}
