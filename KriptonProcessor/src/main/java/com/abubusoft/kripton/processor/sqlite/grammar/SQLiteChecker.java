/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.grammar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import com.abubusoft.kripton.processor.sqlite.grammar.Parameter.ParameterType;
import com.abubusoft.kripton.processor.sqlite.grammar.Projection.ProjectionBuilder;
import com.abubusoft.kripton.processor.sqlite.grammar.Projection.ProjectionType;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Bind_dynamic_sqlContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Bind_parameterContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Bind_parameter_nameContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Column_nameContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Result_columnContext;

import sqlite.sqlchecker.Parameter;

public class SQLiteChecker {

	protected static SQLiteChecker instance;

	public static final SQLiteChecker getInstance() {
		if (instance == null) {
			instance = new SQLiteChecker();
		}

		return instance;
	}

	ParseTreeWalker walker = new ParseTreeWalker();

	private SQLiteChecker() {

	}

	protected <L extends SQLiteBaseListener> void analyzeInternal(final String input, L listener) {
		walker.walk(listener, prepareParser(input).value0);
	}

	public <L extends SQLiteBaseListener> void analyze(final String input, L listener) {
		analyzeInternal(input, listener);
	}

	protected Pair<ParserRuleContext, CommonTokenStream> prepareParser(final String sql) {
		SQLiteLexer lexer = new SQLiteLexer(CharStreams.fromString(sql));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SQLiteParser parser = new SQLiteParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new SQLiteBaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
				AssertKripton.assertTrue(false, "unespected char at pos %s of SQL '%s'", charPositionInLine, sql);
			}
		});

		ParserRuleContext context = parser.parse();
		return new Pair<>(context, tokens);
	}

	/**
	 * Retrieve set of projected field.
	 * 
	 * @param sql
	 * @return
	 */
	public Set<Projection> extractProjectedFields(String sql) {
		final Set<Projection> result = new LinkedHashSet<Projection>();

		final One<Boolean> valid = new One<>();
		valid.value0 = false;

		analyzeInternal(sql, new SQLiteBaseListener() {
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

	public String replaceBindParameters(String sql, List<Parameter> parameter, final BindParameterReplacerListener listener) {
		final List<Triple<Token, Token, String>> replace = new ArrayList<>();
				
		SQLiteBaseListener rewriterListener = new SQLiteBaseListener() {
			@Override
			public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
				listener.onBindParameter(parameter);
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, ":dynamic"));
			}

			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, ":bind"));
			}
		};
		
		this.analyzeInternal(sql, new SQLiteBaseListener() {
			
		});
		
		Pair<ParserRuleContext, CommonTokenStream> parser = prepareParser(sql);
		walker.walk(rewriterListener, parser.value0);

		TokenStreamRewriter rewriter = new TokenStreamRewriter(parser.value1);

		for (Triple<Token, Token, String> item : replace) {
			rewriter.replace(item.value0, item.value1, item.value2);
		}

		return rewriter.getText();

	}
	
	public interface BindParameterReplacerListener {
		
		String onBindParameter(Parameter parameter);
		
	}

	/**
	 * <p>
	 * Verify sql is syntactally correct, otherwise a KriptonProcessorException
	 * will be thrown.
	 * </p>
	 * 
	 * @param sql
	 */
	public void verify(final String sql) {
		this.analyzeInternal(sql, new SQLiteBaseListener());
	}

	/**
	 * Extract all bind parameters and dynamic part used in query. It return a list, so duplicate are permitted.
	 * 
	 * @param sql
	 * @return
	 */
	public List<Parameter> extractBindParameters(String sql) {
		final List<Parameter> result = new ArrayList<Parameter>();

		final One<Boolean> valid = new One<>();
		valid.value0 = false;

		analyzeInternal(sql, new SQLiteBaseListener() {
			
			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				if (ctx.bind_parameter_name().getChildCount()==1) {
					result.add(new Parameter(ParameterType.PARAMETER_SIMPLE, ctx.bind_parameter_name().getText()));
				} else {
					result.add(new Parameter(ParameterType.PARAMETER_INNER_FIELD, ctx.bind_parameter_name().getText()));
				}
			}
			
			@Override
			public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
				if (ctx.bind_parameter_name().getChildCount()==1) {
					result.add(new Parameter(ParameterType.DYNAMIC_SQL_SIMPLE, ctx.bind_parameter_name().getText()));
				} else {
					result.add(new Parameter(ParameterType.DYNAMIC_SQL_INNER_FIELD, ctx.bind_parameter_name().getText()));
				}
			}
			
			
					
		});
		return result;
	}

}
