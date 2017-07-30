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
package com.abubusoft.kripton.android.sqlite.migration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlLexer;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Bind_dynamic_sqlContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Bind_parameterContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_nameContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_name_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_name_to_updateContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_value_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Group_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Having_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Limit_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Offset_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Order_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Projected_columnsContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Result_columnContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Select_coreContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Select_or_valuesContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Table_nameContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmtContext;

public class MigrationSQLChecker {

	protected static MigrationSQLChecker instance;

	public static final MigrationSQLChecker getInstance() {
		if (instance == null) {
			instance = new MigrationSQLChecker();
		}

		return instance;
	}

	ParseTreeWalker walker = new ParseTreeWalker();

	private MigrationSQLChecker() {

	}

	protected <L extends JqlBaseListener> void analyzeInternal(final String jql, L listener) {
		walker.walk(listener, prepareParser(jql).value0);
	}

	public <L extends JqlBaseListener> void analyze(final String sql, L listener) {
		analyzeInternal(sql, listener);
	}

	protected Pair<ParserRuleContext, CommonTokenStream> prepareParser(final String jql) {
		JqlLexer lexer = new JqlLexer(CharStreams.fromString(jql));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JqlParser parser = new JqlParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new JQLBaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				AssertKripton.assertTrue(false, "unespected char at pos %s of SQL '%s'", charPositionInLine, jql);
			}
		});

		ParserRuleContext context = parser.parse();
		return new Pair<>(context, tokens);
	}

}
