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
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLPlaceHolder.JQLPlaceHolderType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection.ProjectionBuilder;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection.ProjectionType;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlLexer;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Bind_dynamic_sqlContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Bind_parameterContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_nameContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_name_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_value_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Group_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Having_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Limit_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Offset_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Order_stmtContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Projected_columnsContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Result_columnContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Table_nameContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmtContext;

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

	protected <L extends JqlBaseListener> void analyzeVariableStatementInternal(final String jql, L listener) {
		walker.walk(listener, prepareVariableStatement(jql).value0);
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

	/**
	 * <p>Parse the variable parts of a SQL: </p>
	 * 
	 * <ul>
	 * <li>where_stmt</li>
	 * <li>group_stmt</li>
	 * <li>having_stmt</li>
	 * <li>order_stmt</li>
	 * <li>limit_stmt</li>
	 * <li>offset_stmt</li>
	 * </ul>
	 * 
	 * @param jql
	 * @return
	 */
	protected Pair<ParserRuleContext, CommonTokenStream> prepareVariableStatement(final String jql) {
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

		ParserRuleContext context = parser.parse_variable();
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

		analyzeInternal(jql.value, new JqlBaseListener() {
			@Override
			public void enterResult_column(Result_columnContext ctx) {
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
			}
		});
		return result;
	}

	/**
	 * Replace place holder with element passed by listener
	 * 
	 * @param jql
	 * @param listener
	 * @return string obtained by replacements
	 */
	public String replaceFromVariableStatement(String jql, final JQLReplacerListener listener) {
		rewriterListener.init(listener);	

		return replaceFromVariableStatementInternal(jql, replace, rewriterListener);
	}
	
	List<Triple<Token, Token, String>> replace = new ArrayList<>();
	
	/**
	 * 
	 * @author Francesco Benincasa (abubusoft@gmail.com)
	 *
	 */
	public class JQLRewriterListener extends JqlBaseListener {
		
		private JQLReplacerListener listener;

		public void init(JQLReplacerListener listener) {
			this.listener=listener;
			replace.clear();			
		}
		
		@Override
		public void enterTable_name(Table_nameContext ctx) {
			String value = listener.onTableName(ctx.getText());
			
			// skip without replace
			if (value==null) return;
			
			replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
		}		

		@Override
		public void enterBind_parameter(Bind_parameterContext ctx) {
			String value;
			if (ctx.bind_parameter_name()!=null) {
				value = listener.onBindParameter(ctx.bind_parameter_name().getText());
			} else {
				value = listener.onBindParameter(ctx.getText());
			}
			
			// skip without replace
			if (value==null) return;
			
			replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
		}

		@Override
		public void enterColumn_name(Column_nameContext ctx) {
			String value = listener.onColumnName(ctx.getText());
			
			// skip without replace
			if (value==null) return;
			
			replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
		}
		
		@Override
		public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
			String value = listener.onDynamicSQL(JQLDynamicStatementType.valueOf(ctx.bind_parameter_name().getText()));
			
			// skip without replace
			if (value==null) return;
			
			replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
		}
		
		

		@Override
		public void enterWhere_stmt(Where_stmtContext ctx) {
			listener.onWhereStatementBegin(ctx);
		}

		@Override
		public void exitWhere_stmt(Where_stmtContext ctx) {
			listener.onWhereStatementEnd(ctx);
		}
	}
	
	JQLRewriterListener rewriterListener = new JQLRewriterListener();


	/**
	 * Replace place holder with element passed by listener
	 * 
	 * @param jql
	 * @param listener
	 * @return string obtained by replacements
	 */
	public String replace(JQL jql, final JQLReplacerListener listener) {
		rewriterListener.init(listener);	
		
		return replaceInternal(jql.value, replace, rewriterListener);

	}

	private String replaceInternal(String jql, final List<Triple<Token, Token, String>> replace, JqlBaseListener rewriterListener) {
		Pair<ParserRuleContext, CommonTokenStream> parser = prepareParser(jql);
		walker.walk(rewriterListener, parser.value0);

		TokenStreamRewriter rewriter = new TokenStreamRewriter(parser.value1);

		for (Triple<Token, Token, String> item : replace) {
			rewriter.replace(item.value0, item.value1, item.value2);
		}

		return rewriter.getText();
	}

	private String replaceFromVariableStatementInternal(String jql, final List<Triple<Token, Token, String>> replace, JqlBaseListener rewriterListener) {
		Pair<ParserRuleContext, CommonTokenStream> parser = prepareVariableStatement(jql);
		walker.walk(rewriterListener, parser.value0);

		TokenStreamRewriter rewriter = new TokenStreamRewriter(parser.value1);

		for (Triple<Token, Token, String> item : replace) {
			rewriter.replace(item.value0, item.value1, item.value2);
		}

		return rewriter.getText();
	}

	public interface JQLReplacerListener {
		
		/**
		 * If event return null, replacement will no executed.
		 * 
		 * @param statement
		 * @return
		 * 		<code>null</code> to avoid replacement.
		 */
		String onBindParameter(String bindParameterName);

		/**
		 * If event return null, replacement will no executed.
		 * 
		 * @param statement
		 * @return
		 * 		<code>null</code> to avoid replacement.
		 */
		String onDynamicSQL(JQLDynamicStatementType dynamicStatement);
		

		/**
		 * If event return null, replacement will no executed.
		 * 
		 * @param statement
		 * @return
		 * 		<code>null</code> to avoid replacement.
		 */
		String onTableName(String tableName);

		/**
		 * If event return null, replacement will no executed.
		 * 
		 * @param statement
		 * @return
		 * 		<code>null</code> to avoid replacement.
		 */
		String onColumnName(String columnName);
		
		void onWhereStatementBegin(Where_stmtContext ctx);

		void onWhereStatementEnd(Where_stmtContext ctx);
	}
	
	public static class JQLReplacerListenerImpl implements JQLReplacerListener {

		@Override
		public String onBindParameter(String bindParameterName) {
			return null;
		}

		@Override
		public String onDynamicSQL(JQLDynamicStatementType dynamicStatement) {
			return null;
		}

		@Override
		public String onTableName(String tableName) {
			return null;
		}

		@Override
		public String onColumnName(String columnName) {
			return null;
		}

		@Override
		public void onWhereStatementBegin(Where_stmtContext ctx) {
			
		}

		@Override
		public void onWhereStatementEnd(Where_stmtContext ctx) {
			
		}
		
	}

	/**
	 * Listener to replace variable parts of a sql statement. 
	 * 
	 * If event return null, replacement will no executed.
	 * 
	 * @author Francesco Benincasa (abubusoft@gmail.com)
	 *
	 */
	public interface JQLReplaceVariableStatementListener {

		/**
		 * If event return null, replacement will no executed.
		 * 
		 * @param statement
		 * @return
		 * 		<code>null</code> to avoid replacement.
		 */
		String onWhere(String statement);
		
		/**
		 * If event return null, replacement will no executed.
		 * 
		 * @param statement
		 * @return
		 * 		<code>null</code> to avoid replacement.
		 */
		String onOrderBy(String statement);

		/**
		 * If event return null, replacement will no executed.
		 * 
		 * @param statement
		 * @return
		 * 		<code>null</code> to avoid replacement.
		 */
		String onLimit(String statement);

		/**
		 * If event return null, replacement will no executed.
		 * 
		 * @param statement
		 * @return
		 * 		<code>null</code> to avoid replacement.
		 */
		String onOffset(String statement);

		/**
		 * If event return null, replacement will no executed.
		 * 
		 * @param statement
		 * @return
		 * 		<code>null</code> to avoid replacement.
		 */
		String onGroup(String statement);

		/**
		 * If event return null, replacement will no executed.
		 * 
		 * @param statement
		 * @return
		 * 		<code>null</code> to avoid replacement.
		 */
		String onHaving(String statement);

		/**
		 * If event return null, replacement will no executed.
		 * 
		 * @param statement
		 * @return
		 * 		<code>null</code> to avoid replacement.
		 */
		String onProjectedColumns(String statement);

		/**
		 * If event return null, replacement will no executed.
		 * 
		 * @param statement
		 * @return
		 * 		<code>null</code> to avoid replacement.
		 */
		String onColumnNameSet(String statement);
		
		/**
		 * If event return null, replacement will no executed.
		 * 
		 * @param statement
		 * @return
		 * 		<code>null</code> to avoid replacement.
		 */
		String onColumnValueSet(String statement);

	}
	
	public static class JQLReplaceVariableStatementListenerImpl implements JQLReplaceVariableStatementListener  {

		@Override
		public String onWhere(String statement) {
			return null;
		}

		@Override
		public String onOrderBy(String statement) {
			return null;
		}

		@Override
		public String onLimit(String statement) {
			return null;
		}

		@Override
		public String onOffset(String statement) {
			return null;
		}

		@Override
		public String onGroup(String statement) {
			return null;
		}

		@Override
		public String onHaving(String statement) {
			return null;
		}

		@Override
		public String onProjectedColumns(String statement) {
			return null;
		}

		@Override
		public String onColumnNameSet(String statement) {
			return null;
		}

		@Override
		public String onColumnValueSet(String statement) {
			return null;
		}
		

	}
	
	

	public static class JQLParameterName {
		private JQLParameterName(String value) {
			values = value.split("\\.");
		}

		private String[] values;

		public String getValue() {
			return values[values.length - 1];
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
	public Set<JQLPlaceHolder> extractPlaceHoldersFromVariableStatementAsSet(String jql) {
		return extractPlaceHoldersFromVariableStatement(jql, new LinkedHashSet<JQLPlaceHolder>());
	}

	/**
	 * Extract all bind parameters and dynamic part used in query.
	 * 
	 * @param jql
	 * @return
	 */
	public List<JQLPlaceHolder> extractFromVariableStatement(String jql) {
		return extractPlaceHoldersFromVariableStatement(jql, new ArrayList<JQLPlaceHolder>());
	}

	private <L extends Collection<JQLPlaceHolder>> L extractPlaceHolders(String jql, final L result) {
		final One<Boolean> valid = new One<>();
		valid.value0 = false;

		analyzeInternal(jql, new JqlBaseListener() {

			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				String value;
				if (ctx.bind_parameter_name()!=null) {
					value = ctx.bind_parameter_name().getText();
				} else {
					value = ctx.getText();
				}
				result.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, value));
			}

			@Override
			public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
				result.add(new JQLPlaceHolder(JQLPlaceHolderType.DYNAMIC_SQL, ctx.bind_parameter_name().getText()));
			}

		});
		return result;
	}

	private <L extends Collection<JQLPlaceHolder>> L extractPlaceHoldersFromVariableStatement(String jql, final L result) {
		final One<Boolean> valid = new One<>();
		
		if (!StringUtils.hasText(jql)) return result;		
		
		valid.value0 = false;

		analyzeVariableStatementInternal(jql, new JqlBaseListener() {

			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				String parameter;
				if (ctx.bind_parameter_name()!=null)
				{
					parameter=ctx.bind_parameter_name().getText();
				} else {
					parameter=ctx.getText();
				}
				result.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, parameter));
			}

			@Override
			public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
				result.add(new JQLPlaceHolder(JQLPlaceHolderType.DYNAMIC_SQL, ctx.bind_parameter_name().getText()));
			}

		});
		return result;
	}

	/**
	 * Given a sql, replace som component like where, order by, etc..
	 * @param jql
	 * @param listener
	 * @return
	 */
	public String replaceVariableStatements(final String jql, final JQLReplaceVariableStatementListener listener) {
		final List<Triple<Token, Token, String>> replace = new ArrayList<>();

		JqlBaseListener rewriterListener = new JqlBaseListener() {
			
			@Override
			public void enterProjected_columns(Projected_columnsContext ctx) {
				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;
				
				if (start==stop) return;
				
				String statement = jql.substring(start, stop);
				
				String value = listener.onProjectedColumns(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}

			@Override
			public void enterWhere_stmt(Where_stmtContext ctx) {
				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;
				
				if (start==stop) return;
				
				String statement = jql.substring(start, stop);
				
				String value = listener.onWhere(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}
			
			@Override
			public void enterOrder_stmt(Order_stmtContext ctx) {
				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;
				
				if (start==stop) return;
				
				String statement = jql.substring(start, stop);
				
				String value = listener.onOrderBy(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}
			
			@Override
			public void enterGroup_stmt(Group_stmtContext ctx) {
				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;
				
				if (start==stop) return;
				
				String statement = jql.substring(start, stop);
				
				String value = listener.onGroup(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}
			
			@Override
			public void enterHaving_stmt(Having_stmtContext ctx) {
				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;
				
				if (start==stop) return;
				
				String statement = jql.substring(start, stop);
				
				String value = listener.onHaving(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}
			
			
			@Override
			public void enterOffset_stmt(Offset_stmtContext ctx) {
				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;
				
				if (start==stop) return;
				
				String statement = jql.substring(start, stop);
				
				String value = listener.onOffset(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}
			
			@Override
			public void enterLimit_stmt(Limit_stmtContext ctx) {
				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;
				
				if (start==stop) return;
				
				String statement = jql.substring(start, stop);
				
				String value = listener.onLimit(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}
			
			@Override
			public void enterColumn_name_set(Column_name_setContext ctx) {
				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;
				
				if (start==stop) return;
				
				String statement = jql.substring(start, stop);
				
				String value = listener.onColumnNameSet(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}
			
			@Override
			public void enterColumn_value_set(Column_value_setContext ctx) {
				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;
				
				if (start==stop) return;
				
				String statement = jql.substring(start, stop);
				
				String value = listener.onColumnValueSet(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}
			
			
		};

		return replaceInternal(jql, replace, rewriterListener);
	}

}
