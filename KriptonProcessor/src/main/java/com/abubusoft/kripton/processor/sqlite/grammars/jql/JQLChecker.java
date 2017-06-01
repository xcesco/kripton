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
package com.abubusoft.kripton.processor.sqlite.grammars.jql;

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
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLPlaceHolder.JQLPlaceHolderType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection.ProjectionBuilder;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection.ProjectionType;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlLexer;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Bind_dynamic_sqlContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Bind_parameterContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_nameContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Result_columnContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Table_nameContext;

public class JQLChecker {

	protected static JQLChecker instance;

	public static final JQLChecker getInstance() {
		if (instance == null) {
			instance = new JQLChecker();
		}

		return instance;
	}

	ParseTreeWalker walker = new ParseTreeWalker();

	private JQLChecker() {

	}

	protected <L extends JqlBaseListener> void analyzeInternal(final String jql, L listener) {
		walker.walk(listener, prepareParser(jql).value0);
	}
	
	protected <L extends JqlBaseListener> void analyzeWhereInternal(final String jql, L listener) {
		walker.walk(listener, prepareWhere(jql).value0);
	}

	public <L extends JqlBaseListener> void analyze(final JQL jql, L listener) {
		analyzeInternal(jql.value, listener);
	}

	protected Pair<ParserRuleContext, CommonTokenStream> prepareParser(final String jql) {
		JqlLexer lexer = new JqlLexer(CharStreams.fromString(jql));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JqlParser parser = new JqlParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new JQLBaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
				AssertKripton.assertTrue(false, "unespected char at pos %s of SQL '%s'", charPositionInLine, jql);
			}
		});

		ParserRuleContext context = parser.parse();
		return new Pair<>(context, tokens);
	}
	
	protected Pair<ParserRuleContext, CommonTokenStream> prepareWhere(final String jql) {
		JqlLexer lexer = new JqlLexer(CharStreams.fromString(jql));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JqlParser parser = new JqlParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new JQLBaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
				AssertKripton.assertTrue(false, "unespected char at pos %s of SQL '%s'", charPositionInLine, jql);
			}
		});

		ParserRuleContext context = parser.where_stmt_clauses();
		return new Pair<>(context, tokens);
	}

	/**
	 * Retrieve set of projected field.
	 * 
	 * @param jql
	 * @return
	 */
	public Set<JQLProjection> extractProjections(JQL jql) {
		final Set<JQLProjection> result = new LinkedHashSet<JQLProjection>();

		final One<Boolean> valid = new One<>();
		valid.value0 = false;

		analyzeInternal(jql.value, new JqlBaseListener() {
			@Override
			public void enterResult_column(Result_columnContext ctx) {
				valid.value0 = true;				
				ProjectionBuilder builder = ProjectionBuilder.create();
				if (ctx.expr().column_name() != null) {					
					if (ctx.expr().table_name() != null) {
						builder.table(ctx.expr().table_name().getText());						
					}					
					builder.column(ctx.expr().column_name().getText());
					builder.type(ProjectionType.COLUMN);
				} else {					
					builder.type(ProjectionType.COMPLEX);				
					builder.expression(ctx.expr().getText());
				}
				
				if (ctx.column_alias() != null) {					
					builder.alias(ctx.column_alias().getText());
				} 		
				result.add(builder.build());

			}
	
			@Override
			public void exitResult_column(Result_columnContext ctx) {
				valid.value0 = false;
			}
		});
		return result;
	}

	/**
	 * Replace place holder with element passed by listener
	 * 
	 * @param jql
	 * @param listener
	 * @return
	 * 		string obtained by replacements
	 */
	public String replacePlaceHolders(JQL jql, final JQLPlaceHolderReplacerListener listener) {
		final List<Triple<Token, Token, String>> replace = new ArrayList<>();
				
		JqlBaseListener rewriterListener = new JqlBaseListener() {
									
			@Override
			public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
				String value=listener.onDynamicSQL(ctx.bind_parameter_name().getText());
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
			}

			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				String value=listener.onParameter(ctx.bind_parameter_name().getText());
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
			}
		};
		
		return replaceInternal(jql, replace, rewriterListener);
	}
	
	/**
	 * Replace place holder with element passed by listener
	 * 
	 * @param jql
	 * @param listener
	 * @return
	 * 		string obtained by replacements
	 */
	public String replace(JQL jql, final JQLReplacerListener listener) {
		final List<Triple<Token, Token, String>> replace = new ArrayList<>();
				
		JqlBaseListener rewriterListener = new JqlBaseListener() {
			
			@Override
			public void enterTable_name(Table_nameContext ctx) {
				String value=listener.onTableName(ctx.getText());
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
			}

			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				String value=listener.onColumnValue(ctx.bind_parameter_name().getText());
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
			}

			@Override
			public void enterColumn_name(Column_nameContext ctx) {
				String value=listener.onColumnName(ctx.getText());
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
			}
						
		
		};
					
		return replaceInternal(jql, replace, rewriterListener);

	}

	private String replaceInternal(JQL jql, final List<Triple<Token, Token, String>> replace, JqlBaseListener rewriterListener) {
		Pair<ParserRuleContext, CommonTokenStream> parser = prepareParser(jql.value);
		walker.walk(rewriterListener, parser.value0);

		TokenStreamRewriter rewriter = new TokenStreamRewriter(parser.value1);

		for (Triple<Token, Token, String> item : replace) {
			rewriter.replace(item.value0, item.value1, item.value2);
		}

		return rewriter.getText();
	}
	
	public interface JQLPlaceHolderReplacerListener {
		
		/**
		 * Event fired when we found a parameter: either it is a parameter, either it is dynamic sql
		 * 
		 * @param string
		 * @return
		 */
		String onParameter(String placeHolder);

		String onDynamicSQL(String placeHolder);		
	}
	
	public interface JQLReplacerListener {
		
		String onTableName(String tableName);
		
		String onColumnName(String columnName);

		String onColumnValue(String columnValue);		
	}
	
	public static class JQLParameterName {
		private JQLParameterName(String value) {
			values=value.split("\\.");
		}
		
		private String[] values;
		
		public String getValue() {
			return values[values.length-1];			
		}
		
		public static JQLParameterName parse(String value) {
			return new JQLParameterName(value);
		}
	}

	/**
	 * <p>
	 * Verify sql is syntactally correct, otherwise a KriptonProcessorException
	 * will be thrown.
	 * </p>
	 * 
	 * @param sql
	 */
	public void verify(final JQL jql) {
		this.analyzeInternal(jql.value, new JqlBaseListener());
	}

	/**
	 * Extract all bind parameters and dynamic part used in query.
	 * 
	 * @param jql
	 * @return
	 */
	public List<JQLPlaceHolder> extractPlaceHoldersAsList(String jql) {
		return extractPlaceHolders(jql, new ArrayList<JQLPlaceHolder>());
	}
	
	/**
	 * Extract all bind parameters and dynamic part used in query.
	 * 
	 * @param jql
	 * @return
	 */
	public Set<JQLPlaceHolder> extractPlaceHoldersAsSet(String jql) {
		return extractPlaceHolders(jql, new LinkedHashSet<JQLPlaceHolder>());
	}
	
	/**
	 * Extract all bind parameters and dynamic part used in query.
	 * 
	 * @param jql
	 * @return
	 */
	public Set<JQLPlaceHolder> extractPlaceHoldersFromWhereCondition(String jql) {
		return extractPlaceHoldersFromWhereCondition(jql, new LinkedHashSet<JQLPlaceHolder>());
	}
	
	
	private <L extends Collection<JQLPlaceHolder>> L extractPlaceHolders(String jql, final L result) {		
		final One<Boolean> valid = new One<>();
		valid.value0 = false;

		analyzeInternal(jql, new JqlBaseListener() {
			
			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				result.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, ctx.bind_parameter_name().getText()));				
			}
			
			@Override
			public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
				result.add(new JQLPlaceHolder(JQLPlaceHolderType.DYNAMIC_SQL, ctx.bind_parameter_name().getText()));				
			}						
					
		});
		return result;
	}

	private <L extends Collection<JQLPlaceHolder>> L extractPlaceHoldersFromWhereCondition(String jql, final L result) {		
		final One<Boolean> valid = new One<>();
		valid.value0 = false;

		analyzeWhereInternal(jql, new JqlBaseListener() {
			
			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				result.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, ctx.bind_parameter_name().getText()));				
			}
			
			@Override
			public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
				result.add(new JQLPlaceHolder(JQLPlaceHolderType.DYNAMIC_SQL, ctx.bind_parameter_name().getText()));				
			}						
					
		});
		return result;
	}

		
}
