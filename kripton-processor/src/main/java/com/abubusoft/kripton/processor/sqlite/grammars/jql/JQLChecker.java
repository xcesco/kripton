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
import com.abubusoft.kripton.processor.core.Finder;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLPlaceHolder.JQLPlaceHolderType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection.ProjectionBuilder;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection.ProjectionType;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlLexer;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Bind_dynamic_sqlContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Bind_parameterContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_aliasContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_fully_qualified_nameContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_nameContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_name_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_name_to_updateContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_value_setContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Columns_to_updateContext;
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
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Where_stmt_in_clauseContext;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;

/**
 * The Class JQLChecker.
 */
public class JQLChecker {

	/** The instance. */
	protected static JQLChecker instance;

	/**
	 * Gets the single instance of JQLChecker.
	 *
	 * @return single instance of JQLChecker
	 */
	public static final JQLChecker getInstance() {
		if (instance == null) {
			instance = new JQLChecker();
		}

		return instance;
	}

	/** The walker. */
	ParseTreeWalker walker = new ParseTreeWalker();

	/**
	 * Instantiates a new JQL checker.
	 */
	private JQLChecker() {

	}

	/**
	 * Analyze internal.
	 *
	 * @param <L>
	 *            the generic type
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 * @param listener
	 *            the listener
	 */
	protected <L extends JqlBaseListener> void analyzeInternal(JQLContext jqlContext, final String jql, L listener) {
		walker.walk(listener, prepareParser(jqlContext, jql).value0);
	}

	/**
	 * Analyze variable statement internal.
	 *
	 * @param <L>
	 *            the generic type
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 * @param listener
	 *            the listener
	 */
	protected <L extends JqlBaseListener> void analyzeVariableStatementInternal(JQLContext jqlContext, final String jql, L listener) {
		walker.walk(listener, prepareVariableStatement(jqlContext, jql).value0);
	}

	/**
	 * Analyze.
	 *
	 * @param <L>
	 *            the generic type
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 * @param listener
	 *            the listener
	 */
	public <L extends JqlBaseListener> void analyze(final JQLContext jqlContext, final JQL jql, L listener) {
		analyzeInternal(jqlContext, jql.value, listener);
	}

	/**
	 * Prepare parser.
	 *
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 * @return the pair
	 */
	protected Pair<ParserRuleContext, CommonTokenStream> prepareParser(final JQLContext jqlContext, final String jql) {
		JqlLexer lexer = new JqlLexer(CharStreams.fromString(jql));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JqlParser parser = new JqlParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new JQLBaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
				AssertKripton.assertTrue(false, jqlContext.getContextDescription() + ": unespected char at pos %s of SQL '%s'", charPositionInLine, jql);
			}
		});

		ParserRuleContext context = parser.parse();
		return new Pair<>(context, tokens);
	}

	/**
	 * <p>
	 * Parse the variable parts of a SQL:
	 * </p>
	 * 
	 * <ul>
	 * <li>where_stmt</li>
	 * <li>group_stmt</li>
	 * <li>having_stmt</li>
	 * <li>order_stmt</li>
	 * <li>limit_stmt</li>
	 * <li>offset_stmt</li>
	 * </ul>
	 * .
	 *
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 * @return the pair
	 */
	protected Pair<ParserRuleContext, CommonTokenStream> prepareVariableStatement(final JQLContext jqlContext, final String jql) {
		JqlLexer lexer = new JqlLexer(CharStreams.fromString(jql));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JqlParser parser = new JqlParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new JQLBaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
				AssertKripton.assertTrue(false, jqlContext.getContextDescription() + ": unespected char at pos %s of JQL '%s'", charPositionInLine, jql);
			}
		});

		ParserRuleContext context = parser.parse_variable();
		return new Pair<>(context, tokens);
	}

	/**
	 * Retrieve set of projected field.
	 *
	 * @param jqlContext
	 *            the jql context
	 * @param jqlValue
	 *            the jql value
	 * @param entity
	 *            the entity
	 * @return the sets the
	 */
	public Set<JQLProjection> extractProjections(final JQLContext jqlContext, String jqlValue, final Finder<SQLProperty> entity) {
		final Set<JQLProjection> result = new LinkedHashSet<JQLProjection>();

		final One<Boolean> projection = new One<Boolean>(null);

		analyzeInternal(jqlContext, jqlValue, new JqlBaseListener() {

			@Override
			public void enterProjected_columns(Projected_columnsContext ctx) {
				if (projection.value0 == null) {
					projection.value0 = true;
				}
			}

			@Override
			public void exitProjected_columns(Projected_columnsContext ctx) {
				projection.value0 = false;
			}

			@Override
			public void enterResult_column(Result_columnContext ctx) {
				if (projection.value0 != true)
					return;
				ProjectionBuilder builder = ProjectionBuilder.create();

				if (ctx.getText().endsWith("*")) {
					builder.type(ProjectionType.STAR);
				} else if (ctx.table_name() != null) {
					builder.table(ctx.expr().table_name().getText());
				} else if (ctx.expr().column_fully_qualified_name() != null && ctx.expr().column_fully_qualified_name().column_simple_name() != null) {
					Finder<SQLProperty> currentEntity = entity;
					if (ctx.expr().column_fully_qualified_name().table_simple_name() != null) {
						String entityName = ctx.expr().column_fully_qualified_name().table_simple_name().getText();
						builder.table(entityName);
						currentEntity = jqlContext.findEntityByName(entityName);
					}

					String jqlColumnName = ctx.expr().column_fully_qualified_name().column_simple_name().getText();
					builder.column(jqlColumnName);

					SQLProperty property = currentEntity.findPropertyByName(jqlColumnName);
					AssertKripton.assertTrueOrUnknownPropertyInJQLException(property != null, jqlContext, jqlColumnName);

					builder.property(property);
					builder.type(ProjectionType.COLUMN);
				} else {
					builder.type(ProjectionType.COMPLEX);
					builder.expression(ctx.expr().getText());
				}

				if (ctx.column_alias() != null) {
					String columnAlias = ctx.column_alias().getText();

					SQLProperty property = entity.findPropertyByName(columnAlias);
					AssertKripton.assertTrueOrUnknownPropertyInJQLException(property != null, jqlContext, columnAlias);

					builder.property(property);
					builder.type(ProjectionType.COLUMN);

					builder.alias(columnAlias);
				}
				result.add(builder.build());

			}

			@Override
			public void exitResult_column(Result_columnContext ctx) {
			}
		});

		if (result.size() == 1 && result.toArray(new JQLProjection[1])[0].type == ProjectionType.STAR) {
			// the projected columns are full
			result.clear();

			if (entity != null) {

				for (SQLProperty item : entity.getCollection()) {
					JQLProjection col = new JQLProjection(ProjectionType.COLUMN, entity.getSimpleName(), item.getName(), null, null, item);
					result.add(col);
				}
			}
		}

		return result;
	}

	/**
	 * Extract columns to insert or update.
	 *
	 * @param jqlContext
	 *            the jql context
	 * @param jqlValue
	 *            the jql value
	 * @param entity
	 *            the entity
	 * @return the sets the
	 */
	public Set<String> extractColumnsToInsertOrUpdate(final JQLContext jqlContext, String jqlValue, final Finder<SQLProperty> entity) {
		final Set<String> result = new LinkedHashSet<String>();

		final One<Boolean> selectionOn = new One<Boolean>(null);
		final One<Boolean> insertOn = new One<Boolean>(null);

		// Column_name_set is needed for insert
		// Columns_to_update is needed for update
		analyzeInternal(jqlContext, jqlValue, new JqlBaseListener() {

			@Override
			public void enterColumn_name_set(Column_name_setContext ctx) {
				if (insertOn.value0 == null) {
					insertOn.value0 = true;
				}
			}

			@Override
			public void exitColumn_name_set(Column_name_setContext ctx) {
				insertOn.value0 = false;
			}

			@Override
			public void enterColumns_to_update(Columns_to_updateContext ctx) {
				if (selectionOn.value0 == null) {
					selectionOn.value0 = true;
				}
			}

			@Override
			public void exitColumns_to_update(Columns_to_updateContext ctx) {
				selectionOn.value0 = false;
			}

			@Override
			public void enterColumn_name(Column_nameContext ctx) {
				// works for INSERTS
				if (insertOn.value0 != null && insertOn.value0 == true) {
					result.add(ctx.getText());
				}
			}

			@Override
			public void enterColumn_name_to_update(Column_name_to_updateContext ctx) {
				result.add(ctx.getText());
			}

		});

		return result;
	}

	/**
	 * Replace place holder with element passed by listener.
	 *
	 * @param context
	 *            the context
	 * @param jql
	 *            the jql
	 * @param listener
	 *            the listener
	 * @return string obtained by replacements
	 */
	public String replaceFromVariableStatement(JQLContext context, String jql, final JQLReplacerListener listener) {
		JQLRewriterListener rewriterListener = new JQLRewriterListener();
		rewriterListener.init(listener);

		return replaceFromVariableStatementInternal(context, jql, replace, rewriterListener);
	}

	/** The replace. */
	List<Triple<Token, Token, String>> replace = new ArrayList<>();

	/**
	 * The listener interface for receiving JQLRewriter events. The class that
	 * is interested in processing a JQLRewriter event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's <code>addJQLRewriterListener</code>
	 * method. When the JQLRewriter event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @author Francesco Benincasa (info@abubusoft.com)
	 */
	public class JQLRewriterListener extends JqlBaseListener {

		private boolean inStatement = false;

		/** The listener. */
		private JQLReplacerListener listener;

		/**
		 * Inits the.
		 *
		 * @param listener
		 *            the listener
		 */
		public void init(JQLReplacerListener listener) {
			this.listener = listener;
			replace.clear();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener#
		 * enterTable_name(com.abubusoft.kripton.processor.sqlite.grammars.jsql.
		 * JqlParser.Table_nameContext)
		 */
		@Override
		public void enterTable_name(Table_nameContext ctx) {
			String value = listener.onTableName(ctx.getText());

			// skip without replace
			if (value == null)
				return;

			replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener#
		 * enterBind_parameter(com.abubusoft.kripton.processor.sqlite.grammars.
		 * jsql.JqlParser.Bind_parameterContext)
		 */
		@Override
		public void enterBind_parameter(Bind_parameterContext ctx) {
			String value;
			if (ctx.bind_parameter_name() != null) {
				value = listener.onBindParameter(ctx.bind_parameter_name().getText(), inStatement);
			} else {
				value = listener.onBindParameter(ctx.getText(), inStatement);
			}

			// skip without replace
			if (value == null)
				return;

			replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener#
		 * enterColumn_name_to_update(com.abubusoft.kripton.processor.sqlite.
		 * grammars.jsql.JqlParser.Column_name_to_updateContext)
		 */
		@Override
		public void enterColumn_name_to_update(Column_name_to_updateContext ctx) {
			String value = listener.onColumnNameToUpdate(ctx.getText());

			// skip without replace
			if (value == null)
				return;

			replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener#
		 * enterColumn_fully_qualified_name(com.abubusoft.kripton.processor.
		 * sqlite.grammars.jsql.JqlParser.Column_fully_qualified_nameContext)
		 */
		@Override
		public void enterColumn_fully_qualified_name(Column_fully_qualified_nameContext ctx) {
			String value = listener.onColumnFullyQualifiedName(ctx.table_simple_name() != null ? ctx.table_simple_name().getText() : "", ctx.column_simple_name().getText());

			// skip without replace
			if (value == null)
				return;

			replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener#
		 * enterColumn_name(com.abubusoft.kripton.processor.sqlite.grammars.jsql
		 * .JqlParser.Column_nameContext)
		 */
		@Override
		public void enterColumn_name(Column_nameContext ctx) {
			String value = listener.onColumnName(ctx.getText());

			// skip without replace
			if (value == null)
				return;

			replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
		}

		@Override
		public void enterColumn_alias(Column_aliasContext ctx) {
			String value = listener.onColumnAlias(ctx.getText());

			// skip without replace
			if (value == null)
				return;

			replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener#
		 * enterBind_dynamic_sql(com.abubusoft.kripton.processor.sqlite.grammars
		 * .jsql.JqlParser.Bind_dynamic_sqlContext)
		 */
		@Override
		public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
			String value = listener.onDynamicSQL(JQLDynamicStatementType.valueOf(ctx.bind_parameter_name().getText()));

			// skip without replace
			if (value == null)
				return;

			replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener#
		 * enterWhere_stmt(com.abubusoft.kripton.processor.sqlite.grammars.jsql.
		 * JqlParser.Where_stmtContext)
		 */
		@Override
		public void enterWhere_stmt(Where_stmtContext ctx) {
			listener.onWhereStatementBegin(ctx);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener#
		 * exitWhere_stmt(com.abubusoft.kripton.processor.sqlite.grammars.jsql.
		 * JqlParser.Where_stmtContext)
		 */
		@Override
		public void exitWhere_stmt(Where_stmtContext ctx) {
			listener.onWhereStatementEnd(ctx);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener#
		 * enterColumn_name_set(com.abubusoft.kripton.processor.sqlite.grammars.
		 * jsql.JqlParser.Column_name_setContext)
		 */
		@Override
		public void enterColumn_name_set(Column_name_setContext ctx) {
			listener.onColumnNameSetBegin(ctx);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener#
		 * exitColumn_name_set(com.abubusoft.kripton.processor.sqlite.grammars.
		 * jsql.JqlParser.Column_name_setContext)
		 */
		@Override
		public void exitColumn_name_set(Column_name_setContext ctx) {
			listener.onColumnNameSetEnd(ctx);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener#
		 * enterColumn_value_set(com.abubusoft.kripton.processor.sqlite.grammars
		 * .jsql.JqlParser.Column_value_setContext)
		 */
		@Override
		public void enterColumn_value_set(Column_value_setContext ctx) {
			listener.onColumnValueSetBegin(ctx);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener#
		 * exitColumn_value_set(com.abubusoft.kripton.processor.sqlite.grammars.
		 * jsql.JqlParser.Column_value_setContext)
		 */
		@Override
		public void exitColumn_value_set(Column_value_setContext ctx) {
			listener.onColumnValueSetEnd(ctx);
		}

		@Override
		public void enterWhere_stmt_in_clause(Where_stmt_in_clauseContext ctx) {
			inStatement = true;
		}

		@Override
		public void exitWhere_stmt_in_clause(Where_stmt_in_clauseContext ctx) {
			inStatement = false;
		}
	}

	/**
	 * Replace place holder with element passed by listener.
	 *
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 * @param listener
	 *            the listener
	 * @return string obtained by replacements
	 */
	public String replace(final JQLContext jqlContext, JQL jql, final JQLReplacerListener listener) {
		return replace(jqlContext, jql.value, listener);
	}

	/**
	 * Replace place holder with element passed by listener.
	 *
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            string
	 * @param listener
	 *            the listener
	 * @return string obtained by replacements
	 */
	public String replace(final JQLContext jqlContext, String jql, final JQLReplacerListener listener) {
		JQLRewriterListener rewriterListener = new JQLRewriterListener();
		rewriterListener.init(listener);

		return replaceInternal(jqlContext, jql, replace, rewriterListener);

	}

	/**
	 * Replace internal.
	 *
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 * @param replace
	 *            the replace
	 * @param rewriterListener
	 *            the rewriter listener
	 * @return the string
	 */
	private String replaceInternal(final JQLContext jqlContext, String jql, final List<Triple<Token, Token, String>> replace, JqlBaseListener rewriterListener) {
		Pair<ParserRuleContext, CommonTokenStream> parser = prepareParser(jqlContext, jql);
		walker.walk(rewriterListener, parser.value0);

		TokenStreamRewriter rewriter = new TokenStreamRewriter(parser.value1);

		for (Triple<Token, Token, String> item : replace) {
			rewriter.replace(item.value0, item.value1, item.value2);
		}

		return rewriter.getText();
	}

	/**
	 * Replace from variable statement internal.
	 *
	 * @param context
	 *            the context
	 * @param jql
	 *            the jql
	 * @param replace
	 *            the replace
	 * @param rewriterListener
	 *            the rewriter listener
	 * @return the string
	 */
	private String replaceFromVariableStatementInternal(JQLContext context, String jql, final List<Triple<Token, Token, String>> replace, JqlBaseListener rewriterListener) {
		Pair<ParserRuleContext, CommonTokenStream> parser = prepareVariableStatement(context, jql);
		walker.walk(rewriterListener, parser.value0);

		TokenStreamRewriter rewriter = new TokenStreamRewriter(parser.value1);

		for (Triple<Token, Token, String> item : replace) {
			rewriter.replace(item.value0, item.value1, item.value2);
		}

		return rewriter.getText();
	}

	/**
	 * The Class JQLParameterName.
	 */
	public static class JQLParameterName {

		/**
		 * Instantiates a new JQL parameter name.
		 *
		 * @param value
		 *            the value
		 */
		private JQLParameterName(String value) {
			values = value.split("\\.");
		}

		/** The values. */
		private String[] values;

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public String getValue() {
			return values[values.length - 1];
		}

		/**
		 * Gets the bean name.
		 *
		 * @return the bean name
		 */
		public String getBeanName() {
			if (isNested())
				return values[0];

			return "";
		}

		/**
		 * Checks if is nested.
		 *
		 * @return true, if is nested
		 */
		public boolean isNested() {
			return values.length > 1;
		}

		/**
		 * Parses the.
		 *
		 * @param value
		 *            the value
		 * @return the JQL parameter name
		 */
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
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 */
	public void verify(final JQLContext jqlContext, final JQL jql) {
		this.analyzeInternal(jqlContext, jql.value, new JqlBaseListener());
	}

	/**
	 * Extract all bind parameters and dynamic part used in query.
	 *
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 * @return the list
	 */
	public List<JQLPlaceHolder> extractPlaceHoldersAsList(final JQLContext jqlContext, String jql) {
		return extractPlaceHolders(jqlContext, jql, new ArrayList<JQLPlaceHolder>());
	}

	/**
	 * Extract all bind parameters and dynamic part used in query.
	 *
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 * @return the sets the
	 */
	public Set<JQLPlaceHolder> extractPlaceHoldersAsSet(final JQLContext jqlContext, String jql) {
		return extractPlaceHolders(jqlContext, jql, new LinkedHashSet<JQLPlaceHolder>());
	}

	/**
	 * Extract all bind parameters and dynamic part used in query.
	 *
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 * @return the sets the
	 */
	public Set<JQLPlaceHolder> extractPlaceHoldersFromVariableStatementAsSet(JQLContext jqlContext, String jql) {
		return extractPlaceHoldersFromVariableStatement(jqlContext, jql, new LinkedHashSet<JQLPlaceHolder>());
	}

	/**
	 * Extract all bind parameters and dynamic part used in query.
	 *
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 * @return the list
	 */
	public List<JQLPlaceHolder> extractFromVariableStatement(JQLContext jqlContext, String jql) {
		return extractPlaceHoldersFromVariableStatement(jqlContext, jql, new ArrayList<JQLPlaceHolder>());
	}

	/**
	 * Extract place holders.
	 *
	 * @param <L>
	 *            the generic type
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 * @param result
	 *            the result
	 * @return the l
	 */
	private <L extends Collection<JQLPlaceHolder>> L extractPlaceHolders(final JQLContext jqlContext, String jql, final L result) {
		final One<Boolean> valid = new One<>();
		valid.value0 = false;

		analyzeInternal(jqlContext, jql, new JqlBaseListener() {

			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				String value;
				if (ctx.bind_parameter_name() != null) {
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

	/**
	 * Extract place holders from variable statement.
	 *
	 * @param <L>
	 *            the generic type
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 * @param result
	 *            the result
	 * @return the l
	 */
	private <L extends Collection<JQLPlaceHolder>> L extractPlaceHoldersFromVariableStatement(final JQLContext jqlContext, String jql, final L result) {
		final One<Boolean> valid = new One<>();

		if (!StringUtils.hasText(jql))
			return result;

		valid.value0 = false;

		analyzeVariableStatementInternal(jqlContext, jql, new JqlBaseListener() {

			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				String parameter;
				if (ctx.bind_parameter_name() != null) {
					parameter = ctx.bind_parameter_name().getText();
				} else {
					parameter = ctx.getText();
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
	 * <p>
	 * Given a sql, replace som component like where, order by, etc..
	 * 
	 * <p>
	 * Note that only first level of variable statements will be replaced.
	 *
	 * @param jqlContext
	 *            the jql context
	 * @param jql
	 *            the jql
	 * @param listener
	 *            the listener
	 * @return the string
	 */
	public String replaceVariableStatements(final JQLContext jqlContext, final String jql, final JQLReplaceVariableStatementListener listener) {
		final List<Triple<Token, Token, String>> replace = new ArrayList<>();
		final One<Integer> currentSelectLevel = new One<Integer>(-1);

		JqlBaseListener rewriterListener = new JqlBaseListener() {

			@Override
			public void enterSelect_core(Select_coreContext ctx) {
				currentSelectLevel.value0++;
			}

			@Override
			public void enterSelect_or_values(Select_or_valuesContext ctx) {
				currentSelectLevel.value0++;
			}

			@Override
			public void exitSelect_core(Select_coreContext ctx) {
				currentSelectLevel.value0--;
			}

			@Override
			public void exitSelect_or_values(Select_or_valuesContext ctx) {
				currentSelectLevel.value0--;
			}

			@Override
			public void enterProjected_columns(Projected_columnsContext ctx) {
				// we work on level 0
				if (currentSelectLevel.value0 > 0)
					return;

				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;

				if (start == stop)
					return;

				String statement = jql.substring(start, stop);

				String value = listener.onProjectedColumns(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}

			@Override
			public void enterWhere_stmt(Where_stmtContext ctx) {
				// we work on level 0
				if (currentSelectLevel.value0 > 0)
					return;

				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;

				if (start == stop)
					return;

				String statement = jql.substring(start, stop);

				String value = listener.onWhere(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}

			@Override
			public void enterOrder_stmt(Order_stmtContext ctx) {
				// we work on level 0
				if (currentSelectLevel.value0 > 0)
					return;

				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;

				if (start == stop)
					return;

				String statement = jql.substring(start, stop);

				String value = listener.onOrderBy(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}

			@Override
			public void enterGroup_stmt(Group_stmtContext ctx) {
				// we work on level 0
				if (currentSelectLevel.value0 > 0)
					return;

				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;

				if (start == stop)
					return;

				String statement = jql.substring(start, stop);

				String value = listener.onGroup(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}

			@Override
			public void enterHaving_stmt(Having_stmtContext ctx) {
				// we work on level 0
				if (currentSelectLevel.value0 > 0)
					return;

				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;

				if (start == stop)
					return;

				String statement = jql.substring(start, stop);

				String value = listener.onHaving(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}

			@Override
			public void enterOffset_stmt(Offset_stmtContext ctx) {
				// we work on level 0
				if (currentSelectLevel.value0 > 0)
					return;

				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;

				if (start == stop)
					return;

				String statement = jql.substring(start, stop);

				String value = listener.onOffset(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}

			@Override
			public void enterLimit_stmt(Limit_stmtContext ctx) {
				// we work on level 0
				if (currentSelectLevel.value0 > 0)
					return;

				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 1;

				if (start == stop)
					return;

				String statement = jql.substring(start, stop);

				String value = listener.onLimit(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}

			@Override
			public void enterColumn_name_set(Column_name_setContext ctx) {
				// we work on level 0
				if (currentSelectLevel.value0 > 0)
					return;

				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 2;

				if (start == stop)
					return;

				String statement = jql.substring(start, stop);

				String value = listener.onColumnNameSet(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}

			@Override
			public void enterColumn_value_set(Column_value_setContext ctx) {
				// we work on level 0
				if (currentSelectLevel.value0 > 0)
					return;

				int start = ctx.getStart().getStartIndex() - 1;
				int stop = ctx.getStop().getStopIndex() + 2;

				if (start == stop)
					return;

				String statement = jql.substring(start, stop);

				String value = listener.onColumnValueSet(statement);

				if (value != null) {
					replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
				}
			}

		};

		return replaceInternal(jqlContext, jql, replace, rewriterListener);
	}

}
